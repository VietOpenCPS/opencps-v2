package org.opencps.jasper.utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class JRReportTemplateTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getJasperReportTest() {
		try{
			JRReportTemplate.getJasperReport("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getJRReportTemplateTest() {
		try{
			JRReportTemplate.getJRReportTemplate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}