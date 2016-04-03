package us.fihgu.toolbox.item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.Arrays;

public class ItemUtils
{
	public static ItemStack setLore(ItemStack item, String[] lores)
	{
		ItemMeta meta = item.getItemMeta();
		meta.setLore(Arrays.asList(lores));
		
		return item;
	}
	
	public static ItemStack setLore(ItemStack item, String lore)
	{
		return setLore(item, new String[]{lore});
	}
	
	public static ItemStack setDisplayName(ItemStack item, String name)
	{
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(name);
		return item;
	}
}
