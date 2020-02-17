package org.opencps.api.controller.impl;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.backgroundtask.BackgroundTask;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskManagerUtil;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatus;
import com.liferay.portal.kernel.backgroundtask.BackgroundTaskStatusRegistryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.RoleConstants;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.RoleLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.Serializable;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.constants.SystemManagementConstants;
import org.opencps.api.controller.SystemManagement;
import org.opencps.api.controller.util.MessageUtil;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.background.executor.SiteCleanBackgroundTaskExecutor;
import org.opencps.background.model.BackgroundTaskProgress;
import org.opencps.background.model.BackgroundTaskResult;
import org.opencps.communication.model.Notificationtemplate;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.NotificationtemplateLocalServiceUtil;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.dossiermgt.action.ServiceProcessActions;
import org.opencps.dossiermgt.action.impl.ServiceProcessActionsImpl;
import org.opencps.dossiermgt.constants.ProcessActionTerm;
import org.opencps.dossiermgt.model.ActionConfig;
import org.opencps.dossiermgt.model.DocumentType;
import org.opencps.dossiermgt.model.MenuConfig;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ServiceConfig;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.model.StepConfig;
import org.opencps.dossiermgt.service.ActionConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.DocumentTypeLocalServiceUtil;
import org.opencps.dossiermgt.service.MenuConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessActionLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceInfoLocalServiceUtil;
import org.opencps.dossiermgt.service.StepConfigLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;

public class SystemManagementImpl implements SystemManagement {
	Log _log = LogFactoryUtil.getLog(SystemManagementImpl.class.getName());
	@Override
	public Response cleanSite(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, Long siteId) {
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		
		BackgroundTaskResult result = new BackgroundTaskResult();
	    Map<String, Serializable> taskContextMap = new HashMap<>();
	    taskContextMap.put(Field.GROUP_ID, siteId);

	    try {
			BackgroundTask backgroundTask = BackgroundTaskManagerUtil.addBackgroundTask(user.getUserId(),
			        groupId, SiteCleanBackgroundTaskExecutor.class.getName(),
			        SiteCleanBackgroundTaskExecutor.class.getName(),
			        taskContextMap, serviceContext);
			
			result.setBackgroundTaskId(backgroundTask.getBackgroundTaskId());
		} catch (PortalException e) {
			_log.error(e);
		}
	    
		return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();
	}

	@Override
	public Response getProgress(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, Long backgroundTaskId) {
		BackgroundTaskProgress result = new BackgroundTaskProgress();
		
		BackgroundTaskStatus backgroundTaskStatus =
			    BackgroundTaskStatusRegistryUtil.getBackgroundTaskStatus(
			            backgroundTaskId);
		
		if (backgroundTaskStatus != null && Validator.isNotNull(backgroundTaskStatus.getAttribute(SystemManagementConstants.PERCENTAGE))) {
			result.setPercentage((int)backgroundTaskStatus.getAttribute(SystemManagementConstants.PERCENTAGE));			
		}
		else {
			result.setPercentage(100);
		}
		result.setBackgroundTaskId(backgroundTaskId);
		if (backgroundTaskStatus != null) {
			result.setExecutionLog((String)backgroundTaskStatus.getAttribute(SystemManagementConstants.EXECUTION_LOG));			
		}
		else {
			result.setExecutionLog(StringPool.BLANK);
		}
		
		return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();
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
				if (r.getName().startsWith(ConstantUtils.ROLE_ADMIN)) {
					isAdmin = true;
					break;
				}
			}
			
			if (!isAdmin) {
				throw new UnauthenticationException();
			}
			
			Role oldApplicantRole = RoleLocalServiceUtil.fetchRole(user.getCompanyId(), SystemManagementConstants.APPLICANT);
			Map<Locale, String> titleMap = new HashMap<>();
			titleMap.put(Locale.getDefault(), SystemManagementConstants.APPLICANT);
			
			Map<Locale, String> titleDesc = new HashMap<>();
			titleDesc.put(Locale.getDefault(), SystemManagementConstants.APPLICANT);

