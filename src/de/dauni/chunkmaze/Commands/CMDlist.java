package de.dauni.chunkmaze.Commands;

import de.dauni.chunkmaze.Arena;
import de.dauni.chunkmaze.ChunkMaze;
import de.dauni.chunkmaze.ConfigC;
import de.dauni.chunkmaze.W;
import de.dauni.chunkmaze.Managers.MessageM;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CMDlist extends DefaultCMD {

	@Override
	public boolean exectue(Player player, Command cmd, String label,
			String[] args) {
		MessageM.sendFMessage(player, ConfigC.chat_headerhigh, "header-"
				+ ChunkMaze.pdfFile.getName());
		if (W.arenaList.size() >= 1) {
			MessageM.sendMessage(player, "&7Available arena(s):");
			for (Arena arena : W.arenaList) {
				MessageM.sendMessage(player, "%A" + arena.arenaName);
			}
		} else {
			MessageM.sendMessage(player, "&7&oNo arenas available...");
			MessageM.sendMessage(player, "&7&oCreate an arena first please.");
		}
		MessageM.sendFMessage(player, ConfigC.chat_headerhigh,
				"header-&oArenas list");
		return true;
	}
}
