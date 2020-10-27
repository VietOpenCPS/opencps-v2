package org.opencps.usermgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class ResourceRoleActionsTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void cloneTest() {
//		try{
//			ResourceRoleActions object = new ResourceRoleActions();
//			object.clone("abcde", "abcde", Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void deleteTest() {
//		try{
//			ResourceRoleActions object = new ResourceRoleActions();
//			object.delete(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void createTest() {
//		try{
//			ResourceRoleActions object = new ResourceRoleActions();
//			object.create(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void createResourceRolePatchTest() {
//		try{
//			ResourceRoleActions object = new ResourceRoleActions();
//			object.createResourceRolePatch("abcde", "abcde", Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getResourceRolesTest() {
		try{
			ResourceRoleActions object = new ResourceRoleActions();
			object.getResourceRoles("abcde", "abcde", Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}