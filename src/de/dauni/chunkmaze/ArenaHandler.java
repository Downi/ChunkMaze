package de.dauni.chunkmaze;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.dauni.chunkmaze.Arena.ArenaState;
import de.dauni.chunkmaze.PermissionsC.Permissions;
import de.dauni.chunkmaze.Managers.MessageM;
import de.dauni.chunkmaze.Managers.PermissionsM;
import de.dauni.chunkmaze.Serializables.LocationSerializable;
import de.dauni.chunkmaze.W;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import com.earth2me.essentials.api.Economy;
import com.earth2me.essentials.api.NoLoanPermittedException;
import com.earth2me.essentials.api.UserDoesNotExistException;

@SuppressWarnings("deprecation")
public class ArenaHandler {
	public static void loadArenas() {
		W.arenaList.clear();
		for (String arenaName : W.arenas.getFile().getKeys(false)) {
			W.arenaList.add((Arena) W.arenas.getFile().get(arenaName));
		}
	}

	public static void sendMessage(Arena arena, String message, String... vars) {
		for (Player player : arena.playersInArena) {
			String pMessage = message.replaceAll("%player%", player.getName());
			player.sendMessage(MessageM.replaceAll(pMessage, vars));
		}
	}

	public static void sendFMessage(Arena arena, ConfigC location,
			String... vars) {
		for (Player player : arena.playersInArena) {
			String pMessage = location.config.getFile().get(location.location)
					.toString().replaceAll("%player%", player.getName());
			player.sendMessage(MessageM.replaceAll(pMessage, vars));
		}
	}

	public static void playerJoinArena(Player player, String arenaname) {
		boolean found = false;
		boolean alreadyJoined = false;
		for (Arena arena : W.arenaList) {
			if (arena.playersInArena != null) {
				if (arena.playersInArena.contains(player)) {
					alreadyJoined = true;
				}
			}
		}

		if (!alreadyJoined) {
			for (Arena arena : W.arenaList) {
				if (arena.arenaName.equalsIgnoreCase(arenaname)) {
					found = true;
						boolean inventoryempty = true;
						for (ItemStack invitem : player.getInventory()) {
							if (invitem != null) {
								if (invitem.getType() != Material.AIR) {
									inventoryempty = false;
								}
							}
						}

						for (ItemStack invitem : player.getInventory()
								.getArmorContents()) {
							if (invitem.getType() != Material.AIR) {
								inventoryempty = false;
							}
						}

						if ((Boolean) W.config
								.get(ConfigC.requireInventoryClearOnJoin)
								&& !inventoryempty) {
							MessageM.sendFMessage(player,
									ConfigC.error_joinInventoryNotEmpty);
							return;
						}

						LocationSerializable zero = new LocationSerializable(
								Bukkit.getWorld(player.getWorld().getName()
										.toString()), 0, 0, 0, 0, 0);
						if (arena.lobbyWarp != null && arena.spawnWarp != null && arena.finishWarp != null) {
							if (!arena.lobbyWarp.equals(zero) && !arena.spawnWarp.equals(zero) && !arena.finishWarp.equals(zero)) {
									arena.playersInArena.add(player);
									PlayerArenaData pad = new PlayerArenaData(
											player.getLocation(),
											player.getGameMode(), player
													.getInventory()
													.getContents(), player
													.getInventory()
													.getArmorContents(),
											player.getExp(), player.getLevel(),
											player.getHealth(),
											player.getFoodLevel(),
											player.getActivePotionEffects(),
											player.getAllowFlight());

									W.pData.put(player, pad);

									player.teleport(arena.spawnWarp);
									player.setGameMode(GameMode.SURVIVAL);
									for (PotionEffect pe : player
											.getActivePotionEffects()) {
										player.removePotionEffect(pe.getType());
									}
									player.setFoodLevel(20);
									player.setHealth(20);
									player.setExp(0);
									player.getInventory().clear();
									player.getInventory().setHelmet(
											new ItemStack(Material.AIR));
									player.getInventory().setChestplate(
											new ItemStack(Material.AIR));
									player.getInventory().setLeggings(
											new ItemStack(Material.AIR));
									player.getInventory().setBoots(
											new ItemStack(Material.AIR));
									player.setFlying(false);
									player.setAllowFlight(false);
									
									player.updateInventory();
									ArenaHandler.sendFMessage(arena,
											ConfigC.normal_joinJoinedArena,
											"playername-" + player.getName(),
											"1-" + arena.playersInArena.size());
							} else {
								MessageM.sendFMessage(player,
										ConfigC.error_joinWarpsNotSet);
							}
						} else {
							MessageM.sendFMessage(player,
									ConfigC.error_joinWarpsNotSet);
						}
					}
			}
		} else {
			MessageM.sendFMessage(player, ConfigC.error_joinAlreadyJoined);
			return;
		}

		if (!found) {
			MessageM.sendFMessage(player, ConfigC.error_noArena, "name-"
					+ arenaname);
		}

		SignsHandler.updateSigns();
	}

