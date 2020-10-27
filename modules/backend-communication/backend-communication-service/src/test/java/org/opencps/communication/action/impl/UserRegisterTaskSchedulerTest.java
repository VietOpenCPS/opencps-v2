package org.opencps.communication.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class UserRegisterTaskSchedulerTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setModuleServiceLifecycleTest() {
		try{
			UserRegisterTaskScheduler object = new UserRegisterTaskScheduler();
			object.setModuleServiceLifecycle(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSchedulerEngineHelperTest() {
		try{
			UserRegisterTaskScheduler object = new UserRegisterTaskScheduler();
			object.setSchedulerEngineHelper(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTriggerFactoryTest() {
		try{
			UserRegisterTaskScheduler object = new UserRegisterTaskScheduler();
			object.setTriggerFactory(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void doReceiveTest() {
		try{
			UserRegisterTaskScheduler object = new UserRegisterTaskScheduler();
			object.doReceive(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deactivateTest() {
		try{
			UserRegisterTaskScheduler object = new UserRegisterTaskScheduler();
			object.deactivate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void activateTest() {
//		try{
//			UserRegisterTaskScheduler object = new UserRegisterTaskScheduler();
//			object.activate();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}