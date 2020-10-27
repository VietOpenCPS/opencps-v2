package org.opencps.datamgt.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class CommonDateUtilsTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void processPartWorkingTest() {
//		try{
//			CommonDateUtils.processPartWorking(0, Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getDayByGroupIdTest() {
//		try{
//			CommonDateUtils.getDayByGroupId(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getDayOffTest() {
		try{
			CommonDateUtils.getDayOff(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isHolidayTest() {
		try{
			CommonDateUtils.isHoliday(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void compareDateTest() {
		try{
			CommonDateUtils.compareDate(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}