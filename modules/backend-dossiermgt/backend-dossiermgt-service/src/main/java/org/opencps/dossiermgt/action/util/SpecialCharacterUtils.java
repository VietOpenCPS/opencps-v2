package org.opencps.dossiermgt.action.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class SpecialCharacterUtils {

	private static Log _log = LogFactoryUtil.getLog(SpecialCharacterUtils.class);
	
	public static String splitSpecial(String value) {
		String[] charSpecialArr = new String[]{
				StringPool.PLUS,
				StringPool.DASH,
				StringPool.EQUAL,
				StringPool.AMPERSAND + StringPool.AMPERSAND,
				StringPool.PIPE + StringPool.PIPE,
				StringPool.GREATER_THAN,
				StringPool.LESS_THAN,
				StringPool.EXCLAMATION,
				StringPool.OPEN_PARENTHESIS,
				StringPool.CLOSE_PARENTHESIS,
				StringPool.OPEN_CURLY_BRACE,
				StringPool.CLOSE_CURLY_BRACE,
				StringPool.OPEN_BRACKET,
				StringPool.CLOSE_BRACKET,
				StringPool.CARET,
				StringPool.TILDE,
				StringPool.QUESTION,
				StringPool.COLON,
				StringPool.BACK_SLASH,
				StringPool.FORWARD_SLASH,
				StringPool.PERIOD,
				StringPool.COMMA };

		String valueSplit = StringPool.BLANK;
		for (int i = 0; i < charSpecialArr.length; i++) {
			String specialCharacter = charSpecialArr[i];
			if (i==0) {
				if (value.contains(specialCharacter)) {
					valueSplit = value.replaceAll(Pattern.quote(specialCharacter), StringPool.UNDERLINE);
				} else {
					valueSplit = value;
				}
			} else {
				if (value.contains(specialCharacter)) {
					valueSplit = valueSplit.replaceAll(Pattern.quote(specialCharacter), StringPool.UNDERLINE);
				}
			}
	    }
		
		return valueSplit;
	}

	public static List<Object[]> getKeyValues(String formData) {
		List<Object[]> keyValues = null;
		if (Validator.isNotNull(formData)) {
			keyValues = new ArrayList<Object[]>();
	
			keyValues = getKeyValues(formData, keyValues);
	
	//		if (keyValues != null) {
	//			for (Object[] keyValue : keyValues) {
	//				_log.info("=========DELIVERABLE_INDEX_FORM_DATA========:" + keyValue[0] + "_" + keyValue[1]);
	//                document.addKeyword(
	//                    keyValue[0].toString(), keyValue[1].toString());
	//				document.addKeyword(keyValue[0].toString().toLowerCase(),
	//						keyValue[1].toString().toLowerCase());
	//			}
	//		}
		}
		return keyValues;
	}
	
	private static List<Object[]> getKeyValues(String formData, List<Object[]> keyValues) {

		try {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject(formData);
			parseJSONObject(keyValues, jsonObject);
		} catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
			_log.info("Can not parse json object from FormData: =>"
					+ " : Cause " + e.getCause());
		}

		return keyValues;

	}

	/**
	 * @param keyValues
	 * @param JSONObject
	 * @return
	 * @throws JSONException
	 */
	private static List<Object[]> parseJSONObject(List<Object[]> keyValues, JSONObject json) {

		List<Object[]> objects = new ArrayList<Object[]>();
		if (json != null) {
			Iterator<String> itr = json.keys();
			while (itr.hasNext()) {
				String key = itr.next();
				String strObject = String.valueOf(json.get(key));
				// check json
				try {
					JSONObject valueObject = JSONFactoryUtil.createJSONObject(strObject);
					Object[] keyValue = new Object[2];
					keyValue[0] = key;
					if (Validator.isNotNull(valueObject.toString())) {
						keyValue[1] = SpecialCharacterUtils.splitSpecial(valueObject.toString());
					} else {
						keyValue[1] = valueObject.toString();
					}
					keyValues.add(keyValue);
					parseJSONObjectIndex(keyValues, json.getJSONObject(key), key);
				} catch (JSONException e) {
					_log.debug(e);
					//_log.error(e);
					// string
					Object[] keyValue = new Object[2];
					keyValue[0] = key;
					if (Validator.isNotNull(strObject.toString())) {
						keyValue[1] = SpecialCharacterUtils.splitSpecial(strObject.toString());
					} else {
						keyValue[1] = strObject.toString();
					}
					keyValues.add(keyValue);
				}
			}
		}

		return objects;
	}

	//
	private static List<Object[]> parseJSONObjectIndex(List<Object[]> keyValues, JSONObject json, String keyJson) {

		List<Object[]> objects = new ArrayList<Object[]>();

		if (json != null) {
			Iterator<String> itr = json.keys();
			while (itr.hasNext()) {
				String key = itr.next();
				String strObject = String.valueOf(json.get(key));
				// check json
				try {
					JSONObject valueObject = JSONFactoryUtil.createJSONObject(strObject);
					Object[] keyValue = new Object[2];
					keyValue[0] = keyJson + StringPool.AT + key;
					if (Validator.isNotNull(valueObject.toString())) {
						keyValue[1] = SpecialCharacterUtils.splitSpecial(valueObject.toString());
					} else {
						keyValue[1] = valueObject.toString();
					}
					keyValues.add(keyValue);
					parseJSONObjectIndex(keyValues, json.getJSONObject(key), keyValue[0].toString());
				} catch (JSONException e) {
					_log.debug(e);
					//_log.error(e);
					// string
					Object[] keyValue = new Object[2];
					keyValue[0] = keyJson + StringPool.AT + key;
					if (Validator.isNotNull(strObject.toString())) {
						keyValue[1] = SpecialCharacterUtils.splitSpecial(strObject.toString());
					} else {
						keyValue[1] = strObject.toString();
					}
					keyValues.add(keyValue);
				}
			}
		}

		return objects;
	}

}
