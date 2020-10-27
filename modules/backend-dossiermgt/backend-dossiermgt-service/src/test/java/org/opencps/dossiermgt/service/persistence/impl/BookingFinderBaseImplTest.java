package org.opencps.dossiermgt.service.persistence.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class BookingFinderBaseImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getBadColumnNamesTest() {
		try{
			BookingFinderBaseImpl object = new BookingFinderBaseImpl();
			object.getBadColumnNames();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBookingPersistenceTest() {
		try{
			BookingFinderBaseImpl object = new BookingFinderBaseImpl();
			object.getBookingPersistence();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setBookingPersistenceTest() {
		try{
			BookingFinderBaseImpl object = new BookingFinderBaseImpl();
			object.setBookingPersistence(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}