package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.opencps.api.controller.ServiceProcessManagement;
import org.opencps.api.controller.util.ProcessSequenceUtils;
import org.opencps.api.controller.util.ServiceProcessUtils;
import org.opencps.api.processsequence.model.ProcessSequenceInputModel;
import org.opencps.api.processsequence.model.ProcessSequenceOutputModel;
import org.opencps.api.processsequence.model.ProcessSequenceResultModel;
import org.opencps.api.serviceprocess.model.ProcessActionInputModel;
import org.opencps.api.serviceprocess.model.ProcessActionResultsModel;
import org.opencps.api.serviceprocess.model.ProcessActionReturnModel;
import org.opencps.api.serviceprocess.model.ProcessActionSearchModel;
import org.opencps.api.serviceprocess.model.ProcessStepInputModel;
import org.opencps.api.serviceprocess.model.ProcessStepResultsModel;
import org.opencps.api.serviceprocess.model.ProcessStepSearchModel;
import org.opencps.api.serviceprocess.model.RoleInputModel;
import org.opencps.api.serviceprocess.model.RoleResultsModel;
import org.opencps.api.serviceprocess.model.ServiceProcessDetailModel;
import org.opencps.api.serviceprocess.model.ServiceProcessInputModel;
import org.opencps.api.serviceprocess.model.ServiceProcessResultsModel;
import org.opencps.api.serviceprocess.model.ServiceProcessSearchModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.dossiermgt.action.ServiceProcessActions;
import org.opencps.dossiermgt.action.impl.ServiceProcessActionsImpl;
import org.opencps.dossiermgt.constants.ProcessActionTerm;
import org.opencps.dossiermgt.constants.ProcessStepTerm;
import org.opencps.dossiermgt.exception.DuplicateStepNoException;
import org.opencps.dossiermgt.exception.NoSuchServiceProcessException;
import org.opencps.dossiermgt.model.ProcessAction;
import org.opencps.dossiermgt.model.ProcessSequence;
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.model.ProcessStepRole;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.model.ServiceProcessRole;
import org.opencps.dossiermgt.service.ProcessActionLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessSequenceLocalServiceUtil;
import org.opencps.dossiermgt.service.ProcessStepLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessRoleLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;
import backend.auth.api.exception.NotFoundException;

public class ServiceProcessManagementImpl implements ServiceProcessManagement {

	private static Log _log = LogFactoryUtil.getLog(ServiceProcessManagementImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public Response getServiceProcesses(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, ServiceProcessSearchModel query) {

		ServiceProcessActions actions = new ServiceProcessActionsImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		ServiceProcessResultsModel results = new ServiceProcessResultsModel();

		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (query.getEnd() == 0) {
				query.setStart(-1);
				query.setEnd(-1);
			}

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(Field.KEYWORD_SEARCH, query.getKeyword());

			Sort[] sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE,
					GetterUtil.getBoolean(query.getOrder())) };

			JSONObject jsonData = actions.getServiceProcesses(user.getUserId(), serviceContext.getCompanyId(), groupId,
					params, sorts, query.getStart(), query.getEnd(), serviceContext);

