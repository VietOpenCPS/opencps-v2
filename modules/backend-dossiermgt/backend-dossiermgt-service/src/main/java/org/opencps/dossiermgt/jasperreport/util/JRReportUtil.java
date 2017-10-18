///**
// * OpenCPS is the open source Core Public Services software
// * Copyright (C) 2016-present OpenCPS community
//
// * This program is free software: you can redistribute it and/or modify
// * it under the terms of the GNU Affero General Public License as published by
// * the Free Software Foundation, either version 3 of the License, or
// * any later version.
//
// * This program is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
// * GNU Affero General Public License for more details.
// * You should have received a copy of the GNU Affero General Public License
// * along with this program. If not, see <http://www.gnu.org/licenses/>
// */
//package org.opencps.dossiermgt.jasperreport.util;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.Map;
//
//
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JasperExportManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//
//import org.opencps.dossiermgt.jasperreport.compile.JRReportTemplate;
//import org.opencps.dossiermgt.jasperreport.datasource.JRJSONDataSource;
//
//import com.liferay.portal.kernel.log.Log;
//import com.liferay.portal.kernel.log.LogFactoryUtil;
//import com.liferay.portal.kernel.util.StringPool;
//
///**
// * @author trungnt
// */
//public class JRReportUtil {
//
//	public static enum DocType {
//		DOC(".doc"), DOCX(".docx"), HTML(".html"), ODT(".odt"), PDF(".pdf"), PNG(
//				".png"), PPTX(".pptx"), RTF(".rtf"), XLS(".xls"), XLSX(".xlsx");
//		private String value;
//
//		DocType(String value) {
//
//			this.value = value;
//		}
//
//		public String getValue() {
//
//			return value;
//		}
//
//		@Override
//		public String toString() {
//
//			return this.getValue();
//		}
//
//		public static DocType getEnum(String value) {
//
//			for (DocType v : values())
//				if (v.getValue().equalsIgnoreCase(value))
//					return v;
//			throw new IllegalArgumentException();
//		}
//	}
//
//	/**
//	 * @param jrxmlTemplate
//	 * @param jsonData
//	 * @param parameters
//	 * @param destFileName
//	 * @return
//	 */
//	public static String createReportFile(String jrxmlTemplate,
//			String jsonData, Map<String, Object> parameters,
//			String destFileName) {
//
//		try {
//			// fix json enter char
//			jsonData = quoteHTML(jsonData);
//			JasperReport reportTemplate = JRReportTemplate
//					.getJasperReport(jrxmlTemplate);
//			JRJSONDataSource dataSource = JRJSONDataSource
//					.getInstance(jsonData);
//
//			JasperPrint jasperPrint = getJasperPrint(reportTemplate,
//					parameters, dataSource);
//
//			return exportReport(jasperPrint, destFileName, DocType.PDF);
//		} catch (Exception e) {
//			_log.error(e);
//
//			return StringPool.BLANK;
//
//		}
//	}
//
//	/**
//	 * @param jrxmlTemplate
//	 * @param jsonData
//	 * @param parameters
//	 * @param outputDestination
//	 * @param exportName
//	 * @return
//	 */
//	public static String createReportPDFFile(String jrxmlTemplate,
//			String jsonData, Map<String, Object> parameters,
//			String outputDestination, String exportName) {
//
//		String sourceFileName = outputDestination + exportName;
//		try {
//			// fix json enter char
//			jsonData = quoteHTML(jsonData);
//			JasperReport reportTemplate = JRReportTemplate
//					.getJasperReport(jrxmlTemplate);
//			JRJSONDataSource dataSource = JRJSONDataSource
//					.getInstance(jsonData);
//
//			JasperPrint jasperPrint = getJasperPrint(reportTemplate,
//					parameters, dataSource);
//
//			return exportPdfFile(jasperPrint, sourceFileName);
//		} catch (Exception e) {
//			_log.error(e);
//
//			return StringPool.BLANK;
//
//		}
//	}
//
//	public static String exportReportFile(String jrxmlTemplate, String formData,
//			Map<String, Object> map, String destFileName) {
//
//		return JRReportUtil.createReportFile(jrxmlTemplate, formData, map,
//				destFileName);
//	}
//	
//	public static String quoteHTML(String string) {
//
//		if (string == null || string.length() == 0) {
//			return "{}";
//		}
//
//		char c = 0;
//		int i;
//		int len = string.length();
//		StringBuilder sb = new StringBuilder(len + 4);
//		String t;
//
//		for (i = 0; i < len; i += 1) {
//			c = string.charAt(i);
//			switch (c) {
//			case '\\':
//				sb.append("REPLACEKEY");
//				break;
//			default:
//				if (c < ' ') {
//					t = "000" + Integer.toHexString(c);
//					sb.append("\\u" + t.substring(t.length() - 4));
//				} else {
//					sb.append(c);
//				}
//			}
//		}
//		String result = sb.toString().replaceAll("REPLACEKEYn", "<br />");
//		return result;
//	}
//	
//	/**
//	 * @param jasperPrint
//	 * @param destFileName
//	 * @return
//	 * @throws JRException
//	 */
//	protected static String exportPdfFile(JasperPrint jasperPrint,
//			String destFileName) throws JRException {
//
//		JasperExportManager.exportReportToPdfFile(jasperPrint, destFileName);
//
//		return destFileName;
//
//	}
//
//	/**
//	 * @param jasperPrint
//	 * @param sourceFileName
//	 * @param docType
//	 * @return
//	 * @throws JRException
//	 * @throws FileNotFoundException
//	 * @throws IOException
//	 */
//	protected static String exportReport(JasperPrint jasperPrint,
//			String sourceFileName, DocType docType) throws JRException,
//			FileNotFoundException, IOException {
//
//		switch (docType) {
//		case PDF:
//			sourceFileName = exportPdfFile(jasperPrint, sourceFileName);
//			break;
//		default:
//			break;
//		}
//		
//		return sourceFileName;
//	}
//
//	/**
//	 * @param jrReportTemplate
//	 * @param parameters
//	 * @param dataSource
//	 * @return
//	 * @throws JRException
//	 */
//	protected static JasperPrint getJasperPrint(JasperReport jrReportTemplate,
//			Map<String, Object> parameters, JRJSONDataSource dataSource)
//			throws JRException {
//
//		return JasperFillManager.fillReport(
//				jrReportTemplate, null, dataSource);
//	}
//
//	/**
//	 * @param jrReportTemplate
//	 * @param parameters
//	 * @param dataSource
//	 * @return
//	 * @throws JRException
//	 */
//	protected static JasperPrint getJasperPrint(
//			JRReportTemplate jrReportTemplate, Map<String, Object> parameters,
//			JRJSONDataSource dataSource) throws JRException {
//
//		return JasperFillManager.fillReport(
//				jrReportTemplate, null, dataSource);
//	}
//	
//	private static Log _log = LogFactoryUtil.getLog(JRReportUtil.class
//			.getName());
//}
