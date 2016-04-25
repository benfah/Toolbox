package us.fihgu.toolbox.json.event;

import us.fihgu.toolbox.json.JsonBase;

public class JsonClickEvent extends JsonBase
{
	public ClickEventAction action;
	public String value;
	
	public JsonClickEvent(ClickEventAction action, String value)
	{
		this.action = action;
		this.value = value;
	}
}
