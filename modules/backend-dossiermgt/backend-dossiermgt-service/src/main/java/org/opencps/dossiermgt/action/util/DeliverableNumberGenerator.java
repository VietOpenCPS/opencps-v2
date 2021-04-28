package org.opencps.dossiermgt.action.util;

import com.liferay.counter.kernel.model.Counter;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
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
import org.opencps.dossiermgt.constants.DeliverableTypesTerm;
import org.opencps.dossiermgt.model.DeliverableType;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.service.DeliverableTypeLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;

public class DeliverableNumberGenerator {
	private static final String CONSTANT_ICREMENT = "opencps.deliverable#";
	public static String generateReferenceUID(long groupId) {

		return UUID.randomUUID().toString();
	}
	
	private static final String CODE_PATTERN = "\\{(n+|N+)\\}";
	private static final String DAY_PATTERN = "\\{(d{2}|D{2})\\}";
	private static final String MONTH_PATTERN = "\\{(m{2}|M{2})\\}";
	private static final String YEAR_PATTERN = "\\{(y+|Y+)\\}";
	private static final String DYNAMIC_VARIABLE_PATTERN = "\\{\\$(.*?)\\}";
	private static final String DATETIME_PATTERN = "\\{([D|d]{2}[-\\/]{1}[M|m]{2}[-|\\/]{1}[Y|y]{4})\\}";
	private static final String PERCENT_ZERO = "%0";
	private static final String FORWARD_SLASH = "/";
	private static final String NUMBER_FORMAT = "d";
	private static final String DOT = "\\.";
	private static final String OK = "OK";
	
