package org.opencps.backend.exportmgt.util;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.Validator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Comment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.opencps.backend.exportmgt.exception.OutofRangeException;

/**
 * @author trungnt
 *
 */
public class ExcelReportUtil {
	private static Log _log = LogFactoryUtil.getLog(ExcelReportUtil.class.getName());

	public static String createReportFile(String templateFilePath, Map<String, Object> map) {

		File file = FileUtil.createTempFile("xlsx");

		String outputFilePath = StringPool.BLANK;

		Workbook workbook = null;

		FileInputStream fis = null;

		FileOutputStream fos = null;

		try {

			Path sourcePath = Paths.get(templateFilePath);

			Path targetPath = Files.copy(sourcePath, file.toPath());

			fis = new FileInputStream(targetPath.toFile());

			workbook = new XSSFWorkbook(fis);

			// close fis after init workbook
			fis.close();

			Sheet sheet = workbook.getSheetAt(0);

			CellAddress firstCellAddress = new CellAddress(0, 0);

			Comment comment = sheet.getCellComment(firstCellAddress);

			// check range
			int[] indexs = new int[] { 0, 0, 0, 0 };
			HashMap<Integer, String> dataListMap = new HashMap<Integer, String>();
			if (comment != null && Validator.isNotNull(comment.getString())) {
				indexs = ExcelParseUtil.getCellIndexs(comment.getString().getString());
				dataListMap = ExcelParseUtil.getDataListMap(comment.getString().getString());

			}

			if (indexs.length < 4) {
				indexs = new int[] { 0, 0, 0, 0 };
			}
			// fc = first col, lc = last col, fr = first row, lr = last row
			int fc = indexs[0];
			int lc = indexs[1];
			int fr = indexs[2];
			int lr = indexs[3];

			if (lc < 0 || lc < fc || lr < 0 || lr < fr) {
				throw new OutofRangeException("Template range error: " + comment.getString().getString());
			}

			Row row = null;

			int dataSize = 0;

			for (int r = fr; r < lr; r++) {

				row = sheet.getRow(r);

				if (row != null) {

					// fill data list
					// move group rows(from data list row template to last row) down to currentIndex
					// + (dataSize - 1) row

					if (dataListMap.containsKey(r)) {

						List<Object[]> tmp = (List<Object[]>) map.get(dataListMap.get(r));
						if (tmp != null) {
							dataSize = tmp.size();
						}

						if (dataSize > 1) {
							sheet.shiftRows(r, sheet.getLastRowNum(), dataSize - 1);
							Row newRow = null;
							int dataListCount = 0;

							for (int i = r; i <= r + (dataSize - 1); i++) {

								// new row insert
								if (i < r + (dataSize - 1)) {
									newRow = sheet.createRow(i);
									newRow.setHeight(row.getHeight());
									// create cell end set style
									Cell newCell = null;
									CellStyle cellStyle = null;
									for (int c = fc; c < lc; c++) {
										newCell = newRow.createCell(c);
										cellStyle = row.getCell(c).getCellStyle();

										if (cellStyle != null) {
											newCell.setCellStyle(cellStyle);
										}

										String value = row.getCell(c).getStringCellValue();
										value = ExcelParseUtil.parse(value, map, dataListCount);
										newCell.setCellValue(value);
									}
								} else {
									// current row
									for (int c = fc; c < lc; c++) {

										String value = row.getCell(c).getStringCellValue();
										value = ExcelParseUtil.parse(value, map, dataListCount);
										row.getCell(c).setCellValue(value);
									}
								}
								dataListCount++;

							}
							// increment last row index
							lr += (dataSize - 1);
						}
					} else {
						// insert data
						for (int c = fc; c < lc; c++) {

							Cell cell = row.getCell(c);

							if (cell != null) {
								if (cell.getCellComment() != null) {
									cell.removeCellComment();
								}
								String dataTemplate = cell.getStringCellValue();

								if (ExcelParseUtil.isDataPattern(dataTemplate)) {

									dataTemplate = ExcelParseUtil.parse(dataTemplate, map);

									cell.setCellValue(dataTemplate);
								}

							}
						}
					}
				}
			}

			fos = new FileOutputStream(targetPath.toFile());

			workbook.write(fos);

			fos.close();

			outputFilePath = file.getCanonicalPath();

		} catch (Exception e) {
			_log.error(e);
		} finally {
			try {

				if (workbook != null) {
					workbook.close();
				}
				if (fis != null) {
					fis.close();
				}

				if (fos != null) {
					fos.close();
				}
			} catch (Exception e) {
				_log.error(e);
			}
		}

		return outputFilePath;
	}

}
