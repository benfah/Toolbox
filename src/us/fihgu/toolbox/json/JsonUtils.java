package us.fihgu.toolbox.json;

import org.bukkit.entity.Player;

import com.google.gson.Gson;

import us.fihgu.toolbox.json.text.JsonText;

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
	
	
	public static void sendJsonMessage(Player player, JsonText jObj)
	{
		//TODO: send JsonText though packet
	}
}
