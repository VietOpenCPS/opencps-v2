package org.opencps.api.controller.impl;

import com.liferay.document.library.kernel.model.DLFileEntry;
import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.EntityTag;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.controller.ServiceInfoManagement;
import org.opencps.api.controller.util.MessageUtil;
import org.opencps.api.controller.util.ServiceInfoUtils;
import org.opencps.api.serviceinfo.model.FileTemplateModel;
import org.opencps.api.serviceinfo.model.FileTemplateResultsModel;
import org.opencps.api.serviceinfo.model.FileTemplateSearchModel;
import org.opencps.api.serviceinfo.model.ServiceInfoDetailModel;
import org.opencps.api.serviceinfo.model.ServiceInfoInputModel;
import org.opencps.api.serviceinfo.model.ServiceInfoRecentResultModel;
import org.opencps.api.serviceinfo.model.ServiceInfoResultsModel;
import org.opencps.api.serviceinfo.model.ServiceInfoSearchModel;
import org.opencps.api.serviceinfo.model.ServiceRecentDetailModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.auth.api.exception.UnauthorizationException;
import org.opencps.auth.api.keys.ActionKeys;
import org.opencps.communication.model.ServerConfig;
import org.opencps.communication.service.ServerConfigLocalServiceUtil;
import org.opencps.datamgt.constants.DictItemTerm;
import org.opencps.datamgt.model.FileAttach;
import org.opencps.datamgt.service.FileAttachLocalServiceUtil;
import org.opencps.dossiermgt.action.FileUploadUtils;
import org.opencps.dossiermgt.action.ServiceInfoActions;
import org.opencps.dossiermgt.action.impl.DVCQGIntegrationActionImpl;
import org.opencps.dossiermgt.action.impl.ServiceInfoActionsImpl;
import org.opencps.dossiermgt.action.util.OpenCPSConfigUtil;
import org.opencps.dossiermgt.action.util.SpecialCharacterUtils;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.constants.ServiceInfoTerm;
import org.opencps.dossiermgt.model.ServiceFileTemplate;
import org.opencps.dossiermgt.model.ServiceInfo;
import org.opencps.dossiermgt.rest.utils.SyncServerTerm;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceConfigLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceFileTemplateLocalServiceUtil;
import org.opencps.dossiermgt.service.ServiceInfoLocalServiceUtil;
import org.opencps.dossiermgt.service.persistence.ServiceFileTemplatePK;

import backend.auth.api.exception.BusinessExceptionImpl;

public class ServiceInfoManagementImpl implements ServiceInfoManagement {