			if (oldApplicantRole == null) {
				RoleLocalServiceUtil.addRole(user.getUserId(), Role.class.getName(), CounterLocalServiceUtil.increment(),
						SystemManagementConstants.APPLICANT, titleMap, titleDesc, RoleConstants.TYPE_REGULAR, SystemManagementConstants.APPLICANT, serviceContext);				
				oldApplicantRole = RoleLocalServiceUtil.fetchRole(user.getCompanyId(), SystemManagementConstants.APPLICANT);
				oldApplicantRole.setTitle(SystemManagementConstants.APPLICANT);
				oldApplicantRole.setDescription(SystemManagementConstants.APPLICANT);
				RoleLocalServiceUtil.updateRole(oldApplicantRole);
			}
			Role oldEmployeeRole = RoleLocalServiceUtil.fetchRole(user.getCompanyId(), SystemManagementConstants.EMPLOYEE);
			if (oldEmployeeRole == null) {
				titleMap.put(Locale.getDefault(), SystemManagementConstants.EMPLOYEE);
				titleDesc.put(Locale.getDefault(), SystemManagementConstants.EMPLOYEE);				
				RoleLocalServiceUtil.addRole(user.getUserId(), Role.class.getName(), CounterLocalServiceUtil.increment(),
						SystemManagementConstants.EMPLOYEE, titleMap, titleDesc, RoleConstants.TYPE_REGULAR, SystemManagementConstants.EMPLOYEE, serviceContext);				
				oldEmployeeRole = RoleLocalServiceUtil.fetchRole(user.getCompanyId(), SystemManagementConstants.CITIZEN);
				oldEmployeeRole.setTitle(SystemManagementConstants.EMPLOYEE);
				oldEmployeeRole.setDescription(SystemManagementConstants.EMPLOYEE);
				RoleLocalServiceUtil.updateRole(oldEmployeeRole);
			}
			Role oldCitizenRole = RoleLocalServiceUtil.fetchRole(user.getCompanyId(), SystemManagementConstants.CITIZEN);
			if (oldCitizenRole == null) {
				titleMap.put(Locale.getDefault(), SystemManagementConstants.CITIZEN);
				titleDesc.put(Locale.getDefault(), SystemManagementConstants.CITIZEN);
				RoleLocalServiceUtil.addRole(user.getUserId(), Role.class.getName(), CounterLocalServiceUtil.increment(),
						SystemManagementConstants.CITIZEN, titleMap, titleDesc, RoleConstants.TYPE_REGULAR, SystemManagementConstants.CITIZEN, serviceContext);
				oldCitizenRole = RoleLocalServiceUtil.fetchRole(user.getCompanyId(), SystemManagementConstants.CITIZEN);
				oldCitizenRole.setTitle(SystemManagementConstants.CITIZEN);
				oldCitizenRole.setDescription(SystemManagementConstants.CITIZEN);
				RoleLocalServiceUtil.updateRole(oldCitizenRole);
			}
			
