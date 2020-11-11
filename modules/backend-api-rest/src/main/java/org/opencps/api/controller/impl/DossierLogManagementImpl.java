package org.opencps.api.controller.impl;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.SortFactoryUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.opencps.api.constants.StatisticManagementConstants;
import org.opencps.api.controller.DossierLogManagement;
import org.opencps.api.controller.util.DossierLogUtils;
import org.opencps.api.controller.util.DossierUtils;
import org.opencps.api.controller.util.MessageUtil;
import org.opencps.api.dossier.model.DossierSearchModel;
import org.opencps.api.dossieraction.model.DossierActionModel;
import org.opencps.api.dossieraction.model.DossierActionResultsModel;
import org.opencps.api.dossierlog.model.DossierLogModel;
import org.opencps.api.dossierlog.model.DossierLogResultsModel;
import org.opencps.api.dossierlog.model.DossierLogSearchModel;
import org.opencps.auth.utils.APIDateTimeUtils;
import org.opencps.dossiermgt.action.DossierActions;
import org.opencps.dossiermgt.action.DossierLogActions;
import org.opencps.dossiermgt.action.impl.DossierActionsImpl;
import org.opencps.dossiermgt.action.impl.DossierLogActionsImpl;
import org.opencps.dossiermgt.action.util.ConstantUtils;
import org.opencps.dossiermgt.action.util.ReadFilePropertiesUtils;
import org.opencps.dossiermgt.constants.DossierActionTerm;
import org.opencps.dossiermgt.constants.DossierLogTerm;
import org.opencps.dossiermgt.constants.DossierTerm;
import org.opencps.dossiermgt.model.DossierLog;

import backend.auth.api.exception.BusinessExceptionImpl;

public class DossierLogManagementImpl implements DossierLogManagement {

	private static Log _log = LogFactoryUtil.getLog(DossierLogManagementImpl.class);

