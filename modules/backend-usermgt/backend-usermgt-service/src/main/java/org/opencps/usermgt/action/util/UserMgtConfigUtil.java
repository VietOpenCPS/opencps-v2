package org.opencps.usermgt.action.util;

import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

public class UserMgtConfigUtil {
	public static final String OPENCPS_TRACK_USER_ENABLE = "org.opencps.trackuser.enable";
	
	public static boolean isTrackUserEnable() {
		String trackUserEnable = PropsUtil.get(OPENCPS_TRACK_USER_ENABLE);
		return Validator.isNotNull(trackUserEnable) ? Boolean.parseBoolean(trackUserEnable) : false;
	}	

}
