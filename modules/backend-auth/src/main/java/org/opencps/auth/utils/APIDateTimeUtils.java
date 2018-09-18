package org.opencps.auth.utils;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author binhth
 * DateTimeUtils.class
 */
public class APIDateTimeUtils {

	public static final String _TIMESTAMP = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	
	public static final String _NORMAL_PARTTERN = "dd/MM/yyyy HH:mm:ss";

	public static final String _NORMAL_DATE = "dd/MM/yyyy";
	
	public static final String _NSW_PATTERN = "yyyy-MM-dd HH:mm:ss";

	public static final String _LUCENE_PATTERN = "yyyyMMddHHmmss";

	public static String convertNormalDateToLuceneDate(String normal) {
		if (Validator.isNull(normal)) {
			return StringPool.BLANK;
		}
		String[] splitD = normal.split("/");
		if (splitD.length != 3 ||
				splitD[1].length() > 2 ||
				splitD[0].length() > 2) {
			return StringPool.BLANK;
		}
		String year = splitD[2];
		String month = (splitD[1].length() == 1) ? "0" + splitD[1] : splitD[1];
		String day = (splitD[0].length() == 1) ? "0" + splitD[0] : splitD[0];
		
		return year + month + day;
	}
	
	public static String convertDateToString(Date date) {
		return convertDateToString(date, _NORMAL_PARTTERN);
	}

	public static String convertDateToString(Date date, String pattern) {
		DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			pattern);

		if (Validator.isNull(date) || Validator.isNull(pattern)) {
			return StringPool.BLANK;
		}

		dateFormat.setTimeZone(TimeZoneUtil.getTimeZone("Asia/Ho_Chi_Minh"));

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		return dateFormat.format(calendar.getTime());
	}
	
	public static Date convertStringToDate(String source, String pattern) {
		
		if (Validator.isNull(source) || Validator.isNull(pattern)) {
			return null;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		
		try {
			return sdf.parse(source);
		} catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
			return null;
		}
		
	}

	private static Log _log = LogFactoryUtil.getLog(APIDateTimeUtils.class);

}