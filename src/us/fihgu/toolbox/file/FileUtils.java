package us.fihgu.toolbox.file;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import org.bukkit.plugin.java.JavaPlugin;

public class FileUtils
{
	public static void createFileAndPath(File file) throws IOException
	{
		File path = file.getParentFile();
		
		if(path != null && !path.exists())
		{
			path.mkdirs();
		}
		
		if(!file.exists())
		{
			file.createNewFile();
		}
	}
	
	public static void copyFolder(File from, File to) throws IOException
	{
		org.apache.commons.io.FileUtils.copyDirectory(from, to);
	}
	
	public static void deleteFolder(File folder) throws IOException
	{
		org.apache.commons.io.FileUtils.deleteDirectory(folder);
	}
	
	public static void copyStreams(InputStream in, OutputStream out) throws IOException
	{
		byte[] buffer = new byte[4096];
		int length = 0;
		
		try
		{
			while((length = in.read(buffer)) > 0)
			{
				out.write(buffer, 0 ,length);
			}
		}
		finally
		{
			out.flush();
			out.close();
			in.close();
		}
	}
	
	/**
	 * 
	 * @param plugin
	 * @return the jar file of the given plugin
	 */
	public static String getPluginJarPath(JavaPlugin plugin)
	{
        String path = new File(plugin.getClass().getProtectionDomain().getCodeSource().getLocation().getPath()).getAbsolutePath();
        try
		{
			path = URLDecoder.decode(path, "UTF-8");
		}
        catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
        return path;
    }
}
