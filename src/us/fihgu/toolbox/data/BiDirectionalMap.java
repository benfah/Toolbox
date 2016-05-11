package us.fihgu.toolbox.data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

import us.fihgu.toolbox.json.JsonBase;

/**
 * A map that both key, and value are unique.
 * @author fihgu
 */
public class BiDirectionalMap<K,V> extends JsonBase implements Serializable
{
	private static final long serialVersionUID = 1L;
	protected HashMap<K, V> keyMap = new HashMap<K, V>();
	protected HashMap<V, K> valueMap = new HashMap<V, K>();
	
	public void put(K key, V value)
	{
		V oldValue = keyMap.get(key);
		K oldKey = valueMap.get(value);
		
		if(oldKey != null)
		{
			keyMap.remove(oldKey);
		}
		
		if(oldValue != null)
		{
			valueMap.remove(oldValue);
		}
		
		keyMap.put(key, value);
		valueMap.put(value, key);
	}
	
	public V removeKey(K key)
	{
		V value = keyMap.remove(key);
		if(value != null)
		{
			valueMap.remove(value);
		}
		return value;
	}
	
	public K removeValue(V value)
	{
		K key = valueMap.remove(value);
		if(key != null)
		{
			keyMap.remove(key);
		}
		return key;
	}
	
	public V getValue(K key)
	{
		return keyMap.get(key);
	}
	
	public K getKey(V value)
	{
		return valueMap.get(value);
	}
	
	public boolean containsKey(K key)
	{
		return keyMap.containsKey(key);
	}
	
	public boolean containsValue(V value)
	{
		return keyMap.containsKey(value);
	}
	
	public void clear()
	{
		keyMap.clear();
		valueMap.clear();
	}
	
	public int size()
	{
		return keyMap.size();
	}
	
	public Set<K> getKeySet()
	{
		return keyMap.keySet();
	}
	
	public Set<V> getValueSet()
	{
		return valueMap.keySet();
	}
}
