package org.opencps.dossiermgt.action.util;

import java.util.regex.Pattern;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;

public class SpecialCharacterUtils {

//	private static Log _log = LogFactoryUtil.getLog(SpecialCharacterUtils.class);
	
	public static String splitSpecial(String value) {
		String[] charSpecialArr = new String[]{"+", "-", "=", "&&", "||", ">", "<", "!", "(", ")", "{", "}", "[", "]", "^", "~", "?", ":","\\", "/"};
		String valueSplit = StringPool.BLANK;
		for (int i = 0; i < charSpecialArr.length; i++) {
			String specialCharacter = charSpecialArr[i];
			if (i==0) {
				if (value.contains(specialCharacter)) {
					valueSplit = value.replaceAll(Pattern.quote(specialCharacter), StringPool.UNDERLINE);
				} else {
					valueSplit = value;
				}
			} else {
				if (value.contains(specialCharacter)) {
					valueSplit = valueSplit.replaceAll(Pattern.quote(specialCharacter), StringPool.UNDERLINE);
				}
			}
	    }
		
		return valueSplit;
	}
}
