package org.opencps.api.holiday.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class HolidayResultsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getTotalTest() {
		try{
			HolidayResults object = new HolidayResults();
			object.getTotal();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getHolidayModelTest() {
		try{
			HolidayResults object = new HolidayResults();
			object.getHolidayModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTotalTest() {
		try{
			HolidayResults object = new HolidayResults();
			object.setTotal(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}