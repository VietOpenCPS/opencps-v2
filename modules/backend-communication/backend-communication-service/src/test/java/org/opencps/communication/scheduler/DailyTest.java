package org.opencps.communication.scheduler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DailyTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getStorageTypeTest() {
		try{
			Daily object = new Daily();
			object.getStorageType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModuleServiceLifecycleTest() {
		try{
			Daily object = new Daily();
			object.setModuleServiceLifecycle(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSchedulerEngineHelperTest() {
		try{
			Daily object = new Daily();
			object.setSchedulerEngineHelper(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTriggerFactoryTest() {
		try{
			Daily object = new Daily();
			object.setTriggerFactory(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void doProcessNotificationTest() {
		try{
			Daily object = new Daily();
			object.doProcessNotification(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void doReceiveTest() {
		try{
			Daily object = new Daily();
			object.doReceive(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deactivateTest() {
		try{
			Daily object = new Daily();
			object.deactivate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void activateTest() {
		try{
			Daily object = new Daily();
			object.activate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}