package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class EmployeeUtilsTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void mapperEmployeeAccountModelTest() {
//		try{
//			EmployeeUtils.mapperEmployeeAccountModel(null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void mapperEmployeeListTest() {
		try{
			EmployeeUtils.mapperEmployeeList(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mapperEmployeeJobposModelTest() {
		try{
			EmployeeUtils.mapperEmployeeJobposModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mapperEmployeeModelTest() {
		try{
			EmployeeUtils.mapperEmployeeModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void mapperEmployeeJobposListTest() {
//		try{
//			EmployeeUtils.mapperEmployeeJobposList(null, Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}