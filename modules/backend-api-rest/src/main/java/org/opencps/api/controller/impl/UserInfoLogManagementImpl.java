package org.opencps.api.controller.impl;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.UserInfoLogManagement;
import org.opencps.api.userinfolog.model.UserInfoLogInputModel;
import org.opencps.api.userinfolog.model.UserInfoLogSearchModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.dossiermgt.action.UserInfoLogActions;
import org.opencps.dossiermgt.action.impl.UserInfoLogActionsImpl;
import org.opencps.dossiermgt.model.UserInfoLog;

import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;

public class UserInfoLogManagementImpl implements UserInfoLogManagement{

	@Override
	public Response getUserLogs(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, UserInfoLogSearchModel query) {

		BackendAuth auth = new BackendAuthImpl();
		long userId = user.getUserId();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			UserInfoLogActions uilAction = new UserInfoLogActionsImpl();

			List<UserInfoLog> userInfoList = uilAction.getUserInfoLog(userId, serviceContext);
			UserInfoLog userInfo = userInfoList.get(0);

			return Response.status(200).entity(JSONFactoryUtil.looseSerialize(userInfo.getPayload())).build();
		} catch (Exception e) {
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(e).build();
		}
	}

	@Override
	public Response addUserLog(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, UserInfoLogInputModel input) {

		BackendAuth auth = new BackendAuthImpl();
		long userId = user.getUserId();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			UserInfoLogActions uilAction = new UserInfoLogActionsImpl();

			String serviceInfo = input.getServiceInfo();
			String applicant = input.getApplicant();
			String dossierNo = input.getDossierNo();

			UserInfoLog userInfo = uilAction.addUserInfoLog(userId, serviceInfo, applicant, 
					dossierNo, serviceContext);

			return Response.status(200).entity(JSONFactoryUtil.looseSerialize(userInfo.getPayload())).build();
		} catch (Exception e) {
			return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(e).build();
		}
	}

}
