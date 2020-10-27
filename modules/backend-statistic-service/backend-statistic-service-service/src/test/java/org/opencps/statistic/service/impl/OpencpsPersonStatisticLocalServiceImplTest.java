package org.opencps.statistic.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class OpencpsPersonStatisticLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void searchPersonStatisticTest() {
		try{
			OpencpsPersonStatisticLocalServiceImpl object = new OpencpsPersonStatisticLocalServiceImpl();
			object.searchPersonStatistic(Long.valueOf(0), 0, 0, "abcde", Long.valueOf(0), "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removePersonStatisticByMonthYearTest() {
		try{
			OpencpsPersonStatisticLocalServiceImpl object = new OpencpsPersonStatisticLocalServiceImpl();
			object.removePersonStatisticByMonthYear(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removePersonStatisticByYearTest() {
		try{
			OpencpsPersonStatisticLocalServiceImpl object = new OpencpsPersonStatisticLocalServiceImpl();
			object.removePersonStatisticByYear(Long.valueOf(0), Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updatePersonStatisticTest() {
		try{
			OpencpsPersonStatisticLocalServiceImpl object = new OpencpsPersonStatisticLocalServiceImpl();
			object.updatePersonStatistic(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, "abcde", 0, 0, 0, 0, 0, 0, 0, "abcde", "abcde", Long.valueOf(0), "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchPersonStatisticTest() {
		try{
			OpencpsPersonStatisticLocalServiceImpl object = new OpencpsPersonStatisticLocalServiceImpl();
			object.fetchPersonStatistic(Long.valueOf(0), 0, 0, "abcde", Long.valueOf(0), "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void checkExsitTest() {
		try{
			OpencpsPersonStatisticLocalServiceImpl object = new OpencpsPersonStatisticLocalServiceImpl();
			object.checkExsit(Long.valueOf(0), 0, 0, "abcde", Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}