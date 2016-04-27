package us.fihgu.toolbox.ui;
import org.bukkit.event.Listener;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.plugin.java.JavaPlugin;

import us.fihgu.toolbox.ui.anvil.AnvilMenu;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.PrepareAnvilEvent;

public class MenuListener implements Listener
{
	public static HashMap<UUID, InventoryMenu> menuMap = new HashMap<>();
	
	public void register(JavaPlugin plugin)
	{
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	/**
	 * will not work because of a bug.<br>
	 * @see <a href="https://hub.spigotmc.org/jira/browse/SPIGOT-2225">SPIGOT-2225</a>
	 */
	@EventHandler
	public void onPrepareAnvil(PrepareAnvilEvent event)
	{
		List<HumanEntity> viewers = event.getViewers();
		if(viewers != null && viewers.size() > 0)
		{
			UUID id = viewers.get(0).getUniqueId();
			InventoryMenu menu = menuMap.get(id);
			if(menu != null && menu instanceof AnvilMenu)
			{
				((AnvilMenu)menu).onPrepareAnvil(event);
			}
		}
	}
	
	@EventHandler
	public void onCloseInventory(InventoryCloseEvent event)
	{
		UUID id = event.getPlayer().getUniqueId();
		InventoryMenu menu = menuMap.get(id);
		if(menu != null)
		{
			menu.onClose(event);
			menuMap.remove(id);
		}
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
	
	@EventHandler
	public void onDragInventory(InventoryDragEvent event)
	{
		UUID id = event.getWhoClicked().getUniqueId();
		InventoryMenu menu = menuMap.get(id);
		if(menu != null)
		{
			menu.onDrag(event);
		}
	}
}