	public static void playerWinArena(Player player, Arena arena) {
		Date last = new Date();
		Date now = new Date();
        if (W.player.getFile().get(player.getName() + ".lasttime") != null) {
        	last = (Date) W.player.getFile().get(player.getName() + ".lasttime");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(last);
        c.add(Calendar.HOUR, arena.diff);
        Date newDate = c.getTime();
		if(newDate.after(now) && now != last) {
			MessageM.sendFMessage(player, ConfigC.error_alreadywon,
					"lasttime-" + last.toGMTString(),
					"hours-" + arena.diff);	
		} else {
			W.player.getFile().set(player.getName() + ".lasttime", last);
			W.player.save();
			ArenaHandler.sendFMessage(arena, ConfigC.normal_winArena,
					"playername-" + player.getName());
			try {
				Economy.add(player.getName(), arena.winBytes);
			} catch (UserDoesNotExistException | NoLoanPermittedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ArenaHandler.playerLeaveArena(player, false, true);
	}
	public static void playerLeaveArena(Player player, boolean message,
			boolean cleanup) {
		Arena arena = null;
		for (Arena arena2 : W.arenaList) {
			if (arena2.playersInArena != null) {
				if (arena2.playersInArena.contains(player)) {
					arena = arena2;
				}
			}
		}

		if (arena != null) {
			if (cleanup) {
				arena.playersInArena.remove(player);

			PlayerArenaData pad = new PlayerArenaData(null, null, null, null,
					null, null, null, null, null, false);

			if (W.pData.get(player) != null) {
				pad = W.pData.get(player);
			}

			player.getInventory().clear();
			player.getInventory().setContents(pad.pInventory);
			player.getInventory().setArmorContents(pad.pArmor);
			player.updateInventory();
			player.setExp(pad.pEXP);
			player.setLevel(pad.pEXPL);
			player.setFoodLevel(pad.pFood);
			player.addPotionEffects(pad.pPotionEffects);
			player.teleport(arena.spawnWarp);
			player.setGameMode(pad.pGameMode);
			player.setAllowFlight(pad.pFlying);
			if (player.getAllowFlight()) {
				player.setFlying(true);
			}

			W.pData.remove(player);

			for (Player pl : Bukkit.getOnlinePlayers()) {
				pl.showPlayer(player);
				if (W.hiddenLoc.get(player) != null) {
					if (W.hiddenLocWater.get(player) != null) {
						Block pBlock = W.hiddenLoc.get(player).getBlock();
						if (W.hiddenLocWater.get(player)) {
							pl.sendBlockChange(pBlock.getLocation(),
									Material.STATIONARY_WATER, (byte) 0);
						} else {
							pl.sendBlockChange(pBlock.getLocation(),
									Material.AIR, (byte) 0);
						}
					}
				}

			}
			player.teleport(arena.lobbyWarp);
			MessageM.sendFMessage(player, ConfigC.normal_leaveYouLeft);
			if (message) {
				ArenaHandler.sendFMessage(arena, ConfigC.normal_leaveLeftArena,
						"playername-" + player.getName(), "1-"
								+ arena.playersInArena.size());
			}
		} else {
			if (message) {
				MessageM.sendFMessage(player, ConfigC.error_leaveNotInArena);
			}
			return;
		}
		SignsHandler.updateSigns();
		}
	}
	public static void setReward(Arena arena, String bytes) {
		arena.winBytes = Integer.parseInt(bytes);
		W.arenas.save();
	}
	public static void setdiff(Arena arena, String diff) {
		arena.diff = Integer.parseInt(diff);
		W.arenas.save();
	}
	
	public static void stopArena(Arena arena) {
		ArenaHandler.sendFMessage(arena, ConfigC.warning_arenaStopped);

		for (Player player : arena.playersInArena) {
			playerLeaveArena(player, false, false);
			player.playSound(player.getLocation(), Sound.LEVEL_UP, 1, 1);
		}

		arena.gameState = ArenaState.DISABLED;
		arena.playersInArena.clear();
	}

	public static void startArena(Arena arena) {
		ArenaHandler.sendFMessage(arena, ConfigC.warning_arenaStarted);
		arena.gameState = ArenaState.INGAME;
		arena.playersInArena.clear();
	}
}
