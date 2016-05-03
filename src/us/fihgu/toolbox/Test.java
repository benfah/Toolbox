package us.fihgu.toolbox;

import java.net.InetSocketAddress;
import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import us.fihgu.toolbox.http.HTTPServer;


public class Test implements Listener
{
	public static int count = 0;
	
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent event)
	{
		Player player = event.getPlayer();
		String parts[] = event.getMessage().split(" ");
		
		if(parts.length > 0 && parts[0].equalsIgnoreCase("/test"))
		{
			count++;
			String args[] = Arrays.asList(parts).subList(Math.min(1, parts.length - 1), parts.length).toArray(new String[]{});
			player.sendMessage("The test command has been used " + count + " times, args[" + args.length + "]");
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			event.setCancelled(true);
		}
	}
	
	public void register(JavaPlugin plugin)
	{
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	public static void main(String[] args)
	{
		HTTPServer server = new HTTPServer(new InetSocketAddress("localhost", 80));
		server.startServer();
		//server.stopServer();
	}
}
