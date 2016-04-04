package us.fihgu.toolbox.item;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * this class provides ItemStack/Item related shortcuts.
 */
public class ItemUtils
{
	/**
	 * replaces items entire lore to given lore.
	 */
	public static ItemStack setLore(ItemStack item, String[] lores)
	{
		ItemMeta meta = item.getItemMeta();
		itemMetaNullCheck(item);
		meta.setLore(Arrays.asList(lores));
		item.setItemMeta(meta);
		return item;
	}
	
	/**
	 * replaces items entire lore to given lore.
	 */
	public static ItemStack setLore(ItemStack item, String lore)
	{
		return setLore(item, new String[]{lore});
	}
	
	/**
	 * Changes Items display name.
	 */
	public static ItemStack setDisplayName(ItemStack item, String name)
	{
		ItemMeta meta = item.getItemMeta();
		itemMetaNullCheck(item);
		meta.setDisplayName(name);
		item.setItemMeta(meta);
		return item;
	}
	
	/**
	 * adds given lore to the end of other lore.
	 */
	public static ItemStack addLore(ItemStack item, String[] lores)
	{
		ItemMeta meta = item.getItemMeta();
		itemMetaNullCheck(item);
		List<String> list = meta.getLore();
		if(list == null)
		{
			list = new LinkedList<String>();
		}
		list.addAll(Arrays.asList(lores));
		meta.setLore(list);
		item.setItemMeta(meta);
		
		return item;
	}
	
	/**
	 * adds given lore to the end of other lore.
	 */
	public static ItemStack addLore(ItemStack item, String lore)
	{
		return addLore(item, new String[]{lore});
	}
	
	/**
	 * checks if item has meta data, will create one for item if not.
	 */
	private static void itemMetaNullCheck(ItemStack item)
	{
		if(!item.hasItemMeta())
		{
			ItemMeta meta = Bukkit.getItemFactory().getItemMeta(item.getType());
			item.setItemMeta(meta);
		}
	}
}
