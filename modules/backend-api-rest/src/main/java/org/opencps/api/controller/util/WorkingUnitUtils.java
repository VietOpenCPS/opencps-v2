package org.opencps.api.controller.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.opencps.api.workingunit.model.WorkingUnitModel;
import org.opencps.datamgt.constants.WorkspaceTerm;
import org.opencps.usermgt.constants.WorkingUnitTerm;
import org.opencps.usermgt.model.WorkingUnit;

import org.opencps.auth.utils.APIDateTimeUtils;

public class WorkingUnitUtils {

	public static List<WorkingUnitModel> mapperWorkingUnitList(List<Document> listDocument) {

		List<WorkingUnitModel> results = new ArrayList<>();

		try {

			WorkingUnitModel ett = null;

			for (Document document : listDocument) {
				ett = new WorkingUnitModel();

				ett.setWorkingUnitId(Long.valueOf(document.get("entryClassPK")));
				ett.setCreateDate(Validator.isNotNull(document.getDate(WorkspaceTerm.CREATE_DATE)) ? APIDateTimeUtils
						.convertDateToString(document.getDate(WorkspaceTerm.CREATE_DATE), APIDateTimeUtils._TIMESTAMP)
						: StringPool.BLANK);
				ett.setModifiedDate(
						Validator.isNotNull(document.getDate("modified")) ? APIDateTimeUtils.convertDateToString(
								document.getDate("modified"), APIDateTimeUtils._TIMESTAMP) : StringPool.BLANK);

				ett.setName(document.get(WorkingUnitTerm.NAME));
				ett.setEnName(document.get(WorkingUnitTerm.NAME));
				ett.setGovAgencyCode(document.get(WorkingUnitTerm.GOV_AGENCY_CODE));
				ett.setParentWorkingUnitId(Long.valueOf(document.get(WorkingUnitTerm.PARENT_WORKING_UNIT_ID)));
				ett.setAddress(document.get(WorkingUnitTerm.ADDRESS));
				ett.setTelNo(document.get(WorkingUnitTerm.TEL_NO));
				ett.setFaxNo(document.get(WorkingUnitTerm.FAX_NO));
				ett.setEmail(document.get(WorkingUnitTerm.EMAIL));
				ett.setWebsite(document.get(WorkingUnitTerm.WEBSITE));
				ett.setSibling(GetterUtil.get(document.get(WorkingUnitTerm.SIBLING), 0));
				ett.setTreeIndex(document.get(WorkingUnitTerm.TREEINDEX));
				ett.setLevel(StringUtil.count(document.get(WorkingUnitTerm.TREEINDEX), StringPool.PERIOD));

				results.add(ett);
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return results;
	}

	public static WorkingUnitModel mapperWorkingUnitModel(WorkingUnit workingUnit) {

		WorkingUnitModel ett = new WorkingUnitModel();

		try {

			ett.setWorkingUnitId(workingUnit.getWorkingUnitId());
			ett.setCreateDate(Validator.isNotNull(workingUnit.getCreateDate())
					? APIDateTimeUtils.convertDateToString(workingUnit.getCreateDate(), APIDateTimeUtils._TIMESTAMP)
					: StringPool.BLANK);
			ett.setModifiedDate(Validator.isNotNull(workingUnit.getModifiedDate())
					? APIDateTimeUtils.convertDateToString(workingUnit.getModifiedDate(), APIDateTimeUtils._TIMESTAMP)
					: StringPool.BLANK);
			ett.setName(workingUnit.getName());
			ett.setEnName(workingUnit.getEnName());
			ett.setGovAgencyCode(workingUnit.getGovAgencyCode());
			ett.setParentWorkingUnitId(workingUnit.getParentWorkingUnitId());
			ett.setAddress(workingUnit.getAddress());
			ett.setTelNo(workingUnit.getTelNo());
			ett.setFaxNo(workingUnit.getFaxNo());
			ett.setEmail(workingUnit.getEmail());
			ett.setWebsite(workingUnit.getWebsite());
			ett.setSibling(GetterUtil.get(workingUnit.getSibling(), 0));
			ett.setTreeIndex(workingUnit.getTreeIndex());
			ett.setLevel(StringUtil.count(workingUnit.getTreeIndex(), StringPool.PERIOD));

		} catch (Exception e) {
			_log.error(e);
		}

		return ett;
	}

	
	public static JSONArray readExcel(InputStream excelInputStream) {

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
						JSONObject workingUnitObj = convertRowToWorkingUnit(currentRow, nOfColumns, formDataFormat);
						if (Validator.isNotNull(workingUnitObj)) {
							results.put(workingUnitObj);
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
	
	public static JSONObject convertRowToWorkingUnit(Row currentRow, int nOfColumns, JSONObject formDataFormat) {

		JSONObject workingUnitObj = JSONFactoryUtil.createJSONObject();
		try {
			for (int i = 0; i <= nOfColumns; i++) {
				workingUnitObj.put(formDataFormat.getString(String.valueOf(i)), getCellValue(currentRow.getCell(i)));
			}
		} catch (Exception e) {
			_log.debug(e);
		}

		return workingUnitObj;
	}
	
	private static Object getCellValue(Cell cell) {
		
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
	
	static Log _log = LogFactoryUtil.getLog(WorkingUnitUtils.class);
}
