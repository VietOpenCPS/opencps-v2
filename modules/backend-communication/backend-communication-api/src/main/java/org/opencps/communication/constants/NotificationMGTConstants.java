package org.opencps.communication.constants;

import java.util.HashMap;
import java.util.Map;

import com.liferay.portal.kernel.util.WebKeys;

import aQute.bnd.osgi.Constants;

public class NotificationMGTConstants implements Constants, WebKeys {

	public static final Map<String, String> NOTIFICATION_TEMPLATE_INIT = new HashMap<String, String>();

	static {
		
		NOTIFICATION_TEMPLATE_INIT.put("ACTIVITY-01", "M\u1EDDi theo d\u00F5i ho\u1EA1t \u0111\u1ED9ng \u0111\u01B0\u1EE3c chia s\u1EBB");
		
		NOTIFICATION_TEMPLATE_INIT.put("ACTIVITY-02", "G\u1EEDi gi\u1EA5y m\u1EDDi tham gia m\u1ED9t s\u1EF1 ki\u1EC7n");
		
		NOTIFICATION_TEMPLATE_INIT.put("ACTIVITY-03", "X\u00E1c nh\u1EADn c\u1EE7a ng\u01B0\u1EDDi tham gia ho\u1EA1t \u0111\u1ED9ng");
		
		NOTIFICATION_TEMPLATE_INIT.put("ACTIVITY-04", "C\u1EADp nh\u1EADt th\u1EDDi gian, \u0111\u1ECBa \u0111i\u1EC3m, tr\u1EA1ng th\u00E1i c\u1EE7a ho\u1EA1t \u0111\u1ED9ng");
		
		NOTIFICATION_TEMPLATE_INIT.put("ACTIVITY-05", "Nh\u1EAFc l\u1ECBch ho\u1EA1t \u0111\u1ED9ng s\u1EAFp b\u1EAFt \u0111\u1EA7u ho\u1EB7c k\u1EBFt th\u00FAc");
		
		NOTIFICATION_TEMPLATE_INIT.put("FILEATTACH", "C\u1EADp nh\u1EADt t\u00E0i li\u1EC7u \u0111\u00EDnh k\u00E8m");
		
		NOTIFICATION_TEMPLATE_INIT.put("ALBUMFILE", "C\u1EADp nh\u1EADt \u1EA3nh album");
		
		NOTIFICATION_TEMPLATE_INIT.put("COMMENT", "C\u1EADp nh\u1EADt b\u00ECnh lu\u1EADn m\u1EDBi");
		
		NOTIFICATION_TEMPLATE_INIT.put("VOTING-01", "T\u1EA1o \u00FD ki\u1EBFn \u0111\u00E1nh gi\u00E1 m\u1EDBi");
		
		NOTIFICATION_TEMPLATE_INIT.put("VOTING-02", "Tr\u1EA3 l\u1EDDi c\u00E2u h\u1ECFi \u0111\u00E1nh gi\u00E1");
		
		NOTIFICATION_TEMPLATE_INIT.put("APPROVAL-01", "Tr\u00ECnh ph\u00EA duy\u1EC7t");
		
		NOTIFICATION_TEMPLATE_INIT.put("APPROVAL-02", "\u0110\u00E3 ph\u00EA duy\u1EC7t");
		
		NOTIFICATION_TEMPLATE_INIT.put("DOCUMENT-01", "M\u1EDDi theo d\u00F5i v\u0103n b\u1EA3n \u0111\u01B0\u1EE3c chia s\u1EBB");
		
		NOTIFICATION_TEMPLATE_INIT.put("DOCUMENT-02", "C\u1EADp nh\u1EADt tr\u1EA1ng th\u00E1i v\u0103n b\u1EA3n");
		
		NOTIFICATION_TEMPLATE_INIT.put("DOCUMENT-03", "C\u1EADp nh\u1EADt d\u1EEF li\u1EC7u bi\u1EC3u m\u1EABu \u0111i\u1EC7n t\u1EED");
		
		NOTIFICATION_TEMPLATE_INIT.put("ARCHIVE", "C\u1EADp nh\u1EADt v\u0103n b\u1EA3n h\u1ED3 s\u01A1");
		
		NOTIFICATION_TEMPLATE_INIT.put("CHECKLIST-01", "M\u1EDDi theo d\u00F5i c\u00F4ng vi\u1EC7c c\u00F4ng vi\u1EC7c \u0111\u01B0\u1EE3c chia s\u1EBB");
		
		NOTIFICATION_TEMPLATE_INIT.put("CHECKLIST-02", "Th\u00F4ng b\u00E1o ph\u00E2n c\u00F4ng th\u1EF1c hi\u1EC7n c\u00F4ng vi\u1EC7c");
		
		NOTIFICATION_TEMPLATE_INIT.put("CHECKLIST-03", "C\u1EADp nh\u1EADt tr\u1EA1ng th\u00E1i c\u1EE7a c\u00F4ng vi\u1EC7c");
		
		NOTIFICATION_TEMPLATE_INIT.put("CHECKLIST-04", "Nh\u1EAFc l\u1ECBch c\u00F4ng vi\u1EC7c s\u1EAFp h\u1EBFt h\u1EA1n");
		
		NOTIFICATION_TEMPLATE_INIT.put("KEYRESULT-01", "Ph\u00E2n c\u00F4ng th\u1EF1c hi\u1EC7n k\u1EBFt qu\u1EA3 m\u1EE5c ti\u00EAu");
		
		NOTIFICATION_TEMPLATE_INIT.put("KEYRESULT-02", "C\u1EADp nh\u1EADt k\u1EBFt qu\u1EA3 m\u1EE5c ti\u00EAu");
		
		NOTIFICATION_TEMPLATE_INIT.put("PROJECT-01", "M\u1EDDi theo d\u00F5i d\u1EF1 \u00E1n \u0111\u01B0\u1EE3c chia s\u1EBB");
		
		NOTIFICATION_TEMPLATE_INIT.put("PROJECT-02", "C\u1EADp nh\u1EADt tr\u1EA1ng th\u00E1i d\u1EF1 \u00E1n");
		
		NOTIFICATION_TEMPLATE_INIT.put("PROJECT-03", "C\u1EADp nh\u1EADt tr\u1EA1ng th\u00E1i h\u1EA1ng m\u1EE5c (task) trong k\u1EBF ho\u1EA1ch");
		
		NOTIFICATION_TEMPLATE_INIT.put("PROJECT-04", "Nh\u1EAFc l\u1ECBch h\u1EA1ng m\u1EE5c k\u1EBF ho\u1EA1ch trong k\u1EBF ho\u1EA1ch");
		
		NOTIFICATION_TEMPLATE_INIT.put("USER-01", "Th\u00F4ng b\u00E1o m\u1EDF t\u00E0i kho\u1EA3n m\u1EDBi cho ng\u01B0\u1EDDi d\u00F9ng tr\u00EAn h\u1EC7 th\u1ED1ng");
		
		NOTIFICATION_TEMPLATE_INIT.put("USER-02", "Th\u00F4ng b\u00E1o thay \u0111\u1ED5i tr\u1EA1ng th\u00E1i k\u00EDch ho\u1EA1t t\u00E0i kho\u1EA3n");
		
		NOTIFICATION_TEMPLATE_INIT.put("USER-03", "Th\u00F4ng b\u00E1o confirm reset m\u1EADt kh\u1EA9u t\u00E0i kho\u1EA3n b\u1EDFi h\u1EC7 th\u1ED1ng (qu\u00EAn m\u1EADt kh\u1EA9u)");
		
		NOTIFICATION_TEMPLATE_INIT.put("USER-04", "Th\u00F4ng b\u00E1o m\u1EADt kh\u1EA9u t\u00E0i kho\u1EA3n \u0111\u00E3 thay \u0111\u1ED5i");
		
		NOTIFICATION_TEMPLATE_INIT.put("NEWSBOARD", "M\u1EDDi theo d\u00F5i b\u1EA3n tin \u0111\u01B0\u1EE3c c\u00F4ng b\u1ED1");
		
		NOTIFICATION_TEMPLATE_INIT.put("WORKCHECKIN", "M\u1EDDi theo d\u00F5i l\u1ECBch checkin");
		
		NOTIFICATION_TEMPLATE_INIT.put("TIMESHEET-01", "M\u1EDDi theo d\u00F5i b\u1EA3ng ch\u1EA5m c\u00F4ng th\u00E1ng");
		
		NOTIFICATION_TEMPLATE_INIT.put("TIMESHEET-02", "C\u1EADp nh\u1EADt d\u1EEF li\u1EC7u b\u1EA3ng ch\u1EA5m c\u00F4ng th\u00E1ng");
	}
}
