package org.opencps.api.controller.impl;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.opencps.api.controller.ServiceInfoManagement;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.controller.util.ApplicantUtils;
import org.opencps.api.controller.util.ServiceFileTemplateUtils;
import org.opencps.api.controller.util.ServiceInfoUtils;
import org.opencps.api.serviceinfo.model.FileTemplateModel;
import org.opencps.api.serviceinfo.model.FileTemplateResultsModel;
import org.opencps.api.serviceinfo.model.ServiceInfoDetailModel;
import org.opencps.api.serviceinfo.model.ServiceInfoInputModel;
import org.opencps.api.serviceinfo.model.ServiceInfoModel;
import org.opencps.api.serviceinfo.model.ServiceInfoResultsModel;
import org.opencps.api.serviceinfo.model.ServiceInfoSearchModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.auth.api.keys.ActionKeys;
import org.opencps.dossiermgt.action.ServiceInfoActions;
import org.opencps.dossiermgt.action.impl.ServiceInfoActionsImpl;
import org.opencps.dossiermgt.exception.DuplicateServiceCodeException;
import org.opencps.dossiermgt.exception.RequiredAdministrationCodeException;
import org.opencps.dossiermgt.exception.RequiredServiceCodeException;
import org.opencps.dossiermgt.exception.RequiredServiceNameException;
import org.opencps.dossiermgt.model.ServiceFileTemplate;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.service.ServiceFileTemplateLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceInfoLocalServiceUtil;
import org.opencps.exception.model.ExceptionModel;

import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

public class ServiceInfoImpl implements ServiceInfoManagement {

	@Override
	public Response getServiceInfos(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, ServiceInfoSearchModel query) {

		ServiceInfoActions actions = new ServiceInfoActionsImpl();

		ServiceInfoResultsModel results = new ServiceInfoResultsModel();

		try {
			if (query.getEnd() == 0) {

				query.setStart(-1);

				query.setEnd(-1);

			}

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(Field.KEYWORD_SEARCH, query.getKeyword());

			Sort[] sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE,
					Boolean.getBoolean(query.getOrder())) };

			JSONObject jsonData = actions.getServiceInfos(serviceContext.getUserId(), serviceContext.getCompanyId(),
					groupId, params, sorts, query.getStart(), query.getEnd(), serviceContext);

