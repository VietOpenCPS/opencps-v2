package org.opencps.dossiermgt.action.util;

import java.util.UUID;

import org.opencps.auth.utils.UserUtils;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.usermgt.service.util.OCPSUserUtils;

import com.liferay.portal.kernel.util.PwdGenerator;
import com.liferay.portal.kernel.util.StringPool;

public class DossierNumberGenerator {

	public static String generateReferenceUID(long groupId) {
		// TODO add more logic here for the generate by pattern

		return UUID.randomUUID().toString();
	}

	public static String generateDossierNumber(String pattern, long groupId) {
		// TODO add more logic here for the generate by pattern

		return UUID.randomUUID().toString();
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
