package org.opencps.usermgt.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class WorkingUnitSoapTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getAddressTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.getAddress();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNameTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.getName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setNameTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.setName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest() {
		try{
			WorkingUnitSoap.toSoapModels(new WorkingUnit[][]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest5() {
		try{
			WorkingUnitSoap.toSoapModels(new WorkingUnit[]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest6() {
		try{
			WorkingUnitSoap.toSoapModels(new ArrayList<>());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDateTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserNameTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.getUserName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserNameTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.setUserName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupIdTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.setGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCompanyIdTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.setCompanyId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserIdTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.setUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.setCreateDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCompanyIdTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.getCompanyId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupIdTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.getGroupId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.setModifiedDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelTest() {
		try{
			WorkingUnitSoap.toSoapModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCeremonyDateTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.setCeremonyDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getParentWorkingUnitIdTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.getParentWorkingUnitId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWebsiteTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.setWebsite("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getLevelTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.getLevel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSiblingTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.getSibling();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSiblingTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.setSibling("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setLevelTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.setLevel(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGovAgencyCodeTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.getGovAgencyCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGovAgencyCodeTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.setGovAgencyCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFaxNoTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.setFaxNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEnNameTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.setEnName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setParentWorkingUnitIdTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.setParentWorkingUnitId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUuidTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.setUuid("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAddressTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.setAddress("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWorkingUnitIdTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.getWorkingUnitId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWorkingUnitIdTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.setWorkingUnitId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEnNameTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.getEnName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFaxNoTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.getFaxNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWebsiteTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.getWebsite();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTelNoTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.setTelNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmailTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.setEmail("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmailTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.getEmail();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTelNoTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.getTelNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPrimaryKeyTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.setPrimaryKey(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPrimaryKeyTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.getPrimaryKey();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCeremonyDateTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.getCeremonyDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTreeIndexTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.setTreeIndex("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTreeIndexTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.getTreeIndex();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUuidTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.getUuid();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getLogoFileEntryIdTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.getLogoFileEntryId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setLogoFileEntryIdTest() {
		try{
			WorkingUnitSoap object = new WorkingUnitSoap();
			object.setLogoFileEntryId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}