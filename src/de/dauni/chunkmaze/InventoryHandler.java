package de.dauni.chunkmaze;

import de.dauni.chunkmaze.Managers.MessageM;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryHandler {

	public static void openReward(Player player, String arenaname) {

		Arena arena = null;
		for (Arena arena2 : W.arenaList) {
			if (arena2.arenaName.equalsIgnoreCase(arenaname)) {
				arena = arena2;
			}
		}

		if (arena != null) {
			String shorten = arena.arenaName;
			arenaname = arena.arenaName;
			if (shorten.length() > 6)
				shorten = shorten.substring(0, 6);
			Inventory panel = Bukkit
					.createInventory(
							null,
							9,
							MessageM.replaceAll("\u00A7r%N&lSettings of: %A"
									+ shorten));

			ItemStack arenaNameNote = new ItemStack(Material.PAPER, 1);
			ItemMeta arenaNameNote_IM = arenaNameNote.getItemMeta();
			arenaNameNote_IM.setDisplayName(MessageM
					.replaceAll("%NSettings of arena: %A" + arena.arenaName));
			arenaNameNote.setItemMeta(arenaNameNote_IM);
			panel.setItem(0, arenaNameNote);

			player.openInventory(panel);
		} else {
			MessageM.sendFMessage(player, ConfigC.error_noArena, "name-"
					+ arenaname);
		}
	}
}
