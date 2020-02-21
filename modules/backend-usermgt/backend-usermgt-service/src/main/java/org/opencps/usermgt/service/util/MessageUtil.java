package org.opencps.usermgt.service.util;

import com.liferay.portal.kernel.language.LanguageUtil;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageUtil {
	public static String getMessage(String key) {
		return LanguageUtil.get(ResourceBundle.getBundle(ConstantUtils.CONTENT_LANGUAGE, Locale.getDefault()), key);		
	}
}
