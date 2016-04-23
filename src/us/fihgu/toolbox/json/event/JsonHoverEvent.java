package us.fihgu.toolbox.json.event;

import us.fihgu.toolbox.json.JsonUtils;

public class JsonHoverEvent extends JsonEvent
{
	final public static String NAME = "hoverEvent";
	protected Action action;
	
	public JsonHoverEvent(Action action)
	{
		this.pairs.put("action", action.name());
		this.action = action;
	}
	
	public Object getValue()
	{
		return this.pairs.get("value");
	}
	
	public void setValue(Object value)
	{
		this.pairs.put("value", value);
	}
	
	public enum Action
	{
		show_text, 
		show_achievement,
		/**
		 * show_item accepts an ItemStack in the form of unnamed NBT compound<br>
		 * use {@link JsonUtils#escapeQuotation(String)} to escape the nbt string before use it as value.
		 */
		show_item, 
		show_entity;
	}
}
