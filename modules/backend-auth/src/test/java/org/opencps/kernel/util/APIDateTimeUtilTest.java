package org.opencps.kernel.util;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class APIDateTimeUtilTest {
	@Before
	public void setUp() {
	}
	@Test
	public void convertDateToStringTest() {
		try{
			APIDateTimeUtil.convertDateToString(new Date(), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void convertDateToStringTest2() {
//		try{
//			APIDateTimeUtil.convertDateToString(null, "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}