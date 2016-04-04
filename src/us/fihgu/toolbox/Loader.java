package us.fihgu.toolbox;
import org.bukkit.plugin.java.JavaPlugin;
import us.fihgu.toolbox.ui.MenuListener;

public class Loader extends JavaPlugin
{
	@Override
	public void onEnable()
	{
		new MenuListener().register(this);
		System.out.print("fihgu's Toolbox is loaded.");
	}
}
