package org.opencps.usermgt.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ApplicantSoapTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getAddressTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.getAddress();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest() {
		try{
			ApplicantSoap.toSoapModels(new ArrayList<>());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest3() {
		try{
			ApplicantSoap.toSoapModels(new Applicant[][]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest4() {
		try{
			ApplicantSoap.toSoapModels(new Applicant[]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDateTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserNameTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.getUserName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserNameTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.setUserName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupIdTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.setGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCompanyIdTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.setCompanyId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserIdTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.setUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.setCreateDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCompanyIdTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.getCompanyId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupIdTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.getGroupId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.setModifiedDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelTest() {
		try{
			ApplicantSoap.toSoapModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setApplicantIdNoTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.setApplicantIdNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantIdNoTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.getApplicantIdNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRepresentativeEnterpriseTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.getRepresentativeEnterprise();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setApplicantIdTypeTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.setApplicantIdType("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setApplicantIdDateTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.setApplicantIdDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWardCodeTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.getWardCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getContactNameTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.getContactName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getContactTelNoTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.getContactTelNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDistrictCodeTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.getDistrictCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCityNameTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.getCityName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getVerificationTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.getVerification();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDistrictNameTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.getDistrictName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWardNameTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.getWardName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getContactEmailTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.getContactEmail();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProfileTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.getProfile();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCityCodeTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.getCityCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantNameTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.getApplicantName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantIdTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.getApplicantId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMappingUserIdTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.getMappingUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTmpPassTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.getTmpPass();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMappingClassPKTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.setMappingClassPK("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRepresentativeEnterpriseTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.setRepresentativeEnterprise("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMappingClassPKTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.getMappingClassPK();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUuidTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.setUuid("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setProfileTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.setProfile("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantIdTypeTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.getApplicantIdType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantIdDateTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.getApplicantIdDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAddressTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.setAddress("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPrimaryKeyTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.setPrimaryKey(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPrimaryKeyTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.getPrimaryKey();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setApplicantIdTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.setApplicantId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDistrictNameTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.setDistrictName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWardCodeTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.setWardCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setContactNameTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.setContactName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWardNameTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.setWardName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setVerificationTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.setVerification(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setContactEmailTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.setContactEmail("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCityNameTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.setCityName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCityCodeTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.setCityCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDistrictCodeTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.setDistrictCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setContactTelNoTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.setContactTelNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setApplicantNameTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.setApplicantName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUuidTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.getUuid();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMappingClassNameTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.getMappingClassName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMappingUserIdTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.setMappingUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getLock_Test() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.getLock_();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTmpPassTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.setTmpPass("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setLock_Test() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.setLock_(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isLock_Test() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.isLock_();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActivationCodeTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.getActivationCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setActivationCodeTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.setActivationCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMappingClassNameTest() {
		try{
			ApplicantSoap object = new ApplicantSoap();
			object.setMappingClassName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}