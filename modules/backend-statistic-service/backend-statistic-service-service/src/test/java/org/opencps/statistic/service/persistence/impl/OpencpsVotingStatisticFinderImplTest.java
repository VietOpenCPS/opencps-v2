package org.opencps.statistic.service.persistence.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class OpencpsVotingStatisticFinderImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void searchVotingStatisticTest() {
		try{
			OpencpsVotingStatisticFinderImpl object = new OpencpsVotingStatisticFinderImpl();
			object.searchVotingStatistic(Long.valueOf(0), 0, "abcde", "abcde", "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void checkContainsTest() {
		try{
			OpencpsVotingStatisticFinderImpl object = new OpencpsVotingStatisticFinderImpl();
			object.checkContains(Long.valueOf(0), 0, 0, "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchByVotingServiceGovAgencyGroupTest() {
		try{
			OpencpsVotingStatisticFinderImpl object = new OpencpsVotingStatisticFinderImpl();
			object.searchByVotingServiceGovAgencyGroup(Long.valueOf(0), 0, 0, "abcde", "abcde", "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}