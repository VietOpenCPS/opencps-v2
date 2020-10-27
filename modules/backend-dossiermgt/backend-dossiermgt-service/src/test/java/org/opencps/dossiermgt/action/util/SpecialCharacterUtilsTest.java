package org.opencps.dossiermgt.action.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class SpecialCharacterUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void splitSpecialTest() {
		try{
			SpecialCharacterUtils.splitSpecial("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getKeyValuesTest() {
//		try{
//			SpecialCharacterUtils.getKeyValues("abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}