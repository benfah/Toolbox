package us.fihgu.toolbox.resourcepack;

import us.fihgu.toolbox.json.JsonBase;

public class OverrideEntry extends JsonBase
{
	public Predicate predicate;
	public String model;
	
	public OverrideEntry(Predicate predicate, String model)
	{
		this.predicate = predicate;
		this.model = model;
	}
}
