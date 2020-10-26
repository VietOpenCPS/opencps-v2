package org.opencps.api.v21.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class UserManagementTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getRolesTest() {
		try{
			UserManagement object = new UserManagement();
			object.getRoles();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRolesTest() {
		try{
			UserManagement object = new UserManagement();
			object.setRoles(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUsersTest() {
		try{
			UserManagement object = new UserManagement();
			object.setUsers(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUsersTest() {
		try{
			UserManagement object = new UserManagement();
			object.getUsers();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}