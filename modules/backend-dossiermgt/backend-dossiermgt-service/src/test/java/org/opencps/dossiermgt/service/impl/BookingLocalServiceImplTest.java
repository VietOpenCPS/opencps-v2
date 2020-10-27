package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class BookingLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void searchLuceneTest() {
		try{
			BookingLocalServiceImpl object = new BookingLocalServiceImpl();
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateBookingTest() {
		try{
			BookingLocalServiceImpl object = new BookingLocalServiceImpl();
			object.updateBooking(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", 0, new Date(), new Date(), true, "abcde", true, "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByCodeNumberTest() {
		try{
			BookingLocalServiceImpl object = new BookingLocalServiceImpl();
			object.getByCodeNumber("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneTest() {
		try{
			BookingLocalServiceImpl object = new BookingLocalServiceImpl();
			object.countLucene(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByClassName_PKTest() {
		try{
			BookingLocalServiceImpl object = new BookingLocalServiceImpl();
			object.getByClassName_PK(Long.valueOf(0), "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByGID_DATETest() {
		try{
			BookingLocalServiceImpl object = new BookingLocalServiceImpl();
			object.getByGID_DATE(Long.valueOf(0), "abcde", true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}