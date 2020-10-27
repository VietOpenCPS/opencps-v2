package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class BookingLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void dynamicQueryTest() {
		try{
			BookingLocalServiceWrapper object = new BookingLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest2() {
		try{
			BookingLocalServiceWrapper object = new BookingLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			BookingLocalServiceWrapper object = new BookingLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			BookingLocalServiceWrapper object = new BookingLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addBookingTest() {
		try{
			BookingLocalServiceWrapper object = new BookingLocalServiceWrapper(null);
			object.addBooking(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			BookingLocalServiceWrapper object = new BookingLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBookingTest() {
		try{
			BookingLocalServiceWrapper object = new BookingLocalServiceWrapper(null);
			object.getBooking(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			BookingLocalServiceWrapper object = new BookingLocalServiceWrapper(null);
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchBookingTest() {
		try{
			BookingLocalServiceWrapper object = new BookingLocalServiceWrapper(null);
			object.fetchBooking(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateBookingTest() {
		try{
			BookingLocalServiceWrapper object = new BookingLocalServiceWrapper(null);
			object.updateBooking(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", 0, new Date(), new Date(), true, "abcde", true, "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateBookingTest11() {
		try{
			BookingLocalServiceWrapper object = new BookingLocalServiceWrapper(null);
			object.updateBooking(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByCodeNumberTest() {
		try{
			BookingLocalServiceWrapper object = new BookingLocalServiceWrapper(null);
			object.getByCodeNumber("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createBookingTest() {
		try{
			BookingLocalServiceWrapper object = new BookingLocalServiceWrapper(null);
			object.createBooking(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteBookingTest() {
		try{
			BookingLocalServiceWrapper object = new BookingLocalServiceWrapper(null);
			object.deleteBooking(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteBookingTest15() {
		try{
			BookingLocalServiceWrapper object = new BookingLocalServiceWrapper(null);
			object.deleteBooking(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneTest() {
		try{
			BookingLocalServiceWrapper object = new BookingLocalServiceWrapper(null);
			object.countLucene(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByClassName_PKTest() {
		try{
			BookingLocalServiceWrapper object = new BookingLocalServiceWrapper(null);
			object.getByClassName_PK(Long.valueOf(0), "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByGID_DATETest() {
		try{
			BookingLocalServiceWrapper object = new BookingLocalServiceWrapper(null);
			object.getByGID_DATE(Long.valueOf(0), "abcde", true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBookingsCountTest() {
		try{
			BookingLocalServiceWrapper object = new BookingLocalServiceWrapper(null);
			object.getBookingsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBookingsTest() {
		try{
			BookingLocalServiceWrapper object = new BookingLocalServiceWrapper(null);
			object.getBookings(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBookingByUuidAndGroupIdTest() {
		try{
			BookingLocalServiceWrapper object = new BookingLocalServiceWrapper(null);
			object.getBookingByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBookingsByUuidAndCompanyIdTest() {
		try{
			BookingLocalServiceWrapper object = new BookingLocalServiceWrapper(null);
			object.getBookingsByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBookingsByUuidAndCompanyIdTest23() {
		try{
			BookingLocalServiceWrapper object = new BookingLocalServiceWrapper(null);
			object.getBookingsByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchBookingByUuidAndGroupIdTest() {
		try{
			BookingLocalServiceWrapper object = new BookingLocalServiceWrapper(null);
			object.fetchBookingByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			BookingLocalServiceWrapper object = new BookingLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			BookingLocalServiceWrapper object = new BookingLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			BookingLocalServiceWrapper object = new BookingLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			BookingLocalServiceWrapper object = new BookingLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			BookingLocalServiceWrapper object = new BookingLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest30() {
		try{
			BookingLocalServiceWrapper object = new BookingLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			BookingLocalServiceWrapper object = new BookingLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest32() {
		try{
			BookingLocalServiceWrapper object = new BookingLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			BookingLocalServiceWrapper object = new BookingLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			BookingLocalServiceWrapper object = new BookingLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest35() {
		try{
			BookingLocalServiceWrapper object = new BookingLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}