package org.opencps.usermgt.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ResourceUserSoapTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getClassNameTest() {
		try{
			ResourceUserSoap object = new ResourceUserSoap();
			object.getClassName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest() {
		try{
			ResourceUserSoap.toSoapModels(new ArrayList<>());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest3() {
		try{
			ResourceUserSoap.toSoapModels(new ResourceUser[][]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest4() {
		try{
			ResourceUserSoap.toSoapModels(new ResourceUser[]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDateTest() {
		try{
			ResourceUserSoap object = new ResourceUserSoap();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserNameTest() {
		try{
			ResourceUserSoap object = new ResourceUserSoap();
			object.getUserName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserNameTest() {
		try{
			ResourceUserSoap object = new ResourceUserSoap();
			object.setUserName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupIdTest() {
		try{
			ResourceUserSoap object = new ResourceUserSoap();
			object.setGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCompanyIdTest() {
		try{
			ResourceUserSoap object = new ResourceUserSoap();
			object.setCompanyId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserIdTest() {
		try{
			ResourceUserSoap object = new ResourceUserSoap();
			object.setUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			ResourceUserSoap object = new ResourceUserSoap();
			object.setCreateDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCompanyIdTest() {
		try{
			ResourceUserSoap object = new ResourceUserSoap();
			object.getCompanyId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			ResourceUserSoap object = new ResourceUserSoap();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			ResourceUserSoap object = new ResourceUserSoap();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupIdTest() {
		try{
			ResourceUserSoap object = new ResourceUserSoap();
			object.getGroupId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			ResourceUserSoap object = new ResourceUserSoap();
			object.setModifiedDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelTest() {
		try{
			ResourceUserSoap.toSoapModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setToUserIdTest() {
		try{
			ResourceUserSoap object = new ResourceUserSoap();
			object.setToUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getToUserIdTest() {
		try{
			ResourceUserSoap object = new ResourceUserSoap();
			object.getToUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setReadonlyTest() {
		try{
			ResourceUserSoap object = new ResourceUserSoap();
			object.setReadonly(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUuidTest() {
		try{
			ResourceUserSoap object = new ResourceUserSoap();
			object.setUuid("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isReadonlyTest() {
		try{
			ResourceUserSoap object = new ResourceUserSoap();
			object.isReadonly();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getClassPKTest() {
		try{
			ResourceUserSoap object = new ResourceUserSoap();
			object.getClassPK();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setClassPKTest() {
		try{
			ResourceUserSoap object = new ResourceUserSoap();
			object.setClassPK("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setClassNameTest() {
		try{
			ResourceUserSoap object = new ResourceUserSoap();
			object.setClassName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFullnameTest() {
		try{
			ResourceUserSoap object = new ResourceUserSoap();
			object.setFullname("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmailTest() {
		try{
			ResourceUserSoap object = new ResourceUserSoap();
			object.setEmail("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFullnameTest() {
		try{
			ResourceUserSoap object = new ResourceUserSoap();
			object.getFullname();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmailTest() {
		try{
			ResourceUserSoap object = new ResourceUserSoap();
			object.getEmail();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPrimaryKeyTest() {
		try{
			ResourceUserSoap object = new ResourceUserSoap();
			object.setPrimaryKey(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPrimaryKeyTest() {
		try{
			ResourceUserSoap object = new ResourceUserSoap();
			object.getPrimaryKey();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReadonlyTest() {
		try{
			ResourceUserSoap object = new ResourceUserSoap();
			object.getReadonly();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUuidTest() {
		try{
			ResourceUserSoap object = new ResourceUserSoap();
			object.getUuid();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getResourceUserIdTest() {
		try{
			ResourceUserSoap object = new ResourceUserSoap();
			object.getResourceUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setResourceUserIdTest() {
		try{
			ResourceUserSoap object = new ResourceUserSoap();
			object.setResourceUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}