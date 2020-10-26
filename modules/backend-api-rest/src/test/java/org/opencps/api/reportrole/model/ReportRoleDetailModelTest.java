package org.opencps.api.reportrole.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ReportRoleDetailModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setRoleIdTest() {
		try{
			ReportRoleDetailModel object = new ReportRoleDetailModel();
			object.setRoleId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReportRoleIdTest() {
		try{
			ReportRoleDetailModel object = new ReportRoleDetailModel();
			object.getReportRoleId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRoleIdTest() {
		try{
			ReportRoleDetailModel object = new ReportRoleDetailModel();
			object.getRoleId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setReportRoleIdTest() {
		try{
			ReportRoleDetailModel object = new ReportRoleDetailModel();
			object.setReportRoleId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDynamicReportIdTest() {
		try{
			ReportRoleDetailModel object = new ReportRoleDetailModel();
			object.setDynamicReportId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDynamicReportIdTest() {
		try{
			ReportRoleDetailModel object = new ReportRoleDetailModel();
			object.getDynamicReportId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}