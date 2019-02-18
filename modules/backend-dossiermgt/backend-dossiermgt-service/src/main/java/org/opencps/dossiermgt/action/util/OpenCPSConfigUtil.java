package org.opencps.dossiermgt.action.util;

import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

public class OpenCPSConfigUtil {
	public static final String OPENCPS_NOTIFICATION_ENABLE = "org.opencps.notification.enable";
	public static final String OPENCPS_CACHE_TTL = "cache.default.ttl.seconds";
	private static final long DEFAULT_TTL = 3600;
	
	public static boolean isNotificationEnable() {
	    String notificationEnableProperty = PropsUtil.get(OPENCPS_NOTIFICATION_ENABLE);
	    return Validator.isNotNull(notificationEnableProperty) ? Boolean.parseBoolean(notificationEnableProperty) : false;
	}
	
	public static long getCacheTTL() {
	    String cacheTTLProperty = PropsUtil.get(OPENCPS_CACHE_TTL);
	    return Validator.isNotNull(cacheTTLProperty) ? Long.parseLong(cacheTTLProperty) : DEFAULT_TTL;
		
	}
}
