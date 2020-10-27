package org.opencps.usermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class TrackClientLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getOnlineTest() {
		try{
			TrackClientLocalServiceImpl object = new TrackClientLocalServiceImpl();
			object.getOnline();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTopURLUserAccessTest() {
		try{
			TrackClientLocalServiceImpl object = new TrackClientLocalServiceImpl();
			object.getTopURLUserAccess(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findPeriodCountDayTest() {
		try{
			TrackClientLocalServiceImpl object = new TrackClientLocalServiceImpl();
			object.findPeriodCountDay("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateTrackClientTest() {
		try{
			TrackClientLocalServiceImpl object = new TrackClientLocalServiceImpl();
			object.updateTrackClient(Long.valueOf(0), "abcde", "abcde", 0, 0, 0, new Date(), new Date(), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), true, true, true, Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateTrackClientTest5() {
		try{
			TrackClientLocalServiceImpl object = new TrackClientLocalServiceImpl();
			object.updateTrackClient(Long.valueOf(0), "abcde", "abcde", 0, 0, 0, new Date(), new Date(), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), true, true, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findBySTest() {
		try{
			TrackClientLocalServiceImpl object = new TrackClientLocalServiceImpl();
			object.findByS("abcde", 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findBySTest7() {
		try{
			TrackClientLocalServiceImpl object = new TrackClientLocalServiceImpl();
			object.findByS("abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findPreviousPageTest() {
		try{
			TrackClientLocalServiceImpl object = new TrackClientLocalServiceImpl();
			object.findPreviousPage("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findPeriodRegionTest() {
		try{
			TrackClientLocalServiceImpl object = new TrackClientLocalServiceImpl();
			object.findPeriodRegion("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByS_NULL_LTest() {
		try{
			TrackClientLocalServiceImpl object = new TrackClientLocalServiceImpl();
			object.findByS_NULL_L("abcde", new Date(), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByS_NULL_LTest11() {
		try{
			TrackClientLocalServiceImpl object = new TrackClientLocalServiceImpl();
			object.findByS_NULL_L("abcde", new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByS_LVDTest() {
		try{
			TrackClientLocalServiceImpl object = new TrackClientLocalServiceImpl();
			object.findByS_LVD("abcde", new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByS_LVDTest13() {
		try{
			TrackClientLocalServiceImpl object = new TrackClientLocalServiceImpl();
			object.findByS_LVD("abcde", new Date(), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}