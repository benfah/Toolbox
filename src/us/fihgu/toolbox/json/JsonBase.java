package us.fihgu.toolbox.json;

public class JsonBase
{
	/**
	 * deserialize from json string<br>
	 * @return the deserialized object
	 */
	public static <T> T fromJson(String json, Class<T> classType)
	{
		return JsonUtils.gson.fromJson(json, classType);
	}
	
	/**
	 * @return serialized json string
	 */
	@Override
	public String toString()
	{
		return JsonUtils.gson.toJson(this);
	}
}
