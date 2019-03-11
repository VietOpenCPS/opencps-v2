package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Document;
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

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.opencps.api.controller.OfficeSiteManagement;
import org.opencps.api.controller.util.OfficeSiteUtils;
import org.opencps.api.error.model.ErrorMsg;
import org.opencps.api.officesite.model.DataSearchModel;
import org.opencps.api.officesite.model.OfficeSiteInputModel;
import org.opencps.api.officesite.model.OfficeSiteModel;
import org.opencps.api.officesite.model.OfficeSiteResults;
import org.opencps.usermgt.action.OfficeSiteInterface;
import org.opencps.usermgt.action.impl.OfficeSiteActions;
import org.opencps.usermgt.model.OfficeSite;
import org.opencps.usermgt.service.OfficeSiteLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;

public class OfficeSiteManagementImpl implements OfficeSiteManagement {

	@SuppressWarnings("unchecked")
	@Override
	public Response getOfficeSites(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DataSearchModel query) {
		OfficeSiteInterface actions = new OfficeSiteActions();
		OfficeSiteResults result = new OfficeSiteResults();
		try {

			if (query.getEnd() == 0) {

				query.setStart(-1);

				query.setEnd(-1);

			}

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put("groupId", String.valueOf(groupId));
			params.put("keywords", query.getKeywords());

			Sort[] sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + "_sortable", Sort.STRING_TYPE,
					Boolean.valueOf(query.getOrder())) };

			JSONObject jsonData = actions.getOfficeSites(user.getUserId(), company.getCompanyId(), groupId, params,
					sorts, query.getStart(), query.getEnd(), serviceContext);

