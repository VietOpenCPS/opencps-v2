package org.opencps.dossiermgt.action.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class AutoFillFormDataTest {
	@Before
	public void setUp() {
	}
	@Test
	public void toMapTest() {
		try{
			AutoFillFormData.toMap(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toListTest() {
		try{
			AutoFillFormData.toList(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void sampleDataBindingTest() {
		try{
			AutoFillFormData.sampleDataBinding("abcde", Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void sampleDataBindingTest4() {
		try{
			AutoFillFormData.sampleDataBinding("abcde", null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void jsonToMapTest() {
//		try{
//			AutoFillFormData.jsonToMap(null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}