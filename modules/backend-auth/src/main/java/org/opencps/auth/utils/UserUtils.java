package org.opencps.auth.utils;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

public class UserUtils {
	
	public static User getUser(long userId) {
		
		User user = null;
		
		try {
			user = UserLocalServiceUtil.getUser(userId);
		} catch (Exception e) {
			
		}
		
		return user;
	}
}
