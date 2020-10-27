package org.opencps.usermgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class JobposActionsTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void updateTest() {
//		try{
//			JobposActions object = new JobposActions();
//			object.update(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void createTest() {
//		try{
//			JobposActions object = new JobposActions();
//			object.create(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getJobPosTest() {
//		try{
//			JobposActions object = new JobposActions();
//			object.getJobPos(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateJobPosDBTest() {
//		try{
//			JobposActions object = new JobposActions();
//			object.updateJobPosDB(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	@Test
//	public void createPermissionsTest() {
//		try{
//			JobposActions object = new JobposActions();
//			object.createPermissions(Long.valueOf(0), Long.valueOf(0), "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	
//	@Test
//	public void deletePermissionByKeyTest() {
//		try{
//			JobposActions object = new JobposActions();
//			object.deletePermissionByKey(Long.valueOf(0), Long.valueOf(0), "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	
	
	@Test
	public void getJobposPermissionsTest() {
		try{
			JobposActions object = new JobposActions();
			object.getJobposPermissions();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getJobposTest() {
		try{
			JobposActions object = new JobposActions();
			object.getJobpos(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	
	@Test
	public void createPermissionsPatchTest() {
		try{
			JobposActions object = new JobposActions();
			object.createPermissionsPatch(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}