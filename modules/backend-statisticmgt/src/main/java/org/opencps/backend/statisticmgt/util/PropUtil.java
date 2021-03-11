package org.opencps.backend.statisticmgt.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author trungnt
 *
 */
public class PropUtil {
	private static Log _log = LogFactoryUtil.getLog(PropUtil.class);

	public static Properties _queryProperties = null;

	public static void loadProperties() {
		InputStream is = null;
		try {

			is = PropUtil.class.getClassLoader().getResourceAsStream("query.properties");
			_queryProperties = System.getProperties();
			_queryProperties.load(is);

		} catch (Exception e) {
			_log.error(e);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					_log.error(e);
				}
			}
		}
	}

	public static String getProperty(String key) {
		if (_queryProperties == null) {
			loadProperties();
		}

		return _queryProperties.getProperty(key);
	}

	public static Properties getQueryproperties() {
		return _queryProperties;
	}

}
