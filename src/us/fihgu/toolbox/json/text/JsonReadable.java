package us.fihgu.toolbox.json.text;

import java.util.HashMap;

import us.fihgu.toolbox.json.JsonArray;
import us.fihgu.toolbox.json.JsonObject;
import us.fihgu.toolbox.json.event.JsonClickEvent;
import us.fihgu.toolbox.json.event.JsonHoverEvent;

/**
 * this class is not meant to be directly used.
 */
class JsonReadable extends JsonObject
{
	
	
	public JsonReadable(String text)
	{
		this.pairs.put(getKeyWord(), text);
	}
	
	/**
	 * 
	 * @return
	 */
	protected String getKeyWord()
	{
		return "String";
	}
	
	public void setText(String text)
	{
		this.pairs.put(getKeyWord(), text);
	}
	
	public String getText()
	{
		Object obj = this.pairs.get(getKeyWord());
		String text = null;
		
		if(obj != null && obj instanceof String)
		{
			text = (String)obj;
		}
		
		return text;
	}
	
	public JsonArray getExtra()
	{
		Object value = this.pairs.get("extra");
		if(value != null && value instanceof JsonArray)
		{
			return (JsonArray)value;
		}
		
		return null;
	}

	public void setExtra(JsonArray array)
	{
		this.pairs.put("extra", array);
	}
	
	public void removeExtra()
	{
		this.pairs.remove("extra");
	}
	
	public Color getColor()
	{
		Object value = this.pairs.get("color");
		if(value != null)
		{
			try
			{
				return Color.valueOf(value.toString());
			}
			catch(IllegalArgumentException e){}
		}
		
		return null;
	}

	public void setColor(Color color)
	{
		this.pairs.put("color", color.toString());
	}
	
	public void removeColor()
	{
		this.pairs.remove("color");
	}
	
	public void setStyle(ChatStyle style, Boolean value)
	{
		this.pairs.put(style.name(), value);
	}
	
	public Boolean getStyle(ChatStyle style)
	{
		Object obj = this.pairs.get(style.name());
		
		if(obj != null && obj instanceof Boolean)
		{
			return (Boolean)obj;
		}
		else
		{
			return null;
		}
	}
	
	public void removeStyle(ChatStyle style)
	{
		this.pairs.remove(style.name());
	}
	
	public void setInsertion(String text)
	{
		this.pairs.put("insertion", text);
	}
	
	public String getInsertion()
	{
		Object obj = this.pairs.get("insertion");
		String text = null;
		
		if(obj != null && obj instanceof String)
		{
			text = (String)obj;
		}
		
		return text;
	}
	
	public void removeInsertion()
	{
		this.pairs.remove("insertion");
	}
	
	public void setClickEvent(JsonClickEvent event)
	{
		this.pairs.put(JsonClickEvent.NAME, event);
	}
	
	public JsonClickEvent getClickEvent()
	{
		Object obj = this.pairs.get(JsonClickEvent.NAME);
		JsonClickEvent event = null;
		
		if(obj != null && obj instanceof JsonClickEvent)
		{
			event = (JsonClickEvent)obj;
		}
		
		return event;
	}
	
	public void removeClickEvent()
	{
		this.pairs.remove(JsonClickEvent.NAME);
	}
	
	public void setHoverEvent(JsonHoverEvent event)
	{
		this.pairs.put(JsonHoverEvent.NAME, event);
	}
	
	public JsonHoverEvent getHoverEvent()
	{
		Object obj = this.pairs.get(JsonHoverEvent.NAME);
		JsonHoverEvent event = null;
		
		if(obj != null && obj instanceof JsonHoverEvent)
		{
			event = (JsonHoverEvent)obj;
		}
		
		return event;
	}
	
	public void removeHoverEvent()
	{
		this.pairs.remove(JsonHoverEvent.NAME);
	}
	
	@Deprecated
	@Override
	public HashMap<String, Object> getPairs()
	{
		return super.getPairs();
	}
}
