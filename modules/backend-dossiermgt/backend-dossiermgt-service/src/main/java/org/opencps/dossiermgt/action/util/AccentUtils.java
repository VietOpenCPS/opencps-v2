package org.opencps.dossiermgt.action.util;

import com.liferay.petra.string.StringPool;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class AccentUtils {

	public static String removeAccent(String textConvert) {

		String temp = Normalizer.normalize(textConvert, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll(StringPool.BLANK)
				.replaceAll(ReadFilePropertiesUtils.get(ConstantUtils.MARK_UPCASE_D),
						ReadFilePropertiesUtils.get(ConstantUtils.UPCASE_D))
				.replaceAll(ReadFilePropertiesUtils.get(ConstantUtils.MARK_LOWERCASE_D),
						ReadFilePropertiesUtils.get(ConstantUtils.LOWERCASE_D))
				.toLowerCase();
	}

}
