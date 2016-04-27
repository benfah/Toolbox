package us.fihgu.toolbox.file;

import java.io.File;
import java.io.IOException;

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
}