			return Response.status(HttpURLConnection.HTTP_OK).entity(StringPool.BLANK).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response verifyMasterData(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext) {
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith(ConstantUtils.ROLE_ADMIN)) {
					isAdmin = true;
					break;
				}
			}
			
			if (!isAdmin) {
				throw new UnauthenticationException();
			}
			
			JSONObject resultObj = JSONFactoryUtil.createJSONObject();
			
			JSONArray verifyWarninglArr = JSONFactoryUtil.createJSONArray();
			JSONArray verifyServiceConfigArr = verifyCriticalServiceConfig(groupId);
			JSONArray verifyErrorArr = JSONFactoryUtil.createJSONArray();
			
			if (groupId != 0) {
				JSONArray verifyCriticalArr = verifyCriticalConfig(groupId);
				verifyWarninglArr = verifyWarningConfig(groupId);
				for (int i = 0; i < verifyCriticalArr.length(); i++) {
					verifyErrorArr.put(verifyCriticalArr.get(i));
				}
				for (int i = 0; i < verifyServiceConfigArr.length(); i++) {
					verifyErrorArr.put(verifyServiceConfigArr.get(i));
				}
			}
			else {
			}
			
			if (verifyErrorArr.length() > 0) {
				resultObj.put(SystemManagementConstants.VERIFY, false);
				resultObj.put(SystemManagementConstants.MESSAGE, MessageUtil.getMessage(SystemManagementConstants.ERRORS_MESSAGE));	
				resultObj.put(SystemManagementConstants.ERRORS, verifyErrorArr);
			}
			else {
				resultObj.put(SystemManagementConstants.VERIFY, true);
				if (verifyWarninglArr.length() > 0) {
					resultObj.put(SystemManagementConstants.MESSAGE, MessageUtil.getMessage(SystemManagementConstants.WARNINGS_MESSAGE));					
				}
				else {
					resultObj.put(SystemManagementConstants.MESSAGE, MessageUtil.getMessage(SystemManagementConstants.SUCCESS_MESSAGE));
				}
			}
			
			resultObj.put(SystemManagementConstants.WARNINGS, verifyWarninglArr);

			return Response.status(HttpURLConnection.HTTP_OK).entity(resultObj.toJSONString()).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	private JSONArray verifyCriticalConfig(long groupId) {
		JSONArray objArr = JSONFactoryUtil.createJSONArray();
				
		if (groupId != 0) {
			List<ActionConfig> lstAcs = ActionConfigLocalServiceUtil.getByGroupId(groupId);

			if (lstAcs.isEmpty()) {
				JSONObject obj = JSONFactoryUtil.createJSONObject();
				obj.put(SystemManagementConstants.MESSAGE, MessageUtil.getMessage(SystemManagementConstants.ACTIONCONFIG_MISSING_MESSAGE));
				
				objArr.put(obj);
			}
			List<MenuConfig> lstMenuConfigs = MenuConfigLocalServiceUtil.getByGroupId(groupId);
			if (lstMenuConfigs.isEmpty()) {
				JSONObject obj = JSONFactoryUtil.createJSONObject();
				obj.put(SystemManagementConstants.MESSAGE, MessageUtil.getMessage(SystemManagementConstants.MENUCONFIG_ERROR_MESSAGE));
				
				objArr.put(obj);
			}
			List<ServerConfig> lstServerConfigs = ServerConfigLocalServiceUtil.getGroupId(groupId);
			if (lstServerConfigs.isEmpty()) {
				JSONObject obj = JSONFactoryUtil.createJSONObject();
				obj.put(SystemManagementConstants.MESSAGE, MessageUtil.getMessage(SystemManagementConstants.SERVERCONFIG_ERROR_MESSAGE));
				
				objArr.put(obj);
			}
			List<StepConfig> lstStepConfigs = StepConfigLocalServiceUtil.getStepByGroupId(groupId);
			if (lstStepConfigs.isEmpty()) {
				JSONObject obj = JSONFactoryUtil.createJSONObject();
				obj.put(SystemManagementConstants.MESSAGE, MessageUtil.getMessage(SystemManagementConstants.STEPCONFIG_ERROR_MESSAGE));
				
				objArr.put(obj);
			}
		}
		else {
			
		}
		
		return objArr;
	}
	
	private JSONArray verifyWarningConfig(long groupId) {
		JSONArray objArr = JSONFactoryUtil.createJSONArray();
				
		if (groupId != 0) {
			List<ActionConfig> lstAcs = ActionConfigLocalServiceUtil.getByGroupId(groupId);

			if (lstAcs.isEmpty()) {
			}
			else {
				for (ActionConfig ac : lstAcs) {
					if (Validator.isNotNull(ac)) {
						try {
							JSONFactoryUtil.createJSONObject(ac.getFormConfig());
						} catch (JSONException e) {
							JSONObject obj = JSONFactoryUtil.createJSONObject();
							String formConfigMessage = String.format(MessageUtil.getMessage(SystemManagementConstants.FORMCONFIG_ERROR_MESSAGE), ac.getActionCode(), ac.getActionName());
							obj.put(SystemManagementConstants.MESSAGE, formConfigMessage);
							objArr.put(obj);
							_log.debug(e);
						}
						
						//Verify notification template
						if (Validator.isNotNull(ac.getNotificationType())) {
							Notificationtemplate nt = NotificationtemplateLocalServiceUtil.fetchByF_NotificationtemplateByType(groupId, ac.getNotificationType());
							if (Validator.isNull(nt)) {
								JSONObject obj = JSONFactoryUtil.createJSONObject();
								String notificationTemplateMessage = String.format(MessageUtil.getMessage(SystemManagementConstants.NOTIFICATIONTEMPLATE_ERROR_MESSAGE), ac.getNotificationType(), ac.getActionCode(), ac.getActionName());
								
								obj.put(SystemManagementConstants.MESSAGE, notificationTemplateMessage);
								objArr.put(obj);							
							}							
						}

						//Verify document type
						if (Validator.isNotNull(ac.getDocumentType())) {
							DocumentType docType = DocumentTypeLocalServiceUtil.getByTypeCode(groupId, ac.getDocumentType());
							if (docType == null) {
								JSONObject obj = JSONFactoryUtil.createJSONObject();
								String documentTypeMessage = String.format(MessageUtil.getMessage(SystemManagementConstants.DOCUMENTTYPE_ERROR_MESSAGE), ac.getDocumentType(), ac.getActionCode(), ac.getActionName());
								
								obj.put(SystemManagementConstants.MESSAGE, documentTypeMessage);
								objArr.put(obj);															
							}
							else if (Validator.isNull(docType.getDocumentScript())) {
								JSONObject obj = JSONFactoryUtil.createJSONObject();
								String documentTypeJasperMessage = String.format(MessageUtil.getMessage(SystemManagementConstants.DOCUMENTTYPE_JASPER_ERROR_MESSAGE), ac.getDocumentType(), ac.getActionCode(), ac.getActionName());

								obj.put(SystemManagementConstants.MESSAGE, documentTypeJasperMessage);
								objArr.put(obj);																							
							}
						}						
					}
				}
			}
			List<MenuConfig> lstMenuConfigs = MenuConfigLocalServiceUtil.getByGroupId(groupId);
			if (lstMenuConfigs.isEmpty()) {
			}
			else {
				
			}
			List<ServerConfig> lstServerConfigs = ServerConfigLocalServiceUtil.getGroupId(groupId);
			if (lstServerConfigs.isEmpty()) {
			}
			List<StepConfig> lstStepConfigs = StepConfigLocalServiceUtil.getStepByGroupId(groupId);
			if (lstStepConfigs.isEmpty()) {
			}
			
		}
		else {
			
		}
		
		return objArr;
	}	
	
	private JSONArray verifyCriticalServiceConfig(long groupId) {
		JSONArray objArr = JSONFactoryUtil.createJSONArray();
				
		if (groupId != 0) {
			List<ServiceConfig> lstScs;
			try {
				lstScs = ServiceConfigLocalServiceUtil.getByGroupId(groupId);
				for (ServiceConfig sc : lstScs) {
					ServiceInfo si = ServiceInfoLocalServiceUtil.fetchServiceInfo(sc.getServiceInfoId());
					if (si == null) {
						JSONObject obj = JSONFactoryUtil.createJSONObject();
						String serviceConfigMessage = String.format(MessageUtil.getMessage(SystemManagementConstants.SERVICECONFIG_ERROR_MESSAGE), sc.getServiceConfigId());
						obj.put(SystemManagementConstants.MESSAGE, serviceConfigMessage);
						objArr.put(obj);
					}
				}
			} catch (SystemException e) {
				_log.debug(e);
			} catch (PortalException e) {
				_log.debug(e);
			}
		}
		else {
			
		}
		
		return objArr;
	}

	@Override
	public Response resolveConflict(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext) {
		ServiceProcessActions actions = new ServiceProcessActionsImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		BackendAuth auth = new BackendAuthImpl();
		Indexer<ProcessAction> indexer = IndexerRegistryUtil
				.nullSafeGetIndexer(ProcessAction.class);
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(Field.KEYWORD_SEARCH, StringPool.BLANK);
			
			Sort[] sorts = new Sort[] { };

			JSONObject jsonData = actions.getProcessActions(user.getUserId(), serviceContext.getCompanyId(), groupId,
					params, sorts, QueryUtil.ALL_POS, QueryUtil.ALL_POS, serviceContext);

			long total = jsonData.getInt(ConstantUtils.TOTAL);
			if (total > 0) {
				List<Document> lstDocuments = (List<Document>) jsonData.get(ConstantUtils.DATA);	
				for (Document document : lstDocuments) {
					long processActionId = GetterUtil.getLong(document.get(ProcessActionTerm.PROCESS_ACTION_ID));
					long companyId = GetterUtil.getLong(document.get(Field.COMPANY_ID));
					String uid = document.get(Field.UID);
					ProcessAction pa = ProcessActionLocalServiceUtil.fetchProcessAction(processActionId);
					if (pa == null) {
						try {
							indexer.delete(companyId, uid);
						} catch (SearchException e) {
							_log.error(e);
						}
					}
				}
			}
			
			return Response.status(HttpURLConnection.HTTP_OK).entity(ConstantUtils.API_JSON_EMPTY).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response batchChangePassword(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String suffix, String newPassword) {
		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			List<Role> userRoles = user.getRoles();
			boolean isAdmin = false;
			for (Role r : userRoles) {
				if (r.getName().startsWith(ConstantUtils.ROLE_ADMIN)) {
					isAdmin = true;
					break;
				}
			}
			
			if (!isAdmin) {
				throw new UnauthenticationException();
			}
			long groupId = header.getRequestHeaders().containsKey(Field.GROUP_ID) ? GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID)) : 0l;
			
			List<User> lstUsers = (groupId == 0) ? UserLocalServiceUtil.getUsers(QueryUtil.ALL_POS, QueryUtil.ALL_POS) : UserLocalServiceUtil.getGroupUsers(groupId);
			for (User u : lstUsers) {
				if (u.getEmailAddress().endsWith(suffix)) {
					u.setPasswordEncrypted(false);
					UserLocalServiceUtil.updateUser(u);
					UserLocalServiceUtil.updatePassword(u.getUserId(), newPassword, newPassword, false);
				}
			}
			return Response.status(HttpURLConnection.HTTP_OK).entity(StringPool.BLANK).build();

		} catch (Exception e) {
			e.printStackTrace();
			return BusinessExceptionImpl.processException(e);
		}

	}	
}
