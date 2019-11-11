package org.opencps.api.controller.impl;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
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
import com.sun.xml.bind.v2.runtime.reflect.opt.Const;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.CharSet;
import org.opencps.api.controller.EFormManagement;
import org.opencps.api.controller.util.EFormUtils;
import org.opencps.api.eform.model.EFormDataModel;
import org.opencps.api.eform.model.EFormInputModel;
import org.opencps.api.eform.model.EFormResultsModel;
import org.opencps.api.eform.model.EFormSearchModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.dossiermgt.action.EFormActions;
import org.opencps.dossiermgt.action.impl.DossierPermission;
import org.opencps.dossiermgt.action.impl.EFormActionsImpl;
import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.action.util.ReadFilePropertiesUtils;
import org.opencps.dossiermgt.action.util.SpecialCharacterUtils;
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
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

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
				//String state = search.getState();
				params.put(EFormTerm.SERVICE_CODE, service);
				//params.put(DossierTerm.STATE, state);
				
				Sort[] sorts = null;
				if (Validator.isNull(search.getSort())) {
					sorts = new Sort[] { SortFactoryUtil.create(EFormTerm.CHECK_IN_DATE + ReadFilePropertiesUtils.get(ConstantUtils.SORT_PATTERN), Sort.STRING_TYPE,
							GetterUtil.getBoolean(search.getOrder())) };
				} else {
					sorts = new Sort[] { SortFactoryUtil.create(search.getSort() + ReadFilePropertiesUtils.get(ConstantUtils.SORT_PATTERN), Sort.STRING_TYPE,
							GetterUtil.getBoolean(search.getOrder())) };
				}


				EFormActions actions = new EFormActionsImpl();
				JSONObject jsonData = actions.getEFormList(user.getUserId(), company.getCompanyId(), groupId, params, sorts,
							search.getStart(), search.getEnd(), serviceContext);

				results.setTotal(jsonData.getInt(ConstantUtils.TOTAL));

				results.getData().addAll(EFormUtils.mappingForGetList((List<Document>) jsonData.get(ConstantUtils.DATA)));

			return Response.status(200).entity(results).build();

		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addEFromOfFileTemplate(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, EFormInputModel input) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		long userId = serviceContext.getUserId();

		try {

			EFormActions actions = new EFormActionsImpl();

			String eFormNo = Validator.isNotNull(input.geteFormNo()) ? input.geteFormNo() : StringPool.BLANK;
			Long serviceInfoId = input.getServiceInfoId() != null ? input.getServiceInfoId() : 0;
			String fileTemplateNo = Validator.isNotNull(input.getFileTemplateNo()) ? input.getFileTemplateNo()
					: StringPool.BLANK;
			String eFormName = Validator.isNotNull(input.geteFormName()) ? input.geteFormName() : StringPool.BLANK;
			Long formScriptFileId = input.getFormScriptFileId() != null ? input.getFormScriptFileId() : 0;
			Long formReportFileId = input.getFormReportFileId() != null ? input.getFormReportFileId(): 0;
			String eFormData = Validator.isNotNull(input.geteFormData()) ? input.geteFormData() : StringPool.BLANK;
			String email = Validator.isNotNull(input.getEmail()) ? input.getEmail() : StringPool.BLANK;
			String secret = Validator.isNotNull(input.getSecret()) ? input.getSecret() : StringPool.BLANK;

			EForm eFormInfo = actions.updateEForm(userId, groupId, 0, eFormNo, serviceInfoId, fileTemplateNo, eFormName,
					formScriptFileId, formReportFileId, eFormData, email, secret, serviceContext);

			EFormDataModel result = EFormUtils.mappingForGetDetail(eFormInfo);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {

			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response getEFromBySecret(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String eFormNo, String secret) {

		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		try {

			if (Validator.isNotNull(secret)) {
				try {
					EForm eForm = EFormLocalServiceUtil.getByEFormNo(groupId, eFormNo);

					EFormUtils.checkPassword(eForm, secret);

					EFormDataModel result = EFormUtils.mappingForGetDetail(eForm);

					return Response.status(200).entity(result).build();
				} catch (Exception e) {
					_log.debug(e);
					return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(ReadFilePropertiesUtils.get(ConstantUtils.ERROR_NOT_PERMISSION))
							.build();
				}

			}
			else {
				if (!auth.isAuth(serviceContext)) {
					throw new UnauthenticationException();
				}

				EForm eForm = EFormLocalServiceUtil.getByEFormNo(groupId, eFormNo);

				EFormDataModel result = EFormUtils.mappingForGetDetail(eForm);

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

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		BackendAuth auth = new BackendAuthImpl();

		EFormActions actions = new EFormActionsImpl();

		String eFormData = input.geteFormData();
		try {

			if (Validator.isNotNull(secret)) {
				try {
					EForm eform = EFormLocalServiceUtil.getByEFormNo(groupId, eFormNo);

					if (eform != null && secret.equalsIgnoreCase(eform.getSecret())) {
						eform = actions.updateDataByEFormNo(eform.getEFormId(),eFormData, serviceContext);
						//
						return Response.status(200).entity(eform.getEFormData()).build();
					}
				} catch (Exception e) {
					_log.debug(e);
				}

				return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(ReadFilePropertiesUtils.get(ConstantUtils.ERROR_NOT_PERMISSION))
						.build();
			}
			else {
				if (!auth.isAuth(serviceContext)) {
					throw new UnauthenticationException();
				}

				EForm eform = EFormLocalServiceUtil.getByEFormNo(groupId, eFormNo);

				if (eform != null) {
					eform = actions.updateDataByEFormNo(eform.getEFormId(),eFormData, serviceContext);
					return Response.status(200).entity(eform.getEFormData()).build();
				}
			}

		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
		return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(ReadFilePropertiesUtils.get(ConstantUtils.ERROR_NOT_PERMISSION))
				.build();
	}

	@Override
	public Response updateEFromById(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id, EFormInputModel input) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		long userId = serviceContext.getUserId();
		long eFormId = GetterUtil.getLong(id);

		try {

			EForm eform = null;
			if (eFormId > 0) {
				eform = EFormLocalServiceUtil.fetchEForm(eFormId);
			} else {
				eform = EFormLocalServiceUtil.getByEFormNo(groupId, id);
			}
			
			if (eform != null) {
				EFormActions actions = new EFormActionsImpl();

				String eFormNo = input.geteFormNo();
				String secret = input.getSecret();
				long serviceInfoId = input.getServiceInfoId() != null ? input.getServiceInfoId() : 0;
				String fileTemplateNo = input.getFileTemplateNo();
				String eFormName = input.geteFormName();
				Long formScriptFileId = input.getFormScriptFileId();
				Long formReportFileId = input.getFormReportFileId();
				String eFormData = input.geteFormData();
				String email = input.getEmail();

				eform = actions.updateEForm(userId, groupId, eform.getEFormId(), eFormNo, serviceInfoId, fileTemplateNo,
						eFormName, formScriptFileId, formReportFileId, eFormData, email, secret, serviceContext);
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
			User user, ServiceContext serviceContext, String id, String secret) {

		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		long eFormId = GetterUtil.getLong(id);

		try {
			if (Validator.isNull(secret)) {
				if (!auth.isAuth(serviceContext)) {
					throw new UnauthenticationException();
				}
			}

			
			EForm eform = null;
			if (eFormId > 0) {
				eform = EFormLocalServiceUtil.fetchEForm(eFormId);
			} else {
				eform = EFormLocalServiceUtil.getByEFormNo(groupId, id);
			}

			if (Validator.isNotNull(eform) && secret.equalsIgnoreCase(eform.getSecret())) {

				long formReportFileId = eform.getFormReportFileId();
				String formReport = StringPool.BLANK;
				InputStream is = null;
				if (formReportFileId > 0) {
					try {
						DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.getFileEntry(formReportFileId);

						is = dlFileEntry.getContentStream();
						formReport = IOUtils.toString(is, StandardCharsets.UTF_8.name());
					} catch (Exception e) {
						_log.error(e);
					} finally {
						if (is != null) {
							try {
								is.close();
							} catch (IOException e) {
								_log.error(e);
							}
						}
					}
				}
				// Get formData
				String formData = eform.getEFormData();
				JSONObject jsonData = JSONFactoryUtil.createJSONObject(formData);
				jsonData.put("userName", user.getFullName());
				jsonData.put("url", serviceContext.getPortalURL());
				jsonData.put("eFormNo", eform.getEFormNo());
				jsonData.put("serviceCode", eform.getServiceCode());
				jsonData.put("fileTemplateNo", eform.getFileTemplateNo());
				jsonData.put("eFormName", eform.getEFormName());
				jsonData.put("formScriptFileId", eform.getFormScriptFileId());
				jsonData.put("formReportFileId", eform.getFormReportFileId());
				jsonData.put(ConstantUtils.VALUE_EMAIL, eform.getEmail());
				jsonData.put("secret", eform.getSecret());
				jsonData.put("eFormId", eform.getEFormId());
//				jsonData.put("gateNumber", eform.getGateNumber());
//				jsonData.put("state", eform.getState());
				//Send message bus
				Message message = new Message();
				message.put("formReport", formReport);
				message.put("formData", jsonData.toJSONString());

				try {
					String previewResponse = (String) MessageBusUtil
							.sendSynchronousMessage("jasper/engine/preview/destination", message, 10000);

					File file = new File(previewResponse);

					ResponseBuilder responseBuilder = Response.ok((Object) file);

					responseBuilder.header(ReadFilePropertiesUtils.get(ConstantUtils.TYPE_DISPOSITON),
							ReadFilePropertiesUtils.get(ConstantUtils.VALUE_PATTERN_FILENAME) + file.getName() + StringPool.QUOTE);
					responseBuilder.header(ConstantUtils.CONTENT_TYPE, ReadFilePropertiesUtils.get(ConstantUtils.CONTENT_TYPE_PDF));

					return responseBuilder.build();

				} catch (MessageBusException e) {
					_log.error(e);
					throw new Exception(ReadFilePropertiesUtils.get(ConstantUtils.MSG_ERROR));
				}
			}

		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);

		}
		return Response.status(HttpURLConnection.HTTP_NO_CONTENT).build();
	}

	@Override
	public Response getEFromById(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		try {

			EForm eform = null;
			long eFormId = GetterUtil.getLong(id);
			if (eFormId > 0) {
				eform = EFormLocalServiceUtil.fetchEForm(eFormId);
			} else {
				eform = EFormLocalServiceUtil.getByEFormNo(groupId, id);
			}

			EFormDataModel result = EFormUtils.mappingForGetDetail(eform);

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getEFormDataById(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id, String secret) {

		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		try {

			if (Validator.isNull(secret)) {
				if (!auth.isAuth(serviceContext)) {
					throw new UnauthenticationException();
				}
			}
			
			EForm eform = null;
			long eFormId = GetterUtil.getLong(id);
			if (eFormId > 0) {
				eform = EFormLocalServiceUtil.fetchEForm(eFormId);
			} else {
				eform = EFormLocalServiceUtil.getByEFormNo(groupId, id);
			}

			if (eform != null && secret.equalsIgnoreCase(eform.getSecret())) {
				String eFormData = eform.getEFormData();

				return Response.status(200).entity(eFormData).build();
			}
		} catch (Exception e) {
			_log.debug(e);
		}

		return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(ReadFilePropertiesUtils.get(ConstantUtils.ERROR_NOT_PERMISSION))
				.build();
	}

	@Override
	public Response updateEFromDataById(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id, String secret, EFormInputModel input) {

		BackendAuth auth = new BackendAuthImpl();
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		try {

			if (Validator.isNull(secret)) {
				if (!auth.isAuth(serviceContext)) {
					throw new UnauthenticationException();
				}
			}

			String eFormData = input.geteFormData();
			EForm eform = null;
			EFormActions actions = new EFormActionsImpl();
			long eFormId = GetterUtil.getLong(id);
			if (eFormId > 0) {
				eform = EFormLocalServiceUtil.fetchEForm(eFormId);
			} else {
				eform = EFormLocalServiceUtil.getByEFormNo(groupId, id);
			}

			if (eform != null && secret.equalsIgnoreCase(eform.getSecret())) {
				eform = actions.updateDataByEFormNo(eform.getEFormId(),eFormData, serviceContext);

				return Response.status(200).entity(eform.getEFormData()).build();
			}
		} catch (Exception e) {
			_log.debug(e);
		}

		return Response.status(HttpURLConnection.HTTP_NOT_AUTHORITATIVE).entity(ReadFilePropertiesUtils.get(ConstantUtils.ERROR_NOT_PERMISSION))
				.build();
	}

	@Override
	public Response getEFormByBarCodeAndSecret(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String eFormNo) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		try {

			EForm eform = EFormLocalServiceUtil.getByEFormNo(groupId, eFormNo);

			EFormDataModel result = EFormUtils.mappingForGetDetail(eform);

			return Response.status(200).entity(result).build();


		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getEFormDataByEFormNo(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String eFormNo) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		try {

			EForm eform = EFormLocalServiceUtil.getByEFormNo(groupId, eFormNo);

			if (eform != null) {
				return Response.status(200).entity(eform.getEFormData()).build();
			}
		} catch (Exception e) {
			_log.debug(e);
		}

		return Response.status(HttpURLConnection.HTTP_NO_CONTENT).entity(ReadFilePropertiesUtils.get(ConstantUtils.MSG_ERROR))
				.build();
	}

}
