package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.GroupLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.activation.DataHandler;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.commons.io.IOUtils;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.controller.ImportDataManagement;
import org.opencps.api.controller.util.DossierFileUtils;
import org.opencps.api.controller.util.DossierUtils;
import org.opencps.api.controller.util.ImportDataUtils;
import org.opencps.api.controller.util.ImportZipFileUtils;
import org.opencps.api.dossier.model.DossierPublishImportModel;
import org.opencps.api.dossierfile.model.DossierFileModel;
import org.opencps.auth.api.BackendAuth;
import org.opencps.auth.api.BackendAuthImpl;
import org.opencps.auth.api.exception.UnauthenticationException;
import org.opencps.dossiermgt.action.DossierActions;
import org.opencps.dossiermgt.action.DossierFileActions;
import org.opencps.dossiermgt.action.impl.DossierActionsImpl;
import org.opencps.dossiermgt.action.impl.DossierFileActionsImpl;
import org.opencps.dossiermgt.action.util.DossierNumberGenerator;
import org.opencps.dossiermgt.model.Dossier;
import org.opencps.dossiermgt.model.DossierFile;
import org.opencps.dossiermgt.model.DossierMark;
import org.opencps.dossiermgt.model.DossierPart;
import org.opencps.dossiermgt.model.DossierTemplate;
import org.opencps.dossiermgt.service.DossierFileLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierMarkLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierPartLocalServiceUtil;
import org.opencps.dossiermgt.service.DossierTemplateLocalServiceUtil;
import org.opencps.usermgt.model.Question;
import org.opencps.usermgt.service.AnswerLocalServiceUtil;
import org.opencps.usermgt.service.QuestionLocalServiceUtil;

import backend.auth.api.exception.BusinessExceptionImpl;

public class ImportDataManagementImpl implements ImportDataManagement{

