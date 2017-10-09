package org.opencps.api.controller.util;

import java.util.ArrayList;
import java.util.List;

import org.opencps.api.resourceuser.model.ResourceUserModel;
import org.opencps.usermgt.constants.ResourceUserTerm;
import org.opencps.usermgt.model.ResourceUser;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

public class ResourceUserUtils {

	public static List<ResourceUserModel> mapperResourceUserList(List<Document> listDocument) {

		List<ResourceUserModel> results = new ArrayList<>();

		try {

			ResourceUserModel ett = null;

			for (Document document : listDocument) {
				ett = new ResourceUserModel();

				ett.setUserId(Long.valueOf(document.get(ResourceUserTerm.TO_USERID)));
				ett.setEmail(document.get(ResourceUserTerm.TO_USERID));
				ett.setFullName(document.get(ResourceUserTerm.TO_USERID));
				ett.setUserClass(document.get(ResourceUserTerm.TO_USERID));
				ett.setReadonly(true);
				ett.setSelected(Boolean.valueOf(document.get("selected")));

				results.add(ett);
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return results;
	}

	public static ResourceUserModel mapperResourceUserModel(ResourceUser resourceUser) {

		ResourceUserModel ett = new ResourceUserModel();

		try {

			ett.setUserId(resourceUser.getToUserId());

			User user = UserLocalServiceUtil.fetchUser(resourceUser.getUserId());

			ett.setFullName(user.getFullName());

			ett.setSelected(true);

		} catch (Exception e) {
			_log.error(e);
		}

		return ett;
	}

	static Log _log = LogFactoryUtil.getLog(ResourceUserUtils.class);
}