	@Override
	public Response addDossierLogByDossierId(HttpServletRequest request, HttpHeaders header, Company company,
			Locale locale, User user, ServiceContext serviceContext, long id, String notificationType, String author,
			String payload, String content) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {

			DossierLogActions action = new DossierLogActionsImpl();

			DossierLog dossierLog = action.addDossierLog(groupId, id, author, content, notificationType, payload,
					serviceContext);

			DossierLogModel result = DossierLogUtils.mappingToDossierLogModel(dossierLog);

			return Response.status(HttpURLConnection.HTTP_OK).entity(result).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getDossierLogs(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DossierLogSearchModel query) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));

		try {

			DossierLogResultsModel results = new DossierLogResultsModel();

			DossierLogActions action = new DossierLogActionsImpl();

			JSONObject dossierLogJsonObject = action.getDossierLogs(groupId, query.getNotificationType(),
					query.isOwner(), query.getStart(), query.getEnd(), query.getSort(), query.getOrder(),
					serviceContext);

			List<Document> documents = (List<Document>) dossierLogJsonObject.get(ConstantUtils.DATA);
			//
			results.setTotal(dossierLogJsonObject.getInt(ConstantUtils.TOTAL));
			results.getData().addAll(DossierLogUtils.mappingToDossierLogResultsModel(documents));

			return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Response getDossierLogById(HttpServletRequest request, HttpHeaders header, Company company, Locale locale,
			User user, ServiceContext serviceContext, DossierLogSearchModel query, long dossierId, String password) {

		long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
		try {

			JSONObject results = JSONFactoryUtil.createJSONObject();

			DossierLogActions action = new DossierLogActionsImpl();

			JSONObject dossierLogJsonObject = action.getDossierLogById(groupId, dossierId, password, query.isOwner(),
					query.getStart(),

					query.getEnd(), query.getSort(), query.getOrder(), serviceContext);

			List<Document> documents = (List<Document>) dossierLogJsonObject.get(ConstantUtils.DATA);

			JSONArray models = JSONFactoryUtil.createJSONArray();

			for (Document document : documents) {

				JSONObject model = JSONFactoryUtil.createJSONObject();

				long dossierLogId = GetterUtil.getLong(document.get(Field.ENTRY_CLASS_PK));

				model.put(DossierLogTerm.DOSSIER_LOG_ID, dossierLogId);

				model.put(DossierLogTerm.AUTHOR, document.get(DossierLogTerm.AUTHOR) != null ? document.get(DossierLogTerm.AUTHOR).toUpperCase() : StringPool.BLANK);

				model.put(DossierLogTerm.CONTENT, document.get(DossierLogTerm.CONTENT));

				String strDate = document.get(DossierLogTerm.CREATE_DATE);

				Date date = null;

				if (Validator.isNotNull(strDate)) {
					date = APIDateTimeUtils.convertStringToDate(strDate, ReadFilePropertiesUtils.get(ConstantUtils.PATTERN_LUCENE));
				}

//				model.put("createDate", date != null
//						? APIDateTimeUtils.convertDateToString(date, APIDateTimeUtils._TIMESTAMP) : strDate);

				model.put(DossierLogTerm.CREATE_DATE, date != null
				? date.getTime() : 0l);

				model.put(DossierLogTerm.NOTIFICATION_TYPE, document.get(DossierLogTerm.NOTIFICATION_TYPE));

				JSONObject payload = JSONFactoryUtil.createJSONObject(document.get(DossierLogTerm.PAYLOAD));

				model.put(DossierLogTerm.PAYLOAD, payload);

				models.put(model);

			}

			results.put(ConstantUtils.TOTAL, dossierLogJsonObject.getInt(ConstantUtils.TOTAL));

			results.put(ConstantUtils.DATA, models);

			return Response.status(HttpURLConnection.HTTP_OK).entity(results.toJSONString()).build();

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}
	private static final String STT = "STT";
	private static final String DOSSIER_ACTION = "DOSSIER_ACTION";
	@Override
	public Response getRevisionLogByGroupId(HttpServletRequest request, HttpHeaders header, Company company,
											Locale locale, User user, ServiceContext serviceContext, DossierSearchModel query) {
		LinkedHashMap<String, Object> params =
				new LinkedHashMap<>();
		DossierActions actions = new DossierActionsImpl();
		DossierActionResultsModel results = new DossierActionResultsModel();


		try {
			if (Validator.isNull(query.getEnd()) || query.getEnd() == 0) {
				query.setStart(QueryUtil.ALL_POS);
				query.setEnd(QueryUtil.ALL_POS);
			}
			long groupId = GetterUtil.getLong(header.getHeaderString(Field.GROUP_ID));
			params.put(Field.GROUP_ID, String.valueOf(groupId));

			String dossierNo = query.getDossierNo();
			String userName = query.getUserName();
			String actionUser = query.getActionUser();
			boolean isExport = query.isExport();
			String createDateStart =
					APIDateTimeUtils.convertNormalDateToLuceneDate(
							query.getCreateDateStart());
			String createDateEnd =
					APIDateTimeUtils.convertNormalDateToLuceneDate(
							query.getCreateDateEnd());

			if(Validator.isNotNull(createDateStart)){
				params.put(DossierTerm.CREATE_DATE_START, createDateStart);
			}
			if(Validator.isNotNull(createDateEnd)){
				params.put(DossierTerm.CREATE_DATE_END, createDateEnd);
			}
			if(Validator.isNotNull(dossierNo)){
				params.put(DossierTerm.DOSSIER_NO, dossierNo);
			}
			if(Validator.isNotNull(userName)){
				params.put(DossierTerm.USER_NAME, userName);
			}
			if(Validator.isNotNull(actionUser)){
				params.put(DossierTerm.ACTION_USER, actionUser);
			}
			Sort[] sorts = null;
			if (Validator.isNull(query.getSort())) {
				String dateSort = String.format(MessageUtil.getMessage(org.opencps.api.constants.ConstantUtils.QUERY_NUMBER_SORT), DossierTerm.CREATE_DATE);
				sorts = new Sort[]{
						SortFactoryUtil.create(
								dateSort, Sort.LONG_TYPE,
								GetterUtil.getBoolean(query.getOrder()))
				};
			} else {
				String querySort = String.format(MessageUtil.getMessage(org.opencps.api.constants.ConstantUtils.QUERY_STRING_SORT), query.getSort());
				sorts = new Sort[]{
						SortFactoryUtil.create(
								querySort, Sort.STRING_TYPE,
								GetterUtil.getBoolean(query.getOrder()))
				};
			}

			JSONObject jsonData = actions.getDossierActionsList(
					user.getUserId(), company.getCompanyId(), groupId, params,
					sorts, query.getStart(), query.getEnd(), serviceContext);
			if (jsonData != null && jsonData.getInt(ConstantUtils.TOTAL) > 0) {
				results.getData().addAll(
						DossierUtils.mappingForListDossierActions(
								(List<Document>) jsonData.get(ConstantUtils.DATA)));

			}
			results.setTotal(jsonData.getInt(ConstantUtils.TOTAL));
			if(isExport){
				HSSFWorkbook workbook = null;
				FileOutputStream out = null;
				try {
					workbook = new HSSFWorkbook();
					// Create sheet
					String sheetName = DOSSIER_ACTION;
					HSSFSheet mainSheet = workbook.createSheet(sheetName);
					int rowIndex = 0;
					int stt = 0;
//					 Write header
					JSONArray jsonArr = JSONFactoryUtil.createJSONArray();
					jsonArr.put(STT);
					jsonArr.put(DossierActionTerm.ACTION_CODE_EXPORT);
					jsonArr.put(DossierActionTerm.ACTION_NAME_EXPORT);
					jsonArr.put(DossierActionTerm.CREATE_DATE_EXPORT);
					jsonArr.put(DossierActionTerm.ACTION_NOTE_EXPORT);
					System.out.println(jsonArr);

					writeHeader(mainSheet, rowIndex, jsonArr);

					// Write data
					rowIndex++;
					stt++;

					List<DossierActionModel> lst = new ArrayList<>();
					for(DossierActionModel item : results.getData()){
						DossierActionModel model = new DossierActionModel();
						model.setActionUser(item.getActionUser());
						model.setActionName(item.getActionName());
						model.setCreateDate(item.getCreateDate());
						model.setActionNote(item.getActionNote());
						lst.add(model);
					}
					for(DossierActionModel item : lst){
						Row row = mainSheet.createRow(rowIndex);
						createList(mainSheet, item, row, stt);
						rowIndex++;
						stt++;
					}
					// Auto resize column witdth
					int numberOfColumn = mainSheet.getRow(0).getPhysicalNumberOfCells();
					autosizeColumn(mainSheet, numberOfColumn);

					// Create file excel

					String fileName = DOSSIER_ACTION + StringPool.UNDERLINE
							+ String.format("%d.xls", System.currentTimeMillis());
					System.out.println("fileName: "+fileName);

					File exportDir = new File(StatisticManagementConstants.FOLDER_EXPORTED);
					if (!exportDir.exists()) {
						exportDir.mkdirs();
					}

					File file = new File(exportDir+ StringPool.SLASH + fileName);

					ByteArrayOutputStream bos = new ByteArrayOutputStream();
					workbook.write(bos);
					byte[] input = bos.toByteArray();
					try {
						out = new FileOutputStream(file);
						out.write(input);
						out.flush();
						out.close();
						workbook.close();
					}
					catch (Exception e) {
						_log.debug(e);
					}
					Response.ResponseBuilder responseBuilder = Response.ok((Object) file);
					String attachmentFilename = String.format(MessageUtil.getMessage(org.opencps.api.constants.ConstantUtils.ATTACHMENT_FILENAME), file.getName());
					responseBuilder.header(org.opencps.api.constants.ConstantUtils.CONTENT_DISPOSITION,attachmentFilename);
					responseBuilder.header(HttpHeaders.CONTENT_TYPE, org.opencps.api.constants.ConstantUtils.MEDIA_TYPE_EXCEL);

					return responseBuilder.build();
				}catch (Exception e){
					return BusinessExceptionImpl.processException(e);
				}finally {
					if (workbook != null) {
						try {
							workbook.close();
						} catch (IOException e) {
							_log.debug(e);
						}
					}
					if (out != null) {
						try {
							out.close();
						} catch (IOException e) {
							_log.debug(e);
						}
					}
				}
			}else {
				return Response.status(HttpURLConnection.HTTP_OK).entity(results).build();
			}

		} catch (Exception e) {
			return BusinessExceptionImpl.processException(e);
		}
	}
	private static void createList(HSSFSheet sheet,DossierActionModel dossierActionModel, Row row , int stt) // creating cells for each row
	{
		CellStyle cellStyle = createStyleForContent(sheet);
		Cell firstCell = row.createCell(0);
		firstCell.setCellStyle(cellStyle);
		firstCell.setCellValue(stt);

		Cell cell = row.createCell(1);
		cell.setCellValue(dossierActionModel.getActionUser());

		cell = row.createCell(2);
		cell.setCellValue(dossierActionModel.getActionName());

		cell = row.createCell(3);
		cell.setCellValue(dossierActionModel.getCreateDate());

		cell = row.createCell(4);
		cell.setCellValue(dossierActionModel.getActionNote());

	}
	// Auto resize column width
	private static void autosizeColumn(HSSFSheet sheet, int lastColumn) {
		for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
			sheet.autoSizeColumn(columnIndex);
		}
	}

	// Create CellStyle for header
	private static CellStyle createStyleForHeader(HSSFSheet sheet) {
		// Create font
		Font font = sheet.getWorkbook().createFont();
		font.setFontName("Times New Roman");
		font.setBold(true);
		font.setFontHeightInPoints((short) 13); // font size
		font.setColor(IndexedColors.BLACK.getIndex()); // text color

		// Create CellStyle
		CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		cellStyle.setBorderBottom(BorderStyle.THIN);
		return cellStyle;
	}

	private static void writeHeader(HSSFSheet sheet, int rowIndex, JSONArray headerArr ) {
		// create CellStyle
		CellStyle cellStyle = createStyleForHeader(sheet);

		// Create row
		Row row = sheet.createRow(rowIndex);

		// Create first cell - STT
		Cell firstCell = row.createCell(0);
		firstCell.setCellStyle(cellStyle);
		firstCell.setCellValue(STT);

		// Create cell
		for(int i = 1; i< headerArr.length(); i++) {
			Cell cell = row.createCell(i);
			cell.setCellStyle(cellStyle);
			cell.setCellValue(headerArr.getString(i));
		}
	}

	// Create CellStyle for content
	private static CellStyle createStyleForContent(HSSFSheet sheet) {
		// Create font
		Font font = sheet.getWorkbook().createFont();
		font.setFontName("Times New Roman");
		font.setBold(false);
		font.setFontHeightInPoints((short) 11); // font size
		font.setColor(IndexedColors.BLACK.getIndex()); // text color

		// Create CellStyle
		CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
		cellStyle.setFont(font);
		cellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		cellStyle.setWrapText(true);
		cellStyle.setAlignment(HorizontalAlignment.LEFT);
		return cellStyle;
	}
}