			results.setTotal(jsonData.getInt("total"));
			results.getData()
					.addAll(ServiceProcessUtils.mappingToServiceProcessData((List<Document>) jsonData.get("data")));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response addServiceProcesses(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, ServiceProcessInputModel input) {

		ServiceProcessActions actions = new ServiceProcessActionsImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

//			if (!auth.hasResource(serviceContext, ServiceProcess.class.getName(), ActionKeys.ADD_ENTRY)) {
//				throw new UnauthorizationException();
//			}

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
			
			String processNo = HtmlUtil.escape(input.getProcessNo());
			String processName = HtmlUtil.escape(input.getProcessName());
			String description = HtmlUtil.escape(input.getDescription());
			String generateDossierNo = HtmlUtil.escape(String.valueOf(input.getGenerateDossierNo()));
			String dossierNoPattern = HtmlUtil.escape(input.getDossierNoPattern());
			String generateDueDate = HtmlUtil.escape(String.valueOf(input.getGenerateDueDate()));
//			String dueDatePattern = HtmlUtil.escape(input.getDueDatePattern());
			String dueDatePattern = input.getDueDatePattern();
			String generatePassword = HtmlUtil.escape(String.valueOf(input.getGeneratePassword()));
			String directNotification = HtmlUtil.escape(String.valueOf(input.getDirectNotification()));
			String serverNo = HtmlUtil.escape(String.valueOf(input.getDirectNotification()));
			String paymentFee = HtmlUtil.escape(input.getPaymentFee());
			
			ServiceProcess serviceProcess = actions.updateServiceProcess(groupId, 0l, processNo,
					processName, description, input.getDurationCount(), input.getDurationUnit(),
					input.getCounter(), GetterUtil.getBoolean(generateDossierNo),
					dossierNoPattern, GetterUtil.getBoolean(generateDueDate),
					dueDatePattern, GetterUtil.getBoolean(generatePassword),
					GetterUtil.getBoolean(directNotification), serverNo, paymentFee,
					serviceContext);

			ServiceProcessDetailModel result = ServiceProcessUtils.mappingToDetail(serviceProcess);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response getServiceProcessDetail(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id) {

		ServiceProcessActions actions = new ServiceProcessActionsImpl();

		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			ServiceProcess serviceProcess = actions.getServiceProcessDetail(id);

			ServiceProcessDetailModel result = ServiceProcessUtils.mappingToDetail(serviceProcess);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response updateServiceProcess(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, ServiceProcessInputModel input) {
		ServiceProcessActions actions = new ServiceProcessActionsImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			String processNo = HtmlUtil.escape(input.getProcessNo());
			String processName = HtmlUtil.escape(input.getProcessName());
			String description = HtmlUtil.escape(input.getDescription());
			String generateDossierNo = HtmlUtil.escape(String.valueOf(input.getGenerateDossierNo()));
			String dossierNoPattern = HtmlUtil.escape(input.getDossierNoPattern());
			String generateDueDate = HtmlUtil.escape(String.valueOf(input.getGenerateDueDate()));
//			String dueDatePattern = HtmlUtil.escape(input.getDueDatePattern());
			String dueDatePattern = input.getDueDatePattern();
			String generatePassword = HtmlUtil.escape(String.valueOf(input.getGeneratePassword()));
			String directNotification = HtmlUtil.escape(String.valueOf(input.getDirectNotification()));
			String serverNo = HtmlUtil.escape(String.valueOf(input.getServerNo()));
			String paymentFee = HtmlUtil.escape(input.getPaymentFee());
			
			ServiceProcess serviceProcess = actions.updateServiceProcess(groupId, id, processNo,
					processName, description, input.getDurationCount(), input.getDurationUnit(),
					input.getCounter(), GetterUtil.getBoolean(generateDossierNo),
					dossierNoPattern, GetterUtil.getBoolean(generateDueDate),
					dueDatePattern, GetterUtil.getBoolean(generatePassword),
					GetterUtil.getBoolean(directNotification), serverNo, paymentFee,
					serviceContext);

			ServiceProcessDetailModel result = ServiceProcessUtils.mappingToDetail(serviceProcess);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response removeServiceProcess(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {

		ServiceProcessActions actions = new ServiceProcessActionsImpl();

		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		long userId = user.getUserId();

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
			
//			if (!auth.hasResource(serviceContext, ServiceProcess.class.getName(), ActionKeys.ADD_ENTRY)) {
//				throw new UnauthorizationException();
//			}

			ServiceProcess serviceProcess = actions.removeServiceProcess(userId, groupId, id, serviceContext);

			ServiceProcessDetailModel result = ServiceProcessUtils.mappingToDetail(serviceProcess);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getServiceProcessRoles(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id) {
		ServiceProcessActions actions = new ServiceProcessActionsImpl();

		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException("UnauthenticationException");
			}

			JSONObject jsonData = actions.getServiceProcessRoles(id);

			RoleResultsModel results = new RoleResultsModel();

			results.setTotal(jsonData.getInt("total"));

			results.getData()
					.addAll(ServiceProcessUtils.mappingToServiceRole((List<ServiceProcessRole>) jsonData.get("data")));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addServiceProcessRole(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, RoleInputModel input) {

		ServiceProcessActions actions = new ServiceProcessActionsImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		//BackendAuth auth = new BackendAuthImpl();
		try {
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException("UnauthenticationException");
//			}
//
//			if (!auth.hasResource(serviceContext, ServiceProcess.class.getName(), ActionKeys.ADD_ENTRY)) {
//				throw new UnauthorizationException("UnauthorizationException");
//			}

			ServiceProcessRole role = actions.updateServiceProcessRole(groupId, id, input.getRoleId(),
					GetterUtil.getBoolean(input.getModerator()), input.getCondition(), input.getRoleCode(),
					input.getRoleName());

			RoleInputModel result = ServiceProcessUtils.mappingToServiceRoleInput(role);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response updateServiceProcessRole(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, long roleid, RoleInputModel input) {

		ServiceProcessActions actions = new ServiceProcessActionsImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		//BackendAuth auth = new BackendAuthImpl();
		try {
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException("UnauthenticationException");
//			}
//
//			if (!auth.hasResource(serviceContext, ServiceProcess.class.getName(), ActionKeys.ADD_ENTRY)) {
//				throw new UnauthorizationException("UnauthorizationException");
//			}

			ServiceProcessRole role = actions.updateServiceProcessRole(groupId, id, roleid,
					GetterUtil.getBoolean(input.getModerator()), input.getCondition(), input.getRoleCode(),
					input.getRoleName());
			if (role != null) {
				role.setRoleName(input.getRoleName());
				ServiceProcessRoleLocalServiceUtil.updateServiceProcessRole(role);
			}

			RoleInputModel result = ServiceProcessUtils.mappingToServiceRoleInput(role);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response removeServiceProcessRole(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, long roleid) {
		ServiceProcessActions actions = new ServiceProcessActionsImpl();

		//BackendAuth auth = new BackendAuthImpl();
		try {
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException("UnauthenticationException");
//			}
//
//			if (!auth.hasResource(serviceContext, ServiceProcess.class.getName(), ActionKeys.ADD_ENTRY)) {
//				throw new UnauthorizationException("UnauthorizationException");
//			}

			ServiceProcessRole role = actions.removeServiceProcessRole(id, roleid);

			RoleInputModel result = ServiceProcessUtils.mappingToServiceRoleInput(role);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getProcessSteps(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, ProcessStepSearchModel query) {

		ServiceProcessActions actions = new ServiceProcessActionsImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (query.getEnd() == 0) {
				query.setStart(-1);
				query.setEnd(-1);
			}

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(Field.KEYWORD_SEARCH, query.getKeyword());
			params.put(ProcessStepTerm.SERVICE_PROCESS_ID, id);

			Sort[] sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE,
					GetterUtil.getBoolean(query.getOrder())) };

			ProcessStepResultsModel results = new ProcessStepResultsModel();

			JSONObject jsonData = actions.getProcessSteps(user.getUserId(), serviceContext.getCompanyId(), groupId,
					params, sorts, query.getStart(), query.getEnd(), serviceContext);

			results.setTotal(jsonData.getInt("total"));
			results.getData()
					.addAll(ServiceProcessUtils.mappingToProcessStepData((List<Document>) jsonData.get("data")));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response addProcessStep(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, ProcessStepInputModel input) {

		ServiceProcessActions actions = new ServiceProcessActionsImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

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
			ProcessStep addstep = ProcessStepLocalServiceUtil.fetchBySC_GID(input.getStepCode(), groupId, id);

			if (Validator.isNotNull(addstep)) {
				throw new DuplicateStepNoException("DuplicateStepNoException");
			}

			String stepCode = HtmlUtil.escape(input.getStepCode());
			String stepName = HtmlUtil.escape(input.getStepName());
			String sequenceNo = HtmlUtil.escape(input.getSequenceNo());
			String dossierStatus = HtmlUtil.escape(input.getDossierStatus());
			String dossierSubStatus = HtmlUtil.escape(input.getDossierSubStatus());
			String durationCount = HtmlUtil.escape(String.valueOf(input.getDurationCount()));
			String customProcessUrl = HtmlUtil.escape(input.getCustomProcessUrl());
			String stepInstruction = HtmlUtil.escape(input.getStepInstruction());
			String briefNote = HtmlUtil.escape(input.getBriefNote());
			String editable = HtmlUtil.escape(String.valueOf(input.getEditable()));
			String lockState = HtmlUtil.escape(input.getLockState());
			Integer checkInput = input.getCheckInput();
			
			ProcessStep step = actions.updateProcessStep(groupId, StringPool.BLANK, stepCode,
					stepName, id, sequenceNo, dossierStatus,
					dossierSubStatus, GetterUtil.getInteger(durationCount),
					customProcessUrl, stepInstruction, briefNote,
					GetterUtil.getBoolean(editable), lockState, checkInput, serviceContext);

			ProcessStepInputModel result = ServiceProcessUtils.mapptingToStepPOST(step);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response updateProcessStep(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String code, ProcessStepInputModel input) {
		ServiceProcessActions actions = new ServiceProcessActionsImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

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
			
			ProcessStep addstep = ProcessStepLocalServiceUtil.fetchBySC_GID(code, groupId, id);

			if (Validator.isNull(addstep)) {
				throw new DuplicateStepNoException("InvalidStepCode");
			}
			
			String stepCode = HtmlUtil.escape(input.getStepCode());
			String stepName = HtmlUtil.escape(input.getStepName());
			String sequenceNo = HtmlUtil.escape(input.getSequenceNo());
			String dossierStatus = HtmlUtil.escape(input.getDossierStatus());
			String dossierSubStatus = HtmlUtil.escape(input.getDossierSubStatus());
			String durationCount = HtmlUtil.escape(String.valueOf(input.getDurationCount()));
			String customProcessUrl = HtmlUtil.escape(input.getCustomProcessUrl());
			String stepInstruction = HtmlUtil.escape(input.getStepInstruction());
			String briefNote = HtmlUtil.escape(input.getBriefNote());
			String editable = HtmlUtil.escape(String.valueOf(input.getEditable()));
			String lockState = HtmlUtil.escape(input.getLockState());
			Integer checkInput = input.getCheckInput();
			
			ProcessStep step = actions.updateProcessStep(groupId, code, stepCode, stepName, id,
					sequenceNo, dossierStatus, dossierSubStatus,
					GetterUtil.getInteger(durationCount), customProcessUrl,
					stepInstruction, briefNote, GetterUtil.getBoolean(editable),
					lockState, checkInput, serviceContext);

			ProcessStepInputModel result = ServiceProcessUtils.mapptingToStepPOST(step);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response removeProcessStep(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String code) {
		ServiceProcessActions actions = new ServiceProcessActionsImpl();

		//BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException();
//			}
//
//			if (!auth.hasResource(serviceContext, ServiceProcess.class.getName(), ActionKeys.ADD_ENTRY)) {
//				throw new UnauthorizationException();
//			}

			ProcessStep serviceProcess = actions.deleteProcessStep(code, groupId, id);

			ProcessStepInputModel result = ServiceProcessUtils.mapptingToStepPOST(serviceProcess);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getProcessStepRoles(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String code) {

		ServiceProcessActions actions = new ServiceProcessActionsImpl();

		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException("UnauthenticationException");
			}

			ProcessStep step = ProcessStepLocalServiceUtil.fetchBySC_GID(code, groupId, id);

			long processStepId = 0;

			if (Validator.isNotNull(step))
				processStepId = step.getPrimaryKey();

			JSONObject jsonData = actions.getProcessStepRoles(processStepId);

			RoleResultsModel results = new RoleResultsModel();

			results.setTotal(jsonData.getInt("total"));

			results.getData()
					.addAll(ServiceProcessUtils.mappingToStepRole((List<ProcessStepRole>) jsonData.get("data")));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addProcessStepRole(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String code, RoleInputModel input) {

		ServiceProcessActions actions = new ServiceProcessActionsImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		//BackendAuth auth = new BackendAuthImpl();
		try {
//			if (!auth.isAuth(serviceContext)) {
//				throw new UnauthenticationException("UnauthenticationException");
//			}
//
//			if (!auth.hasResource(serviceContext, ServiceProcess.class.getName(), ActionKeys.ADD_ENTRY)) {
//				throw new UnauthorizationException("UnauthorizationException");
//			}

			ProcessStep step = ProcessStepLocalServiceUtil.fetchBySC_GID(code, groupId, id);

			long processStepId = 0;

			if (Validator.isNotNull(step))
				processStepId = step.getPrimaryKey();

			ProcessStepRole role = actions.updateProcessStepRole(processStepId, input.getRoleId(),
					GetterUtil.getBoolean(input.getModerator()), input.getCondition(), input.getRoleCode(),
					input.getRoleName());

			RoleInputModel result = ServiceProcessUtils.mappingToServiceRoleInput(role);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response updateProcessStepRole(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, String code, long roleid,
			RoleInputModel input) {

		ServiceProcessActions actions = new ServiceProcessActionsImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException("UnauthenticationException");
			}

//			if (!auth.hasResource(serviceContext, ServiceProcess.class.getName(), ActionKeys.ADD_ENTRY)) {
//				throw new UnauthorizationException("UnauthorizationException");
//			}

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
			
			ProcessStep step = ProcessStepLocalServiceUtil.fetchBySC_GID(code, groupId, id);

			long processStepId = 0;

			if (Validator.isNotNull(step))
				processStepId = step.getPrimaryKey();

			ProcessStepRole role = actions.updateProcessStepRole(processStepId, roleid,
					GetterUtil.getBoolean(input.getModerator()), input.getCondition(), input.getRoleCode(),
					input.getRoleName());

			RoleInputModel result = ServiceProcessUtils.mappingToServiceRoleInput(role);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response removeProcessStepRole(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, String code, long roleid) {
		ServiceProcessActions actions = new ServiceProcessActionsImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException("UnauthenticationException");
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

			ProcessStep step = ProcessStepLocalServiceUtil.fetchBySC_GID(code, groupId, id);

			long processStepId = 0;

			if (Validator.isNotNull(step))
				processStepId = step.getPrimaryKey();

			ProcessStepRole role = actions.deleteProcessStepRole(processStepId, roleid);

			RoleInputModel result = ServiceProcessUtils.mappingToServiceRoleInput(role);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getProcessActions(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, ProcessActionSearchModel query) {

		ServiceProcessActions actions = new ServiceProcessActionsImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (query.getEnd() == 0) {
				query.setStart(-1);
				query.setEnd(-1);
			}

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(Field.KEYWORD_SEARCH, query.getKeyword());
			params.put(ProcessActionTerm.SERVICE_PROCESS_ID, id);

			Sort[] sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE,
					GetterUtil.getBoolean(query.getOrder())) };

			ProcessActionResultsModel results = new ProcessActionResultsModel();

			JSONObject jsonData = actions.getProcessActions(user.getUserId(), serviceContext.getCompanyId(), groupId,
					params, sorts, query.getStart(), query.getEnd(), serviceContext);

			results.setTotal(jsonData.getInt("total"));
			results.getData()
					.addAll(ServiceProcessUtils.mappingToProcessActionData((List<Document>) jsonData.get("data")));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addProcessAction(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, ProcessActionInputModel input) {
		ServiceProcessActions actions = new ServiceProcessActionsImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

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
			
			ProcessActionReturnModel results;

			String preStepCode = HtmlUtil.escape(input.getPreStepCode());
			String postStepCode = HtmlUtil.escape(input.getPostStepCode());
			String autoEvent = HtmlUtil.escape(input.getAutoEvent());
			String preCondition = HtmlUtil.escape(input.getPreCondition());
			String actionCode = HtmlUtil.escape(input.getActionCode());
			String actionName = HtmlUtil.escape(input.getActionName());
			String allowAssignUser = HtmlUtil.escape(String.valueOf(input.getAllowAssignUser()));
			String assignUserId = HtmlUtil.escape(String.valueOf(input.getAssignUserId()));
			String requestPayment = HtmlUtil.escape(String.valueOf(input.getRequestPayment()));
//			String paymentFee = HtmlUtil.escape(input.getPaymentFee());
			String paymentFee = input.getPaymentFee();
			String createDossierFiles = HtmlUtil.escape(input.getCreateDossierFiles());
			String returnDossierFiles = HtmlUtil.escape(input.getReturnDossierFiles());
			String makeBriefNote = HtmlUtil.escape(input.getMakeBriefNote());
			String syncActionCode = HtmlUtil.escape(input.getSyncActionCode());
			String rollbackable = HtmlUtil.escape(String.valueOf(input.getRollbackable()));
			String configNote = HtmlUtil.escape(input.getConfigNote());
			String dossierTemplateNo = HtmlUtil.escape(input.getDossierTemplateNo());
			
/*			ProcessAction processAction = actions.updateProcessAction(groupId, 0, id, input.getPreStepCode(),
					input.getPostStepCode(), input.getAutoEvent(), input.getPreCondition(), input.getActionCode(),
					input.getActionName(), GetterUtil.getBoolean(input.getAllowAssignUser()),
					GetterUtil.getLong(input.getAssignUserId()), GetterUtil.getBoolean(input.getRequestPayment()),
					input.getPaymentFee(), input.getCreateDossierFiles(), input.getReturnDossierFiles(),
					input.getMakeBriefNote(), input.getSyncActionCode(), GetterUtil.getBoolean(input.getRollbackable()),
					serviceContext);
*/
			ProcessAction processAction = actions.updateProcessAction(groupId, 0, id, preStepCode,
					postStepCode, autoEvent, preCondition, actionCode,
					actionName, GetterUtil.getInteger(allowAssignUser),
					GetterUtil.getLong(assignUserId), GetterUtil.getInteger(requestPayment),
					paymentFee, createDossierFiles, returnDossierFiles,
					makeBriefNote, syncActionCode, GetterUtil.getBoolean(rollbackable),
					input.isCreateDossierNo(), input.iseSignature(), input.getSignatureType(), configNote,
					dossierTemplateNo, serviceContext);
			
			if (Validator.isNotNull(input.getCreateDossiers())) {
				processAction.setCreateDossiers(input.getCreateDossiers());
				processAction = ProcessActionLocalServiceUtil.updateProcessAction(processAction);
			}
			
			results = ServiceProcessUtils.mappingToActionPOST(processAction);

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	

	@Override
	public Response updateProcessAction(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, long actionid, ProcessActionInputModel input) {
		
		ServiceProcessActions actions = new ServiceProcessActionsImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

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
			
			ProcessActionReturnModel results;

/*			ProcessAction processAction = actions.updateProcessAction(groupId, actionid, id, input.getPreStepCode(),
					input.getPostStepCode(), input.getAutoEvent(), input.getPreCondition(), input.getActionCode(),
					input.getActionName(), GetterUtil.getBoolean(input.getAllowAssignUser()),
					GetterUtil.getLong(input.getAssignUserId()), GetterUtil.getBoolean(input.getRequestPayment()),
					input.getPaymentFee(), input.getCreateDossierFiles(), input.getReturnDossierFiles(),
					input.getMakeBriefNote(), input.getSyncActionCode(), GetterUtil.getBoolean(input.getRollbackable()),
					serviceContext);
*/
			ProcessAction processAction = actions.updateProcessAction(groupId, actionid, id, input.getPreStepCode(),
					input.getPostStepCode(), input.getAutoEvent(), input.getPreCondition(), input.getActionCode(),
					input.getActionName(), Integer.parseInt(input.getAllowAssignUser()),
					GetterUtil.getLong(input.getAssignUserId()), Integer.parseInt(input.getRequestPayment()),
					input.getPaymentFee(), input.getCreateDossierFiles(), input.getReturnDossierFiles(),
					input.getMakeBriefNote(), input.getSyncActionCode(), GetterUtil.getBoolean(input.getRollbackable()),
					input.isCreateDossierNo(), input.iseSignature(), input.getSignatureType(), input.getConfigNote(),
					input.getDossierTemplateNo(), serviceContext);

			if (Validator.isNotNull(input.getCreateDossiers())) {
				processAction.setCreateDossiers(input.getCreateDossiers());
				processAction = ProcessActionLocalServiceUtil.updateProcessAction(processAction);
			}
			
			results = ServiceProcessUtils.mappingToActionPOST(processAction);

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response removeProcessAction(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, long actionid) {
		ServiceProcessActions actions = new ServiceProcessActionsImpl();


		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			
//			if (!auth.hasResource(serviceContext, ProcessAction.class.getName(), ActionKeys.ADD_ENTRY)) {
//				throw new UnauthorizationException("UnauthorizationException");
//			}

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
			
			ProcessActionReturnModel results;

			ProcessAction processAction = actions.deleteProcessAction(actionid);

			results = ServiceProcessUtils.mappingToActionPOST(processAction);

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response initServiceProcesses(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext) {
		

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			
//			if (!auth.hasResource(serviceContext, ServiceProcess.class.getName(), ActionKeys.ADD_ENTRY)) {
//				throw new UnauthorizationException("UnauthorizationException");
//			}

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
			
			String results = "Init Done";

			ServiceProcessLocalServiceUtil.initServiceProcess(groupId, serviceContext);

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response cloneServiceProcesses(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, String processNo) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			
//			if (!auth.hasResource(serviceContext, ServiceProcess.class.getName(), ActionKeys.ADD_ENTRY)) {
//				throw new UnauthorizationException("UnauthorizationException");
//			}

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
			
			String results = "Clone done";

			ServiceProcessLocalServiceUtil.cloneServiceProcess(id, groupId, processNo, serviceContext);

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response getProcessSequences(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {
		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		
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
			
			List<ProcessSequence> lstSequences = ProcessSequenceLocalServiceUtil.getByServiceProcess(groupId, id);
			ProcessSequenceResultModel result = new ProcessSequenceResultModel();
			result.setTotal(lstSequences.size());
			List<ProcessSequenceOutputModel> lstModels = new ArrayList<>();
			for (ProcessSequence ps : lstSequences) {
				ProcessSequenceOutputModel model = new ProcessSequenceOutputModel();
				model.setSequenceName(ps.getSequenceName());
				model.setDurationCount(ps.getDurationCount());
				model.setSequenceNo(ps.getSequenceNo());
				model.setSequenceRole(ps.getSequenceRole());
				
				lstModels.add(model);
			}
			
			result.getData().addAll(lstModels);
			
			return Response.status(200).entity(result).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}	
	}

	@Override
	public Response addProcessSequence(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, ProcessSequenceInputModel input) {
		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		
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
			
			ServiceProcess serviceProcess = ServiceProcessLocalServiceUtil.fetchServiceProcess(id);
			
			ProcessSequence processSequence = ProcessSequenceLocalServiceUtil.addProcessSequence(serviceContext.getUserId(), groupId, serviceProcess.getServiceProcessId(), input.getSequenceNo(), input.getSequenceName(), input.getSequenceRole(), input.getDurationCount());
			
			return Response.status(200).entity(ProcessSequenceUtils.mappingDetail(processSequence)).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}	
	}

	@Override
	public Response updateProcessSequence(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, String sequenceNo,
			ProcessSequenceInputModel input) {
		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		
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
			
			ProcessSequence processSequence = ProcessSequenceLocalServiceUtil.fetchProcessSequence(id);
			
			if (processSequence != null) {
				processSequence = ProcessSequenceLocalServiceUtil.updateProcessSequence(serviceContext.getUserId(), groupId, processSequence.getServiceProcessId(), processSequence.getProcessSequenceId(), sequenceNo, input.getSequenceName(), input.getSequenceRole(), input.getDurationCount());				
			}
						
			return Response.status(200).entity(ProcessSequenceUtils.mappingDetail(processSequence)).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}	
	}

	@Override
	public Response getProcessStep(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, String code) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			ProcessStep foundStep = ProcessStepLocalServiceUtil.fetchBySC_GID(code, groupId, id);

			if (Validator.isNull(foundStep)) {
				throw new NotFoundException("InvalidStepCode");
			}
			
			ProcessStepInputModel result = ServiceProcessUtils.mapptingToStepPOST(foundStep);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}		
	}

	@Override
	public Response getProcessAction(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, long actionid) {
		ServiceProcessActions actions = new ServiceProcessActionsImpl();

		//long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			
			ProcessAction processAction = null; 
			
			try {
				processAction = actions.getProcessActionDetail(actionid);
			}
			catch (Exception e)  {
				_log.debug(e);
			}
			
			ProcessActionReturnModel results;

			results = ServiceProcessUtils.mappingToActionPOST(processAction);

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}		
	}

	@Override
	public Response getServiceProcessMermaidGraph(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id) {
		ServiceProcessActions actions = new ServiceProcessActionsImpl();

		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			long serviceProcessId = GetterUtil.getLong(id);
			
			ServiceProcess serviceProcess = null; 
			try {
				serviceProcess = actions.getServiceProcessDetail(serviceProcessId);
			}
			catch (NoSuchServiceProcessException e) {
				_log.debug(e);
			}
			if (serviceProcess == null) {
				serviceProcess = ServiceProcessLocalServiceUtil.getByG_PNO(groupId, id);
			}
			if (serviceProcess != null) {
				List<ProcessStep> lstSteps = ProcessStepLocalServiceUtil.getProcessStepbyServiceProcessId(serviceProcess.getServiceProcessId());
				StringBuilder result = new StringBuilder();
				result.append("graph TD\n");
				result.append("0((Bắt đầu))\n");
				for (ProcessStep ps : lstSteps) {
					result.append(ps.getStepCode());
					result.append("(\"[");
					result.append(ps.getStepCode());
					result.append("] ");
					result.append(ps.getStepName());
					result.append("\")\n");
				}
				List<ProcessAction> lstActions = ProcessActionLocalServiceUtil.getProcessActionbyServiceProcessId(serviceProcess.getServiceProcessId());
				for (ProcessAction pa : lstActions) {
					if ("".equals(pa.getPreStepCode())) {
						result.append("0");
					}
					else {
						result.append(pa.getPreStepCode());
					}
					if ("listener".equals(pa.getAutoEvent()) || "timmer".equals(pa.getAutoEvent())) {
						result.append("-.->|\"[");
						result.append(pa.getActionCode());
						result.append("] ");
						result.append(pa.getActionName());
						result.append("\"|");
					}
					else {
						result.append("-->|\"[");
						result.append(pa.getActionCode());
						result.append("] ");
						result.append(pa.getActionName());
						result.append("\"|");						
					}
					if ("".equals(pa.getPostStepCode())) {
						result.append("1{Quay lại}");
					}
					else {
						result.append(pa.getPostStepCode());
					}
					result.append("\n");
				}
				return Response.status(200).entity(result.toString()).build();				
			}
			else {
				return Response.status(200).entity(StringPool.BLANK).build();
			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}		
	}

	@Override
	public Response deleteProcessSequence(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, String sequenceNo) {
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
			
			ServiceProcess serviceProcess = ServiceProcessLocalServiceUtil.fetchServiceProcess(id);
			if (serviceProcess != null) {
				ProcessSequence processSequence = ProcessSequenceLocalServiceUtil.findBySID_SNO(serviceProcess.getGroupId(), serviceProcess.getServiceProcessId(), sequenceNo);
				
				if (processSequence != null) {
					processSequence = ProcessSequenceLocalServiceUtil.deleteProcessSequence(processSequence.getProcessSequenceId());
				}
							
				return Response.status(200).entity(ProcessSequenceUtils.mappingDetail(processSequence)).build();				
			}
			else {
				throw new NotFoundException("Service process not found");
			}
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}			
	}

}
