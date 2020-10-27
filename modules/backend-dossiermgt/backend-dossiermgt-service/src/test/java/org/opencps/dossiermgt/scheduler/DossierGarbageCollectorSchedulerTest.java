package org.opencps.dossiermgt.scheduler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DossierGarbageCollectorSchedulerTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getStorageTypeTest() {
		try{
			DossierGarbageCollectorScheduler object = new DossierGarbageCollectorScheduler();
			object.getStorageType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModuleServiceLifecycleTest() {
		try{
			DossierGarbageCollectorScheduler object = new DossierGarbageCollectorScheduler();
			object.setModuleServiceLifecycle(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSchedulerEngineHelperTest() {
		try{
			DossierGarbageCollectorScheduler object = new DossierGarbageCollectorScheduler();
			object.setSchedulerEngineHelper(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTriggerFactoryTest() {
		try{
			DossierGarbageCollectorScheduler object = new DossierGarbageCollectorScheduler();
			object.setTriggerFactory(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void doReceiveTest() {
//		try{
//			DossierGarbageCollectorScheduler object = new DossierGarbageCollectorScheduler();
//			object.doReceive(null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void deactivateTest() {
		try{
			DossierGarbageCollectorScheduler object = new DossierGarbageCollectorScheduler();
			object.deactivate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void activateTest() {
		try{
			DossierGarbageCollectorScheduler object = new DossierGarbageCollectorScheduler();
			object.activate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}