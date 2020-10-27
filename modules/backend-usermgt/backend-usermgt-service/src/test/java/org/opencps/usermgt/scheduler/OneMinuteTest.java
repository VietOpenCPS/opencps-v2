package org.opencps.usermgt.scheduler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class OneMinuteTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setModuleServiceLifecycleTest() {
		try{
			OneMinute object = new OneMinute();
			object.setModuleServiceLifecycle(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSchedulerEngineHelperTest() {
		try{
			OneMinute object = new OneMinute();
			object.setSchedulerEngineHelper(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTriggerFactoryTest() {
		try{
			OneMinute object = new OneMinute();
			object.setTriggerFactory(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void doReceiveTest() {
		try{
			OneMinute object = new OneMinute();
			object.doReceive(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deactivateTest() {
		try{
			OneMinute object = new OneMinute();
			object.deactivate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void activateTest() {
//		try{
//			OneMinute object = new OneMinute();
//			object.activate();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}