package de.dauni.chunkmaze;

import java.util.Collection;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

public class PlayerArenaData {
	public GameMode pGameMode;
	public ItemStack[] pInventory;
	public ItemStack[] pArmor;
	public Float pEXP;
	public Integer pEXPL;
	public Integer pHealth;
	public Integer pFood;
	public Collection<PotionEffect> pPotionEffects;
	public boolean pFlying;

	public PlayerArenaData (Location pLocation, GameMode pGameMode,
			ItemStack[] pInventory, ItemStack[] pArmor, Float pEXP,
			Integer pEXPL, Integer pHealth, Integer pFood,
			Collection<PotionEffect> pPotionEffects, boolean pFlying) {
		this.pGameMode = pGameMode;
		this.pInventory = pInventory;
		this.pArmor = pArmor;
		this.pEXP = pEXP;
		this.pEXPL = pEXPL;
		this.pHealth = pHealth;
		this.pFood = pFood;
		this.pPotionEffects = pPotionEffects;
		this.pFlying = pFlying;
	}
}
