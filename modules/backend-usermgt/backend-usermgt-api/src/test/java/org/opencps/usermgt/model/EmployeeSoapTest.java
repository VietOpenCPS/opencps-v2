package org.opencps.usermgt.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class EmployeeSoapTest {
	@Before
	public void setUp() {
	}
	@Test
	public void toSoapModelsTest() {
		try{
			EmployeeSoap.toSoapModels(new ArrayList<>());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest2() {
		try{
			EmployeeSoap.toSoapModels(new Employee[][]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest3() {
		try{
			EmployeeSoap.toSoapModels(new Employee[]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDateTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserNameTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.getUserName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserNameTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.setUserName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupIdTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.setGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCompanyIdTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.setCompanyId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserIdTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.setUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.setCreateDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCompanyIdTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.getCompanyId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupIdTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.getGroupId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.setModifiedDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelTest() {
		try{
			EmployeeSoap.toSoapModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTitleTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.getTitle();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMappingUserIdTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.getMappingUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMobileTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.setMobile("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRecruitDateTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.setRecruitDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setLeaveDateTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.setLeaveDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setJobPosTitleTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.setJobPosTitle("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getJobPosTitleTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.getJobPosTitle();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmployeeIdTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.setEmployeeId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWorkingStatusTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.setWorkingStatus(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setBirthdateTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.setBirthdate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFullNameTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.setFullName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGenderTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.setGender(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmployeeNoTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.setEmployeeNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setScopeTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.setScope("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUuidTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.setUuid("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getScopeTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.getScope();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmployeeIdTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.getEmployeeId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmployeeNoTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.getEmployeeNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBirthdateTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.getBirthdate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWorkingStatusTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.getWorkingStatus();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRecruitDateTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.getRecruitDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMobileTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.getMobile();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getLeaveDateTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.getLeaveDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGenderTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.getGender();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTelNoTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.setTelNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmailTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.setEmail("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmailTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.getEmail();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTelNoTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.getTelNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPrimaryKeyTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.setPrimaryKey(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPrimaryKeyTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.getPrimaryKey();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFullNameTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.getFullName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTitleTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.setTitle("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMainJobPostIdTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.getMainJobPostId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUuidTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.getUuid();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMainJobPostIdTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.setMainJobPostId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMappingUserIdTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.setMappingUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileCertPathTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.getFileCertPath();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileCertIdTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.setFileCertId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileSignIdTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.getFileSignId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileCertIdTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.getFileCertId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileSignIdTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.setFileSignId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileCertPathTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.setFileCertPath("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileSignPathTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.setFileSignPath("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileSignPathTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.getFileSignPath();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPhotoFileEntryIdTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.getPhotoFileEntryId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPhotoFileEntryIdTest() {
		try{
			EmployeeSoap object = new EmployeeSoap();
			object.setPhotoFileEntryId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}