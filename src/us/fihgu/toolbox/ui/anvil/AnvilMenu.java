package us.fihgu.toolbox.ui.anvil;

import org.bukkit.event.inventory.InventoryType;

import us.fihgu.toolbox.ui.InventoryMenu;

public class AnvilMenu extends InventoryMenu
{
	public AnvilMenu(String title)
	{
		super(title, InventoryType.ANVIL);
	}
	
	public AnvilMenu()
	{
		super("Repairing", InventoryType.ANVIL);
	}
}
