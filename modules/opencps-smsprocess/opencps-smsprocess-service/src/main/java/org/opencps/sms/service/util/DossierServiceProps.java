package org.opencps.sms.service.util;

import java.util.Properties;

import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.configuration.ConfigurationFactoryUtil;
import com.liferay.portal.kernel.configuration.Filter;

import aQute.bnd.annotation.ProviderType;

@ProviderType
public class DossierServiceProps {
	public static void addProperties(Properties properties) {
		_instance._configuration.addProperties(properties);
	}

	public static boolean contains(String key) {
		return _instance._configuration.contains(key);
	}

	public static String get(String key) {
		return _instance._configuration.get(key);
	}

	public static String get(String key, Filter filter) {
		return _instance._configuration.get(key, filter);
	}

	public static String[] getArray(String key) {
		return _instance._configuration.getArray(key);
	}

	public static String[] getArray(String key, Filter filter) {
		return _instance._configuration.getArray(key, filter);
	}

	public static Properties getProperties() {
		return _instance._configuration.getProperties();
	}

	public static void removeProperties(Properties properties) {
		_instance._configuration.removeProperties(properties);
	}

	public static void set(String key, String value) {
		_instance._configuration.set(key, value);
	}

	private DossierServiceProps() {
		_configuration = ConfigurationFactoryUtil.getConfiguration(getClass().getClassLoader(), "dossierconfig");
	}

	private static DossierServiceProps _instance = new DossierServiceProps();
	private Configuration _configuration;
}