			results.setTotal(jsonData.getInt("total"));
			results.getData()
					.addAll(ServiceInfoUtils.mappingToServiceInfoResultModel((List<Document>) jsonData.get("data")));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(404).entity(error).build();
		}

	}

	@Override
	public Response addServiceInfo(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user,
			@Context ServiceContext serviceContext, ServiceInfoInputModel input) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		long userId = serviceContext.getUserId();

		BackendAuth auth = new BackendAuthImpl();

		ServiceInfoActions actions = new ServiceInfoActionsImpl();

		ServiceInfoInputModel serviceInfoInput = new ServiceInfoInputModel();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (!auth.hasResource(serviceContext, ServiceInfo.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			ServiceInfo serviceInfo = actions.updateServiceInfo(userId, groupId, input.getServiceInfoId(),
					input.getServiceCode(), input.getServiceName(), input.getProcessText(), input.getMethodText(),
					input.getDossierText(), input.getConditionText(), input.getDurationText(), input.getApplicantText(),
					input.getResultText(), input.getRegularText(), input.getFeeText(), input.getAdministrationCode(),
					input.getDomainCode(), input.getMaxLevel(), GetterUtil.getBoolean(input.getPublic()),
					serviceContext);

			serviceInfoInput = ServiceInfoUtils.mappingToServiceInfoInputModel(serviceInfo);

			return Response.status(200).entity(serviceInfoInput).build();

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

					if (e instanceof RequiredServiceCodeException) {
						error.setMessage("RequiredServiceCodeException");
						error.setCode(HttpURLConnection.HTTP_NOT_ACCEPTABLE);
						error.setDescription("RequiredServiceCodeException");

						return Response.status(HttpURLConnection.HTTP_NOT_ACCEPTABLE).entity(error).build();

					} else if (e instanceof RequiredServiceNameException) {
						error.setMessage("RequiredServiceNameException");
						error.setCode(HttpURLConnection.HTTP_NOT_ACCEPTABLE);
						error.setDescription("RequiredServiceNameException");

						return Response.status(HttpURLConnection.HTTP_NOT_ACCEPTABLE).entity(error).build();

					} else if (e instanceof RequiredAdministrationCodeException) {
						error.setMessage("RequiredAdministrationCodeException");
						error.setCode(HttpURLConnection.HTTP_NOT_ACCEPTABLE);
						error.setDescription("RequiredAdministrationCodeException");

						return Response.status(HttpURLConnection.HTTP_NOT_ACCEPTABLE).entity(error).build();

					} else if (e instanceof DuplicateServiceCodeException) {
						error.setMessage("DuplicateServiceCodeException");
						error.setCode(HttpURLConnection.HTTP_NOT_ACCEPTABLE);
						error.setDescription("DuplicateServiceCodeException");

						return Response.status(HttpURLConnection.HTTP_NOT_ACCEPTABLE).entity(error).build();

					} else {
						error.setMessage(" Internal Server Error.");
						error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
						error.setDescription(" Internal Server Error.");

						return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();
					}
				}
			}
		}

	}

	@Override
	public Response getDetailServiceInfo(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user, @Context ServiceContext serviceContext, String id) {
		ServiceInfoActions actions = new ServiceInfoActionsImpl();

		ServiceInfoDetailModel results = new ServiceInfoDetailModel();

		try {
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			ServiceInfo serviceInfo = null;

			serviceInfo = actions.getByCode(id, groupId);

			if (Validator.isNull(serviceInfo)) {
				serviceInfo = actions.getById(GetterUtil.getLong(id));
			}

			if (Validator.isNull(serviceInfo)) {
				throw new Exception();
			} else {
				results = ServiceInfoUtils.mappingToServiceInfoDetailModel(serviceInfo);
			}

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(404).entity(error).build();
		}
	}

	@Override
	public Response updateServiceInfo(@Context HttpServletRequest request, @Context HttpHeaders header,
			@Context Company company, @Context Locale locale, @Context User user, @Context ServiceContext serviceContext, long id,
			ServiceInfoInputModel input) {
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		BackendAuth auth = new BackendAuthImpl();

		ServiceInfoActions actions = new ServiceInfoActionsImpl();

		ServiceInfoInputModel serviceInfoInput = new ServiceInfoInputModel();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (!auth.hasResource(serviceContext, ServiceInfo.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			ServiceInfo serviceInfo = actions.updateServiceInfo(user.getUserId(), groupId, input.getServiceInfoId(),
					input.getServiceCode(), input.getServiceName(), input.getProcessText(), input.getMethodText(),
					input.getDossierText(), input.getConditionText(), input.getDurationText(), input.getApplicantText(),
					input.getResultText(), input.getRegularText(), input.getFeeText(), input.getAdministrationCode(),
					input.getDomainCode(), input.getMaxLevel(), GetterUtil.getBoolean(input.getPublic()),
					serviceContext);

			serviceInfoInput = ServiceInfoUtils.mappingToServiceInfoInputModel(serviceInfo);

			return Response.status(200).entity(serviceInfoInput).build();

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

					if (e instanceof RequiredServiceCodeException) {
						error.setMessage("RequiredServiceCodeException");
						error.setCode(HttpURLConnection.HTTP_NOT_ACCEPTABLE);
						error.setDescription("RequiredServiceCodeException");

						return Response.status(HttpURLConnection.HTTP_NOT_ACCEPTABLE).entity(error).build();

					} else if (e instanceof RequiredServiceNameException) {
						error.setMessage("RequiredServiceNameException");
						error.setCode(HttpURLConnection.HTTP_NOT_ACCEPTABLE);
						error.setDescription("RequiredServiceNameException");

						return Response.status(HttpURLConnection.HTTP_NOT_ACCEPTABLE).entity(error).build();

					} else if (e instanceof RequiredAdministrationCodeException) {
						error.setMessage("RequiredAdministrationCodeException");
						error.setCode(HttpURLConnection.HTTP_NOT_ACCEPTABLE);
						error.setDescription("RequiredAdministrationCodeException");

						return Response.status(HttpURLConnection.HTTP_NOT_ACCEPTABLE).entity(error).build();

					} else if (e instanceof DuplicateServiceCodeException) {
						error.setMessage("DuplicateServiceCodeException");
						error.setCode(HttpURLConnection.HTTP_NOT_ACCEPTABLE);
						error.setDescription("DuplicateServiceCodeException");

						return Response.status(HttpURLConnection.HTTP_NOT_ACCEPTABLE).entity(error).build();

					} else {
						error.setMessage(" Internal Server Error.");
						error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
						error.setDescription(" Internal Server Error.");

						return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(error).build();
					}
				}
			}
		}

	}

	@Override
	public Response deleteServiceInfo(HttpServletRequest request, HttpHeaders header, ServiceContext serviceContext,
			String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getFileTemplatesOfServiceInfo(HttpServletRequest request, HttpHeaders header,
			ServiceContext serviceContext, String id) {

		FileTemplateResultsModel result = null;
		ExceptionModel exception = new ExceptionModel();

		long serviceInfoId = GetterUtil.getLong(id);

		if (serviceInfoId > 0) {
			try {
				int total = ServiceFileTemplateLocalServiceUtil.countByServiceInfoId(serviceInfoId);

				List<ServiceFileTemplate> serviceFileTemplates = ServiceFileTemplateLocalServiceUtil
						.getByServiceInfoId(serviceInfoId);

				List<FileTemplateResultModel> fileTemplateResultsModel = ServiceFileTemplateUtils
						.getFileTemplateResultsModel(serviceFileTemplates);

				result = new FileTemplateResultsModel();
				result.setTotal(total);
				result.getData().addAll(fileTemplateResultsModel);
			} catch (Exception e) {
				_log.error(e);

				exception.setCode(Status.BAD_REQUEST.getStatusCode());
				exception.setDescription(e.getClass().getName());
				exception.setMessage(e.getClass().getName());
			}
		}

		if (result != null) {
			return Response.ok(result).build();
		} else {
			return Response.status(exception.getCode()).entity(exception).build();
		}
	}

	@Override
	public Response addFileTemplateToServiceInfo(HttpServletRequest request, HttpHeaders header,
			ServiceContext serviceContext, Attachment file, String id, String fileTemplateNo, String templateName) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		long userId = serviceContext.getUserId();

		ServiceFileTemplate serviceFileTemplate = null;
		ExceptionModel exception = new ExceptionModel();

		try {

			String fileName = file.getContentDisposition().getParameter("filename");
			InputStream is = file.getDataHandler().getInputStream();

			_log.info("===fileName===" + fileName);

			serviceFileTemplate = ServiceFileTemplateLocalServiceUtil.addServiceFileTemplate(userId, groupId,
					DLFolderConstants.DEFAULT_PARENT_FOLDER_ID, GetterUtil.getLong(id), fileTemplateNo, templateName,
					fileName, is, serviceContext);
		} catch (Exception e) {
			_log.error(e);

			exception.setCode(Status.BAD_REQUEST.getStatusCode());
			exception.setDescription(e.getClass().getName());
			exception.setMessage(e.getClass().getName());
		}

		if (serviceFileTemplate != null) {
			FileTemplateModel fileTemplateModel = ServiceFileTemplateUtils.getFileTemplateModel(serviceFileTemplate);
			return Response.ok(fileTemplateModel).build();
		} else {
			return Response.status(exception.getCode()).entity(exception).build();
		}
	}

	@Override
	public Response downloadFileTemplateOfServiceInfo(HttpServletRequest request, HttpHeaders header,
			ServiceContext serviceContext, String id, String templateNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response deleteFileTemplateOfServiceInfo(HttpServletRequest request, HttpHeaders header,
			ServiceContext serviceContext, String id, String templateNo) {
		// TODO Auto-generated method stub
		return null;
	}

	private static Log _log = LogFactoryUtil.getLog(ServiceInfoImpl.class);

}
