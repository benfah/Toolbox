package us.fihgu.toolbox.nbt;

import us.fihgu.toolbox.reflection.ClassType;
import us.fihgu.toolbox.reflection.WarpsClass;

@WarpsClass(className = "NBTTagCompound", classType = ClassType.NMS)
public class NBTCompoundWrapper extends NBTBaseWrapper
{	
	public NBTCompoundWrapper()
	{
		try
		{
			this.instance = this.getWrappedClass().getConstructor().newInstance();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public NBTCompoundWrapper(Object instance)
	{
		super(instance);
	}
	
	
	public void set(String key, NBTBaseWrapper nbtBase)
	{
		this.invoke("set", void.class, key, nbtBase.getInstance());
	}
	public void setByte(String key, byte b)
	{
		this.invoke("setByte", void.class, key, b);
	}
	public void setShort(String key, short s)
	{
		this.invoke("setShort", void.class, key, s);
	}
	public void setInt(String key, int i)
	{
		this.invoke("setInt", void.class, key, i);
	}
	public void setLong(String key, long l)
	{
		this.invoke("setLong", void.class, key, l);
	}
	public void setFloat(String key, float f)
	{
		this.invoke("setFloat", void.class, key, f);
	}
	public void setDouble(String key, double d)
	{
		this.invoke("setDouble", void.class, key, d);
	}
	public void setString(String key, String text)
	{
		this.invoke("setString", void.class, key, text);
	}
	public void setByteArray(String key, byte[] bytes)
	{
		this.invoke("setByteArray", void.class, key, bytes);
	}
	public void setIntArray(String key, int[] ints)
	{
		this.invoke("setIntArray", void.class, key, ints);
	}
	public void setBoolean(String key, boolean bool)
	{
		this.invoke("setBoolean", void.class, key, bool);
	}
	
	
	
	public NBTBaseWrapper get(String key)
	{
		Object result = this.invoke("get", getWrappedClass(NBTBaseWrapper.class), key);
		if(result != null)
		{
			return new NBTBaseWrapper(result);
		}
		else
		{
			return null;
		}
	}
	public NBTCompoundWrapper getCompound(String key)
	{
		Object result = this.invoke("getCompound", getWrappedClass(NBTCompoundWrapper.class), key);
		if(result != null)
		{
			return new NBTCompoundWrapper(result);
		}
		else
		{
			return null;
		}
	}
	public NBTListWrapper getList(String key, int typeId)
	{
		Object result = this.invoke("getList", getWrappedClass(NBTListWrapper.class), key, typeId);
		if(result != null)
		{
			return new NBTListWrapper(result);
		}
		else
		{
			return null;
		}
	}
	public byte getByte(String key)
	{
		return this.invoke("getByte", byte.class, key);
	}
	public short getShort(String key)
	{
		return this.invoke("getShort", short.class, key);
	}
	public int getInt(String key)
	{
		return this.invoke("getInt", int.class, key);
	}
	public long getLong(String key)
	{
		return this.invoke("getLong", long.class, key);
	}
	public float getFloat(String key)
	{
		return this.invoke("getFloat", float.class, key);
	}
	public double getDouble(String key)
	{
		return this.invoke("getDouble", double.class, key);
	}
	public String getString(String key)
	{
		return this.invoke("getString", String.class, key);
	}
	public byte[] getByteArray(String key)
	{
		return this.invoke("getByteArray", byte[].class, key);
	}
	public int[] getIntArray(String key)
	{
		return this.invoke("getIntArray", int[].class, key);
	}
	public boolean getBoolean(String key)
	{
		return this.invoke("getBoolean", boolean.class, key);
	}
	public void remove(String key)
	{
		this.invoke("remove", void.class, key);
	}
	
	public boolean hasKey(String key)
	{
		return this.invoke("hasKey", boolean.class, key);
	}
	public boolean hasKeyOfType(String key, NBTType typeId)
	{
		return this.invoke("hasKeyOfType", boolean.class, key, typeId.getId());
	}
	public boolean isEmpty()
	{
		return this.invoke("isEmpty", boolean.class);
	}
	
	public byte getTypeId()
	{
		return this.invoke("getTypeId", byte.class);
	}
	
	public String toString()
	{		
		return this.invoke("toString", String.class);
	}
}