	@SuppressWarnings("unchecked")
	@Override
	public Response getServiceInfos(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, ServiceInfoSearchModel query, Request requestCC) {

		ServiceInfoActions actions = new ServiceInfoActionsImpl();

		ServiceInfoResultsModel results = new ServiceInfoResultsModel();

		try {
			if (query.getEnd() == 0) {

				query.setStart(QueryUtil.ALL_POS);

				query.setEnd(QueryUtil.ALL_POS);

			}

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			long userId = user.getUserId();

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			//params.put(Field.KEYWORD_SEARCH, query.getKeyword());
			//Keyword search like
			String keywordSearch = query.getKeyword();
			String keySearch = StringPool.BLANK;
			if (Validator.isNotNull(keywordSearch)) {
				keySearch = SpecialCharacterUtils.splitSpecial(keywordSearch);
			}
			params.put(Field.KEYWORD_SEARCH, keySearch);

			//Administration Code
			String administrationCode = query.getAdministration();
			String administrationCodeSearch = StringPool.BLANK;
			if (Validator.isNotNull(administrationCode)) {
				administrationCodeSearch = SpecialCharacterUtils.splitSpecial(administrationCode);
			}
			params.put(ServiceInfoTerm.ADMINISTRATION_CODE_SEARCH, administrationCodeSearch);
			//Domain Code
			String domainCode = query.getDomain();
			String domainSearch = StringPool.BLANK;
			if (Validator.isNotNull(domainCode)) {
				domainSearch = SpecialCharacterUtils.splitSpecial(domainCode);
			}
			params.put(ServiceInfoTerm.DOMAIN_CODE_SEARCH, domainSearch);
			params.put(ServiceInfoTerm.MAX_LEVEL, query.getLevel());
			params.put(ServiceInfoTerm.PUBLIC_, query.getActive());
			params.put(ServiceInfoTerm.MAPPING, query.getMapping());
			params.put(ServiceInfoTerm.SYNCED, query.getSynced());

			Sort[] sorts = null;
//			_log.info("sorts: "+query.getSort());
//			_log.info("getOrder: "+query.getOrder());
			if (Validator.isNotNull(query.getSort()) && query.getSort().equals(ServiceInfoTerm.MAX_LEVEL)) {
				String numberSort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_NUMBER_SORT), query.getSort());
				// String serviceCodeSort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_STRING_SORT), ServiceInfoTerm.SERVICE_CODE_SEARCH);
				String serviceNameSort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_STRING_SORT), ServiceInfoTerm.SERVICE_NAME_SEARCH);
				sorts = new Sort[] {
						SortFactoryUtil.create(numberSort, Sort.INT_TYPE, GetterUtil.getBoolean(query.getOrder())),
						// SortFactoryUtil.create(serviceCodeSort, Sort.STRING_TYPE, false),
						SortFactoryUtil.create(serviceNameSort, Sort.STRING_TYPE, false)
					};
			} else if (Validator.isNotNull(query.getSort()) && (query.getSort().equals(DictItemTerm.SIBLING_AGENCY)
					|| query.getSort().equals(DictItemTerm.SIBLING_DOMAIN))) {
				String numberSort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_NUMBER_SORT), query.getSort());
				sorts = new Sort[] { SortFactoryUtil.create(numberSort, Sort.INT_TYPE,
					GetterUtil.getBoolean(query.getOrder())) };
			} else if (Validator.isNotNull(query.getSort())) {
				String querySort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_STRING_SORT), query.getSort());
				sorts = new Sort[] { SortFactoryUtil.create(querySort, Sort.STRING_TYPE,
						GetterUtil.getBoolean(query.getOrder())) };
			} else {
				String stringSort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_STRING_SORT), ServiceInfoTerm.SERVICE_CODE_SEARCH);
				sorts = new Sort[] { SortFactoryUtil.create(stringSort, Sort.STRING_TYPE,
						GetterUtil.getBoolean(query.getOrder())) };
			}

			JSONObject jsonData = (Validator.isNotNull(query.getAgency())) ? actions.getServiceInfos(serviceContext.getUserId(), serviceContext.getCompanyId(),
				groupId, params, sorts, QueryUtil.ALL_POS, QueryUtil.ALL_POS, serviceContext) : actions.getServiceInfos(serviceContext.getUserId(), serviceContext.getCompanyId(),
						groupId, params, sorts, query.getStart(), query.getEnd(), serviceContext);

			List<Document> lstDocs = new ArrayList<Document>();
			if (Validator.isNotNull(query.getAgency())) {
				int total = 0;
				for (Document doc : (List<Document>) jsonData.get(ConstantUtils.DATA)) {
					long serviceInfoId = (GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK)));
					int countService = ServiceConfigLocalServiceUtil.countBySIAndGAC(groupId, serviceInfoId, query.getAgency());
					if (countService > 0) {
						total++;
						lstDocs.add(doc);
					}
				}		
				jsonData.put(ConstantUtils.TOTAL, total);
				if (query.getEnd() < lstDocs.size()) {
					jsonData.put(ConstantUtils.DATA, lstDocs.subList(query.getStart(), query.getEnd()));					
				}
				else {
					jsonData.put(ConstantUtils.DATA, lstDocs.subList(query.getStart(), lstDocs.size()));					
				}
			}

			results.getData()
				.addAll(ServiceInfoUtils.mappingToServiceInfoResultModel((List<Document>) jsonData.get(ConstantUtils.DATA), groupId, userId, query.isFilterApplicant(), serviceContext));
			if(query.isFilterApplicant()){
				results.setTotal(results.getData().size());
			}else {
				results.setTotal(jsonData.getInt(ConstantUtils.TOTAL));
			}
//			EntityTag etag = new EntityTag(Integer.toString(Long.valueOf(groupId).hashCode()));
//		    ResponseBuilder builder = requestCC.evaluatePreconditions(etag);
//		    
//			if (OpenCPSConfigUtil.isHttpCacheEnable() && builder == null) {
//				builder = Response.status(HttpURLConnection.HTTP_OK);
//				CacheControl cc = new CacheControl();
//				cc.setMaxAge(OpenCPSConfigUtil.getHttpCacheMaxAge());
//				cc.setPrivate(true);	
//				builder.tag(etag);
//				return builder.status(HttpURLConnection.HTTP_OK).entity(results).cacheControl(cc).build();
//			}
//			else {
//				return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();				
//			}

