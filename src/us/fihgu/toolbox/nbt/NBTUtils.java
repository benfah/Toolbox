package us.fihgu.toolbox.nbt;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.inventory.ItemStack;

import us.fihgu.toolbox.reflection.ReflectionUtils;

public class NBTUtils
{
	public static NBTCompoundWrapper getNBTCompound(ItemStack item)
	{
		Class<?> craftItemStackClass = ReflectionUtils.getCraftBukkitClass("inventory.CraftItemStack");
		Class<?> nmsItemStackClass = ReflectionUtils.getNMSClass("ItemStack");
		try
		{
			Object nmsItemStack = craftItemStackClass.getMethod("asNMSCopy", ItemStack.class).invoke(null, item);
			Object nmsNBT = nmsItemStackClass.getMethod("getTag").invoke(nmsItemStack);
			
			if(nmsNBT == null)
			{
				return null;
			}
			
			NBTCompoundWrapper nbt = new NBTCompoundWrapper(nmsNBT);
			
			if(nbt != null)
			{
				return nbt;
			}
		} 
		catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
