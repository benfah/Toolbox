package us.fihgu.toolbox.lib;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.bukkit.plugin.java.JavaPlugin;

import us.fihgu.toolbox.file.FileUtils;

public class LibraryUtils
{
	/**
	 *extract contents from "libs/"  folder from given plugin's jar file, and register them to given plugin's ClassLoader.
	 */
	public static void loadLibraries(JavaPlugin plugin)
	{
		ZipFile zip;
		try
		{
			zip = new ZipFile(FileUtils.getPluginJarPath(plugin));
		} 
		catch (IOException e)
		{
			System.err.println("Failed to open plugin jar for " + plugin.getName());
			e.printStackTrace();
			return;
		}

		try
		{
			Enumeration<? extends ZipEntry> entries = zip.entries();
			while (entries.hasMoreElements())
			{
				ZipEntry entry = entries.nextElement();

				if (!entry.isDirectory() && entry.getName().startsWith("libs/"))
				{
					System.out.println(plugin.getName() + " is Loading " + entry.getName());
					String[] parts = entry.getName().split("/");
					String name = parts[parts.length - 1];

					File output = new File("libs/" + name);
					try
					{
						if (!output.exists())
						{
							FileUtils.createFileAndPath(output);

							FileOutputStream out = new FileOutputStream(output);
							InputStream in = zip.getInputStream(entry);

							FileUtils.copyStreams(in, out);
						}
						URL url = output.toURI().toURL();
						LibraryUtils.addToClasspath(plugin, url);
						
					} catch (Exception e)
					{
						System.err.println(plugin.getName() + " failed to load " + output.getName() + ".");
						e.printStackTrace();
					}

				}
			}
		} finally
		{
			try
			{
				zip.close();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	public static void addToClasspath(JavaPlugin plugin, URL url) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		Object loader = plugin.getClass().getClassLoader();
		Method addUrl = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
		addUrl.setAccessible(true);
		addUrl.invoke(loader, url);
		addUrl.setAccessible(false);
	}
}
