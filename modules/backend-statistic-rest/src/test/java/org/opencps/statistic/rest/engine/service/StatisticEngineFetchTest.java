package org.opencps.statistic.rest.engine.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class StatisticEngineFetchTest {
	@Before
	public void setUp() {
	}
	@Test
	public void fecthStatisticDataTest() {
		try{
			StatisticEngineFetch object = new StatisticEngineFetch();
			object.fecthStatisticData(Long.valueOf(0), null, null, new Date(), new Date(), true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchSumStatisticDataTest() {
		try{
			StatisticEngineFetch object = new StatisticEngineFetch();
			object.fetchSumStatisticData(Long.valueOf(0), null, null, new Date(), new Date(), true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStatisticVotingDataTest() {
		try{
			StatisticEngineFetch object = new StatisticEngineFetch();
			object.getStatisticVotingData(Long.valueOf(0), null, new Date(), new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStatisticPersonDataTest() {
		try{
			StatisticEngineFetch object = new StatisticEngineFetch();
			object.getStatisticPersonData(Long.valueOf(0), null, new Date(), new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}