package org.opencps.dossiermgt.action.util;

import com.liferay.counter.kernel.model.Counter;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.util.Validator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.opencps.datamgt.utils.DateTimeUtils;
import org.opencps.dossiermgt.model.DeliverableType;
import org.opencps.dossiermgt.service.DeliverableTypeLocalServiceUtil;

public class DeliverableNumberGenerator {

	public static String generateReferenceUID(long groupId) {

		return UUID.randomUUID().toString();
	}
	
	private static final String CODE_PATTERN = "\\{(n+|N+)\\}";
	private static final String DAY_PATTERN = "\\{(d{2}|D{2})\\}";
	private static final String MONTH_PATTERN = "\\{(m{2}|M{2})\\}";
	private static final String YEAR_PATTERN = "\\{(y+|Y+)\\}";
	private static final String DYNAMIC_VARIABLE_PATTERN = "\\{\\$(.*?)\\}";
	private static final String DATETIME_PATTERN = "\\{([D|d]{2}[-\\/]{1}[M|m]{2}[-|\\/]{1}[Y|y]{4})\\}";
	
	public static String generateDeliverableNumber(long groupId, long companyId, long deliverableTypeId)
			throws ParseException {
		DeliverableType deliverableType = DeliverableTypeLocalServiceUtil.fetchDeliverableType(deliverableTypeId);
		String seriNumberPattern = null;		
		
		String deliverableNumber = StringPool.BLANK;

		if (deliverableType != null) {
			seriNumberPattern = deliverableType.getCodePattern();
			String codePattern = CODE_PATTERN;
			String dayPattern = DAY_PATTERN;
			String monthPattern = MONTH_PATTERN;
			String yearPattern = YEAR_PATTERN;
			String dynamicVariablePattern = DYNAMIC_VARIABLE_PATTERN;
			String datetimePattern = DATETIME_PATTERN;
			
			String[] patterns = new String[] { codePattern, dayPattern, monthPattern, yearPattern,
					dynamicVariablePattern, datetimePattern };

			Date now = new Date();

			String day = String.valueOf(DateTimeUtils.getDayFromDate(now));
			String month = String.valueOf(DateTimeUtils.getMonthFromDate(now));
			String year = String.valueOf(DateTimeUtils.getYearFromDate(now));

			for (String pattern : patterns) {
				Pattern r = Pattern.compile(pattern);

				Matcher m = r.matcher(seriNumberPattern);

				while (m.find()) {
					String tmp = m.group(1);

					if (r.toString().equals(codePattern)) {
						String number = countByInit(pattern, deliverableType.getCounter());

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

			deliverableNumber = seriNumberPattern;
		}
		return deliverableNumber;
	}	
	
	private static final String YEAR_DATEFORMAT = "yyyy";
	private static final String COUNTER_NUMBER_FORMAT = "%07d";
	
	private static String countByInit(String pattern, long count) {
		
		String certNumber;

		try {

			long _counterNumber = 0;

			Calendar cal = Calendar.getInstance();

			cal.setTime(new Date());
			
			DateFormat df = new SimpleDateFormat(YEAR_DATEFORMAT);
			
			String curYear = df.format(cal.getTime());

			String certConfigId = PRE_FIX_CERT + pattern + StringPool.AT + curYear;
			
			Counter counterConfig = CounterLocalServiceUtil.fetchCounter(certConfigId);

			if (Validator.isNotNull(counterConfig)) {
				counterConfig.setCurrentId(counterConfig.getCurrentId() + 1);
				CounterLocalServiceUtil.updateCounter(counterConfig);
				
				_counterNumber = counterConfig.getCurrentId();
				
				certNumber = String.format(COUNTER_NUMBER_FORMAT, _counterNumber); 
				
			} else {
				counterConfig = CounterLocalServiceUtil.createCounter(certConfigId);
				
				counterConfig.setCurrentId(count);
				CounterLocalServiceUtil.updateCounter(counterConfig);
				
				_counterNumber = counterConfig.getCurrentId();
				certNumber = String.format(COUNTER_NUMBER_FORMAT, _counterNumber); 
			}


		} catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
			certNumber = "" + count;
		}
		
		return certNumber;

	}	
	
	public static final String PRE_FIX_CERT = "OPENCPS_CERT@";
	private static final Log _log = LogFactoryUtil.getLog(DeliverableNumberGenerator.class);
}
