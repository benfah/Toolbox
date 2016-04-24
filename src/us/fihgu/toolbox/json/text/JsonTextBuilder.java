package us.fihgu.toolbox.json.text;

import java.util.ArrayList;

/**
 * A JsonText builder. <br>
 * Appended JsonText will not effect later appended Jsontext's style.<br>
 * Instead of that, a style base JsonText is used to control the style all components will inherit.<br>
 * @author fihgu
 *
 */
public class JsonTextBuilder
{
	protected JsonText base = new JsonText("");
	protected ArrayList<JsonText> components = new ArrayList<>();
	
	public JsonTextBuilder()
	{
		
	}
	
	/**
	 * @return a JsonText that's used as the parent of all appended JsonText<br>
	 * Appended JsonText will inherit styles from this base.
	 */
	public JsonText getStyleBase()
	{
		return this.base;
	}
	
	/**
	 * append the given JsonText to the end of the array.
	 */
	public void append(JsonText text)
	{
		this.components.add(text);
	}
	
	public ArrayList<JsonText> getComponents()
	{
		return this.components;
	}
	
	/**
	 * @return the reuslt of this builder.
	 */
	public JsonText toJsonText()
	{
		base.extra = this.components.toArray();
		return this.base;
	}
}
