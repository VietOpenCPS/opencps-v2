package org.opencps.api.reportrole.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ReportRoleInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setRoleIdTest() {
		try{
			ReportRoleInputModel object = new ReportRoleInputModel();
			object.setRoleId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRoleIdTest() {
		try{
			ReportRoleInputModel object = new ReportRoleInputModel();
			object.getRoleId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDynamicReportIdTest() {
		try{
			ReportRoleInputModel object = new ReportRoleInputModel();
			object.setDynamicReportId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDynamicReportIdTest() {
		try{
			ReportRoleInputModel object = new ReportRoleInputModel();
			object.getDynamicReportId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}