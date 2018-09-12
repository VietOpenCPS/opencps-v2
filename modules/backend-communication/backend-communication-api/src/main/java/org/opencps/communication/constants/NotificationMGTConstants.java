package org.opencps.communication.constants;

import java.util.HashMap;
import java.util.Map;

import org.opencps.auth.api.keys.NotificationType;

import com.liferay.portal.kernel.util.WebKeys;

import aQute.bnd.osgi.Constants;

public class NotificationMGTConstants {

	private static final Map<String, String> NOTIFICATION_TEMPLATE_INIT = new HashMap<String, String>();

	static {
		
		NOTIFICATION_TEMPLATE_INIT.put(NotificationType.DOSSIER_01, "Y\u00EAu c\u1EA7u b\u1ED5 sung h\u1ED3 s\u01A1");
		
		NOTIFICATION_TEMPLATE_INIT.put(NotificationType.DOSSIER_02, "Y\u00EAu c\u1EA7u thanh to\u00E1n");
		
		NOTIFICATION_TEMPLATE_INIT.put(NotificationType.DOSSIER_03, "Chuy\u1EC3n ti\u1EBFp ch\u1EDD x\u1EED l\u00FD h\u1ED3 s\u01A1");
		
		NOTIFICATION_TEMPLATE_INIT.put(NotificationType.DOSSIER_04, "H\u1ED3 s\u01A1 \u0111\u00E3 \u0111\u01B0\u1EE3c b\u1ED5 sung");
		
		NOTIFICATION_TEMPLATE_INIT.put(NotificationType.DOSSIER_05, "H\u1ED3 s\u01A1 c\u00F3 y\u00EAu c\u1EA7u h\u1EE7y");
		
		NOTIFICATION_TEMPLATE_INIT.put(NotificationType.DOSSIER_06, "H\u1ED3 s\u01A1 c\u00F3 y\u00EAu c\u1EA7u ch\u1EC9nh s\u1EEDa k\u1EBFt qu\u1EA3");
		
		NOTIFICATION_TEMPLATE_INIT.put(NotificationType.DOSSIER_07, "H\u1ED3 s\u01A1 b\u00E1o \u0111\u00E3 \u0111\u01B0\u1EE3c thanh to\u00E1n");
		
		NOTIFICATION_TEMPLATE_INIT.put(NotificationType.DOSSIER_08, "X\u00E1c nh\u1EADn h\u1ED3 \u0111\u00E3 \u0111\u01B0\u1EE3c thanh to\u00E1n");
		
		NOTIFICATION_TEMPLATE_INIT.put(NotificationType.DOSSIER_09, "C\u1EA3nh b\u00E1o qu\u00E1 h\u1EA1n x\u1EED l\u00FD h\u1ED3 s\u01A1");
		
		NOTIFICATION_TEMPLATE_INIT.put(NotificationType.DOSSIER_10, "H\u1ED3 s\u01A1 \u0111\u00E3 c\u00F3 k\u1EBFt qu\u1EA3 x\u1EED l\u00FD");
		
		NOTIFICATION_TEMPLATE_INIT.put(NotificationType.APPLICANT_01, "Th\u00F4ng b\u00E1o m\u1EDF m\u1EDBi t\u00E0i kho\u1EA3n ng\u01B0\u1EDDi l\u00E0m th\u1EE7 t\u1EE5c");
		
		NOTIFICATION_TEMPLATE_INIT.put(NotificationType.APPLICANT_02, "Th\u00F4ng b\u00E1o \u0111\u00E3 ti\u1EBFp nh\u1EADn h\u1ED3 s\u01A1 t\u1EA1i b\u1ED9 ph\u1EADn m\u1ED9t c\u1EEDa \u0111i\u1EC7n t\u1EED");
		
		NOTIFICATION_TEMPLATE_INIT.put(NotificationType.APPLICANT_03, "Th\u00F4ng b\u00E1o y\u00EAu c\u1EA7u ch\u1EC9nh s\u1EEDa h\u1ED3 s\u01A1 t\u1EA1i b\u1ED9 ph\u1EADn m\u1ED9t c\u1EEDa");
		
		NOTIFICATION_TEMPLATE_INIT.put(NotificationType.APPLICANT_04, "Th\u00F4ng b\u00E1o l\u1EA5y k\u1EBFt qu\u1EA3 h\u1ED3 s\u01A1 t\u1EA1i b\u1ED9 ph\u1EADn m\u1ED9t c\u1EEDa");
		
		NOTIFICATION_TEMPLATE_INIT.put(NotificationType.APPLICANT_05, "Th\u00F4ng b\u00E1o \u0111\u00E3 ho\u00E0n th\u00E0nh tr\u1EA3 k\u1EBFt qu\u1EA3 h\u1ED3 s\u01A1");
		
		NOTIFICATION_TEMPLATE_INIT.put(NotificationType.REGISTRATION_01, "C\u00F3 \u0111\u0103ng k\u00ED h\u1ED3 s\u01A1 th\u01B0\u01A1ng nh\u00E2n m\u1EDBi");
		
		NOTIFICATION_TEMPLATE_INIT.put(NotificationType.REGISTRATION_02, "Thay \u0111\u1ED5i th\u00F4ng tin \u0111\u0103ng k\u00ED h\u1ED3 s\u01A1 th\u01B0\u01A1ng nh\u00E2n");
		
	}

	public static String getNotificationTemp(String key) {
		return NOTIFICATION_TEMPLATE_INIT.get(key);
	}

	public static Map<String, String> getNotificationTempMap() {
		Map<String, String> initNotificationTemp = new HashMap<String, String>();
		if (NOTIFICATION_TEMPLATE_INIT != null && NOTIFICATION_TEMPLATE_INIT.size() > 0) {
			for (Map.Entry<String, String> entry: NOTIFICATION_TEMPLATE_INIT.entrySet()) {
				initNotificationTemp.put(entry.getKey(), entry.getValue());
			}
		}
		return initNotificationTemp;
	}

}
