package org.opencps.dossiermgt.action.impl;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.List;

import org.opencps.dossiermgt.action.UserInfoLogActions;
import org.opencps.dossiermgt.constants.ServiceInfoTerm;
import org.opencps.dossiermgt.model.UserInfoLog;
import org.opencps.dossiermgt.service.UserInfoLogLocalServiceUtil;

public class UserInfoLogActionsImpl implements UserInfoLogActions{

	@Override
	public UserInfoLog addUserInfoLog(long userId, String serviceInfo, String applicant, String dossierNo,
			ServiceContext serviceContext) {

		JSONObject jsonData = JSONFactoryUtil.createJSONObject();
		jsonData.put(ServiceInfoTerm.KEY_SERVICE_INFO, serviceInfo);
		jsonData.put(ServiceInfoTerm.KEY_APPLICANT, applicant);
		jsonData.put(ServiceInfoTerm.KEY_DOSSIER_NO, dossierNo);

		return UserInfoLogLocalServiceUtil.addUserInfoLog(userId, jsonData, serviceContext);
	}

	@Override
	public List<UserInfoLog> getUserInfoLog(long userId, ServiceContext serviceContext) {
		
		return UserInfoLogLocalServiceUtil.getUserInfoLogs(userId);
	}

}
