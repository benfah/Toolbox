package us.fihgu.toolbox;
import org.bukkit.plugin.java.JavaPlugin;

import us.fihgu.toolbox.lib.LibraryUtils;
import us.fihgu.toolbox.resourcepack.ResourcePackListener;
import us.fihgu.toolbox.resourcepack.ResourcePackManager;
import us.fihgu.toolbox.ui.MenuListener;

public class Loader extends JavaPlugin
{
	public static Loader instance = null;
	
	public static boolean debug = true;
	
	@Override
	public void onEnable()
	{
		Loader.instance = this;
		
		LibraryUtils.loadLibraries(this);
		new MenuListener().register(this);
		new ResourcePackListener().register(this);
		
		ResourcePackManager.startServer();
		
		System.out.print("fihgu's Toolbox is loaded.");
		
		if(debug)
		{
			new Test().register(this);
		}
	}
	
	@Override
	public void onDisable()
	{
		ResourcePackManager.stopServer();
	}
}
