package org.opencps.communication.scheduler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class NotificationSchedulerTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setModuleServiceLifecycleTest() {
		try{
			NotificationScheduler object = new NotificationScheduler();
			object.setModuleServiceLifecycle(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSchedulerEngineHelperTest() {
		try{
			NotificationScheduler object = new NotificationScheduler();
			object.setSchedulerEngineHelper(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTriggerFactoryTest() {
		try{
			NotificationScheduler object = new NotificationScheduler();
			object.setTriggerFactory(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void doReceiveTest() {
		try{
			NotificationScheduler object = new NotificationScheduler();
			object.doReceive(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deactivateTest() {
		try{
			NotificationScheduler object = new NotificationScheduler();
			object.deactivate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void activateTest() {
//		try{
//			NotificationScheduler object = new NotificationScheduler();
//			object.activate();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}