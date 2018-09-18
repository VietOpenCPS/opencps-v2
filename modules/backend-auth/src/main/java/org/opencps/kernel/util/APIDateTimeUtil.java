
package org.opencps.kernel.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * @author binhth DateTimeUtils.class
 */
public class APIDateTimeUtil {

	public static final String _TIMESTAMP = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	public static final String _VNDATE = "dd/MM/yyyy";

	public static String convertDateToString(Date date, String pattern) {

		DateFormat dateFormat =
			DateFormatFactoryUtil.getSimpleDateFormat(pattern);

		if (Validator.isNull(date) || Validator.isNull(pattern)) {
			return StringPool.BLANK;
		}

		dateFormat.setTimeZone(TimeZoneUtil.getTimeZone("Asia/Ho_Chi_Minh"));

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);
		//return utc format
		//return dateFormat.format(calendar.getTime());
		
		//return timestamp
		return String.valueOf(date.getTime());
	}

	public static String convertDateToString(
		Document doc, String field, String pattern) {

		String result = StringPool.BLANK;

		if (Validator.isNull(pattern)) {
			pattern = _TIMESTAMP;
		}

		try {
			if (doc != null && Validator.isNotNull(doc.getDate(field))) {
				result = convertDateToString(doc.getDate(field), pattern);
			}
		}
		catch (ParseException e) {
			_log.debug(e);
			//_log.error(e);
			//_log.error("Can't convert lucene date");
		}

		return result;
	}

	private static Log _log = LogFactoryUtil.getLog(APIDateTimeUtil.class);

}
