package org.opencps.dossiermgt.action.util;

import com.liferay.counter.kernel.model.Counter;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.opencps.datamgt.utils.DateTimeUtils;
import org.opencps.dossiermgt.model.ConfigCounter;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.service.ConfigCounterLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessOptionLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.comparator.DossierFileComparator;

public class ConfigCounterNumberGenerator {

	private static Log _log = LogFactoryUtil.getLog(ConfigCounterNumberGenerator.class.getName());

	private static final String CONSTANT_ICREMENT = "opencps.configcounter#";
	public static String generateReferenceUID(long groupId) {

		return UUID.randomUUID().toString();
	}

	private static final String CODE_PATTERN_GOV = "\\{(a+|A+)\\}";
	private static final String CODE_PATTERN_DATE = "\\{(n+|N+)\\}";
	private static final String CODE_PATTERN_MONTH = "\\{(p+|P+)\\}";
	private static final String CODE_PATTERN_YEAR = "\\{(q+|Q+)\\}";
	private static final String CODE_PATTERN_SERVICE = "\\{(r+|R+)\\}";
	private static final String DAY_PATTERN = "\\{(d{2}|D{2})\\}";
	private static final String MONTH_PATTERN = "\\{(m{2}|M{2})\\}";
	private static final String YEAR_PATTERN = "\\{(y+|Y+)\\}";
	private static final String DYNAMIC_VARIABLE_PATTERN = "\\{\\$(.*?)\\}";
	private static final String DEFAULT_VALUE_PATTERN = "^([A-Z]|[a-z])+\\d*\\s";
	private static final String EXTRACT_VALUE_PATTERN = "\\[\\$(.*?)\\$\\]";
	private static final String DATE_TIME_PATTERN = "\\{([D|d]{2}[-\\/]{1}[M|m]{2}[-|\\/]{1}[Y|y]{4})\\}";
	private static final String OK = "OK";
	
