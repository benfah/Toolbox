package us.fihgu.toolbox;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;

import us.fihgu.toolbox.item.ItemUtils;
import us.fihgu.toolbox.json.event.HoverEventAction;
import us.fihgu.toolbox.json.event.JsonHoverEvent;
import us.fihgu.toolbox.json.text.JsonText;
import us.fihgu.toolbox.nbt.NBTCompoundWrapper;

public class Test implements Listener
{
	@EventHandler
	public void onCommand(PlayerCommandPreprocessEvent event)
	{
		Player player = event.getPlayer();
		String parts[] = event.getMessage().split(" ");
		
		if(parts.length > 0 && parts[0].equalsIgnoreCase("/test"))
		{
			//String args[] = Arrays.asList(parts).subList(Math.min(1, parts.length - 1), parts.length - 1).toArray(new String[]{});
			player.sendMessage("The test command has be used.");
			//System.out.println(NBTUtils.getNBTCompound(player.getItemInHand()));
			
			@SuppressWarnings("deprecation")
			NBTCompoundWrapper compound = ItemUtils.toNBTCompoound(player.getItemInHand());
			
			JsonText text = new JsonText("Test");
			text.hoverEvent = new JsonHoverEvent(HoverEventAction.show_item, compound.toString());
			text.strikethrough = true;
			String jsonMessage = text.toString();
			System.out.println(jsonMessage);
			
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "tellraw" + " @a " + jsonMessage);
			
			event.setCancelled(true);
		}
	}
	
	public void register(JavaPlugin plugin)
	{
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
}
