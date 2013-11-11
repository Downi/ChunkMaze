package de.dauni.chunkmaze;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import de.dauni.chunkmaze.Managers.CommandM;
import de.dauni.chunkmaze.Managers.ConfigM;
import de.dauni.chunkmaze.Serializables.LocationSerializable;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class W {
	/*
	 * Standard stuff.
	 */
	public static ArrayList<String> newFiles = new ArrayList<String>();
	public static ArrayList<CommandM> commands = new ArrayList<CommandM>();

	/*
	 * If you want another file to be created. Copy and paste this line.
	 */
	public static ConfigM config = new ConfigM("config");
	public static ConfigM messages = new ConfigM("messages");
	public static ConfigM arenas = new ConfigM("arenas");
	public static ConfigM signs = new ConfigM("signs");
	public static ConfigM player = new ConfigM("player");

	/*
	 * Add any variable you need in different classes here:
	 */

	public static HashMap<Player, LocationSerializable> pos1 = new HashMap<Player, LocationSerializable>();
	public static HashMap<Player, LocationSerializable> pos2 = new HashMap<Player, LocationSerializable>();

	public static ArrayList<Arena> arenaList = new ArrayList<Arena>();
	public static Random random = new Random();
	public static HashMap<Player, Integer> seekertime = new HashMap<Player, Integer>();

	public static HashMap<Player, PlayerArenaData> pData = new HashMap<Player, PlayerArenaData>();
	public static HashMap<Player, ItemStack> choosenBlock = new HashMap<Player, ItemStack>();
	public static HashMap<Player, Boolean> choosenSeeker = new HashMap<Player, Boolean>();

	public static HashMap<Player, ItemStack> pBlock = new HashMap<Player, ItemStack>();
	public static HashMap<Player, Location> moveLoc = new HashMap<Player, Location>();
	public static HashMap<Player, Location> hiddenLoc = new HashMap<Player, Location>();
	public static HashMap<Player, Boolean> hiddenLocWater = new HashMap<Player, Boolean>();
}
