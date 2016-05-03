package us.fihgu.toolbox.resourcepack;

import us.fihgu.toolbox.reflection.ReflectionUtils;

public class ResourcePackManager
{
	private static boolean force = false;
	
	public static void startServer()
	{
		//TODO:
	}
	
	public static void stopServer()
	{
		//TODO:
	}
	
	public static void buildResourcePack()
	{
		//TODO
	}
	
	public static void setForceResourcePack(boolean force)
	{
		ResourcePackManager.force = force;
	}
	
	public static boolean getForceReourcePack()
	{
		return ResourcePackManager.force;
	}
	
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
}
