package us.fihgu.toolbox.ui;
import org.bukkit.event.inventory.InventoryType;
import java.util.HashMap;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ButtonMenu extends InventoryMenu
{
	protected HashMap<Integer, Button> buttons = new HashMap<>();
	
	public ButtonMenu(String title, InventoryType type)
	{
		super(title, type);
	}
	
	public ButtonMenu(String title, int rows)
	{
		super(title, rows);
	}
	
	/**
	/* due to the nature of Events<br>
	/* actions that may interupt other Listeners should be done AFTER the event is handled <br>
	/* a common way to do that is though a BukkitRunable, and schedule the task on next server tick.
	/*/
	@Override
	public void onClick(InventoryClickEvent event)
	{
		super.onClick(event);
		Button button = this.buttons.get(event.getSlot());
		
		if(button != null)
		{
			button.onClick(event);
		}
	}
	
	/**
	/* sets the button and updates the inventory <br>
	/* it is prefered to use #setButton(int slot, Button button) if you are setting multiple buttons at once.
	/*/
	public void setButtonAndUpdate(int slot, Button button)
	{
		this.setButton(slot, button);
		this.updateList();
	}
	
	/**
	/* sets the button to the internal button map <br>
	/* Note the inventory is not updated so the button is invisible to user until you #updateList() <br>
	/* button should never be null.
	/*/
	public void setButton(int slot, Button button)
	{
		if(button == null)
		{
			new NullPointerException("button can not be null.").printStackTrace();
			return;
		}
		
		if(slot < 0 || slot >= this.inventory.getSize())
		{
			new IndexOutOfBoundsException("Inventory size: " + this.inventory.getSize() + ", given slot: " + slot).printStackTrace();
			return;
		}
		
		this.buttons.put(slot, button);
	}
	
	/**
	/* removes the button on given slot<br>
	/* Note the inventory is NOT updated, so user will still see the ItemStack until you do #updateList()
	/*/
	public void removeButton(int slot)
	{
		this.buttons.remove(slot);
	}
	
	/**
	/* removes button on given slot and #updateList()<br>
	/* its recommanded to use #removeButton(int slot) then #updateList() when removing multiple buttons
	/*/
	public void removeButtonAndUpdate(int slot)
	{
		this.removeButton(slot);
		this.updateList();
	}
	
	
	
	/**
	/* remap the buttons into the inventory.
	/*/
	public void updateList()
	{
		if(this.inventory == null)
		{
			System.err.println("Inventory is null:");
			new Throwable().printStackTrace();
			return;
		}
		
		this.inventory.clear();
		
		for(int slot : this.buttons.keySet())
		{
			if(slot < 0 || slot >= this.inventory.getSize())
			{
				new IndexOutOfBoundsException("Inventory size: " + this.inventory.getSize() + ", given slot: " + slot).printStackTrace();
				continue;
			}
			
			Button button = this.buttons.get(slot);
			button.onUpdate();
			this.inventory.setItem(slot, button.getItem());
		}
	}
}
