package org.opencps.dossiermgt.action.util;

import com.liferay.counter.kernel.model.Counter;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.Validator;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.opencps.datamgt.utils.DateTimeUtils;
import org.opencps.dossiermgt.constants.ConstantsUtils;

public class EFormNumberGenerator {

	private static final String CONSTANT_ICREMENT = "opencps.eform#";
	private static Log _log = LogFactoryUtil.getLog(EFormNumberGenerator.class);

	private static final String CODE_PATTERN_GOV = "\\{(a+|A+)\\}";
	private static final String CODE_PATTERN_DATE = "\\{(n+|N+)\\}";
	private static final String CODE_PATTERN_MONTH = "\\{(p+|P+)\\}";
	private static final String CODE_PATTERN_YEAR = "\\{(q+|Q+)\\}";
	private static final String CODE_PATTERN_SERVICE = "\\{(r+|R+)\\}";
	private static final String DAY_PATTERN = "\\{(d{2}|D{2})\\}";
	private static final String MONTH_PATTERN = "\\{(m{2}|M{2})\\}";
	private static final String YEAR_PATTERN = "\\{(y+|Y+)\\}";
	private static final String DYNAMIC_VARIABLE_PATTERN = "\\{\\$(.*?)\\}";
	private static final String DATETIME_PATTERN = "\\{([D|d]{2}[-\\/]{1}[M|m]{2}[-|\\/]{1}[Y|y]{4})\\}";
	
