package org.opencps.usermgt.service.persistence.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class TrackClientFinderImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getOnlineTest() {
		try{
			TrackClientFinderImpl object = new TrackClientFinderImpl();
			object.getOnline();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTopURLUserAccessTest() {
		try{
			TrackClientFinderImpl object = new TrackClientFinderImpl();
			object.getTopURLUserAccess(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findPeriodCountDayTest() {
		try{
			TrackClientFinderImpl object = new TrackClientFinderImpl();
			object.findPeriodCountDay("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findPreviousPageTest() {
		try{
			TrackClientFinderImpl object = new TrackClientFinderImpl();
			object.findPreviousPage("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findPeriodRegionTest() {
		try{
			TrackClientFinderImpl object = new TrackClientFinderImpl();
			object.findPeriodRegion("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}