package backend.utils;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

/**
 * @author binhth DateTimeUtils.class
 */
public class APIDateTimeUtils {

	public static final String _TIMESTAMP = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	public static final String HCM_TIMEZONE = "Asia/Ho_Chi_Minh";

	public static final String LUCENE_DATE_FORMAT = "yyyyMMddHHmmss";
	public static final String ISO8601 = "yyyy-MM-dd'T'hh:mm:ss'Z'";

	public static String _dateToString(Date date, String format) {

		return new SimpleDateFormat(format).format(date);
	}

	public static Date _stringToDate(String dateStr, String format) {

		try {

			return new SimpleDateFormat(format).parse(dateStr);
		} catch (ParseException e) {

			return null;
		}
	}

	public static String timeZone2Lucene(String date) {
		if (GetterUtil.getLong(date) > 0) {
			return new SimpleDateFormat(LUCENE_DATE_FORMAT).format(new Date(GetterUtil.getLong(date)));
		} else {
			Date d = Date.from(ZonedDateTime.parse(date).toInstant());
			return new SimpleDateFormat(LUCENE_DATE_FORMAT).format(d);
		}
	}

	public static String convertDateToString(Date date, String pattern) {
		DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(pattern);

		if (Validator.isNull(date) || Validator.isNull(pattern)) {
			return StringPool.BLANK;
		}

		dateFormat.setTimeZone(TimeZoneUtil.getTimeZone(HCM_TIMEZONE));

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		return dateFormat.format(calendar.getTime());
	}

//	private static Log _log = LogFactoryUtil.getLog(APIDateTimeUtils.class);

}