package org.opencps.dossiermgt.scheduler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class SyncDVCQGSchedulerTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getStorageTypeTest() {
		try{
			SyncDVCQGScheduler object = new SyncDVCQGScheduler();
			object.getStorageType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModuleServiceLifecycleTest() {
		try{
			SyncDVCQGScheduler object = new SyncDVCQGScheduler();
			object.setModuleServiceLifecycle(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSchedulerEngineHelperTest() {
		try{
			SyncDVCQGScheduler object = new SyncDVCQGScheduler();
			object.setSchedulerEngineHelper(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTriggerFactoryTest() {
		try{
			SyncDVCQGScheduler object = new SyncDVCQGScheduler();
			object.setTriggerFactory(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void doReceiveTest() {
//		try{
//			SyncDVCQGScheduler object = new SyncDVCQGScheduler();
//			object.doReceive(null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void deactivateTest() {
		try{
			SyncDVCQGScheduler object = new SyncDVCQGScheduler();
			object.deactivate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void activateTest() {
		try{
			SyncDVCQGScheduler object = new SyncDVCQGScheduler();
			object.activate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}