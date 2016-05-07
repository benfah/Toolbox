package us.fihgu.toolbox.item;

import java.io.File;
import java.util.HashMap;

import org.bukkit.plugin.java.JavaPlugin;

import us.fihgu.toolbox.data.BiDirectionalMap;
import us.fihgu.toolbox.json.JsonUtils;

public class CustomItemManager
{
	public static final short MAX_ID = 1562;
	
	protected static File saveFile = new File("./fihgu/toolbox/registeredItemNames.json");
	
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
	protected static HashMap<Short, CustomItem> registeredItems = new HashMap<>();
	
	
	public static void save()
	{		
		JsonUtils.toFile(saveFile, registeredItemIDs);;
	}
	
	@SuppressWarnings("unchecked")
	public static void load()
	{
		registeredItemIDs = (BiDirectionalMap<Short, String>) JsonUtils.fromFile(saveFile, BiDirectionalMap.class);
		if(registeredItemIDs == null)
		{
			registeredItemIDs = new BiDirectionalMap<>();
		}
	}
}
