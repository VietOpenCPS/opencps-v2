
package org.opencps.api.controller.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.activation.DataHandler;

import org.apache.commons.lang.Validate;
import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.opencps.api.constants.ConstantUtils;
import org.opencps.api.deliverable.model.DeliverableInputModel;
import org.opencps.api.deliverable.model.DeliverableModel;
import org.opencps.api.deliverable.model.DeliverableUpdateModel;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.action.FileUploadUtils;
import org.opencps.dossiermgt.constants.DeliverableTerm;
import org.opencps.dossiermgt.model.Deliverable;
import org.opencps.dossiermgt.service.DeliverableLocalServiceUtil;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

public class DeliverableUtils {

	public static List<DeliverableModel> mappingToDeliverableResultModel(
		List<Document> documents) {

		List<DeliverableModel> data = new ArrayList<DeliverableModel>();

		for (Document doc : documents) {
			DeliverableModel model = mappingToDeliverable(doc);
			data.add(model);
		}

		return data;
	}

	public static DeliverableModel mappingToDeliverable(Document doc) {

		DeliverableModel model = new DeliverableModel();

		model.setDeliverableId(
			Long.valueOf(doc.get(DeliverableTerm.DELIVERABLE_ID)));
		model.setUserId(Long.valueOf(doc.get(Field.USER_ID)));
		model.setUserName(doc.get(Field.USER_NAME));
		// Convert Date to String
		String strCreateDate = doc.get(Field.CREATE_DATE);
		Date createDate = null;
		if (Validator.isNotNull(strCreateDate)) {
			createDate = APIDateTimeUtils.convertStringToDate(
				strCreateDate, APIDateTimeUtils._LUCENE_PATTERN);
		}
		model.setCreateDate(
			createDate != null
				? APIDateTimeUtils.convertDateToString(
					createDate, APIDateTimeUtils._TIMESTAMP)
				: strCreateDate);

		String strModifiedDate = doc.get(Field.MODIFIED_DATE);
		Date modifiedDate = null;
		if (Validator.isNotNull(strModifiedDate)) {
			modifiedDate = APIDateTimeUtils.convertStringToDate(
				strModifiedDate, APIDateTimeUtils._LUCENE_PATTERN);
		}
		model.setModifiedDate(
			modifiedDate != null
				? APIDateTimeUtils.convertDateToString(
					modifiedDate, APIDateTimeUtils._TIMESTAMP)
				: strModifiedDate);

		model.setDeliverableCode(doc.get(DeliverableTerm.DELIVERABLE_CODE));
		model.setDeliverableName(doc.get(DeliverableTerm.DELIVERABLE_NAME));
		model.setDeliverableType(doc.get(DeliverableTerm.DELIVERABLE_TYPE));
		model.setGovAgencyCode(doc.get(DeliverableTerm.GOV_AGENCY_CODE));
		model.setGovAgencyName(doc.get(DeliverableTerm.GOV_AGENCY_NAME));
		model.setApplicantIdNo(doc.get(DeliverableTerm.APPLICANT_ID_NO));
		model.setApplicantName(doc.get(DeliverableTerm.APPLICANT_NAME));
		model.setSubject(doc.get(DeliverableTerm.SUBJECT));
		// Convert Date to String
		String strIssueDate = doc.get(DeliverableTerm.ISSUE_DATE);
		if (Validator.isNotNull(strIssueDate)) {
			// _log.info("SUBMIT_DATE_DATEEEEEE:
			// "+doc.get(DossierTerm.SUBMIT_DATE));
			Date issueDate = APIDateTimeUtils.convertStringToDate(
				strIssueDate, APIDateTimeUtils._LUCENE_PATTERN);
			// _log.info("SUBMIT_DATE_DATEEEEEE: "+submitDate);
			model.setIssueDate(
				APIDateTimeUtils.convertDateToString(
					issueDate, APIDateTimeUtils._NORMAL_PARTTERN));
			// _log.info("SUBMIT_DATE_CONVERT:
			// "+APIDateTimeUtils.convertDateToString(submitDate,
			// APIDateTimeUtils._NORMAL_PARTTERN));
		}
		else {
			model.setIssueDate(strIssueDate);
		}

		String strExpireDate = doc.get(DeliverableTerm.EXPIRE_DATE);
		if (Validator.isNotNull(strExpireDate)) {
			// _log.info("SUBMIT_DATE_DATEEEEEE:
			// "+doc.get(DossierTerm.SUBMIT_DATE));
			Date expireDate = APIDateTimeUtils.convertStringToDate(
				strExpireDate, APIDateTimeUtils._LUCENE_PATTERN);
			// _log.info("SUBMIT_DATE_DATEEEEEE: "+submitDate);
			model.setExpireDate(
				APIDateTimeUtils.convertDateToString(
					expireDate, APIDateTimeUtils._NORMAL_PARTTERN));
			// _log.info("SUBMIT_DATE_CONVERT:
			// "+APIDateTimeUtils.convertDateToString(submitDate,
			// APIDateTimeUtils._NORMAL_PARTTERN));
		}
		else {
			model.setExpireDate(strExpireDate);
		}

		String strRevalidate = doc.get(DeliverableTerm.REVALIDATE);
		Date revalidate = null;
		if (Validator.isNotNull(strRevalidate)) {
			revalidate = APIDateTimeUtils.convertStringToDate(
				strRevalidate, APIDateTimeUtils._LUCENE_PATTERN);
		}
		model.setRevalidate(
			revalidate != null
				? APIDateTimeUtils.convertDateToString(
					revalidate, APIDateTimeUtils._TIMESTAMP)
				: strRevalidate);

		model.setDeliverableState(doc.get(DeliverableTerm.DELIVERABLE_STATE));

		return model;
	}

