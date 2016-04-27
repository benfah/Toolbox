package us.fihgu.toolbox.world;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;

import us.fihgu.toolbox.file.FileUtils;

public class MapManager
{
	/**
	 * create a world with given name <br>
	 * Also loads the given map.<br>
	 * 
	 * @param name the name of the created world
	 * @param mapFolder the directory of the map you want to load.<br>
	 * if it is null, it will load existing world folder in your minecraft directory, or create an empty world.
	 */
	public static World createWorld(String name, File mapFolder)
	{
		World oldWorld = Bukkit.getWorld(name);
		if(oldWorld != null)
		{
			System.err.println("world " + name + " already exist!" );
			return oldWorld;
		}
		
		try
		{
			File worldFolder = new File("./" + name + "/");		
			if (mapFolder != null && mapFolder.exists())
			{
				FileUtils.deleteFolder(worldFolder);
				FileUtils.copyFolder(mapFolder, worldFolder);
			}
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}

		WorldCreator creator = new WorldCreator(name);
		creator.generator(new EmptyChunkGenerator());
		World world = creator.createWorld();
		
		return world;
	}

	/**
	 * unloads world form memory and deletes save files on disk. <br>
	 * @param world the world to unload.
	 * @param playerRetreatLocation should be a location in another world.<br>
	 * players in this world will be teleported to given location before unloading.<br>
	 * if it is null, or in the same world, players in this world will be teleported to the spawn location of dimension 0.<br>
	 */
	public static void deleteWorld(String worldName, Location playerRetreatLocation)
	{
		World world = Bukkit.getWorld(worldName);
		
		boolean clear = unloadWorld(world, playerRetreatLocation);
		
		if (clear)
		{
			try
			{
				File folder = new File("./" + worldName);
				FileUtils.deleteFolder(folder);
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			System.err.println("Could not delete world: " + world.getName());
		}
	}
	
	/**
	 * unloads world form memory<br>
	 * @param world the world to unload.
	 * @param playerRetreatLocation should be a location in another world.<br>
	 * players in this world will be teleported to given location before unloading.<br>
	 * if it is null, or in the same world, players in this world will be teleported to the spawn location of dimension 0.<br>
	 * @return true if the world was successfully unloaded.
	 */
	public static boolean unloadWorld(World world, Location playerRetreatLocation)
	{
		if (world == null)
		{
			return true;
		}
		
		if(playerRetreatLocation != null && playerRetreatLocation.getWorld() != world)
		{
			for(Player player : world.getPlayers())
			{
				player.teleport(playerRetreatLocation);
			}
		}
		else
		{
			for(Player player : world.getPlayers())
			{
				player.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
			}
		}
		
		return Bukkit.unloadWorld(world, false);
	}

	/**
	 * save the given world as a map in the given directory.
	 */
	public static void saveMap(World world, File path)
	{
		world.save();
		try
		{
			if (path.exists())
			{
				FileUtils.deleteFolder(path);
			}
			FileUtils.copyFolder(world.getWorldFolder(), path);
			
			// delete player location, inventory data, uid.
			FileUtils.deleteFolder(new File(path.getPath() + "/playerdata"));
			new File(path.getPath() + "/uid.dat").delete();
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
