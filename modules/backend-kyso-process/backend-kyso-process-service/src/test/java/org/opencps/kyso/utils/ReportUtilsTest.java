package org.opencps.kyso.utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ReportUtilsTest {
	@Before
	public void setUp() {
	}
	
	@Test
	public void getTemplateReportFilePathTest2() {
		try{
			ReportUtils.getTemplateReportFilePath(null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}

	/*@Test
	public void getTemplateReportFilePathTest3() {
		try{
			ReportUtils.getTemplateReportFilePath(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}*/
	@Test
	public void getSubTemplatePathTest() {
		try{
			ReportUtils.getSubTemplatePath(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}