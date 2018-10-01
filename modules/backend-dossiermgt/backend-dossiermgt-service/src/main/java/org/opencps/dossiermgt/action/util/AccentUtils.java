package org.opencps.dossiermgt.action.util;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class AccentUtils {

	public static void main(String[] args) {
		System.out.print(removeAccent("Sinh Viên Công Nghệ Thông Tin"));
	}

	public static String removeAccent(String textConvert) {

		String temp = Normalizer.normalize(textConvert, Normalizer.Form.NFD);
		Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
		return pattern.matcher(temp).replaceAll("").replaceAll("Đ", "D").replaceAll("đ", "d").toLowerCase();
	}

}
