package org.opencps.dossiermgt.action.util;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.opencps.datamgt.utils.DateTimeUtils;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.comparator.DossierFileComparator;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PwdGenerator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

public class DossierNumberGenerator {

	public static String generateReferenceUID(long groupId) {
		// TODO add more logic here for the generate by pattern

		return UUID.randomUUID().toString();
	}

	public static String generateDossierNumber(long groupId, long companyId, long dossierId, String seriNumberPattern,
			LinkedHashMap<String, Object> params, SearchContext ...searchContext) throws ParseException, SearchException {
		Dossier dossier = DossierLocalServiceUtil.fetchDossier(dossierId);

		String dossierNumber = StringPool.BLANK;

		if (dossier != null) {
			String codePattern = "\\{(n+|N+)\\}";
			String dayPattern = "\\{(d{2}|D{2})\\}";
			String monthPattern = "\\{(m{2}|M{2})\\}";
			String yearPattern = "\\{(y+|Y+)\\}";
			String dynamicVariablePattern = "\\{\\$(.*?)\\}";
			String defaultValuePattern = "^([A-Z]|[a-z])+\\d*\\s";
			String extractValuePattern = "\\[\\$(.*?)\\$\\]";
			String datetimePattern = "\\{([D|d]{2}[-\\/]{1}[M|m]{2}[-|\\/]{1}[Y|y]{4})\\}";
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

						// String number =
						// String.valueOf(DossierLocalServiceUtil.countLucene(params,
						// searchContext) + 1);

						String number = String
								.valueOf(DossierLocalServiceUtil.countDossierByG_C_GAC_SC_DTNO_NOTDS(groupId, companyId,
										GetterUtil.getString(params.get(DossierTerm.GOV_AGENCY_CODE)),
										GetterUtil.getString(params.get(DossierTerm.SERVICE_CODE)),
										GetterUtil.getString(params.get(DossierTerm.DOSSIER_TEMPLATE_NO)),
										GetterUtil.getString(params.get(DossierTerm.DOSSIER_STATUS))) + 1);
						
						_log.info("//////////////////////////////////////////////////////////// " + number);

						tmp = tmp.replaceAll(tmp.charAt(0) + StringPool.BLANK, String.valueOf(0));

						if (number.length() < tmp.length()) {
							number = tmp.substring(0, tmp.length() - number.length()).concat(number);
						}

						seriNumberPattern = seriNumberPattern.replace(m.group(0), number);

					} else if (r.toString().equals(datetimePattern)) {
						System.out.println(tmp);

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
						String key = StringPool.BLANK;
						String param = StringPool.BLANK;
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
								_log.error("Can not get data from online form! " + e);

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
		if (listPattern[0].equals("{code}")) {
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

	public static String generatePassword(String pattern, int length) {
		String password = StringPool.BLANK;

		// TODO add more logic here if that is necessary
		password = PwdGenerator.getPassword(pattern, length);

		return password;
	}

	private static Log _log = LogFactoryUtil.getLog(DossierNumberGenerator.class.getName());
}
