package org.opencps.jasper.utils;
/**
 * OpenCPS is the open source Core Public Services software
 * Copyright (C) 2016-present OpenCPS community

 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.

 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.export.SimpleDocxReportConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

/**
 * @author trungnt
 */
public class JRReportUtil {

	public static enum DocType {
		DOC(".doc"), DOCX(".docx"), HTML(".html"), ODT(".odt"), PDF(".pdf"), PNG(".png"), PPTX(".pptx"), RTF(
				".rtf"), XLS(".xls"), XLSX(".xlsx");
		private String value;

		DocType(String value) {

			this.value = value;
		}

		public String getValue() {

			return value;
		}

		@Override
		public String toString() {

			return this.getValue();
		}

		public static DocType getEnum(String value) {

			for (DocType v : values())
				if (v.getValue().equalsIgnoreCase(value))
					return v;
			throw new IllegalArgumentException();
		}
	}

	private static boolean isJsonObject(String jsonString) {
        try {
            JSONArray arr = JSONFactoryUtil.createJSONArray(jsonString);
            if (arr == null)
            	return true;
            else
            	return false;
        } catch (JSONException ex) {
        	_log.debug(ex);
            try {
                JSONObject obj = JSONFactoryUtil.createJSONObject(jsonString);
                if (obj == null) {
                	return false;
                }
                else {
                	return true;
                }
            } catch (JSONException e) {
            	_log.debug(e);
                return false;
            }
        }
 	}
	
	/**
	 * @param jrxmlTemplate
	 * @param jsonData
	 * @param parameters
	 * @param destFileName
	 * @return
	 */
	public static String createReportFile(String jrxmlTemplate, String jsonData, Map<String, Object> parameters,
			String destFileName) {

		try {
			// fix json enter char
			//hot fix
//			jsonData = quoteHTML(jsonData);
//			_log.info("JASPER JSON DATA: " + jsonData);
			if (isJsonObject(jsonData)) {
				//_log.info("JSON Object");
			JasperReport reportTemplate = JRReportTemplate.getJasperReport(jrxmlTemplate);
			JRJSONDataSource dataSource = JRJSONDataSource.getInstance(jsonData);

			JasperPrint jasperPrint = getJasperPrint(reportTemplate, parameters, dataSource);

				return exportReport(jasperPrint, destFileName, DocType.PDF);
			}
			else {
				_log.info("JSON array");
				List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
				
				JSONArray jsonArrData = JSONFactoryUtil.createJSONArray(jsonData);
				_log.info("JSON Array data object: " + jsonArrData.toJSONString());
//				if (jsonArrData.length() > 0) {
//					JSONObject jsonDataObj = jsonArrData.getJSONObject(0);
//					
//					JasperReport reportTemplate = JRReportTemplate.getJasperReport(jrxmlTemplate);
//					JRJSONDataSource dataSource = JRJSONDataSource.getInstance(jsonDataObj.toJSONString());
//
//					JasperPrint jasperPrint = getJasperPrint(reportTemplate, parameters, dataSource);	
//					return exportReport(jasperPrint, destFileName, DocType.PDF);									
//				}
//				else {
//					return StringPool.BLANK;
//				}
				JasperPrint jasperPrint = null;
				JasperReport reportTemplate = JRReportTemplate.getJasperReport(jrxmlTemplate);
				JRJSONDataSource dataSource = null;
				
				for (int i = 0; i < jsonArrData.length(); i++) {
					JSONObject jsonDataObj = jsonArrData.getJSONObject(i);
					_log.info("JASPER ONE JSON: " + jsonDataObj.toJSONString());
					
					dataSource = JRJSONDataSource.getInstance(jsonDataObj.toJSONString());

					jasperPrint = getJasperPrint(reportTemplate, parameters, dataSource);	
					jasperPrints.add(jasperPrint);

				}
//				return exportReport(jasperPrint, destFileName, DocType.PDF);				
				
				return exportPdfFile(jasperPrints, destFileName);
			}
		} catch (Exception e) {
			_log.error(e);

			return StringPool.BLANK;

		}
	}

