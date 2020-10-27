package org.opencps.usermgt.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ResourceRoleSoapTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getClassNameTest() {
		try{
			ResourceRoleSoap object = new ResourceRoleSoap();
			object.getClassName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest() {
		try{
			ResourceRoleSoap.toSoapModels(new ArrayList<>());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest3() {
		try{
			ResourceRoleSoap.toSoapModels(new ResourceRole[][]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest4() {
		try{
			ResourceRoleSoap.toSoapModels(new ResourceRole[]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDateTest() {
		try{
			ResourceRoleSoap object = new ResourceRoleSoap();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserNameTest() {
		try{
			ResourceRoleSoap object = new ResourceRoleSoap();
			object.getUserName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserNameTest() {
		try{
			ResourceRoleSoap object = new ResourceRoleSoap();
			object.setUserName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupIdTest() {
		try{
			ResourceRoleSoap object = new ResourceRoleSoap();
			object.setGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCompanyIdTest() {
		try{
			ResourceRoleSoap object = new ResourceRoleSoap();
			object.setCompanyId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserIdTest() {
		try{
			ResourceRoleSoap object = new ResourceRoleSoap();
			object.setUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			ResourceRoleSoap object = new ResourceRoleSoap();
			object.setCreateDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCompanyIdTest() {
		try{
			ResourceRoleSoap object = new ResourceRoleSoap();
			object.getCompanyId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			ResourceRoleSoap object = new ResourceRoleSoap();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			ResourceRoleSoap object = new ResourceRoleSoap();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupIdTest() {
		try{
			ResourceRoleSoap object = new ResourceRoleSoap();
			object.getGroupId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			ResourceRoleSoap object = new ResourceRoleSoap();
			object.setModifiedDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelTest() {
		try{
			ResourceRoleSoap.toSoapModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setReadonlyTest() {
		try{
			ResourceRoleSoap object = new ResourceRoleSoap();
			object.setReadonly(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUuidTest() {
		try{
			ResourceRoleSoap object = new ResourceRoleSoap();
			object.setUuid("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRoleIdTest() {
		try{
			ResourceRoleSoap object = new ResourceRoleSoap();
			object.setRoleId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRoleIdTest() {
		try{
			ResourceRoleSoap object = new ResourceRoleSoap();
			object.getRoleId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getClassPKTest() {
		try{
			ResourceRoleSoap object = new ResourceRoleSoap();
			object.getClassPK();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setClassPKTest() {
		try{
			ResourceRoleSoap object = new ResourceRoleSoap();
			object.setClassPK("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setClassNameTest() {
		try{
			ResourceRoleSoap object = new ResourceRoleSoap();
			object.setClassName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPrimaryKeyTest() {
		try{
			ResourceRoleSoap object = new ResourceRoleSoap();
			object.setPrimaryKey(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPrimaryKeyTest() {
		try{
			ResourceRoleSoap object = new ResourceRoleSoap();
			object.getPrimaryKey();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReadonlyTest() {
		try{
			ResourceRoleSoap object = new ResourceRoleSoap();
			object.getReadonly();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUuidTest() {
		try{
			ResourceRoleSoap object = new ResourceRoleSoap();
			object.getUuid();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setResourceRoleIdTest() {
		try{
			ResourceRoleSoap object = new ResourceRoleSoap();
			object.setResourceRoleId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getResourceRoleIdTest() {
		try{
			ResourceRoleSoap object = new ResourceRoleSoap();
			object.getResourceRoleId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}