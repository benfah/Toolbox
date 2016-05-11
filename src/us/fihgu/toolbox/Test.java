package us.fihgu.toolbox;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import us.fihgu.toolbox.resourcepack.ResourcePackServer;


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
			String link = "http://" + ResourcePackServer.host + ":" + ResourcePackServer.port + ResourcePackServer.path;
			player.setResourcePack(link);
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			event.setCancelled(true);
		}
	}
	
	public void register(JavaPlugin plugin)
	{
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	public static void main(String[] args) throws Exception
	{
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(new File("./test.zip")));
		ZipEntry entry = new ZipEntry("test//");
		out.putNextEntry(entry);
		out.closeEntry();
		out.close();
	}
	
	public static void backup()
	{
		
	}
}
