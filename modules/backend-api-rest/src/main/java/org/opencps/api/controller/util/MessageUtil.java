package org.opencps.api.controller.util;

import com.liferay.portal.kernel.language.LanguageUtil;

import java.util.Locale;
import java.util.ResourceBundle;
import org.opencps.api.constants.ConstantUtils;

public class MessageUtil {
	public static String getMessage(String key) {
		return LanguageUtil.get(ResourceBundle.getBundle(ConstantUtils.CONTENT_LANGUAGE, Locale.getDefault()), key);
	}
}
