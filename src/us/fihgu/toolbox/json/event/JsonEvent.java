package us.fihgu.toolbox.json.event;

import java.util.HashMap;

import us.fihgu.toolbox.json.JsonObject;

public class JsonEvent extends JsonObject
{
		/**
		 * @Deprecated do not access pairs directly.
		 */
		@Deprecated
		@Override
		public HashMap<String, Object> getPairs()
		{
			return super.getPairs();
		}
}
