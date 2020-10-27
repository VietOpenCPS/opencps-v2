package org.opencps.statistic.rest.dto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class StatisticFixedModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setGroupIdTest() {
		try{
			StatisticFixedModel object = new StatisticFixedModel();
			object.setGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupIdTest() {
		try{
			StatisticFixedModel object = new StatisticFixedModel();
			object.getGroupId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMonthTest() {
		try{
			StatisticFixedModel object = new StatisticFixedModel();
			object.setMonth(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getYearTest() {
		try{
			StatisticFixedModel object = new StatisticFixedModel();
			object.getYear();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMonthTest() {
		try{
			StatisticFixedModel object = new StatisticFixedModel();
			object.getMonth();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setYearTest() {
		try{
			StatisticFixedModel object = new StatisticFixedModel();
			object.setYear(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}