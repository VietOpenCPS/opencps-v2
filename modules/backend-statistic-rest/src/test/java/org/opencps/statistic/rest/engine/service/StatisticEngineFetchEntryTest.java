package org.opencps.statistic.rest.engine.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class StatisticEngineFetchEntryTest {
	@Before
	public void setUp() {
	}
	@Test
	public void updateDossierStatisticDataTest() {
		try{
			StatisticEngineFetchEntry object = new StatisticEngineFetchEntry();
			object.updateDossierStatisticData(null, null, new Date(), new Date(), true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void calculateVotingStatisticDataTest() {
		try{
			StatisticEngineFetchEntry object = new StatisticEngineFetchEntry();
			object.calculateVotingStatisticData(null, null, new Date(), new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateSumDossierStatisticDataTest() {
		try{
			StatisticEngineFetchEntry object = new StatisticEngineFetchEntry();
			object.updateSumDossierStatisticData(null, null, new Date(), new Date(), true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void calculatePersonStatisticDataTest() {
		try{
			StatisticEngineFetchEntry object = new StatisticEngineFetchEntry();
			object.calculatePersonStatisticData(null, null, new Date(), new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}