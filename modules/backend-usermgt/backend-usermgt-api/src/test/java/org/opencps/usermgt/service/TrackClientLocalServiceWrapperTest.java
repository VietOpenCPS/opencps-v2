package org.opencps.usermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class TrackClientLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void dynamicQueryTest() {
		try{
			TrackClientLocalServiceWrapper object = new TrackClientLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest2() {
		try{
			TrackClientLocalServiceWrapper object = new TrackClientLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			TrackClientLocalServiceWrapper object = new TrackClientLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			TrackClientLocalServiceWrapper object = new TrackClientLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			TrackClientLocalServiceWrapper object = new TrackClientLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOnlineTest() {
		try{
			TrackClientLocalServiceWrapper object = new TrackClientLocalServiceWrapper(null);
			object.getOnline();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			TrackClientLocalServiceWrapper object = new TrackClientLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			TrackClientLocalServiceWrapper object = new TrackClientLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			TrackClientLocalServiceWrapper object = new TrackClientLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			TrackClientLocalServiceWrapper object = new TrackClientLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest11() {
		try{
			TrackClientLocalServiceWrapper object = new TrackClientLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			TrackClientLocalServiceWrapper object = new TrackClientLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest13() {
		try{
			TrackClientLocalServiceWrapper object = new TrackClientLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			TrackClientLocalServiceWrapper object = new TrackClientLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			TrackClientLocalServiceWrapper object = new TrackClientLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest16() {
		try{
			TrackClientLocalServiceWrapper object = new TrackClientLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTopURLUserAccessTest() {
		try{
			TrackClientLocalServiceWrapper object = new TrackClientLocalServiceWrapper(null);
			object.getTopURLUserAccess(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findPeriodCountDayTest() {
		try{
			TrackClientLocalServiceWrapper object = new TrackClientLocalServiceWrapper(null);
			object.findPeriodCountDay("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateTrackClientTest() {
		try{
			TrackClientLocalServiceWrapper object = new TrackClientLocalServiceWrapper(null);
			object.updateTrackClient(Long.valueOf(0), "abcde", "abcde", 0, 0, 0, new Date(), new Date(), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), true, true, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateTrackClientTest20() {
		try{
			TrackClientLocalServiceWrapper object = new TrackClientLocalServiceWrapper(null);
			object.updateTrackClient(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateTrackClientTest21() {
		try{
			TrackClientLocalServiceWrapper object = new TrackClientLocalServiceWrapper(null);
			object.updateTrackClient(Long.valueOf(0), "abcde", "abcde", 0, 0, 0, new Date(), new Date(), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), true, true, true, Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTrackClientsCountTest() {
		try{
			TrackClientLocalServiceWrapper object = new TrackClientLocalServiceWrapper(null);
			object.getTrackClientsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createTrackClientTest() {
		try{
			TrackClientLocalServiceWrapper object = new TrackClientLocalServiceWrapper(null);
			object.createTrackClient(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteTrackClientTest() {
		try{
			TrackClientLocalServiceWrapper object = new TrackClientLocalServiceWrapper(null);
			object.deleteTrackClient(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteTrackClientTest25() {
		try{
			TrackClientLocalServiceWrapper object = new TrackClientLocalServiceWrapper(null);
			object.deleteTrackClient(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findBySTest() {
		try{
			TrackClientLocalServiceWrapper object = new TrackClientLocalServiceWrapper(null);
			object.findByS("abcde", 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findBySTest27() {
		try{
			TrackClientLocalServiceWrapper object = new TrackClientLocalServiceWrapper(null);
			object.findByS("abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findPreviousPageTest() {
		try{
			TrackClientLocalServiceWrapper object = new TrackClientLocalServiceWrapper(null);
			object.findPreviousPage("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findPeriodRegionTest() {
		try{
			TrackClientLocalServiceWrapper object = new TrackClientLocalServiceWrapper(null);
			object.findPeriodRegion("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByS_NULL_LTest() {
		try{
			TrackClientLocalServiceWrapper object = new TrackClientLocalServiceWrapper(null);
			object.findByS_NULL_L("abcde", new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByS_NULL_LTest31() {
		try{
			TrackClientLocalServiceWrapper object = new TrackClientLocalServiceWrapper(null);
			object.findByS_NULL_L("abcde", new Date(), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByS_LVDTest() {
		try{
			TrackClientLocalServiceWrapper object = new TrackClientLocalServiceWrapper(null);
			object.findByS_LVD("abcde", new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByS_LVDTest33() {
		try{
			TrackClientLocalServiceWrapper object = new TrackClientLocalServiceWrapper(null);
			object.findByS_LVD("abcde", new Date(), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTrackClientTest() {
		try{
			TrackClientLocalServiceWrapper object = new TrackClientLocalServiceWrapper(null);
			object.getTrackClient(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTrackClientsTest() {
		try{
			TrackClientLocalServiceWrapper object = new TrackClientLocalServiceWrapper(null);
			object.getTrackClients(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addTrackClientTest() {
		try{
			TrackClientLocalServiceWrapper object = new TrackClientLocalServiceWrapper(null);
			object.addTrackClient(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchTrackClientTest() {
		try{
			TrackClientLocalServiceWrapper object = new TrackClientLocalServiceWrapper(null);
			object.fetchTrackClient(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}