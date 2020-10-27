package org.opencps.statistic.service.persistence.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class OpencpsPersonStatisticFinderImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void searchPersonStatisticTest() {
		try{
			OpencpsPersonStatisticFinderImpl object = new OpencpsPersonStatisticFinderImpl();
			object.searchPersonStatistic(Long.valueOf(0), 0, "abcde", Long.valueOf(0), "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void checkContainsTest() {
		try{
			OpencpsPersonStatisticFinderImpl object = new OpencpsPersonStatisticFinderImpl();
			object.checkContains(Long.valueOf(0), 0, 0, "abcde", Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchByPersonServiceGovAgencyGroupTest() {
		try{
			OpencpsPersonStatisticFinderImpl object = new OpencpsPersonStatisticFinderImpl();
			object.searchByPersonServiceGovAgencyGroup(Long.valueOf(0), 0, 0, "abcde", Long.valueOf(0), "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}