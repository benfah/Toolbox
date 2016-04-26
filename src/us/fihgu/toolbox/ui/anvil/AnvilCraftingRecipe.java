package us.fihgu.toolbox.ui.anvil;
import org.bukkit.inventory.ItemStack;

public interface AnvilCraftingRecipe
{
	/**
	 * checks if the given item is allowed to be put inside the AnvilCraftingMenu
	 */
	public boolean canLeftSlotAccept(ItemStack item);
	
	/**
	 * checks if the given item is allowed to be put inside the AnvilCraftingMenu
	 */
	public boolean canRightSlotAccept(ItemStack item);
	
	/**
	 * Note: returned ItemStack is not the result of actually crafting, but a button displayed to player, showing them what the result might be.
	 * @return An ItemStack that's displayed in the result slot, indicates the crafting is possible.<br>
	 * return null if Material required is not enough, or are the wrong types.
	 */
	public ItemStack getResult(AnvilCraftingMenu menu);
	
	/**
	 * returns the crafting result of the given material.<br>
	 * this method is responsible for consuming materials.<br>
	 */
	public ItemStack craft(AnvilCraftingMenu menu);
}
