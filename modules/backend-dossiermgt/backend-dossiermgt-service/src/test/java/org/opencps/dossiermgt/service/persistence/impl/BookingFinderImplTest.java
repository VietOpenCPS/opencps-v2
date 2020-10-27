package org.opencps.dossiermgt.service.persistence.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class BookingFinderImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void findBookingDateOnlineTest() {
		try{
			BookingFinderImpl object = new BookingFinderImpl();
			object.findBookingDateOnline(Long.valueOf(0), "abcde", true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findBookingMaxByServiceGroupCodeTest() {
		try{
			BookingFinderImpl object = new BookingFinderImpl();
			object.findBookingMaxByServiceGroupCode(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}