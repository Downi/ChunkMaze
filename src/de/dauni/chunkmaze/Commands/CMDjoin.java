package de.dauni.chunkmaze.Commands;

import de.dauni.chunkmaze.ArenaHandler;
import de.dauni.chunkmaze.ChunkMaze;
import de.dauni.chunkmaze.ConfigC;
import de.dauni.chunkmaze.Managers.MessageM;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CMDjoin extends DefaultCMD {

	@Override
	public boolean exectue(Player player, Command cmd, String label,
			String[] args) {
		if (player != null) {
			if (args.length <= 1) {
				MessageM.sendFMessage(player, ConfigC.error_notEnoughArguments,
						"syntax-" + ChunkMaze.CMDjoin.usage);
			} else {
				ArenaHandler.playerJoinArena(player, args[1]);
			}
		} else {
			MessageM.sendFMessage(player, ConfigC.error_onlyIngame);
		}
		return true;
	}
}
