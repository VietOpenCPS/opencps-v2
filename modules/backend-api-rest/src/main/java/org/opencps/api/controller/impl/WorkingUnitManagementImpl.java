package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
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
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.controller.WorkingUnitManagement;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.controller.util.MessageUtil;
import org.opencps.api.controller.util.WorkingUnitUtils;
import org.opencps.api.workingunit.model.DataSearchModel;
import org.opencps.api.workingunit.model.WorkingUnitInputModel;
import org.opencps.api.workingunit.model.WorkingUnitModel;
import org.opencps.api.workingunit.model.WorkingUnitResults;
import org.opencps.usermgt.action.WorkingUnitInterface;
import org.opencps.usermgt.action.impl.WorkingUnitActions;
import org.opencps.usermgt.constants.WorkingUnitTerm;
import org.opencps.usermgt.model.WorkingUnit;
import org.opencps.usermgt.service.WorkingUnitLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;

public class WorkingUnitManagementImpl implements WorkingUnitManagement {

	private static final Log _log = LogFactoryUtil.getLog(WorkingUnitManagementImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public Response getWorkingUnits(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DataSearchModel query) {

		WorkingUnitInterface actions = new WorkingUnitActions();
		WorkingUnitResults result = new WorkingUnitResults();

		try {

			if (query.getEnd() == 0) {

				query.setStart(QueryUtil.ALL_POS);

				query.setEnd(QueryUtil.ALL_POS);

			}

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(ConstantUtils.API_KEYWORDS_KEY, query.getKeywords());
			params.put(WorkingUnitTerm.PARENT_WORKING_UNIT_ID, query.getParent());
			
			Sort[] sorts = new Sort[] {
					SortFactoryUtil.create(ConstantUtils.API_TREEINDEX_SORTABLE, Sort.STRING_TYPE, Boolean.valueOf(query.getOrder())) };

			JSONObject jsonData = actions.getWorkingUnits(user.getUserId(), company.getCompanyId(), groupId, params,
					sorts, query.getStart(), query.getEnd(), serviceContext);

			result.setTotal(jsonData.getLong(ConstantUtils.TOTAL));
			result.getWorkingUnitModel()
					.addAll(WorkingUnitUtils.mapperWorkingUnitList((List<Document>) jsonData.get(ConstantUtils.DATA)));

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response read(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, long id) {
		WorkingUnit workingUnit = WorkingUnitLocalServiceUtil.fetchWorkingUnit(id);

		if (Validator.isNotNull(workingUnit)) {

			WorkingUnitModel workingUnitModel = WorkingUnitUtils.mapperWorkingUnitModel(workingUnit);

			return Response.status(HttpURLConnection.HTTP_OK).entity(workingUnitModel).build();

		} else {

			ErrorMsg error = new ErrorMsg();

			error.setMessage(MessageUtil.getMessage(ConstantUtils.API_MESSAGE_NOTFOUND));
			error.setCode(HttpURLConnection.HTTP_NOT_FOUND);
			error.setDescription(MessageUtil.getMessage(ConstantUtils.API_MESSAGE_NOTFOUND));

			return Response.status(HttpURLConnection.HTTP_NOT_FOUND).entity(error).build();

		}
	}

	@Override
	public Response create(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, WorkingUnitInputModel input) {
		WorkingUnitInterface actions = new WorkingUnitActions();
		WorkingUnitModel workingUnitModel = new WorkingUnitModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			String name = HtmlUtil.escape(input.getName());
			String enName = HtmlUtil.escape(input.getEnName());
			String govAgencyCode = HtmlUtil.escape(input.getGovAgencyCode());
			String address = HtmlUtil.escape(input.getAddress());
			String telNo = HtmlUtil.escape(input.getTelNo());
			String faxNo = HtmlUtil.escape(input.getFaxNo()); 
			String email = HtmlUtil.escape(input.getEmail());
			String website = HtmlUtil.escape(input.getWebsite());
			
			WorkingUnit workingUnit = actions.create(user.getUserId(), company.getCompanyId(), groupId,
					address, email, enName, faxNo, govAgencyCode,
					name, telNo, website, input.getParentWorkingUnitId(),
					input.getSibling(), input.getCeremonyDate(), serviceContext);

			workingUnitModel = WorkingUnitUtils.mapperWorkingUnitModel(workingUnit);

			return Response.status(HttpURLConnection.HTTP_OK).entity(workingUnitModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response update(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, long id, WorkingUnitInputModel input) {
		WorkingUnitInterface actions = new WorkingUnitActions();
		WorkingUnitModel workingUnitModel = new WorkingUnitModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			String name = HtmlUtil.escape(input.getName());
			String enName = HtmlUtil.escape(input.getEnName());
			String govAgencyCode = HtmlUtil.escape(input.getGovAgencyCode());
			String address = HtmlUtil.escape(input.getAddress());
			String telNo = HtmlUtil.escape(input.getTelNo());
			String faxNo = HtmlUtil.escape(input.getFaxNo()); 
			String email = HtmlUtil.escape(input.getEmail());
			String website = HtmlUtil.escape(input.getWebsite());
			
			WorkingUnit workingUnit = actions.update(user.getUserId(), company.getCompanyId(), groupId, id,
					address, email, enName, faxNo, govAgencyCode,
					name, telNo, website, input.getParentWorkingUnitId(),
					input.getSibling(), input.getCeremonyDate(), serviceContext);

			workingUnitModel = WorkingUnitUtils.mapperWorkingUnitModel(workingUnit);

			return Response.status(HttpURLConnection.HTTP_OK).entity(workingUnitModel).build();

		} catch (Exception e) {

			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response delete(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, long id) {
		try {

			WorkingUnitLocalServiceUtil.deleteWorkingUnit(id, serviceContext);

			return Response.status(HttpURLConnection.HTTP_OK).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response updateLogo(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, Attachment attachment, String fileName, String fileType,
			long fileSize) {
		WorkingUnitInterface actions = new WorkingUnitActions();
		InputStream inputStream = null;

		try {
			DataHandler dataHandler = attachment.getDataHandler();

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			inputStream = dataHandler.getInputStream();

			File file = actions.updateLogo(user.getUserId(), company.getCompanyId(), groupId, id, inputStream,
					fileName, fileType, fileSize, ConstantUtils.EMPLOYEE_WORKINGUNIT_FOLDER, ConstantUtils.EMPLOYEE_WORKINGUNIT_DESC, serviceContext);

			FileEntry fileEntry = actions.getFileEntry(id, serviceContext);

			String fileNameRespone = Validator.isNotNull(fileEntry) ? fileEntry.getFileName() : StringPool.BLANK;

			ResponseBuilder responseBuilder = Response.ok((Object) file);
			String attachmentFilename = String.format(MessageUtil.getMessage(ConstantUtils.ATTACHMENT_FILENAME), fileNameRespone);
			responseBuilder.header(ConstantUtils.CONTENT_DISPOSITION, attachmentFilename)
					.header(ConstantUtils.CONTENT_TYPE, fileEntry.getMimeType());

			return responseBuilder.build();
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);

		} finally {
			try {
				if (inputStream != null)
					inputStream.close();
			} catch (IOException e) {
				_log.error(e);
			}
		}
	}

	@Override
	public Response getLogo(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, long id) {
		WorkingUnitInterface actions = new WorkingUnitActions();

		try {

			File file = actions.getLogo(id, serviceContext);

			FileEntry fileEntry = actions.getFileEntry(id, serviceContext);

			String fileName = Validator.isNotNull(fileEntry) ? fileEntry.getFileName() : StringPool.BLANK;

			ResponseBuilder responseBuilder = Response.ok((Object) file);
			
			String attachmentFilename = String.format(MessageUtil.getMessage(ConstantUtils.ATTACHMENT_FILENAME), fileName);
			responseBuilder.header(ConstantUtils.CONTENT_DISPOSITION, attachmentFilename)
					.header(ConstantUtils.CONTENT_TYPE, fileEntry.getMimeType());

			return responseBuilder.build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

}
