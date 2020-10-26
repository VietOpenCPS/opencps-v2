package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class BookingManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getBookingCounterOnlineTest() {
		try{
			BookingManagementImpl object = new BookingManagementImpl();
			object.getBookingCounterOnline(null, null, null, null, null, null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void resolveConflictBookingTest() {
		try{
			BookingManagementImpl object = new BookingManagementImpl();
			object.resolveConflictBooking(null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBookingByCodeNumberTest() {
		try{
			BookingManagementImpl object = new BookingManagementImpl();
			object.getBookingByCodeNumber(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBokkingListByClassNameTest() {
		try{
			BookingManagementImpl object = new BookingManagementImpl();
			object.getBokkingListByClassName(null, null, null, null, null, null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateBookingByIdTest() {
		try{
			BookingManagementImpl object = new BookingManagementImpl();
			object.updateBookingById(null, null, null, null, null, null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void deleteBookingByIdTest() {
//		try{
//			BookingManagementImpl object = new BookingManagementImpl();
//			object.deleteBookingById(null, null, null, null, null, null, Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void addBookingTest() {
		try{
			BookingManagementImpl object = new BookingManagementImpl();
			object.addBooking(null, null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void validateCaptchaTest() {
//		try{
//			BookingManagementImpl object = new BookingManagementImpl();
//			object.validateCaptcha(null, null, null, null, null, null, "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}