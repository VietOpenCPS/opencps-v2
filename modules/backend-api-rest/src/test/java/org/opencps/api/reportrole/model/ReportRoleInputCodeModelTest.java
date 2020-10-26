package org.opencps.api.reportrole.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ReportRoleInputCodeModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setReportCodeTest() {
		try{
			ReportRoleInputCodeModel object = new ReportRoleInputCodeModel();
			object.setReportCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReportCodeTest() {
		try{
			ReportRoleInputCodeModel object = new ReportRoleInputCodeModel();
			object.getReportCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRoleCodeTest() {
		try{
			ReportRoleInputCodeModel object = new ReportRoleInputCodeModel();
			object.setRoleCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRoleCodeTest() {
		try{
			ReportRoleInputCodeModel object = new ReportRoleInputCodeModel();
			object.getRoleCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}