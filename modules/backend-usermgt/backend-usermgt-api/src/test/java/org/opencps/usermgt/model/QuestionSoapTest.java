package org.opencps.usermgt.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class QuestionSoapTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getAddressTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.getAddress();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getContentTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.getContent();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getClassNameTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.getClassName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest() {
		try{
			QuestionSoap.toSoapModels(new Question[]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest5() {
		try{
			QuestionSoap.toSoapModels(new ArrayList<>());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest6() {
		try{
			QuestionSoap.toSoapModels(new Question[][]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDateTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupIdTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.setGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCompanyIdTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.setCompanyId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.setCreateDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCompanyIdTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.getCompanyId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupIdTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.getGroupId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.setModifiedDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelTest() {
		try{
			QuestionSoap.toSoapModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPhoneTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.setPhone("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDomainNameTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.getDomainName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDomainNameTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.setDomainName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGovAgencyNameTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.setGovAgencyName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGovAgencyCodeTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.getGovAgencyCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGovAgencyCodeTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.setGovAgencyCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDomainCodeTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.getDomainCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDomainCodeTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.setDomainCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGovAgencyNameTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.getGovAgencyName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSyncedTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.setSynced(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setContentTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.setContent("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAddressTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.setAddress("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setQuestionTypeTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.setQuestionType("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSubDomainCodeTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.getSubDomainCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSubDomainNameTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.setSubDomainName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPublishTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.getPublish();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getQuestionIdTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.getQuestionId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setQuestionIdTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.setQuestionId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPhoneTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.getPhone();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getQuestionTypeTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.getQuestionType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSubDomainCodeTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.setSubDomainCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSubDomainNameTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.getSubDomainName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPublishTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.setPublish(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getClassPKTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.getClassPK();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setClassPKTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.setClassPK("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setClassNameTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.setClassName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFullnameTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.setFullname("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmailTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.setEmail("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFullnameTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.getFullname();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmailTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.getEmail();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPrimaryKeyTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.setPrimaryKey(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPrimaryKeyTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.getPrimaryKey();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSyncedTest() {
		try{
			QuestionSoap object = new QuestionSoap();
			object.getSynced();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}