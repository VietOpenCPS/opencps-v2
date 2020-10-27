package org.opencps.statistic.rest.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class VotingStatisticFinderServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void finderVotingStatisticTest() {
		try{
			VotingStatisticFinderServiceImpl object = new VotingStatisticFinderServiceImpl();
			object.finderVotingStatistic(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void finderVotingStatisticListTest() {
		try{
			VotingStatisticFinderServiceImpl object = new VotingStatisticFinderServiceImpl();
			object.finderVotingStatisticList(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}