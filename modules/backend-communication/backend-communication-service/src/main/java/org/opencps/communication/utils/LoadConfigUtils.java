package org.opencps.communication.utils;

import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

public class LoadConfigUtils {
    public static final String OPENCPS_ENABLE_ALL_SCHEDULER = "org.opencps.scheduler.enable";

    public static boolean isEnableAllScheduler() {
        return Validator.isNotNull(PropsUtil.get(OPENCPS_ENABLE_ALL_SCHEDULER))
                ? Boolean.parseBoolean(PropsUtil.get(OPENCPS_ENABLE_ALL_SCHEDULER)) : false;
    }
}
