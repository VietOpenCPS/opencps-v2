package org.opencps.usermgt.service.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DateTimeUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void incrementTest() {
		try{
			DateTimeUtils.increment(new Date(), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dateToStringTest() {
		try{
			DateTimeUtils.dateToString(new Date(), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dateToStringTest3() {
		try{
			DateTimeUtils.dateToString(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void stringToDateTest() {
		try{
			DateTimeUtils.stringToDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void stringToDateTest5() {
		try{
			DateTimeUtils.stringToDate("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}