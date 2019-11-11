package org.opencps.dossiermgt.action.util;

import com.liferay.petra.string.StringPool;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class AccentUtils {

	public static String removeAccent(String textConvert) {

		String temp = Normalizer.normalize(textConvert, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll(StringPool.BLANK).replaceAll("Đ", "D").replaceAll("đ", "d").toLowerCase();
	}

}
