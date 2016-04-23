package us.fihgu.toolbox.json;

import java.util.ArrayList;

public class JsonArray extends JsonObject
{
	protected ArrayList<Object> array = new ArrayList<>();
	
	public ArrayList<Object> getArray()
	{
		return this.array;
	}
	
	@Override
	public String toString()
	{
		String text = "";
		boolean firstEntry = true;
		
		for(Object obj : array)
		{
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
			
			text += value; 
		}
		
		
		text = "[" + text + "]";
		return text;
	}
}
