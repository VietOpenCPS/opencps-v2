package org.opencps.api.controller.impl;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskManagerUtil;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatus;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatusRegistryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.SystemManagement;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.background.executor.SiteCleanBackgroundTaskExecutor;
import org.opencps.background.model.BackgroundTaskProgress;
import org.opencps.background.model.BackgroundTaskResult;

import backend.auth.api.exception.BusinessExceptionImpl;

public class SystemManagementImpl implements SystemManagement {
	Log _log = LogFactoryUtil.getLog(SystemManagementImpl.class.getName());
	@Override
	public Response cleanSite(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, Long siteId) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		
		BackgroundTaskResult result = new BackgroundTaskResult();
	    Map<String, Serializable> taskContextMap = new HashMap<>();
	    taskContextMap.put("groupId", siteId);

	    try {
			BackgroundTask backgroundTask = BackgroundTaskManagerUtil.addBackgroundTask(user.getUserId(),
			        groupId, SiteCleanBackgroundTaskExecutor.class.getName(),
			        SiteCleanBackgroundTaskExecutor.class.getName(),
			        taskContextMap, serviceContext);
			
			result.setBackgroundTaskId(backgroundTask.getBackgroundTaskId());
		} catch (PortalException e) {
			_log.error(e);
		}
	    
		return Response.status(200).entity(result).build();
	}

	@Override
	public Response getProgress(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, Long backgroundTaskId) {
		BackgroundTaskProgress result = new BackgroundTaskProgress();
		
		BackgroundTaskStatus backgroundTaskStatus =
			    BackgroundTaskStatusRegistryUtil.getBackgroundTaskStatus(
			            backgroundTaskId);
		
		if (backgroundTaskStatus != null && Validator.isNotNull(backgroundTaskStatus.getAttribute("percentage"))) {
			result.setPercentage((int)backgroundTaskStatus.getAttribute("percentage"));			
		}
		else {
			result.setPercentage(100);
		}
		result.setBackgroundTaskId(backgroundTaskId);
		if (backgroundTaskStatus != null) {
			result.setExecutionLog((String)backgroundTaskStatus.getAttribute("executionLog"));			
		}
		else {
			result.setExecutionLog(StringPool.BLANK);
		}
		
		return Response.status(200).entity(result).build();
	}

	@Override
	public Response initSystem(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext) {
		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith("Administrator")) {
					isAdmin = true;
					break;
				}
			}
			
			if (!isAdmin) {
				throw new UnauthenticationException();
			}
			
			Role oldApplicantRole = RoleLocalServiceUtil.fetchRole(user.getCompanyId(), "APPLICANT");
			Map<Locale, String> titleMap = new HashMap<>();
			titleMap.put(Locale.getDefault(), "APPLICANT");
			
			Map<Locale, String> titleDesc = new HashMap<>();
			titleDesc.put(Locale.getDefault(), "APPLICANT");

			if (oldApplicantRole == null) {
				RoleLocalServiceUtil.addRole(user.getUserId(), Role.class.getName(), CounterLocalServiceUtil.increment(),
						"APPLICANT", titleMap, titleDesc, RoleConstants.TYPE_REGULAR, "", serviceContext);				
			}
			Role oldEmployeeRole = RoleLocalServiceUtil.fetchRole(user.getCompanyId(), "EMPLOYEE");
			if (oldEmployeeRole == null) {
				titleMap.put(Locale.getDefault(), "EMPLOYEE");
				titleDesc.put(Locale.getDefault(), "EMPLOYEE");				
				RoleLocalServiceUtil.addRole(user.getUserId(), Role.class.getName(), CounterLocalServiceUtil.increment(),
						"EMPLOYEE", titleMap, titleDesc, RoleConstants.TYPE_REGULAR, "", serviceContext);				
			}
			Role oldCitizenRole = RoleLocalServiceUtil.fetchRole(user.getCompanyId(), "CITIZEN");
			if (oldCitizenRole == null) {
				titleMap.put(Locale.getDefault(), "CITIZEN");
				titleDesc.put(Locale.getDefault(), "CITIZEN");
				RoleLocalServiceUtil.addRole(user.getUserId(), Role.class.getName(), CounterLocalServiceUtil.increment(),
						"CITIZEN", titleMap, titleDesc, RoleConstants.TYPE_REGULAR, "", serviceContext);				
			}
			
			return Response.status(200).entity(StringPool.BLANK).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

}
