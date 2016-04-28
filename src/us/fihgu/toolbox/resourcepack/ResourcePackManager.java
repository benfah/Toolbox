package us.fihgu.toolbox.resourcepack;


public class ResourcePackManager
{
	private static boolean force = false;
	
	public static void setForceResourcePack(boolean force)
	{
		ResourcePackManager.force = force;
	}
	
	public static boolean getForceReourcePack()
	{
		return ResourcePackManager.force;
	}
	
	
}