	public static DeliverableInputModel mappingToDeliverablesModel(
		Deliverable deliverable) {

		if (deliverable == null) {
			return null;
		}
		DeliverableInputModel model = new DeliverableInputModel();

		model.setDeliverableType(deliverable.getDeliverableType());
		model.setDeliverableCode(deliverable.getDeliverableCode());
		model.setGovAgencyCode(deliverable.getGovAgencyCode());
		model.setApplicantIdNo(deliverable.getApplicantIdNo());
		model.setApplicantName(deliverable.getApplicantName());
		model.setSubject(deliverable.getSubject());
		model.setIssueDate(
			APIDateTimeUtils.convertDateToString(
				deliverable.getIssueDate(), APIDateTimeUtils._TIMESTAMP));
		model.setExpireDate(
			APIDateTimeUtils.convertDateToString(
				deliverable.getExpireDate(), APIDateTimeUtils._TIMESTAMP));
		model.setRevalidate(
			APIDateTimeUtils.convertDateToString(
				deliverable.getRevalidate(), APIDateTimeUtils._TIMESTAMP));
		model.setDeliverableState(
			String.valueOf(deliverable.getDeliverableState()));

		return model;
	}

	public static DeliverableModel mappingToDeliverableDetailModel(
		Deliverable deliverable) {

		if (deliverable != null) {
			DeliverableModel model = new DeliverableModel();

			model.setDeliverableId(deliverable.getDeliverableId());
			model.setUserId(deliverable.getUserId());
			model.setUserName(deliverable.getUserName());
			// Convert Date to String
			model.setCreateDate(
				APIDateTimeUtils.convertDateToString(
					deliverable.getCreateDate(), APIDateTimeUtils._TIMESTAMP));
			model.setModifiedDate(
				APIDateTimeUtils.convertDateToString(
					deliverable.getModifiedDate(),
					APIDateTimeUtils._TIMESTAMP));

			model.setDeliverableCode(deliverable.getDeliverableCode());
			model.setDeliverableName(deliverable.getDeliverableName());
			model.setDeliverableType(deliverable.getDeliverableType());
			model.setGovAgencyCode(deliverable.getGovAgencyCode());
			model.setGovAgencyName(deliverable.getGovAgencyName());
			model.setApplicantIdNo(deliverable.getApplicantIdNo());
			model.setApplicantName(deliverable.getApplicantName());
			model.setSubject(deliverable.getSubject());
			// Convert Date to String
			model.setModifiedDate(
				APIDateTimeUtils.convertDateToString(
					deliverable.getIssueDate(), APIDateTimeUtils._TIMESTAMP));
			model.setModifiedDate(
				APIDateTimeUtils.convertDateToString(
					deliverable.getExpireDate(), APIDateTimeUtils._TIMESTAMP));
			model.setModifiedDate(
				APIDateTimeUtils.convertDateToString(
					deliverable.getRevalidate(), APIDateTimeUtils._TIMESTAMP));

			model.setDeliverableState(
				String.valueOf(deliverable.getDeliverableState()));

			return model;
		}
		else {
			return null;
		}
	}

