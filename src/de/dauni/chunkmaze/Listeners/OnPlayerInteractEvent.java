package de.dauni.chunkmaze.Listeners;

import java.util.ArrayList;

import de.dauni.chunkmaze.Arena;
import de.dauni.chunkmaze.Arena.ArenaState;
import de.dauni.chunkmaze.ArenaHandler;
import de.dauni.chunkmaze.ConfigC;
import de.dauni.chunkmaze.PermissionsC.Permissions;
import de.dauni.chunkmaze.SignsHandler;
import de.dauni.chunkmaze.W;
import de.dauni.chunkmaze.Managers.MessageM;
import de.dauni.chunkmaze.Managers.PermissionsM;
import de.dauni.chunkmaze.Serializables.LocationSerializable;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class OnPlayerInteractEvent implements Listener {

	@SuppressWarnings("unchecked")
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerInteractEvent(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		Block block = event.getClickedBlock();
		if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (event.getClickedBlock() != null) {
				if (event.getClickedBlock().getType()
						.equals(Material.SIGN_POST)
						|| event.getClickedBlock().getType()
								.equals(Material.WALL_SIGN)) {
					if (SignsHandler.isSign(new LocationSerializable(event
							.getClickedBlock().getLocation()))) {
						Sign sign = (Sign) event.getClickedBlock().getState();
						if (sign.getLine(1) != null){
							if (sign.getLine(1)
									.equals(MessageM
											.replaceAll(W.config
													.getFile()
													.getStringList(
															ConfigC.sign_LEAVE.location)
													.get(1)))) {
								if (PermissionsM.hasPerm(player,
										Permissions.joinsign, true)) {
									ArenaHandler.playerLeaveArena(player, true,
											true);
								}
							} else {
								for (Arena arena : W.arenaList) {
									if (sign.getLines()[1]
											.contains(arena.arenaName)) {
										if (PermissionsM.hasPerm(player,
												Permissions.joinsign, true)) {
											ArenaHandler.playerJoinArena(
													player, arena.arenaName);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		if (PermissionsM.hasPerm(player, Permissions.create, false)) {
			ItemStack item = player.getItemInHand();
			if (item.getType() != Material.AIR) {
				if (item.getItemMeta().hasDisplayName()) {
					ItemMeta im = item.getItemMeta();
					if (im.getDisplayName().equals(
							MessageM.replaceAll((String) W.config
									.get(ConfigC.wandName)))) {
						Action action = event.getAction();
						if (event.hasBlock()) {
							LocationSerializable location = new LocationSerializable(
									event.getClickedBlock().getLocation());
							if (action.equals(Action.LEFT_CLICK_BLOCK)) {
								event.setCancelled(true);
								if (W.pos1.get(player) == null
										|| !W.pos1.get(player).equals(location)) {
									MessageM.sendFMessage(
											player,
											ConfigC.normal_wandSetPosition,
											"number-1",
											"pos-%N(%A" + location.getBlockX()
													+ "%N, %A"
													+ location.getBlockY()
													+ "%N, %A"
													+ location.getBlockZ()
													+ "%N)",
											"x-" + location.getBlockX(), "y-"
													+ location.getBlockY(),
											"z-" + location.getBlockZ());
									W.pos1.put(player, location);
								}
							} else if (action.equals(Action.RIGHT_CLICK_BLOCK)) {
								event.setCancelled(true);
								if (W.pos2.get(player) == null
										|| !W.pos2.get(player).equals(location)) {
									MessageM.sendFMessage(
											player,
											ConfigC.normal_wandSetPosition,
											"number-2",
											"pos-%N(%A" + location.getBlockX()
													+ "%N, %A"
													+ location.getBlockY()
													+ "%N, %A"
													+ location.getBlockZ()
													+ "%N)",
											"x-" + location.getBlockX(), "y-"
													+ location.getBlockY(),
											"z-" + location.getBlockZ());
									W.pos2.put(player, location);
								}
							}
						}
					}
				}
			}
		}

	}
}
