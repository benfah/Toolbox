package us.fihgu.toolbox.json.text;

public class JsonText extends JsonReadable
{

	public JsonText(String text)
	{
		super(text);
	}

	@Override
	protected String getKeyWord()
	{
		return "text";
	}
}


