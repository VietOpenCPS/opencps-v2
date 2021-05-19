package org.opencps.backend.statisticmgt.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;


import java.io.File;
import java.io.FileInputStream;
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
	public static Properties _configurationProperties = null;
	public static final String QUERY_NAME = "query.properties";
	public static final String CONFIGS_DIR = "/configs/";

	public static void loadQueryProperties() {
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
	
	public static void loadConfigProperties() {
		InputStream is = null;
		try {

			is = PropUtil.class.getClassLoader().getResourceAsStream("configuration.properties");
			_configurationProperties = System.getProperties();
			_configurationProperties.load(is);

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

	public static String getQueryProperty(String key) {
		InputStream is = null;
		try {
			String dir = PropsUtil.get(PropsKeys.LIFERAY_HOME) + CONFIGS_DIR + QUERY_NAME;
			if (_queryProperties == null) {
				File file = new File(dir);
				if (!file.exists()) {
					_queryProperties = System.getProperties();
					loadQueryProperties();
				} else {
					_queryProperties.load(new FileInputStream(dir));
				}
			}
		} catch (Exception e) {
			_log.error(e);
			e.getMessage();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					_log.error(e);
				}
			}
		}

		return _queryProperties.getProperty(key);
	}

	public static Properties getQueryProperties() {
		return _queryProperties;
	}
	
	public static String getConfigProperty(String key) {
		if (_configurationProperties == null) {
			loadConfigProperties();
		}

		return _configurationProperties.getProperty(key);
	}

	public static Properties getConfigProperties() {
		return _configurationProperties;
	}

}
