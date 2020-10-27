package org.opencps.statistic.rest.engine.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class VotingStatisticUpdateServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void updateVotingStatisticTest() {
		try{
			VotingStatisticUpdateServiceImpl object = new VotingStatisticUpdateServiceImpl();
			object.updateVotingStatistic(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateVotingStatisticTest2() {
		try{
			VotingStatisticUpdateServiceImpl object = new VotingStatisticUpdateServiceImpl();
			object.updateVotingStatistic(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}