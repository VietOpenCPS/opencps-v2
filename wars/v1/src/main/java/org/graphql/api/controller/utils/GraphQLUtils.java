package org.graphql.api.controller.utils;

import com.liferay.petra.string.StringPool;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.graphql.api.controller.impl.DeliverableService;

public class GraphQLUtils {

	public static String readGrapQL(String fileName) {

		String result = StringPool.BLANK;

		InputStream is = DeliverableService.class.getClassLoader().getResourceAsStream(fileName);
		
		try {
			
			result = IOUtils.toString(is, StandardCharsets.UTF_8);
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return result;

	}

}
