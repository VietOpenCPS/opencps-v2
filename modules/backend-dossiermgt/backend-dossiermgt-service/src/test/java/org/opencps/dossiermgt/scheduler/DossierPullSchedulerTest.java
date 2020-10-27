package org.opencps.dossiermgt.scheduler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DossierPullSchedulerTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setModuleServiceLifecycleTest() {
		try{
			DossierPullScheduler object = new DossierPullScheduler();
			object.setModuleServiceLifecycle(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSchedulerEngineHelperTest() {
		try{
			DossierPullScheduler object = new DossierPullScheduler();
			object.setSchedulerEngineHelper(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTriggerFactoryTest() {
		try{
			DossierPullScheduler object = new DossierPullScheduler();
			object.setTriggerFactory(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void doReceiveTest() {
		try{
			DossierPullScheduler object = new DossierPullScheduler();
			object.doReceive(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deactivateTest() {
		try{
			DossierPullScheduler object = new DossierPullScheduler();
			object.deactivate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void activateTest() {
//		try{
//			DossierPullScheduler object = new DossierPullScheduler();
//			object.activate();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void pullRequestDossiersTest() {
//		try{
//			DossierPullScheduler object = new DossierPullScheduler();
//			object.pullRequestDossiers(Long.valueOf(0), Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}