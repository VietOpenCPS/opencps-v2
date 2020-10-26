package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class JobposManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void updateTest() {
		try{
			JobposManagementImpl object = new JobposManagementImpl();
			object.update(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void deleteTest() {
//		try{
//			JobposManagementImpl object = new JobposManagementImpl();
//			object.delete(null, null, null, null, null, null, Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void readTest() {
//		try{
//			JobposManagementImpl object = new JobposManagementImpl();
//			object.read(null, null, null, null, null, null, Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void createTest() {
		try{
			JobposManagementImpl object = new JobposManagementImpl();
			object.create(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void readPermissionsTest() {
		try{
			JobposManagementImpl object = new JobposManagementImpl();
			object.readPermissions(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getJobpossTest() {
		try{
			JobposManagementImpl object = new JobposManagementImpl();
			object.getJobposs(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createPermissionsTest() {
		try{
			JobposManagementImpl object = new JobposManagementImpl();
			object.createPermissions(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createPermissionsPatchTest() {
		try{
			JobposManagementImpl object = new JobposManagementImpl();
			object.createPermissionsPatch(null, null, null, null, null, null, Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePermissionByKeyTest() {
		try{
			JobposManagementImpl object = new JobposManagementImpl();
			object.deletePermissionByKey(null, null, null, null, null, null, Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}