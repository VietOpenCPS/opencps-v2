package org.opencps.dossiermgt.action.util;

import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

public class OpenCPSConfigUtil {
	public static final String OPENCPS_NOTIFICATION_ENABLE = "org.opencps.notification.enable";
	
	public static boolean isNotificationEnable() {
	    String notificationEnableProperty = PropsUtil.get(OPENCPS_NOTIFICATION_ENABLE);
	    boolean notificationEnable = Validator.isNotNull(notificationEnableProperty) ? Boolean.parseBoolean(notificationEnableProperty) : false;
	    return notificationEnable;
	}
}
