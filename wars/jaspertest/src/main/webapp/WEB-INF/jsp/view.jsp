<%@page import="org.apache.commons.io.FileUtils"%>
<%@page import="java.io.ByteArrayInputStream"%>
<%@page import="java.nio.charset.StandardCharsets"%>
<%@page import="net.sf.jasperreports.engine.JasperExportManager"%>
<%@page import="net.sf.jasperreports.engine.JasperFillManager"%>
<%@page import="net.sf.jasperreports.engine.JasperPrint"%>
<%@page import="net.sf.jasperreports.engine.JasperCompileManager"%>
<%@page import="net.sf.jasperreports.engine.JasperReport"%>
<%@page import="java.io.File"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="net.sf.jasperreports.engine.data.JRBeanCollectionDataSource"%>
<%@page import="net.sf.jasperreports.engine.JRDataSource"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ include file="/WEB-INF/jsp/init.jsp" %>

<p>
	<b><liferay-ui:message key="jaspertest.caption" /></b>
</p>

<%

	List<Map<String, ?>> dataSource = new ArrayList<Map<String, ?>>();
	
	JRDataSource jrDataSource = new JRBeanCollectionDataSource(dataSource);
	
	String jrxmlTemplate = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <!-- Created with Jaspersoft Studio version 6.6.0.final using JasperReports Library version 6.6.0  --> <jasperReport xmlns=\"http://jasperreports.sourceforge.net/jasperreports\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd\" name=\"5Blank_A4\" pageWidth=\"595\" pageHeight=\"842\" columnWidth=\"555\" leftMargin=\"20\" rightMargin=\"20\" topMargin=\"20\" bottomMargin=\"20\" uuid=\"ae5dc07d-0d4f-4bea-81f6-9551fe5b94ac\"> 	<property name= \"net.sf.jasperreports.awt.ignore.missing.font \" value= \"true \"/> <queryString> 		<![CDATA[]]> 	</queryString> 	<background> 		<band splitType=\"Stretch\"/> 	</background> 	<title> 		<band height=\"79\" splitType=\"Stretch\"/> 	</title> 	<pageHeader> 		<band height=\"35\" splitType=\"Stretch\"/> 	</pageHeader> 	<columnHeader> 		<band height=\"61\" splitType=\"Stretch\"/> 	</columnHeader> 	<detail> 		<band height=\"88\" splitType=\"Stretch\"> 			<staticText> 				<reportElement x=\"160\" y=\"30\" width=\"100\" height=\"30\" uuid=\"5856b2af-f6b0-4531-bfde-659fd4a2041b\"/> 				<text><![CDATA[Static Text sdfsdf]]></text> 			</staticText> 		</band> 	</detail> 	<columnFooter> 		<band height=\"45\" splitType=\"Stretch\"/> 	</columnFooter> 	<pageFooter> 		<band height=\"54\" splitType=\"Stretch\"/> 	</pageFooter> 	<summary> 		<band height=\"42\" splitType=\"Stretch\"/> 	</summary> </jasperReport> ";
	
	InputStream input = new ByteArrayInputStream(jrxmlTemplate.getBytes(StandardCharsets.UTF_8));
	
	JasperReport jasperReport = JasperCompileManager.compileReport(input);
	
	JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, jrDataSource);
	
	byte[] abc = JasperExportManager.exportReportToPdf(jasperPrint);
	
	System.out.println("pdf" + abc);
	
	FileUtils.writeByteArrayToFile(new File("/Users/binhthgc/Documents/bbat/dkmlscl.pdf"), abc);

%>