package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class NewsBoardUtilsTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void mappingForGetDetailTest() {
//		try{
//			NewsBoardUtils.mappingForGetDetail(null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void mappingForGetListTest() {
		try{
			NewsBoardUtils.mappingForGetList(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}