	private static final Log _log = LogFactoryUtil.getLog(ImportDataManagementImpl.class);
	@Override
	public Response addDossierFileByDossierId(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, Attachment file, String id, String referenceUid,
			String dossierTemplateNo, String dossierPartNo, String dossierPartType, String fileTemplateNo,
			String displayName, String fileType, String isSync) {

		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		_log.info("START CREATE DOSSIER FILE: "+groupId);
		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			long dossierId = GetterUtil.getLong(id);
			_log.info("dossierId: "+dossierId);
			Dossier dossier = null;

			if (dossierId != 0) {
				dossier = DossierLocalServiceUtil.fetchDossier(dossierId);
				if (Validator.isNull(dossier)) {
					dossier = DossierLocalServiceUtil.getByRef(groupId, id);
				}
			} else {
				dossier = DossierLocalServiceUtil.getByRef(groupId, id);
			}

			DataHandler dataHandler = (file != null) ? file.getDataHandler() : null;
			DossierFileActions action = new DossierFileActionsImpl();


			//List<DossierPart> lstParts = DossierPartLocalServiceUtil.getByTemplateNo(groupId, dossier.getDossierTemplateNo());
//			for (DossierPart dp : lstParts) {
//				if (dp.getPartNo().equals(dossierPartNo)) {
//					fileTemplateNo = dp.getFileTemplateNo();
//					dossierTemplateNo = dossier.getDossierTemplateNo();
//				}
//			}
			
			DossierFile oldDossierFile = null;
			if (Validator.isNotNull(dossierPartNo) && Validator.isNotNull(dossierPartType) && Validator.isNotNull(displayName)) {
				oldDossierFile = DossierFileLocalServiceUtil.getByG_DID_PART_NAME(groupId, dossierId, dossierPartNo,
						GetterUtil.getInteger(dossierPartType), displayName);
			}
				if (oldDossierFile != null) {
					_log.info("__Start update file at:" + new Date());
					DossierFile dossierFile =  null;
					
					if (dataHandler != null && dataHandler.getInputStream() != null) {
						dossierFile = action.updateDossierFile(groupId, 
								dossier.getDossierId(), 
								oldDossierFile.getReferenceUid(), 
								displayName, 
								dataHandler.getInputStream(), serviceContext);
					} else {
						dossierFile = action.updateDossierFile(groupId, 
								dossier.getDossierId(), 
								oldDossierFile.getReferenceUid(), 
								displayName, 
								null, serviceContext);
					}
					_log.info("__End update file at:" + new Date());
					
					dossierFile.setRemoved(false);
					_log.info("__Start update dossier file at:" + new Date());
					DossierFileLocalServiceUtil.updateDossierFile(dossierFile);
					_log.info("__End update dossier file at:" + new Date());

					DossierFileModel result = DossierFileUtils.mappingToDossierFileModel(dossierFile);
					
					_log.info("__End bind to dossierFile" + new Date());

					return Response.status(200).entity(result).build();
				}
				else {
					_log.info("__Start add file at:" + new Date());
					DossierFile dossierFile =  null;
					
					if (dataHandler != null && dataHandler.getInputStream() != null) {
						dossierFile = action.addDossierFile(groupId, dossier.getDossierId(), referenceUid, dossierTemplateNo,
								dossierPartNo, fileTemplateNo, displayName, dataHandler.getName(), 0,
								dataHandler.getInputStream(), fileType, isSync, serviceContext);
					} else {
						dossierFile = action.addDossierFile(groupId, dossier.getDossierId(), referenceUid, dossierTemplateNo,
								dossierPartNo, fileTemplateNo, displayName, displayName, 0, null, fileType, isSync,
								serviceContext);
					}
					
					_log.info("__End add file at:" + new Date());
					dossierFile.setRemoved(false);
					_log.info("__Start update dossier file at:" + new Date());
		
					DossierFileLocalServiceUtil.updateDossierFile(dossierFile);
		
					_log.info("__End update dossier file at:" + new Date());
		
					DossierFileModel result = DossierFileUtils.mappingToDossierFileModel(dossierFile);
					
					_log.info("__End bind to dossierFile" + new Date());
		
					return Response.status(200).entity(result).build();
				}
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@Override
	public Response addDossierImportData(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DossierPublishImportModel input) {

		_log.info("START PUPISH");
		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		BackendAuth auth = new BackendAuthImpl();
		DossierActions actions = new DossierActionsImpl();

		try {

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			//Get input
			String referenceUid = input.getReferenceUid();
			long createDateLong = GetterUtil.getLong(input.getCreateDate());
			long modifiedDateLong = GetterUtil.getLong(input.getModifiedDate());
			int counter = 0;
			String serviceCode = input.getServiceCode();
			String serviceName = input.getServiceName();
			String govAgencyCode = input.getGovAgencyCode();
			String govAgencyName = input.getGovAgencyName();
			String applicantName = input.getApplicantName();
			String applicantIdType = input.getApplicantIdType();
			String applicantIdNo = input.getApplicantIdNo();
			long applicantIdDateLong = GetterUtil.getLong(input.getApplicantIdDate());
			String address = input.getAddress();
			String contactName = input.getContactName();
			String contactTelNo = input.getContactTelNo();
			String contactEmail = input.getContactEmail();
			String dossierNo = input.getDossierNo();
			_log.info("dossierNo: "+dossierNo);
			//long extendDateLong = GetterUtil.getLong(input.getExtendDate());
			//long processDateLong = GetterUtil.getLong(input.getProcessDate());
			String dossierStatus = input.getDossierStatus();
			String dossierStatusText = input.getDossierStatusText();
			//String online = input.getOnline();
			String online = "false";
			//int originality = GetterUtil.getInteger(input.getOriginality());
			int originality = 3;
			int sampleCount = GetterUtil.getInteger(input.getSampleCount());
			double durationCount = GetterUtil.getDouble(input.getDurationCount());
			int durationUnit = GetterUtil.getInteger(input.getDurationUnit());
			String dossierTemplateNo = input.getDossierTemplateNo();
			String dossierTemplateName = input.getDossierTemplateName();

			Dossier oldDossier = null;
			if (Validator.isNotNull(input.getReferenceUid())) {
				oldDossier = DossierUtils.getDossier(input.getReferenceUid(), groupId);
			} else {
				oldDossier = DossierLocalServiceUtil.getByDossierNo(groupId, dossierNo);
				//
				referenceUid = DossierNumberGenerator.generateReferenceUID(groupId);
			}

			if (Validator.isNotNull(dossierTemplateNo)) {
				if ("TT12_15_2012_TT_BVHTTDL_HS12".equals(dossierTemplateNo)) {
					DossierPart part = DossierPartLocalServiceUtil.fetchByTemplatePartNo(groupId, dossierTemplateNo, "TP99");
					_log.info("part: "+part);
					if (part == null) {
						DossierPartLocalServiceUtil.updateDossierPartDB(user.getUserId(), groupId, dossierTemplateNo,
								"TP99", "Thành phần khác", "Thành phần khác", 1, false, null, null, true, false, "TP99",
								null, 0, false, null, 0, serviceContext);
					}
				}
				if ("TT130_BVHTTDL".equals(dossierTemplateNo)) {
					DossierTemplate template = DossierTemplateLocalServiceUtil.getByTemplateNo(groupId, dossierTemplateNo);
					if (template == null) {
						DossierTemplateLocalServiceUtil.updateDossierTemplateDB(user.getUserId(), groupId,
								"TT130_BVHTTDL",
								"Cấp phép nhập khẩu văn hóa phẩm không nhằm mục đích kinh doanh thuộc thẩm quyền của Bộ Văn hóa, Thể thao và Du lịch",
								"", StringPool.BLANK, serviceContext);
					}
					
					DossierPart part1 = DossierPartLocalServiceUtil.fetchByTemplatePartNo(groupId, dossierTemplateNo, "TP1");
					if (part1 == null) {
						DossierPartLocalServiceUtil.updateDossierPartDB(user.getUserId(), groupId, dossierTemplateNo,
								"TP1", "Đơn đề nghị cấp giấy phép nhập khẩu văn hóa phẩm",
								"Đơn đề nghị cấp giấy phép nhập khẩu văn hóa phẩm", 1, false, null, null, true, false,
								"TP99", null, 0, false, null, 0, serviceContext);
					}
					//
					DossierPart part2 = DossierPartLocalServiceUtil.fetchByTemplatePartNo(groupId, dossierTemplateNo, "TP2");
					if (part2 == null) {
						DossierPartLocalServiceUtil.updateDossierPartDB(user.getUserId(), groupId, dossierTemplateNo,
								"TP2",
								"Giấy chứng nhận bản quyền tác giả, bản dịch tóm tắt nội dung phim; giấy ủy quyền; chứng nhận hoặc cam kết sở hữu hợp pháp đối với di vật, cổ vật, cụ thể: + Cá nhân, tổ chức nhập khẩu phim để phổ biến theo quy định của pháp luật phải cung cấp giấy chứng nhận bản quyền tác giả; hợp đồng; bản dịch tóm tắt nội dung phim. + Cá nhân, tổ chức nhập khẩu di vật, cổ vật phải cung cấp giấy chứng nhận hoặc cam kết sở hữu hợp pháp đối với di vật, cổ vật. + Cá nhân, tổ chức làm dịch vụ giao nhận vận chuyển văn hóa phẩm nhập khẩu cho khách hàng phải cung cấp giấy ủy quyền.",
								"Giấy chứng nhận bản quyền tác giả, bản dịch tóm tắt nội dung phim; giấy ủy quyền; chứng nhận hoặc cam kết sở hữu hợp pháp đối với di vật, cổ vật, cụ thể: + Cá nhân, tổ chức nhập khẩu phim để phổ biến theo quy định của pháp luật phải cung cấp giấy chứng nhận bản quyền tác giả; hợp đồng; bản dịch tóm tắt nội dung phim. + Cá nhân, tổ chức nhập khẩu di vật, cổ vật phải cung cấp giấy chứng nhận hoặc cam kết sở hữu hợp pháp đối với di vật, cổ vật. + Cá nhân, tổ chức làm dịch vụ giao nhận vận chuyển văn hóa phẩm nhập khẩu cho khách hàng phải cung cấp giấy ủy quyền.",
								1, false, null, null, true, false, "TP2", null, 0, false, null, 0, serviceContext);
					}
					//
					DossierPart part3 = DossierPartLocalServiceUtil.fetchByTemplatePartNo(groupId, dossierTemplateNo, "TP3");
					if (part3 == null) {
						DossierPartLocalServiceUtil.updateDossierPartDB(user.getUserId(), groupId, dossierTemplateNo,
								"TP3", "Bản sao vận đơn hoặc giấy báo nhận hàng (nếu có)",
								"Bản sao vận đơn hoặc giấy báo nhận hàng (nếu có)", 1, false, null, null, true, false,
								"TP3", null, 0, false, null, 0, serviceContext);
					}
				}
			}

			long submitDateLong = GetterUtil.getLong(input.getSubmitDate());
			long receiveDateLong = GetterUtil.getLong(input.getReceiveDate());
			long dueDateLong = GetterUtil.getLong(input.getDueDate());
			long releaseDateLong = GetterUtil.getLong(input.getReleaseDate());
			long finishDateLong = GetterUtil.getLong(input.getFinishDate());

			Date receiveDate = null;
			if (submitDateLong != 0) {
				Calendar calReceive = Calendar.getInstance();
				calReceive.setTimeInMillis(receiveDateLong);
				calReceive.set(Calendar.HOUR_OF_DAY, 10);
				//
				receiveDate = calReceive.getTime();
			}
			//
			Date dueDate = null;
			if (dueDateLong != 0) {
				Calendar calDue = Calendar.getInstance();
				calDue.setTimeInMillis(dueDateLong);
				calDue.set(Calendar.HOUR_OF_DAY, 10);
				//
				dueDate = calDue.getTime();
			}
			//
			Date releaseDate = null;
			if (releaseDateLong != 0) {
				Calendar calRelease = Calendar.getInstance();
				calRelease.setTimeInMillis(releaseDateLong);
				calRelease.set(Calendar.HOUR_OF_DAY, 9);
				//
				releaseDate = calRelease.getTime();
			}
			//
			Date finishDate = null;
			if (finishDateLong != 0) {
				Calendar calFinish = Calendar.getInstance();
				calFinish.setTimeInMillis(finishDateLong);
				calFinish.set(Calendar.HOUR_OF_DAY, 9);
				//
				finishDate = calFinish.getTime();
			}

			if (oldDossier == null || oldDossier.getOriginality() == 0) {
				Dossier dossier = actions.publishImportDossier(groupId, 0l, referenceUid, counter, serviceCode,
						serviceName, govAgencyCode, govAgencyName, applicantName, applicantIdType, applicantIdNo,
						applicantIdDateLong != 0 ? new Date(applicantIdDateLong) : null, address, contactName,
						contactTelNo, contactEmail, dossierNo, dossierStatus, dossierStatusText,
						Boolean.valueOf(online), originality, sampleCount, durationCount, durationUnit,
						createDateLong != 0 ? new Date(createDateLong) : null,
						modifiedDateLong != 0 ? new Date(modifiedDateLong) : null,
						submitDateLong != 0 ? new Date(submitDateLong) : null, receiveDate, dueDate, releaseDate,
						finishDate, dossierTemplateNo, dossierTemplateName, serviceContext);

				if (Validator.isNotNull(dossierTemplateNo)) {
					List<DossierPart> partList = DossierPartLocalServiceUtil.getByTemplateNo(groupId, dossierTemplateNo);
//					_log.info("partList: "+partList);
					if (partList != null && partList.size() > 0) {
						_log.info("partList.size(): "+partList.size());
						org.opencps.dossiermgt.input.model.DossierMarkBatchModel[] marks = new org.opencps.dossiermgt.input.model.DossierMarkBatchModel[partList.size()];
						int count = 0;
						List<DossierMark> lstMarks = DossierMarkLocalServiceUtil.getDossierMarks(groupId, dossier.getDossierId());
						Map<String, DossierMark> mapMarks = new HashMap<>();
						for (DossierMark dm : lstMarks) {
							mapMarks.put(dm.getDossierPartNo(), dm);
						}
						for (DossierPart dossierPart : partList) {
							//int fileMark = dossierPart.getFileMark();
							String dossierPartNo = dossierPart.getPartNo();
							org.opencps.dossiermgt.input.model.DossierMarkBatchModel model = new org.opencps.dossiermgt.input.model.DossierMarkBatchModel();
							model.setDossierId(dossier.getDossierId());
							model.setDossierPartNo(dossierPartNo);
							model.setFileCheck(0);
							model.setFileMark(0);
							model.setFileComment(StringPool.BLANK);
							marks[count++] = model;
//							DossierMarkLocalServiceUtil.addDossierMark(groupId, dossier.getDossierId(), dossierPartNo,
//									fileMark, 0, StringPool.BLANK, serviceContext);
						}
						
						DossierMarkLocalServiceUtil.addBatchDossierMark(groupId, marks, mapMarks, serviceContext);
					}
				}

				return Response.status(200).entity(JSONFactoryUtil.looseSerializeDeep(dossier)).build();
			}
			else {
				oldDossier = actions.publishImportDossier(groupId, oldDossier.getDossierId(), referenceUid, counter,
						serviceCode, serviceName, govAgencyCode, govAgencyName, applicantName, applicantIdType,
						applicantIdNo, applicantIdDateLong != 0 ? new Date(applicantIdDateLong) : null, address,
						contactName, contactTelNo, contactEmail, dossierNo, dossierStatus, dossierStatusText,
						Boolean.valueOf(online), originality, sampleCount, durationCount, durationUnit,
						createDateLong != 0 ? new Date(createDateLong) : null,
						modifiedDateLong != 0 ? new Date(modifiedDateLong) : null,
						submitDateLong != 0 ? new Date(submitDateLong) : null, receiveDate, dueDate, releaseDate,
						finishDate, dossierTemplateNo, dossierTemplateName, serviceContext);
				if (Validator.isNotNull(dossierTemplateNo)) {
					List<DossierPart> partList = DossierPartLocalServiceUtil.getByTemplateNo(groupId, dossierTemplateNo);
//					_log.info("partList: "+partList);
					if (partList != null && partList.size() > 0) {
						_log.info("partList.size(): "+partList.size());
						org.opencps.dossiermgt.input.model.DossierMarkBatchModel[] marks = new org.opencps.dossiermgt.input.model.DossierMarkBatchModel[partList.size()];
						int count = 0;
						List<DossierMark> lstMarks = DossierMarkLocalServiceUtil.getDossierMarks(groupId, oldDossier.getDossierId());
						Map<String, DossierMark> mapMarks = new HashMap<>();
						for (DossierMark dm : lstMarks) {
							mapMarks.put(dm.getDossierPartNo(), dm);
						}
						for (DossierPart dossierPart : partList) {
							//int fileMark = dossierPart.getFileMark();
							String dossierPartNo = dossierPart.getPartNo();
							org.opencps.dossiermgt.input.model.DossierMarkBatchModel model = new org.opencps.dossiermgt.input.model.DossierMarkBatchModel();
							model.setDossierId(oldDossier.getDossierId());
							model.setDossierPartNo(dossierPartNo);
							model.setFileCheck(0);
							model.setFileMark(0);
							model.setFileComment(StringPool.BLANK);
							marks[count++] = model;
//							DossierMarkLocalServiceUtil.addDossierMark(groupId, dossier.getDossierId(), dossierPartNo,
//									fileMark, 0, StringPool.BLANK, serviceContext);
						}
						
						DossierMarkLocalServiceUtil.addBatchDossierMark(groupId, marks, mapMarks, serviceContext);
					}
				}

				return Response.status(200).entity(JSONFactoryUtil.looseSerializeDeep(oldDossier)).build();
			}
		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@SuppressWarnings("resource")
	@Override
	public Response uploadFileDossiers(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, Attachment file) {
		_log.info("uploadFileDossiers");
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		//long userId = user.getUserId();
		InputStream fileInputStream = null;
		FileInputStream excelInputStream = null;
		Workbook workbook = null;

		try {
			DataHandler dataHandle = file.getDataHandler();

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			//DossierFileActions action = new DossierFileActionsImpl();
			//action.uploadFileEntry(dataHandle.getName(), dataHandle.getInputStream(), serviceContext);

			JSONObject result = JSONFactoryUtil.createJSONObject();
			//
			List<Group> groupList = GroupLocalServiceUtil.getActiveGroups(company.getCompanyId(), true);
			String strGroupId = StringPool.BLANK;
			if (groupList != null && groupList.size() > 0) {
				List<String> groupIdList = new ArrayList<>();
				for (Group group : groupList) {
					if (group.isSite()) {
						groupIdList.add(String.valueOf(group.getGroupId()));
					}
				}
				if (groupIdList != null && groupIdList.size() > 0) {
					strGroupId = String.join(StringPool.COMMA, groupIdList);
				}
			}
			//Check group
			if (!strGroupId.contains(String.valueOf(groupId))) {
				return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity("GroupId not exits!").build();
			}
			
			//Process FILE
			fileInputStream = dataHandle.getInputStream();
			String fileName = dataHandle.getName();
			String extFile = ImportZipFileUtils.getExtendFileName(fileName);
			_log.info("extFile: "+extFile);
			if (Validator.isNotNull(extFile) && ("xlsx".equalsIgnoreCase(extFile) || "xls".equalsIgnoreCase(extFile))) {
				String pathFile = ConstantUtils.DEST_DIRECTORY + StringPool.SLASH + fileName;
//				//delete folder if exits
				File fileOld = new File(pathFile);
				_log.info("fileOld: "+fileOld.getAbsolutePath());
				if (fileOld.exists()) {
					boolean flag = fileOld.delete() ? true : false;
					_log.info("LamTV_Delete DONE: "+flag);
				}
				_log.info("LamTV_pathFolder: "+pathFile);
				File excelFile = new File(pathFile);
				FileOutputStream out = new FileOutputStream(excelFile);
				IOUtils.copy(fileInputStream, out);
				excelInputStream = new FileInputStream(excelFile);
//					FileUtils.copyInputStreamToFile(fileInputStream, fileList);
				_log.info("excelFile: "+excelFile);
				_log.info("LamTV_fileList: "+excelFile.getPath());
				String subFileName = ImportZipFileUtils.getSubFileName(fileName);
				if (Validator.isNotNull(subFileName)) {
					//String xmlString = ReadXMLFileUtils.convertFiletoString(fileList);
					workbook = new XSSFWorkbook(excelInputStream);
					//Dossier
					Sheet datatypeSheetOne = workbook.getSheetAt(0);
					//
					//Map<Long, List<NameValuePair>> mapData = new HashMap<Long, List<NameValuePair>>();
					
					int nOfRows = datatypeSheetOne.getPhysicalNumberOfRows();
					System.out.println("nOfRows: "+nOfRows);
					
					if (nOfRows > 1) {
						int count = 0;
						JSONArray dataArr = JSONFactoryUtil.createJSONArray();
						for (int i = 1; i < nOfRows; i++) {
							Row currentRow = datatypeSheetOne.getRow(i);
							if (currentRow != null) {

								JSONObject dossierData = ImportDataUtils.convertRowToDossier(currentRow);
								if (dossierData != null) {
									count ++;
									dataArr.put(dossierData);
								}
								_log.info("mapData: " +i +" : " + dossierData);
							}
						}
						if (count > 0) {
							result.put("total", count);
							result.put("data", dataArr);
							
						}
					}
				}
				_log.info("LamTV_IMPORT DONE_FILE");
			}

			return Response.status(200).entity(JSONFactoryUtil.looseSerialize(result)).build();

		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		} finally {
			if (excelInputStream != null) {
				try {
					excelInputStream.close();
				} catch (IOException e) {
					_log.debug(e);
				}
			}
			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					_log.debug(e);
				}
			}
		}
	}

	@SuppressWarnings("resource")
	@Override
	public Response uploadFileQuestion(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, Attachment file) {
		_log.info("uploadFileDossiers");
		BackendAuth auth = new BackendAuthImpl();

		long groupId = GetterUtil.getLong(header.getHeaderString("groupId"));
		long userId = user.getUserId();
		InputStream fileInputStream = null;
		Workbook workbook = null;

		try {
			DataHandler dataHandle = file.getDataHandler();

			if (!auth.isAuth(serviceContext)) {
				throw new UnauthenticationException();
			}

			//DossierFileActions action = new DossierFileActionsImpl();
			//action.uploadFileEntry(dataHandle.getName(), dataHandle.getInputStream(), serviceContext);

			JSONObject result = JSONFactoryUtil.createJSONObject();
			//
			List<Group> groupList = GroupLocalServiceUtil.getActiveGroups(company.getCompanyId(), true);
			String strGroupId = StringPool.BLANK;
			if (groupList != null && groupList.size() > 0) {
				List<String> groupIdList = new ArrayList<>();
				for (Group group : groupList) {
					if (group.isSite()) {
						groupIdList.add(String.valueOf(group.getGroupId()));
					}
				}
				if (groupIdList != null && groupIdList.size() > 0) {
					strGroupId = String.join(StringPool.COMMA, groupIdList);
				}
			}
			//Check group
			if (!strGroupId.contains(String.valueOf(groupId))) {
				return Response.status(HttpURLConnection.HTTP_INTERNAL_ERROR).entity("GroupId not exits!").build();
			}
			
			//Process FILE
			fileInputStream = dataHandle.getInputStream();
			String fileName = dataHandle.getName();
			String extFile = ImportZipFileUtils.getExtendFileName(fileName);
			_log.info("extFile: "+extFile);
			if (Validator.isNotNull(extFile) && ("xlsx".equalsIgnoreCase(extFile) || "xls".equalsIgnoreCase(extFile))) {
				String pathFile = ConstantUtils.DEST_DIRECTORY + StringPool.SLASH + fileName;
//				//delete folder if exits
				File fileOld = new File(pathFile);
				_log.info("fileOld: "+fileOld.getAbsolutePath());
				if (fileOld.exists()) {
					boolean flag = fileOld.delete() ? true : false;
					_log.info("LamTV_Delete DONE: "+flag);
				}
				_log.info("LamTV_pathFolder: "+pathFile);
				File excelFile = new File(pathFile);
				FileOutputStream out = new FileOutputStream(excelFile);
				IOUtils.copy(fileInputStream, out);
				FileInputStream excelInputStream = new FileInputStream(excelFile);
//					FileUtils.copyInputStreamToFile(fileInputStream, fileList);
				_log.info("excelFile: "+excelFile);
				_log.info("LamTV_fileList: "+excelFile.getPath());
				String subFileName = ImportZipFileUtils.getSubFileName(fileName);
				if (Validator.isNotNull(subFileName)) {
					//String xmlString = ReadXMLFileUtils.convertFiletoString(fileList);
					workbook = new XSSFWorkbook(excelInputStream);
					//Dossier
					Sheet datatypeSheetOne = workbook.getSheetAt(0);
					//
					//Map<Long, List<NameValuePair>> mapData = new HashMap<Long, List<NameValuePair>>();
					
					int nOfRows = datatypeSheetOne.getPhysicalNumberOfRows();
					System.out.println("nOfRows: "+nOfRows);
					
					if (nOfRows > 1) {
						//int count = 0;
						//JSONArray dataArr = JSONFactoryUtil.createJSONArray();
						for (int i = 1; i < nOfRows; i++) {
							Row currentRow = datatypeSheetOne.getRow(i);
							if (currentRow != null) {

								JSONObject questionData = ImportDataUtils.convertRowToQuestion(currentRow);
								if (questionData != null) {
									//count ++;
									String content = questionData.getString("question");
									String govAgencyCode = questionData.getString("govAgencyCode");
									if (Validator.isNotNull(content) && Validator.isNotNull(govAgencyCode)) {
										Question question = QuestionLocalServiceUtil.updateQuestion(
												serviceContext.getCompanyId(), groupId, 0l, "Hệ thống", "test@liferay.com",
												questionData.getString("question"), 1, questionData.getString("govAgencyCode"), 
												questionData.getString("govAgencyName"));
										if (question != null) {
											AnswerLocalServiceUtil.updateAnswer(userId, groupId, 0l,
													question.getQuestionId(), questionData.getString("answer"), 1);
										}
									}
								}
								//_log.info("mapData: " +i +" : " + dossierData);
							}
						}
//						if (count > 0) {
						result.put("total", nOfRows);
//							result.put("data", dataArr);
//							
//						}
					}
				}
				_log.info("LamTV_IMPORT DONE_FILE");
			}

			return Response.status(200).entity(JSONFactoryUtil.looseSerialize(result)).build();

		} catch (Exception e) {
			_log.error(e);
			return BusinessExceptionImpl.processException(e);
		} finally {
			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
					_log.debug(e);
				}
			}
		}
	}

}
