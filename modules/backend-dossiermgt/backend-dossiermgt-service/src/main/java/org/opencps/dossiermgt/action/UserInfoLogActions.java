package org.opencps.dossiermgt.action;

import java.util.List;

import org.opencps.dossiermgt.model.UserInfoLog;

import com.liferay.portal.kernel.service.ServiceContext;

public interface UserInfoLogActions {

	public UserInfoLog addUserInfoLog(long userId, String serviceInfo, String applicant, String dossierNo,
			ServiceContext serviceContext);

	public List<UserInfoLog> getUserInfoLog(long userId, ServiceContext serviceContext);

}