	public static DeliverableUpdateModel mappingToDeliverablesUpdateModel(
		Deliverable deliverable) {

		if (deliverable != null) {
			DeliverableUpdateModel model = new DeliverableUpdateModel();

			model.setSubject(deliverable.getSubject());
			model.setIssueDate(
				APIDateTimeUtils.convertDateToString(
					deliverable.getIssueDate(), APIDateTimeUtils._TIMESTAMP));
			model.setExpireDate(
				APIDateTimeUtils.convertDateToString(
					deliverable.getExpireDate(), APIDateTimeUtils._TIMESTAMP));
			model.setRevalidate(
				APIDateTimeUtils.convertDateToString(
					deliverable.getRevalidate(), APIDateTimeUtils._TIMESTAMP));
			model.setDeliverableState(
				String.valueOf(deliverable.getDeliverableState()));

			model.setDeliverableState(
				String.valueOf(deliverable.getDeliverableState()));

			return model;
		}
		else {
			return null;
		}
	}

	public static String mappingToDeliverableFormDataModel(
		List<Document> documents) {

		Document doc = documents.get(0);
		if (doc != null) {
			return doc.get(DeliverableTerm.FORM_DATA);
		}
		else {
			return null;
		}

	}

	public static List<Deliverable> readWorkBooksDeliverabe(
		Attachment file, long userId, long groupId,
		ServiceContext serviceContext) {

		List<Deliverable> deliverables = new ArrayList<>();
		try {
			DataHandler dataHandle = file.getDataHandler();
			InputStream fileInputStream = dataHandle.getInputStream();
			String fileName = dataHandle.getName();
			String pathFolder = ImportZipFileUtils.getFolderPath(
				fileName, ConstantUtils.DEST_DIRECTORY);
			File fileOld = new File(pathFolder);
			_log.info("fileOld: " + fileOld);
			if (fileOld.exists()) {
				boolean flag =
					ReadXMLFileUtils.deleteFilesForParentFolder(fileOld);
				_log.info("LamTV_Delete DONE: " + flag);
			}
			ImportZipFileUtils.unzip(
				fileInputStream, ConstantUtils.DEST_DIRECTORY);

			File fileList = new File(pathFolder);

			for (File fileEntry : fileList.listFiles()) {

				_log.info("excelFile: " + fileEntry);
				_log.info("LamTV_fileList: " + fileEntry.getPath());

				if (fileEntry.isDirectory()) {
					System.out.println("folder " + fileEntry.getName());
				}
				else {
					if ("xls".equals(
						ImportZipFileUtils.getExtendFileName(
							fileEntry.getName())) ||
						"xlsx".equals(
							ImportZipFileUtils.getExtendFileName(
								fileEntry.getName()))) {

						deliverables = readExcelDeliverable(
							fileEntry, fileList.getAbsolutePath(), userId,
							groupId, serviceContext);
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			// _log.error(e);
		}
		return deliverables;
	}

	public static List<Deliverable> readExcelDeliverable(
		File fileEntry, String pathFolder, long userId, long groupId,
		ServiceContext serviceContext) {

		Workbook workbook = null;
		List<Deliverable> results = new ArrayList<>();

		try {

			FileInputStream excelInputStream = new FileInputStream(fileEntry);

			workbook = new XSSFWorkbook(excelInputStream);
			//
			Sheet datatypeSheetOne = workbook.getSheetAt(0);
			int nOfRows = datatypeSheetOne.getPhysicalNumberOfRows();
			System.out.println("nOfRows: " + nOfRows);

			if (nOfRows > 1) {

				for (int i = 1; i < nOfRows; i++) {
					Row currentRow = datatypeSheetOne.getRow(i);
					if (currentRow != null) {

						Deliverable deliverableInfo = convertRowToDeliverable(
							currentRow, pathFolder, userId, groupId,
							serviceContext);
						if (deliverableInfo != null) {
							results.add(deliverableInfo);
						}
					}
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (workbook != null) {
				try {
					workbook.close();
				}
				catch (IOException e) {
					e.printStackTrace();
					// _log.debug(e);
				}
			}
		}
		return results;
	}

	public static Deliverable convertRowToDeliverable(
		Row currentRow, String pathFolder, long userId, long groupId,
		ServiceContext serviceContext) {

		Deliverable deliverable = null;

		try {
			// currentRow.getCell(1).setCellType(CellType.STRING);
			// currentRow.getCell(2).setCellType(CellType.STRING);
			// currentRow.getCell(3).setCellType(CellType.STRING);
			// currentRow.getCell(4).setCellType(CellType.STRING);
			// currentRow.getCell(5).setCellType(CellType.STRING);
			// currentRow.getCell(6).setCellType(CellType.STRING);
			// currentRow.getCell(7).setCellType(CellType.STRING);
			// currentRow.getCell(8).setCellType(CellType.STRING);
			// currentRow.getCell(9).setCellType(CellType.STRING);
			// currentRow.getCell(10).setCellType(CellType.STRING);
			// currentRow.getCell(11).setCellType(CellType.STRING);

			String deliverableCode = Validator.isNull(currentRow.getCell(1))
				? StringPool.BLANK : currentRow.getCell(1).getStringCellValue();
			String deliverableName = Validator.isNull(currentRow.getCell(2))
				? StringPool.BLANK : currentRow.getCell(2).getStringCellValue();
			String deliverableType = Validator.isNull(currentRow.getCell(3))
				? StringPool.BLANK : currentRow.getCell(3).getStringCellValue();
			String applicantIdNo = Validator.isNull(currentRow.getCell(4))
				? "0" : new Double(
					currentRow.getCell(4).getNumericCellValue()).toString();
			String applicantName = Validator.isNull(currentRow.getCell(5))
				? StringPool.BLANK : currentRow.getCell(5).getStringCellValue();
			String subject = Validator.isNull(currentRow.getCell(6))
				? StringPool.BLANK : currentRow.getCell(6).getStringCellValue();
			String issueDate = Validator.isNull(currentRow.getCell(7))
				? StringPool.BLANK : currentRow.getCell(7).getStringCellValue();
			String expireDate = Validator.isNull(currentRow.getCell(8))
				? StringPool.BLANK : currentRow.getCell(8).getStringCellValue();
			String revalidate = Validator.isNull(currentRow.getCell(9))
				? StringPool.BLANK : currentRow.getCell(9).getStringCellValue();
			String deliverableState = Validator.isNull(currentRow.getCell(10))
				? StringPool.BLANK
				: currentRow.getCell(10).getStringCellValue();
			String filePath = Validator.isNull(currentRow.getCell(11))
				? StringPool.BLANK
				: currentRow.getCell(11).getStringCellValue();
			long fileEntryId = 0;

			if (!StringPool.BLANK.equals(filePath)) {

				File fileAttach = new File(pathFolder + "/" + filePath);
				System.out.println("fileAttach.getName()==========="+ fileAttach.getName());
				System.out.println("fileAttach.getTotalSpace()================="+fileAttach.getTotalSpace());
				FileEntry fileEntry = FileUploadUtils.uploadDossierFile(
					userId, groupId, fileAttach, pathFolder + "/" + filePath,
					serviceContext);

				fileEntryId = fileEntry.getFileEntryId();
			}

			String govAgencyCode = StringPool.BLANK;
			String govAgencyName = StringPool.BLANK;
			long dossierId = 0;
			long formScriptFileId = 0;
			long formReportFileId = 0;
			String formData = StringPool.BLANK;

			deliverable = DeliverableLocalServiceUtil.addDeliverableSign(
				groupId, deliverableType, deliverableName, deliverableCode,
				govAgencyCode, govAgencyName, applicantIdNo, applicantName,
				subject, issueDate, expireDate, revalidate, deliverableState,
				dossierId, fileEntryId, formScriptFileId, formReportFileId,
				formData, serviceContext);
		}
		catch (Exception e) {
			e.printStackTrace();
			// _log.error(e);
		}

		return deliverable;
	}

	private static Log _log = LogFactoryUtil.getLog(DeliverableUtils.class);

}
