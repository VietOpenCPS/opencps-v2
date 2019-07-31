package org.graphql.api.controller.utils;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

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

}
