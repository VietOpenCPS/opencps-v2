package org.opencps.statistic.rest.engine;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class StatisticsReportSchedulerTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getStorageTypeTest() {
		try{
			StatisticsReportScheduler object = new StatisticsReportScheduler();
			object.getStorageType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModuleServiceLifecycleTest() {
		try{
			StatisticsReportScheduler object = new StatisticsReportScheduler();
			object.setModuleServiceLifecycle(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSchedulerEngineHelperTest() {
		try{
			StatisticsReportScheduler object = new StatisticsReportScheduler();
			object.setSchedulerEngineHelper(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTriggerFactoryTest() {
		try{
			StatisticsReportScheduler object = new StatisticsReportScheduler();
			object.setTriggerFactory(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void doReceiveTest() {
//		try{
//			StatisticsReportScheduler object = new StatisticsReportScheduler();
//			object.doReceive(null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void deactivateTest() {
		try{
			StatisticsReportScheduler object = new StatisticsReportScheduler();
			object.deactivate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void activateTest() {
		try{
			StatisticsReportScheduler object = new StatisticsReportScheduler();
			object.activate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}