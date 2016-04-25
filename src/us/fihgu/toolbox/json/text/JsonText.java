package us.fihgu.toolbox.json.text;

import us.fihgu.toolbox.json.JsonBase;
import us.fihgu.toolbox.json.event.JsonClickEvent;
import us.fihgu.toolbox.json.event.JsonHoverEvent;

/**
 * can be used with "/tellraw" command <br>
 * Or you can send it to a player with {@link us.fihgu.toolbox.packet.PacketUtils#sendJsonMessage(org.bukkit.entity.Player, JsonText) PacketUtils.sendJsonMessage(Player, JsonText)}
 * @author fihgu
 *
 */
public class JsonText extends JsonBase
{
	public String text;
	public String translate;
	public Object[] with;
	public JsonScore score;
	public String selector;
	public JsonChatColor color;
	public Boolean bold;
	public Boolean italic;
	public Boolean underlined;
	public Boolean strikethrough;
	public Boolean obfuscated;
	public String insertion;
	public JsonClickEvent clickEvent;
	public JsonHoverEvent hoverEvent;
	public Object[] extra;
	
	public JsonText(String text)
	{
		this.text = text;
	}
	
	public JsonText(String text, boolean isTranslation)
	{
		if(isTranslation)
		{
			this.translate = text;
		}
		else
		{
			this.text = text;
		}
	}
}
