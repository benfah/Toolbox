package us.fihgu.toolbox.json.event;


public class JsonClickEvent extends JsonEvent
{
	final public static String NAME = "clickEvent";
	protected Action action;
	
	public JsonClickEvent(Action action)
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
	
	//TODO: validate values 
	
	public enum Action
	{
		open_url, open_file, run_command, suggest_command, change_page;
	}
}
