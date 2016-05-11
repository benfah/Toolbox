package us.fihgu.toolbox.item;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import us.fihgu.toolbox.resourcepack.Model;
import us.fihgu.toolbox.resourcepack.ResourcePackManager;

/**
 * A custom item based on diamond hoe. <br>
 * it must be registered with {@link #register(JavaPlugin)} method to function.<br>
 * <br>
 * Features:<br>
 * Display custom models.<br>
 * Item events are fed to the class.<br>
 * Limitation: <br>
 * Can not stack normally like other items.<br>
 * @author fihgu
 *
 */
public abstract class CustomItem
{	
	public String name;
	public String displayName;
	public String[] lores;
	public Model model;
	
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
		Short id = null;
		Number temp = CustomItemManager.registeredItemIDs.getKey(registeredName);
		if(temp != null)
		{
			id = temp.shortValue();
		}
		
		if(id == null)
		{
			id = CustomItemManager.getFreeId();
		}
		
		if(id == -1)
		{
			System.err.println("Custom item: " + name + " can not be registered, all ids are taken, try remove some plugins and purge custom item ids.");
		}
		else
		{
			this.id = id;
			CustomItemManager.registeredItemIDs.put(id, registeredName);
			CustomItemManager.registeredItems.put(id, this);
			ResourcePackManager.resourceUsers.put(plugin.getName() ,plugin.getDescription().getVersion());
		}
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
