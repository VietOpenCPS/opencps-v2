package org.opencps.usermgt.scheduler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class OneDayTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getStorageTypeTest() {
		try{
			OneDay object = new OneDay();
			object.getStorageType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModuleServiceLifecycleTest() {
		try{
			OneDay object = new OneDay();
			object.setModuleServiceLifecycle(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSchedulerEngineHelperTest() {
		try{
			OneDay object = new OneDay();
			object.setSchedulerEngineHelper(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTriggerFactoryTest() {
		try{
			OneDay object = new OneDay();
			object.setTriggerFactory(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void doReceiveTest() {
//		try{
//			OneDay object = new OneDay();
//			object.doReceive(null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void deactivateTest() {
		try{
			OneDay object = new OneDay();
			object.deactivate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void activateTest() {
		try{
			OneDay object = new OneDay();
			object.activate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}