package us.fihgu.toolbox.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import us.fihgu.toolbox.resourcepack.Model;

public abstract class CustomItem
{	
	protected String name;
	protected String displayName;
	protected String[] lores;
	protected Model model;
	
	private short id;
	private String registeredName;
	
	public CustomItem(String name, String displayName, String[] lores, Model model)
	{
		this.name = name;
		this.displayName = displayName;
		this.lores = lores;
		this.model = model;
	}
	
	/**
	 * You must register the custom item in your {@link JavaPlugin#onEnable() onEnable()} method<br>
	 * Else the custom model won't show, and item events will not be fed to this object.<br>
	 */
	public void register(JavaPlugin plugin)
	{
		this.registeredName = plugin.getName() + ":" + this.name;
		//TODO: register the item.
	}
	
	public ItemStack createItemStack()
	{
		ItemStack item = new ItemStack(Material.DIAMOND_HOE);
		ItemUtils.setLore(item, this.lores);
		ItemUtils.setDisplayName(item, this.name);
		
		ItemMeta meta = item.getItemMeta();
		meta.spigot().setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
		item.setItemMeta(meta);
		
		item.setDurability(this.id);
		
		return item;
	}
	
	public short getId()
	{
		return this.id;
	}
	
	/**
	 * 
	 * @return the registered name of this item. which will be pluginName:name <br>
	 * return null if this item was not registered.
	 */
	public String getRegisteredName()
	{
		return this.registeredName;
	}
}
