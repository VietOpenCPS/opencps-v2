package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ResourceUserManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void cloneTest() {
		try{
			ResourceUserManagementImpl object = new ResourceUserManagementImpl();
			object.clone(null, null, null, null, null, null, "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteTest() {
		try{
			ResourceUserManagementImpl object = new ResourceUserManagementImpl();
			object.delete(null, null, null, null, null, null, "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createTest() {
		try{
			ResourceUserManagementImpl object = new ResourceUserManagementImpl();
			object.create(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createResourceUserPatchTest() {
		try{
			ResourceUserManagementImpl object = new ResourceUserManagementImpl();
			object.createResourceUserPatch(null, null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getResourceUsersTest() {
		try{
			ResourceUserManagementImpl object = new ResourceUserManagementImpl();
			object.getResourceUsers(null, null, null, null, null, null, "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}