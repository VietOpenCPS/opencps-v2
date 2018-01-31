/**
 * @author GIAHUY
 *
 */
package com.fds.vr.business.action.util;

import java.util.Iterator;
import java.util.LinkedHashMap;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;

public class ConvertJONObjectUtils {

	private static Log _log = LogFactoryUtil.getLog(ConvertJONObjectUtils.class);
	
	public static LinkedHashMap<String, String> getKeyValuesMap(String formData) {
		LinkedHashMap<String, String> mapData = null;
		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(formData);
			mapData = parseJSONObject(jsonObject);
		} catch (Exception e) {
			_log.info("Can not parse json object from FormData: =>"
					+ " : Cause " + e.getCause());
		}

		return mapData;
	}
	// Parse Map
	/**
	 * @param keyValues
	 * @param JSONObject
	 * @return
	 * @throws JSONException
	 */
	private static LinkedHashMap<String, String> parseJSONObject(JSONObject json) {
	
		LinkedHashMap<String, String> mapObject = new LinkedHashMap<String, String>();
		if (json != null) {
			Iterator<String> itr = json.keys();
			while (itr.hasNext()) {
				String key = itr.next();
				String strObject = String.valueOf(json.get(key));
				// check json
				String strValueObject = StringPool.BLANK;
				try {
					JSONObject valueObject = JSONFactoryUtil.createJSONObject(strObject);
//					Object[] keyValue = new Object[2];
					strValueObject = valueObject.toString();

					mapObject.put(key, strValueObject);
					parseJSONObjectIndex(mapObject, json.getJSONObject(key), key);
				} catch (JSONException e) {
					// string
					strValueObject = strObject.toString();

					mapObject.put(key, strValueObject);
				}
			}
		}
	
		return mapObject;
	}
	//
	private static void parseJSONObjectIndex(LinkedHashMap<String, String> mapData, 
			JSONObject json, String keyJson) {

		if (json != null) {
			Iterator<String> itr = json.keys();
			while (itr.hasNext()) {
				String key = itr.next();
				String strObject = String.valueOf(json.get(key));
				// check json
				String keyObject = StringPool.BLANK;
				String strValueObject = StringPool.BLANK;
				try {
					JSONObject valueObject = JSONFactoryUtil.createJSONObject(strObject);
					keyObject = keyJson + "@" + key;
					strValueObject = valueObject.toString();

					mapData.put(keyObject.toLowerCase(), strValueObject);
					parseJSONObjectIndex(mapData, json.getJSONObject(key), keyObject);
				} catch (JSONException e) {
					// string
					keyObject = keyJson + "@" + key;
					strValueObject = strObject.toString();

					mapData.put(keyObject.toLowerCase(), strValueObject);
				}
			}
		}

	}

	//Return List
//	public static List<Object[]> getKeyValues(String formData) {
//		List<Object[]> keyValues = null;
//		if (Validator.isNotNull(formData)) {
//			keyValues = new ArrayList<Object[]>();
//	
//			keyValues = getKeyValues(formData, keyValues);
//		}
//
//		return keyValues;
//	}
	
//	private static List<Object[]> getKeyValues(String formData, List<Object[]> keyValues) {
//	
//		try {
//			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(formData);
//			parseJSONObject(keyValues, jsonObject);
//		} catch (Exception e) {
//			_log.info("Can not parse json object from FormData: =>"
//					+ " : Cause " + e.getCause());
//		}
//	
//		return keyValues;
//	
//	}
	
	/**
	 * @param keyValues
	 * @param JSONObject
	 * @return
	 * @throws JSONException
	 */
//	private static List<Object[]> parseJSONObject(List<Object[]> keyValues, JSONObject json) {
//	
//		List<Object[]> objects = new ArrayList<Object[]>();
//		if (json != null) {
//			Iterator<String> itr = json.keys();
//			while (itr.hasNext()) {
//				String key = itr.next();
//				String strObject = String.valueOf(json.get(key));
//				// check json
//				try {
//					JSONObject valueObject = JSONFactoryUtil.createJSONObject(strObject);
//					Object[] keyValue = new Object[2];
//					keyValue[0] = key;
//					keyValue[1] = valueObject.toString();
//
//					keyValues.add(keyValue);
//					parseJSONObjectIndex(keyValues, json.getJSONObject(key), key);
//				} catch (JSONException e) {
//					// string
//					Object[] keyValue = new Object[2];
//					keyValue[0] = key;
//					keyValue[1] = strObject.toString();
//
//					keyValues.add(keyValue);
//				}
//			}
//		}
//	
//		return objects;
//	}
	
	//
//	private static List<Object[]> parseJSONObjectIndex(List<Object[]> keyValues, JSONObject json, String keyJson) {
//	
//		List<Object[]> objects = new ArrayList<Object[]>();
//	
//		if (json != null) {
//			Iterator<String> itr = json.keys();
//			while (itr.hasNext()) {
//				String key = itr.next();
//				String strObject = String.valueOf(json.get(key));
//				// check json
//				try {
//					JSONObject valueObject = JSONFactoryUtil.createJSONObject(strObject);
//					Object[] keyValue = new Object[2];
//					keyValue[0] = keyJson + "@" + key;
//					keyValue[1] = valueObject.toString();
//
//					keyValues.add(keyValue);
//					parseJSONObjectIndex(keyValues, json.getJSONObject(key), keyValue[0].toString());
//				} catch (JSONException e) {
//					// string
//					Object[] keyValue = new Object[2];
//					keyValue[0] = keyJson + "@" + key;
//					keyValue[1] = strObject.toString();
//
//					keyValues.add(keyValue);
//				}
//			}
//		}
//	
//		return objects;
//	}

}