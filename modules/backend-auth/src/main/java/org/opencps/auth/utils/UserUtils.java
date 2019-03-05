package org.opencps.auth.utils;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

public class UserUtils {
	private static Log _log = LogFactoryUtil.getLog(UserUtils.class);

	public static User getUser(long userId) {

		User user = null;

		try {
			user = UserLocalServiceUtil.getUser(userId);
		} catch (PortalException e) {
			_log.error(e);
		}

		return user;
	}
}
