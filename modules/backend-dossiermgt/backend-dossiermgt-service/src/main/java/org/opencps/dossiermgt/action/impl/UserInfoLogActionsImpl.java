package org.opencps.dossiermgt.action.impl;

import java.util.List;

import org.opencps.dossiermgt.action.UserInfoLogActions;
import org.opencps.dossiermgt.model.UserInfoLog;
import org.opencps.dossiermgt.service.UserInfoLogLocalServiceUtil;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.service.ServiceContext;

public class UserInfoLogActionsImpl implements UserInfoLogActions{

	@Override
	public UserInfoLog addUserInfoLog(long userId, String serviceInfo, String applicant, String dossierNo,
			ServiceContext serviceContext) {

		JSONObject jsonData = JSONFactoryUtil.createJSONObject();
		jsonData.put("serviceInfo", serviceInfo);
		jsonData.put("applicant", applicant);
		jsonData.put("dossierNo", dossierNo);

		return UserInfoLogLocalServiceUtil.addUserInfoLog(userId, jsonData, serviceContext);
	}

	@Override
	public List<UserInfoLog> getUserInfoLog(long userId, ServiceContext serviceContext) {
		
		return UserInfoLogLocalServiceUtil.getUserInfoLogs(userId);
	}

}
