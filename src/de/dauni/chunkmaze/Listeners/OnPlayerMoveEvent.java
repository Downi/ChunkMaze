package de.dauni.chunkmaze.Listeners;

import de.dauni.chunkmaze.Arena;
import de.dauni.chunkmaze.Arena.ArenaState;
import de.dauni.chunkmaze.ArenaHandler;
import de.dauni.chunkmaze.W;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class OnPlayerMoveEvent implements Listener {

	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerMoveEvent(PlayerMoveEvent event) {
		Player player = event.getPlayer();

		
		int isin = 0;
		for (Arena arena : W.arenaList) {
			if (arena.playersInArena.contains(player))
				isin = 1;
		}
		if(isin==0) {
			return;
		}
		for (Arena arena : W.arenaList) {
			if (arena.playersInArena.contains(player)) {
				if (arena.gameState == ArenaState.INGAME) {
					W.moveLoc.put(player, player.getLocation());
					Location loc = player.getLocation();
					if(loc.getBlockX() == arena.finishWarp.getBlockX() && loc.getBlockY() == arena.finishWarp.getBlockY() && loc.getBlockZ() == arena.finishWarp.getBlockZ()) {
						ArenaHandler.playerWinArena(player, arena);
					}
				}
			}
		}
	}
}
