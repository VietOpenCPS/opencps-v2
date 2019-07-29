package org.opencps.dossiermgt.action.util;

import com.liferay.counter.kernel.model.Counter;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.PwdGenerator;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.opencps.datamgt.utils.DateTimeUtils;
import org.opencps.dossiermgt.constants.ConstantsUtils;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.ProcessOption;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessOptionLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.comparator.DossierFileComparator;

public class DossierNumberGenerator {

	private static final String CONSTANT_ICREMENT = "opencps.dossier#";
	public static String generateReferenceUID(long groupId) {

		return UUID.randomUUID().toString();
	}

	public static String generateDossierNumber(long groupId, long companyId, long dossierId, long processOtionId,
			String seriNumberPattern, LinkedHashMap<String, Object> params, SearchContext... searchContext)
			throws ParseException, SearchException {

		Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
		String dossierNumber = StringPool.BLANK;
//		_log.info("seriNumberPattern: "+seriNumberPattern);
		if (dossier != null) {
			String codePatternGov = "\\{(a+|A+)\\}";
			String codePatternDate = "\\{(n+|N+)\\}";
			String codePatternMonth = "\\{(p+|P+)\\}";
			String codePatternYear = "\\{(q+|Q+)\\}";
			String codePatternService = "\\{(r+|R+)\\}";
			String dayPattern = "\\{(d{2}|D{2})\\}";
			String monthPattern = "\\{(m{2}|M{2})\\}";
			String yearPattern = "\\{(y+|Y+)\\}";
			String dynamicVariablePattern = "\\{\\$(.*?)\\}";
			String defaultValuePattern = "^([A-Z]|[a-z])+\\d*\\s";
			String extractValuePattern = "\\[\\$(.*?)\\$\\]";
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
						String number = countByNumber(key, tmp);

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
						String number = countByNumber(key, tmp);

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
						String number = countByNumber(key, tmp);

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
						String number = countByNumber(key, tmp);

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

						String number = countByInit(govAgencyCode, dossierId, tmp, groupId);

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
						String[] textSplit = StringUtil.split(extractContent, "@");
						if (textSplit == null || textSplit.length < 2) {
							seriNumberPattern = seriNumberPattern.replace(m.group(0), defaultValue);
						} else {
							key = textSplit[0];
							param = textSplit[1];

							DossierFile dossierFile = null;
							try {
								dossierFile = DossierFileLocalServiceUtil.getDossierFileByDID_FTNO_First(dossierId,
										param, false, new DossierFileComparator(false, "createDate", Date.class));

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

	@Deprecated
	public static String generateDossierNumber(String code, String pattern, long groupId, Long companyId, String agency,
			String service, String template) throws ParseException, SearchException {
		LinkedHashMap<String, Object> param = new LinkedHashMap<String, Object>();
		param.put(DossierTerm.AGENCY, agency);
		param.put(DossierTerm.SERVICE, service);
		param.put(DossierTerm.TEMPLATE, template);
		SearchContext sc = new SearchContext();
		sc.setCompanyId(companyId);

		String[] listPattern = pattern.split("/");
		// Xử lý year
		String year = listPattern[1].substring(1, listPattern[1].length() - 1);
		Calendar calendar = Calendar.getInstance();
		String y = "";
		String processIdPattern = "";
		if (year.length() == 2) {
			y = String.valueOf(calendar.get(Calendar.YEAR)).substring(2);
			// replace year
			pattern = pattern.replace(listPattern[1], y);
		} else {
			y = String.valueOf(calendar.get(Calendar.YEAR));
			// replace year
			pattern = pattern.replace(listPattern[1], y);
		}
		String processId = String.valueOf(DossierLocalServiceUtil.countLucene(param, sc) + 1);
		// String processId = "100";
		String processP = "";
		if ("{code}".equals(listPattern[0])) {
			// replace code
			pattern = pattern.replace(listPattern[0], code);
			processP = listPattern[2];
		} else {
			// replace code
			pattern = pattern.replace(listPattern[2], code);
			processP = listPattern[0];
		}
		processIdPattern = processP.substring(1, processP.length() - 1);
		// replace procsesId
		if (processId.length() == processIdPattern.length()) {
			pattern = pattern.replace(processP, processId);
		} else if (processId.length() > processIdPattern.length()) {
			pattern = pattern.replace(processP, processId.substring(processId.length() - processIdPattern.length()));
		} else {
			String p = "%0" + (processIdPattern.length() - processId.length()) + "d%s";
			pattern = pattern.replace(processP, String.format(p, 0, processId));
		}

		return pattern;
	}

	public static int counterDossier(long userId, long groupId) {
		// TODO add more logic for the case DOSSIER was created in Client by
		// EMPLOYEE
		// Get userType by OCPSUserUtils.getUserTypes(long groupId, long userId)

		return DossierLocalServiceUtil.countByUserId(userId, groupId) + 1;
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

	private static String countByNumber(String pattern, String tmp) {

		long counter = CounterLocalServiceUtil.increment(pattern);
		int lengthPatern = Validator.isNotNull(tmp) ? tmp.length() : 0;
		String format = "%0" + lengthPatern + "d";

		return String.format(format, counter); 
	}

	public static String generatePassword(String pattern, int length) {

		String password = PwdGenerator.getPassword(pattern, length);

		return password;
	}

	public static long countByRegiterBookCode(long groupId, String registerBookCode) {
		
		long _counterNumber = 0;

		try {
			String certConfigName = ConstantsUtils.PRE_FIX_COUNTER + registerBookCode + StringPool.AT + groupId;
			
			_log.info("___certConfigId" + certConfigName);
			Counter counterConfig = CounterLocalServiceUtil.fetchCounter(certConfigName);

			if (Validator.isNotNull(counterConfig)) {
				// create counter config
				_counterNumber = counterConfig.getCurrentId() + 1;
				//
				counterConfig.setCurrentId(_counterNumber);
				CounterLocalServiceUtil.updateCounter(counterConfig);
					
				} else {
					_log.info("COUTER_CURR_CONFIG_IS_NOT_NULL");
					counterConfig = CounterLocalServiceUtil.createCounter(certConfigName);
					//increment CurrentCounter 
					counterConfig.setCurrentId(1);
					_counterNumber = 1;
					CounterLocalServiceUtil.updateCounter(counterConfig);
				}
		} catch (Exception e) {
			_log.debug(e);
		}

		return _counterNumber;

	}

	public static String generateDossierNumber(long groupId, String dossierNoPattern, String serviceCode, String govAgencyCode, String templateNo, String serviceProcessCode) {

		//String dossierNumber = StringPool.BLANK;
		String codePatternGov = "\\{(a+|A+)\\}";
		String codePatternDate = "\\{(n+|N+)\\}";
		String codePatternMonth = "\\{(p+|P+)\\}";
		String codePatternYear = "\\{(q+|Q+)\\}";
		String codePatternService = "\\{(r+|R+)\\}";
		String dayPattern = "\\{(d{2}|D{2})\\}";
		String monthPattern = "\\{(m{2}|M{2})\\}";
		String yearPattern = "\\{(y+|Y+)\\}";
		String dynamicVariablePattern = "\\{\\$(.*?)\\}";
		String datetimePattern = "\\{([D|d]{2}[-\\/]{1}[M|m]{2}[-|\\/]{1}[Y|y]{4})\\}";
		String[] patterns = new String[] { codePatternDate, codePatternMonth, codePatternYear, codePatternService,
				codePatternGov, dayPattern, monthPattern, yearPattern, dynamicVariablePattern, datetimePattern };

		Date now = new Date();

		String day = String.valueOf(DateTimeUtils.getDayFromDate(now));
		// LamTV_Process month = monthCalendar + 1
		String month = String.valueOf(DateTimeUtils.getMonthFromDate(now) + 1);
		String year = String.valueOf(DateTimeUtils.getYearFromDate(now));

		for (String pattern : patterns) {
			Pattern r = Pattern.compile(pattern);

			Matcher m = r.matcher(dossierNoPattern);

			while (m.find()) {
				String tmp = m.group(1);
//					_log.info("tmp11: "+tmp);

				// Pattern follow serviceProcess
				if (r.toString().equals(codePatternDate)) {
					// String key = "opencps.dossier.number.counter#" + processOtionId + "#" + year;
					String key = CONSTANT_ICREMENT + groupId + StringPool.POUND + day + month + year;
					String number = countByNumber(key, tmp);

					// String number11 = countByInit(serviceProcessCode, dossierId, tmp, groupId);

					_log.debug(
							"//////////////////////////////////////////////////////////// " + "|certNumber= " + number);

					tmp = tmp.replaceAll(tmp.charAt(0) + StringPool.BLANK, String.valueOf(0));

					if (number.length() < tmp.length()) {
						number = tmp.substring(0, tmp.length() - number.length()).concat(number);
					}

					dossierNoPattern = dossierNoPattern.replace(m.group(0), number);

					// Pattern follow GovAgencyCode
				} else if (r.toString().equals(codePatternMonth)) {
					// String key = "opencps.dossier.number.counter#" + processOtionId + "#" + year;
					String key = CONSTANT_ICREMENT + groupId + StringPool.POUND + month + year;
					String number = countByNumber(key, tmp);

					// String number11 = countByInit(serviceProcessCode, dossierId, tmp, groupId);

					_log.debug(
							"//////////////////////////////////////////////////////////// " + "|certNumber= " + number);

					tmp = tmp.replaceAll(tmp.charAt(0) + StringPool.BLANK, String.valueOf(0));

					if (number.length() < tmp.length()) {
						number = tmp.substring(0, tmp.length() - number.length()).concat(number);
					}

					dossierNoPattern = dossierNoPattern.replace(m.group(0), number);

					// Pattern follow GovAgencyCode
				} else if (r.toString().equals(codePatternYear)) {
					// String key = "opencps.dossier.number.counter#" + processOtionId + "#" + year;
					String key = CONSTANT_ICREMENT + groupId + StringPool.POUND + year;
					String number = countByNumber(key, tmp);

					// String number11 = countByInit(serviceProcessCode, dossierId, tmp, groupId);

					_log.debug(
							"//////////////////////////////////////////////////////////// " + "|certNumber= " + number);

					tmp = tmp.replaceAll(tmp.charAt(0) + StringPool.BLANK, String.valueOf(0));

					if (number.length() < tmp.length()) {
						number = tmp.substring(0, tmp.length() - number.length()).concat(number);
					}

					dossierNoPattern = dossierNoPattern.replace(m.group(0), number);

					// Pattern follow GovAgencyCode
				} else if (r.toString().equals(codePatternService)) {
					// String key = "opencps.dossier.number.counter#" + processOtionId + "#" + year;
					String key = CONSTANT_ICREMENT + groupId + StringPool.POUND + serviceCode;
					String number = countByNumber(key, tmp);

					// String number11 = countByInit(serviceProcessCode, dossierId, tmp, groupId);

					_log.debug(
							"//////////////////////////////////////////////////////////// " + "|certNumber= " + number);

					tmp = tmp.replaceAll(tmp.charAt(0) + StringPool.BLANK, String.valueOf(0));

					if (number.length() < tmp.length()) {
						number = tmp.substring(0, tmp.length() - number.length()).concat(number);
					}

					dossierNoPattern = dossierNoPattern.replace(m.group(0), number);

					// Pattern follow GovAgencyCode
				} else if (r.toString().equals(codePatternGov)) {
//						_log.info("codePatternGov: "+ true);
					// String key = "opencps.dossier.number.counter#" + processOtionId + "#" + year;

					String number = countByInit(govAgencyCode, 0, tmp, groupId);

					_log.debug("//////////////////////////////////////////////////////////// " + number
							+ "|processOtionId= " + number);

					tmp = tmp.replaceAll(tmp.charAt(0) + StringPool.BLANK, String.valueOf(0));

					if (number.length() < tmp.length()) {
						number = tmp.substring(0, tmp.length() - number.length()).concat(number);
					}

					dossierNoPattern = dossierNoPattern.replace(m.group(0), number);

				} else if (r.toString().equals(datetimePattern)) {
//						System.out.println(tmp);

					dossierNoPattern = dossierNoPattern.replace(m.group(0), "OK");

				} else if (r.toString().equals(dayPattern)) {

					tmp = tmp.replaceAll(tmp.charAt(0) + StringPool.BLANK, String.valueOf(0));

					if (day.length() < tmp.length()) {
						day = tmp.substring(0, tmp.length() - day.length()).concat(day);
					} else if (day.length() > tmp.length()) {
						day = day.substring(day.length() - tmp.length(), day.length());
					}

					dossierNoPattern = dossierNoPattern.replace(m.group(0), day);

				} else if (r.toString().equals(monthPattern)) {

					tmp = tmp.replaceAll(tmp.charAt(0) + StringPool.BLANK, String.valueOf(0));

					if (month.length() < tmp.length()) {
						month = tmp.substring(0, tmp.length() - month.length()).concat(month);
					} else if (month.length() > tmp.length()) {
						month = month.substring(month.length() - tmp.length(), month.length());
					}

					dossierNoPattern = dossierNoPattern.replace(m.group(0), month);

				} else if (r.toString().equals(yearPattern)) {

					tmp = tmp.replaceAll(tmp.charAt(0) + StringPool.BLANK, String.valueOf(0));

					if (year.length() < tmp.length()) {
						year = tmp.substring(0, tmp.length() - year.length()).concat(year);
					} else if (year.length() > tmp.length()) {
						year = year.substring(year.length() - tmp.length(), year.length());
					}

					dossierNoPattern = dossierNoPattern.replace(m.group(0), year);
				} 

				m = r.matcher(dossierNoPattern);

			}
		}

		return dossierNoPattern;
	}
	
	private static Log _log = LogFactoryUtil.getLog(DossierNumberGenerator.class.getName());
}
