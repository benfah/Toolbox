package us.fihgu.toolbox.resourcepack;

import us.fihgu.toolbox.json.JsonBase;

public class OverrideEntry extends JsonBase
{
	public Predicate predicate = new Predicate();
	public String model;
	
	public OverrideEntry(String model)
	{
		this.model = model;
	}
}
