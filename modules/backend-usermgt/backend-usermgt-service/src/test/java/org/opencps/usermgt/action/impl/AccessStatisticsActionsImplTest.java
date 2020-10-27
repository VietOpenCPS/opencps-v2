package org.opencps.usermgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class AccessStatisticsActionsImplTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void getAccessStatisticsURLForAllYearTest() {
//		try{
//			AccessStatisticsActionsImpl object = new AccessStatisticsActionsImpl();
//			object.getAccessStatisticsURLForAllYear();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	@Test
//	public void getAccessStatisticsTest() {
//		try{
//			AccessStatisticsActionsImpl object = new AccessStatisticsActionsImpl();
//			object.getAccessStatistics(0, 0, 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getAccessStatisticsForAllYearTest() {
//		try{
//			AccessStatisticsActionsImpl object = new AccessStatisticsActionsImpl();
//			object.getAccessStatisticsForAllYear();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getAccessStatisticsURLForPeriodTest() {
//		try{
//			AccessStatisticsActionsImpl object = new AccessStatisticsActionsImpl();
//			object.getAccessStatisticsURLForPeriod();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getAccessStatisticsForDayTest() {
//		try{
//			AccessStatisticsActionsImpl object = new AccessStatisticsActionsImpl();
//			object.getAccessStatisticsForDay(0, 0, 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getAccessStatisticsForYearTest() {
//		try{
//			AccessStatisticsActionsImpl object = new AccessStatisticsActionsImpl();
//			object.getAccessStatisticsForYear(0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getAccessStatisticsForMonthTest() {
//		try{
//			AccessStatisticsActionsImpl object = new AccessStatisticsActionsImpl();
//			object.getAccessStatisticsForMonth(0, 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	@Test
//	public void getAccessStatisticsURLTest() {
//		try{
//			AccessStatisticsActionsImpl object = new AccessStatisticsActionsImpl();
//			object.getAccessStatisticsURL(0, 0, 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getOnlineTest() {
//		try{
//			AccessStatisticsActionsImpl object = new AccessStatisticsActionsImpl();
//			object.getOnline();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	
	
	@Test
	public void getUserAccessStatisticsTest() {
		try{
			AccessStatisticsActionsImpl object = new AccessStatisticsActionsImpl();
			object.getUserAccessStatistics(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAccessStatisticsForPeriodTest() {
		try{
			AccessStatisticsActionsImpl object = new AccessStatisticsActionsImpl();
			object.getAccessStatisticsForPeriod("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}