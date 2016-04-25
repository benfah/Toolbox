package us.fihgu.toolbox.ui.anvil;
import java.util.LinkedList;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import us.fihgu.toolbox.item.ItemUtils;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryHolder;

public class AnvilCraftingMenu extends AnvilMenu
{
	LinkedList<AnvilCraftingRecipes> recipes = new LinkedList<>();
	protected Location dropLocation;
	
	public AnvilCraftingMenu(Location dropLocation)
	{
		this.dropLocation = dropLocation;
	}
	
	@Override
	public void onClose(InventoryCloseEvent event)
	{
		if(this.inventory.getViewers().size() == 0)
		{
			this.dropItems(this.dropLocation);
		}
	}
}
