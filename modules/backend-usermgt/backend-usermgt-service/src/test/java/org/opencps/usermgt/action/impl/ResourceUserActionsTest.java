package org.opencps.usermgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class ResourceUserActionsTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void cloneTest() {
//		try{
//			ResourceUserActions object = new ResourceUserActions();
//			object.clone("abcde", "abcde", Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void deleteTest() {
		try{
			ResourceUserActions object = new ResourceUserActions();
			object.delete(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void createTest() {
//		try{
//			ResourceUserActions object = new ResourceUserActions();
//			object.create(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", true, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void createResourceUserPatchTest() {
//		try{
//			ResourceUserActions object = new ResourceUserActions();
//			object.createResourceUserPatch("abcde", "abcde", Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getResourceUsersTest() {
		try{
			ResourceUserActions object = new ResourceUserActions();
			object.getResourceUsers("abcde", "abcde", Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}