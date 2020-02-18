package org.opencps.communication.utils;

import com.liferay.portal.kernel.language.LanguageUtil;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageUtil {
	public static final String CONTENT_LANGUAGE = "content/Language";
	
	public static String getMessage(String key) {
		return LanguageUtil.get(ResourceBundle.getBundle(CONTENT_LANGUAGE, Locale.getDefault()), key);		
	}
}