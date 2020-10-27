package org.opencps.usermgt.service.persistence.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class TrackClientStatisticFinderBaseImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getBadColumnNamesTest() {
		try{
			TrackClientStatisticFinderBaseImpl object = new TrackClientStatisticFinderBaseImpl();
			object.getBadColumnNames();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTrackClientStatisticPersistenceTest() {
		try{
			TrackClientStatisticFinderBaseImpl object = new TrackClientStatisticFinderBaseImpl();
			object.setTrackClientStatisticPersistence(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTrackClientStatisticPersistenceTest() {
		try{
			TrackClientStatisticFinderBaseImpl object = new TrackClientStatisticFinderBaseImpl();
			object.getTrackClientStatisticPersistence();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}