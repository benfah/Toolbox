package us.fihgu.toolbox.ui;
import org.bukkit.event.Listener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;
import java.util.UUID;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.ClickType;

public class MenuListener implements Listener
{
	public static boolean registered = false;
	public static HashMap<UUID, InventoryMenu> menuMap = new HashMap<>();
	
	public void register(JavaPlugin plugin)
	{
		Bukkit.getPluginManager().registerEvents(this, plugin);
		MenuListener.registered = true;
	}
	
	@EventHandler
	public void onCloseInventory(InventoryCloseEvent event)
	{
		UUID id = event.getPlayer().getUniqueId();
		menuMap.remove(id);
	}
	
	@EventHandler
	public void onClickInventory(InventoryClickEvent event)
	{
		UUID id = event.getWhoClicked().getUniqueId();
		InventoryMenu menu = menuMap.get(id);
		if(menu != null)
		{
			menu.onClick(event);
		}
	}
}
