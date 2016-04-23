package us.fihgu.toolbox.json;

import java.util.HashMap;

public class JsonObject
{
	protected HashMap<String, Object> pairs = new HashMap<String, Object>();
	
	public HashMap<String, Object> getPairs()
	{
		return this.pairs;
	}
	
	@Override
	/**
	 * convert this JsonObject to Json String
	 */
	public String toString()
	{
		String text = "";
		boolean firstEntry = true;
		
		for(String key : pairs.keySet())
		{
			Object obj = pairs.get(key);
			String value;
			
			if(!firstEntry)
			{
				text+= ", ";
			}
			else
			{
				firstEntry = false;
			}
			
			if(obj instanceof String)
			{
				value = "\"" + ((String)obj) + "\"";
			}
			else
			{
				value = obj.toString();
			}
			
			text += "\"" + key + "\":" + value; 
		}
		
		
		text = "{" + text + "}";
		return text;
	}
}
