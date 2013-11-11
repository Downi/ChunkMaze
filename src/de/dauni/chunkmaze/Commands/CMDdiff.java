package de.dauni.chunkmaze.Commands;

import de.dauni.chunkmaze.Arena;
import de.dauni.chunkmaze.ArenaHandler;
import de.dauni.chunkmaze.ChunkMaze;
import de.dauni.chunkmaze.ConfigC;
import de.dauni.chunkmaze.InventoryHandler;
import de.dauni.chunkmaze.W;
import de.dauni.chunkmaze.Managers.MessageM;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CMDdiff extends DefaultCMD {

	@Override
	public boolean exectue(Player player, Command cmd, String label,
			String[] args) {
			if (args.length <= 2) {
				MessageM.sendFMessage(player, ConfigC.error_notEnoughArguments,
						"syntax-" + ChunkMaze.CMDdiff.usage);
			} else {
				Arena arena = null;
				for (Arena arena2 : W.arenaList) {
					if (arena2.arenaName.equalsIgnoreCase(args[1])) {
						arena = arena2;
					}
				}
				String diff = args[2];
				if(arena==null) {
					MessageM.sendFMessage(player, ConfigC.error_arenanotexists);	
				} else {
					MessageM.sendFMessage(player, ConfigC.normal_diffset);
					ArenaHandler.setdiff(arena, diff);
				}
			}
		return true;
	}
}
