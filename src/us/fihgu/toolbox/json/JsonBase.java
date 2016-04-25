package us.fihgu.toolbox.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonBase
{
	public static Gson gson = new Gson();
	
	/**
	 * deserialize from json string<br>
	 * @return the deserialized object
	 */
	public static <T> T fromJson(String json, Class<T> classType)
	{
		return gson.fromJson(json, classType);
	}
	
	/**
	 * @return serialized json string
	 */
	@Override
	public String toString()
	{
		return gson.toJson(this);
	}
	
	/**
	 * @return serialized json string, but in a readable format
	 */
	public String toPrettyString()
	{
		Gson pretty = new GsonBuilder().setPrettyPrinting().create();
		return pretty.toJson(this);
	}
}
