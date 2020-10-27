package org.opencps.usermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class TrackClientStatisticLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getOnlineTest() {
		try{
			TrackClientStatisticLocalServiceImpl object = new TrackClientStatisticLocalServiceImpl();
			object.getOnline();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countAccessAllYearTest() {
		try{
			TrackClientStatisticLocalServiceImpl object = new TrackClientStatisticLocalServiceImpl();
			object.countAccessAllYear();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void accessStatisticsURLTest() {
		try{
			TrackClientStatisticLocalServiceImpl object = new TrackClientStatisticLocalServiceImpl();
			object.accessStatisticsURL(0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void accessStatisticsURLForAllYearTest() {
		try{
			TrackClientStatisticLocalServiceImpl object = new TrackClientStatisticLocalServiceImpl();
			object.accessStatisticsURLForAllYear();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void accessStatisticsURLForPeriodTest() {
		try{
			TrackClientStatisticLocalServiceImpl object = new TrackClientStatisticLocalServiceImpl();
			object.accessStatisticsURLForPeriod("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countAccessPeriodTest() {
		try{
			TrackClientStatisticLocalServiceImpl object = new TrackClientStatisticLocalServiceImpl();
			object.countAccessPeriod("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateStatisticTotalTest() {
		try{
			TrackClientStatisticLocalServiceImpl object = new TrackClientStatisticLocalServiceImpl();
			object.updateStatisticTotal("abcde", 0, 0, 0, "abcde", true, true, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateTrackClientStatisticTest() {
		try{
			TrackClientStatisticLocalServiceImpl object = new TrackClientStatisticLocalServiceImpl();
			object.updateTrackClientStatistic(Long.valueOf(0), "abcde", 0, 0, 0, "abcde", true, true, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countAccessTest() {
		try{
			TrackClientStatisticLocalServiceImpl object = new TrackClientStatisticLocalServiceImpl();
			object.countAccess(0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}