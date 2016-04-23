package us.fihgu.toolbox.json.text;

import us.fihgu.toolbox.json.JsonArray;

public class JsonTranslate extends JsonReadable
{
	
	public JsonTranslate(String text)
	{
		super(text);
	}
	
	public JsonArray getWith()
	{
		Object value = this.pairs.get("with");
		if(value != null && value instanceof JsonArray)
		{
			return (JsonArray)value;
		}
		
		return null;
	}

	public void setWith(JsonArray array)
	{
		this.pairs.put("with", array);
	}
	
	@Override
	protected String getKeyWord()
	{
		return "translate";
	}
	
}
