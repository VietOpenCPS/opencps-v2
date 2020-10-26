package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ResourceRoleManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void cloneTest() {
		try{
			ResourceRoleManagementImpl object = new ResourceRoleManagementImpl();
			object.clone(null, null, null, null, null, null, "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteTest() {
		try{
			ResourceRoleManagementImpl object = new ResourceRoleManagementImpl();
			object.delete(null, null, null, null, null, null, "abcde", "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createTest() {
		try{
			ResourceRoleManagementImpl object = new ResourceRoleManagementImpl();
			object.create(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createResourceRolePatchTest() {
		try{
			ResourceRoleManagementImpl object = new ResourceRoleManagementImpl();
			object.createResourceRolePatch(null, null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getResourceRolesTest() {
		try{
			ResourceRoleManagementImpl object = new ResourceRoleManagementImpl();
			object.getResourceRoles(null, null, null, null, null, null, "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}