package us.fihgu.toolbox;

import java.io.File;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import us.fihgu.toolbox.world.MapManager;

public class Test implements Listener
{
	public static int count = 0;
	
	private File mapFolder = new File("./maps/TestMap");
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent event)
	{
		Player player = event.getPlayer();
		String parts[] = event.getMessage().split(" ");
		
		if(parts.length > 0 && parts[0].equalsIgnoreCase("/test"))
		{
			count++;
			String args[] = Arrays.asList(parts).subList(Math.min(1, parts.length - 1), parts.length).toArray(new String[]{});
			player.sendMessage("The test command has been used " + count + " times.");
			
			if(args.length == 1)
			{
				if(args[0].equalsIgnoreCase("load"))
				{
					World world = MapManager.createWorld("testWorld", mapFolder);
					player.teleport(world.getSpawnLocation());
				}
				else if(args[0].equalsIgnoreCase("delete"))
				{
					MapManager.deleteWorld("testWorld", null);
				}
				else if(args[0].equalsIgnoreCase("save"))
				{
					MapManager.saveMap(player.getWorld(), mapFolder);
				}
				else if(args[0].equalsIgnoreCase("unload"))
				{
					MapManager.unloadWorld(Bukkit.getWorld("testWorld"), null);
				}
			}
			
			event.setCancelled(true);
		}
	}
	
	public void register(JavaPlugin plugin)
	{
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
}
