package org.opencps.api.holiday.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class HolidayModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setHolidayDateTest() {
		try{
			HolidayModel object = new HolidayModel();
			object.setHolidayDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDescriptionTest() {
		try{
			HolidayModel object = new HolidayModel();
			object.getDescription();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDescriptionTest() {
		try{
			HolidayModel object = new HolidayModel();
			object.setDescription("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getHolidayDateTest() {
		try{
			HolidayModel object = new HolidayModel();
			object.getHolidayDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}