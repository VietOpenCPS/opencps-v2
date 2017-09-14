package org.opencps.communication.constants;

import java.util.HashMap;
import java.util.Map;

import com.liferay.portal.kernel.util.WebKeys;

import aQute.bnd.osgi.Constants;

public class NotificationMGTConstants implements Constants, WebKeys {

	public static final Map<String, String> NOTIFICATION_TEMPLATE_INIT = new HashMap<String, String>();

	static {
		
		NOTIFICATION_TEMPLATE_INIT.put("ACTIVITY-01", "M\u1EDDi tham gia m\u1ED9t ho\u1EA1t \u0111\u1ED9ng");
		
		NOTIFICATION_TEMPLATE_INIT.put("ACTIVITY-02", "C\u1EADp nh\u1EADt tr\u1EA1ng th\u00E1i m\u1EDBi c\u1EE7a ng\u01B0\u1EDDi tham gia ho\u1EA1t \u0111\u1ED9ng (x\u00E1c nh\u1EADn/kh\u00F4ng)");
		
		NOTIFICATION_TEMPLATE_INIT.put("ACTIVITY-03", "T\u1EA1o t\u00E0i li\u1EC7u cho ho\u1EA1t \u0111\u1ED9ng (fileattachs)");
		
		NOTIFICATION_TEMPLATE_INIT.put("ACTIVITY-04", "Xin \u00FD ki\u1EBFn trong ho\u1EA1t \u0111\u1ED9ng");
		
		NOTIFICATION_TEMPLATE_INIT.put("ACTIVITY-05", "C\u1EADp nh\u1EADt \u00FD ki\u1EBFn trong ho\u1EA1t \u0111\u1ED9ng");
		
		NOTIFICATION_TEMPLATE_INIT.put("ACTIVITY-06", "T\u1EA1o c\u00F4ng vi\u1EC7c m\u1EDBi cho ho\u1EA1t \u0111\u1ED9ng (checklist)");
		
		NOTIFICATION_TEMPLATE_INIT.put("ACTIVITY-07", "C\u1EADp nh\u1EADt b\u00ECnh lu\u1EADn m\u1EDBi ho\u1EA1t \u0111\u1ED9ng");
		
		NOTIFICATION_TEMPLATE_INIT.put("ACTIVITY-08", "Nh\u1EAFc l\u1ECBch ho\u1EA1t \u0111\u1ED9ng (tr\u01B0\u1EDBc khi b\u1EAFt \u0111\u1EA7u v\u00E0 k\u1EBFt th\u00FAc)");
		
		NOTIFICATION_TEMPLATE_INIT.put("ACTIVITY-09", "C\u1EADp nh\u1EADt th\u1EDDi gian, \u0111\u1ECBa \u0111i\u1EC3m ho\u1EA1t \u0111\u1ED9ng");
		
		NOTIFICATION_TEMPLATE_INIT.put("ACTIVITY-10", "Th\u00F4ng b\u00E1o thay \u0111\u1ED5i tr\u1EA1ng th\u00E1i c\u1EE7a ho\u1EA1t \u0111\u1ED9ng");
		
		NOTIFICATION_TEMPLATE_INIT.put("DOCUMENT-01", "M\u1EDDi theo d\u00F5i v\u0103n b\u1EA3n m\u1EDBi");
		
		NOTIFICATION_TEMPLATE_INIT.put("DOCUMENT-02", "C\u1EADp nh\u1EADt fileattachs cho v\u0103n b\u1EA3n");
		
		NOTIFICATION_TEMPLATE_INIT.put("DOCUMENT-03", "\u00DD ki\u1EBFn x\u1EED l\u00FD v\u0103n b\u1EA3n");
		
		NOTIFICATION_TEMPLATE_INIT.put("DOCUMENT-04", "Xin \u00FD ki\u1EBFn th\u1ED1ng nh\u1EA5t v\u0103n b\u1EA3n");
		
		NOTIFICATION_TEMPLATE_INIT.put("DOCUMENT-05", "C\u1EADp nh\u1EADt \u00FD ki\u1EBFn th\u1ED1ng nh\u1EA5t v\u0103n b\u1EA3n");
		
		NOTIFICATION_TEMPLATE_INIT.put("DOCUMENT-06", "Xin ph\u00EA duy\u1EC7t v\u0103n b\u1EA3n");

		NOTIFICATION_TEMPLATE_INIT.put("DOCUMENT-07", "\u0110\u00E3 ph\u00EA duy\u1EC7t v\u0103n b\u1EA3n");
		
		NOTIFICATION_TEMPLATE_INIT.put("ARCHIVE-01", "M\u1EDDi theo d\u00F5i h\u1ED3 s\u01A1 m\u1EDBi t\u1EA1o ra");
		
		NOTIFICATION_TEMPLATE_INIT.put("ARCHIVE-02", "C\u1EADp nh\u1EADt v\u0103n b\u1EA3n m\u1EDBi tr\u00EAn h\u1ED3 s\u01A1");
		
		NOTIFICATION_TEMPLATE_INIT.put("ARCHIVE-03", "\u00DD ki\u1EBFn x\u1EED l\u00FD tr\u00EAn h\u1ED3 s\u01A1");
		
		NOTIFICATION_TEMPLATE_INIT.put("ARCHIVE-04", "Xin \u00FD ki\u1EBFn th\u1ED1ng nh\u1EA5t h\u1ED3 s\u01A1");
		
		NOTIFICATION_TEMPLATE_INIT.put("ARCHIVE-05", "C\u1EADp nh\u1EADt \u00FD ki\u1EBFn th\u1ED1ng nh\u1EA5t h\u1ED3 s\u01A1");
		
		NOTIFICATION_TEMPLATE_INIT.put("ARCHIVE-06", "Xin ph\u00EA duy\u1EC7t h\u1ED3 s\u01A1");
		
		NOTIFICATION_TEMPLATE_INIT.put("ARCHIVE-07", "\u0110\u00E3 ph\u00EA duy\u1EC7t h\u1ED3 s\u01A1");
		
		NOTIFICATION_TEMPLATE_INIT.put("CHECKLIST-01", "Ph\u00E2n c\u00F4ng th\u1EF1c hi\u1EC7n c\u00F4ng vi\u1EC7c");
		
		NOTIFICATION_TEMPLATE_INIT.put("CHECKLIST-02", "M\u1EDDi theo d\u00F5i c\u00F4ng vi\u1EC7c");
		
		NOTIFICATION_TEMPLATE_INIT.put("CHECKLIST-03", "Th\u00F4ng b\u00E1o thay \u0111\u1ED5i tr\u1EA1ng th\u00E1i, th\u00F4ng tin c\u1EE7a c\u00F4ng vi\u1EC7c");
		
		NOTIFICATION_TEMPLATE_INIT.put("CHECKLIST-04", "B\u00ECnh lu\u1EADn m\u1EDBi c\u00F4ng vi\u1EC7c");
		
		NOTIFICATION_TEMPLATE_INIT.put("CHECKLIST-05", "\u0110\u00EDnh k\u00E8m fileattachs v\u00E0o c\u00F4ng vi\u1EC7c");
		
		NOTIFICATION_TEMPLATE_INIT.put("PROJECT-01", "M\u1EDDi theo d\u00F5i k\u1EBF ho\u1EA1ch d\u1EF1 \u00E1n");
		
		NOTIFICATION_TEMPLATE_INIT.put("PROJECT-02", "Nh\u1EAFc l\u1ECBch nhi\u1EC7m v\u1EE5 trong d\u1EF1 \u00E1n");
		
		NOTIFICATION_TEMPLATE_INIT.put("PROJECT-03", "Th\u00F4ng b\u00E1o c\u1EADp nh\u1EADt tr\u1EA1ng th\u00E1i v\u00E0 th\u00F4ng tin c\u1EE7a nhi\u1EC7m v\u1EE5 trong d\u1EF1 \u00E1n");
		
		NOTIFICATION_TEMPLATE_INIT.put("PROJECT-04", "\u00DD ki\u1EBFn x\u1EED l\u00FD tr\u00EAn k\u1EBF ho\u1EA1ch d\u1EF1 \u00E1n");
		
		NOTIFICATION_TEMPLATE_INIT.put("PROJECT-05", "Xin \u00FD ki\u1EBFn th\u1ED1ng nh\u1EA5t k\u1EBF ho\u1EA1ch");
		
		NOTIFICATION_TEMPLATE_INIT.put("PROJECT-06", "C\u1EADp nh\u1EADt \u00FD ki\u1EBFn th\u1ED1ng nh\u1EA5t k\u1EBF ho\u1EA1ch");
		
		NOTIFICATION_TEMPLATE_INIT.put("PROJECT-07", "Xin ph\u00EA duy\u1EC7t k\u1EBF ho\u1EA1ch d\u1EF1 \u00E1n");
		
		NOTIFICATION_TEMPLATE_INIT.put("PROJECT-08", "\u0110\u00E3 ph\u00EA duy\u1EC7t k\u1EBF ho\u1EA1ch d\u1EF1 \u00E1n");
		
		NOTIFICATION_TEMPLATE_INIT.put("USER-01", "Th\u00F4ng b\u00E1o m\u1EDF t\u00E0i kho\u1EA3n m\u1EDBi cho ng\u01B0\u1EDDi d\u00F9ng tr\u00EAn h\u1EC7 th\u1ED1ng");
		
		NOTIFICATION_TEMPLATE_INIT.put("USER-02", "Th\u00F4ng b\u00E1o thay \u0111\u1ED5i tr\u1EA1ng th\u00E1i k\u00EDch ho\u1EA1t t\u00E0i kho\u1EA3n");
		
		NOTIFICATION_TEMPLATE_INIT.put("USER-03", "Th\u00F4ng b\u00E1o reset m\u1EADt kh\u1EA9u t\u00E0i kho\u1EA3n b\u1EDFi h\u1EC7 th\u1ED1ng (qu\u00EAn m\u1EADt kh\u1EA9u)");
	}
}
