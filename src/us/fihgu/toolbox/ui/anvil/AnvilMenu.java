package us.fihgu.toolbox.ui.anvil;

import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareAnvilEvent;

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
	
	/**
	 * will not work because of a bug.<br>
	 * @see <a href="https://hub.spigotmc.org/jira/browse/SPIGOT-2225">SPIGOT-2225</a>
	 */
	public void onPrepareAnvil(PrepareAnvilEvent event)
	{
		if(this.cancelEvent == true)
		{
			event.setResult(null);
		}
	}
}
