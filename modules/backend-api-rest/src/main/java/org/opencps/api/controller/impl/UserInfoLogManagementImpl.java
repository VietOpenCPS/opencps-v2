package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.UserInfoLogManagement;
import org.opencps.api.userinfolog.model.UserInfoLogInputModel;
import org.opencps.api.userinfolog.model.UserInfoLogSearchModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.dossiermgt.action.UserInfoLogActions;
import org.opencps.dossiermgt.action.impl.UserInfoLogActionsImpl;
import org.opencps.dossiermgt.model.UserInfoLog;

import backend.auth.api.exception.BusinessExceptionImpl;

public class UserInfoLogManagementImpl implements UserInfoLogManagement{

	Log _log = LogFactoryUtil.getLog(UserInfoLogManagementImpl.class);

	@Override
	public Response getUserLogs(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, UserInfoLogSearchModel query) {

//		_log.info("START++++++++++");
		BackendAuth auth = new BackendAuthImpl();
		long userId = user.getUserId();
		_log.info("userId: "+userId);

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

//			_log.info("START++++++++++");
			UserInfoLogActions uilAction = new UserInfoLogActionsImpl();

//			_log.info("START++++++++++");
			List<UserInfoLog> userInfoList = uilAction.getUserInfoLog(userId, serviceContext);
//			_log.info("START++++++++++");
			UserInfoLog userInfo = null;
			String payLoad = StringPool.BLANK;
			if (userInfoList != null && userInfoList.size() > 0) {
				userInfo = userInfoList.get(0);
				if (userInfo != null) {
					payLoad = userInfo.getPayload();
//					_log.info("START++++++++++: "+payLoad);
				}
			}

			return Response.status(200).entity(payLoad).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
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

			return Response.status(200).entity(userInfo.getPayload()).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

}