			result.setTotal(jsonData.getLong("total"));
			result.getOfficeSiteModel()
					.addAll(OfficeSiteUtils.mapperOfficeSiteList((List<Document>) jsonData.get("data")));

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addOfficeSite(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, OfficeSiteInputModel input) {
		OfficeSiteInterface actions = new OfficeSiteActions();
		OfficeSiteModel officeSiteModel = new OfficeSiteModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			OfficeSite officeSite = actions.create(user.getUserId(), company.getCompanyId(), groupId,
					input.getAddress(), input.getAdminEmail(), input.getEmail(), input.getEnName(), input.getFaxNo(),
					input.getGovAgencyCode(), input.getName(), input.getPreferences(), input.getSiteGroupId(),
					input.getTelNo(), input.getWebsite(), serviceContext);

			officeSiteModel = OfficeSiteUtils.mapperOfficeSiteModel(officeSite);

			return Response.status(200).entity(officeSiteModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getOfficeSite(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {

		OfficeSite officeSite = OfficeSiteLocalServiceUtil.fetchOfficeSite(id);

		if (Validator.isNotNull(officeSite)) {

			OfficeSiteModel officeSiteModel = OfficeSiteUtils.mapperOfficeSiteModel(officeSite);

			return Response.status(200).entity(officeSiteModel).build();

		} else {

			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(404).entity(error).build();

		}
	}

	@Override
	public Response updateOfficeSite(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, OfficeSiteInputModel input) {
		OfficeSiteInterface actions = new OfficeSiteActions();
		OfficeSiteModel officeSiteModel = new OfficeSiteModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			OfficeSite officeSite = actions.update(user.getUserId(), company.getCompanyId(), groupId, id,
					input.getAddress(), input.getAdminEmail(), input.getEmail(), input.getEnName(), input.getFaxNo(),
					input.getGovAgencyCode(), input.getName(), input.getPreferences(), input.getSiteGroupId(),
					input.getTelNo(), input.getWebsite(), serviceContext);

			officeSiteModel = OfficeSiteUtils.mapperOfficeSiteModel(officeSite);

			return Response.status(200).entity(officeSiteModel).build();

		} catch (Exception e) {

			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response deleteOfficeSite(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {
		try {

			OfficeSiteLocalServiceUtil.deleteOfficeSite(id, serviceContext);

			return Response.status(200).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response getOfficeSiteLogo(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id) {
		OfficeSiteInterface actions = new OfficeSiteActions();

		try {

			File file = actions.getOfficeSiteLogo(id, serviceContext);

			FileEntry fileEntry = actions.getFileEntry(id, serviceContext);

			String fileName = Validator.isNotNull(fileEntry) ? fileEntry.getFileName() : StringPool.BLANK;

			ResponseBuilder responseBuilder = Response.ok((Object) file);

			responseBuilder.header("Content-Disposition", "attachment; filename=\"" + fileName + "\"")
					.header("Content-Type", fileEntry.getMimeType());

			return responseBuilder.build();

		} catch (Exception e) {
			_log.error(e);

			ErrorMsg error = new ErrorMsg();
			error.setMessage("file not found!");
			error.setCode(404);
			error.setDescription("file not found!");
			return Response.status(404).entity(error).build();
		}
	}

	@Override
	public Response uploadOfficeSiteLogo(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, long id, Attachment attachment, String fileName, String fileType,
			long fileSize) {
		OfficeSiteInterface actions = new OfficeSiteActions();
		InputStream inputStream = null;

		try {
			DataHandler dataHandler = attachment.getDataHandler();

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			inputStream = dataHandler.getInputStream();

			File file = actions.uploadOfficeSiteLogo(user.getUserId(), company.getCompanyId(), groupId, id, inputStream,
					fileName, fileType, fileSize, "OfficeSite/", "OfficeSite file upload", serviceContext);

			FileEntry fileEntry = actions.getFileEntry(id, serviceContext);

			String fileNameRespone = Validator.isNotNull(fileEntry) ? fileEntry.getFileName() : StringPool.BLANK;

			ResponseBuilder responseBuilder = Response.ok((Object) file);

			responseBuilder.header("Content-Disposition", "attachment; filename=\"" + fileNameRespone + "\"")
					.header("Content-Type", fileEntry.getMimeType());

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
	public Response getOfficeSitePreferences(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id) {
		OfficeSite officeSite = OfficeSiteLocalServiceUtil.fetchOfficeSite(id);

		if (Validator.isNotNull(officeSite)) {

			return Response.status(200).entity(officeSite.getPreferences()).build();

		} else {

			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(404).entity(error).build();

		}
	}

	@Override
	public Response getOfficeSitePreferencesByKey(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, String key) {
		OfficeSite officeSite = OfficeSiteLocalServiceUtil.fetchOfficeSite(id);
		
		if (Validator.isNotNull(officeSite)) {

			JSONObject jsonObject;

			String data = StringPool.BLANK;

			try {
				
				jsonObject = JSONFactoryUtil.createJSONObject(officeSite.getPreferences());
				
				data = jsonObject.getString(key);
				
			} catch (JSONException e) {
				_log.error(e);
			}

			return Response.status(200).entity(data).build();

		} else {

			ErrorMsg error = new ErrorMsg();

			error.setMessage("not found!");
			error.setCode(404);
			error.setDescription("not found!");

			return Response.status(404).entity(error).build();

		}
	}

	private static final Log _log = LogFactoryUtil.getLog(OfficeSiteManagementImpl.class);

	@Override
	public Response updateOfficeSitePreferences(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, OfficeSiteInputModel input) {
		OfficeSiteInterface actions = new OfficeSiteActions();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			OfficeSite officeSite = actions.updateOfficeSitePreferences(user.getUserId(), company.getCompanyId(), groupId, id,
					input.getPreferences(), serviceContext);

			return Response.status(200).entity(officeSite.getPreferences()).build();

		} catch (Exception e) {

			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response updateOfficeSitePreferencesByKey(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, String key, String value) {
		OfficeSiteInterface actions = new OfficeSiteActions();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));

			OfficeSite officeSite = actions.updateOfficeSitePreferencesByKey(user.getUserId(), company.getCompanyId(), groupId, id,
					key, value, serviceContext);

			return Response.status(200).entity(officeSite.getPreferences()).build();

		} catch (Exception e) {

			return BusinessExceptionImpl.processException(e);
		}
	}
}