	public static String generateCounterNumber(long groupId, long companyId, long dossierId, long processOtionId,
			String seriNumberPattern, ConfigCounter configCounter, LinkedHashMap<String, Object> params, SearchContext... searchContext)
			throws ParseException, SearchException {

		Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
		String dossierNumber = StringPool.BLANK;
//		_log.info("seriNumberPattern: "+seriNumberPattern);
		if (dossier != null) {
			String codePatternGov = CODE_PATTERN_GOV;
			String codePatternDate = CODE_PATTERN_DATE;
			String codePatternMonth = CODE_PATTERN_MONTH;
			String codePatternYear = CODE_PATTERN_YEAR;
			String codePatternService = CODE_PATTERN_SERVICE;
			String dayPattern = DAY_PATTERN;
			String monthPattern = MONTH_PATTERN;
			String yearPattern = YEAR_PATTERN;
			String dynamicVariablePattern = DYNAMIC_VARIABLE_PATTERN;
			String defaultValuePattern = DEFAULT_VALUE_PATTERN;
			String extractValuePattern = EXTRACT_VALUE_PATTERN;
			String datetimePattern = DATE_TIME_PATTERN;
			String[] patterns = new String[] { codePatternDate, codePatternMonth, codePatternYear, codePatternService,
					codePatternGov, dayPattern, monthPattern, yearPattern, dynamicVariablePattern, datetimePattern };

			Date now = new Date();

			String day = String.valueOf(DateTimeUtils.getDayFromDate(now));
			//LamTV_Process month = monthCalendar + 1
			String month = String.valueOf(DateTimeUtils.getMonthFromDate(now) + 1);
			String year = String.valueOf(DateTimeUtils.getYearFromDate(now));

			//Process Pattern
			//String serviceProcessCode = StringPool.BLANK;
			String govAgencyCode = StringPool.BLANK;
			try {
				ProcessOption processOption = ProcessOptionLocalServiceUtil.getProcessOption(processOtionId);
				
				//ServiceProcess serviceProcess = ServiceProcessLocalServiceUtil.getServiceProcess(processOption.getServiceProcessId());
				//serviceProcessCode = serviceProcess.getProcessNo();
				
				ServiceConfig serviceConfig = ServiceConfigLocalServiceUtil.fetchServiceConfig(processOption.getServiceConfigId());
				if (serviceConfig != null) {
					govAgencyCode = serviceConfig.getGovAgencyCode();
//					_log.info("govAgencyCode: "+govAgencyCode);
				}
//				_log.info("SERVICECODE____"+serviceProcessCode);
				
			} catch (Exception e) {
				_log.debug(e);
				//_log.error(e);
				_log.info("SERVICECODE____ERROR");

			}

			for (String pattern : patterns) {
				Pattern r = Pattern.compile(pattern);

				Matcher m = r.matcher(seriNumberPattern);

				while (m.find()) {
					String tmp = m.group(1);
//					_log.info("tmp11: "+tmp);

					// Pattern follow serviceProcess
					if (r.toString().equals(codePatternDate)) {
						//String key = "opencps.dossier.number.counter#" + processOtionId + "#" + year;
						String key = CONSTANT_ICREMENT + groupId + StringPool.POUND + day + month + year;
						String number = countByNumber(key, tmp, configCounter);

						//String number11 = countByInit(serviceProcessCode, dossierId, tmp, groupId);

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
						String number = countByNumber(key, tmp, configCounter);

						//String number11 = countByInit(serviceProcessCode, dossierId, tmp, groupId);

						_log.debug("//////////////////////////////////////////////////////////// "
								+ "|certNumber= " + number);

						tmp = tmp.replaceAll(tmp.charAt(0) + StringPool.BLANK, String.valueOf(0));

						if (number.length() < tmp.length()) {
							number = tmp.substring(0, tmp.length() - number.length()).concat(number);
						}

						seriNumberPattern = seriNumberPattern.replace(m.group(0), number);

						// Pattern follow GovAgencyCode
					} else if (r.toString().equals(codePatternYear)) {
						//String key = "opencps.dossier.number.counter#" + processOtionId + "#" + year;
						String key = CONSTANT_ICREMENT + groupId + StringPool.POUND + year;
						String number = countByNumber(key, tmp, configCounter);

						//String number11 = countByInit(serviceProcessCode, dossierId, tmp, groupId);

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
						String key = CONSTANT_ICREMENT + groupId + StringPool.POUND + dossier.getServiceCode();
						String number = countByNumber(key, tmp, configCounter);

						//String number11 = countByInit(serviceProcessCode, dossierId, tmp, groupId);

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
						String key = CONSTANT_ICREMENT + groupId + StringPool.POUND + (Validator.isNotNull(govAgencyCode)
								? govAgencyCode
								: dossier.getGovAgencyCode());
						String number = countByNumber(key, tmp, configCounter);

						//String number = countByInit(govAgencyCode, dossierId, tmp, groupId);

						_log.debug("//////////////////////////////////////////////////////////// " + number
								+ "|processOtionId= " + number);

						tmp = tmp.replaceAll(tmp.charAt(0) + StringPool.BLANK, String.valueOf(0));

						if (number.length() < tmp.length()) {
							number = tmp.substring(0, tmp.length() - number.length()).concat(number);
						}

						seriNumberPattern = seriNumberPattern.replace(m.group(0), number);

					} else if (r.toString().equals(datetimePattern)) {
//						System.out.println(tmp);

						seriNumberPattern = seriNumberPattern.replace(m.group(0), OK);

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

					} else if (r.toString().equals(dynamicVariablePattern)) {
						Pattern r1 = Pattern.compile(defaultValuePattern);
						Matcher m1 = r1.matcher(tmp);

						String defaultValue = (m1.find() ? m1.group() : StringPool.BLANK).trim();

						Pattern r2 = Pattern.compile(extractValuePattern);
						Matcher m2 = r2.matcher(tmp);
						String extractContent = (m2.find() ? m2.group(1) : StringPool.BLANK).trim();
						String key;
						String param;
						String value = StringPool.BLANK;
						String[] textSplit = StringUtil.split(extractContent, StringPool.AT);
						if (textSplit == null || textSplit.length < 2) {
							seriNumberPattern = seriNumberPattern.replace(m.group(0), defaultValue);
						} else {
							key = textSplit[0];
							param = textSplit[1];

							DossierFile dossierFile = null;
							try {
								dossierFile = DossierFileLocalServiceUtil.getDossierFileByDID_FTNO_First(dossierId,
										param, false, new DossierFileComparator(false, Field.CREATE_DATE, Date.class));

								String formData = dossierFile.getFormData();

								if (Validator.isNotNull(formData)) {
									JSONObject object = JSONFactoryUtil.createJSONObject(formData);
									if (object.has(key)) {
										value = object.getString(key);
									}
								}

							} catch (Exception e) {
								_log.debug(e);
								//_log.error(e);
//								_log.error("Can not get data from online form! ");

								seriNumberPattern = seriNumberPattern.replace(m.group(0), defaultValue);
							}

							seriNumberPattern = seriNumberPattern.replace(m.group(0), value);
						}

					}

					m = r.matcher(seriNumberPattern);

				}
			}

			dossierNumber = seriNumberPattern;
		}
		return dossierNumber;
	}
	private static final String PERCENT_ZERO = "%0";
	private static final String NUMBER_FORMAT = "d";
	
