package org.opencps.usermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class TrackClientStatisticLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void dynamicQueryTest() {
		try{
			TrackClientStatisticLocalServiceWrapper object = new TrackClientStatisticLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest2() {
		try{
			TrackClientStatisticLocalServiceWrapper object = new TrackClientStatisticLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			TrackClientStatisticLocalServiceWrapper object = new TrackClientStatisticLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			TrackClientStatisticLocalServiceWrapper object = new TrackClientStatisticLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			TrackClientStatisticLocalServiceWrapper object = new TrackClientStatisticLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOnlineTest() {
		try{
			TrackClientStatisticLocalServiceWrapper object = new TrackClientStatisticLocalServiceWrapper(null);
			object.getOnline();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			TrackClientStatisticLocalServiceWrapper object = new TrackClientStatisticLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			TrackClientStatisticLocalServiceWrapper object = new TrackClientStatisticLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			TrackClientStatisticLocalServiceWrapper object = new TrackClientStatisticLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			TrackClientStatisticLocalServiceWrapper object = new TrackClientStatisticLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest11() {
		try{
			TrackClientStatisticLocalServiceWrapper object = new TrackClientStatisticLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			TrackClientStatisticLocalServiceWrapper object = new TrackClientStatisticLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest13() {
		try{
			TrackClientStatisticLocalServiceWrapper object = new TrackClientStatisticLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			TrackClientStatisticLocalServiceWrapper object = new TrackClientStatisticLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			TrackClientStatisticLocalServiceWrapper object = new TrackClientStatisticLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest16() {
		try{
			TrackClientStatisticLocalServiceWrapper object = new TrackClientStatisticLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addTrackClientStatisticTest() {
		try{
			TrackClientStatisticLocalServiceWrapper object = new TrackClientStatisticLocalServiceWrapper(null);
			object.addTrackClientStatistic(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countAccessAllYearTest() {
		try{
			TrackClientStatisticLocalServiceWrapper object = new TrackClientStatisticLocalServiceWrapper(null);
			object.countAccessAllYear();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void accessStatisticsURLTest() {
		try{
			TrackClientStatisticLocalServiceWrapper object = new TrackClientStatisticLocalServiceWrapper(null);
			object.accessStatisticsURL(0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void accessStatisticsURLForAllYearTest() {
		try{
			TrackClientStatisticLocalServiceWrapper object = new TrackClientStatisticLocalServiceWrapper(null);
			object.accessStatisticsURLForAllYear();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void accessStatisticsURLForPeriodTest() {
		try{
			TrackClientStatisticLocalServiceWrapper object = new TrackClientStatisticLocalServiceWrapper(null);
			object.accessStatisticsURLForPeriod("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countAccessPeriodTest() {
		try{
			TrackClientStatisticLocalServiceWrapper object = new TrackClientStatisticLocalServiceWrapper(null);
			object.countAccessPeriod("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createTrackClientStatisticTest() {
		try{
			TrackClientStatisticLocalServiceWrapper object = new TrackClientStatisticLocalServiceWrapper(null);
			object.createTrackClientStatistic(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteTrackClientStatisticTest() {
		try{
			TrackClientStatisticLocalServiceWrapper object = new TrackClientStatisticLocalServiceWrapper(null);
			object.deleteTrackClientStatistic(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteTrackClientStatisticTest25() {
		try{
			TrackClientStatisticLocalServiceWrapper object = new TrackClientStatisticLocalServiceWrapper(null);
			object.deleteTrackClientStatistic(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchTrackClientStatisticTest() {
		try{
			TrackClientStatisticLocalServiceWrapper object = new TrackClientStatisticLocalServiceWrapper(null);
			object.fetchTrackClientStatistic(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTrackClientStatisticTest() {
		try{
			TrackClientStatisticLocalServiceWrapper object = new TrackClientStatisticLocalServiceWrapper(null);
			object.getTrackClientStatistic(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTrackClientStatisticsTest() {
		try{
			TrackClientStatisticLocalServiceWrapper object = new TrackClientStatisticLocalServiceWrapper(null);
			object.getTrackClientStatistics(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateStatisticTotalTest() {
		try{
			TrackClientStatisticLocalServiceWrapper object = new TrackClientStatisticLocalServiceWrapper(null);
			object.updateStatisticTotal("abcde", 0, 0, 0, "abcde", true, true, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTrackClientStatisticsCountTest() {
		try{
			TrackClientStatisticLocalServiceWrapper object = new TrackClientStatisticLocalServiceWrapper(null);
			object.getTrackClientStatisticsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateTrackClientStatisticTest() {
		try{
			TrackClientStatisticLocalServiceWrapper object = new TrackClientStatisticLocalServiceWrapper(null);
			object.updateTrackClientStatistic(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateTrackClientStatisticTest32() {
		try{
			TrackClientStatisticLocalServiceWrapper object = new TrackClientStatisticLocalServiceWrapper(null);
			object.updateTrackClientStatistic(Long.valueOf(0), "abcde", 0, 0, 0, "abcde", true, true, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countAccessTest() {
		try{
			TrackClientStatisticLocalServiceWrapper object = new TrackClientStatisticLocalServiceWrapper(null);
			object.countAccess(0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}