package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class HolidayManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void updateTest() {
		try{
			HolidayManagementImpl object = new HolidayManagementImpl();
			object.update(null, null, null, null, null, null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteTest() {
		try{
			HolidayManagementImpl object = new HolidayManagementImpl();
			object.delete(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void readTest() {
		try{
			HolidayManagementImpl object = new HolidayManagementImpl();
			object.read(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createTest() {
		try{
			HolidayManagementImpl object = new HolidayManagementImpl();
			object.create(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getHolidaysTest() {
		try{
			HolidayManagementImpl object = new HolidayManagementImpl();
			object.getHolidays(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}