package us.fihgu.toolbox.ui.anvil;

import org.bukkit.event.inventory.InventoryType;

import us.fihgu.toolbox.ui.InventoryMenu;

public class AnvilMenu extends InventoryMenu
{
	final public static int SLOT_LEFT = 0;
	final public static int SLOT_RIGHT = 1;
	final public static int SLOT_RESULT = 2;
	
	public AnvilMenu(String title)
	{
		super(title, InventoryType.ANVIL);
	}
	
	public AnvilMenu()
	{
		super("Repairing", InventoryType.ANVIL);
	}
}
