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
	
	public void setButton()
	{
		
	}
}
