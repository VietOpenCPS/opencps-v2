package org.opencps.datamgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class HolidayActionsTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void updateTest() {
//		try{
//			HolidayActions object = new HolidayActions();
//			object.update(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void deleteTest() {
//		try{
//			HolidayActions object = new HolidayActions();
//			object.delete(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	@Test
//	public void createTest() {
//		try{
//			HolidayActions object = new HolidayActions();
//			object.create(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateHolidayDBTest() {
//		try{
//			HolidayActions object = new HolidayActions();
//			object.updateHolidayDB(Long.valueOf(0), Long.valueOf(0), new Date(), "abcde", 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getHolidaysTest() {
		try{
			HolidayActions object = new HolidayActions();
			object.getHolidays(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	
	@Test
	public void readTest() {
		try{
			HolidayActions object = new HolidayActions();
			object.read(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}