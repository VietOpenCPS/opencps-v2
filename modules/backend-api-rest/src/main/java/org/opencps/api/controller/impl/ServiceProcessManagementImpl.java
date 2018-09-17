package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
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
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.auth.api.keys.ActionKeys;
import org.opencps.dossiermgt.action.ServiceProcessActions;
import org.opencps.dossiermgt.action.impl.ServiceProcessActionsImpl;
import org.opencps.dossiermgt.constants.ProcessActionTerm;
import org.opencps.dossiermgt.constants.ProcessStepTerm;
import org.opencps.dossiermgt.exception.DuplicateStepNoException;
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

import backend.auth.api.exception.BusinessExceptionImpl;

public class ServiceProcessManagementImpl implements ServiceProcessManagement {

//	private static Log _log = LogFactoryUtil.getLog(ServiceProcessManagementImpl.class);

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

			if (!auth.hasResource(serviceContext, ServiceProcess.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			ServiceProcess serviceProcess = actions.updateServiceProcess(groupId, 0l, input.getProcessNo(),
					input.getProcessName(), input.getDescription(), input.getDurationCount(), input.getDurationUnit(),
					input.getCounter(), GetterUtil.getBoolean(input.getGenerateDossierNo()),
					input.getDossierNoPattern(), GetterUtil.getBoolean(input.getGenerateDueDate()),
					input.getDueDatePattern(), GetterUtil.getBoolean(input.getGeneratePassword()),
					GetterUtil.getBoolean(input.getDirectNotification()), input.getServerNo(), input.getPaymentFee(),
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

			if (!auth.hasResource(serviceContext, ServiceProcess.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			ServiceProcess serviceProcess = actions.updateServiceProcess(groupId, id, input.getProcessNo(),
					input.getProcessName(), input.getDescription(), input.getDurationCount(), input.getDurationUnit(),
					input.getCounter(), GetterUtil.getBoolean(input.getGenerateDossierNo()),
					input.getDossierNoPattern(), GetterUtil.getBoolean(input.getGenerateDueDate()),
					input.getDueDatePattern(), GetterUtil.getBoolean(input.getGeneratePassword()),
					GetterUtil.getBoolean(input.getDirectNotification()), input.getServerNo(), input.getPaymentFee(),
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

			if (!auth.hasResource(serviceContext, ServiceProcess.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

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

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException("UnauthenticationException");
			}

			if (!auth.hasResource(serviceContext, ServiceProcess.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException("UnauthorizationException");
			}

			ServiceProcessRole role = actions.updateServiceProcessRole(groupId, id, input.getRoleId(),
					GetterUtil.getBoolean(input.getModerator()), input.getCondition());

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

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException("UnauthenticationException");
			}

			if (!auth.hasResource(serviceContext, ServiceProcess.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException("UnauthorizationException");
			}

			ServiceProcessRole role = actions.updateServiceProcessRole(groupId, id, roleid,
					GetterUtil.getBoolean(input.getModerator()), input.getCondition());

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

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException("UnauthenticationException");
			}

			if (!auth.hasResource(serviceContext, ServiceProcess.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException("UnauthorizationException");
			}

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

			if (!auth.hasResource(serviceContext, ServiceProcess.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			ProcessStep addstep = ProcessStepLocalServiceUtil.fetchBySC_GID(input.getStepCode(), groupId, id);

			if (Validator.isNotNull(addstep)) {
				throw new DuplicateStepNoException("DuplicateStepNoException");
			}

			ProcessStep step = actions.updateProcessStep(groupId, StringPool.BLANK, input.getStepCode(),
					input.getStepName(), id, input.getSequenceNo(), input.getDossierStatus(),
					input.getDossierSubStatus(), GetterUtil.getInteger(input.getDurationCount()),
					input.getCustomProcessUrl(), input.getStepInstruction(), input.getBriefNote(),
					GetterUtil.getBoolean(input.getEditable()), input.getLockState(), serviceContext);

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

			if (!auth.hasResource(serviceContext, ServiceProcess.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			ProcessStep addstep = ProcessStepLocalServiceUtil.fetchBySC_GID(code, groupId, id);

			if (Validator.isNull(addstep)) {
				throw new DuplicateStepNoException("InvalidStepCode");
			}
			
			ProcessStep step = actions.updateProcessStep(groupId, code, input.getStepCode(), input.getStepName(), id,
					input.getSequenceNo(), input.getDossierStatus(), input.getDossierSubStatus(),
					GetterUtil.getInteger(input.getDurationCount()), input.getCustomProcessUrl(),
					input.getStepInstruction(), input.getBriefNote(), GetterUtil.getBoolean(input.getEditable()),
					input.getLockState(), serviceContext);

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

		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (!auth.hasResource(serviceContext, ServiceProcess.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

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

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException("UnauthenticationException");
			}

			if (!auth.hasResource(serviceContext, ServiceProcess.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException("UnauthorizationException");
			}

			ProcessStep step = ProcessStepLocalServiceUtil.fetchBySC_GID(code, groupId, id);

			long processStepId = 0;

			if (Validator.isNotNull(step))
				processStepId = step.getPrimaryKey();

			ProcessStepRole role = actions.updateProcessStepRole(processStepId, input.getRoleId(),
					GetterUtil.getBoolean(input.getModerator()), input.getCondition());

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

			if (!auth.hasResource(serviceContext, ServiceProcess.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException("UnauthorizationException");
			}

			ProcessStep step = ProcessStepLocalServiceUtil.fetchBySC_GID(code, groupId, id);

			long processStepId = 0;

			if (Validator.isNotNull(step))
				processStepId = step.getPrimaryKey();

			ProcessStepRole role = actions.updateProcessStepRole(processStepId, roleid,
					GetterUtil.getBoolean(input.getModerator()), input.getCondition());

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

			if (!auth.hasResource(serviceContext, ServiceProcess.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException("UnauthorizationException");
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
			
			if (!auth.hasResource(serviceContext, ProcessAction.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException("UnauthorizationException");
			}


			ProcessActionReturnModel results;

/*			ProcessAction processAction = actions.updateProcessAction(groupId, 0, id, input.getPreStepCode(),
					input.getPostStepCode(), input.getAutoEvent(), input.getPreCondition(), input.getActionCode(),
					input.getActionName(), GetterUtil.getBoolean(input.getAllowAssignUser()),
					GetterUtil.getLong(input.getAssignUserId()), GetterUtil.getBoolean(input.getRequestPayment()),
					input.getPaymentFee(), input.getCreateDossierFiles(), input.getReturnDossierFiles(),
					input.getMakeBriefNote(), input.getSyncActionCode(), GetterUtil.getBoolean(input.getRollbackable()),
					serviceContext);
*/
			ProcessAction processAction = actions.updateProcessAction(groupId, 0, id, input.getPreStepCode(),
					input.getPostStepCode(), input.getAutoEvent(), input.getPreCondition(), input.getActionCode(),
					input.getActionName(), GetterUtil.getInteger(input.getAllowAssignUser()),
					GetterUtil.getLong(input.getAssignUserId()), GetterUtil.getInteger(input.getRequestPayment()),
					input.getPaymentFee(), input.getCreateDossierFiles(), input.getReturnDossierFiles(),
					input.getMakeBriefNote(), input.getSyncActionCode(), GetterUtil.getBoolean(input.getRollbackable()),
					input.isCreateDossierNo(), input.iseSignature(), input.getConfigNote(),
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
	public Response updateProcessAction(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, long actionid, ProcessActionInputModel input) {
		
		ServiceProcessActions actions = new ServiceProcessActionsImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}
			
			if (!auth.hasResource(serviceContext, ProcessAction.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException("UnauthorizationException");
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
					input.isCreateDossierNo(), input.iseSignature(), input.getConfigNote(),
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
			
			if (!auth.hasResource(serviceContext, ProcessAction.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException("UnauthorizationException");
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
			
			if (!auth.hasResource(serviceContext, ServiceProcess.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException("UnauthorizationException");
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
			
			if (!auth.hasResource(serviceContext, ServiceProcess.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException("UnauthorizationException");
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
			
			if (!auth.hasResource(serviceContext, ProcessSequence.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException("UnauthorizationException");
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
			
			if (!auth.hasResource(serviceContext, ProcessSequence.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException("UnauthorizationException");
			}

			ServiceProcess serviceProcess = ServiceProcessLocalServiceUtil.fetchServiceProcess(id);
			
			ProcessSequence processSequence = ProcessSequenceLocalServiceUtil.addProcessSequence(serviceContext.getUserId(), groupId, serviceProcess.getServiceProcessId(), input.getSequenceNo(), input.getSequenceName(), input.getDurationCount());
			
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
			
			if (!auth.hasResource(serviceContext, ProcessSequence.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException("UnauthorizationException");
			}

			ProcessSequence processSequence = ProcessSequenceLocalServiceUtil.fetchProcessSequence(id);
			
			if (processSequence != null) {
				processSequence = ProcessSequenceLocalServiceUtil.updateProcessSequence(serviceContext.getUserId(), groupId, processSequence.getServiceProcessId(), processSequence.getProcessSequenceId(), sequenceNo, input.getSequenceName(), input.getDurationCount());				
			}
						
			return Response.status(200).entity(ProcessSequenceUtils.mappingDetail(processSequence)).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}	
	}

}