	/**
	 * @param jrxmlTemplate
	 * @param jsonData
	 * @param parameters
	 * @param outputDestination
	 * @param exportName
	 * @return
	 */
	public static String createReportPDFFile(String jrxmlTemplate, String jsonData, Map<String, Object> parameters,
			String outputDestination, String exportName) {

		String sourceFileName = outputDestination + exportName;
		try {
			// fix json enter char
			jsonData = quoteHTML(jsonData);
			JasperReport reportTemplate = JRReportTemplate.getJasperReport(jrxmlTemplate);
			JRJSONDataSource dataSource = JRJSONDataSource.getInstance(jsonData);

			JasperPrint jasperPrint = getJasperPrint(reportTemplate, parameters, dataSource);

			return exportPdfFile(jasperPrint, sourceFileName);
		} catch (Exception e) {
			_log.error(e);

			return StringPool.BLANK;

		}
	}

	public static String exportReportFile(String jrxmlTemplate, String formData, Map<String, Object> map,
			String destFileName) {

		return JRReportUtil.createReportFile(jrxmlTemplate, formData, map, destFileName);
	}

	public static String quoteHTML(String string) {

		if (string == null || string.length() == 0) {
			return "{}";
		}

		char c = 0;
		int i;
		int len = string.length();
		StringBuilder sb = new StringBuilder(len + 4);
		String t;

		for (i = 0; i < len; i += 1) {
			c = string.charAt(i);
			switch (c) {
			case '\\':
				sb.append("REPLACEKEY");
				break;
			default:
				if (c < ' ') {
					t = "000" + Integer.toHexString(c);
					sb.append("\\u" + t.substring(t.length() - 4));
				} else {
					sb.append(c);
				}
			}
		}
		String result = sb.toString().replaceAll("REPLACEKEYn", "<br />");
		return result;
	}

	/**
	 * @param jasperPrint
	 * @param destFileName
	 * @return
	 * @throws JRException
	 */
	protected static String exportPdfFile(JasperPrint jasperPrint, String destFileName) throws JRException {

		JasperExportManager.exportReportToPdfFile(jasperPrint, destFileName);

		return destFileName;

	}

	/**
	 * @param jasperPrint
	 * @param destFileName
	 * @return
	 * @throws JRException
	 */
	protected static String exportExcelFile(JasperPrint jasperPrint, String destFileName) throws JRException {

		JRXlsExporter exporter = new JRXlsExporter();
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFileName));
		SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
//		configuration.setOnePagePerSheet(true);
		configuration.setDetectCellType(true);
		configuration.setCollapseRowSpan(false);
		configuration.setRemoveEmptySpaceBetweenRows(true);
		exporter.setConfiguration(configuration);
		exporter.exportReport();

		return destFileName;

	}

	/**
	 * @param jasperPrint
	 * @param destFileName
	 * @return
	 * @throws JRException
	 */
	protected static String exportDocFile(JasperPrint jasperPrint, String destFileName) throws JRException {
		JRDocxExporter export = new JRDocxExporter();
		export.setExporterInput(new SimpleExporterInput(jasperPrint));
		export.setExporterOutput(new SimpleOutputStreamExporterOutput(destFileName));

		SimpleDocxReportConfiguration config = new SimpleDocxReportConfiguration();
		config.setFlexibleRowHeight(true); //Set desired configuration

		export.setConfiguration(config);            
		export.exportReport();
		
		return destFileName;

	}

	/**
	 * @param jasperPrints
	 * @param destFileName
	 * @return
	 * @throws JRException
	 */
	protected static String exportPdfFile(List<JasperPrint> jasperPrints, String destFileName) throws JRException {
		JRPdfExporter exporter = new JRPdfExporter();

		exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrints));
		exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(destFileName));
		SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
		configuration.setCreatingBatchModeBookmarks(true);
		exporter.setConfiguration(configuration);

		exporter.exportReport();
