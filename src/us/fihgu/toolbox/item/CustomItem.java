package us.fihgu.toolbox.item;

import java.util.HashMap;
import java.util.List;

import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import us.fihgu.toolbox.resourcepack.Model;
import us.fihgu.toolbox.resourcepack.ResourcePackManager;
import us.fihgu.toolbox.data.BiDirectionalMap;
import us.fihgu.toolbox.nbt.NBTUtils;

/**
 * A custom item based on a damageable item<br>
 * it must be registered with {@link #register(JavaPlugin)} method to function.<br>
 * @author fihgu
 *
 */
public class CustomItem
{	
	public DamageableItem baseItem;
	public String name;
	public Model model;
	
	private short id;
	private String registeredName;
	
	public CustomItem(DamageableItem baseItem, String name, Model model)
	{
		this.baseItem = baseItem;
		this.name = name;
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
		
		BiDirectionalMap<Short, String> save = CustomItemManager.registeredItemIDs.get(this.baseItem);
		
		if(save != null)
		{
			id = save.getKey(registeredName);
		}
		else
		{
			save = new BiDirectionalMap<Short, String>();
			CustomItemManager.registeredItemIDs.put(baseItem, save);
		}
		
		if(id == null)
		{
			id = CustomItemManager.getFreeId(this.baseItem);
		}
		
		if(id == -1)
		{
			System.err.println("Custom item: " + name + " can not be registered, all ids are taken, try remove some plugins and purge custom item ids.");
		}
		else
		{
			this.id = id;
			save.put(id, registeredName);
			
			HashMap<Short, CustomItem> ids = CustomItemManager.registeredItems.get(baseItem);
			if(ids == null)
			{
				ids = new HashMap<Short, CustomItem>();
				CustomItemManager.registeredItems.put(baseItem, ids);
			}
			ids.put(this.id, this);
			ResourcePackManager.resourceUsers.put(plugin.getName() ,plugin.getDescription().getVersion());
		}
	}
	
	public ItemStack createItemStack(String displayName, List<String> lores)
	{
		ItemStack item = new ItemStack(this.baseItem.getMaterial());
		
		if(lores != null)
		{
			ItemUtils.setLore(item, lores);
		}
		
		if(displayName != null)
		{
			ItemUtils.setDisplayName(item, displayName);
		}
		else
		{
			ItemUtils.setDisplayName(item, this.name);
		}
		
		ItemMeta meta = item.getItemMeta();
		meta.spigot().setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE);
		item.setItemMeta(meta);
		
		item.setDurability(this.id);
		
		System.out.println(NBTUtils.getNBTTag(item).toString());
		
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
	
	/**
	 * checks item unbreakable flag, material type, damage value <br>
	 * @return true if the itemstack it similar to this CustomItem
	 */
	public boolean isSimlair(ItemStack item)
	{
		if(item == null)
		{
			return false;
		}
		
		if(item.hasItemMeta())
		{
			ItemMeta meta = item.getItemMeta();
			if(meta.spigot().isUnbreakable())
			{
				if(item.getType() == this.baseItem.getMaterial() && item.getDurability() == this.id)
				{
					return true;
				}
			}
		}
		
		return false;
	}
}
