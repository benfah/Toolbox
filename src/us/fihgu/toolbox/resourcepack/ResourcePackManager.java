package us.fihgu.toolbox.resourcepack;

import us.fihgu.toolbox.reflection.ReflectionUtils;
import java.io.OutputStream;
import java.io.IOException;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;
import java.io.File;
import us.fihgu.toolbox.json.JsonUtils;
import us.fihgu.toolbox.file.FileUtils;
import java.util.LinkedList;
import java.io.FileOutputStream;
import java.io.InputStream;

public class ResourcePackManager
{
	private static boolean force = false;
	
	/**
	* It stores the name and version of plugin that uses this resource manager.
	*/
	private static HashMap<String, String> resourceUsers = new HashMap<String, String>();
	
	private static HashMap<String, String> oldResourceUsers;
	
	private static File saveFile = new File("./fihgu/toolbox/resource/");
	
	private static LinkedList<InputStream> resources = new LinkedList<>();
	
	/**
	* register a resource pack to be combined into server resource pack.<br>
	* you may only use this method inside your onEnabl() method, else the resource will not be registered correctly.<br>
	* when a new resource being registered the first time, the server chache will be reconstructed.<br>
	*/
	public static void registerResource(JavaPlugin plugin, InputStream source) throws IOException
	{
		resourceUsers.put(plugin.getName() ,plugin.getDescription().getVersion());
		resources.add(source);
	}
	
	public static void Load()
	{
		oldResourceUsers = (HashMap<String, String>) JsonUtils.fromFile(saveFile, HashMap.class);
	}
	
	public static void save()
	{
		JsonUtils.toFile(saveFile, resourceUsers);
	}
	
	private static boolean needsRebuild()
	{
		if(oldResourceUsers == null)
		{
			if(resourceUsers.size() > 0)
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
			File work = new File("./fihgu/toolbox/resource/work/");
			FileUtils.deleteFolder(work);
			FileUtils.createFileAndPath(work);
			File temp = new File("./fihgus/toolbox/resource/work/temp.zip");
			FileUtils.createFileAndPath(temp);
			for(InputStream in : resources)
			{
				FileOutputStream fileOut = new FileOutputStream(temp);
				byte[] buffer = new byte[4096];
				int byteRead = 0;
				while((byteRead = in.read(buffer)) > 0)
				{
					fileOut.write(buffer, 0, byteRead);
				}
				fileOut.close();
			}
		}
		
		for(InputStream in: resources)
		{
			in.close();
		}
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
	* @return whatever the user entered as resourcepack in the server.porperties file.
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
	
	/**
	 * 
	 */
	public static void purge()
	{
		
	}

	public static boolean hasResource()
	{
		return !resourceUsers.keySet().isEmpty();
	}
}
