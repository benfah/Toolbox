package us.fihgu.toolbox.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import us.fihgu.toolbox.file.FileUtils;

public class JsonUtils
{
	public static Gson gson;
	
	static
	{
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();
		gson = gsonBuilder.create();
	}
	
	public static Object fromFile(File file, Class<?> type)
	{
		if(file.exists())
		{
			StringBuilder str = new StringBuilder();
			try(Scanner scan = new Scanner(new FileInputStream(file));)
			{
				while(scan.hasNextLine())
				{
					str.append(scan.nextLine() + "\n");
				}
				
				return gson.fromJson(str.toString(), type);
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
				return null;
			}
		}
		
		return null;
	}
	
	public static void toFile(File file, Object obj)
	{
		if(!file.exists())
		{
			try
			{
				FileUtils.createFileAndPath(file);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		
		String str = gson.toJson(obj);
		try(PrintWriter out = new PrintWriter(new FileOutputStream(file));)
		{
			out.print(str);
			out.flush();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