//			EntityTag etag = new EntityTag(Integer.toString((groupId + keySearch + query.getAdministration() + query.getDomain() + query.getLevel() + query.getActive()).hashCode()));
//		    ResponseBuilder builder = requestCC.evaluatePreconditions(etag);			
//			CacheControl cc = new CacheControl();
//			cc.setMaxAge(OpenCPSConfigUtil.getHttpCacheMaxAge());
//		    if (OpenCPSConfigUtil.isHttpCacheEnable() && builder == null) {
//				builder = Response.ok(results);
//				builder.tag(etag);
//			}
//			
//		    builder.cacheControl(cc);
//		    
//		    return builder.build();
		    return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response addServiceInfo(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, ServiceInfoInputModel input) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
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

			String serviceCode = HtmlUtil.escape(input.getServiceCode());
			String serviceName = HtmlUtil.escape(input.getServiceName());
			String processText = HtmlUtil.escape(input.getProcessText());
			String methodText = HtmlUtil.escape(input.getMethodText());
			String dossierText = HtmlUtil.escape(input.getDossierText());
			String conditionText = HtmlUtil.escape(input.getConditionText());
			String durationText = HtmlUtil.escape(input.getDurationText());
			String applicantText = HtmlUtil.escape(input.getApplicantText());
			String resultText = HtmlUtil.escape(input.getResultText());
			String regularText = HtmlUtil.escape(input.getRegularText());
			String feeText = HtmlUtil.escape(input.getFeeText());
			String administrationCode = HtmlUtil.escape(input.getAdministrationCode());
			String domainCode = HtmlUtil.escape(input.getDomainCode());
			String active = HtmlUtil.escape(input.getActive());
			
			ServiceInfo serviceInfo = actions.updateServiceInfo(userId, groupId, input.getServiceInfoId(),
					serviceCode, serviceName, processText, methodText,
					dossierText, conditionText, durationText, applicantText,
					resultText, regularText, feeText, administrationCode,
					domainCode, input.getMaxLevel(), GetterUtil.getBoolean(active),
					input.getGovAgencyText(), serviceContext);

			serviceInfoInput = ServiceInfoUtils.mappingToServiceInfoInputModel(serviceInfo);

			return Response.status(HttpURLConnection.HTTP_OK).entity(serviceInfoInput).build();

		} catch (Exception e) {

			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response getDetailServiceInfo(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String id, Request requestCC) {
		ServiceInfoActions actions = new ServiceInfoActionsImpl();

		ServiceInfoDetailModel results = new ServiceInfoDetailModel();

		try {
			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			long userId = user.getUserId();

			ServiceInfo serviceInfo = null;

			serviceInfo = actions.getByCode(id, groupId);

			if (Validator.isNull(serviceInfo)) {
				serviceInfo = actions.getById(GetterUtil.getLong(id));
			}

			if (Validator.isNull(serviceInfo)) {
				throw new Exception();
			} else {
				results = ServiceInfoUtils.mappingToServiceInfoDetailModel(serviceInfo, userId);
			}
                        /*
			EntityTag etag = new EntityTag(String.valueOf((groupId + StringPool.UNDERLINE + id).hashCode()));
		    ResponseBuilder builder = requestCC.evaluatePreconditions(etag);
			CacheControl cc = new CacheControl();
			cc.setMaxAge(OpenCPSConfigUtil.getHttpCacheMaxAge());
			cc.setPrivate(true);

		    if (OpenCPSConfigUtil.isHttpCacheEnable() && builder == null) {
				builder = Response.ok(results);
				builder.tag(etag);
			}
                 
		    builder.cacheControl(cc);
                    
		    return builder.build();
                    */
                    return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response updateServiceInfo(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, ServiceInfoInputModel input) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

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

			String serviceCode = HtmlUtil.escape(input.getServiceCode());
			String serviceName = HtmlUtil.escape(input.getServiceName());
			String processText = HtmlUtil.escape(input.getProcessText());
			String methodText = HtmlUtil.escape(input.getMethodText());
			String dossierText = HtmlUtil.escape(input.getDossierText());
			String conditionText = HtmlUtil.escape(input.getConditionText());
			String durationText = HtmlUtil.escape(input.getDurationText());
			String applicantText = HtmlUtil.escape(input.getApplicantText());
			String resultText = HtmlUtil.escape(input.getResultText());
			String regularText = HtmlUtil.escape(input.getRegularText());
			String feeText = HtmlUtil.escape(input.getFeeText());
			String administrationCode = HtmlUtil.escape(input.getAdministrationCode());
			String domainCode = HtmlUtil.escape(input.getDomainCode());
			String active = HtmlUtil.escape(input.getActive());

			ServiceInfo serviceInfo = actions.updateServiceInfo(user.getUserId(), groupId, GetterUtil.getLong(id),
					serviceCode, serviceName, processText, methodText,
					dossierText, conditionText, durationText, applicantText,
					resultText, regularText, feeText, administrationCode,
					domainCode, input.getMaxLevel(), GetterUtil.getBoolean(active),
					input.getGovAgencyText(), serviceContext);

			serviceInfoInput = ServiceInfoUtils.mappingToServiceInfoInputModel(serviceInfo);

			return Response.status(HttpURLConnection.HTTP_OK).entity(serviceInfoInput).build();

		} catch (Exception e) {

			return BusinessExceptionImpl.processException(e);
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

				return Response.status(HttpURLConnection.HTTP_OK).entity(serviceInfoInput).build();
			} else {
				throw new Exception();
			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getFileTemplatesOfServiceInfo(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id, FileTemplateSearchModel query) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		ServiceInfoActions actions = new ServiceInfoActionsImpl();
		FileTemplateResultsModel results = new FileTemplateResultsModel();

		try {

			if (Validator.isNotNull(query.getEnd()) || query.getEnd() == 0) {
				query.setStart(QueryUtil.ALL_POS);
				query.setEnd(QueryUtil.ALL_POS);
			}

			if (Validator.isNotNull(query.geteForm())) {
				boolean eformFlag = Boolean.valueOf(query.geteForm());
				JSONObject jsonData = actions.getServiceFileTemplate(groupId, id, eformFlag, query.getStart(),
						query.getEnd());
				
				List<ServiceFileTemplate> fileTemplates = (List<ServiceFileTemplate>) jsonData.get(ConstantUtils.DATA);

				results.setTotal(jsonData.getInt(ConstantUtils.TOTAL));
				results.getData().addAll(ServiceInfoUtils.mappingToFileTemplates(fileTemplates));

				return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();
			} else {
				JSONObject jsonData = actions.getServiceFileTemplate(groupId, id, query.getStart(),
						query.getEnd());
				
				List<ServiceFileTemplate> fileTemplates = (List<ServiceFileTemplate>) jsonData.get(ConstantUtils.DATA);

				results.setTotal(jsonData.getInt(ConstantUtils.TOTAL));
				results.getData().addAll(ServiceInfoUtils.mappingToFileTemplates(fileTemplates));

				return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();
			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	@Override
	public Response addFileTemplateToServiceInfo(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, Attachment file, String id, String fileTemplateNo,
			String templateName, String fileType, int fileSize, String fileName) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

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
					fileTemplateNo, templateName, fileName,
					inputStream, fileType, fileSize, serviceContext);

			FileTemplateModel result = ServiceInfoUtils.mappingToFileTemplateModel(serviceFileTemplate);

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		} catch (Exception e) {

			return BusinessExceptionImpl.processException(e);
		}

	}

	public static byte[] getBytes(InputStream is) throws IOException {

		int len;
		int size = 1024;
		byte[] buf;

		if (is instanceof ByteArrayInputStream) {
			size = is.available();
			buf = new byte[size];
			is.read(buf, 0, size);
		} else {
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			buf = new byte[size];
			while ((len = is.read(buf, 0, size)) != -1)
				bos.write(buf, 0, len);
			buf = bos.toByteArray();
		}
		return buf;
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
			String attachmentFilename = String.format(MessageUtil.getMessage(ConstantUtils.ATTACHMENT_FILENAME), fileEntry.getFileName());
			responseBuilder.header(ConstantUtils.CONTENT_DISPOSITION, attachmentFilename);
			responseBuilder.header(HttpHeaders.CONTENT_TYPE, fileEntry.getMimeType());

			return responseBuilder.build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);

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

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		} catch (Exception e) {

			return BusinessExceptionImpl.processException(e);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ServiceInfoManagementImpl.class);

	@Override
	public Response getStatisticByLevel(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, Request requestCC) {

		ServiceInfoActions actions = new ServiceInfoActionsImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		JSONObject results = JSONFactoryUtil.createJSONObject();

		try {
			results = actions.getStatisticByLevel(serviceContext, groupId);
			
//			_log.info(results);
//			EntityTag etag = new EntityTag(Integer.toString(Long.valueOf(groupId).hashCode()));
//		    ResponseBuilder builder = requestCC.evaluatePreconditions(etag);
//		    
//			if (OpenCPSConfigUtil.isHttpCacheEnable() && builder == null) {
//				builder = Response.status(HttpURLConnection.HTTP_OK);
//				CacheControl cc = new CacheControl();
//				cc.setMaxAge(OpenCPSConfigUtil.getHttpCacheMaxAge());
//				cc.setPrivate(true);	
//				builder.tag(etag);
//				return builder.status(HttpURLConnection.HTTP_OK).entity(JSONFactoryUtil.looseSerialize(results)).cacheControl(cc).build();
//			}
//			else {
//				return Response.status(HttpURLConnection.HTTP_OK).entity(JSONFactoryUtil.looseSerialize(results)).build();				
//			}
			
			return Response.status(HttpURLConnection.HTTP_OK).entity(JSONFactoryUtil.looseSerialize(results)).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getStatisticByAgency(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, ServiceInfoSearchModel search, Request requestCC) {
		ServiceInfoActions actions = new ServiceInfoActionsImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		JSONObject results = JSONFactoryUtil.createJSONObject();

		try {
			//Sort agency
			Sort[] sorts = null;
			if (Validator.isNull(search.getSort())) {
				String dateSort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_SORT), DossierTerm.CREATE_DATE);
				sorts = new Sort[] { SortFactoryUtil.create(dateSort, Sort.STRING_TYPE,
						GetterUtil.getBoolean(search.getOrder())) };
			} else {
				String numberSort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_NUMBER_SORT), search.getSort());
				sorts = new Sort[] { SortFactoryUtil.create(numberSort, Sort.INT_TYPE,
						GetterUtil.getBoolean(search.getOrder())) };
			}
			results = actions.getStatisticByAdministration(groupId, sorts, serviceContext);
			
//			_log.info(results);
//			EntityTag etag = new EntityTag(Integer.toString(Long.valueOf(groupId).hashCode()));
//			ResponseBuilder builder = requestCC.evaluatePreconditions(etag);
//			if (OpenCPSConfigUtil.isHttpCacheEnable() && builder == null) {
//				builder = Response.status(HttpURLConnection.HTTP_OK);
//				CacheControl cc = new CacheControl();
//				cc.setMaxAge(OpenCPSConfigUtil.getHttpCacheMaxAge());
//				cc.setPrivate(true);	
//				builder.tag(etag);
//				return Response.status(HttpURLConnection.HTTP_OK).entity(JSONFactoryUtil.looseSerialize(results)).cacheControl(cc).build();
			return Response.status(HttpURLConnection.HTTP_OK).entity(JSONFactoryUtil.looseSerialize(results)).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getStatisticByDomain(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String agency, ServiceInfoSearchModel search, Request requestCC) {
		ServiceInfoActions actions = new ServiceInfoActionsImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		JSONObject results = JSONFactoryUtil.createJSONObject();

		try {
			//Sort agency
			Sort[] sorts = null;
			if (Validator.isNull(search.getSort())) {
				String dateSort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_SORT), DossierTerm.CREATE_DATE);
				sorts = new Sort[] { SortFactoryUtil.create(dateSort, Sort.STRING_TYPE,
						GetterUtil.getBoolean(search.getOrder())) };
			} else {
				String numberSort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_NUMBER_SORT), search.getSort());
				sorts = new Sort[] { SortFactoryUtil.create(numberSort, Sort.INT_TYPE,
						GetterUtil.getBoolean(search.getOrder())) };
			}
			if (Validator.isNotNull(agency)) {
				results = actions.getStatisticByDomainFilterAdministration(groupId, sorts, serviceContext, agency);
			}
			else {
				results = actions.getStatisticByDomain(groupId, sorts, serviceContext);
			}
