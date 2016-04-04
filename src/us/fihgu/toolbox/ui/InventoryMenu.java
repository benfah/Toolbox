package us.fihgu.toolbox.ui;
import org.bukkit.inventory.Inventory;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;


public class InventoryMenu
{
	protected Inventory inventory;
	
	 /**
	 /* will cancel all the InventoryClickEvent by default
	 /*
	 /*/
	public boolean cancelEvent = true;
	
	
	public InventoryMenu(String title, int rows)
	{
		this.inventory = Bukkit.createInventory(null, rows * 9, title);
	}
	
	public InventoryMenu(String title, InventoryType type)
	{
		this.inventory = Bukkit.createInventory(null, type, title);
	}
	
	public void show(Player player)
	{
		if(MenuListener.registered == false)
		{
			System.err.println("MenuListener wasn't registered!");
			return;
		}
		
		MenuListener.menuMap.put(player.getUniqueId(), this);
		player.openInventory(this.inventory);
	}
	
	/**
	/* due to the nature of Events<br>
	/* actions that may interupt other Listeners should be done AFTER the event is handled <br>
	/* a common way to do that is though a BukkitRunable, and schedule the task on next server tick.
	/*/
	public void onClick(InventoryClickEvent event)
	{
		if(this.cancelEvent == true)
		{
			event.setCancelled(true);
		}
	}
}
