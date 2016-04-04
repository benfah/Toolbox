package us.fihgu.toolbox.ui;
import org.bukkit.event.inventory.InventoryType;
import java.util.HashMap;
import org.bukkit.event.inventory.InventoryClickEvent;

public class StaticButtonMenu extends InventoryMenu implements IButtonMenu
{
	protected HashMap<Integer, Button> buttons = new HashMap<>();
	
	public StaticButtonMenu(String title, InventoryType type)
	{
		super(title, type);
	}
	
	/**
	 * @param rows must be 1~6
	 */
	public StaticButtonMenu(String title, int rows)
	{
		super(title, rows);
	}
	
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
	
	public void setButtonAndUpdate(int slot, Button button)
	{
		this.setButton(slot, button);
		this.update();
	}
	
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
	
	@Override
	public void removeButton(int slot)
	{
		this.buttons.remove(slot);
	}
	
	@Override
	public void removeButtonAndUpdate(int slot)
	{
		this.removeButton(slot);
		this.update();
	}	
	
	@Override
	public void update()
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
