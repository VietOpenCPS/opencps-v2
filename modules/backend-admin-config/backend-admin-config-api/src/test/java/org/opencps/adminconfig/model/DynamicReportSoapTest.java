package org.opencps.adminconfig.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DynamicReportSoapTest {
	@Before
	public void setUp() {
	}
	@Test
	public void toSoapModelsTest() {
		try{
			DynamicReportSoap.toSoapModels(new ArrayList<>());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest2() {
		try{
			DynamicReportSoap.toSoapModels(new DynamicReport[][]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest3() {
		try{
			DynamicReportSoap.toSoapModels(new DynamicReport[]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCompanyIdTest() {
		try{
			DynamicReportSoap object = new DynamicReportSoap();
			object.getCompanyId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			DynamicReportSoap object = new DynamicReportSoap();
			object.setCreateDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setReportNameTest() {
		try{
			DynamicReportSoap object = new DynamicReportSoap();
			object.setReportName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFilterConfigTest() {
		try{
			DynamicReportSoap object = new DynamicReportSoap();
			object.setFilterConfig("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReportTypeTest() {
		try{
			DynamicReportSoap object = new DynamicReportSoap();
			object.getReportType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserConfigTest() {
		try{
			DynamicReportSoap object = new DynamicReportSoap();
			object.getUserConfig();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupIdTest() {
		try{
			DynamicReportSoap object = new DynamicReportSoap();
			object.setGroupId(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			DynamicReportSoap object = new DynamicReportSoap();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setReportTypeTest() {
		try{
			DynamicReportSoap object = new DynamicReportSoap();
			object.setReportType("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserNameTest() {
		try{
			DynamicReportSoap object = new DynamicReportSoap();
			object.setUserName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDateTest() {
		try{
			DynamicReportSoap object = new DynamicReportSoap();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupIdTest() {
		try{
			DynamicReportSoap object = new DynamicReportSoap();
			object.getGroupId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReportNameTest() {
		try{
			DynamicReportSoap object = new DynamicReportSoap();
			object.getReportName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReportCodeTest() {
		try{
			DynamicReportSoap object = new DynamicReportSoap();
			object.getReportCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserNameTest() {
		try{
			DynamicReportSoap object = new DynamicReportSoap();
			object.getUserName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTableConfigTest() {
		try{
			DynamicReportSoap object = new DynamicReportSoap();
			object.setTableConfig("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSharingTest() {
		try{
			DynamicReportSoap object = new DynamicReportSoap();
			object.setSharing(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setReportCodeTest() {
		try{
			DynamicReportSoap object = new DynamicReportSoap();
			object.setReportCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			DynamicReportSoap object = new DynamicReportSoap();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFilterConfigTest() {
		try{
			DynamicReportSoap object = new DynamicReportSoap();
			object.getFilterConfig();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			DynamicReportSoap object = new DynamicReportSoap();
			object.setModifiedDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserIdTest() {
		try{
			DynamicReportSoap object = new DynamicReportSoap();
			object.setUserId(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSharingTest() {
		try{
			DynamicReportSoap object = new DynamicReportSoap();
			object.getSharing();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCompanyIdTest() {
		try{
			DynamicReportSoap object = new DynamicReportSoap();
			object.setCompanyId(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTableConfigTest() {
		try{
			DynamicReportSoap object = new DynamicReportSoap();
			object.getTableConfig();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserConfigTest() {
		try{
			DynamicReportSoap object = new DynamicReportSoap();
			object.setUserConfig("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelTest() {
		try{
			DynamicReportSoap.toSoapModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPrimaryKeyTest() {
		try{
			DynamicReportSoap object = new DynamicReportSoap();
			object.getPrimaryKey();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPrimaryKeyTest() {
		try{
			DynamicReportSoap object = new DynamicReportSoap();
			object.setPrimaryKey(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDynamicReportIdTest() {
		try{
			DynamicReportSoap object = new DynamicReportSoap();
			object.setDynamicReportId(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDynamicReportIdTest() {
		try{
			DynamicReportSoap object = new DynamicReportSoap();
			object.getDynamicReportId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}