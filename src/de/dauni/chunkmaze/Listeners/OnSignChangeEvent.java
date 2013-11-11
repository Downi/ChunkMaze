package de.dauni.chunkmaze.Listeners;

import de.dauni.chunkmaze.Arena;
import de.dauni.chunkmaze.ChunkMaze;
import de.dauni.chunkmaze.W;
import de.dauni.chunkmaze.PermissionsC.Permissions;
import de.dauni.chunkmaze.SignsHandler;
import de.dauni.chunkmaze.Managers.PermissionsM;
import de.dauni.chunkmaze.Serializables.LocationSerializable;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class OnSignChangeEvent implements Listener {

	@EventHandler(priority = EventPriority.NORMAL)
	public void onSignChangeEvent(SignChangeEvent event) {
		Player player = event.getPlayer();
		String[] lines = event.getLines();
		if (lines[0] != null) {
			if (lines[0].equalsIgnoreCase("[" + ChunkMaze.pdfFile.getName()
					+ "]")) {
				if (PermissionsM.hasPerm(player, Permissions.signcreate, true)) {
					SignsHandler.createSign(event, lines,
							new LocationSerializable(event.getBlock()
									.getLocation()));
				}
			}
		}
	}
}
