package org.graphql.api.controller.utils;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

import org.apache.commons.io.IOUtils;
import org.graphql.api.controller.impl.DeliverableService;

public class GraphQLUtils {

	private static Log _log = LogFactoryUtil.getLog(GraphQLUtils.class);

	public static String readGrapQL(String fileName) {

		String result = StringPool.BLANK;

		InputStream is = DeliverableService.class.getClassLoader().getResourceAsStream(fileName);
		
		try {
			result = IOUtils.toString(is, StandardCharsets.UTF_8);
			
		} catch (IOException e) {
			_log.debug(e);
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				_log.debug(e);
			}
		}

		return result;

	}

	public static String buildDeliverableSearchDataForm (String formDataKey) {

		String result = StringPool.BLANK;
		try {

			if (Validator.isNull(formDataKey)) {

				return result;
			}

			JSONObject formDataKeyObject = JSONFactoryUtil.createJSONObject(formDataKey);

			for (Iterator<String> iii = formDataKeyObject.keys(); iii.hasNext();) {
				
				String key = iii.next();
				result += " AND " + key + "_data: *" + formDataKeyObject.get(key) + "*";
			}
		}
		catch (Exception e) {
			_log.debug(e);
		}
		
		return result;
	}

}
