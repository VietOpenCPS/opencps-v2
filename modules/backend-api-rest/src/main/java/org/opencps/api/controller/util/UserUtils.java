package org.opencps.api.controller.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

public class UserUtils {
	/**
	 * @author khoavu
	 * @param userId
	 * @return
	 */
	public static User getUser(long userId) {
		try {
			return UserLocalServiceUtil.getUser(userId);
		} catch (Exception e) {
			return null;
		}
	}

	Log _log = LogFactoryUtil.getLog(UserUtils.class);
}