	public static String generateServiceFileNumber(long groupId, long companyId, String serviceCode,
			String govAgencyCode, String seriNumberPattern, SearchContext... searchContext)
			throws ParseException, SearchException {

		//String eFormNo = StringPool.BLANK;
//		_log.info("seriNumberPattern: "+seriNumberPattern);
		String codePatternGov = CODE_PATTERN_GOV;
		String codePatternDate = CODE_PATTERN_DATE;
		String codePatternMonth = CODE_PATTERN_MONTH;
		String codePatternYear = CODE_PATTERN_YEAR;
		String codePatternService = CODE_PATTERN_SERVICE;
		String dayPattern = DAY_PATTERN;
		String monthPattern = MONTH_PATTERN;
		String yearPattern = YEAR_PATTERN;
		String dynamicVariablePattern = DYNAMIC_VARIABLE_PATTERN;
		//String defaultValuePattern = "^([A-Z]|[a-z])+\\d*\\s";
		//String extractValuePattern = "\\[\\$(.*?)\\$\\]";
		String datetimePattern = DATETIME_PATTERN;
		String[] patterns = new String[] { codePatternDate, codePatternMonth, codePatternYear, codePatternService,
				codePatternGov, dayPattern, monthPattern, yearPattern, dynamicVariablePattern, datetimePattern };

		Date now = new Date();

		String day = String.valueOf(DateTimeUtils.getDayFromDate(now));
		//LamTV_Process month = monthCalendar + 1
		String month = String.valueOf(DateTimeUtils.getMonthFromDate(now) + 1);
		String year = String.valueOf(DateTimeUtils.getYearFromDate(now));

		for (String pattern : patterns) {
			Pattern r = Pattern.compile(pattern);

			Matcher m = r.matcher(seriNumberPattern);

			while (m.find()) {
				String tmp = m.group(1);
				String tmp0 = m.group(0);
				_log.info("tmp: "+ tmp);
				_log.info("tmp0: "+ tmp0);
				// Pattern follow serviceProcess
				if (r.toString().equals(codePatternDate)) {
					String key = CONSTANT_ICREMENT + groupId + StringPool.POUND + day + month + year;
					String number = countByNumber(key, tmp);

					_log.debug("//////////////////////////////////////////////////////////// "
							+ "|certNumber= " + number);

					tmp = tmp.replaceAll(tmp.charAt(0) + StringPool.BLANK, String.valueOf(0));

					if (number.length() < tmp.length()) {
						number = tmp.substring(0, tmp.length() - number.length()).concat(number);
					}

					seriNumberPattern = seriNumberPattern.replace(m.group(0), number);

					// Pattern follow GovAgencyCode
				} else if (r.toString().equals(codePatternMonth)) {
					//String key = "opencps.dossier.number.counter#" + processOtionId + "#" + year;
					String key = CONSTANT_ICREMENT + groupId + StringPool.POUND + month + year;
					String number = countByNumber(key, tmp);

					_log.debug("//////////////////////////////////////////////////////////// "
							+ "|certNumber= " + number);

					tmp = tmp.replaceAll(tmp.charAt(0) + StringPool.BLANK, String.valueOf(0));

					if (number.length() < tmp.length()) {
						number = tmp.substring(0, tmp.length() - number.length()).concat(number);
					}

					seriNumberPattern = seriNumberPattern.replace(m.group(0), number);

					// Pattern follow GovAgencyCode
				} else if (r.toString().equals(codePatternYear)) {
					_log.info("codePatternYear: "+ codePatternYear);
					//String key = "opencps.dossier.number.counter#" + processOtionId + "#" + year;
					String key = CONSTANT_ICREMENT + groupId + StringPool.POUND + year;
					String number = countByNumber(key, tmp);

					_log.info("//////////////////////////////////////////////////////////// "
							+ "|certNumber= " + number);

					_log.info("tmp: "+ tmp);
					tmp = tmp.replaceAll(tmp.charAt(0) + StringPool.BLANK, String.valueOf(0));
					_log.info("tmp: "+ tmp);

					_log.info("number.length(): "+ number.length());
					_log.info("tmp.length(): "+ tmp.length());
					if (number.length() < tmp.length()) {
						number = tmp.substring(0, tmp.length() - number.length()).concat(number);
						_log.info("number: "+ number);
					}

					seriNumberPattern = seriNumberPattern.replace(m.group(0), number);
					_log.info("seriNumberPattern: "+ seriNumberPattern);

					// Pattern follow GovAgencyCode
				} else if (r.toString().equals(codePatternService)) {
					//String key = "opencps.dossier.number.counter#" + processOtionId + "#" + year;
					String key = CONSTANT_ICREMENT + groupId + StringPool.POUND + serviceCode;
					String number = countByNumber(key, tmp);

					_log.debug("//////////////////////////////////////////////////////////// "
							+ "|certNumber= " + number);

					tmp = tmp.replaceAll(tmp.charAt(0) + StringPool.BLANK, String.valueOf(0));

					if (number.length() < tmp.length()) {
						number = tmp.substring(0, tmp.length() - number.length()).concat(number);
					}

					seriNumberPattern = seriNumberPattern.replace(m.group(0), number);

					// Pattern follow GovAgencyCode
				} else if (r.toString().equals(codePatternGov)) {
//						_log.info("codePatternGov: "+ true);
					//String key = "opencps.dossier.number.counter#" + processOtionId + "#" + year;
					String key = CONSTANT_ICREMENT + groupId + StringPool.POUND + govAgencyCode;
					String number = countByNumber(key, tmp);

					_log.debug("//////////////////////////////////////////////////////////// " + number
							+ "|processOtionId= " + number);

					tmp = tmp.replaceAll(tmp.charAt(0) + StringPool.BLANK, String.valueOf(0));

					if (number.length() < tmp.length()) {
						number = tmp.substring(0, tmp.length() - number.length()).concat(number);
					}

					seriNumberPattern = seriNumberPattern.replace(m.group(0), number);

				} else if (r.toString().equals(datetimePattern)) {
//						System.out.println(tmp);

					seriNumberPattern = seriNumberPattern.replace(m.group(0), "OK");

				} else if (r.toString().equals(dayPattern)) {

					tmp = tmp.replaceAll(tmp.charAt(0) + StringPool.BLANK, String.valueOf(0));

					if (day.length() < tmp.length()) {
						day = tmp.substring(0, tmp.length() - day.length()).concat(day);
					} else if (day.length() > tmp.length()) {
						day = day.substring(day.length() - tmp.length(), day.length());
					}

					seriNumberPattern = seriNumberPattern.replace(m.group(0), day);

				} else if (r.toString().equals(monthPattern)) {

					tmp = tmp.replaceAll(tmp.charAt(0) + StringPool.BLANK, String.valueOf(0));

					if (month.length() < tmp.length()) {
						month = tmp.substring(0, tmp.length() - month.length()).concat(month);
					} else if (month.length() > tmp.length()) {
						month = month.substring(month.length() - tmp.length(), month.length());
					}

					seriNumberPattern = seriNumberPattern.replace(m.group(0), month);

				} else if (r.toString().equals(yearPattern)) {

					tmp = tmp.replaceAll(tmp.charAt(0) + StringPool.BLANK, String.valueOf(0));

					if (year.length() < tmp.length()) {
						year = tmp.substring(0, tmp.length() - year.length()).concat(year);
					} else if (year.length() > tmp.length()) {
						year = year.substring(year.length() - tmp.length(), year.length());
					}

					seriNumberPattern = seriNumberPattern.replace(m.group(0), year);
				} 

				m = r.matcher(seriNumberPattern);
			}
		}

		return seriNumberPattern;
	}

	private static final String COUNTER_NUMBER_FORMAT = "%0";
	private static final String COUNTER_D = "d";
	
	private static String countByNumber(String pattern, String tmp) {

		//long counter = CounterLocalServiceUtil.increment(pattern);
		int lengthPatern = Validator.isNotNull(tmp) ? tmp.length() : 0;
		String format = COUNTER_NUMBER_FORMAT + lengthPatern + COUNTER_D;

		long _counterNumber = 0;
		_log.info("pattern" + pattern);
		Counter counterConfig = CounterLocalServiceUtil.fetchCounter(pattern);

		if (Validator.isNotNull(counterConfig)) {
			// create counter config
			_counterNumber = counterConfig.getCurrentId() + 1;
			//
			counterConfig.setCurrentId(_counterNumber);
			CounterLocalServiceUtil.updateCounter(counterConfig);
				
			} else {
				_log.info("COUTER_CURR_CONFIG_IS_NOT_NULL");
				counterConfig = CounterLocalServiceUtil.createCounter(pattern);
				//increment CurrentCounter 
				counterConfig.setCurrentId(1);
				_counterNumber = 1;
				CounterLocalServiceUtil.updateCounter(counterConfig);
			}

		return String.format(format, _counterNumber); 
	}
}
