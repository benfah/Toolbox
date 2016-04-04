package us.fihgu.toolbox.ui;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import us.fihgu.toolbox.item.ItemUtils;
import org.bukkit.event.inventory.InventoryClickEvent;

public abstract class Button
{
	protected ItemStack item;
	
	public Button(ItemStack item)
	{
		this.item = item;
	}
	
	public Button(Material material, String name, String[] lores, int amount)
	{
		ItemStack item = new ItemStack(material, amount);
		item = ItemUtils.setDisplayName(item, name);
		item = ItemUtils.setLore(item, lores);
		
		this.item = item;
	}
	
	public ItemStack getItem()
	{
		return this.item;
	}
	
	public void setItem(ItemStack item)
	{
		this.item = item;
	}
	
	public Button(Material material, String name, String lore, int amount)
	{
		this(material, name, new String[]{lore}, amount);
	}
	
	public void onUpdate()
	{
		
	}
	
	public abstract void onClick(InventoryClickEvent event);
	
}
