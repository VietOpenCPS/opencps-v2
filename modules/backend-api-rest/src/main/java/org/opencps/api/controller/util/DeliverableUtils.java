
package org.opencps.api.controller.util;

import com.google.gson.JsonArray;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.activation.DataHandler;

import org.apache.cxf.jaxrs.ext.multipart.Attachment;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
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

public class DeliverableUtils {

	public static List<DeliverableModel> mappingToDeliverableResultModel(List<Document> documents) {

		List<DeliverableModel> data = new ArrayList<DeliverableModel>();

		for (Document doc : documents) {
			DeliverableModel model = mappingToDeliverable(doc);
			data.add(model);
		}

		return data;
	}

	public static DeliverableModel mappingToDeliverable(Document doc) {

		DeliverableModel model = new DeliverableModel();

		model.setDeliverableId(Long.valueOf(doc.get(DeliverableTerm.DELIVERABLE_ID)));
		model.setUserId(Long.valueOf(doc.get(Field.USER_ID)));
		model.setUserName(doc.get(Field.USER_NAME));
		// Convert Date to String
		String strCreateDate = doc.get(Field.CREATE_DATE);
		Date createDate = null;
		if (Validator.isNotNull(strCreateDate)) {
			createDate = APIDateTimeUtils.convertStringToDate(strCreateDate, APIDateTimeUtils._LUCENE_PATTERN);
		}
		model.setCreateDate(
				createDate != null ? APIDateTimeUtils.convertDateToString(createDate, APIDateTimeUtils._TIMESTAMP)
						: strCreateDate);

		String strModifiedDate = doc.get(Field.MODIFIED_DATE);
		Date modifiedDate = null;
		if (Validator.isNotNull(strModifiedDate)) {
			modifiedDate = APIDateTimeUtils.convertStringToDate(strModifiedDate, APIDateTimeUtils._LUCENE_PATTERN);
		}
		model.setModifiedDate(
				modifiedDate != null ? APIDateTimeUtils.convertDateToString(modifiedDate, APIDateTimeUtils._TIMESTAMP)
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
			Date issueDate = APIDateTimeUtils.convertStringToDate(strIssueDate, APIDateTimeUtils._LUCENE_PATTERN);
			// _log.info("SUBMIT_DATE_DATEEEEEE: "+submitDate);
			model.setIssueDate(APIDateTimeUtils.convertDateToString(issueDate, APIDateTimeUtils._NORMAL_PARTTERN));
			// _log.info("SUBMIT_DATE_CONVERT:
			// "+APIDateTimeUtils.convertDateToString(submitDate,
			// APIDateTimeUtils._NORMAL_PARTTERN));
		} else {
			model.setIssueDate(strIssueDate);
		}

		String strExpireDate = doc.get(DeliverableTerm.EXPIRE_DATE);
		if (Validator.isNotNull(strExpireDate)) {
			// _log.info("SUBMIT_DATE_DATEEEEEE:
			// "+doc.get(DossierTerm.SUBMIT_DATE));
			Date expireDate = APIDateTimeUtils.convertStringToDate(strExpireDate, APIDateTimeUtils._LUCENE_PATTERN);
			// _log.info("SUBMIT_DATE_DATEEEEEE: "+submitDate);
			model.setExpireDate(APIDateTimeUtils.convertDateToString(expireDate, APIDateTimeUtils._NORMAL_PARTTERN));
			// _log.info("SUBMIT_DATE_CONVERT:
			// "+APIDateTimeUtils.convertDateToString(submitDate,
			// APIDateTimeUtils._NORMAL_PARTTERN));
		} else {
			model.setExpireDate(strExpireDate);
		}

		String strRevalidate = doc.get(DeliverableTerm.REVALIDATE);
		Date revalidate = null;
		if (Validator.isNotNull(strRevalidate)) {
			revalidate = APIDateTimeUtils.convertStringToDate(strRevalidate, APIDateTimeUtils._LUCENE_PATTERN);
		}
		model.setRevalidate(
				revalidate != null ? APIDateTimeUtils.convertDateToString(revalidate, APIDateTimeUtils._TIMESTAMP)
						: strRevalidate);

		model.setDeliverableState(doc.get(DeliverableTerm.DELIVERABLE_STATE));

		return model;
	}

	public static DeliverableInputModel mappingToDeliverablesModel(Deliverable deliverable) {

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
				APIDateTimeUtils.convertDateToString(deliverable.getIssueDate(), APIDateTimeUtils._TIMESTAMP));
		model.setExpireDate(
				APIDateTimeUtils.convertDateToString(deliverable.getExpireDate(), APIDateTimeUtils._TIMESTAMP));
		model.setRevalidate(
				APIDateTimeUtils.convertDateToString(deliverable.getRevalidate(), APIDateTimeUtils._TIMESTAMP));
		model.setDeliverableState(String.valueOf(deliverable.getDeliverableState()));

