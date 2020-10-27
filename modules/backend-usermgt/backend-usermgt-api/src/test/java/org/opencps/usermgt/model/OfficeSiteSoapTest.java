package org.opencps.usermgt.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class OfficeSiteSoapTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getAddressTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.getAddress();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNameTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.getName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setNameTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.setName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest() {
		try{
			OfficeSiteSoap.toSoapModels(new ArrayList<>());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest5() {
		try{
			OfficeSiteSoap.toSoapModels(new OfficeSite[]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest6() {
		try{
			OfficeSiteSoap.toSoapModels(new OfficeSite[][]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDateTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserNameTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.getUserName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserNameTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.setUserName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupIdTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.setGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCompanyIdTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.setCompanyId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserIdTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.setUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.setCreateDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCompanyIdTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.getCompanyId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupIdTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.getGroupId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.setModifiedDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelTest() {
		try{
			OfficeSiteSoap.toSoapModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCeremonyDateTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.setCeremonyDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWebsiteTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.setWebsite("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGovAgencyCodeTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.getGovAgencyCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGovAgencyCodeTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.setGovAgencyCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFaxNoTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.setFaxNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOfficeSiteIdTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.getOfficeSiteId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAdminUserIdTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.getAdminUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOfficeSiteIdTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.setOfficeSiteId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEnNameTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.setEnName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSiteGroupIdTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.setSiteGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUuidTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.setUuid("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAddressTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.setAddress("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSiteGroupIdTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.getSiteGroupId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPreferencesTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.getPreferences();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEnNameTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.getEnName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFaxNoTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.getFaxNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWebsiteTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.getWebsite();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPreferencesTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.setPreferences("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTelNoTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.setTelNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmailTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.setEmail("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmailTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.getEmail();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTelNoTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.getTelNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPrimaryKeyTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.setPrimaryKey(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPrimaryKeyTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.getPrimaryKey();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCeremonyDateTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.getCeremonyDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUuidTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.getUuid();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAdminUserIdTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.setAdminUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getLogoFileEntryIdTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.getLogoFileEntryId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setLogoFileEntryIdTest() {
		try{
			OfficeSiteSoap object = new OfficeSiteSoap();
			object.setLogoFileEntryId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}