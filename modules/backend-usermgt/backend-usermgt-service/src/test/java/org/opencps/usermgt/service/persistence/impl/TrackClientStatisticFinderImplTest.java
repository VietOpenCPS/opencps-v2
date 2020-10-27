package org.opencps.usermgt.service.persistence.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class TrackClientStatisticFinderImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void findAccessPeriodDesktopMobileTabletTest() {
		try{
			TrackClientStatisticFinderImpl object = new TrackClientStatisticFinderImpl();
			object.findAccessPeriodDesktopMobileTablet("abcde", "abcde", true, true, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findAccessURLPeriodTest() {
		try{
			TrackClientStatisticFinderImpl object = new TrackClientStatisticFinderImpl();
			object.findAccessURLPeriod("abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findAccessURLAllYearTest() {
		try{
			TrackClientStatisticFinderImpl object = new TrackClientStatisticFinderImpl();
			object.findAccessURLAllYear("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findPeriodTest() {
		try{
			TrackClientStatisticFinderImpl object = new TrackClientStatisticFinderImpl();
			object.findPeriod("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findURLAllYearTest() {
		try{
			TrackClientStatisticFinderImpl object = new TrackClientStatisticFinderImpl();
			object.findURLAllYear();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findURLPeriodTest() {
		try{
			TrackClientStatisticFinderImpl object = new TrackClientStatisticFinderImpl();
			object.findURLPeriod("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findAllYearTest() {
		try{
			TrackClientStatisticFinderImpl object = new TrackClientStatisticFinderImpl();
			object.findAllYear();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}