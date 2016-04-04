package us.fihgu.toolbox.ui;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

import us.fihgu.toolbox.item.ItemUtils;


public class PageInfoButton extends Button
{
	ListButtonMenu menu;
	public PageInfoButton(ListButtonMenu menu)
	{
		super(Material.PAPER, "Page: 1/1", "", 1);
		this.menu = menu;
	}
	
	public PageInfoButton(ListButtonMenu menu, Material material, String name, String lore, int amount)
	{
		super(material, name, lore, amount);
		this.menu = menu;
	}

	@Override
	public void onClick(InventoryClickEvent event)
	{
	}
	
	@Override
	public void onUpdate()
	{
		String name = "Page: " + menu.getPage() + "/" + menu.getMaxPage();
		ItemUtils.setDisplayName(this.item, name);
	}
}
