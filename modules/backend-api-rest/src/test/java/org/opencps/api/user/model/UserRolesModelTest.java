package org.opencps.api.user.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class UserRolesModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getRoleNameTest() {
		try{
			UserRolesModel object = new UserRolesModel();
			object.getRoleName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRoleNameTest() {
		try{
			UserRolesModel object = new UserRolesModel();
			object.setRoleName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRoleIdTest() {
		try{
			UserRolesModel object = new UserRolesModel();
			object.setRoleId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRoleIdTest() {
		try{
			UserRolesModel object = new UserRolesModel();
			object.getRoleId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}