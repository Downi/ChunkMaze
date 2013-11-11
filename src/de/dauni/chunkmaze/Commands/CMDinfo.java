package de.dauni.chunkmaze.Commands;

import de.dauni.chunkmaze.ChunkMaze;
import de.dauni.chunkmaze.ConfigC;
import de.dauni.chunkmaze.Managers.MessageM;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CMDinfo extends DefaultCMD {
	/**
	 * Steffion's Engine - Made by Steffion.
	 * 
	 * You're allowed to use this engine for own usage, you're not allowed to
	 * republish the engine. Using this for your own plugin is allowed when a
	 * credit is placed somewhere in the plugin.
	 * 
	 * Thanks for your cooperate!
	 * 
	 * @author Steffion
	 */

	@Override
	public boolean exectue(Player player, Command cmd, String label,
			String[] args) {
		MessageM.sendFMessage(player, ConfigC.chat_headerhigh, "header-"
				+ ChunkMaze.pdfFile.getName());
		MessageM.sendMessage(player, "%A%name%%N made by %A%autors%%N.",
				"name-" + "ChunkMaze", "autors-"
						+ ChunkMaze.pdfFile.getAuthors().get(0));
		MessageM.sendMessage(player, "%NVersion: %A%version%%N.", "version-"
				+ ChunkMaze.pdfFile.getVersion());
		MessageM.sendMessage(player, "%NType %A%helpusage% %Nfor help.",
				"helpusage-" + ChunkMaze.CMDhelp.usage);
		MessageM.sendFMessage(player, ConfigC.chat_headerhigh,
				"header-&oInfo Page");
		return true;
	}
}
