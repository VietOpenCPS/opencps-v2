package org.opencps.usermgt.action.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.Validator;

public class UserMgtConfigUtil {
	public static final String OPENCPS_TRACK_USER_ENABLE = "org.opencps.trackuser.enable";
	public static final String OPENCPS_TRACK_CLIENT_ENABLE = "org.opencps.trackclient.enable";
	public static final String OPENCPS_GEODB_PATH = "org.opencps.geodb.path";
	
	public static boolean isTrackUserEnable() {
		String trackUserEnable = PropsUtil.get(OPENCPS_TRACK_USER_ENABLE);
		return Validator.isNotNull(trackUserEnable) ? Boolean.parseBoolean(trackUserEnable) : false;
	}	

	public static boolean isTrackClientEnable() {
		String trackClientEnable = PropsUtil.get(OPENCPS_TRACK_CLIENT_ENABLE);
		return Validator.isNotNull(trackClientEnable) ? Boolean.parseBoolean(trackClientEnable) : true;
	}	
	
	public static String getGeoDBPath() {
		String geoDBPath = PropsUtil.get(OPENCPS_GEODB_PATH);
		return Validator.isNotNull(geoDBPath) ? geoDBPath : StringPool.BLANK;
	}
}
