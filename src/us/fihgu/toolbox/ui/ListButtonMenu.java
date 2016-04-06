package us.fihgu.toolbox.ui;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;

/**
/* the ListButtonMenu contains a row of navigation bar<br>
/* while all other rows are used to list items.
/*/
public class ListButtonMenu extends InventoryMenu implements IButtonMenu
{
	protected ArrayList<Button> buttons = new ArrayList<Button>();
	protected Button[] navigation = new Button[CHEST_COLUMN_COUNT];
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
		this.updateSlotCount();
		this.setupNavigation();
		this.update();
	}
	
	protected void updateSlotCount()
	{
		this.slotCount = this.inventory.getSize() - CHEST_COLUMN_COUNT;
	}
	
	public int getPage()
	{
		return this.page;
	}
	
	public int getMaxPage()
	{
		return (int) (Math.ceil(this.buttons.size() / ((double)slotCount)));
	}
	
	public void nextPage()
	{
		this.page++;
		checkPageRange();
		this.update();
	}
	
	public void lastPage()
	{
		this.page--;
		checkPageRange();
		this.update();
	}
	
	private void checkPageRange()
	{
		int minPage = 0;
		int maxPage = this.getMaxPage();
		
		if(this.page < minPage)
		{
			this.page = Math.max(maxPage - 1, minPage);
		}
		
		if(this.page >= maxPage)
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
		this.inventory.clear();
		
		//navigation:
		for(int i = 0; i < this.navigation.length; i++)
		{
			Button button = this.navigation[i];
			if(button != null)
			{
				button.onUpdate();
				this.inventory.setItem(i, button.getItem());
			}
		}
		
		//listed items:
		int startIndex = this.slotCount * this.page;
		int endIndex = this.slotCount * (this.page + 1);
		
		for(int index = startIndex; index < endIndex && index < buttons.size(); index++)
		{
			Button button = buttons.get(index);
			if(button != null)
			{
				button.onUpdate();
				int slot = index - startIndex + CHEST_COLUMN_COUNT;
				
				if(slot >= this.inventory.getSize())
				{
					break;
				}
				
				this.inventory.setItem(slot, button.getItem());
			}
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
		
		Button pageInfoBtn = new PageInfoButton(this);
		
		this.navigation[6] = lastPageBtn;
		this.navigation[7] = pageInfoBtn;
		this.navigation[8] = nextPageBtn;
	}
	
	@Override
	public void onClick(InventoryClickEvent event)
	{
		super.onClick(event);
		
		int slot = event.getSlot();
		
		if(slot < 0)
		{
			return;
		}
		else if(slot < CHEST_COLUMN_COUNT)
		{
			Button button = this.navigation[slot];
			if(button != null)
			{
				button.onClick(event);
			}
		}
		else
		{
			int index = slot - CHEST_COLUMN_COUNT + (this.page * this.slotCount);

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
}
