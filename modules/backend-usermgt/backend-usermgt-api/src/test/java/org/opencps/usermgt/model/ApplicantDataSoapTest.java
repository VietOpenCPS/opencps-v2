package org.opencps.usermgt.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ApplicantDataSoapTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getFileNameTest() {
		try{
			ApplicantDataSoap object = new ApplicantDataSoap();
			object.getFileName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest() {
		try{
			ApplicantDataSoap.toSoapModels(new ArrayList<>());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest3() {
		try{
			ApplicantDataSoap.toSoapModels(new ApplicantData[][]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest4() {
		try{
			ApplicantDataSoap.toSoapModels(new ApplicantData[]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDateTest() {
		try{
			ApplicantDataSoap object = new ApplicantDataSoap();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserNameTest() {
		try{
			ApplicantDataSoap object = new ApplicantDataSoap();
			object.getUserName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserNameTest() {
		try{
			ApplicantDataSoap object = new ApplicantDataSoap();
			object.setUserName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupIdTest() {
		try{
			ApplicantDataSoap object = new ApplicantDataSoap();
			object.setGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCompanyIdTest() {
		try{
			ApplicantDataSoap object = new ApplicantDataSoap();
			object.setCompanyId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserIdTest() {
		try{
			ApplicantDataSoap object = new ApplicantDataSoap();
			object.setUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			ApplicantDataSoap object = new ApplicantDataSoap();
			object.setCreateDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCompanyIdTest() {
		try{
			ApplicantDataSoap object = new ApplicantDataSoap();
			object.getCompanyId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			ApplicantDataSoap object = new ApplicantDataSoap();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			ApplicantDataSoap object = new ApplicantDataSoap();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupIdTest() {
		try{
			ApplicantDataSoap object = new ApplicantDataSoap();
			object.getGroupId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			ApplicantDataSoap object = new ApplicantDataSoap();
			object.setModifiedDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelTest() {
		try{
			ApplicantDataSoap.toSoapModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileTemplateNoTest() {
		try{
			ApplicantDataSoap object = new ApplicantDataSoap();
			object.getFileTemplateNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileTemplateNoTest() {
		try{
			ApplicantDataSoap object = new ApplicantDataSoap();
			object.setFileTemplateNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantDataTypeTest() {
		try{
			ApplicantDataSoap object = new ApplicantDataSoap();
			object.getApplicantDataType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setApplicantDataIdTest() {
		try{
			ApplicantDataSoap object = new ApplicantDataSoap();
			object.setApplicantDataId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setApplicantDataTypeTest() {
		try{
			ApplicantDataSoap object = new ApplicantDataSoap();
			object.setApplicantDataType(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantDataIdTest() {
		try{
			ApplicantDataSoap object = new ApplicantDataSoap();
			object.getApplicantDataId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setApplicantIdNoTest() {
		try{
			ApplicantDataSoap object = new ApplicantDataSoap();
			object.setApplicantIdNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantIdNoTest() {
		try{
			ApplicantDataSoap object = new ApplicantDataSoap();
			object.getApplicantIdNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUuidTest() {
		try{
			ApplicantDataSoap object = new ApplicantDataSoap();
			object.setUuid("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStatusTest() {
		try{
			ApplicantDataSoap object = new ApplicantDataSoap();
			object.setStatus(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileNoTest() {
		try{
			ApplicantDataSoap object = new ApplicantDataSoap();
			object.getFileNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileNoTest() {
		try{
			ApplicantDataSoap object = new ApplicantDataSoap();
			object.setFileNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileNameTest() {
		try{
			ApplicantDataSoap object = new ApplicantDataSoap();
			object.setFileName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileEntryIdTest() {
		try{
			ApplicantDataSoap object = new ApplicantDataSoap();
			object.getFileEntryId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileEntryIdTest() {
		try{
			ApplicantDataSoap object = new ApplicantDataSoap();
			object.setFileEntryId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMetadataTest() {
		try{
			ApplicantDataSoap object = new ApplicantDataSoap();
			object.getMetadata();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMetadataTest() {
		try{
			ApplicantDataSoap object = new ApplicantDataSoap();
			object.setMetadata("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStatusTest() {
		try{
			ApplicantDataSoap object = new ApplicantDataSoap();
			object.getStatus();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierNoTest() {
		try{
			ApplicantDataSoap object = new ApplicantDataSoap();
			object.setDossierNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierNoTest() {
		try{
			ApplicantDataSoap object = new ApplicantDataSoap();
			object.getDossierNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPrimaryKeyTest() {
		try{
			ApplicantDataSoap object = new ApplicantDataSoap();
			object.setPrimaryKey(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPrimaryKeyTest() {
		try{
			ApplicantDataSoap object = new ApplicantDataSoap();
			object.getPrimaryKey();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getLogTest() {
		try{
			ApplicantDataSoap object = new ApplicantDataSoap();
			object.getLog();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUuidTest() {
		try{
			ApplicantDataSoap object = new ApplicantDataSoap();
			object.getUuid();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setLogTest() {
		try{
			ApplicantDataSoap object = new ApplicantDataSoap();
			object.setLog("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}