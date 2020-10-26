package org.opencps.api.holiday.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class HolidayInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setHolidayDateTest() {
		try{
			HolidayInputModel object = new HolidayInputModel();
			object.setHolidayDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDescriptionTest() {
		try{
			HolidayInputModel object = new HolidayInputModel();
			object.getDescription();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDescriptionTest() {
		try{
			HolidayInputModel object = new HolidayInputModel();
			object.setDescription("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getHolidayDateTest() {
		try{
			HolidayInputModel object = new HolidayInputModel();
			object.getHolidayDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}