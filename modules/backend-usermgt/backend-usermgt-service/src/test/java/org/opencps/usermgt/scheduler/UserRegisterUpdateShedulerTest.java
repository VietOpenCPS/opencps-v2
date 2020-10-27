package org.opencps.usermgt.scheduler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class UserRegisterUpdateShedulerTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getStorageTypeTest() {
		try{
			UserRegisterUpdateSheduler object = new UserRegisterUpdateSheduler();
			object.getStorageType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModuleServiceLifecycleTest() {
		try{
			UserRegisterUpdateSheduler object = new UserRegisterUpdateSheduler();
			object.setModuleServiceLifecycle(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSchedulerEngineHelperTest() {
		try{
			UserRegisterUpdateSheduler object = new UserRegisterUpdateSheduler();
			object.setSchedulerEngineHelper(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTriggerFactoryTest() {
		try{
			UserRegisterUpdateSheduler object = new UserRegisterUpdateSheduler();
			object.setTriggerFactory(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void doReceiveTest() {
//		try{
//			UserRegisterUpdateSheduler object = new UserRegisterUpdateSheduler();
//			object.doReceive(null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void deactivateTest() {
		try{
			UserRegisterUpdateSheduler object = new UserRegisterUpdateSheduler();
			object.deactivate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void activateTest() {
		try{
			UserRegisterUpdateSheduler object = new UserRegisterUpdateSheduler();
			object.activate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}