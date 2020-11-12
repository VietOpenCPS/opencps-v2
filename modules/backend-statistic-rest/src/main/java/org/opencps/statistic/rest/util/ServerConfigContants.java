package org.opencps.statistic.rest.util;

import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

public class ServerConfigContants {
	public static final String SERVER_SYNC_KEY = "opencps.dossiersync.client.user";
	public static final String SERVER_SYNC_SECRET = "opencps.dossiersync.client.pass";
	public static final String OPENCPS_ENABLE_ALL_SCHEDULER = "org.opencps.scheduler.enable";
	public static boolean isEnableAllScheduler() {
		return Validator.isNotNull(PropsUtil.get(OPENCPS_ENABLE_ALL_SCHEDULER))
				? Boolean.parseBoolean(PropsUtil.get(OPENCPS_ENABLE_ALL_SCHEDULER)) : false;
	}

}
