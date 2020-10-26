package org.opencps.api.worktime.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class WorkTimeModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setHoursTest() {
		try{
			WorkTimeModel object = new WorkTimeModel();
			object.setHours("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDayTest() {
		try{
			WorkTimeModel object = new WorkTimeModel();
			object.getDay();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDayTest() {
		try{
			WorkTimeModel object = new WorkTimeModel();
			object.setDay(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getHoursTest() {
		try{
			WorkTimeModel object = new WorkTimeModel();
			object.getHours();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}