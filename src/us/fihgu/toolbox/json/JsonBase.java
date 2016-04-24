package us.fihgu.toolbox.json;

import com.google.gson.Gson;

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
}