		return model;
	}

	public static DeliverableModel mappingToDeliverableDetailModel(Deliverable deliverable) {

		if (deliverable != null) {
			DeliverableModel model = new DeliverableModel();

			model.setDeliverableId(deliverable.getDeliverableId());
			model.setUserId(deliverable.getUserId());
			model.setUserName(deliverable.getUserName());
			// Convert Date to String
			model.setCreateDate(
					APIDateTimeUtils.convertDateToString(deliverable.getCreateDate(), APIDateTimeUtils._TIMESTAMP));
			model.setModifiedDate(
					APIDateTimeUtils.convertDateToString(deliverable.getModifiedDate(), APIDateTimeUtils._TIMESTAMP));

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
					APIDateTimeUtils.convertDateToString(deliverable.getIssueDate(), APIDateTimeUtils._TIMESTAMP));
			model.setModifiedDate(
					APIDateTimeUtils.convertDateToString(deliverable.getExpireDate(), APIDateTimeUtils._TIMESTAMP));
			model.setModifiedDate(
					APIDateTimeUtils.convertDateToString(deliverable.getRevalidate(), APIDateTimeUtils._TIMESTAMP));

			model.setDeliverableState(String.valueOf(deliverable.getDeliverableState()));

			return model;
		} else {
			return null;
		}
	}

	public static DeliverableUpdateModel mappingToDeliverablesUpdateModel(Deliverable deliverable) {

		if (deliverable != null) {
			DeliverableUpdateModel model = new DeliverableUpdateModel();

			model.setSubject(deliverable.getSubject());
			model.setIssueDate(
					APIDateTimeUtils.convertDateToString(deliverable.getIssueDate(), APIDateTimeUtils._TIMESTAMP));
			model.setExpireDate(
					APIDateTimeUtils.convertDateToString(deliverable.getExpireDate(), APIDateTimeUtils._TIMESTAMP));
			model.setRevalidate(
					APIDateTimeUtils.convertDateToString(deliverable.getRevalidate(), APIDateTimeUtils._TIMESTAMP));
			model.setDeliverableState(String.valueOf(deliverable.getDeliverableState()));

			model.setDeliverableState(String.valueOf(deliverable.getDeliverableState()));

			return model;
		} else {
			return null;
		}
	}

	public static String mappingToDeliverableFormDataModel(List<Document> documents) {

		Document doc = documents.get(0);
		if (doc != null) {
			return doc.get(DeliverableTerm.FORM_DATA);
		} else {
			return null;
		}

	}

	public static JSONArray readExcelDeliverable(InputStream excelInputStream) {

		Workbook workbook = null;
		JSONArray results = JSONFactoryUtil.createJSONArray();

		try {

			workbook = new XSSFWorkbook(excelInputStream);
			//
			Sheet datatypeSheetOne = workbook.getSheetAt(0);
			int nOfRows = datatypeSheetOne.getPhysicalNumberOfRows();
			int nOfColumns = 1000;
			_log.debug("nOfRows: " + nOfRows);

			if (nOfRows > 1) {

				JSONObject formDataFormat = JSONFactoryUtil.createJSONObject();
				for (int i = 0; i < nOfColumns; i++) {
					Cell celli = datatypeSheetOne.getRow(0).getCell(i);
					if (Validator.isNotNull(celli) && Validator.isNotNull(celli.getStringCellValue())) {
						formDataFormat.put(String.valueOf(i),
								datatypeSheetOne.getRow(0).getCell(i).getStringCellValue());
					} else {
						nOfColumns = i - 1;
						break;
					}
				}
				_log.debug("====dataForm__" + formDataFormat);
				_log.debug("====nOfColumns===" + nOfColumns);
				for (int i = 1; i < nOfRows; i++) {
					Row currentRow = datatypeSheetOne.getRow(i);
					if (currentRow != null) {

						// todo convert
						JSONObject deliverableObj = convertRowToDeliverable(currentRow, nOfColumns, formDataFormat);
						if (Validator.isNotNull(deliverableObj)) {
							results.put(deliverableObj);
						}
					}
				}
			}
		} catch (Exception e) {
			_log.debug(e);
		} finally {
			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
//					e.printStackTrace();
					_log.debug(e);
				}
			}
		}
		return results;
	}

	public static JSONObject convertRowToDeliverable(Row currentRow, int nOfColumns, JSONObject formDataFormat) {

		JSONObject formData = JSONFactoryUtil.createJSONObject();
		JSONObject deliverableObj = JSONFactoryUtil.createJSONObject();

		try {
			for (int i = 0; i <= nOfColumns; i++) {

				formData.put(formDataFormat.getString(String.valueOf(i)), getCellValue(currentRow.getCell(i)));
				deliverableObj.put(formDataFormat.getString(String.valueOf(i)), getCellValue(currentRow.getCell(i)));
			}
			deliverableObj.put("formData", formData);
		} catch (Exception e) {
//			e.printStackTrace();
			_log.debug(e);
		}

		return deliverableObj;
	}

	public static Object getCellValue(Cell cell) {

		if (cell == null) {

			return null;
		} else if (CellType.STRING == cell.getCellType()) {

			return cell.getStringCellValue();
		} else if (CellType.BOOLEAN == cell.getCellType()) {

			return cell.getBooleanCellValue();
		} else if (CellType.ERROR == cell.getCellType()) {

			return cell.getErrorCellValue();
		} else if (CellType.NUMERIC == cell.getCellType()) {

			return cell.getNumericCellValue();
		} else if (DateUtil.isCellDateFormatted(cell)) {

			return new SimpleDateFormat(APIDateTimeUtils._NORMAL_DATE).format(cell.getDateCellValue());
		} else {

			return null;
		}
	}

	static void copyInputStreamToFile(InputStream in, File file) {

		OutputStream out = null;
		try {
			out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			if (out != null) {
				out.close();
			}
			if (in != null)
				in.close();
		} catch (Exception e) {
//			e.printStackTrace();
			_log.debug(e);
		} finally {
			if (out != null || in != null) {
				try {
					if (out != null)
						out.close();
					if (in != null)
						in.close();
				} catch (IOException e) {
					_log.debug(e);
//					e.printStackTrace();
				}
			}
		}
	}

	public static JSONArray readZipDeliverabe(Attachment file, long userId, long groupId,
			long companyId, String userName, ServiceContext serviceContext) {

		JSONArray deliverablesArr = JSONFactoryUtil.createJSONArray();
		try {
			DataHandler dataHandle = file.getDataHandler();
			InputStream fileInputStream = dataHandle.getInputStream();
			String fileName = dataHandle.getName();
			String pathFolder = ImportZipFileUtils.getFolderPath(fileName, ConstantUtils.DEST_DIRECTORY);
			File fileOld = new File(pathFolder);
			_log.info("fileOld: " + fileOld);
			if (fileOld.exists()) {
				boolean flag = ReadXMLFileUtils.deleteFilesForParentFolder(fileOld);
				_log.info("LamTV_Delete DONE: " + flag);
			}
			ImportZipFileUtils.unzip(fileInputStream, ConstantUtils.DEST_DIRECTORY);

			File fileList = new File(pathFolder);

			for (File fileEntry : fileList.listFiles()) {

				_log.info("excelFile: " + fileEntry);
				_log.info("LamTV_fileList: " + fileEntry.getPath());

				if (fileEntry.isDirectory()) {
					System.out.println("folder " + fileEntry.getName());
				} else {
					if ("xls".equals(ImportZipFileUtils.getExtendFileName(fileEntry.getName()))
							|| "xlsx".equals(ImportZipFileUtils.getExtendFileName(fileEntry.getName()))) {

						FileInputStream excelInputStream = new FileInputStream(fileEntry);
						deliverablesArr = readExcelDeliverable(excelInputStream);
						for (int i = 0; i < deliverablesArr.length(); i++) {

							JSONObject deliverable = deliverablesArr.getJSONObject(i);

							if (Validator.isNotNull(deliverable.get(DeliverableTerm.DELIVERABLE_CODE))) {

								Deliverable deliverableObj =
									DeliverableLocalServiceUtil.getByF_GID_DCODE(
										groupId, deliverable.getString(DeliverableTerm.DELIVERABLE_CODE));

								deliverable.put(
									DeliverableTerm.DELIVERABLE_ID, Validator.isNotNull(deliverableObj)
										? deliverableObj.getDeliverableId() : 0);
								deliverable.put(Field.GROUP_ID, groupId);
								deliverable.put(Field.USER_ID, userId);
								deliverable.put(Field.COMPANY_ID, companyId);
								deliverable.put(Field.USER_NAME, userName);
								deliverable.put(DeliverableTerm.FILE_ATTACH, false);
								if (Validator.isNotNull(pathFolder) && deliverable.has("filePath") && Validator.isNotNull(deliverable.getString("filePath"))) {

									File fileAttach = new File(pathFolder + "/" + deliverable.getString("filePath"));
									System.out.println("fileAttach.getName()==========="+ fileAttach.getName());
									System.out.println("fileAttach.getTotalSpace()================="+fileAttach.getTotalSpace());
									FileEntry fileEntryAttach = FileUploadUtils.uploadDossierFile(
										userId, groupId, fileAttach, pathFolder + "/" + deliverable.getString("filePath"),
										serviceContext);
	
									JSONObject formData = deliverable.getJSONObject(DeliverableTerm.FORM_DATA);
									formData.put(DeliverableTerm.FILE_ATTACHS, fileEntryAttach.getFileEntryId());
									deliverable.put(DeliverableTerm.FORM_DATA, formData.toString());
									deliverable.put(DeliverableTerm.FILE_ATTACHS, fileEntryAttach.getFileEntryId());
									deliverable.put(DeliverableTerm.FILE_ENTRY_ID, fileEntryAttach.getFileEntryId());
								}
								DeliverableLocalServiceUtil.adminProcessData(deliverable);
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// _log.error(e);
		}
		return deliverablesArr;
	}

	public static JSONArray readExcelDeliverableV3(InputStream excelInputStream) {

		Workbook workbook = null;
		JSONArray results = JSONFactoryUtil.createJSONArray();

		try {

			workbook = new XSSFWorkbook(excelInputStream);
			//
			Sheet datatypeSheet = workbook.getSheetAt(0);
			Sheet sheetConfig = workbook.getSheetAt(1);
			int nOfRows = datatypeSheet.getPhysicalNumberOfRows();
			int nOfColumns = 1000;
			_log.debug("nOfRows: " + nOfRows);

			if (nOfRows > 1 && nOfRows < 10000) {

				JSONObject formDataFormat = JSONFactoryUtil.createJSONObject();
				for (int i = 0; i < nOfColumns; i++) {
					Cell celli = datatypeSheet.getRow(0).getCell(i);
					if (Validator.isNotNull(celli) && Validator.isNotNull(celli.getStringCellValue())) {
						formDataFormat.put(String.valueOf(i),
								sheetConfig.getRow(0).getCell(i).getStringCellValue());
					} else {
						nOfColumns = i - 1;
						break;
					}
				}
				_log.debug("====dataForm__" + formDataFormat);
				_log.debug("====nOfColumns===" + nOfColumns);
				for (int i = 1; i < nOfRows; i++) {
					Row currentRow = datatypeSheet.getRow(i);
					
					if (currentRow != null) {

						// todo convert
						JSONObject deliverableObj = convertRowToDeliverableV3(currentRow, nOfColumns, formDataFormat);
						if (Validator.isNotNull(deliverableObj)) {
							results.put(deliverableObj);
						}
					}
				}
			}
		} catch (Exception e) {
			_log.debug(e);
		} finally {
			if (workbook != null) {
				try {
					workbook.close();
				} catch (IOException e) {
//					e.printStackTrace();
					_log.debug(e);
				}
			}
		}
		return results;
	}
	
	public static JSONObject convertRowToDeliverableV3(Row currentRow, int nOfColumns, JSONObject formDataFormat) {

		JSONObject formData = JSONFactoryUtil.createJSONObject();
		JSONObject deliverableObj = JSONFactoryUtil.createJSONObject();

		try {
			for (int i = 0; i <= nOfColumns; i++) {
				
				String value = formDataFormat.getString(String.valueOf(i));
				
				if (validateRequiredField(value) == true) {
					if (getCellValueV3(currentRow.getCell(i)) != null) {
						formData.put(formDataFormat.getString(String.valueOf(i)), getCellValueV3(currentRow.getCell(i)));
						deliverableObj.put(formDataFormat.getString(String.valueOf(i)), getCellValueV3(currentRow.getCell(i)));
					} else {
						// in ra file co du lieu loi
					}
				} else if (value.equals(DeliverableTerm.G_T) && getCellValueV3(currentRow.getCell(i)) == null) {
					formData.put(formDataFormat.getString(String.valueOf(i)), DeliverableTerm.K_C_T_T);
					deliverableObj.put(formDataFormat.getString(String.valueOf(i)), DeliverableTerm.K_C_T_T);
				}else if (value.equals(DeliverableTerm.QUE_QUAN) && getCellValueV3(currentRow.getCell(i)) != null) {
					String[] quequan = currentRow.getCell(i).getStringCellValue().split(",");
					formData.put(DeliverableTerm.XA_PHUONG, quequan[0]);
					formData.put(DeliverableTerm.QUAN_HUYEN, quequan[1]);
					formData.put(DeliverableTerm.TINH_TP, quequan[2]);
					formData.put(DeliverableTerm.XA_PHUONG_TEXT, quequan[0]);
					formData.put(DeliverableTerm.QUAN_HUYEN_TEXT, quequan[1]);
					formData.put(DeliverableTerm.TINH_TP_TEXT, quequan[2]);
					deliverableObj.put(DeliverableTerm.XA_PHUONG, quequan[0]);
					deliverableObj.put(DeliverableTerm.QUAN_HUYEN, quequan[1]);
					deliverableObj.put(DeliverableTerm.TINH_TP, quequan[2]);
				}else if (value.equals(DeliverableTerm.NAM_SINH) && getCellValueV3(currentRow.getCell(i)) != null) {
					
					String[] birdthday = null;
					if (CellType.STRING == currentRow.getCell(i).getCellType()) {
						birdthday = currentRow.getCell(i).getStringCellValue().split("\\.");
					} else if (CellType.NUMERIC == currentRow.getCell(i).getCellType() 
							&& DateUtil.isCellDateFormatted(currentRow.getCell(i))) {
						String namsinh = new SimpleDateFormat(APIDateTimeUtils._NORMAL_DATE).format(currentRow.getCell(i).getDateCellValue());
						birdthday = namsinh.split("/");
					}
					formData.put(DeliverableTerm.NGAY, birdthday[0]);
					formData.put(DeliverableTerm.THANG, birdthday[1]);
					formData.put(DeliverableTerm.NAM, birdthday[2]);
					deliverableObj.put(DeliverableTerm.NGAY, birdthday[0]);
					deliverableObj.put(DeliverableTerm.THANG, birdthday[1]);
					deliverableObj.put(DeliverableTerm.NAM, birdthday[2]);
				}else if (value.equals(DeliverableTerm.NGAY_QD) && getCellValueV3(currentRow.getCell(i)) != null) {
					String[] ngayQD = null;
					if (CellType.STRING == currentRow.getCell(i).getCellType()) {
						ngayQD = currentRow.getCell(i).getStringCellValue().split("\\.");
						String ngayquyetdinh = ngayQD[0]+"/"+ngayQD[1]+"/"+ngayQD[2];
						formData.put(DeliverableTerm.NGAY_QD, ngayquyetdinh);
						deliverableObj.put(DeliverableTerm.NGAY_QD, ngayquyetdinh);
					} else if (CellType.NUMERIC == currentRow.getCell(i).getCellType() 
							&& DateUtil.isCellDateFormatted(currentRow.getCell(i))) {
						String ngayqd = new SimpleDateFormat(APIDateTimeUtils._NORMAL_DATE).format(currentRow.getCell(i).getDateCellValue());
						formData.put(DeliverableTerm.NGAY_QD, ngayqd);
						deliverableObj.put(DeliverableTerm.NGAY_QD, ngayqd);
					}
				}else {
					formData.put(formDataFormat.getString(String.valueOf(i)), getCellValueV3(currentRow.getCell(i)));
					deliverableObj.put(formDataFormat.getString(String.valueOf(i)), getCellValueV3(currentRow.getCell(i)));
				}			
			}
			deliverableObj.put("formData", formData);
		} catch (Exception e) {
//			e.printStackTrace();
			_log.debug(e);
		}

		return deliverableObj;
	}
	
	private static boolean validateRequiredField(String value) {
		boolean result = false;
		if (value.equals(DeliverableTerm.HO_TEN)|| value.equals(DeliverableTerm.SO_QD) 
				|| value.equals(DeliverableTerm.CHE_DO_TC)|| value.equals(DeliverableTerm.DON_VI_HCD)) {
			result = true;
		}
		return result;
	}

	private static Object getCellValueV3(Cell cell) {
		
		if (cell == null) {

			return null;
		} else if (CellType.STRING == cell.getCellType()) {

			return cell.getStringCellValue();
		} else if (CellType.BOOLEAN == cell.getCellType()) {

			return cell.getBooleanCellValue();
		} else if (CellType.ERROR == cell.getCellType()) {

			return cell.getErrorCellValue();
		} else if (CellType.NUMERIC == cell.getCellType()) {
			if (DateUtil.isCellDateFormatted(cell)) {
				return new SimpleDateFormat(APIDateTimeUtils._NORMAL_DATE).format(cell.getDateCellValue());
			} else {
				return new BigDecimal(cell.getNumericCellValue());
			}			
		} else {

			return null;
		}
	}
		
	private static Log _log = LogFactoryUtil.getLog(DeliverableUtils.class);

}
