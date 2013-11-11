package de.dauni.chunkmaze.Commands;

import de.dauni.chunkmaze.Arena;
import de.dauni.chunkmaze.ArenaHandler;
import de.dauni.chunkmaze.ChunkMaze;
import de.dauni.chunkmaze.ConfigC;
import de.dauni.chunkmaze.W;
import de.dauni.chunkmaze.Managers.MessageM;
import de.dauni.chunkmaze.Serializables.LocationSerializable;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CMDsetwarp extends DefaultCMD {

	@Override
	public boolean exectue(Player player, Command cmd, String label,
			String[] args) {
		if (player != null) {
			if (args.length <= 2) {
				MessageM.sendFMessage(player, ConfigC.error_notEnoughArguments,
						"syntax-" + ChunkMaze.CMDsetwarp.usage);
			} else {
				String arenaname = args[2];
				String warpname = args[1];

				Arena arena = null;
				for (Arena arena2 : W.arenaList) {
					if (arena2.arenaName.equalsIgnoreCase(arenaname)) {
						arena = arena2;
					}
				}
				if (arena != null) {
					LocationSerializable loc = new LocationSerializable(
							player.getLocation());
					if (warpname.equalsIgnoreCase("lobby")) {
						arena.lobbyWarp = loc;
						save(arena);
						MessageM.sendFMessage(player,
								ConfigC.normal_setwarpWarpSet, "warp-"
										+ warpname);
					} else if (warpname.equalsIgnoreCase("spawn")) {
						arena.spawnWarp = loc;
						save(arena);
						MessageM.sendFMessage(player,
								ConfigC.normal_setwarpWarpSet, "warp-"
										+ warpname);
					} else if (warpname.equalsIgnoreCase("finish")) {
						arena.finishWarp = loc;
						save(arena);
						MessageM.sendFMessage(player,
								ConfigC.normal_setwarpWarpSet, "warp-"
										+ warpname);
					} else {
						MessageM.sendFMessage(player,
								ConfigC.error_setwarpWarpNotFound, "warp-"
										+ warpname);
					}
				} else {
					MessageM.sendFMessage(player, ConfigC.error_noArena,
							"name-" + arenaname);
				}
			}
		} else {
			MessageM.sendFMessage(player, ConfigC.error_onlyIngame);
		}
		return true;
	}

	public void save(Arena arena) {
		W.arenas.getFile().set(arena.arenaName, arena);
		W.arenas.save();
		ArenaHandler.loadArenas();
	}
}
