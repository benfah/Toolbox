package us.fihgu.toolbox;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import us.fihgu.toolbox.nbt.NBTUtils;

public class Test implements Listener
{
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent event)
	{
		Player player = event.getPlayer();
		String parts[] = event.getMessage().split(" ");
		
		if(parts.length > 0 && parts[0].equalsIgnoreCase("/test"))
		{
			String args[] = Arrays.asList(parts).subList(Math.min(1, parts.length - 1), parts.length - 1).toArray(new String[]{});
			player.sendMessage("The test command has be used.");
			System.out.println(args);
			System.out.println(NBTUtils.getNBTCompound(player.getItemInHand()));
			
			event.setCancelled(true);
		}
	}
	
	public void register(JavaPlugin plugin)
	{
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
}
