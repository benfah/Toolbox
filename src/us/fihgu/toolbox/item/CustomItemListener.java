package us.fihgu.toolbox.item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import us.fihgu.toolbox.nbt.NBTCompoundWrapper;
import us.fihgu.toolbox.nbt.NBTUtils;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;

public class CustomItemListener implements Listener
{
	@EventHandler
	public void onInteract(PlayerInteractEvent event)
	{
		ItemStack item = event.getItem();
		NBTCompoundWrapper nbt = NBTUtils.getNBTTag(item);
		Short id = nbt.getShort("customItemId");
		
		if(id != null)
		{
			CustomItem customItem = CustomItemManager.registeredItems.get(id);
			if(customItem != null)
			{
				customItem.onInteract(event);
			}
		}
	}
	
	public void register(JavaPlugin plugin)
	{
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
}
