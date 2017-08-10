package org.opencps.communication.constants;

import java.util.HashMap;
import java.util.Map;

import com.liferay.portal.kernel.util.WebKeys;

import aQute.bnd.osgi.Constants;

public class NotificationMGTConstants implements Constants, WebKeys {

	public static final Map<String, String> NOTIFICATION_TEMPLATE_INIT = new HashMap<String, String>();

	static {
		
		NOTIFICATION_TEMPLATE_INIT.put("ACTIVITY-01", "M\u1EDDi tham gia ho\u1EA1t \u0111\u1ED9ng");
		
		NOTIFICATION_TEMPLATE_INIT.put("ACTIVITY-02", "C\u1EADp nh\u1EADt tr\u1EA1ng th\u00E1i m\u1EDBi c\u1EE7a ng\u01B0\u1EDDi tham gia ho\u1EA1t \u0111\u1ED9ng (x\u00E1c nh\u1EADn/kh\u00F4ng)");
		
		NOTIFICATION_TEMPLATE_INIT.put("ACTIVITY-03", "C\u1EADp nh\u1EADt t\u00E0i li\u1EC7u/h\u00ECnh \u1EA3nh ho\u1EA1t \u0111\u1ED9ng");
		
		NOTIFICATION_TEMPLATE_INIT.put("ACTIVITY-04", "L\u1EA5y \u00FD ki\u1EBFn trong ho\u1EA1t \u0111\u1ED9ng");
		
		NOTIFICATION_TEMPLATE_INIT.put("ACTIVITY-05", "C\u1EADp nh\u1EADt \u00FD ki\u1EBFn trong ho\u1EA1t \u0111\u1ED9ng");
		
		NOTIFICATION_TEMPLATE_INIT.put("ACTIVITY-06", "C\u1EADp nh\u1EADt t\u00ECnh tr\u1EA1ng c\u00F4ng vi\u1EC7c (checklist)");
		
		NOTIFICATION_TEMPLATE_INIT.put("ACTIVITY-07", "C\u1EADp nh\u1EADt b\u00ECnh lu\u1EADn m\u1EDBi ho\u1EA1t \u0111\u1ED9ng");
		
		NOTIFICATION_TEMPLATE_INIT.put("ACTIVITY-08", "Nh\u1EAFc l\u1ECBch ho\u1EA1t \u0111\u1ED9ng \u0111\u00E3 qu\u00E1 h\u1EA1n");
		
		NOTIFICATION_TEMPLATE_INIT.put("ACTIVITY-09", "C\u1EADp nh\u1EADt th\u1EDDi gian \u0111\u1ECBa \u0111i\u1EC3m ho\u1EA1t \u0111\u1ED9ng");
		
		NOTIFICATION_TEMPLATE_INIT.put("ACTIVITY-10", "C\u1EADp nh\u1EADt tr\u1EA1ng th\u00E1i m\u1EDBi c\u1EE7a ho\u1EA1t \u0111\u1ED9ng (status) - theo tr\u1EA1ng th\u00E1i m\u1EDBi c\u1EE7a template");
		
		NOTIFICATION_TEMPLATE_INIT.put("DOCUMENT-01", "M\u1EDDi theo d\u00F5i v\u0103n b\u1EA3n m\u1EDBi");
		
		NOTIFICATION_TEMPLATE_INIT.put("DOCUMENT-02", "B\u00ECnh lu\u1EADn x\u1EED l\u00FD v\u0103n b\u1EA3n");
		
		NOTIFICATION_TEMPLATE_INIT.put("DOCUMENT-03", "L\u1EA5y \u00FD ki\u1EBFn v\u0103n b\u1EA3n");
		
		NOTIFICATION_TEMPLATE_INIT.put("DOCUMENT-04", "C\u1EADp nh\u1EADt \u00FD ki\u1EBFn v\u0103n b\u1EA3n");
		
		NOTIFICATION_TEMPLATE_INIT.put("DOCUMENT-05", "C\u1EADp nh\u1EADt v\u0103n b\u1EA3n");
		
		NOTIFICATION_TEMPLATE_INIT.put("DOCUMENT-06", "Ph\u00EA duy\u1EC7t v\u0103n b\u1EA3n");
		
		NOTIFICATION_TEMPLATE_INIT.put("GANTTCHART-01", "M\u1EDDi theo d\u00F5i k\u1EBF ho\u1EA1ch ti\u1EBFn \u0111\u1ED9");
		
		NOTIFICATION_TEMPLATE_INIT.put("GANTTCHART-02", "Nh\u1EAFc l\u1ECBch k\u1EBF ho\u1EA1ch (gantt chart)");
		
		NOTIFICATION_TEMPLATE_INIT.put("GANTTCHART-03", "C\u1EADp nh\u1EADt k\u1EBF ho\u1EA1ch (gantt chart)");
		
	}
}
