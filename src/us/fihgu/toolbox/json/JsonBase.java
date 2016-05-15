package us.fihgu.toolbox.json;

public class JsonBase
{
	
	/**
	 * @return serialized json string
	 */
	@Override
	public String toString()
	{
		return JsonUtils.gson.toJson(this);
	}
}
