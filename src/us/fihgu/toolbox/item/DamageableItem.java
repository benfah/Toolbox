package us.fihgu.toolbox.item;

import org.bukkit.Material;

public enum DamageableItem
{
	WOOD_HOE(Material.WOOD_HOE, 60),
	STONE_HOE(Material.STONE_HOE, 132),
	IRON_HOE(Material.IRON_HOE, 251),
	GOLD_HOE(Material.GOLD_HOE, 33),
	DIAMOND_HOE(Material.DIAMOND_HOE, 1562),
	
	WOOD_AXE(Material.WOOD_AXE, 60),
	STONE_AXE(Material.STONE_AXE, 132),
	IRON_AXE(Material.IRON_AXE, 251),
	GOLD_AXE(Material.GOLD_AXE, 33),
	DIAMOND_AXE(Material.DIAMOND_AXE, 1562),
	
	WOOD_SWORD(Material.WOOD_SWORD, 60),
	STONE_SWORD(Material.STONE_SWORD, 132),
	IRON_SWORD(Material.IRON_SWORD, 251),
	GOLD_SWORD(Material.GOLD_SWORD, 33),
	DIAMOND_SWORD(Material.DIAMOND_SWORD, 1562),
	
	WOOD_PICKAXE(Material.WOOD_PICKAXE, 60),
	STONE_PICKAXE(Material.STONE_PICKAXE, 132),
	IRON_PICKAXE(Material.IRON_PICKAXE, 251),
	GOLD_PICKAXE(Material.GOLD_PICKAXE, 33),
	DIAMOND_PICKAXE(Material.DIAMOND_PICKAXE, 1562),
	
	WOOD_SHOVEL(Material.WOOD_SPADE, 60),
	STONE_SHOVEL(Material.STONE_SPADE, 132),
	IRON_SHOVEL(Material.IRON_SPADE, 251),
	GOLD_SHOVEL(Material.GOLD_SPADE, 33),
	DIAMOND_SHOVEL(Material.DIAMOND_SPADE, 1562),
	
	LEATHER_HELMET(Material.LEATHER_HELMET, 56),
	GOLD_HELMET(Material.GOLD_HELMET, 78),
	CHAINMAIL_HELMET(Material.CHAINMAIL_HELMET, 166),
	IRON_HELMET(Material.IRON_HELMET, 166),
	DIAMOND_HELMET(Material.DIAMOND_HELMET, 364),
	
	LEATHER_CHESTPLATE(Material.LEATHER_CHESTPLATE, 81),
	GOLD_CHESTPLATE(Material.GOLD_CHESTPLATE, 113),
	CHAINMAIL_CHESTPLATE(Material.CHAINMAIL_CHESTPLATE, 241),
	IRON_CHESTPLATE(Material.IRON_CHESTPLATE, 241),
	DIAMOND_CHESTPLATE(Material.DIAMOND_CHESTPLATE, 529),
	
	LEATHER_LEGGINGS(Material.LEATHER_LEGGINGS, 76),
	GOLD_LEGGINGS(Material.GOLD_LEGGINGS, 106),
	CHAINMAIL_LEGGINGS(Material.CHAINMAIL_LEGGINGS, 226),
	IRON_LEGGINGS(Material.IRON_LEGGINGS, 226),
	DIAMOND_LEGGINGS(Material.DIAMOND_LEGGINGS, 496),
	
	LEATHER_BOOTS(Material.LEATHER_BOOTS, 66),
	GOLD_BOOTS(Material.GOLD_BOOTS, 92),
	CHAINMAIL_BOOTS(Material.CHAINMAIL_BOOTS, 196),
	IRON_BOOTS(Material.IRON_BOOTS, 196),
	DIAMOND_BOOTS(Material.DIAMOND_BOOTS, 430),
	
	SHEARS(Material.SHEARS, 238),
	FISHING_ROD(Material.FISHING_ROD, 65),
	CARROT_ON_A_STICK(Material.CARROT_STICK, 26),
	FLINT_AND_STEEL(Material.FLINT_AND_STEEL, 65),
	BOW(Material.BOW, 385),
	ELYTRA(Material.ELYTRA, 432);
	
	private Material material;
	private int maxDurability;
	DamageableItem(Material material, int maxDurability)
	{
		this.material = material;
		this.maxDurability = maxDurability;
	}
	
	public Material getMaterial()
	{
		return this.material;
	}
	
	public int getMaxDurability()
	{
		return this.maxDurability;
	}
	
	public String getModelName()
	{
		return this.name().toLowerCase();
	}
}
