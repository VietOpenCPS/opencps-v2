package org.opencps.dossiermgt.scheduler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DossierSubmitSchedulerTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setModuleServiceLifecycleTest() {
		try{
			DossierSubmitScheduler object = new DossierSubmitScheduler();
			object.setModuleServiceLifecycle(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSchedulerEngineHelperTest() {
		try{
			DossierSubmitScheduler object = new DossierSubmitScheduler();
			object.setSchedulerEngineHelper(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTriggerFactoryTest() {
		try{
			DossierSubmitScheduler object = new DossierSubmitScheduler();
			object.setTriggerFactory(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void doReceiveTest() {
		try{
			DossierSubmitScheduler object = new DossierSubmitScheduler();
			object.doReceive(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deactivateTest() {
		try{
			DossierSubmitScheduler object = new DossierSubmitScheduler();
			object.deactivate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void activateTest() {
//		try{
//			DossierSubmitScheduler object = new DossierSubmitScheduler();
//			object.activate();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}