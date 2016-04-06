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
		int maxPage = menu.getMaxPage();
		int page = menu.getPage();
		
		if(maxPage > 0)
		{
			page++;
		}
		
		String name = "Page: " + page + "/" + maxPage;
		ItemUtils.setDisplayName(this.item, name);
	}
}
