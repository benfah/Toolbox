package us.fihgu.toolbox.ui;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryClickEvent;

import us.fihgu.toolbox.Loader;


public class InventoryMenu
{
	protected Inventory inventory;
	
	 /**
	 /* will cancel all the InventoryClickEvent by default
	 /*
	 /*/
	public boolean cancelEvent = true;
	
	/**
	 * the number of columns a minecraft chest have.
	 */
	public static final int CHEST_COLUMN_COUNT = 9;
	
	/**
	 * @param rows must be 1~6
	 */
	public InventoryMenu(String title, int rows)
	{
		rows = Math.min(6, Math.max(1, rows));
		this.inventory = Bukkit.createInventory(null, rows * CHEST_COLUMN_COUNT, title);
	}
	
	public InventoryMenu(String title, InventoryType type)
	{
		this.inventory = Bukkit.createInventory(null, type, title);
	}
	
	public void show(HumanEntity player)
	{
		
		BukkitRunnable task = new BukkitRunnable()
		{
			@Override
			public void run()
			{
				player.openInventory(inventory);
				MenuListener.menuMap.put(player.getUniqueId(), InventoryMenu.this);
			}
		};
		
		task.runTaskLater(Loader.instance, 1);
	}
	
	/**
	/* due to the nature of Events<br>
	/* actions that may interrupt other Listeners should be done AFTER the event is handled <br>
	/* a common way to do that is though a BukkitRunable, and schedule the task on next server tick.
	/*/
	public void onClick(InventoryClickEvent event)
	{
		if(this.cancelEvent == true)
		{
			event.setCancelled(true);
		}
	}
	
	public void closeMenu(HumanEntity player)
	{
		BukkitRunnable task = new BukkitRunnable()
		{
			@Override
			public void run()
			{
				player.closeInventory();
			}
		};
		
		task.runTaskLater(Loader.instance, 1);
	}
}
