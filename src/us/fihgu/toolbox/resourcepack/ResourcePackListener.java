package us.fihgu.toolbox.resourcepack;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent.Status;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import us.fihgu.toolbox.Loader;

public class ResourcePackListener implements Listener
{
	public void register(JavaPlugin plugin)
	{
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onLogin(PlayerLoginEvent event)
	{
		Player player = event.getPlayer();
		
		BukkitRunnable task = new BukkitRunnable()
		{

			@Override
			public void run()
			{
				if (ResourcePackManager.hasResource())
				{
					String link = "http://" + ResourcePackServer.host + ":" + ResourcePackServer.port + ResourcePackServer.path;
					System.out.println(link);
					player.setResourcePack(link);
				}
			}

		};
		task.runTask(Loader.instance);
	}
	
	@EventHandler
	public void onResourcestatusChange(PlayerResourcePackStatusEvent event)
	{
		if(ResourcePackManager.getForceReourcePack())
		{
			Status status = event.getStatus();
			switch (status)
			{
			case DECLINED:
			case FAILED_DOWNLOAD:
				final Player player = event.getPlayer();
				BukkitRunnable task = new BukkitRunnable()
				{
					@Override
					public void run()
					{
						player.kickPlayer("This server require you use their resource pack, you either declined, or failed to download their resource pack.");
					}
				};
				task.runTask(Loader.instance);
				break;
			case ACCEPTED:
			case SUCCESSFULLY_LOADED:
				break;
			default:
				break;
			}
		}
	}
}
