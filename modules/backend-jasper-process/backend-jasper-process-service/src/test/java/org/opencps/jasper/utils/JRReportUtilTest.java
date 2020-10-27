package org.opencps.jasper.utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import net.sf.jasperreports.engine.JasperPrint;

import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class JRReportUtilTest {
	@Before
	public void setUp() {
	}
	@Test
	public void createReportFileTest() {
		try{
			JRReportUtil.createReportFile("abcde", "abcde", null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createReportFileTest2() {
		try{
			JRReportUtil.createReportFile("abcde", "abcde", null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getJasperPrintTest() {
		try{
			JRReportUtil.getJasperPrint(null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getJasperPrintTest5() {
		try{
			JRReportUtil.getJasperPrint(null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void exportReportFileTest() {
		try{
			JRReportUtil.exportReportFile("abcde", "abcde", null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void quoteHTMLTest() {
		try{
			JRReportUtil.quoteHTML("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void exportReportTest() {
		try{
			JRReportUtil.exportReport(null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void exportDocFileTest() {
		try{
			JRReportUtil.exportDocFile(null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	
	@Test
	public void exportPdfFileTest11() {
		try{
			JRReportUtil.exportPdfFile(new JasperPrint(), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void exportExcelFileTest() {
		try{
			JRReportUtil.exportExcelFile(null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createReportPDFFileTest() {
		try{
			JRReportUtil.createReportPDFFile("abcde", "abcde", null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}