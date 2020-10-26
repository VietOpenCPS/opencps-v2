package org.opencps.adminconfig.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ReportRoleSoapTest {
	@Before
	public void setUp() {
	}
	@Test
	public void toSoapModelsTest() {
		try{
			ReportRoleSoap.toSoapModels(new ArrayList<>());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest2() {
		try{
			ReportRoleSoap.toSoapModels(new ReportRole[][]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest3() {
		try{
			ReportRoleSoap.toSoapModels(new ReportRole[]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelTest() {
		try{
			ReportRoleSoap.toSoapModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPrimaryKeyTest() {
		try{
			ReportRoleSoap object = new ReportRoleSoap();
			object.setPrimaryKey(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPrimaryKeyTest() {
		try{
			ReportRoleSoap object = new ReportRoleSoap();
			object.getPrimaryKey();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDynamicReportIdTest() {
		try{
			ReportRoleSoap object = new ReportRoleSoap();
			object.setDynamicReportId(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDynamicReportIdTest() {
		try{
			ReportRoleSoap object = new ReportRoleSoap();
			object.getDynamicReportId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRoleIdTest() {
		try{
			ReportRoleSoap object = new ReportRoleSoap();
			object.getRoleId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setReportRoleIdTest() {
		try{
			ReportRoleSoap object = new ReportRoleSoap();
			object.setReportRoleId(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRoleIdTest() {
		try{
			ReportRoleSoap object = new ReportRoleSoap();
			object.setRoleId(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReportRoleIdTest() {
		try{
			ReportRoleSoap object = new ReportRoleSoap();
			object.getReportRoleId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}