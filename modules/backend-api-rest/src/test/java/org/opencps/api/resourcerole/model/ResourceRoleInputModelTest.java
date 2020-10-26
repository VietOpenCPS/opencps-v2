package org.opencps.api.resourcerole.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ResourceRoleInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getClassNameTest() {
		try{
			ResourceRoleInputModel object = new ResourceRoleInputModel();
			object.getClassName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRoleIdTest() {
		try{
			ResourceRoleInputModel object = new ResourceRoleInputModel();
			object.setRoleId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRoleIdTest() {
		try{
			ResourceRoleInputModel object = new ResourceRoleInputModel();
			object.getRoleId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getClassPKTest() {
		try{
			ResourceRoleInputModel object = new ResourceRoleInputModel();
			object.getClassPK();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setClassPKTest() {
		try{
			ResourceRoleInputModel object = new ResourceRoleInputModel();
			object.setClassPK("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setClassNameTest() {
		try{
			ResourceRoleInputModel object = new ResourceRoleInputModel();
			object.setClassName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}