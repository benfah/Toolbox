package us.fihgu.toolbox.item;

import java.io.File;
import java.util.HashMap;

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
	protected static BiDirectionalMap<Short, String> registeredItemIDs;
	
	/**
	 * Items registered here will be provided with item events. <br>
	 * This map is reconstructed every time you reload plugins.<br>
	 * <br>
	 * If a plugin is removed, its custom items will not exist in this list.<br>
	 */
	public static HashMap<Short, CustomItem> registeredItems = new HashMap<>();
	
	private static short nextId = 1;
	
	/**
	 * @return A free id that's not yet been registered.<br>
	 * return -1 if all id has been registered.
	 */
	public static short getFreeId()
	{
		if(registeredItemIDs.size() >= MAX_ID)
		{
			return -1;
		}
		
		for(short i = nextId; i <= MAX_ID; i++)
		{
			if(!registeredItemIDs.containsKey(i))
			{
				nextId = (short) (i + 1);
				return i;
			}
		}
		
		for(short i = 1; i < nextId; i++)
		{
			if(!registeredItemIDs.containsKey(i))
			{
				nextId = (short) (i + 1);
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
		for(Short id : registeredItems.keySet())
		{
			CustomItem item = registeredItems.get(id);
			registeredItemIDs.put(id, item.getRegisteredName());
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
			registeredItemIDs = (BiDirectionalMap<Short, String>) FileUtils.deserialize(saveFile);
		}
		
		if(registeredItemIDs == null)
		{
			registeredItemIDs = new BiDirectionalMap<>();
		}
	}
}
