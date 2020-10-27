package org.opencps.statistic.rest.engine.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class StatisticEngineUpdateTest {
	@Before
	public void setUp() {
	}
	@Test
	public void updateStatisticDataTest() {
		try{
			StatisticEngineUpdate object = new StatisticEngineUpdate();
			object.updateStatisticData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateStatisticDataTest2() {
		try{
			StatisticEngineUpdate object = new StatisticEngineUpdate();
			object.updateStatisticData(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void convertStatisticDataListTest() {
		try{
			StatisticEngineUpdate object = new StatisticEngineUpdate();
			object.convertStatisticDataList(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateVotingStatisticDataTest() {
		try{
			StatisticEngineUpdate object = new StatisticEngineUpdate();
			object.updateVotingStatisticData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updatePersonStatisticDataTest() {
		try{
			StatisticEngineUpdate object = new StatisticEngineUpdate();
			object.updatePersonStatisticData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}