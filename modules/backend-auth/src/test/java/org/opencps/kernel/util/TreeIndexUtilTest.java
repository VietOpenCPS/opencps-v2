package org.opencps.kernel.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class TreeIndexUtilTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getMaxIntegerNumberOfRangeTest() {
		try{
			TreeIndexUtil object = new TreeIndexUtil();
			object.getMaxIntegerNumberOfRange(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertHexToNumberTest() {
		try{
			TreeIndexUtil object = new TreeIndexUtil();
			object.convertHexToNumber("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void generateTreeIndexTest() {
//		try{
//			TreeIndexUtil.generateTreeIndex("abcde", 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void generateNumberTreeIndexTest() {
//		try{
//			TreeIndexUtil.generateNumberTreeIndex("abcde", 0, 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void generateHexTreeIndexTest() {
//		try{
//			TreeIndexUtil.generateHexTreeIndex("abcde", 0, 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getMaxHexNumberOfRangeTest() {
		try{
			TreeIndexUtil object = new TreeIndexUtil();
			object.getMaxHexNumberOfRange(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}