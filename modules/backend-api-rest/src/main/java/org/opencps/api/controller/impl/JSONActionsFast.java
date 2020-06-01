package org.opencps.api.controller.impl;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Validator;

public class JSONActionsFast
{
	public static String getJSONObjectString(JSONObject input , String key)
	{
		if (input.has(key) && Validator.isNotNull(input.getString(key)))
			return input.getString(key);
		return "";
	}
	public static JSONObject getJSONObject(JSONObject input , String key)
	{
		if (input.has(key) && Validator.isNotNull(input.getString(key)))
			return input.getJSONObject(key);
		return null;
	}
	public static JSONArray getJSONOArray(JSONObject input , String key)
	{
		if (input.has(key) && Validator.isNotNull(input.getString(key)))
			return input.getJSONArray(key);
		return null;
	}
}