//		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//
//		JRPdfExporter exporter = new JRPdfExporter();
//
//		exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrints);
//		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
//
//		exporter.exportReport();
//
//		try (OutputStream outputStreamWrite = new FileOutputStream(destFileName)) {
//			outputStream.writeTo(outputStreamWrite);
//		}
//		catch (IOException e) {
//			
//		}
				
		return destFileName;

	}	

	/**
	 * @param jasperPrint
	 * @param sourceFileName
	 * @param docType
	 * @return
	 * @throws JRException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	protected static String exportReport(JasperPrint jasperPrint, String sourceFileName, DocType docType)
			throws JRException, FileNotFoundException, IOException {

		switch (docType) {
		case PDF:
			sourceFileName = exportPdfFile(jasperPrint, sourceFileName);
			break;
		case XLS:
			sourceFileName = exportExcelFile(jasperPrint, sourceFileName);
			break;	
		case DOC:
			sourceFileName = exportDocFile(jasperPrint, sourceFileName);
			break;				
		default:
			break;
		}

		return sourceFileName;
	}

	/**
	 * @param jrReportTemplate
	 * @param parameters
	 * @param dataSource
	 * @return
	 * @throws JRException
	 */
	protected static JasperPrint getJasperPrint(JasperReport jrReportTemplate, Map<String, Object> parameters,
			JRJSONDataSource dataSource) throws JRException {

		return JasperFillManager.fillReport(jrReportTemplate, null, dataSource);
	}

	/**
	 * @param jrReportTemplate
	 * @param parameters
	 * @param dataSource
	 * @return
	 * @throws JRException
	 */
	protected static JasperPrint getJasperPrint(JRReportTemplate jrReportTemplate, Map<String, Object> parameters,
			JRJSONDataSource dataSource) throws JRException {

		return JasperFillManager.fillReport(jrReportTemplate, null, dataSource);
	}

	public static String createReportFile(String jrxmlTemplate, String jsonData, Map<String, Object> parameters,
			String destFileName, String reportType) {

		try {
				// fix json enter char
				//hot fix
	//			jsonData = quoteHTML(jsonData);
	//			_log.info("JASPER JSON DATA: " + jsonData);
				if (isJsonObject(jsonData)) {
	//				_log.info("JSON Object");
				JasperReport reportTemplate = JRReportTemplate.getJasperReport(jrxmlTemplate);
				JRJSONDataSource dataSource = JRJSONDataSource.getInstance(jsonData);
	
				JasperPrint jasperPrint = getJasperPrint(reportTemplate, parameters, dataSource);

				if ("excel".equals(reportType)) {
					return exportReport(jasperPrint, destFileName, DocType.XLS);						
				}
				else if ("word".equals(reportType)) {
					return exportReport(jasperPrint, destFileName, DocType.DOC);	
				}
				else {
					return exportReport(jasperPrint, destFileName, DocType.PDF);
				}
			}
			else {
//				_log.info("JSON array");
				List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
				
				JSONArray jsonArrData = JSONFactoryUtil.createJSONArray(jsonData);
//				_log.info("JSON Array data object: " + jsonArrData.toJSONString());
//				if (jsonArrData.length() > 0) {
//					JSONObject jsonDataObj = jsonArrData.getJSONObject(0);
//					
//					JasperReport reportTemplate = JRReportTemplate.getJasperReport(jrxmlTemplate);
//					JRJSONDataSource dataSource = JRJSONDataSource.getInstance(jsonDataObj.toJSONString());
//
//					JasperPrint jasperPrint = getJasperPrint(reportTemplate, parameters, dataSource);	
//					return exportReport(jasperPrint, destFileName, DocType.PDF);									
//				}
//				else {
//					return StringPool.BLANK;
//				}
				JasperPrint jasperPrint = null;
				JasperReport reportTemplate = JRReportTemplate.getJasperReport(jrxmlTemplate);
				JRJSONDataSource dataSource = null;
				
				for (int i = 0; i < jsonArrData.length(); i++) {
					JSONObject jsonDataObj = jsonArrData.getJSONObject(i);
//					_log.info("JASPER ONE JSON: " + jsonDataObj.toJSONString());
					
					dataSource = JRJSONDataSource.getInstance(jsonDataObj.toJSONString());

					jasperPrint = getJasperPrint(reportTemplate, parameters, dataSource);	
					jasperPrints.add(jasperPrint);

				}
//				return exportReport(jasperPrint, destFileName, DocType.PDF);				
				
				return exportPdfFile(jasperPrints, destFileName);
			}
		} catch (Exception e) {
			_log.error(e);

			return StringPool.BLANK;

		}
	}
	
	private static Log _log = LogFactoryUtil.getLog(JRReportUtil.class.getName());
}
