package de.dauni.chunkmaze.Commands;

import java.util.ArrayList;

import de.dauni.chunkmaze.Arena;
import de.dauni.chunkmaze.Arena.ArenaState;
import de.dauni.chunkmaze.ChunkMaze;
import de.dauni.chunkmaze.ConfigC;
import de.dauni.chunkmaze.W;
import de.dauni.chunkmaze.Managers.MessageM;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CMDcreate extends DefaultCMD {

	@Override
	public boolean exectue(Player player, Command cmd, String label,
			String[] args) {
		if (player != null) {
			if (args.length <= 1) {
				MessageM.sendFMessage(player, ConfigC.error_notEnoughArguments,
						"syntax-" + ChunkMaze.CMDcreate.usage);
			} else {
				if (W.pos1.get(player) != null && W.pos2.get(player) != null) {
					if (W.pos1.get(player).getWorld()
							.equals(W.pos2.get(player).getWorld())) {
						Arena arena = new Arena(args[1], 
								W.pos1.get(player),
								W.pos2.get(player), 
								null, 
								null, 
								null, 
								10,
								24,
								new ArrayList<Player>(), 
								ArenaState.INGAME, 
								Bukkit.getScoreboardManager().getNewScoreboard());
						W.arenas.getFile().set(args[1], arena);
						W.arenas.save();
						W.signs.load();

						W.arenaList.add(arena);

						MessageM.sendFMessage(player,
								ConfigC.normal_createCreatedArena, "name-"
										+ args[1]);
					} else {
						MessageM.sendFMessage(player,
								ConfigC.error_createNotSameWorld);
					}
				} else {
					MessageM.sendFMessage(player,
							ConfigC.error_createSelectionFirst);
				}
			}
		} else {
			MessageM.sendFMessage(player, ConfigC.error_onlyIngame);
		}
		return true;
	}
}
