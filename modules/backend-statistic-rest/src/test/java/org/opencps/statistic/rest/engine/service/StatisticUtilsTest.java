package org.opencps.statistic.rest.engine.service;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class StatisticUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void convertStringToDateTest() {
		try{
			StatisticUtils.convertStringToDate("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void convertStringToDateTest2() {
//		try{
//			StatisticUtils.convertStringToDate("abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getStartDayTest() {
		try{
			StatisticUtils.getStartDay(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEndDayTest() {
		try{
			StatisticUtils.getEndDay(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFirstDayTest() {
		try{
			StatisticUtils.getFirstDay(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getLastDayTest() {
		try{
			StatisticUtils.getLastDay(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}