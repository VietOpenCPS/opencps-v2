package org.opencps.api.controller.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.opencps.api.controller.ServiceInfoManagement;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.controller.util.ServiceInfoUtils;
import org.opencps.api.serviceinfo.model.FileTemplateModel;
import org.opencps.api.serviceinfo.model.FileTemplateResultsModel;
import org.opencps.api.serviceinfo.model.ServiceInfoDetailModel;
import org.opencps.api.serviceinfo.model.ServiceInfoInputModel;
import org.opencps.api.serviceinfo.model.ServiceInfoResultsModel;
import org.opencps.api.serviceinfo.model.ServiceInfoSearchModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.auth.api.keys.ActionKeys;
import org.opencps.dossiermgt.action.FileUploadUtils;
import org.opencps.dossiermgt.action.ServiceInfoActions;
import org.opencps.dossiermgt.action.impl.ServiceInfoActionsImpl;
import org.opencps.dossiermgt.constants.ServiceInfoTerm;
import org.opencps.dossiermgt.exception.DuplicateServiceCodeException;
import org.opencps.dossiermgt.exception.RequiredAdministrationCodeException;
import org.opencps.dossiermgt.exception.RequiredServiceCodeException;
import org.opencps.dossiermgt.exception.RequiredServiceNameException;
import org.opencps.dossiermgt.model.ServiceFileTemplate;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.service.ServiceFileTemplateLocalServiceUtil;
import org.opencps.dossiermgt.service.persistence.ServiceFileTemplatePK;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.model.DLFolderConstants;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.document.library.kernel.util.DLUtil;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

public class ServiceInfoManagementImpl implements ServiceInfoManagement {

