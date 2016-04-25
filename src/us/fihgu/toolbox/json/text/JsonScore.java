package us.fihgu.toolbox.json.text;

import us.fihgu.toolbox.json.JsonBase;

public class JsonScore extends JsonBase
{
	public String name;
	public String objective;
	public String value;
	
	public JsonScore(String name, String objective)
	{
		this.name = name;
		this.objective = objective;
	}
}
