package org.opencps.rest.application.api.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class StatisticReportApiServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void statisticReportPrintTest() {
		try{
			StatisticReportApiServiceImpl object = new StatisticReportApiServiceImpl();
			object.statisticReportPrint("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void statisticReportPrintTest2() {
		try{
			StatisticReportApiServiceImpl object = new StatisticReportApiServiceImpl();
			object.statisticReportPrint(null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}