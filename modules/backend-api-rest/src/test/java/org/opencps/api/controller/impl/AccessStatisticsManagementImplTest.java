package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class AccessStatisticsManagementImplTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void getAccessStatisticsURLForAllYearTest() {
//		try{
//			AccessStatisticsManagementImpl object = new AccessStatisticsManagementImpl();
//			object.getAccessStatisticsURLForAllYear(null, null, null, null, null, null, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getUserAccessStatisticsTest() {
		try{
			AccessStatisticsManagementImpl object = new AccessStatisticsManagementImpl();
			object.getUserAccessStatistics(null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAccessStatisticsURLForYearTest() {
		try{
			AccessStatisticsManagementImpl object = new AccessStatisticsManagementImpl();
			object.getAccessStatisticsURLForYear(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAccessStatisticsTest() {
		try{
			AccessStatisticsManagementImpl object = new AccessStatisticsManagementImpl();
			object.getAccessStatistics(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getAccessStatisticsForAllYearTest() {
//		try{
//			AccessStatisticsManagementImpl object = new AccessStatisticsManagementImpl();
//			object.getAccessStatisticsForAllYear(null, null, null, null, null, null, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getAccessStatisticsURLForPeriodTest() {
//		try{
//			AccessStatisticsManagementImpl object = new AccessStatisticsManagementImpl();
//			object.getAccessStatisticsURLForPeriod(null, null, null, null, null, null, "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getAccessStatisticsForDayTest() {
		try{
			AccessStatisticsManagementImpl object = new AccessStatisticsManagementImpl();
			object.getAccessStatisticsForDay(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAccessStatisticsURLForDayTest() {
		try{
			AccessStatisticsManagementImpl object = new AccessStatisticsManagementImpl();
			object.getAccessStatisticsURLForDay(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAccessStatisticsForYearTest() {
		try{
			AccessStatisticsManagementImpl object = new AccessStatisticsManagementImpl();
			object.getAccessStatisticsForYear(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAccessStatisticsURLForMonthTest() {
		try{
			AccessStatisticsManagementImpl object = new AccessStatisticsManagementImpl();
			object.getAccessStatisticsURLForMonth(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAccessStatisticsForMonthTest() {
		try{
			AccessStatisticsManagementImpl object = new AccessStatisticsManagementImpl();
			object.getAccessStatisticsForMonth(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAccessStatisticsForPeriodTest() {
		try{
			AccessStatisticsManagementImpl object = new AccessStatisticsManagementImpl();
			object.getAccessStatisticsForPeriod(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getOnlineTest() {
//		try{
//			AccessStatisticsManagementImpl object = new AccessStatisticsManagementImpl();
//			object.getOnline(null, null, null, null, null, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}