///**
//* OpenCPS is the open source Core Public Services software
//* Copyright (C) 2016-present OpenCPS community
//
//* This program is free software: you can redistribute it and/or modify
//* it under the terms of the GNU Affero General Public License as published by
//* the Free Software Foundation, either version 3 of the License, or
//* any later version.
//
//* This program is distributed in the hope that it will be useful,
//* but WITHOUT ANY WARRANTY; without even the implied warranty of
//* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
//* GNU Affero General Public License for more details.
//* You should have received a copy of the GNU Affero General Public License
//* along with this program. If not, see <http://www.gnu.org/licenses/>
//*/
//
//package org.opencps.dossiermgt.jasperreport.compile;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.Serializable;
//import java.nio.charset.StandardCharsets;
//
//import net.sf.jasperreports.engine.JRException;
//import net.sf.jasperreports.engine.JRExpressionCollector;
//import net.sf.jasperreports.engine.JRReport;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.base.JRBaseObjectFactory;
//
///**
// * @author trungnt
// */
//public class JRReportTemplate extends JasperReport {
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	public JRReportTemplate(
//	    JRReport report, String compilerClass, Serializable compileData,
//	    JRExpressionCollector expressionCollector, String compileNameSuffix) {
//		super(
//		    report, compilerClass, compileData, expressionCollector, compileNameSuffix);
//
//	}
//
//	public JRReportTemplate(
//	    JRReport report, String compilerClass, Serializable compileData,
//	    JRBaseObjectFactory factory, String compileNameSuffix) {
//		super(
//		    report, compilerClass, compileData, factory, compileNameSuffix);
//
//	}
//	
//	public static JasperReport getJasperReport(String jrxmlTemplate)
//	    throws JRException, IOException {
//
//		InputStream isTemplate = new ByteArrayInputStream(jrxmlTemplate
//		    .getBytes(StandardCharsets.UTF_8));
//		JasperReport reportTemplate = JasperCompileManager
//		    .compileReport(isTemplate);
//		
//		if(isTemplate != null){
//			isTemplate.close();
//		}
//
//		return reportTemplate;
//	}
//
//	public static JRReportTemplate getJRReportTemplate(String jrxmlTemplate)
//	    throws JRException, IOException {
//
//		InputStream isTemplate = new ByteArrayInputStream(jrxmlTemplate
//		    .getBytes(StandardCharsets.UTF_8));
//		JRReportTemplate reportTemplate =
//		    (JRReportTemplate) JasperCompileManager
//		        .compileReport(isTemplate);
//		
//		if(isTemplate != null){
//			isTemplate.close();
//		}
//
//		return reportTemplate;
//	}
//
//}
