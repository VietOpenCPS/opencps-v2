package org.opencps.api.controller.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.opencps.dossiermgt.constants.DossierTerm;

public class ImportDataUtils {

	private static final Log _log = LogFactoryUtil.getLog(ImportDataUtils.class);
	public static JSONObject convertRowToDossier(Row currentRow) {
		JSONObject jsonData = JSONFactoryUtil.createJSONObject();
		try {
			
			Cell appIdCell = currentRow.getCell(1);
			if (appIdCell != null) {
				CellType typeApp = currentRow.getCell(1).getCellType();
				if (typeApp == CellType.STRING) {
					jsonData.put(DossierTerm.APPLICANT_ID_NO,
							Validator.isNotNull(currentRow.getCell(1).getStringCellValue())
									? currentRow.getCell(1).getStringCellValue().trim()
									: StringPool.BLANK);
					
				} else if(typeApp == CellType.NUMERIC){
					jsonData.put(DossierTerm.APPLICANT_ID_NO, currentRow.getCell(1).getNumericCellValue());
				}
			} else {
				jsonData.put(DossierTerm.APPLICANT_ID_NO, StringPool.BLANK);
			}
			//jsonData.put(DossierTerm.APPLICANT_ID_NO, currentRow.getCell(1).getStringCellValue().trim());

			jsonData.put(DossierTerm.APPLICANT_NAME,
					Validator.isNotNull(currentRow.getCell(2).getStringCellValue())
							? currentRow.getCell(2).getStringCellValue().trim()
							: StringPool.BLANK);
			
			Cell appTypeCell = currentRow.getCell(1);
			if (appTypeCell != null) {
				String appType = Validator.isNotNull(currentRow.getCell(3).getStringCellValue())
						? currentRow.getCell(3).getStringCellValue().trim()
						: StringPool.BLANK;
				if ("CD".equalsIgnoreCase(appType)) {
					jsonData.put(DossierTerm.APPLICANT_ID_TYPE, "citizen");
				} else if ("DN".equalsIgnoreCase(appType)){
					jsonData.put(DossierTerm.APPLICANT_ID_TYPE, "business");
				} else {
					jsonData.put(DossierTerm.APPLICANT_ID_TYPE, StringPool.BLANK);
				}
			} else {
				jsonData.put(DossierTerm.APPLICANT_ID_TYPE, StringPool.BLANK);
			}

			Cell appDateCell = currentRow.getCell(1);
			if (appDateCell != null) {
				CellType typeDate = currentRow.getCell(4).getCellType();
				if (typeDate == CellType.STRING) {
					
					String strAppIdDate = Validator.isNotNull(currentRow.getCell(4).getStringCellValue())
							? currentRow.getCell(4).getStringCellValue().trim() : StringPool.BLANK;
					Date appIdDate = null;
					if (Validator.isNotNull(strAppIdDate)) {
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
						appIdDate = sdf.parse(strAppIdDate);
					}
					//
					jsonData.put(DossierTerm.APPLICANT_ID_DATE, appIdDate != null ? appIdDate.getTime() : 0);
					
				} else if(typeDate == CellType.NUMERIC){
					jsonData.put(DossierTerm.APPLICANT_ID_DATE, currentRow.getCell(4).getNumericCellValue());
				} else if (DateUtil.isCellDateFormatted(currentRow.getCell(4))) {
					jsonData.put(DossierTerm.APPLICANT_ID_DATE,currentRow.getCell(4).getDateCellValue() != null
							? currentRow.getCell(4).getDateCellValue().getTime() : 0);
				}
			} else {
				jsonData.put(DossierTerm.APPLICANT_ID_DATE, 0);
			}
			

			//jsonData.put(DossierTerm.APPLICANT_ID_DATE, currentRow.getCell(4).getStringCellValue().trim());
			Cell appAddCell = currentRow.getCell(5);
			if (appAddCell != null) {
				jsonData.put(DossierTerm.ADDRESS,
						Validator.isNotNull(currentRow.getCell(5).getStringCellValue())
								? currentRow.getCell(5).getStringCellValue().trim()
								: StringPool.BLANK);
			} else {
				jsonData.put(DossierTerm.ADDRESS, StringPool.BLANK);
			}

			Cell appEmailCell = currentRow.getCell(6);
			if (appEmailCell != null) {
				jsonData.put(DossierTerm.CONTACT_EMAIL,
						Validator.isNotNull(currentRow.getCell(6).getStringCellValue())
								? currentRow.getCell(6).getStringCellValue().trim()
								: StringPool.BLANK);
			} else {
				jsonData.put(DossierTerm.CONTACT_EMAIL, StringPool.BLANK);
			}
			
			Cell appPhoneCell = currentRow.getCell(7);
			if (appPhoneCell != null) {
				CellType typeTel = currentRow.getCell(7).getCellType();
				if (typeTel == CellType.STRING) {
					jsonData.put(DossierTerm.CONTACT_TEL_NO,
							Validator.isNotNull(currentRow.getCell(7).getStringCellValue())
									? currentRow.getCell(7).getStringCellValue().trim()
									: StringPool.BLANK);
					
				} else if(typeTel == CellType.NUMERIC){
					jsonData.put(DossierTerm.CONTACT_TEL_NO, currentRow.getCell(7).getNumericCellValue());
				}
			} else {
				jsonData.put(DossierTerm.CONTACT_TEL_NO, StringPool.BLANK);
			}

		} catch (Exception e) {
			_log.error(e);
		}
		
		return jsonData;
	}

	public static JSONObject convertRowToQuestion(Row currentRow) {
		JSONObject jsonData = JSONFactoryUtil.createJSONObject();
		try {
			
			jsonData.put("domainCode", currentRow.getCell(1).getStringCellValue().trim());
			jsonData.put("domainName", currentRow.getCell(2).getStringCellValue().trim());
			jsonData.put("question", currentRow.getCell(3).getStringCellValue().trim());
			jsonData.put("answer", currentRow.getCell(4).getStringCellValue().trim());
			jsonData.put("subDomainCode", currentRow.getCell(5).getStringCellValue().trim());
			jsonData.put("subDomainName", currentRow.getCell(6).getStringCellValue().trim());
			jsonData.put("govAgencyCode", currentRow.getCell(7).getStringCellValue().trim());
			jsonData.put("govAgencyName", currentRow.getCell(8).getStringCellValue().trim());

		} catch (Exception e) {
			_log.error(e);
		}
		
		return jsonData;
	}

}
