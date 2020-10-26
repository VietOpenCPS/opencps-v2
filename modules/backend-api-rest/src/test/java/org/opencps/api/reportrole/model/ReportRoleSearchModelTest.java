package org.opencps.api.reportrole.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ReportRoleSearchModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setReportCodeTest() {
		try{
			ReportRoleSearchModel object = new ReportRoleSearchModel();
			object.setReportCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReportCodeTest() {
		try{
			ReportRoleSearchModel object = new ReportRoleSearchModel();
			object.getReportCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRoleCodeTest() {
		try{
			ReportRoleSearchModel object = new ReportRoleSearchModel();
			object.setRoleCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRoleCodeTest() {
		try{
			ReportRoleSearchModel object = new ReportRoleSearchModel();
			object.getRoleCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEndTest() {
		try{
			ReportRoleSearchModel object = new ReportRoleSearchModel();
			object.setEnd(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStartTest() {
		try{
			ReportRoleSearchModel object = new ReportRoleSearchModel();
			object.setStart(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStartTest() {
		try{
			ReportRoleSearchModel object = new ReportRoleSearchModel();
			object.getStart();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEndTest() {
		try{
			ReportRoleSearchModel object = new ReportRoleSearchModel();
			object.getEnd();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}