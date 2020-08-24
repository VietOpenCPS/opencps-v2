package org.opencps.api.controller.util;

import org.apache.poi.ss.usermodel.*;

import java.io.InputStream;

public class ExcelUtils {
    public static String formatCell(Cell cell)
    {
        if (cell == null) {
            return "";
        }
        switch(cell.getCellType()) {
            case BOOLEAN:
                return Boolean.toString(cell.getBooleanCellValue());
            case NUMERIC:
                return String.valueOf((int)cell.getNumericCellValue());
            case STRING:
                return cell.getStringCellValue();
            default:
                return "";
        }
    }

    public static String formatElement(String prefix, String tag, String value) {
        StringBuilder stringBuilder = new StringBuilder(prefix);
        stringBuilder.append("<");
        stringBuilder.append(tag);
        if (value != null && value.length() > 0) {
            stringBuilder.append(">");
            stringBuilder.append(value);
            stringBuilder.append("</");
            stringBuilder.append(tag);
            stringBuilder.append(">");
        } else {
            stringBuilder.append("/>");
        }
        return stringBuilder.toString();
    }

    public static String createXmlStringFromExcelFile(InputStream fileInputStream) throws Exception{
    	Workbook wb = null;
    	try {
            boolean firstRow = true;
            wb = WorkbookFactory.create(fileInputStream);
            Sheet sheet = wb.getSheet("Sheet1");

            if(sheet == null) {
                throw new Exception("Sheet1 is not found in excel file");
            }

            StringBuilder resultXml = new StringBuilder();
            resultXml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            resultXml.append("\n\t<UserManagement>");
            resultXml.append("\n\t\t<users>");
            //parse row excel from xlsx file to xml row
            for(Row row: sheet) {
                if (firstRow == true) {
                    firstRow = false;
                    continue;
                }

                if (ExcelUtils.formatCell(row.getCell(0)) == null || ExcelUtils.formatCell(row.getCell(0)).isEmpty()) {
                    continue;
                }
                resultXml.append("\n\t\t\t<Employee>");
                resultXml.append(ExcelUtils.formatElement("\n\t\t\t\t", "employeeNo", ExcelUtils.formatCell(row.getCell(0))));
                resultXml.append(ExcelUtils.formatElement("\n\t\t\t\t", "fullname", ExcelUtils.formatCell(row.getCell(1))));
                resultXml.append(ExcelUtils.formatElement("\n\t\t\t\t", "title", ExcelUtils.formatCell(row.getCell(2))));
                resultXml.append(ExcelUtils.formatElement("\n\t\t\t\t", "gender", ExcelUtils.formatCell(row.getCell(3))));
                resultXml.append(ExcelUtils.formatElement("\n\t\t\t\t", "birthdate", ExcelUtils.formatCell(row.getCell(4))));
                resultXml.append(ExcelUtils.formatElement("\n\t\t\t\t", "telNo", ExcelUtils.formatCell(row.getCell(5))));
                resultXml.append(ExcelUtils.formatElement("\n\t\t\t\t", "email", ExcelUtils.formatCell(row.getCell(6))));
                resultXml.append(ExcelUtils.formatElement("\n\t\t\t\t", "workingStatus", ExcelUtils.formatCell(row.getCell(7))));
                resultXml.append(ExcelUtils.formatElement("\n\t\t\t\t", "jobTitle", ExcelUtils.formatCell(row.getCell(8))));
                resultXml.append(ExcelUtils.formatElement("\n\t\t\t\t", "roles", ExcelUtils.formatCell(row.getCell(9))));
                resultXml.append(ExcelUtils.formatElement("\n\t\t\t\t", "scope", ExcelUtils.formatCell(row.getCell(10))));
                resultXml.append("\n\t\t\t</Employee>");
            }
            resultXml.append("\n\t\t</users>");
            resultXml.append("\n\t</UserManagement>");
            String xmlString = resultXml.toString();

            if(xmlString.isEmpty()) {
                throw new Exception("Xml after created is empty");
            }
            return resultXml.toString();

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        finally {
			if (wb != null) {
				wb.close();
			}
		}
    }

}
