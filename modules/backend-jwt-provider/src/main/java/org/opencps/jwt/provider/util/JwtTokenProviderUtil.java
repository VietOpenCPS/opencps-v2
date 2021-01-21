package org.opencps.jwt.provider.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.Base64;

import org.opencps.jwt.provider.constant.JwtConstant;

public class JwtTokenProviderUtil {

	private static Log _log = LogFactoryUtil.getLog(JwtTokenProviderUtil.class);

	public static String getAgl(String token) {

		String[] arrays = StringUtil.split(token, StringPool.PERIOD);

		if (arrays != null && arrays.length > 0) {
			String base64EncodedHeader = arrays[0];
			String header = new String(Base64.getDecoder().decode(base64EncodedHeader.getBytes()));

			try {
				JSONObject headerObj = JSONFactoryUtil.createJSONObject(header);
				if (headerObj.has(JwtConstant._AGL)) {
					return headerObj.getString(JwtConstant._AGL);
				}
			} catch (JSONException e) {
				_log.error(e);
			}
		}

		return StringPool.BLANK;
	}

	public static JSONObject getBody(String token) {

		String[] arrays = StringUtil.split(token, StringPool.PERIOD);

		if (arrays != null && arrays.length > 1) {
			String base64EncodedBody = arrays[1];
			String body = new String(Base64.getDecoder().decode(base64EncodedBody.getBytes()));

			try {
				JSONObject bodyObj = JSONFactoryUtil.createJSONObject(body);
				return bodyObj;
			} catch (JSONException e) {
				_log.error(e);
			}
		}

		return null;
	}
}
