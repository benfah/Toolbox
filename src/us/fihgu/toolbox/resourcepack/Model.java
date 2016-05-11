package us.fihgu.toolbox.resourcepack;

import java.util.HashMap;
import us.fihgu.toolbox.json.JsonBase;

public class Model extends JsonBase
{
	public String parent = "item/handheld";
	public HashMap<String, String> textures = new HashMap<>();
	public OverrideEntry[] overrides;
	
	public String getParent()
	{
		return this.parent;
	}
	
	/**
	 * A convenience method that creates a simple model with one texture.
	 */
	public static Model CreateSimpleModel(String texture)
	{
		Model model = new Model();
		model.textures.put("layer0", texture);
		
		return model;
	}
}
