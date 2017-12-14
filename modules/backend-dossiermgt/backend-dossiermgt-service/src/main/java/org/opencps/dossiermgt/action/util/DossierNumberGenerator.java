package org.opencps.dossiermgt.action.util;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.UUID;

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
		//String processId = String.valueOf(DossierLocalServiceUtil.countLucene(param, sc) + 1)
		String processId = "100";
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
			pattern = pattern.replace(processP,
					processId.substring(processId.length() - processIdPattern.length() - 1));
		} else {
			String p = "%0" + (processIdPattern.length() - processId.length()) + "d%s";
			pattern = pattern.replace(processP,
					String.format(p,0, processId));
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
}