	public static String generateDeliverableNumber(long groupId, long companyId, long deliverableTypeId, long dossierId)
			throws ParseException {
		DeliverableType deliverableType = DeliverableTypeLocalServiceUtil.fetchDeliverableType(deliverableTypeId);
		String govAgencyCode = StringPool.BLANK;
		if(Validator.isNotNull(dossierId) && dossierId > 0){
			Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
			govAgencyCode = dossier.getGovAgencyCode();
		}
		String seriNumberPattern = null;		
		
		String deliverableNumber = StringPool.BLANK;
		String code = StringPool.BLANK;
		if (deliverableType != null) {
			try {
				if(Validator.isNotNull(govAgencyCode)){
					_log.info("Vao 11111111: " + deliverableType.getDeliverableTypeId());
					JSONObject mappingPattern = JSONFactoryUtil.createJSONObject(deliverableType.getCodePattern());
					_log.info("Vao 222222222222 : " + JSONFactoryUtil.looseSerialize(deliverableType.getCodePattern()));
					if (mappingPattern
							.has(govAgencyCode)) {
						_log.info("Vao 333333 : " + JSONFactoryUtil.looseSerialize(mappingPattern));
						JSONObject patternGov = mappingPattern.getJSONObject(
								govAgencyCode);
						_log.info("Vao 4444444 : " + JSONFactoryUtil.looseSerialize(patternGov));
						code = patternGov.getString(
								DeliverableTypesTerm.CODEPATTERN);
						_log.info("Vao 55555555 : " + code);
						seriNumberPattern = code;

					}
				}else{
					_log.info("ERRRRRRRRR");
					seriNumberPattern = deliverableType.getCodePattern();
				}
			}catch (Exception e) {
				seriNumberPattern = deliverableType.getCodePattern();
				e.getMessage();
			}

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
					_log.info("tmp : " + tmp);
					String tmpCode = m.group(0);
					_log.debug("tmpCode : " + tmpCode);
					if (r.toString().equals(codePattern)) {
						_log.info("CODEPattern : " + codePattern);

						String number = countByInit(pattern, tmp, deliverableType.getCounter(),govAgencyCode,tmpCode);
						_log.info("Number : " + number);
						
						tmp = tmp.replaceAll(tmp.charAt(0) + StringPool.BLANK, String.valueOf(0));
						_log.info("tmp1 : " + tmp);
						if (number.length() < tmp.length()) {
							number = tmp.substring(0, tmp.length() - number.length()).concat(number);
							_log.info("number1 : " + number);
						}
						seriNumberPattern = seriNumberPattern.replace(m.group(0), number);
						_log.info("seriNumberPattern1 : " + seriNumberPattern);
					} else if (r.toString().equals(datetimePattern)) {

						seriNumberPattern = seriNumberPattern.replace(m.group(0), "OK");
						_log.info("seriNumberPattern2 : " + seriNumberPattern);
						
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
					_log.info("seriNumberPattern6 : " + seriNumberPattern);
				}
			}

			deliverableNumber = seriNumberPattern;
			_log.info("deliverableNumber : " + deliverableNumber);
		}
		return deliverableNumber;
	}
	public static String genDeliverableNumberByGovType(long groupId, String typeCode, String govAgencyCode)
			throws ParseException {
		DeliverableType deliverableType = null;
		deliverableType = DeliverableTypeLocalServiceUtil.fetchByG_DLT(groupId,typeCode);
		if(Validator.isNull(deliverableType)){
			deliverableType = DeliverableTypeLocalServiceUtil.fetchByG_DLT(0L,typeCode);
		}
		String seriNumberPattern = null;

		String deliverableNumber = StringPool.BLANK;
		String code = StringPool.BLANK;
		if (deliverableType != null) {
			try {
				if(Validator.isNotNull(govAgencyCode)){
					_log.info("Vao 11111111: " + deliverableType.getDeliverableTypeId());
					JSONObject mappingPattern = JSONFactoryUtil.createJSONObject(deliverableType.getCodePattern());
					_log.info("Vao 222222222222 : " + JSONFactoryUtil.looseSerialize(deliverableType.getCodePattern()));
					if (mappingPattern
							.has(govAgencyCode)) {
						_log.info("Vao 333333 : " + JSONFactoryUtil.looseSerialize(mappingPattern));
						JSONObject patternGov = mappingPattern.getJSONObject(
								govAgencyCode);
						_log.info("Vao 4444444 : " + JSONFactoryUtil.looseSerialize(patternGov));
						code = patternGov.getString(
								DeliverableTypesTerm.CODEPATTERN);
						_log.info("Vao 55555555 : " + code);
						seriNumberPattern = code;

					}
				}else{
					seriNumberPattern = deliverableType.getCodePattern();
				}
			}catch (Exception e) {
				seriNumberPattern = deliverableType.getCodePattern();
				e.getMessage();
			}

			String codePattern = CODE_PATTERN;
			String dayPattern = DAY_PATTERN;
			String monthPattern = MONTH_PATTERN;
			String yearPattern = YEAR_PATTERN;
			String dynamicVariablePattern = DYNAMIC_VARIABLE_PATTERN;
			String datetimePattern = DATETIME_PATTERN;

			String[] patterns = new String[] { dayPattern, monthPattern, yearPattern,
					dynamicVariablePattern, datetimePattern, codePattern };

			Date now = new Date();

			String day = String.valueOf(DateTimeUtils.getDayFromDate(now));
			String month = String.valueOf(DateTimeUtils.getMonthFromDate(now));
			String year = String.valueOf(DateTimeUtils.getYearFromDate(now));
			String codeNumberPattern = StringPool.BLANK;

			for (String pattern : patterns) {
				Pattern r = Pattern.compile(pattern);

				Matcher m = r.matcher(seriNumberPattern);

				while (m.find()) {
					String tmp = m.group(1);

					 if (r.toString().equals(datetimePattern)) {

						seriNumberPattern = seriNumberPattern.replace(m.group(0), "OK");
						 codeNumberPattern = seriNumberPattern.replace(m.group(0), "OK");
						_log.info("seriNumberPattern2 : " + seriNumberPattern);
						_log.info("codeNumberPattern : " + codeNumberPattern);

					} else if (r.toString().equals(dayPattern)) {

						tmp = tmp.replaceAll(tmp.charAt(0) + StringPool.BLANK, String.valueOf(0));

						if (day.length() < tmp.length()) {
							day = tmp.substring(0, tmp.length() - day.length()).concat(day);
						} else if (day.length() > tmp.length()) {
							day = day.substring(day.length() - tmp.length(), day.length());
						}

						seriNumberPattern = seriNumberPattern.replace(m.group(0), day);
						 codeNumberPattern = seriNumberPattern.replace(m.group(0), day);
						 _log.info("codeNumberPattern : " + codeNumberPattern);

					} else if (r.toString().equals(monthPattern)) {
						tmp = tmp.replaceAll(tmp.charAt(0) + StringPool.BLANK, String.valueOf(0));

						if (month.length() < tmp.length()) {
							month = tmp.substring(0, tmp.length() - month.length()).concat(month);
						} else if (month.length() > tmp.length()) {
							month = month.substring(month.length() - tmp.length(), month.length());
						}

						seriNumberPattern = seriNumberPattern.replace(m.group(0), month);
						 codeNumberPattern = seriNumberPattern.replace(m.group(0), month);
						 _log.info("codeNumberPattern : " + codeNumberPattern);

					} else if (r.toString().equals(yearPattern)) {
						tmp = tmp.replaceAll(tmp.charAt(0) + StringPool.BLANK, String.valueOf(0));

						if (year.length() < tmp.length()) {
							year = tmp.substring(0, tmp.length() - year.length()).concat(year);
						} else if (year.length() > tmp.length()) {
							year = year.substring(year.length() - tmp.length(), year.length());
						}

						seriNumberPattern = seriNumberPattern.replace(m.group(0), year);
						codeNumberPattern = seriNumberPattern.replace(m.group(0), year);
						 _log.info("codeNumberPattern : " + codeNumberPattern);

					}else if (r.toString().equals(codePattern)) {
						_log.info("CODEPattern : " + codePattern);

						 String tmpCode = seriNumberPattern.replaceAll("\\{\\w+}", "");
						 _log.info("tmp : " + tmp);
						String number = countByInit(pattern, tmp, deliverableType.getCounter(),govAgencyCode, tmpCode);
						_log.info("Number : " + number);

						tmp = tmp.replaceAll(tmp.charAt(0) + StringPool.BLANK, String.valueOf(0));
						_log.info("tmp1 : " + tmp);
						if (number.length() < tmp.length()) {
							number = tmp.substring(0, tmp.length() - number.length()).concat(number);
							_log.info("number1 : " + number);
						}
						seriNumberPattern = seriNumberPattern.replace(m.group(0), number);
						_log.info("seriNumberPattern1 : " + seriNumberPattern);
					}
					m = r.matcher(seriNumberPattern);
					_log.info("seriNumberPattern6 : " + seriNumberPattern);
				}
			}

			deliverableNumber = seriNumberPattern;
			_log.info("deliverableNumber : " + deliverableNumber);
		}
		return deliverableNumber;
	}
	public static String generateDeliverableNumber(long groupId, String serialNumberPattern, String ngayQD) {
		try {
		String codePattern = CODE_PATTERN;
		String dayPattern = DAY_PATTERN;
		String monthPattern = MONTH_PATTERN;
		String yearPattern = YEAR_PATTERN;
		String datetimePattern = DATETIME_PATTERN;
		String[] patterns = new String[] {codePattern, dayPattern, monthPattern, yearPattern, datetimePattern };

		String[] ngayquyetdinh = null;
		if (ngayQD.split(FORWARD_SLASH).length > 1) {
			ngayquyetdinh = ngayQD.split(FORWARD_SLASH);
		} else if (ngayQD.split(DOT).length > 1) {
			ngayquyetdinh = ngayQD.split(DOT);
		}
		String day = ngayquyetdinh[0];
		String month = ngayquyetdinh[1];
		String year = ngayquyetdinh[2];
		_log.debug("VAO year " + year);

		for (String pattern : patterns) {
			Pattern r = Pattern.compile(pattern);

			Matcher m = r.matcher(serialNumberPattern);

			while (m.find()) {
				String tmp = m.group(1);

				// Pattern follow serviceProcess
				if (r.toString().equals(codePattern)) {
					// String key = "opencps.deliverable#" + groupId + "#" + day + month + year;
					String key = CONSTANT_ICREMENT + groupId + StringPool.POUND + day + month + year;
					String number = counterByNumber(key, tmp);
					_log.debug(
							"//////////////////////////////////////////////////////////// " + "|certNumber= " + number);
					tmp = tmp.replaceAll(tmp.charAt(0) + StringPool.BLANK, String.valueOf(0));
					if (number.length() < tmp.length()) {
						number = tmp.substring(0, tmp.length() - number.length()).concat(number);
					}

					serialNumberPattern = serialNumberPattern.replace(m.group(0), number);

				} else if (r.toString().equals(datetimePattern)) {
					serialNumberPattern = serialNumberPattern.replace(m.group(0), OK);

				} else if (r.toString().equals(dayPattern)) {
					tmp = tmp.replaceAll(tmp.charAt(0) + StringPool.BLANK, String.valueOf(0));
					if (day.length() < tmp.length()) {
						day = tmp.substring(0, tmp.length() - day.length()).concat(day);
					} else if (day.length() > tmp.length()) {
						day = day.substring(day.length() - tmp.length(), day.length());
					}

					serialNumberPattern = serialNumberPattern.replace(m.group(0), day);

				} else if (r.toString().equals(monthPattern)) {
					tmp = tmp.replaceAll(tmp.charAt(0) + StringPool.BLANK, String.valueOf(0));
					if (month.length() < tmp.length()) {
						month = tmp.substring(0, tmp.length() - month.length()).concat(month);
					} else if (month.length() > tmp.length()) {
						month = month.substring(month.length() - tmp.length(), month.length());
					}

					serialNumberPattern = serialNumberPattern.replace(m.group(0), month);

				} else if (r.toString().equals(yearPattern)) {

					tmp = tmp.replaceAll(tmp.charAt(0) + StringPool.BLANK, String.valueOf(0));
					_log.debug("VAO YEARPATTERN : " + tmp);
					if (year.length() < tmp.length()) {
						_log.debug("VAO YEARPATTERN <<<<<<: " + year );
						year = tmp.substring(0, tmp.length() - year.length()).concat(year);
					} else if (year.length() > tmp.length()) {
						_log.debug("VAO YEARPATTERN >>>>>: " + year );
						year = year.substring(year.length() - tmp.length(), year.length());
					}
					_log.debug("VAO m.GROUP >>>>>: " + m.group(0) );
					serialNumberPattern = serialNumberPattern.replace(m.group(0), year);
				}
				m = r.matcher(serialNumberPattern);
			}
		}
		String deliverableNumber = serialNumberPattern;
		return deliverableNumber;
		}catch (Exception e){
			_log.debug(e);
		}
		return null;
	}

	private static String counterByNumber(String pattern, String tmp) {
		try {
			//long counter = CounterLocalServiceUtil.increment(pattern);
			int lengthPatern = Validator.isNotNull(tmp) ? tmp.length() : 0;
			String format = PERCENT_ZERO + lengthPatern + NUMBER_FORMAT;

			long _counterNumber = 0;
			Counter counter = null;
			_log.debug("pattern" + pattern);
			Counter counterDetail = CounterLocalServiceUtil.fetchCounter(pattern);
			if (Validator.isNotNull(counterDetail)) {
				// create counter config
				_counterNumber = counterDetail.getCurrentId() + 1;
				do {
					counterDetail.setCurrentId(_counterNumber);
					try {
						counter = CounterLocalServiceUtil.updateCounter(counterDetail);
					} catch (Exception e) {
						_counterNumber += 1;
						_log.debug(e);
					}
				} while (counter == null);

			} else {
				_log.info("COUTER_CURR_CONFIG_IS_NOT_NULL");
				counterDetail = CounterLocalServiceUtil.createCounter(pattern);
				// increment CurrentCounter
				_counterNumber = counterDetail.getCurrentId() + 1;
				do {
					counterDetail.setCurrentId(_counterNumber);
					try {
						counter = CounterLocalServiceUtil.updateCounter(counterDetail);
					} catch (Exception e) {
						_counterNumber += 1;
						_log.debug(e);
					}
				} while (counter == null);
			}

			return String.format(format, _counterNumber);
		}catch (Exception e){
			_log.debug(e);
		}
		return null;
	}
	
	private static final String YEAR_DATEFORMAT = "yyyy";
	private static final String DAY_DATEFORMAT = "dd";
	private static final String MONTH_DATEFORMAT = "mm";
	private static final String COUNTER_NUMBER_FORMAT = "%07d";
	
	private static String countByInit(String pattern, String tmp, long count, String govAgencyCode, String tmpCode) {
		
		int lengthPatern = Validator.isNotNull(tmp) ? tmp.length() : 0;
		String format = PERCENT_ZERO + lengthPatern + NUMBER_FORMAT;		
		long _counterNumber = 0;
		try {
			String certConfigId = StringPool.BLANK;
			Calendar cal = Calendar.getInstance();

			cal.setTime(new Date());
			
			DateFormat df = new SimpleDateFormat(YEAR_DATEFORMAT);
			String curYear = df.format(cal.getTime());

			certConfigId = PRE_FIX_CERT_DELIVERABLE + tmpCode + StringPool.AT  + govAgencyCode;

			Counter counter = null;
			Counter counterConfig = CounterLocalServiceUtil.fetchCounter(certConfigId);
			_log.info("counterConfig: " + JSONFactoryUtil.looseSerialize(counterConfig));
			
			if (Validator.isNotNull(counterConfig)) {
				// create counter config
				_counterNumber = counterConfig.getCurrentId() + 1;
				do {
					counterConfig.setCurrentId(_counterNumber);
					try {
						counter = CounterLocalServiceUtil.updateCounter(counterConfig);
					} catch (Exception e) {
						_counterNumber += 1;
						_log.debug(e);
					}
				} while (counter == null);

			} else {
				_log.info("COUTER_CURR_CONFIG_IS_NOT_NULL");
				counterConfig = CounterLocalServiceUtil.createCounter(certConfigId);
				// increment CurrentCounter
				_counterNumber = counterConfig.getCurrentId() + 1;
				do {
					counterConfig.setCurrentId(_counterNumber);
					try {
						counter = CounterLocalServiceUtil.updateCounter(counterConfig);
					} catch (Exception e) {
						_counterNumber += 1;
						_log.debug(e);
					}
				} while (counter == null);
			}


		} catch (Exception e) {
			_log.debug(e);
			//_log.error(e);
			String certNumber = "" + count;
		}
		
		return String.format(format, _counterNumber);

	}	
	
	public static final String PRE_FIX_CERT = "OPENCPS_CERT@";
	public static final String PRE_FIX_CERT_DELIVERABLE = "OPENCPS_DELIVERABLE@";
	private static final Log _log = LogFactoryUtil.getLog(DeliverableNumberGenerator.class);
}
