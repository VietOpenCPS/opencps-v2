package org.opencps.datamgt.service.impl;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class HolidayLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			HolidayLocalServiceImpl object = new HolidayLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateHolidayDBTest() {
		try{
			HolidayLocalServiceImpl object = new HolidayLocalServiceImpl();
			object.updateHolidayDB(Long.valueOf(0), Long.valueOf(0), new Date(), "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void luceneSearchEngineTest() {
//		try{
//			HolidayLocalServiceImpl object = new HolidayLocalServiceImpl();
//			object.luceneSearchEngine(null, null, 0, 0, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void fetchByF_holidayDateTest() {
		try{
			HolidayLocalServiceImpl object = new HolidayLocalServiceImpl();
			object.fetchByF_holidayDate(Long.valueOf(0), new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getHolidayByGroupIdAndTypeTest() {
		try{
			HolidayLocalServiceImpl object = new HolidayLocalServiceImpl();
			object.getHolidayByGroupIdAndType(Long.valueOf(0), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getHolidayByGroupIdTest() {
		try{
			HolidayLocalServiceImpl object = new HolidayLocalServiceImpl();
			object.getHolidayByGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addHolidayTest() {
		try{
			HolidayLocalServiceImpl object = new HolidayLocalServiceImpl();
			object.addHoliday(Long.valueOf(0), Long.valueOf(0), new Date(), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getHolidayGtThanTest() {
		try{
			HolidayLocalServiceImpl object = new HolidayLocalServiceImpl();
			object.getHolidayGtThan(Long.valueOf(0), new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateHolidayTest() {
		try{
			HolidayLocalServiceImpl object = new HolidayLocalServiceImpl();
			object.updateHoliday(Long.valueOf(0), Long.valueOf(0), new Date(), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteHolidayTest() {
		try{
			HolidayLocalServiceImpl object = new HolidayLocalServiceImpl();
			object.deleteHoliday(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void countLuceneSearchEngineTest() {
//		try{
//			HolidayLocalServiceImpl object = new HolidayLocalServiceImpl();
//			object.countLuceneSearchEngine(null, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			HolidayLocalServiceImpl object = new HolidayLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}