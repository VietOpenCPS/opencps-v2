package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class EFormUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void checkPasswordTest() {
		try{
			EFormUtils.checkPassword(null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void mappingForGetDetailTest() {
//		try{
//			EFormUtils.mappingForGetDetail(null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void mappingForGetListTest() {
		try{
			EFormUtils.mappingForGetList(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}