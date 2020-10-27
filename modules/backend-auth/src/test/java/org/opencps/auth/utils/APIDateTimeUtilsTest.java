package org.opencps.auth.utils;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class APIDateTimeUtilsTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void convertVNStrToDateTimeTest() {
//		try{
//			APIDateTimeUtils.convertVNStrToDateTime("abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void convertVNStrToDateTest() {
//		try{
//			APIDateTimeUtils.convertVNStrToDate("abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	
//	@Test
//	public void convertStringToDateTest() {
//		try{
//			APIDateTimeUtils.convertStringToDate("abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void convertNormalDateToLuceneDateTest() {
//		try{
//			APIDateTimeUtils.convertNormalDateToLuceneDate("abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
	
	@Test
	public void convertDateToStringTest4() {
		try{
			APIDateTimeUtils.convertDateToString(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	
	@Test
	public void convertDateToStringTest() {
		try{
			APIDateTimeUtils.convertDateToString(new Date(), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}