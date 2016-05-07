package us.fihgu.toolbox.resourcepack;

import us.fihgu.toolbox.reflection.ReflectionUtils;
import java.io.OutputStream;
import java.io.IOException;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;

public class ResourcePackManager
{
	private static boolean force = false;
	
	/**
	* It stores the name and version of plugin that uses this resource manager.
	*/
	private static HashMap<String, String> resourceUsers = new HashMap<String, String>();
	
	/**
	* custom items based on diamond hoes
	*/
	private static HashMap<String, String> customItems = new HashMap<String, String>();
	
	public static void registerResource(JavaPlugin plugin, OutputStream source) throws IOException
	{
	
	}
	
	public static void Load()
	{
		
	}
	
	public static void save()
	{
		
	}
	
	public static void buildResourcePack()
	{
		//TODO
	}
	
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
