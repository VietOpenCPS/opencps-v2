package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
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

import java.io.File;
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
import org.opencps.api.controller.FileAttachManagement;
import org.opencps.api.controller.exception.ErrorMsg;
import org.opencps.api.controller.util.FileAttachUtils;
import org.opencps.api.fileattach.model.DataSearchModel;
import org.opencps.api.fileattach.model.FileAttachInputModel;
import org.opencps.api.fileattach.model.FileAttachModel;
import org.opencps.api.fileattach.model.FileAttachResults;
import org.opencps.api.fileattach.model.FileVersionResults;
import org.opencps.datamgt.action.FileAttachInterface;
import org.opencps.datamgt.action.impl.FileAttachActions;
import org.opencps.datamgt.constants.FileAttachTerm;
import org.opencps.datamgt.model.FileAttach;
import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.action.util.ReadFilePropertiesUtils;
import org.opencps.dossiermgt.constants.DossierFileTerm;

import backend.auth.api.exception.BusinessExceptionImpl;

public class FileAttachManagementImpl implements FileAttachManagement {

	private static final Log _log = LogFactoryUtil.getLog(FileAttachManagementImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public Response getFileAttachs(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, String className, String classPK, DataSearchModel query) {

		FileAttachInterface actions = new FileAttachActions();

		FileAttachResults result = new FileAttachResults();

		try {

			if (query.getEnd() == 0) {

				query.setStart(-1);

				query.setEnd(-1);

			}

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			LinkedHashMap<String, Object> params = new LinkedHashMap<String, Object>();

			params.put(Field.GROUP_ID, String.valueOf(groupId));
			params.put(Field.KEYWORD_SEARCH, query.getKeywords());
			params.put(FileAttachTerm.CLASS_NAME, className);
			params.put(FileAttachTerm.CLASS_PK, classPK);

			Sort[] sorts = new Sort[] { SortFactoryUtil.create(query.getSort() + ReadFilePropertiesUtils.get(ConstantUtils.SORT_PATTERN), Sort.STRING_TYPE,
					Boolean.valueOf(query.getOrder())) };

			JSONObject jsonData = actions.getFileAttachs(user.getUserId(), company.getCompanyId(), groupId, params,
					sorts, query.getStart(), query.getEnd(), serviceContext);

			result.setTotal(jsonData.getLong(ConstantUtils.TOTAL));
			result.getFileAttachModel()
					.addAll(FileAttachUtils.mapperFileAttachList((List<Document>) jsonData.get(ConstantUtils.DATA)));

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response read(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, long id) {

		FileAttachInterface actions = new FileAttachActions();
		try {

			File file = actions.read(id, serviceContext);

			FileEntry fileEntry = actions.getFileEntry(id, serviceContext);

			if (file != null && fileEntry != null) {
				String fileName = fileEntry.getFileName();

				ResponseBuilder responseBuilder = Response.ok((Object) file);

				responseBuilder.header(ReadFilePropertiesUtils.get(ConstantUtils.TYPE_DISPOSITON), ReadFilePropertiesUtils.get(ConstantUtils.VALUE_PATTERN_FILENAME) + fileName + "\"")
						.header(ConstantUtils.CONTENT_TYPE, fileEntry.getMimeType());

				return responseBuilder.build();
			} else {
				ErrorMsg error = new ErrorMsg();

				error.setMessage(ReadFilePropertiesUtils.get(ConstantUtils.ERROR_NOT_PERMISSION));
				error.setCode(404);
				error.setDescription(ReadFilePropertiesUtils.get(ConstantUtils.ERROR_NOT_PERMISSION));
				return Response.status(404).entity(error).build();
			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response create(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, FileAttachInputModel input) {

		FileAttachInterface actions = new FileAttachActions();
		FileAttachModel fileAttachModel = new FileAttachModel();

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			FileAttach fileAttach = actions.create(user.getUserId(), company.getCompanyId(), groupId,
					input.getClassName(), input.getClassPK(), StringPool.BLANK, StringPool.BLANK, 0, input.getSource(),
					input.getSourceUrl(), 0, input.getFileName(), serviceContext);

			fileAttachModel = FileAttachUtils.mapperFileAttachModel(fileAttach);

			return Response.status(200).entity(fileAttachModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response delete(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, long id) {

		FileAttachInterface actions = new FileAttachActions();
		try {

			actions.delete(id, serviceContext);

			return Response.status(200).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response upload(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, String className, String classPK, Attachment attachment, String fileName,
			String fileType, long fileSize) {

		FileAttachInterface actions = new FileAttachActions();
		FileAttachModel fileAttachModel = new FileAttachModel();
		InputStream inputStream = null;

		try {

			DataHandler dataHandler = attachment.getDataHandler();

			inputStream = dataHandler.getInputStream();

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			FileAttach fileAttach = actions.upload(user.getUserId(), company.getCompanyId(), groupId, className,
					classPK, inputStream, fileName, fileType, fileSize, StringPool.BLANK, StringPool.BLANK,
					serviceContext);

			fileAttachModel = FileAttachUtils.mapperFileAttachModel(fileAttach);

			return Response.status(200).entity(fileAttachModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (Exception io) {
					_log.error(io);
				}
			}
		}
	}

	@Override
	public Response update(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, long id, Attachment attachment, String fileName, String fileType,
			long fileSize) {

		FileAttachInterface actions = new FileAttachActions();
		FileAttachModel fileAttachModel = new FileAttachModel();

		InputStream inputStream = null;

		try {

			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

			DataHandler dataHandler = attachment.getDataHandler();

			inputStream = dataHandler.getInputStream();

			FileAttach fileAttach = actions.update(user.getUserId(), company.getCompanyId(), groupId, id, inputStream,
					fileName, fileType, fileSize, StringPool.BLANK, StringPool.BLANK, serviceContext);

			fileAttachModel = FileAttachUtils.mapperFileAttachModel(fileAttach);

			return Response.status(200).entity(fileAttachModel).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (Exception io) {
					_log.error(io);
				}
			}
		}
	}

	@Override
	public Response getFileAttachVersions(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, long fileAttachId, ServiceContext serviceContext) {

		FileAttachInterface actions = new FileAttachActions();

		FileVersionResults result = new FileVersionResults();

		try {

			JSONObject jsonData = actions.getFileAttachVersions(fileAttachId, serviceContext);

			long total = jsonData.getLong(ConstantUtils.TOTAL);
			String fileName = jsonData.getString(DossierFileTerm.FILE_NAME);
			JSONArray versions = jsonData.getJSONArray(Field.VERSION);

			result.setTotal(total);

			result.getData().addAll(

					FileAttachUtils.mapperFileAttachVersionList(fileName, versions));

			return Response.status(200).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response read(HttpServletRequest request, HttpHeaders header, Company company, Locale locale, User user,
			ServiceContext serviceContext, long id, String version) {

		FileAttachInterface actions = new FileAttachActions();
		try {

			File file = actions.read(id, version, serviceContext);

			FileEntry fileEntry = actions.getFileEntry(id, serviceContext);

			if (file != null && fileEntry != null) {
				String fileName = fileEntry.getFileName();

				ResponseBuilder responseBuilder = Response.ok((Object) file);

				responseBuilder.header(ReadFilePropertiesUtils.get(ConstantUtils.TYPE_DISPOSITON), ReadFilePropertiesUtils.get(ConstantUtils.VALUE_PATTERN_FILENAME) + fileName + "\"")
						.header(ConstantUtils.CONTENT_TYPE, fileEntry.getMimeType());

				return responseBuilder.build();
			} else {
				ErrorMsg error = new ErrorMsg();

				error.setMessage(ReadFilePropertiesUtils.get(ConstantUtils.ERROR_NOT_PERMISSION));
				error.setCode(404);
				error.setDescription(ReadFilePropertiesUtils.get(ConstantUtils.ERROR_NOT_PERMISSION));
				return Response.status(404).entity(error).build();
			}
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

}
