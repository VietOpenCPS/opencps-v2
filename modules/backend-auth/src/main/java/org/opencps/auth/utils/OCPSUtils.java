package org.opencps.auth.utils;

import java.util.List;

public class OCPSUtils {

	@Deprecated
	public static long[] toLongArray(List<Long> ls) {

		long[] output = new long[ls.size()];

		int i = 0;
		for (Long l : ls) {
			output[i] = l;
			i++;
		}

		return output;
	}

	@Deprecated
	public static String[] toStringArray(List<String> ls) {

		String[] output = new String[ls.size()];

		int i = 0;
		for (String l : ls) {
			output[i] = l;
			i++;
		}

		return output;
	}
}
