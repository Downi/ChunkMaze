package de.dauni.chunkmaze.Commands;

import de.dauni.chunkmaze.Arena;
import de.dauni.chunkmaze.ArenaHandler;
import de.dauni.chunkmaze.ChunkMaze;
import de.dauni.chunkmaze.ConfigC;
import de.dauni.chunkmaze.W;
import de.dauni.chunkmaze.Managers.ConfigM;
import de.dauni.chunkmaze.Managers.MessageM;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CMDstart extends DefaultCMD {

	@Override
	public boolean exectue(Player player, Command cmd, String label,
			String[] args) {
		if (args.length <= 1) {
			MessageM.sendFMessage(player, ConfigC.error_notEnoughArguments,
					"syntax-" + ChunkMaze.CMDstop.usage);
		} else {
			Arena arena = null;
			for (Arena arena2 : W.arenaList) {
				if (arena2.arenaName.equalsIgnoreCase(args[1])) {
					arena = arena2;
				}
			}
			ArenaHandler.startArena(arena);
			MessageM.sendFMessage(player, ConfigC.warning_arenaStarted);
		}
		return true;
	}
}
