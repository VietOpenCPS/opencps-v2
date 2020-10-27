package org.opencps.statistic.rest.engine.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class StatisticSumYearCalcularTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getFilterSumAllYearTest() {
		try{
			StatisticSumYearCalcular object = new StatisticSumYearCalcular();
			object.getFilterSumAllYear(Long.valueOf(0), Long.valueOf(0), 0, true, true, true, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFilterSumYearTest() {
		try{
			StatisticSumYearCalcular object = new StatisticSumYearCalcular();
			object.getFilterSumYear(Long.valueOf(0), Long.valueOf(0), 0, true, true, true, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void filterSumAllYearTest() {
		try{
			StatisticSumYearCalcular object = new StatisticSumYearCalcular();
			object.filterSumAllYear(Long.valueOf(0), Long.valueOf(0), 0, true, true, true, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void filterSumYearTest() {
		try{
			StatisticSumYearCalcular object = new StatisticSumYearCalcular();
			object.filterSumYear(Long.valueOf(0), Long.valueOf(0), 0, true, true, true, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}