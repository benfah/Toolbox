package us.fihgu.toolbox.resourcepack;

import us.fihgu.toolbox.reflection.ReflectionUtils;
import java.io.IOException;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;
import java.io.File;
import us.fihgu.toolbox.json.JsonUtils;
import us.fihgu.toolbox.Loader;
import us.fihgu.toolbox.file.FileUtils;
import us.fihgu.toolbox.item.CustomItem;
import us.fihgu.toolbox.item.CustomItemManager;
import us.fihgu.toolbox.item.DamageableItem;

import java.util.LinkedList;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

public class ResourcePackManager
{
	private static boolean force = false;
	
	/**
	* It stores the name and version of plugin that uses this resource manager.
	*/
	public static HashMap<String, String> resourceUsers = new HashMap<String, String>();
	
	private static HashMap<String, String> oldResourceUsers;
	
	private static File saveFile = new File("./fihgu/toolbox/resourceUsers.json");
	private static File resourceFile = new File("./fihgu/toolbox/resource/resource.zip");
	
	private static LinkedList<InputStream> resources = new LinkedList<>();
	
	/**
	* register a resource pack to be combined into server resource pack.<br>
	* you may only use this method inside your onEnabl() method, else the resource will not be registered correctly.<br>
	* when a new resource being registered the first time, the server cache will be reconstructed.<br>
	*/
	public static void registerResource(JavaPlugin plugin, InputStream source) throws IOException
	{
		resourceUsers.put(plugin.getName() ,plugin.getDescription().getVersion());
		resources.add(source);
	}
	
	@SuppressWarnings("unchecked")
	public static void Load()
	{
		if(saveFile.exists())
		{
			oldResourceUsers = (HashMap<String, String>) JsonUtils.fromFile(saveFile, HashMap.class);
		}	
	}
	
	public static void save()
	{
		JsonUtils.toFile(saveFile, resourceUsers);
	}
	
	private static boolean needsRebuild()
	{
		String resourcePack = getServerResourcePack();
		String oldResourcePack = Loader.instance.getConfig().getString("resource.lastServerResourcePack");
		
		if(oldResourcePack != null)
		{
			if(!oldResourcePack.equals(resourcePack))
			{
				return true;
			}
		}
		else
		{
			if(resourcePack != null && !resourcePack.equals(""))
			{
				return true;
			}
		}
		
		if(oldResourceUsers == null)
		{
			if(hasResource())
			{
				return true;
			}
		}
		else
		{
			if(resourceUsers.size() != oldResourceUsers.size())
			{
				return true;
			}
			
			for(String key : resourceUsers.keySet())
			{
				String value = resourceUsers.get(key);
				String oldValue = oldResourceUsers.get(key);
				
				if(!value.equals(oldValue))
				{
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static void buildResourcePack() throws IOException
	{
		if(needsRebuild())
		{
			System.out.println("Resource pack change detected, building new resource pack.");
			
			//initialize work space
			File work = new File("./fihgu/toolbox/resource/work/");
			FileUtils.deleteFolder(work);
			File temp = new File("./fihgu/toolbox/resource/download/temp.zip");
			FileUtils.createFileAndPath(temp);
			
			//check and download server's original resource pack.
			String urlStr = getServerResourcePack();
			Loader.instance.getConfig().set("resource.lastServerResourcePack", urlStr);
			if(urlStr != null && !urlStr.equals("") && !urlStr.equals("null"))
			{
				System.out.println("Found server resource pack setting.");
				try
				{
					System.out.println("downloading server resource pack");
					URL url = new URL(urlStr);
					FileUtils.copyURLtoFile(url, temp);
					System.out.println("unpacking server resource pack");
					FileUtils.unzip(temp, work);
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
				
			}
			
			//process plugin resource packs.
			for(InputStream in : resources)
			{
				try
				{
					//download each plugin resource
					FileOutputStream fileOut = new FileOutputStream(temp);
					
					FileUtils.copyStreams(in, fileOut);
				
					//extract each plugin resource
					FileUtils.unzip(temp, work);
				}
				catch(IOException e)
				{
					e.printStackTrace();
				}
			}
			
			
			
			//process custom item.
			for(DamageableItem baseItem: CustomItemManager.registeredItems.keySet())
			{
				//create base model
				Model baseModel = Model.CreateSimpleModel("items/" + baseItem.getModelName());
				LinkedList<OverrideEntry> overrides = new LinkedList<>();
				
				HashMap<Short, CustomItem> items = CustomItemManager.registeredItems.get(baseItem);
				
				for(short id : items.keySet())
				{
					CustomItem item = items.get(id);
					String modelName = "item/" + item.getRegisteredName().replaceAll(":", "_");
					
					//save custom item's model
					File customModelFile = new File(work, "/assets/minecraft/models/" + modelName + ".json");
					JsonUtils.toFile(customModelFile, item.model);
					
					//add override entry to base model
					Predicate predicate = new Predicate();
					predicate.damaged = 0;
					predicate.damage = item.getId()/CustomItemManager.MAX_ID;
					overrides.add(new OverrideEntry(predicate, modelName));
				}
				
				//put all overrides into base model
				baseModel.overrides = overrides.toArray(new OverrideEntry[]{});
				//save baseModel
				File hoeFile = new File(work, "/assets/minecraft/models/item/"+ baseItem.getModelName() +".json");
				JsonUtils.toFile(hoeFile, baseModel);
			}
			
			
			
			
			//copy resource pack.meta and logo.png
			FileUtils.copyResource(Loader.instance, "resource/pack.mcmeta", new File(work, "pack.mcmeta"));
			FileUtils.copyResource(Loader.instance, "resource/pack.png", new File(work, "pack.png"));
			
			//pack up result resource pack.
			System.out.println("Packing complete resource pack.");
			FileUtils.zip(work, resourceFile);
			System.out.println("Resource pack has been constrcuted.");
			
			//remove temporary folder.
			FileUtils.deleteFolder(work);
			FileUtils.deleteFolder(temp.getParentFile());
		}
		else
		{
			System.out.println("No resource pack change, using chached resource pack.");
		}
		
		//close all stream
		for(InputStream in: resources)
		{
			in.close();
		}
		
		ResourcePackManager.save();
	}
	
	/**
	* force player
	*/
	public static void setForceResourcePack()
	{
		ResourcePackManager.force = true;
	}
	
	/**
	* @return true if player will be forced to download the resource pack from server.
	*/
	public static boolean getForceReourcePack()
	{
		return ResourcePackManager.force;
	}
	
	/**
	* @return whatever the user entered as resource pack in the server.porperties file.
	*/
	public static String getServerResourcePack()
	{
		try
		{
			Class<?> minecraftServerClass = ReflectionUtils.getNMSClass("MinecraftServer");
			Object minecraftServer = minecraftServerClass.getMethod("getServer").invoke(null);
			return minecraftServerClass.getMethod("getResourcePack").invoke(minecraftServer).toString();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return "";
	}

	public static boolean hasResource()
	{
		if(CustomItemManager.registeredItems.size() > 0)
		{
			return true;
		}
		
		if(resourceUsers.size() > 0)
		{
			return true;
		}
		
		return false;
	}
}
