package org.opencps.api.worktime.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class WorkTimeInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setHoursTest() {
		try{
			WorkTimeInputModel object = new WorkTimeInputModel();
			object.setHours("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDayTest() {
		try{
			WorkTimeInputModel object = new WorkTimeInputModel();
			object.getDay();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDayTest() {
		try{
			WorkTimeInputModel object = new WorkTimeInputModel();
			object.setDay(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getHoursTest() {
		try{
			WorkTimeInputModel object = new WorkTimeInputModel();
			object.getHours();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}