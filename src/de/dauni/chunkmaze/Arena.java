package de.dauni.chunkmaze;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.dauni.chunkmaze.Serializables.LocationSerializable;
import de.dauni.chunkmaze.Serializables.M;

import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scoreboard.Scoreboard;

@SerializableAs("ChunkMazeArena")
public class Arena implements ConfigurationSerializable {
	public String arenaName;
	public LocationSerializable pos1;
	public LocationSerializable pos2;
	
	public LocationSerializable lobbyWarp;
	public LocationSerializable spawnWarp;
	public LocationSerializable finishWarp;
	public int winBytes;
	public int diff;

	public List<Player> playersInArena;
	public ArenaState gameState;
	public Scoreboard scoreboard;

	public Arena (String arenaName, LocationSerializable pos1,
			LocationSerializable pos2, LocationSerializable lobbyWarp, LocationSerializable spawnWarp, LocationSerializable finishWarp, 
			int winBytes, int diff, List<Player> playersInArena,
			ArenaState gameState, Scoreboard scoreboard) {
		this.arenaName = arenaName;
		this.pos1 = pos1;
		this.pos2 = pos2;
		this.lobbyWarp = lobbyWarp;
		this.spawnWarp = spawnWarp;
		this.finishWarp = finishWarp;
		this.winBytes = winBytes;
		this.diff = diff;
		this.playersInArena = playersInArena;
		this.gameState = gameState;
		this.scoreboard = scoreboard;
	}

	public enum ArenaType {
		winBytes;
	}

	public enum ArenaState {
		INGAME, DISABLED;
	}

	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("arenaName", arenaName);
		map.put("pos1", pos1);
		map.put("pos2", pos2);
		map.put("lobbyWarp", lobbyWarp);
		map.put("spawnWarp", spawnWarp);
		map.put("finishWarp", finishWarp);
		map.put("winBytes", winBytes);
		return map;
	}

	@SuppressWarnings("unchecked")
	public static Arena deserialize(Map<String, Object> map) {
		LocationSerializable loc = new LocationSerializable(
				Bukkit.getWorld("world"), 0, 0, 0, 0, 0);
		return new Arena((String) M.g(map, "arenaName", "UNKNOWN_NAME"),
				(LocationSerializable) M.g(map, "pos1", loc),
				(LocationSerializable) M.g(map, "pos2", loc),
				(LocationSerializable) M.g(map, "lobbyWarp", loc),
				(LocationSerializable) M.g(map, "spawnWarp", loc),
				(LocationSerializable) M.g(map, "finishWarp", loc),
				(Integer) M.g(map, "winBytes", 50), 
				(Integer) M.g(map, "diff", 24), 
				new ArrayList<Player>(), 
				ArenaState.INGAME, 
				Bukkit.getScoreboardManager().getNewScoreboard());
	}
}