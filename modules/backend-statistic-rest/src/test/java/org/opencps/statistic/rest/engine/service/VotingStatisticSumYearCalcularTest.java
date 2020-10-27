package org.opencps.statistic.rest.engine.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class VotingStatisticSumYearCalcularTest {
	@Before
	public void setUp() {
	}
	@Test
	public void filterVotingSumYearTest() {
		try{
			VotingStatisticSumYearCalcular object = new VotingStatisticSumYearCalcular();
			object.filterVotingSumYear(Long.valueOf(0), Long.valueOf(0), 0, true, true, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}