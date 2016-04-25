package us.fihgu.toolbox.packet;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.bukkit.entity.Player;

import us.fihgu.toolbox.json.text.JsonText;
import us.fihgu.toolbox.reflection.ReflectionUtils;

public class PacketUtils
{
	public static void sendJsonMessage(Player player, JsonText message)
	{
		try
		{
			Class<?> craftPlayerClass = ReflectionUtils.getCraftBukkitClass("entity.CraftPlayer");
			Object handle = craftPlayerClass.getMethod("getHandle").invoke(player);
			Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
			Method sendPacket = playerConnection.getClass().getMethod("sendPacket", ReflectionUtils.getNMSClass("Packet"));
			
			Class<?> packetPlayerOutChatClass = ReflectionUtils.getNMSClass("PacketPlayOutChat");
			Class<?> chatSerializerClass = ReflectionUtils.getNMSClass("IChatBaseComponent$ChatSerializer");
			
			Constructor<?> packetPlayerOutChatConstructor = packetPlayerOutChatClass.getConstructor(ReflectionUtils.getNMSClass("IChatBaseComponent"));
			Object text = chatSerializerClass.getMethod("a", String.class).invoke(null, message.toString());
			
			Object packet = packetPlayerOutChatConstructor.newInstance(text);
			sendPacket.invoke(playerConnection, packet);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
