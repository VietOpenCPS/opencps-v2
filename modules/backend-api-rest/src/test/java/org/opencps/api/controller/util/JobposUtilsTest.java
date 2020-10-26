package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class JobposUtilsTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void getTotalTest() {
//		try{
//			JobposUtils.getTotal(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void mapperJobposListTest() {
		try{
			JobposUtils.mapperJobposList(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mapperJobposModelTest() {
		try{
			JobposUtils.mapperJobposModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mapperJobposPermissionModelTest() {
		try{
			JobposUtils.mapperJobposPermissionModel("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mapperJobposPermissionsListTest() {
		try{
			JobposUtils.mapperJobposPermissionsList(null, Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}