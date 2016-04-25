package us.fihgu.toolbox.ui.anvil;
import org.bukkit.inventory.ItemStack;
import java.util.LinkedList;

public interface AnvilCraftingRecipes
{
	public boolean canLeftSlotAccept(ItemStack item);
	public boolean canRightSlotAccept(ItemStack item);
	
	/**
	/* return true when given materials are the right type and amount.
	*/
	public boolean hasResult(ItemStack left, ItemStack right);
	
	/**
	/* returns the crafting result of the given material.
	*/
	public ItemStack craft(AnvilCraftingMenu menu, ItemStack left, ItemStack right);
}
