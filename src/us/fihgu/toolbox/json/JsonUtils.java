package us.fihgu.toolbox.json;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.google.gson.Gson;

public class JsonUtils
{
	public static Gson gson = new Gson();
	
	/**
	 * escape all quotation mark in a string<br>
	 * used when parsing a json String as normal string.
	 */
	public static String escapeQuotation(String original)
	{
		String result = "";
		for(char c : original.toCharArray())
		{
			if(c == '"')
			{
				result += "\"";
			}
			else
			{
				result += c;
			}
		}
		
		return result;
	}
	
	/**
	 * message is sent though the /tellraw command
	 */
	public static void sendJsonMessage(Player player, JsonObject jObj)
	{
		//TODO: use packets instead.
		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "tellraw " + player.getName() + " " + jObj.toString());
	}
	
	/**
	 * message is sent though the /tellraw command
	 */
	public static void broadcastJsonMessage(JsonObject jObj)
	{
		Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "tellraw @a " + jObj.toString());
	}
}