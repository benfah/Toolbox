package us.fihgu.toolbox;
import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import us.fihgu.toolbox.item.CustomItemManager;
import us.fihgu.toolbox.resourcepack.ResourcePackListener;
import us.fihgu.toolbox.resourcepack.ResourcePackManager;
import us.fihgu.toolbox.resourcepack.ResourcePackServer;
import us.fihgu.toolbox.ui.MenuListener;

public class Loader extends JavaPlugin
{
	public static Loader instance = null;
	
	public static boolean debug = true;
	
	@Override
	public void onEnable()
	{
		Loader.instance = this;
		this.saveDefaultConfig();
		
		CustomItemManager.load();
		
		//LibraryUtils.loadLibraries(this);
		new MenuListener().register(this);
		new ResourcePackListener().register(this);
		
		System.out.print("fihgu's Toolbox is loaded.");
		
		if(debug)
		{
			new Test().register(this);
		}
		
		BukkitRunnable task = new BukkitRunnable()
		{

			@Override
			public void run()
			{
				if(ResourcePackManager.hasResource())
				{
					setupHTTPServer();
					CustomItemManager.save();
				}
			}
		};
		task.runTask(this);		
	}
	
	private void setupHTTPServer()
	{
		try
		{
			ResourcePackManager.Load();
			ResourcePackManager.buildResourcePack();
			System.out.println("Starting a http server for hosting resource pack.");
			ResourcePackServer.startServer();
			this.saveConfig();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void onDisable()
	{
		ResourcePackServer.stopServer();
	}
}
