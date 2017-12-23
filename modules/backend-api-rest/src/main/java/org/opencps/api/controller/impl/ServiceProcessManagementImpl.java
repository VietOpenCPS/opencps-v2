package org.opencps.api.controller.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.opencps.api.controller.ServiceProcessManagement;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.controller.util.ServiceProcessUtils;
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
import org.opencps.dossiermgt.model.ProcessStep;
import org.opencps.dossiermgt.model.ProcessStepRole;
import org.opencps.dossiermgt.model.ServiceProcess;
import org.opencps.dossiermgt.model.ServiceProcessRole;
import org.opencps.dossiermgt.service.ProcessStepLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceProcessLocalServiceUtil;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

public class ServiceProcessManagementImpl implements ServiceProcessManagement {

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
			ErrorMsg error = new ErrorMsg();

			error.setMessage("Content not found!");
			error.setCode(404);
			error.setDescription(e.getMessage());

			return Response.status(404).entity(error).build();
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
					GetterUtil.getBoolean(input.getDirectNotification()), input.getServerNo(), serviceContext);

			ServiceProcessDetailModel result = ServiceProcessUtils.mappingToDetail(serviceProcess);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			ErrorMsg error = new ErrorMsg();

			if (e instanceof UnauthenticationException) {
				error.setMessage("Non-Authoritative Information.");
				error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
				error.setDescription("Non-Authoritative Information.");

				return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
			} else {
				if (e instanceof UnauthorizationException) {
					error.setMessage("Unauthorized.");
					error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
					error.setDescription("Unauthorized.");

					return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(error).build();

				} else {

					error.setMessage("Internal Server Error");
					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
					error.setDescription(e.getMessage());

					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();

				}
			}
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
			ErrorMsg error = new ErrorMsg();

			error.setMessage("Content not found!");
			error.setCode(404);
			error.setDescription(e.getMessage());

			return Response.status(404).entity(error).build();
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
					GetterUtil.getBoolean(input.getDirectNotification()), input.getServerNo(), serviceContext);

			ServiceProcessDetailModel result = ServiceProcessUtils.mappingToDetail(serviceProcess);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			ErrorMsg error = new ErrorMsg();

			if (e instanceof UnauthenticationException) {
				error.setMessage("Non-Authoritative Information.");
				error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
				error.setDescription("Non-Authoritative Information.");

				return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
			} else {
				if (e instanceof UnauthorizationException) {
					error.setMessage("Unauthorized.");
					error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
					error.setDescription("Unauthorized.");

					return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(error).build();

				} else {

					error.setMessage("Internal Server Error");
					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
					error.setDescription(e.getMessage());

					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();

				}
			}
		}

	}

	@Override
	public Response removeServiceProcess(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {

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

			ServiceProcess serviceProcess = actions.removeServiceProcess(id, groupId);

			ServiceProcessDetailModel result = ServiceProcessUtils.mappingToDetail(serviceProcess);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			ErrorMsg error = new ErrorMsg();

			error.setMessage("Content not found!");
			error.setCode(404);
			error.setDescription(e.getMessage());

			return Response.status(404).entity(error).build();
		}

	}

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
			ErrorMsg error = new ErrorMsg();

			error.setMessage("Content not found!");
			error.setCode(404);
			error.setDescription(e.getMessage());

			return Response.status(404).entity(error).build();
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
			ErrorMsg error = new ErrorMsg();

			if (e instanceof UnauthenticationException) {
				error.setMessage("Non-Authoritative Information.");
				error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
				error.setDescription("Non-Authoritative Information.");

				return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
			} else {
				if (e instanceof UnauthorizationException) {
					error.setMessage("Unauthorized.");
					error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
					error.setDescription("Unauthorized.");

					return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(error).build();

				} else {

					error.setMessage("Internal Server Error");
					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
					error.setDescription(e.getMessage());

					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();

				}
			}
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
			ErrorMsg error = new ErrorMsg();

			if (e instanceof UnauthenticationException) {
				error.setMessage("Non-Authoritative Information.");
				error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
				error.setDescription("Non-Authoritative Information.");

				return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
			} else {
				if (e instanceof UnauthorizationException) {
					error.setMessage("Unauthorized.");
					error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
					error.setDescription("Unauthorized.");

					return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(error).build();

				} else {

					error.setMessage("Internal Server Error");
					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
					error.setDescription(e.getMessage());

					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();

				}
			}
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
			ErrorMsg error = new ErrorMsg();

			if (e instanceof UnauthenticationException) {
				error.setMessage("Non-Authoritative Information.");
				error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
				error.setDescription("Non-Authoritative Information.");

				return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
			} else {
				if (e instanceof UnauthorizationException) {
					error.setMessage("Unauthorized.");
					error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
					error.setDescription("Unauthorized.");

					return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(error).build();

				} else {

					error.setMessage("Internal Server Error");
					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
					error.setDescription(e.getMessage());

					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();

				}
			}
		}

	}

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
			ErrorMsg error = new ErrorMsg();

			error.setMessage("Content not found!");
			error.setCode(404);
			error.setDescription(e.getMessage());

			return Response.status(404).entity(error).build();
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

			ProcessStep step = actions.updateProcessStep(groupId, StringPool.BLANK, input.getStepCode(), input.getStepName(), id,
					input.getSequenceNo(), input.getDossierStatus(), input.getDossierSubStatus(),
					GetterUtil.getInteger(input.getDurationCount()), input.getCustomProcessUrl(),
					input.getStepInstruction(), input.getBriefNote(), GetterUtil.getBoolean(input.getEditable()), serviceContext);

			ProcessStepInputModel result = ServiceProcessUtils.mapptingToStepPOST(step);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			ErrorMsg error = new ErrorMsg();

			if (e instanceof UnauthenticationException) {
				error.setMessage("Non-Authoritative Information.");
				error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
				error.setDescription("Non-Authoritative Information.");

				return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
			} else {
				if (e instanceof UnauthorizationException) {
					error.setMessage("Unauthorized.");
					error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
					error.setDescription("Unauthorized.");

					return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(error).build();

				} else {

					error.setMessage("Internal Server Error");
					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
					error.setDescription(e.getMessage());

					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();

				}
			}
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
			
			ProcessStep step = actions.updateProcessStep(groupId, code, input.getStepCode(), input.getStepName(), id, input.getSequenceNo(),
					input.getDossierStatus(), input.getDossierSubStatus(),
					GetterUtil.getInteger(input.getDurationCount()), input.getCustomProcessUrl(),
					input.getStepInstruction(), input.getBriefNote(), GetterUtil.getBoolean(input.getEditable()), serviceContext);

			ProcessStepInputModel result = ServiceProcessUtils.mapptingToStepPOST(step);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			ErrorMsg error = new ErrorMsg();

			if (e instanceof UnauthenticationException) {
				error.setMessage("Non-Authoritative Information.");
				error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
				error.setDescription("Non-Authoritative Information.");

				return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
			} else {
				if (e instanceof UnauthorizationException) {
					error.setMessage("Unauthorized.");
					error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
					error.setDescription("Unauthorized.");

					return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(error).build();

				} else {

					error.setMessage("Internal Server Error");
					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
					error.setDescription(e.getMessage());

					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();

				}
			}
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
			ErrorMsg error = new ErrorMsg();

			error.setMessage("Content not found!");
			error.setCode(404);
			error.setDescription(e.getMessage());

			return Response.status(404).entity(error).build();
		}

	}

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
			ErrorMsg error = new ErrorMsg();

			error.setMessage("Content not found!");
			error.setCode(404);
			error.setDescription(e.getMessage());

			return Response.status(404).entity(error).build();
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
			ErrorMsg error = new ErrorMsg();

			if (e instanceof UnauthenticationException) {
				error.setMessage("Non-Authoritative Information.");
				error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
				error.setDescription("Non-Authoritative Information.");

				return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
			} else {
				if (e instanceof UnauthorizationException) {
					error.setMessage("Unauthorized.");
					error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
					error.setDescription("Unauthorized.");

					return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(error).build();

				} else {

					error.setMessage("Internal Server Error");
					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
					error.setDescription(e.getMessage());

					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();

				}
			}
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
			ErrorMsg error = new ErrorMsg();

			if (e instanceof UnauthenticationException) {
				error.setMessage("Non-Authoritative Information.");
				error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
				error.setDescription("Non-Authoritative Information.");

				return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
			} else {
				if (e instanceof UnauthorizationException) {
					error.setMessage("Unauthorized.");
					error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
					error.setDescription("Unauthorized.");

					return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(error).build();

				} else {

					error.setMessage("Internal Server Error");
					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
					error.setDescription(e.getMessage());

					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();

				}
			}
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
			ErrorMsg error = new ErrorMsg();

			if (e instanceof UnauthenticationException) {
				error.setMessage("Non-Authoritative Information.");
				error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
				error.setDescription("Non-Authoritative Information.");

				return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(error).build();
			} else {
				if (e instanceof UnauthorizationException) {
					error.setMessage("Unauthorized.");
					error.setCode(HttpURLConnection.HTTP_NOT_AUTHORITATIVE);
					error.setDescription("Unauthorized.");

					return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).entity(error).build();

				} else {

					error.setMessage("Internal Server Error");
					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
					error.setDescription(e.getMessage());

					return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();

				}
			}
		}
	}

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
			ErrorMsg error = new ErrorMsg();

			error.setMessage("Content not found!");
			error.setCode(404);
			error.setDescription(e.getMessage());

			return Response.status(404).entity(error).build();
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


			ProcessActionReturnModel results = new ProcessActionReturnModel();

			ProcessAction processAction = actions.updateProcessAction(groupId, 0, id, input.getPreStepCode(),
					input.getPostStepCode(), input.getAutoEvent(), input.getPreCondition(), input.getActionCode(),
					input.getActionName(), GetterUtil.getBoolean(input.getAllowAssignUser()),
					GetterUtil.getLong(input.getAssignUserId()), GetterUtil.getBoolean(input.getRequestPayment()),
					input.getPaymentFee(), input.getCreateDossierFiles(), input.getReturnDossierFiles(),
					input.getMakeBriefNote(), input.getSyncActionCode(), GetterUtil.getBoolean(input.getRollbackable()),
					serviceContext);

			results = ServiceProcessUtils.mappingToActionPOST(processAction);

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			ErrorMsg error = new ErrorMsg();

			error.setMessage("Content not found!");
			error.setCode(404);
			error.setDescription(e.getMessage());

			return Response.status(404).entity(error).build();
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

			ProcessActionReturnModel results = new ProcessActionReturnModel();

			ProcessAction processAction = actions.updateProcessAction(groupId, actionid, id, input.getPreStepCode(),
					input.getPostStepCode(), input.getAutoEvent(), input.getPreCondition(), input.getActionCode(),
					input.getActionName(), GetterUtil.getBoolean(input.getAllowAssignUser()),
					GetterUtil.getLong(input.getAssignUserId()), GetterUtil.getBoolean(input.getRequestPayment()),
					input.getPaymentFee(), input.getCreateDossierFiles(), input.getReturnDossierFiles(),
					input.getMakeBriefNote(), input.getSyncActionCode(), GetterUtil.getBoolean(input.getRollbackable()),
					serviceContext);

			results = ServiceProcessUtils.mappingToActionPOST(processAction);

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			ErrorMsg error = new ErrorMsg();

			error.setMessage("Content not found!");
			error.setCode(404);
			error.setDescription(e.getMessage());

			return Response.status(404).entity(error).build();
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

			ProcessActionReturnModel results = new ProcessActionReturnModel();

			ProcessAction processAction = actions.deleteProcessAction(actionid);

			results = ServiceProcessUtils.mappingToActionPOST(processAction);

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			ErrorMsg error = new ErrorMsg();

			error.setMessage("Content not found!");
			error.setCode(404);
			error.setDescription(e.getMessage());

			return Response.status(404).entity(error).build();
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
			ErrorMsg error = new ErrorMsg();

			error.setMessage("Content not found!");
			error.setCode(404);
			error.setDescription(e.getMessage());

			return Response.status(404).entity(error).build();
		}
	}

	@Override
	public Response cloneServiceProcesses(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id) {
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

			ServiceProcessLocalServiceUtil.cloneServiceProcess(id, groupId, serviceContext);

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			ErrorMsg error = new ErrorMsg();

			error.setMessage("Content not found!");
			error.setCode(404);
			error.setDescription(e.getMessage());

			return Response.status(404).entity(error).build();
		}

	}

}
