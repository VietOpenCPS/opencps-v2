package org.opencps.backend.systemlogmgt.service.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author trungnt
 *
 */
public class ParamUtil {

	public static String[] getArrayParams(String params) {

		if (Validator.isNull(params)){
			return null;
		}
		params = params.replaceAll("\\s+", StringPool.BLANK);

		return StringUtil.split(params);
	}

	public static String[] getArrayParams(String params, String delimiter) {
		if (Validator.isNull(params)){
			return null;
		}
		params = params.replaceAll("\\s+", StringPool.BLANK);

		return StringUtil.split(params, delimiter);
	}

	public static int[] getArrayParams(String params, int defaultValue) {
		if (Validator.isNull(params)){
			return null;
		}

		params = params.replaceAll("\\s+", StringPool.BLANK);

		return StringUtil.split(params, defaultValue);
	}

	public static int[] getArrayParams(String params, String delimiter, int defaultValue) {
		if (Validator.isNull(params)){
			return null;
		}

		params = params.replaceAll("\\s+", StringPool.BLANK);

		return StringUtil.split(params, delimiter, defaultValue);
	}

	public static String generalTextParam(String param) {
		if (Validator.isNull(param)){
			return null;
		}

		param = param.replaceAll("\\s+", StringPool.BLANK);

		return StringPool.APOSTROPHE + param + StringPool.APOSTROPHE;
	}

	public static String generalTextParam(String[] params) {
		if (Validator.isNull(params)){
			return StringPool.BLANK;
		}

		return StringPool.APOSTROPHE + StringUtil.merge(params, "','") + StringPool.APOSTROPHE;
	}
	public static String generalTextParam(int[] params) {
		if (Validator.isNull(params)){
			return StringPool.BLANK;
		}
		return StringPool.APOSTROPHE + StringUtil.merge(params, "','") + StringPool.APOSTROPHE;
	}
	public static long[] getArrayLongParams(String params) {

		if (Validator.isNull(params)){
			return null;
		}
		params = params.replaceAll("\\s+", StringPool.BLANK);

		String[] arrayString = StringUtil.split(params);
		long[] result = new long[arrayString.length];
		for(int i=0;i<arrayString.length; i++) {
			result[i] = Long.parseLong(arrayString[i]);
		}
		return result;
	}
	public static JSONObject getValuesByColumns(JSONObject input) {
		JSONObject output = new JSONFactoryUtil().createJSONObject();
		output.put("uuid", input.get("uuid").toString());
		output.put("logId", input.get("logId").toString());
		output.put("groupId", input.get("groupId").toString());
		output.put("createDate", DatetimeUtil.convertTimestampToStringDatetime(input.getLong("createDate"), DatetimeUtil._YYYY_MM_DD));
		output.put("moduleName", input.get("moduleName").toString());
		output.put("preLine", input.get("preLine").toString());
		output.put("preMethod", input.get("preMethod").toString());
		output.put("line", input.get("line").toString());
		output.put("method", input.get("method").toString());
		output.put("message", input.get("message").toString());
		output.put("type", input.get("type").toString());
		output.put("threadId", input.get("threadId").toString());
		return output;
	}
	public static JSONArray getValuesByColumns(JSONArray input) {
		JSONArray output = new JSONFactoryUtil().createJSONArray();
		for (int i = 0; i < input.length(); i++) {
			JSONObject object = input.getJSONObject(i);
			JSONObject objectOuput = new JSONFactoryUtil().createJSONObject();
			objectOuput.put("uuid", object.get("uuid").toString());
			objectOuput.put("logId", object.get("logId").toString());
			objectOuput.put("groupId", object.get("groupId").toString());
			objectOuput.put("createDate", DatetimeUtil.convertTimestampToStringDatetime(object.getLong("createDate"), DatetimeUtil._YYYY_MM_DD));
			objectOuput.put("moduleName", object.get("moduleName").toString());
			objectOuput.put("preLine", object.get("preLine").toString());
			objectOuput.put("preMethod", object.get("preMethod").toString());
			objectOuput.put("line", object.get("line").toString());
			objectOuput.put("method", object.get("method").toString());
			objectOuput.put("message", object.get("message").toString());
			objectOuput.put("type", object.get("type").toString());
			objectOuput.put("threadId", object.get("threadId").toString());
			output.put(objectOuput);
		}
		return output;
	}
}
