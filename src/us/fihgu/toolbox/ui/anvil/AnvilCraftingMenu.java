package us.fihgu.toolbox.ui.anvil;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import us.fihgu.toolbox.Loader;
import us.fihgu.toolbox.item.ItemUtils;

public abstract class AnvilCraftingMenu extends AnvilMenu
{

	public abstract List<AnvilCraftingRecipe> getRecipes();

	protected Location dropLocation;

	@Override
	public void onClick(InventoryClickEvent event)
	{
		super.onClick(event);

		int slot = event.getSlot();
		ItemStack item = event.getCursor();
		switch (slot)
		{
		case SLOT_RESULT:
			final AnvilCraftingRecipe recipe = this.getAvailableRecipe();
			if (recipe != null)
			{
				final InventoryView view = event.getView();
				BukkitRunnable task = new BukkitRunnable()
				{

					@Override
					public void run()
					{
						ItemStack result = recipe.craft(AnvilCraftingMenu.this);
						view.setCursor(result);
					}
				};
				task.runTaskLater(Loader.instance, 0);
			}
			break;
		case SLOT_LEFT:
		case SLOT_RIGHT:
			if (ItemUtils.notNullorAir(item))
			{
				if (this.accepts(slot, item))
				{
					event.setCancelled(false);
					BukkitRunnable task = new BukkitRunnable()
					{
						@Override
						public void run()
						{							
							AnvilCraftingRecipe recipe = AnvilCraftingMenu.this.getAvailableRecipe();
							if (recipe != null)
							{
								AnvilCraftingMenu.this.inventory.setItem(SLOT_RESULT, recipe.getResult(AnvilCraftingMenu.this));
							} else
							{
								AnvilCraftingMenu.this.inventory.setItem(SLOT_RESULT, null);
							}
						}
					};

					task.runTaskLater(Loader.instance, 0);
				}
			} else
			{
				event.setCancelled(false);
			}
			break;
		default:
			event.setCancelled(false);
			break;
		}
	}

	protected boolean accepts(int slot, ItemStack item)
	{
		for (AnvilCraftingRecipe recipe : this.getRecipes())
		{
			if (slot == SLOT_LEFT)
			{
				if (recipe.canLeftSlotAccept(item))
				{
					return true;
				}
			} else if (slot == SLOT_RIGHT)
			{
				if (recipe.canRightSlotAccept(item))
				{
					return true;
				}
			} else
			{
				return false;
			}
		}

		return false;
	}

	public Inventory getInventory()
	{
		return this.inventory;
	}

	/**
	 * @return a recipe that has result for current input.
	 */
	protected AnvilCraftingRecipe getAvailableRecipe()
	{
		for (AnvilCraftingRecipe recipe : this.getRecipes())
		{
			if (recipe.getResult(this) != null)
			{
				return recipe;
			}
		}

		return null;
	}

	public AnvilCraftingMenu(Location dropLocation)
	{
		this.dropLocation = dropLocation;
	}

	@Override
	public void onClose(InventoryCloseEvent event)
	{

		BukkitRunnable task = new BukkitRunnable()
		{
			@Override
			public void run()
			{
				if (AnvilCraftingMenu.this.inventory.getViewers().size() == 0)
				{
					AnvilCraftingMenu.this.dropItems(AnvilCraftingMenu.this.dropLocation);
				}

			}

		};
		task.runTaskLater(Loader.instance, 0);
	}
}
