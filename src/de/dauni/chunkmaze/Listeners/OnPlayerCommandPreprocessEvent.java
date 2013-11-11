package de.dauni.chunkmaze.Listeners;

import de.dauni.chunkmaze.Arena;
import de.dauni.chunkmaze.ConfigC;
import de.dauni.chunkmaze.PermissionsC.Permissions;
import de.dauni.chunkmaze.W;
import de.dauni.chunkmaze.Managers.MessageM;
import de.dauni.chunkmaze.Managers.PermissionsM;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class OnPlayerCommandPreprocessEvent implements Listener {

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerCommandPreprocessEvent(
			PlayerCommandPreprocessEvent event) {
		Player player = event.getPlayer();

		for (Arena arena : W.arenaList) {
			if (arena.playersInArena.contains(player)) {
				String m = event.getMessage();

				if (PermissionsM
						.hasPerm(player, Permissions.allcommands, false)) {
					return;
				}
				if (m.startsWith("/tpa") || m.startsWith("/maze") || m.startsWith("/sethome") || m.startsWith("/tphere") || m.startsWith("/back")) {
					MessageM.sendFMessage(player, ConfigC.warning_unableToCommand);
					event.setCancelled(true);
				}
				return;
			}
		}
	}
}
