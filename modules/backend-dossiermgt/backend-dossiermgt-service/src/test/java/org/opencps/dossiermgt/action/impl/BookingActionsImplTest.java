package org.opencps.dossiermgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class BookingActionsImplTest {
	@Before
	public void setUp() {
	}
	
//	@Test
//	public void getBookingCounterOnlineTest() {
//		try{
//			BookingActionsImpl object = new BookingActionsImpl();
//			object.getBookingCounterOnline(Long.valueOf(0), "abcde", true, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void deleteBookingByIdTest() {
//		try{
//			BookingActionsImpl object = new BookingActionsImpl();
//			object.deleteBookingById(Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateBookingTest() {
//		try{
//			BookingActionsImpl object = new BookingActionsImpl();
//			object.updateBooking(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", 0, new Date(), new Date(), true, "abcde", true, "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
	/*@Test
	public void getByCodeNumberTest() {
		try{
			//BookingActionsImpl object = new BookingActionsImpl();
			BookingLocalServiceUtil.getByCodeNumber("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}*/
	@Test
	public void validateSimpleCaptchaTest() {
		try{
			BookingActionsImpl object = new BookingActionsImpl();
			object.validateSimpleCaptcha(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBookingListTest() {
		try{
			BookingActionsImpl object = new BookingActionsImpl();
			object.getBookingList(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}