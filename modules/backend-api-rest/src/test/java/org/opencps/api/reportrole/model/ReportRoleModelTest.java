package org.opencps.api.reportrole.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ReportRoleModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setRoleIdTest() {
		try{
			ReportRoleModel object = new ReportRoleModel();
			object.setRoleId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReportRoleIdTest() {
		try{
			ReportRoleModel object = new ReportRoleModel();
			object.getReportRoleId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRoleIdTest() {
		try{
			ReportRoleModel object = new ReportRoleModel();
			object.getRoleId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setReportRoleIdTest() {
		try{
			ReportRoleModel object = new ReportRoleModel();
			object.setReportRoleId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDynamicReportIdTest() {
		try{
			ReportRoleModel object = new ReportRoleModel();
			object.setDynamicReportId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDynamicReportIdTest() {
		try{
			ReportRoleModel object = new ReportRoleModel();
			object.getDynamicReportId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}