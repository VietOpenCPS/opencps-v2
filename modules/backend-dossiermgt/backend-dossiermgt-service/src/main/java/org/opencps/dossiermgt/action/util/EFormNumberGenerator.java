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

	public static String generateServiceFileNumber(long groupId, long companyId, String serviceCode,
			String seriNumberPattern, SearchContext... searchContext)
			throws ParseException, SearchException {

		//String eFormNo = StringPool.BLANK;
//		_log.info("seriNumberPattern: "+seriNumberPattern);
		String codePatternGov = "\\{(a+|A+)\\}";
		String codePatternDate = "\\{(n+|N+)\\}";
		String codePatternMonth = "\\{(p+|P+)\\}";
		String codePatternYear = "\\{(q+|Q+)\\}";
		String codePatternService = "\\{(r+|R+)\\}";
		String dayPattern = "\\{(d{2}|D{2})\\}";
		String monthPattern = "\\{(m{2}|M{2})\\}";
		String yearPattern = "\\{(y+|Y+)\\}";
		String dynamicVariablePattern = "\\{\\$(.*?)\\}";
		//String defaultValuePattern = "^([A-Z]|[a-z])+\\d*\\s";
		//String extractValuePattern = "\\[\\$(.*?)\\$\\]";
		String datetimePattern = "\\{([D|d]{2}[-\\/]{1}[M|m]{2}[-|\\/]{1}[Y|y]{4})\\}";
		String[] patterns = new String[] { codePatternDate, codePatternMonth, codePatternYear, codePatternService,
				codePatternGov, dayPattern, monthPattern, yearPattern, dynamicVariablePattern, datetimePattern };

		Date now = new Date();

		String day = String.valueOf(DateTimeUtils.getDayFromDate(now));
		//LamTV_Process month = monthCalendar + 1
		String month = String.valueOf(DateTimeUtils.getMonthFromDate(now) + 1);
		String year = String.valueOf(DateTimeUtils.getYearFromDate(now));

		//Process Pattern
		//String serviceProcessCode = StringPool.BLANK;
		//String govAgencyCode = StringPool.BLANK;
//		try {
//			ProcessOption processOption = ProcessOptionLocalServiceUtil.getProcessOption(processOtionId);
//			
//			ServiceProcess serviceProcess = ServiceProcessLocalServiceUtil.getServiceProcess(processOption.getServiceProcessId());
//			
//			serviceProcessCode = serviceProcess.getProcessNo();
//			
//			ServiceConfig serviceConfig = ServiceConfigLocalServiceUtil.fetchServiceConfig(processOption.getServiceConfigId());
//			if (serviceConfig != null) {
//				govAgencyCode = serviceConfig.getGovAgencyCode();
////					_log.info("govAgencyCode: "+govAgencyCode);
//			}
////				_log.info("SERVICECODE____"+serviceProcessCode);
//			
//		} catch (Exception e) {
//			_log.debug(e);
//			//_log.error(e);
//			_log.info("SERVICECODE____ERROR");
//
//		}

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

					_log.debug("//////////////////////////////////////////////////////////// "
							+ "|certNumber= " + number);

					tmp = tmp.replaceAll(tmp.charAt(0) + StringPool.BLANK, String.valueOf(0));

					if (number.length() < tmp.length()) {
						number = tmp.substring(0, tmp.length() - number.length()).concat(number);
					}

					seriNumberPattern = seriNumberPattern.replace(m.group(0), number);

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
					String key = CONSTANT_ICREMENT + groupId + StringPool.POUND + serviceCode;
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

	private static String countByNumber(String pattern, String tmp) {

		long counter = CounterLocalServiceUtil.increment(pattern);
		int lengthPatern = Validator.isNotNull(tmp) ? tmp.length() : 0;
		String format = "%0" + lengthPatern + "d";

		return String.format(format, counter); 
	}

	private static String countByInit(String pattern, long dossierid, String tmp, long groupId) {
		
		String certNumber = "0";

		try {

			long _counterNumber = 0;

			Calendar cal = Calendar.getInstance();

			cal.setTime(new Date());

			//int curYear = cal.get(Calendar.YEAR);
			
//			DateFormat df = new SimpleDateFormat("yyyy");
//			DateFormat sdf = new SimpleDateFormat("yy");
//			String curYear = df.format(cal.getTime());
//			String shortCurYear = sdf.format(cal.getTime());

			String certConfigId = ConstantsUtils.PRE_FIX_CERT + pattern + StringPool.AT + groupId;
			
			_log.debug("___certConfigId" + certConfigId);

			String certConfigCurrId = ConstantsUtils.PRE_FIX_CERT_CURR + pattern + StringPool.AT + groupId;
			
			_log.debug("___certConfigCurrId" + certConfigCurrId);

			Counter counterConfig = CounterLocalServiceUtil.fetchCounter(certConfigId);

			String elmCertId = ConstantsUtils.PRE_FIX_CERT_ELM + pattern + StringPool.AT + groupId + StringPool.AT + dossierid;

			//Counter counter = CounterLocalServiceUtil.fetchCounter(certId);

			if (Validator.isNotNull(counterConfig)) {
				// create counter config

				Counter currCounter = CounterLocalServiceUtil.fetchCounter(certConfigCurrId);

				if (Validator.isNull(currCounter)) {
					_log.debug("COUTER_CURR_CONFIG_IS_NULL");

					currCounter = CounterLocalServiceUtil.createCounter(certConfigCurrId);

					currCounter.setCurrentId(counterConfig.getCurrentId());

					_counterNumber = counterConfig.getCurrentId() ;

					CounterLocalServiceUtil.updateCounter(currCounter);
					
					//Create elmCounter
					Counter elmCounter = CounterLocalServiceUtil.createCounter(elmCertId);
					
					elmCounter.setCurrentId(_counterNumber);
					
					CounterLocalServiceUtil.updateCounter(elmCounter);
					
				} else {
					_log.debug("COUTER_CURR_CONFIG_IS_NOT_NULL");

					//check counter for element
					Counter elmCounter = CounterLocalServiceUtil.fetchCounter(elmCertId);
					
					if (Validator.isNotNull(elmCounter)) {
						_log.debug("ELM_COUTER_CONFIG_IS_NOT_NULL");

						_counterNumber = elmCounter.getCurrentId();
					} else {
						//create elm Counter 
						_log.debug("ELM_COUTER_CONFIG_IS_NULL");
						elmCounter = CounterLocalServiceUtil.createCounter(elmCertId);
						
						//increment CurrentCounter 
						
						currCounter.setCurrentId(currCounter.getCurrentId()+1);
						CounterLocalServiceUtil.updateCounter(currCounter);
						
						_counterNumber = currCounter.getCurrentId();
						
						elmCounter.setCurrentId(_counterNumber);
						
						CounterLocalServiceUtil.updateCounter(elmCounter);
					}
					
				}

				int lengthPatern = Validator.isNotNull(tmp) ? tmp.length() : 0;
				String format = "%0" + lengthPatern + "d";
				certNumber = String.format(format, _counterNumber); 
				
			}
		} catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
		}

		return certNumber;

	}
}
