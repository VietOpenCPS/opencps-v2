package org.opencps.dossiermgt.action.util;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;

import com.liferay.portal.kernel.search.ParseException;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.PwdGenerator;
import com.liferay.portal.kernel.util.StringPool;

public class DossierNumberGenerator {

	public static String generateReferenceUID(long groupId) {
		// TODO add more logic here for the generate by pattern

		return UUID.randomUUID().toString();
	}

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

	public static void main(String[] args) {
		String noPattern = "{nnnnnnnnnnn}/{yyyy}/{$TEXT1 [$var@fileTemplateNo$]}/{$TEXT2 [$var@fileTemplateNo$]}-{$TEXT3 [$var@fileTemplateNo$]}";

		// String pattern = "\\[\\$(.*?)\\$\\]";

		String codePattern = "\\{(n+)\\}";
		String yearPattern = "\\{(y+)\\}";
		
		String variablePattern = "\\{\\$(.*?)\\}";
		
		String defaultValuePattern = "(.*?)\\s";

		String[] patterns = new String[] { codePattern, yearPattern, variablePattern };

		for (String pattern : patterns) {
			
			Pattern r = Pattern.compile(pattern);

			Matcher m = r.matcher(noPattern);

			int count = 0;

			while (m.find()) {
				String tmp = m.group(1);
				if (r.toString().equals(codePattern)) {
					String number = String.valueOf(100);
					// System.out.println(m.group(1).);
					
					tmp = tmp.replaceAll(tmp.charAt(0) + StringPool.BLANK, String.valueOf(0));
					if (number.length() < tmp.length()) {
						number = tmp.substring(0, tmp.length() - number.length()).concat(number);
					}

					System.out.println(number);

				} else if (r.toString().equals(yearPattern)) {
					String year = String.valueOf(2017);
					tmp = tmp.replaceAll(tmp.charAt(0) + StringPool.BLANK, String.valueOf(0));
					
					if(year.length() < tmp.length()){
						year = tmp.substring(0, tmp.length() - year.length()).concat(year);
					}else if(year.length() > tmp.length()){
						year = year.substring(year.length() - tmp.length(), year.length());
					}
						
					System.out.println(year);
					
				} else if (r.toString().equals(variablePattern)) {
					Pattern r1 = Pattern.compile(defaultValuePattern);
					System.out.println(tmp);
					Matcher m1 = r1.matcher(tmp);
					
					System.out.println(m1.group());
				}

				noPattern = noPattern.replace(m.group(0), "$variable" + count + "$");
				//System.out.println(m.group(1));
				// patternContentMaps.put("$variable" + count + "$",
				// m.group(1));
				m = r.matcher(noPattern);
				count++;
			}
		}

	}
}
