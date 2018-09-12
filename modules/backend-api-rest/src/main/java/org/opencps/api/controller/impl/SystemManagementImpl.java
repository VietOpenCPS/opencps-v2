package org.opencps.api.controller.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.SystemManagement;
import org.opencps.background.executor.SiteCleanBackgroundTaskExecutor;
import org.opencps.background.model.BackgroundTaskProgress;
import org.opencps.background.model.BackgroundTaskResult;

import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskManagerUtil;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatus;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatusRegistryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

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

}