	@Override
	public Response getServiceInfos(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, ServiceInfoSearchModel query) {

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
			params.put(ServiceInfoTerm.ADMINISTRATION_CODE, query.getAdministration());
			params.put(ServiceInfoTerm.DOMAIN_CODE, query.getDomain());
			params.put(ServiceInfoTerm.MAX_LEVEL, query.getLevel());

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
	public Response addServiceInfo(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, ServiceInfoInputModel input) {

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
	public Response getDetailServiceInfo(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id) {
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
	public Response updateServiceInfo(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, ServiceInfoInputModel input) {

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

			ServiceInfo serviceInfo = actions.updateServiceInfo(user.getUserId(), groupId, GetterUtil.getLong(id),
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
	public Response deleteServiceInfo(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id) {

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

			ServiceInfo serviceInfo = actions.removeServiceInfo(GetterUtil.getLong(id));

			if (Validator.isNotNull(serviceInfo)) {
				serviceInfoInput = ServiceInfoUtils.mappingToServiceInfoInputModel(serviceInfo);

				return Response.status(200).entity(serviceInfoInput).build();
			} else {
				throw new Exception();
			}

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

					error.setMessage("No Content.");
					error.setCode(HttpURLConnection.HTTP_NOT_ACCEPTABLE);
					error.setDescription("No Content.");

					return Response.status(HttpURLConnection.HTTP_NO_CONTENT).entity(error).build();

				}
			}
		}
	}

	@Override
	public Response getFileTemplatesOfServiceInfo(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id) {

		ServiceInfoActions actions = new ServiceInfoActionsImpl();

		FileTemplateResultsModel results = new FileTemplateResultsModel();

		try {

			JSONObject jsonData = actions.getServiceFileTemplate(GetterUtil.getLong(id));

			List<ServiceFileTemplate> fileTemplates = (List<ServiceFileTemplate>) jsonData.get("data");

			results.setTotal(jsonData.getInt("total"));
			results.getData().addAll(ServiceInfoUtils.mappingToFileTemplates(fileTemplates));

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
	public Response addFileTemplateToServiceInfo(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, Attachment file, String id, String fileTemplateNo,
			String templateName, String fileType, int fileSize) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		long userId = serviceContext.getUserId();

		ServiceFileTemplate serviceFileTemplate = null;

		BackendAuth auth = new BackendAuthImpl();

		ServiceInfoActions actions = new ServiceInfoActionsImpl();

		DataHandler dataHandler = file.getDataHandler();

		InputStream inputStream = null;

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (!auth.hasResource(serviceContext, ServiceInfo.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			inputStream = dataHandler.getInputStream();


			serviceFileTemplate = actions.addServiceFileTemplate(userId, groupId, GetterUtil.getLong(id),
					fileTemplateNo, templateName, System.currentTimeMillis() + StringPool.DASH + templateName,
					inputStream, fileType, fileSize, serviceContext);

			FileTemplateModel result = ServiceInfoUtils.mappingToFileTemplateModel(serviceFileTemplate);

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

					error.setMessage("No Content.");
					error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
					error.setDescription("No Content.");

					return Response.status(HttpURLConnection.HTTP_FORBIDDEN).entity(error).build();

				}
			}
		}

	}

	public static byte[] getBytes(InputStream is) throws IOException {

		int len;
		int size = 1024;
		byte[] buf;

		if (is instanceof ByteArrayInputStream) {
			size = is.available();
			buf = new byte[size];
			len = is.read(buf, 0, size);
		} else {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			buf = new byte[size];
			while ((len = is.read(buf, 0, size)) != -1)
				bos.write(buf, 0, len);
			buf = bos.toByteArray();
		}
		return buf;
	}

	public static final String UPLOAD_FILE_SERVER = "/Users/khoavu/";

	private void writeToFileServer(InputStream inputStream, String fileName) {

		OutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(new File(UPLOAD_FILE_SERVER + fileName));
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			outputStream.flush();
			outputStream.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			// release resource, if any
		}
	}

	@Override
	public Response downloadFileTemplateOfServiceInfo(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id, String templateNo) {

		ServiceFileTemplatePK fileTemplatePK = new ServiceFileTemplatePK(GetterUtil.getLong(id), templateNo);

		try {
			ServiceFileTemplate fileTemplate = ServiceFileTemplateLocalServiceUtil
					.getServiceFileTemplate(fileTemplatePK);

			FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileTemplate.getFileEntryId());

			File file = DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(), fileEntry.getVersion(), true);

			ResponseBuilder responseBuilder = Response.ok((Object) file);

			responseBuilder.header("Content-Disposition", "attachment; filename=\"" + fileEntry.getFileName() + "\"");
			responseBuilder.header("Content-Type", fileEntry.getMimeType());

			return responseBuilder.build();

		} catch (Exception e) {
			ErrorMsg error = new ErrorMsg();

			error.setMessage("No Content.");
			error.setCode(HttpURLConnection.HTTP_NO_CONTENT);
			error.setDescription("No Content.");

			return Response.status(HttpURLConnection.HTTP_NO_CONTENT).entity(error).build();

		}

	}

	@Override
	public Response deleteFileTemplateOfServiceInfo(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id, String templateNo) {

		BackendAuth auth = new BackendAuthImpl();

		ServiceInfoActions actions = new ServiceInfoActionsImpl();

		ServiceFileTemplate serviceFileTemplate = null;

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (!auth.hasResource(serviceContext, ServiceInfo.class.getName(), ActionKeys.ADD_ENTRY)) {
				throw new UnauthorizationException();
			}

			serviceFileTemplate = actions.removeServiceFileTemplate(GetterUtil.getLong(id), templateNo);

			FileTemplateModel result = ServiceInfoUtils.mappingToFileTemplateModel(serviceFileTemplate);

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

					error.setMessage("No Content.");
					error.setCode(HttpURLConnection.HTTP_NO_CONTENT);
					error.setDescription("No Content.");

					return Response.status(HttpURLConnection.HTTP_NO_CONTENT).entity(error).build();

				}
			}
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ServiceInfoManagementImpl.class);

	@Override
	public Response getStatisticByLevel(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext) {
		// TODO Auto-generated method stub
		ServiceInfoActions actions = new ServiceInfoActionsImpl();
		
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		
		JSONObject results = JSONFactoryUtil.createJSONObject();
		
		try {
			results = actions.getStatisticByLevel(serviceContext, groupId);
			
			_log.info(results);
			
			return Response.status(200).entity(JSONFactoryUtil.looseSerialize(results)).build();

		} catch (Exception e) {
			ErrorMsg error = new ErrorMsg();

				error.setMessage("Forbidden.");
				error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
				error.setDescription("Forbidden.");

				return Response.status(HttpURLConnection.HTTP_FORBIDDEN).entity(error).build();
		}
		
		
	}

	@Override
	public Response getStatisticByAgency(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext) {
		ServiceInfoActions actions = new ServiceInfoActionsImpl();
		
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		
		JSONObject results = JSONFactoryUtil.createJSONObject();
		
		try {
			results = actions.getStatisticByAdministration(serviceContext, groupId);
			
			_log.info(results);
			
			return Response.status(200).entity(JSONFactoryUtil.looseSerialize(results)).build();

		} catch (Exception e) {
			ErrorMsg error = new ErrorMsg();

				error.setMessage("Forbidden.");
				error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
				error.setDescription("Forbidden.");

				return Response.status(HttpURLConnection.HTTP_FORBIDDEN).entity(error).build();
		}
	}

	@Override
	public Response getStatisticByDomain(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext) {
		ServiceInfoActions actions = new ServiceInfoActionsImpl();
		
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		
		JSONObject results = JSONFactoryUtil.createJSONObject();
		
		try {
			results = actions.getStatisticByDomain(serviceContext, groupId);
			
			_log.info(results);
			
			return Response.status(200).entity(JSONFactoryUtil.looseSerialize(results)).build();

		} catch (Exception e) {
			ErrorMsg error = new ErrorMsg();

				error.setMessage("Forbidden.");
				error.setCode(HttpURLConnection.HTTP_FORBIDDEN);
				error.setDescription("Forbidden.");
				
				return Response.status(HttpURLConnection.HTTP_FORBIDDEN).entity(error).build();
		}
	}

}
