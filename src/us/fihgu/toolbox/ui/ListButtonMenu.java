package us.fihgu.toolbox.ui;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;


public class ListButtonMenu extends InventoryMenu implements IButtonMenu
{
	protected ArrayList<Button> buttons = new ArrayList<Button>();
	
	/**
	 * the number of available slots for this list. <br>
	 * this needs to be updated if the internal inventory of this menu changes.
	 */
	protected int slotCount;
	
	/**
	 * current page
	 */
	protected int page;
	
	/**
	 * @param rows must be 2~6.
	 */
	public ListButtonMenu(String title, int rows)
	{
		super(title, Math.max(rows, 2));
		updateSlotCount();
	}
	
	protected void updateSlotCount()
	{
		this.slotCount = (this.inventory.getSize() - 1) * CHEST_COLUMN_COUNT;
	}
	
	public int getPage()
	{
		return this.page;
	}
	
	public int getMaxPage()
	{
		return (int) Math.ceil(this.buttons.size() / (double)slotCount) - 1;
	}
	
	public void nextPage()
	{
		this.page++;
		checkPageRange();
	}
	
	public void lastPage()
	{
		this.page--;
		checkPageRange();
	}
	
	private void checkPageRange()
	{
		int minPage = 0;
		int maxPage = this.getMaxPage();
		
		if(this.page < minPage)
		{
			this.page = maxPage;
		}
		
		if(this.page > maxPage)
		{
			this.page = minPage;
		}
	}

	@Override
	public void setButtonAndUpdate(int index, Button button)
	{
		this.setButton(index, button);
		this.update();
	}

	@Override
	public void setButton(int index, Button button)
	{
		for(int i = index; i > this.buttons.size(); i--)
		{
			this.buttons.add(null);
		}
		
		if(index == this.buttons.size())
		{
			this.addButton(button);
		}
		else
		{
			this.buttons.set(index, button);
		}
	}
	
	public void addButton(Button button)
	{
		this.buttons.add(button);
	}
	
	public void addButtonAndUpdate(Button button)
	{
		this.addButton(button);
		this.update();
	}

	@Override
	public void removeButton(int index)
	{
		if(index < this.buttons.size())
		{
			this.buttons.remove(index);
		}
	}

	@Override
	public void removeButtonAndUpdate(int index)
	{
		this.removeButton(index);
		this.update();
	}

	@Override
	public void update()
	{
		int startIndex = this.slotCount * this.page;
		int endIndex = this.slotCount * (this.page + 1);
		
		for(int index = startIndex; index < endIndex && index < buttons.size(); index++)
		{
			Button button = buttons.get(index);
			button.onUpdate();
			int slot = index - startIndex + CHEST_COLUMN_COUNT;
			this.inventory.setItem(slot, button.getItem());
		}
	}
	
	/**
	 * setup the navigation bar.
	 */
	public void setupNavigation()
	{
		Button nextPageBtn = new Button(Material.BRICK_STAIRS, "Next Page", "", 1)
		{
			@Override
			public void onClick(InventoryClickEvent event)
			{
				nextPage();
			}
		};
		
		Button lastPageBtn = new Button(Material.BRICK_STAIRS, "Last Page", "", 1)
		{
			@Override
			public void onClick(InventoryClickEvent event)
			{
				lastPage();
			}
		};
		
		Button pageInfo = new PageInfoButton(this);
		
		this.
	}
	
	@Override
	public void onClick(InventoryClickEvent event)
	{
		super.onClick(event);
		
		int index = event.getSlot() + (this.page * this.slotCount);
		
		if(index >= this.buttons.size())
		{
			return;
		}
		
		Button button = this.buttons.get(index);
		
		if(button != null)
		{
			button.onClick(event);
		}
	}
}
