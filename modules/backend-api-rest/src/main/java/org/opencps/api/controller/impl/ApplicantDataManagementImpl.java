package org.opencps.api.controller.impl;

import com.liferay.document.library.kernel.service.DLAppLocalServiceUtil;
import com.liferay.document.library.kernel.service.DLFileEntryLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.opencps.api.applicantdata.model.ApplicantDataDetailModel;
import org.opencps.api.applicantdata.model.ApplicantDataResultsModel;
import org.opencps.api.applicantdata.model.ApplicantDataSearchModel;
import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.controller.ApplicantDataManagement;
import org.opencps.api.controller.util.ApplicantDataUtils;
import org.opencps.api.controller.util.MessageUtil;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.usermgt.action.ApplicantDataActions;
import org.opencps.usermgt.action.impl.ApplicantDataActionsImpl;
import org.opencps.usermgt.constants.ApplicantDataTerm;
import org.opencps.usermgt.constants.ApplicantTerm;
import org.opencps.usermgt.model.ApplicantData;
import org.opencps.usermgt.service.ApplicantDataLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;

public class ApplicantDataManagementImpl implements ApplicantDataManagement {

	@Override
	public Response addApplicantData(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, Attachment file, String fileTemplateNo, String fileNo, String fileName,
			String applicantIdNo, String status) {
		//Mặc định groupId =0
		long groupId = ApplicantTerm.GROUP_ID_DEFAULT;
		DataHandler dataHandler = (file != null) ? file.getDataHandler() : null;
		ApplicantData applicantData = null;
		
		try {
			int statusInt = Validator.isNotNull(status) ? Integer.parseInt(status) : 0;
			
			applicantData = ApplicantDataLocalServiceUtil.createApplicantData(groupId, fileTemplateNo, fileNo, fileName, applicantIdNo, statusInt, dataHandler.getName(), dataHandler.getInputStream(), serviceContext);
			ApplicantDataDetailModel result = ApplicantDataUtils.mappingToApplicantDataModel(applicantData);

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response deleteApplicantData(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {
		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			ApplicantData applicantData = ApplicantDataLocalServiceUtil.deleteApplicantData(id);
			
			ApplicantDataDetailModel result =
				ApplicantDataUtils.mappingToApplicantDataModel(applicantData);

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		}
		catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getApplicantDatas(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, ApplicantDataSearchModel query) {
		BackendAuth auth = new BackendAuthImpl();
		ApplicantDataActions actions = new ApplicantDataActionsImpl();
		ApplicantDataResultsModel results = new ApplicantDataResultsModel();
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			if (query.getEnd() == null || query.getEnd() == 0) {

				query.setStart(QueryUtil.ALL_POS);

				query.setEnd(QueryUtil.ALL_POS);

			}
			//Mặc định groupId = 0
			long groupId = ApplicantTerm.GROUP_ID_DEFAULT;

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(ApplicantDataTerm.APPLICANT_ID_NO, query.getApplicantIdNo());
			params.put(ApplicantDataTerm.STATUS, query.getStatus());
			params.put(ApplicantDataTerm.FILE_NO, query.getFileNo());
			params.put(ApplicantDataTerm.FILE_TEMPLATE_NO, query.getFileTemplateNo());
			
			params.put(Field.KEYWORD_SEARCH, query.getKeyword());
			
			String querySort = String.format(MessageUtil.getMessage(ConstantUtils.QUERY_SORT), query.getSort());
			
			Sort[] sorts = new Sort[] { SortFactoryUtil.create(querySort, Sort.STRING_TYPE,
					GetterUtil.getBoolean(query.getOrder())) };

			JSONObject jsonData = actions.getApplicantDatas(serviceContext.getUserId(),
					serviceContext.getCompanyId(), groupId, params, sorts, query.getStart(), query.getEnd(),
					serviceContext);

			results.setTotal(jsonData.getInt(ConstantUtils.TOTAL));
			if (jsonData != null && jsonData.getInt(ConstantUtils.TOTAL) > 0) {
				results.getData().addAll(ApplicantDataUtils.mappingToApplicantDataResults((List<Document>) jsonData.get(ConstantUtils.DATA), request));
			}

			return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}	
	}

	@Override
	public Response updateApplicantData(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, Attachment file, String fileTemplateNo, String fileNo, String fileName,
			String applicantIdNo, String status) {
		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		DataHandler dataHandler = (file != null) ? file.getDataHandler() : null;
		ApplicantData applicantData = null;
		
		try {
			int statusInt = Validator.isNotNull(status) ? Integer.parseInt(status) : 0;
			applicantData = ApplicantDataLocalServiceUtil.updateApplicantData(groupId, id, fileTemplateNo, fileNo, fileName, applicantIdNo, statusInt, dataHandler.getName(), dataHandler.getInputStream(), serviceContext);
			ApplicantDataDetailModel result = ApplicantDataUtils.mappingToApplicantDataModel(applicantData);

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response activeApplicantData(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {
		ApplicantData applicantData = null;
		
		try {
			applicantData = ApplicantDataLocalServiceUtil.active(id);
			ApplicantDataDetailModel result = ApplicantDataUtils.mappingToApplicantDataModel(applicantData);

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response inactiveApplicantData(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id) {
		ApplicantData applicantData = null;
		
		try {
			applicantData = ApplicantDataLocalServiceUtil.inActive(id);
			ApplicantDataDetailModel result = ApplicantDataUtils.mappingToApplicantDataModel(applicantData);

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getApplicantDataFilePreview(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id) {
		BackendAuth auth = new BackendAuthImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			ApplicantData applicantData = ApplicantDataLocalServiceUtil.fetchApplicantData(id);
			
			if (applicantData != null) {
				long fileEntryId = applicantData.getFileEntryId();
				if (fileEntryId > 0) {
					FileEntry fileEntry = DLAppLocalServiceUtil.getFileEntry(fileEntryId);

					File file = DLFileEntryLocalServiceUtil.getFile(fileEntry.getFileEntryId(),
							fileEntry.getVersion(), true);

					ResponseBuilder responseBuilder = Response.ok((Object) file);
					String attachmentFilename = String.format(MessageUtil.getMessage(ConstantUtils.ATTACHMENT_FILENAME), fileEntry.getFileName());
					responseBuilder.header(ConstantUtils.CONTENT_DISPOSITION,
							attachmentFilename);
					responseBuilder.header(HttpHeaders.CONTENT_TYPE, fileEntry.getMimeType());
	
					return responseBuilder.build();
				}
			}
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
		return Response.status(HttpURLConnection.HTTP_NO_CONTENT).build();
	}

}
