package us.fihgu.toolbox.json.event;


public enum HoverEventAction
{
	show_text, 
	
	show_achievement, 
	/**
	 * takes an ItemStack in the format of nbtCompound string<br>
	 * you can generate a compound easily with {@link us.fihgu.toolbox.item.ItemUtils#toNBTCompoound(org.bukkit.inventory.ItemStack) ItemUtils.toNBTCompoound(ItemStack item)}<br>
	 * then use {@link us.fihgu.toolbox.nbt.NBTCompoundWrapper#toString() toString()} method to get the string.
	 */
	show_item, 
	show_entity;
}
