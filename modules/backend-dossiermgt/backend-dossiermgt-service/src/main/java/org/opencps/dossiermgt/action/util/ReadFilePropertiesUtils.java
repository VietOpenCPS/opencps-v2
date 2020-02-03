package org.opencps.dossiermgt.action.util;

import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.configuration.ConfigurationFactoryUtil;


public class ReadFilePropertiesUtils {

	public static String get(String key) {
		return _instance._configuration.get(key);
	}

	private ReadFilePropertiesUtils() {
		_configuration = ConfigurationFactoryUtil.getConfiguration(getClass().getClassLoader(), ConstantUtils.FILE_CONSTANTS);
	}

	private static ReadFilePropertiesUtils _instance = new ReadFilePropertiesUtils();
	private Configuration _configuration;

}
