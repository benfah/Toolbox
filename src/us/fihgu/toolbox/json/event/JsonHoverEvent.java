package us.fihgu.toolbox.json.event;

import us.fihgu.toolbox.json.JsonBase;

public class JsonHoverEvent extends JsonBase
{
	public HoverEventAction action;
	public String value;
	
	public JsonHoverEvent(HoverEventAction action, String value)
	{
		this.action = action;
		this.value = value;
	}
}