//			_log.info(results);
//			EntityTag etag = new EntityTag(Integer.toString(Long.valueOf(groupId).hashCode()));
//		    ResponseBuilder builder = requestCC.evaluatePreconditions(etag);
//			if (OpenCPSConfigUtil.isHttpCacheEnable() && builder == null) {
//				builder = Response.status(HttpURLConnection.HTTP_OK);
//				CacheControl cc = new CacheControl();
//				cc.setMaxAge(OpenCPSConfigUtil.getHttpCacheMaxAge());
//				cc.setPrivate(true);	
//				builder.tag(etag);
//				return builder.status(HttpURLConnection.HTTP_OK).entity(JSONFactoryUtil.looseSerialize(results)).cacheControl(cc).build();
//			}
//			else {
//				return Response.status(HttpURLConnection.HTTP_OK).entity(JSONFactoryUtil.looseSerialize(results)).build();
//			}
			
			return Response.status(HttpURLConnection.HTTP_OK).entity(JSONFactoryUtil.looseSerialize(results)).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getFormOfFileTemplate(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id, String templateNo,
			String fileAttachId) {

		String result = StringPool.BLANK;
		InputStream is = null;

		try {

			FileAttach fileAttach = FileAttachLocalServiceUtil.fetchFileAttach(Long.valueOf(fileAttachId));
			if (fileAttach != null) {
				DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.getFileEntry(fileAttach.getFileEntryId());

				is = dlFileEntry.getContentStream();
				result = IOUtils.toString(is, ConstantUtils.ENCODING_UTF_8);
			}

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

		return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();
	}

	@Override
	public Response getFormReportOfFileTemplate(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id, String templateNo) {

		String result = StringPool.BLANK;
		InputStream is = null;
		long serviceInfoId = GetterUtil.getLong(id);

		try {

			ServiceFileTemplate fileTemplate = ServiceFileTemplateLocalServiceUtil
					.fetchByF_serviceInfoId_fileTemplateNo(serviceInfoId, templateNo);
			if (fileTemplate != null) {
					DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.getFileEntry(fileTemplate.getFormReportFileId());

					is = dlFileEntry.getContentStream();
					result = IOUtils.toString(is, ConstantUtils.ENCODING_UTF_8);
			}
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
		return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();
	}

	@Override
	public Response getFormScriptOfFileTemplate(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id, String templateNo) {

		String result = StringPool.BLANK;
		InputStream is = null;
		long serviceInfoId = GetterUtil.getLong(id);

		try {
			ServiceFileTemplate fileTemplate = ServiceFileTemplateLocalServiceUtil
					.fetchByF_serviceInfoId_fileTemplateNo(serviceInfoId, templateNo);
			if (fileTemplate != null) {
					DLFileEntry dlFileEntry = DLFileEntryLocalServiceUtil.getFileEntry(fileTemplate.getFormScriptFileId());

					is = dlFileEntry.getContentStream();
					result = IOUtils.toString(is, ConstantUtils.ENCODING_UTF_8);
			}
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
		return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();
	}

	@Override
	public Response getServiceInfoRecently(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, ServiceInfoSearchModel search) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		long userId = user.getUserId();
		//String emailLogin = user.getEmailAddress();
		//DossierActions actions = new DossierActionsImpl();

		try {
			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();
			params.put(Field.GROUP_ID, String.valueOf(groupId));
			// LamTV_Process search LIKE
			String owner = StringPool.BLANK;
			if (Validator.isNotNull(search.getTop()) && ConstantUtils.SERVICE_INFO_RECENTLY.equalsIgnoreCase(search.getTop())) {
				owner = "true";
			}

			params.put(DossierTerm.OWNER, owner);
			params.put(DossierTerm.USER_ID, userId);
			_log.info("USER_ID: "+userId);
			String numberSort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_NUMBER_SORT), DossierTerm.SUBMIT_DATE);
			Sort[] sorts = new Sort[] {
					SortFactoryUtil.create(numberSort, Sort.LONG_TYPE, true) };

			ServiceInfoRecentResultModel results = new ServiceInfoRecentResultModel();

			SearchContext searchContext = new SearchContext();
			searchContext.setCompanyId(company.getCompanyId());

			Hits hits = DossierLocalServiceUtil.searchLucene(params, sorts, -1, -1, searchContext);
			if (hits != null) {
				List<Document> docList = hits.toList();

				Integer start = search.getStart();
				Integer end = search.getEnd();
				StringBuilder sb = new StringBuilder();
				for (Document document : docList) {
					String serviceCode = document.get(DossierTerm.SERVICE_CODE);
					if (Validator.isNotNull(serviceCode)) {
						if (sb.length() == 0) {
							sb.append(serviceCode);
						} else if (!sb.toString().contains(serviceCode)) {
							sb.append(StringPool.COMMA);
							sb.append(serviceCode);
						}
					}
				}
				_log.info("sb: " + sb.toString());
				if (sb.length() > 0) {
					// Convert the sb of Array
					String[] serviceArr = sb.toString().split(StringPool.COMMA);
					List<ServiceRecentDetailModel> serviceDetailList = new ArrayList<ServiceRecentDetailModel>();
					List<ServiceInfo> serviceList = ServiceInfoLocalServiceUtil.getByF_GID_SC(groupId, serviceArr);
					if (serviceList != null && serviceList.size() > 0) {
						int lengthFull = serviceList.size();
						results.setTotal(lengthFull);
						if (Validator.isNotNull(end) && end == 0) {
							for (int i = 0; i < lengthFull; i++) {
								String serviceCode = serviceArr[i];
								_log.info("serviceCode: " + serviceCode);
								for (ServiceInfo info : serviceList) {
									if (serviceCode.equals(info.getServiceCode())) {
										serviceDetailList.add(ServiceInfoUtils.mappingToServiceRecentDetailModel(info));
										break;
									}
								}
							}
						} else {
							int length = 0;
							if (lengthFull > end) {
								length = end - start + 1;
							} else {
								length = lengthFull - start;
							}
							for (int i = start; i < length; i++) {
								String serviceCode = serviceArr[i];
								for (ServiceInfo info : serviceList) {
									if (serviceCode.equals(info.getServiceCode())) {
										serviceDetailList.add(ServiceInfoUtils.mappingToServiceRecentDetailModel(info));
										break;
									}
								}
							}
						}
						results.getData().addAll(serviceDetailList);
					} else {
						results.setTotal(0);
					}
				}
			}

			return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();

		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response resolveConflictServiceInfo(
		HttpServletRequest request, HttpHeaders header, Company company,
		Locale locale, User user, ServiceContext serviceContext) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		// long userId = user.getUserId();
		ServiceInfoActions actions = new ServiceInfoActionsImpl();
		Indexer<ServiceInfo> indexer =
			IndexerRegistryUtil.nullSafeGetIndexer(ServiceInfo.class);

		LinkedHashMap<String, Object> params =
			new LinkedHashMap<String, Object>();
		params.put(Field.GROUP_ID, String.valueOf(groupId));

		// get JSON data deliverable
		JSONObject jsonData = actions.getServiceInfos(user.getUserId(), serviceContext.getCompanyId(), groupId, params,
				null, -1, -1, serviceContext);

		long total = jsonData.getLong(ConstantUtils.TOTAL);
		// JSONArray dossierArr = JSONFactoryUtil.createJSONArray();

		if (total > 0) {
			List<Document> lstDocuments = (List<Document>) jsonData.get(ConstantUtils.DATA);
			for (Document document : lstDocuments) {
				long serviceInfoId = GetterUtil.getLong(
					document.get(Field.ENTRY_CLASS_PK));
				long companyId =
					GetterUtil.getLong(document.get(Field.COMPANY_ID));
				String uid = document.get(Field.UID);
				ServiceInfo oldServiceInfo =
					ServiceInfoLocalServiceUtil.fetchServiceInfo(serviceInfoId);
				if (oldServiceInfo == null) {
					try {
						indexer.delete(companyId, uid);
					}
					catch (SearchException e) {
						_log.error(e);
					}
				}
			}
		}

		return Response.status(HttpURLConnection.HTTP_OK).entity(ConstantUtils.API_JSON_EMPTY).build();
	}

	@Override
	public Response updateFileTemplateOfServiceInfo(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String id, String templateNo,
			Attachment fileScript, Attachment fileReport, String templateName, String strFileEntryId, String eForm, String strFormScriptFileId,
			String strFormReportFileId, String eFormNoPattern, String eFormNamePattern) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		long userId = serviceContext.getUserId();

		ServiceFileTemplate serviceFileTemplate = null;
		long serviceInfoId = Long.valueOf(id);

		BackendAuth auth = new BackendAuthImpl();

		try {
			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			serviceFileTemplate = ServiceFileTemplateLocalServiceUtil.fetchByF_serviceInfoId_fileTemplateNo(serviceInfoId, templateNo);
			
			if (serviceFileTemplate != null) {
				//Check formScript
				long formScriptId = 0;
				long formReportId = 0;
				if (fileScript != null) {
					DataHandler handlerScript = fileScript.getDataHandler();

					if (handlerScript != null && handlerScript.getInputStream() != null) {
						FileEntry fileEntry = FileUploadUtils.uploadDossierFile(
								userId, groupId, 0, handlerScript.getInputStream(), 
								FilenameUtils.getExtension(handlerScript.getName()), 
								handlerScript.getName()
								.substring(handlerScript.getName().lastIndexOf(StringPool.PERIOD) + 1),
								handlerScript.getInputStream().available(), serviceContext);
						if (fileEntry != null) {
							formScriptId = fileEntry.getFileEntryId();
						}
					}
				}

				//Check formReport
				if (fileReport != null) {
					DataHandler handlerReport = fileReport.getDataHandler();

					if (handlerReport != null && handlerReport.getInputStream() != null) {
						FileEntry fileEntry = FileUploadUtils.uploadDossierFile(
								userId, groupId, 0, handlerReport.getInputStream(), 
								FilenameUtils.getExtension(handlerReport.getName()), 
								handlerReport.getName()
								.substring(handlerReport.getName().lastIndexOf(StringPool.PERIOD) + 1),
								handlerReport.getInputStream().available(), serviceContext);
						if (fileEntry != null) {
							formReportId = fileEntry.getFileEntryId();
						}
					}
				}

				//Update service file template
				if(Validator.isNotNull(formScriptId)) {
					serviceFileTemplate.setFormScriptFileId(formScriptId);
				}
				if(Validator.isNotNull(formReportId)) {
					serviceFileTemplate.setFormReportFileId(formReportId);
				}
				if (Validator.isNotNull(templateName))
					serviceFileTemplate.setTemplateName(templateName);

				long fileEntryId = GetterUtil.getLong(strFileEntryId);
				if (fileEntryId > 0)
					serviceFileTemplate.setFileEntryId(fileEntryId);

				if (Validator.isNotNull(eForm))
					serviceFileTemplate.setEForm(GetterUtil.getBoolean(eForm));

				long formScriptFileId = GetterUtil.getLong(strFormScriptFileId);
				if (formScriptFileId > 0)
					serviceFileTemplate.setFormScriptFileId(formScriptFileId);

				long formReportFileId = GetterUtil.getLong(strFormReportFileId);
				if (formReportFileId > 0)
					serviceFileTemplate.setFormReportFileId(formReportFileId);

				if (Validator.isNotNull(eFormNoPattern))
					serviceFileTemplate.setEFormNoPattern(eFormNoPattern);

				if (Validator.isNotNull(eFormNamePattern))
					serviceFileTemplate.setEFormNamePattern(eFormNamePattern);

				ServiceFileTemplateLocalServiceUtil.updateServiceFileTemplate(serviceFileTemplate);
				//
				FileTemplateModel result = ServiceInfoUtils.mappingToFileTemplateModel(serviceFileTemplate);
				return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();
			} else {
				return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity(MessageUtil.getMessage(ConstantUtils.API_JSON_MESSAGE_INTERNAL_SERVER_ERROR)).build();
			}
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Deprecated
	@Override
	public Response getServiceInfoMappingSuggest(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, ServiceInfoSearchModel query, Request requestCC) {

		ServiceInfoActions actions = new ServiceInfoActionsImpl();

		DVCQGIntegrationActionImpl dvcqgIntegrationActionImpl = new DVCQGIntegrationActionImpl();

		JSONObject results = JSONFactoryUtil.createJSONObject();

		try {
			if (query.getEnd() == 0) {

				query.setStart(-1);

				query.setEnd(-1);

			}

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			//params.put(Field.KEYWORD_SEARCH, query.getKeyword());
			//Keyword search like
			String keywordSearch = query.getKeyword();
			String keySearch = StringPool.BLANK;
			if (Validator.isNotNull(keywordSearch)) {
				keySearch = SpecialCharacterUtils.splitSpecial(keywordSearch);
			}
			params.put(Field.KEYWORD_SEARCH, keySearch);

			params.put(ServiceInfoTerm.ADMINISTRATION_CODE, query.getAdministration());
			params.put(ServiceInfoTerm.DOMAIN_CODE, query.getDomain());
			params.put(ServiceInfoTerm.MAX_LEVEL, query.getLevel());
			params.put(ServiceInfoTerm.PUBLIC_, query.getActive());

			Sort[] sorts = null;
			//			_log.info("sorts: "+query.getSort());
			if (Validator.isNotNull(query.getSort()) && (query.getSort().equals(DictItemTerm.SIBLING_AGENCY)
					|| query.getSort().equals(DictItemTerm.SIBLING_DOMAIN))) {
				sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + "_Number_sortable", Sort.INT_TYPE,
						GetterUtil.getBoolean(query.getOrder())) };
			} else if (Validator.isNotNull(query.getSort())) {
				sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE,
						GetterUtil.getBoolean(query.getOrder())) };
			} else {
				sorts = new Sort[] { SortFactoryUtil.create(ServiceInfoTerm.SERVICE_CODE_SEARCH + "_String_sortable",
						Sort.STRING_TYPE, GetterUtil.getBoolean(query.getOrder())) };
			}

			JSONObject jsonData = actions.getServiceInfos(serviceContext.getUserId(), serviceContext.getCompanyId(),
					groupId, params, sorts, query.getStart(), query.getEnd(), serviceContext);

			if (_serviceInfoDVCQGMap == null) {
				
				_serviceInfoDVCQGMap = dvcqgIntegrationActionImpl.getServiceInfoDVCQGMap(user, serviceContext);
					
			}

			List<Document> documents = (List<Document>) jsonData.get("data");
			JSONArray data = JSONFactoryUtil.createJSONArray();
			for (Document doc : documents) {
				JSONObject item = JSONFactoryUtil.createJSONObject();
				String serviceName = doc.get(ServiceInfoTerm.SERVICE_NAME);
				String serviceCode = doc.get(ServiceInfoTerm.SERVICE_CODE);
				String serviceCodeDVCQG = doc.get(ServiceInfoTerm.SERVICE_CODE_DVCQG);
				String administrationCode = doc.get(ServiceInfoTerm.ADMINISTRATION_CODE);
				String domainCode = doc.get(ServiceInfoTerm.DOMAIN_CODE);
				String administrationName = doc.get(ServiceInfoTerm.ADMINISTRATION_NAME);
				String domainName = doc.get(ServiceInfoTerm.DOMAIN_NAME);
				item.put("serviceInfoId", GetterUtil.getLong(doc.get(Field.ENTRY_CLASS_PK)));
				item.put("serviceName", serviceName);
				item.put("serviceCode", serviceCode);
				item.put("serviceCodeDVCQG", serviceCodeDVCQG);
				item.put("administrationCode", administrationCode);
				item.put("domainCode", domainCode);
				item.put("administrationName", administrationName);
				item.put("domainName", domainName);
				JSONArray mapping = dvcqgIntegrationActionImpl.getServiceInfoSimilarity(groupId, serviceCode,
						serviceName, _serviceInfoDVCQGMap);
				item.put("similarity", mapping);
				data.put(item);
			}

			//_log.info("jsonData.hit: "+jsonData.get("data"));
			results.put("total", jsonData.getInt("total"));
			results.put("data", data);

			return Response.status(200).entity(results.toJSONString()).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}

	}

	private static HashMap<String, String> _serviceInfoDVCQGMap = null;

	@Override
	public Response doSyncServiceInfoFromDVC(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, String method, String endpointPath, String body) {

		try {
			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
			ServerConfig serverConfig = ServerConfigLocalServiceUtil.getByCode(groupId, ServiceInfoTerm.CFG_SERVER_NO);

			if (serverConfig != null) {
				JSONObject configObj = JSONFactoryUtil.createJSONObject(serverConfig.getConfigs());
				String serverUrl = StringPool.BLANK;
				String authStrEnc = StringPool.BLANK;

				String apiUrl;// = StringPool.BLANK;

				StringBuilder sb = new StringBuilder();

				URL urlVal = null;
				String groupIdRequest = StringPool.BLANK;
				StringBuilder postData = new StringBuilder();
				JSONObject dataObj = JSONFactoryUtil.createJSONObject(body);
				Iterator<?> keys = dataObj.keys();
				while (keys.hasNext()) {
					String key = (String) keys.next();
					if (!"".equals(postData.toString())) {
						postData.append("&");
					}
					postData.append(key);
					postData.append("=");
					postData.append(dataObj.get(key));
				}

				if (configObj.has(SyncServerTerm.SERVER_USERNAME) && configObj.has(SyncServerTerm.SERVER_SECRET)
						&& configObj.has(SyncServerTerm.SERVER_URL) && configObj.has(SyncServerTerm.SERVER_GROUP_ID)) {
					authStrEnc = Base64.getEncoder().encodeToString((configObj.getString(SyncServerTerm.SERVER_USERNAME)
							+ ":" + configObj.getString(SyncServerTerm.SERVER_SECRET)).getBytes());

					serverUrl = configObj.getString(SyncServerTerm.SERVER_URL);
					groupIdRequest = configObj.getString(SyncServerTerm.SERVER_GROUP_ID);
				}

				apiUrl = serverUrl + endpointPath;
				if ("GET".equals(method)) {
					urlVal = new URL(apiUrl + "?" + postData.toString());
				} else {
					urlVal = new URL(apiUrl);
				}

				java.net.HttpURLConnection conn = (java.net.HttpURLConnection) urlVal.openConnection();
				conn.setRequestProperty("groupId", groupIdRequest);
				conn.setRequestMethod(method);
				conn.setRequestProperty("Accept", "application/json");
				conn.setRequestProperty("Authorization", "Basic " + authStrEnc);

				if ("POST".equals(method) || "PUT".equals(method)) {
					conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
					conn.setRequestProperty("Content-Length",
							"" + Integer.toString(postData.toString().getBytes().length));

					conn.setUseCaches(false);
					conn.setDoInput(true);
					conn.setDoOutput(true);

					OutputStream os = conn.getOutputStream();
					os.write(postData.toString().getBytes());
					os.close();
				}

				BufferedReader brf = new BufferedReader(new InputStreamReader(conn.getInputStream()));

				int cp;
				while ((cp = brf.read()) != -1) {
					sb.append((char) cp);
				}

				return Response.status(HttpURLConnection.HTTP_OK).entity(sb.toString()).build();

			} else {
				return Response.status(HttpURLConnection.HTTP_FORBIDDEN).entity("").build();
			}
		} catch (Exception e) {
			_log.error(e);
			return Response.status(HttpURLConnection.HTTP_FORBIDDEN).entity("").build();
		}

	}
}
