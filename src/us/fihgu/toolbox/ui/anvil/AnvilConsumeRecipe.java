package us.fihgu.toolbox.ui.anvil;

import java.util.List;

/**
/* A recipe that will consume both material when crafting.
/* The recipe will accept any amount of material that is acceptable.
/* Crafting will only be available when the correct amount and type of material is provided.
*/
public abstract class AnvilConsumeRecipe
{
	public abstract List<AnvilCraftingRecipes> getRecipes();
	
	//TODO: unfinished.
}
