package org.opencps.api.controller.impl;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.messaging.Message;
import com.liferay.portal.kernel.messaging.MessageBusException;
import com.liferay.portal.kernel.messaging.MessageBusUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.commons.io.IOUtils;
import org.opencps.api.controller.EFormManagement;
import org.opencps.api.controller.util.EFormUtils;
import org.opencps.api.eform.model.EFormDataModel;
import org.opencps.api.eform.model.EFormInputModel;
import org.opencps.api.eform.model.EFormResultsModel;
import org.opencps.api.eform.model.EFormSearchModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.datamgt.model.FileAttach;
import org.opencps.datamgt.service.FileAttachLocalServiceUtil;
import org.opencps.dossiermgt.action.EFormActions;
import org.opencps.dossiermgt.action.impl.EFormActionsImpl;
import org.opencps.dossiermgt.action.util.SpecialCharacterUtils;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.EFormTerm;
import org.opencps.dossiermgt.model.EForm;
import org.opencps.dossiermgt.service.EFormLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;

public class EFormManagementImpl implements EFormManagement{

	private static final Log _log = LogFactoryUtil.getLog(EFormManagementImpl.class);
	@SuppressWarnings("unchecked")
	@Override
	public Response getEFromList(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, EFormSearchModel search) {
		
		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (Validator.isNull(search.getEnd()) || search.getEnd() == 0) {
				search.setStart(-1);
				search.setEnd(-1);
			}

			EFormResultsModel results = new EFormResultsModel();

				LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
				params.put(Field.GROUP_ID, String.valueOf(groupId));
				// LamTV_Process search LIKE
				String keywordSearch = search.getKeyword();
				String keySearch = StringPool.BLANK;
				if (Validator.isNotNull(keywordSearch)) {
					keySearch = SpecialCharacterUtils.splitSpecial(keywordSearch);
				}
				params.put(Field.KEYWORD_SEARCH, keySearch);

				String serviceCode = search.getService();
				String service = StringPool.BLANK;
				if (Validator.isNotNull(serviceCode)) {
					service = SpecialCharacterUtils.splitSpecial(serviceCode);
				}
				String state = search.getState();


				params.put(EFormTerm.SERVICE_CODE, service);
				params.put(DossierTerm.STATE, state);
				
				Sort[] sorts = null;
				if (Validator.isNull(search.getSort())) {
					sorts = new Sort[] { SortFactoryUtil.create(EFormTerm.CHECK_IN_DATE + "_sortable", Sort.STRING_TYPE,
							GetterUtil.getBoolean(search.getOrder())) };
				} else {
					sorts = new Sort[] { SortFactoryUtil.create(search.getSort() + "_sortable", Sort.STRING_TYPE,
							GetterUtil.getBoolean(search.getOrder())) };
				}


				EFormActions actions = new EFormActionsImpl();
				JSONObject jsonData = actions.getEFormList(user.getUserId(), company.getCompanyId(), groupId, params, sorts,
							search.getStart(), search.getEnd(), serviceContext);

				results.setTotal(jsonData.getInt("total"));

				results.getData().addAll(EFormUtils.mappingForGetList((List<Document>) jsonData.get("data")));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addEFromOfFileTemplate(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, EFormInputModel input) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		long userId = serviceContext.getUserId();

		BackendAuth auth = new BackendAuthImpl();

		//EFormInputModel eFromInputModel = new EFormInputModel();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			EFormActions actions = new EFormActionsImpl();

			String eFormNo = Validator.isNotNull(input.geteFormNo()) ? input.geteFormNo() : StringPool.BLANK;
			String serviceCode = Validator.isNotNull(input.getServiceCode()) ? input.getServiceCode()
					: StringPool.BLANK;
			String fileTemplateNo = Validator.isNotNull(input.getFileTemplateNo()) ? input.getFileTemplateNo()
					: StringPool.BLANK;
			String eFormName = Validator.isNotNull(input.geteFormName()) ? input.geteFormName() : StringPool.BLANK;
			Long formScriptFileId = input.getFormScriptFileId() != null ? input.getFormScriptFileId() : 0;
			Long formReportFileId = input.getFormReportFileId() != null ? input.getFormReportFileId(): 0;
			String eFormData = Validator.isNotNull(input.geteFormData()) ? input.geteFormData() : StringPool.BLANK;
			String email = Validator.isNotNull(input.getEmail()) ? input.getEmail() : StringPool.BLANK;
			String secret = Validator.isNotNull(input.getSecret()) ? input.getSecret() : StringPool.BLANK;
			String checkinDate = Validator.isNotNull(input.getCheckinDate()) ? input.getCheckinDate()
					: StringPool.BLANK;
			String gateNumber = Validator.isNotNull(input.getGateNumber()) ? input.getGateNumber() : StringPool.BLANK;
			Integer state = input.getState() != null ? input.getState() : 0;

			EForm eFormInfo = actions.updateEForm(userId, groupId, 0, eFormNo, serviceCode, fileTemplateNo, eFormName,
					formScriptFileId, formReportFileId, eFormData, email, secret, checkinDate, gateNumber, state,
					serviceContext);

			EFormDataModel result = EFormUtils.mappingForGetDetail(eFormInfo);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {

			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response getEFromBySecret(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String eFormNo, String secret) {

		//DossierPermission dossierPermission = new DossierPermission();
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		try {

			if (Validator.isNotNull(secret)) {
				try {
					EForm eform = EFormLocalServiceUtil.getByEFormNo(groupId, eFormNo);

//					List<Role> userRoles = user.getRoles();
//					boolean isAdmin = false;
//					for (Role r : userRoles) {
//						if (r.getName().startsWith("Administrator")) {
//							isAdmin = true;
//							break;
//						}
//					}
//					if (!isAdmin) {
//						dossierPermission.checkPassword(dossier, secretKey);
//					}
					
					EFormDataModel result = EFormUtils.mappingForGetDetail(eform);

					return Response.status(200).entity(result).build();
				} catch (Exception e) {
					_log.debug(e);
					return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity("secretKey not sucess")
							.build();
				}

			}
			else {
//				_log.info("START");
				if (!auth.isAuth(serviceContext)) {
					throw new UnauthenticationException();
				}

				EForm eform = EFormLocalServiceUtil.getByEFormNo(groupId, eFormNo);

				EFormDataModel result = EFormUtils.mappingForGetDetail(eform);

				return Response.status(200).entity(result).build();

			}

		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response updateEFromBySecret(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String eFormNo, String secret, EFormInputModel input) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		BackendAuth auth = new BackendAuthImpl();

		//EFormInputModel eFromInputModel = new EFormInputModel();
		EFormActions actions = new EFormActionsImpl();

		String eFormData = input.geteFormData();
		try {

			if (Validator.isNotNull(secret)) {
				try {
					EForm eform = EFormLocalServiceUtil.getByEFormNo(groupId, eFormNo);

//					List<Role> userRoles = user.getRoles();
//					boolean isAdmin = false;
//					for (Role r : userRoles) {
//						if (r.getName().startsWith("Administrator")) {
//							isAdmin = true;
//							break;
//						}
//					}
//					if (!isAdmin) {
//						dossierPermission.checkPassword(dossier, secretKey);
//					}

					if (eform != null) {
						eform = actions.updateDataByEFormNo(eform.getEFormId(),eFormData, serviceContext);
					}

					return Response.status(200).entity(eform.getEFormData()).build();
				} catch (Exception e) {
					_log.debug(e);
					return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity("secretKey not sucess")
							.build();
				}

			}
			else {
//				_log.info("START");
				if (!auth.isAuth(serviceContext)) {
					throw new UnauthenticationException();
				}

				EForm eform = EFormLocalServiceUtil.getByEFormNo(groupId, eFormNo);

				if (eform != null) {
					eform = actions.updateDataByEFormNo(eform.getEFormId(),eFormData, serviceContext);
				}

				return Response.status(200).entity(eform.getEFormData()).build();

			}

		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response updateEFromById(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, EFormInputModel input) {

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		long userId = serviceContext.getUserId();

		BackendAuth auth = new BackendAuthImpl();

		//EFormInputModel eFromInputModel = new EFormInputModel();
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			EForm eform = EFormLocalServiceUtil.fetchEForm(id);
			if (eform != null) {
				EFormActions actions = new EFormActionsImpl();

				String eFormNo = input.geteFormNo();
				String secret = input.getSecret();
				String serviceCode = input.getServiceCode();
				String fileTemplateNo = input.getFileTemplateNo();
				String eFormName = input.geteFormName();
				Long formScriptFileId = input.getFormScriptFileId();
				Long formReportFileId = input.getFormReportFileId();
				String eFormData = input.geteFormData();
				String email = input.getEmail();
				String checkinDate = input.getCheckinDate();
				String gateNumber = input.getGateNumber();
				Integer state = input.getState();

				eform = actions.updateEForm(userId, groupId, eform.getEFormId(), eFormNo, serviceCode,
						fileTemplateNo, eFormName, formScriptFileId, formReportFileId, eFormData, email, secret,
						checkinDate, gateNumber, state, serviceContext);
			}

			EFormDataModel result = EFormUtils.mappingForGetDetail(eform);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response deleteEFromById(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {

		//long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		//long userId = serviceContext.getUserId();

		BackendAuth auth = new BackendAuthImpl();
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			EFormActions actions = new EFormActionsImpl();
			EForm eform = actions.deleteEFormById(id,serviceContext);

			EFormDataModel result = EFormUtils.mappingForGetDetail(eform);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response printEFormReport(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String eFormNo, String secret) {

		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

		try {
			if (Validator.isNull(secret)) {
				if (!auth.isAuth(serviceContext)) {
					throw new UnauthenticationException();
				}
			}

			EForm eform = EFormLocalServiceUtil.getByEFormNo(groupId, eFormNo);
			if (Validator.isNotNull(eform)) {

				long formReportFileId = eform.getFormReportFileId();
				String formReport = StringPool.BLANK;
				InputStream is = null;
				if (formReportFileId > 0) {
					try {

						FileAttach fileAttach = FileAttachLocalServiceUtil.fetchFileAttach(Long.valueOf(formReportFileId));
						if (fileAttach != null) {
							DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.getFileEntry(fileAttach.getFileEntryId());

							is = dlFileEntry.getContentStream();
							formReport = IOUtils.toString(is, "UTF-8");
						}
					} catch (Exception e) {
						_log.error(e);
					} finally {
						try {
							is.close();
						} catch (IOException e) {
							_log.error(e);
						}
					}
				}
				// Get formData
				String formData = eform.getEFormData();
				//Send message bus
				Message message = new Message();
				message.put("formReport", formReport);
				message.put("formData", formData);

				try {
					String previewResponse = (String) MessageBusUtil
							.sendSynchronousMessage("jasper/engine/preview/destination", message, 10000);

					File file = new File(previewResponse);

					ResponseBuilder responseBuilder = Response.ok((Object) file);

					responseBuilder.header("Content-Disposition",
							"attachment; filename=\"" + file.getName() + "\"");
					responseBuilder.header("Content-Type", "application/pdf");

					return responseBuilder.build();

				} catch (MessageBusException e) {
					_log.error(e);
					throw new Exception("Preview rendering not avariable");
				}
			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);

		}
		return Response.status(HttpURLConnection.HTTP_NO_CONTENT).build();
	}

}
