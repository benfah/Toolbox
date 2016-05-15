package us.fihgu.toolbox.item;

import java.io.File;
import java.util.HashMap;

import us.fihgu.toolbox.Loader;
import us.fihgu.toolbox.data.BiDirectionalMap;
import us.fihgu.toolbox.file.FileUtils;

public class CustomItemManager
{
	public static final double MAX_ID = 1562;
	
	protected static File saveFile = new File("./fihgu/toolbox/registeredItemNames.ser");
	
	/**
	 * This map relates damage value to item IDs<br>
	 * Item IDs registered here are kept registered even if the plugin is removed.<br>
	 * <br>
	 * Each item ID contains the unique name of the item, and the name of plugin that registered it.<br>
	 * For Example: "Swords:FireSword"
	 */
	protected static HashMap<DamageableItem, BiDirectionalMap<Short, String>> registeredItemIDs;
	
	/**
	 * Items registered here will be provided with item events. <br>
	 * This map is reconstructed every time you reload plugins.<br>
	 * <br>
	 * If a plugin is removed, its custom items will not exist in this list.<br>
	 */
	public static HashMap<DamageableItem, HashMap<Short, CustomItem>> registeredItems = new HashMap<>();
	
	/**
	 * @return A free id that's not yet been registered.<br>
	 * return -1 if all id has been registered.
	 */
	public static short getFreeId(DamageableItem itemBase)
	{
		HashMap<Short, CustomItem> items = registeredItems.get(itemBase);
		if(items == null)
		{
			items = new HashMap<Short, CustomItem>();
			registeredItems.put(itemBase, items);
		}
		
		if(items.size() >= itemBase.getMaxDurability())
		{
			return -1;
		}
		
		for(short i = 1; i <= itemBase.getMaxDurability(); i++)
		{
			if(!items.containsKey(i))
			{
				return i;
			}
		}
		
		return -1;
	}
	
	/**
	 * remove invalid entries in the registeredItemIDs HashMap
	 */
	public static void purge()
	{
		registeredItemIDs.clear();
		for(DamageableItem itemBase : registeredItems.keySet())
		{
			HashMap<Short, CustomItem> current = registeredItems.get(itemBase);
			BiDirectionalMap<Short, String> save = new BiDirectionalMap<>();
			for(Short id : current.keySet())
			{
				CustomItem item = current.get(id);
				save.put(id, item.getRegisteredName());
			}
			registeredItemIDs.put(itemBase, save);
		}
	}
	
	public static void save()
	{
		FileUtils.serialize(registeredItemIDs, saveFile);
	}
	
	@SuppressWarnings("unchecked")
	public static void load()
	{
		if(saveFile.exists())
		{
			registeredItemIDs = (HashMap<DamageableItem, BiDirectionalMap<Short, String>>) FileUtils.deserialize(saveFile);
			
			if(Loader.instance.getConfig().getBoolean("resource.purge", false))
			{
				Loader.instance.getConfig().set("resource.purge", false);
				Loader.instance.saveConfig();
				System.out.println("the registered custom item list has been purged.");
				purge();
			}
		}
		
		if(registeredItemIDs == null)
		{
			registeredItemIDs = new HashMap<DamageableItem, BiDirectionalMap<Short, String>>();
		}
	}
}
