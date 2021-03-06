package de.dauni.chunkmaze.Commands;

import de.dauni.chunkmaze.ArenaHandler;
import de.dauni.chunkmaze.ConfigC;
import de.dauni.chunkmaze.Managers.MessageM;

import org.bukkit.command.Command;
import org.bukkit.entity.Player;

public class CMDleave extends DefaultCMD {

	@Override
	public boolean exectue(Player player, Command cmd, String label,
			String[] args) {
		if (player != null) {
			ArenaHandler.playerLeaveArena(player, true, true);
		} else {
			MessageM.sendFMessage(player, ConfigC.error_onlyIngame);
		}
		return true;
	}
}
