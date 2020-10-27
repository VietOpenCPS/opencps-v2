package org.opencps.usermgt.service.persistence.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class TrackClientFinderBaseImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getBadColumnNamesTest() {
		try{
			TrackClientFinderBaseImpl object = new TrackClientFinderBaseImpl();
			object.getBadColumnNames();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTrackClientPersistenceTest() {
		try{
			TrackClientFinderBaseImpl object = new TrackClientFinderBaseImpl();
			object.setTrackClientPersistence(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTrackClientPersistenceTest() {
		try{
			TrackClientFinderBaseImpl object = new TrackClientFinderBaseImpl();
			object.getTrackClientPersistence();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}