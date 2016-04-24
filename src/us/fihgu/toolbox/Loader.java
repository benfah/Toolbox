package us.fihgu.toolbox;
import org.bukkit.plugin.java.JavaPlugin;

import us.fihgu.toolbox.ui.MenuListener;

public class Loader extends JavaPlugin
{
	public static Loader instance = null;
	
	public static boolean debug = false;
	
	@Override
	public void onEnable()
	{
		Loader.instance = this;
		new MenuListener().register(this);
		System.out.print("fihgu's Toolbox is loaded.");
		
		if(debug)
		{
			new Test().register(this);
		}
	}
}
