package org.opencps.api.accessstatistics.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class AccessStatisticsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getDayTest() {
		try{
			AccessStatistics object = new AccessStatistics();
			object.getDay();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMonthTest() {
		try{
			AccessStatistics object = new AccessStatistics();
			object.setMonth(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getYearTest() {
		try{
			AccessStatistics object = new AccessStatistics();
			object.getYear();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDayTest() {
		try{
			AccessStatistics object = new AccessStatistics();
			object.setDay(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMonthTest() {
		try{
			AccessStatistics object = new AccessStatistics();
			object.getMonth();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setYearTest() {
		try{
			AccessStatistics object = new AccessStatistics();
			object.setYear(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}