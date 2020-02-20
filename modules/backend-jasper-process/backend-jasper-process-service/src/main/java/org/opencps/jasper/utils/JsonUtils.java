package org.opencps.jasper.utils;

import backend.jasper.process.service.util.ConfigConstants;

public class JsonUtils {

	public static String quote(String string) {
		if (string == null || string.length() == 0) {
			return ConfigConstants.JASPER_EMPTY_JSON;
		}

		char c = 0;
		int i;
		int len = string.length();
		StringBuilder sb = new StringBuilder(len + 4);
		String t;

		for (i = 0; i < len; i += 1) {
			c = string.charAt(i);
			switch (c) {
			case ConfigConstants.JASPER_BACK_SLASH:
				sb.append(ConfigConstants.JASPER_BACK_SLASH_QUOTE);
				sb.append(ConfigConstants.JASPER_BACK_SLASH_QUOTE);
				break;
			default:
				if (c < ' ') {
					t = ConfigConstants.ZERO + Integer.toHexString(c);
					sb.append(ConfigConstants.UNICODE_PREFIX + t.substring(t.length() - 4));
				} else {
					sb.append(c);
				}
			}
		}
		return sb.toString();
	}

	public static String quoteHTML(String string) {

		if (string == null || string.length() == 0) {
			return ConfigConstants.JASPER_EMPTY_JSON;
		}

		char c = 0;
		int i;
		int len = string.length();
		StringBuilder sb = new StringBuilder(len + 4);
		String t;

		for (i = 0; i < len; i += 1) {
			c = string.charAt(i);
			switch (c) {
			case '\\':
				sb.append(ConfigConstants.REPLACE_KEY);
				break;
			default:
				if (c < ' ') {
					t = ConfigConstants.ZERO + Integer.toHexString(c);
					sb.append(ConfigConstants.UNICODE_PREFIX + t.substring(t.length() - 4));
				} else {
					sb.append(c);
				}
			}
		}
		String result = sb.toString().replaceAll(ConfigConstants.REPLACE_KEYN, ConfigConstants.HTML_NEW_LINE);
		return result;
	}

}
