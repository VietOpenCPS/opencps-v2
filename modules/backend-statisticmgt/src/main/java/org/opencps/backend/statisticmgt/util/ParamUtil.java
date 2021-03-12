package org.opencps.backend.statisticmgt.util;

import com.liferay.petra.string.StringPool;
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
}