	private static String countByNumber(String pattern, String tmp, ConfigCounter configCounter) {

		//long counter = CounterLocalServiceUtil.increment(pattern);
		int lengthPatern = Validator.isNotNull(tmp) ? tmp.length() : 0;
		String format = PERCENT_ZERO + lengthPatern + NUMBER_FORMAT;

		long _counterNumber = 0;
		Counter counter = null;
		//Counter counterDetail = null;
		_log.info("pattern" + pattern);
		if (configCounter.getStartCounter() == 0) {
			Counter counterDetail = CounterLocalServiceUtil.fetchCounter(pattern);

			if (Validator.isNotNull(counterDetail)) {
				// create counter config
				_counterNumber = counterDetail.getCurrentId() + 1;
				while (counter == null) {
					counterDetail.setCurrentId(_counterNumber);
					try {
						counter = CounterLocalServiceUtil.updateCounter(counterDetail);
					} catch (Exception e) {
						_counterNumber += 1;
						_log.debug(e);
					}
				}
				// _counterNumber = counterConfig.getCurrentId() + 1;
				// counterConfig.setCurrentId(_counterNumber);
				// CounterLocalServiceUtil.updateCounter(counterConfig);

			} else {
				_log.info("COUTER_CURR_CONFIG_IS_NOT_NULL");
				counterDetail = CounterLocalServiceUtil.createCounter(pattern);
				// increment CurrentCounter
				_counterNumber = counterDetail.getCurrentId() + 1;
				while (counter == null) {
					counterDetail.setCurrentId(_counterNumber);
					try {
						counter = CounterLocalServiceUtil.updateCounter(counterDetail);
					} catch (Exception e) {
						_counterNumber += 1;
						_log.debug(e);
					}
				}
				// counterConfig.setCurrentId(1);
				// _counterNumber = 1;
				// CounterLocalServiceUtil.updateCounter(counterConfig);
			}
		} else {
			_log.info("COUTER_CURR_CONFIG_IS_NOT_NULL");
			Counter counterDetail = CounterLocalServiceUtil.fetchCounter(pattern);
			// increment CurrentCounter
			_counterNumber = configCounter.getStartCounter() + 1;
			while (counter == null) {
				if (counterDetail != null) {
					counterDetail.setCurrentId(_counterNumber);
					try {
						counter = CounterLocalServiceUtil.updateCounter(counterDetail);
						//
						configCounter.setStartCounter(0);
						ConfigCounterLocalServiceUtil.updateConfigCounter(configCounter);
					} catch (Exception e) {
						_counterNumber += 1;
						_log.debug(e);
					}
				} else {
					counterDetail = CounterLocalServiceUtil.createCounter(pattern);
					counterDetail.setCurrentId(_counterNumber);
					try {
						counter = CounterLocalServiceUtil.updateCounter(counterDetail);
						//
						configCounter.setStartCounter(0);
						ConfigCounterLocalServiceUtil.updateConfigCounter(configCounter);
					} catch (Exception e) {
						_counterNumber += 1;
						_log.debug(e);
					}
				}
				
			}
		}

		return String.format(format, _counterNumber); 
	}
}