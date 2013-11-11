package de.dauni.chunkmaze;

import java.util.ArrayList;
import java.util.List;

import de.dauni.chunkmaze.PermissionsC.Permissions;
import de.dauni.chunkmaze.Commands.CMDcreate;
import de.dauni.chunkmaze.Commands.CMDhelp;
import de.dauni.chunkmaze.Commands.CMDinfo;
import de.dauni.chunkmaze.Commands.CMDjoin;
import de.dauni.chunkmaze.Commands.CMDleave;
import de.dauni.chunkmaze.Commands.CMDlist;
import de.dauni.chunkmaze.Commands.CMDnotfound;
import de.dauni.chunkmaze.Commands.CMDreload;
import de.dauni.chunkmaze.Commands.CMDremove;
import de.dauni.chunkmaze.Commands.CMDreward;
import de.dauni.chunkmaze.Commands.CMDsetwarp;
import de.dauni.chunkmaze.Commands.CMDwand;
import de.dauni.chunkmaze.Commands.CMDstop;
import de.dauni.chunkmaze.Commands.CMDstart;
import de.dauni.chunkmaze.Commands.CMDdiff;
import de.dauni.chunkmaze.Listeners.OnPlayerInteractEvent;
import de.dauni.chunkmaze.Listeners.OnPlayerMoveEvent;
import de.dauni.chunkmaze.Listeners.OnSignChangeEvent;
import de.dauni.chunkmaze.Managers.CommandM;
import de.dauni.chunkmaze.Managers.ConfigM;
import de.dauni.chunkmaze.Managers.MessageM;
import de.dauni.chunkmaze.Managers.PermissionsM;
import de.dauni.chunkmaze.Serializables.LocationSerializable;
import de.dauni.chunkmaze.Arena;
import de.dauni.chunkmaze.Listeners.OnPlayerCommandPreprocessEvent;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class ChunkMaze extends JavaPlugin implements Listener {

	public static PluginDescriptionFile pdfFile;
	public static ChunkMaze plugin;

	@SuppressWarnings("serial")
	public static List<String> ChunkMazeCMD = new ArrayList<String>() {
		{
			add("info");
			add("help");
			add("reload");
			add("join");
			add("leave");
			add("list");
			add("tool");
			add("create");
			add("reward");
			add("diff");
			add("setwarp");
			add("remove");
			add("stop");
			add("start");
		}
	};

	public static CommandM CMD;
	public static CommandM CMDinfo;
	public static CommandM CMDhelp;
	public static CommandM CMDreload;
	public static CommandM CMDjoin;
	public static CommandM CMDstop;
	public static CommandM CMDleave;
	public static CommandM CMDlist;
	public static CommandM CMDshop;
	public static CommandM CMDwand;
	public static CommandM CMDcreate;
	public static CommandM CMDreward;
	public static CommandM CMDdiff;
	public static CommandM CMDsetwarp;
	public static CommandM CMDremove;
	public static CommandM CMDstart;

	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		getServer().getPluginManager().registerEvents(
				new OnPlayerInteractEvent(), this);
		getServer().getPluginManager().registerEvents(new OnSignChangeEvent(),
				this);
		getServer().getPluginManager().registerEvents(new OnPlayerMoveEvent(),
				this);
		getServer().getPluginManager().registerEvents(
				new OnPlayerCommandPreprocessEvent(), this);
		
		ConfigurationSerialization.registerClass(LocationSerializable.class,
				"ChunkMazeLocation");
		ConfigurationSerialization.registerClass(Arena.class, "ChunkMazeArena");
		pdfFile = getDescription();
		plugin = this;

		ConfigM.newFiles();

		CMD = new CommandM("Maze", "maze", null, null,
				Permissions.info, ConfigC.help_info,
				(Boolean) W.config.get(ConfigC.commandEnabled_info),
				ChunkMazeCMD, new CMDinfo(), null);
		CMDinfo = new CommandM("Maze INFO", "maze", "info", "i",
				Permissions.info, ConfigC.help_info,
				(Boolean) W.config.get(ConfigC.commandEnabled_info),
				ChunkMazeCMD, new CMDinfo(), "/Maze [info|i]");
		CMDhelp = new CommandM("Maze HELP", "maze", "help", "h",
				Permissions.help, ConfigC.help_help,
				(Boolean) W.config.get(ConfigC.commandEnabled_help),
				ChunkMazeCMD, new CMDhelp(),
				"/Maze <help|h> [page number]");
		CMDreload = new CommandM("Maze RELOAD", "maze", "reload",
				"r", Permissions.reload, ConfigC.help_reload,
				(Boolean) W.config.get(ConfigC.commandEnabled_reload),
				ChunkMazeCMD, new CMDreload(), "/Maze <reload|r>");
		CMDjoin = new CommandM("Maze JOIN", "maze", "join", "j",
				Permissions.join, ConfigC.help_join,
				(Boolean) W.config.get(ConfigC.commandEnabled_join),
				ChunkMazeCMD, new CMDjoin(), "/Maze <join|j> <arenaname>");
		CMDstop = new CommandM("Maze STOP", "maze", "stop", null,
				Permissions.stop, ConfigC.help_stop,
				(Boolean) W.config.get(ConfigC.commandEnabled_join),
				ChunkMazeCMD, new CMDstop(), "/Maze stop <arenaname>");
		CMDstart = new CommandM("Maze START", "maze", "start", null,
				Permissions.start, ConfigC.help_start,
				(Boolean) W.config.get(ConfigC.commandEnabled_join),
				ChunkMazeCMD, new CMDstart(), "/Maze start <arenaname>");
		CMDleave = new CommandM("Maze LEAVE", "maze", "leave", "l",
				Permissions.leave, ConfigC.help_leave,
				(Boolean) W.config.get(ConfigC.commandEnabled_leave),
				ChunkMazeCMD, new CMDleave(), "/Maze <leave|l>");
		CMDlist = new CommandM("Maze LIST", "maze", "list", "li",
				Permissions.list, ConfigC.help_list,
				(Boolean) W.config.get(ConfigC.commandEnabled_list),
				ChunkMazeCMD, new CMDlist(), "/Maze <list|li>");
		CMDwand = new CommandM("Maze WAND", "maze", "wand", "w",
				Permissions.create, ConfigC.help_wand,
				(Boolean) W.config.get(ConfigC.commandEnabled_wand),
				ChunkMazeCMD, new CMDwand(), "/Maze <wand|w>");
		CMDcreate = new CommandM("Maze CREATE", "maze", "create",
				"c", Permissions.create, ConfigC.help_create,
				(Boolean) W.config.get(ConfigC.commandEnabled_create),
				ChunkMazeCMD, new CMDcreate(),
				"/Maze <create|c> <arenaname>");
		CMDsetwarp = new CommandM("Maze SETWARP", "maze", "setwarp",
				"sw", Permissions.setwarp, ConfigC.help_setwarp,
				(Boolean) W.config.get(ConfigC.commandEnabled_setwarp),
				ChunkMazeCMD, new CMDsetwarp(),
				"/Maze <setwarp|sw> <lobby|hiders|seekers|spawn> <arenaname>");
		CMDremove = new CommandM("Maze REMOVE", "maze", "remove",
				"delete", Permissions.remove, ConfigC.help_remove,
				(Boolean) W.config.get(ConfigC.commandEnabled_remove),
				ChunkMazeCMD, new CMDremove(),
				"/Maze <remove|delete> <arenaname>");
		CMDreward = new CommandM("Maze REWARD", "maze", "reward",
				"rw", Permissions.reward, ConfigC.help_reward,
				(Boolean) W.config.get(ConfigC.commandEnabled_reward),
				ChunkMazeCMD, new CMDreward(),
				"/Maze reward <arenaname> <bytes>");
		CMDdiff = new CommandM("Maze DIFFERENCE", "maze", "difference",
				"dif", Permissions.dif, ConfigC.help_dif,
				(Boolean) W.config.get(ConfigC.commandEnabled_dif),
				ChunkMazeCMD, new CMDdiff(),
				"/Maze difference <arenaname> <hours>");
		
		
		ArenaHandler.loadArenas();

		

	}

	public void onDisable() {
		for (Arena arena : W.arenaList) {
			ArenaHandler.stopArena(arena);
		}

		MessageM.sendFMessage(null, ConfigC.log_disabledPlugin, "name-"
				+ ChunkMaze.pdfFile.getName(),
				"version-" + ChunkMaze.pdfFile.getVersion(), "autors-"
						+ ChunkMaze.pdfFile.getAuthors().get(0));
	}

	/**
	 * Args to String. Makes 1 string.
	 * 
	 * @param input
	 *            String list which should be converted to a string.
	 * @param startArg
	 *            Start on this length.
	 * 
	 * @return The converted string.
	 */
	public static String stringBuilder(String[] input, int startArg) {
		if (input.length - startArg <= 0) {
			return null;
		}
		StringBuilder sb = new StringBuilder(input[startArg]);
		for (int i = ++startArg; i < input.length; i++) {
			sb.append(' ').append(input[i]);
		}
		return sb.toString();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {
		Player player = null;
		if (sender instanceof Player) {
			player = (Player) sender;
		}

		for (CommandM command : W.commands) {
			String[] argsSplit = null;
			String[] argsSplitAlias = null;

			if (command.args != null && command.argsalias != null) {
				argsSplit = command.args.split("/");
				argsSplitAlias = command.argsalias.split("/");
			}

			if (cmd.getName().equalsIgnoreCase(command.label)) {
				boolean equals = true;

				if (argsSplit == null) {
					if (args.length == 0) {
						equals = true;
					} else {
						equals = false;
					}
				} else {
					if (args.length >= argsSplit.length) {
						for (int i2 = argsSplit.length - 1; i2 >= 0; i2 = i2 - 1) {
							int loc = argsSplit.length - i2 - 1;
							if (!argsSplit[loc].equalsIgnoreCase(args[loc])
									&& !argsSplitAlias[loc]
											.equalsIgnoreCase(args[loc])) {
								equals = false;
							}
						}
					} else {
						equals = false;
					}
				}

				if (equals) {
					if (PermissionsM.hasPerm(player, command.permission, true)) {
						if (command.enabled) {
							command.CMD.exectue(player, cmd, label, args);
						} else {
							MessageM.sendFMessage(player,
									ConfigC.error_commandNotEnabled);
						}
					}

					return true;
				}
			}
		}

		CMDnotfound.exectue(player, cmd, label, args);
		return true;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd,
			String label, String[] args) {

		for (CommandM command : W.commands) {
			if (cmd.getName().equalsIgnoreCase(command.label)) {
				if (args.length == 1) {
					return command.mainTABlist;
				}
			}
		}

		return null;
	}

	/**
	 * Short a String for like the Scoreboard title.
	 * 
	 * @param string
	 *            String to be shorten.
	 * @param maxLenght
	 *            Max lenght of the characters.
	 * @return Shorten string, else normal string.
	 */
	public static String cutString(String string, int maxLenght) {
		if (string.length() > maxLenght) {
			string = string.substring(0, maxLenght);
		}
		return string;
	}
}
