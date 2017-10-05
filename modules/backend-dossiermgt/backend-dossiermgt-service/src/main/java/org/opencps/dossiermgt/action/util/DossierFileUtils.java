package org.opencps.dossiermgt.action.util;

import com.liferay.portal.kernel.util.PwdGenerator;
import com.liferay.portal.kernel.util.StringPool;

public class DossierFileUtils {
	public static int counterDossier(long groupId, long userId) {
		int counter = 0;
		
		//TODO add implement for counterDossier
		
		return counter;
	}
	
	public static String generatePassword(String pattern, int length) {
		String password = StringPool.BLANK;
		
		//TODO add more logic here if that is necessary
		password = PwdGenerator.getPassword(pattern, length);
		
		return password;
	}
}
