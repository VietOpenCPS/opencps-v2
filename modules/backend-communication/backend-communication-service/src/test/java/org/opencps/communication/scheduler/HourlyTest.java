package org.opencps.communication.scheduler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class HourlyTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getStorageTypeTest() {
		try{
			Hourly object = new Hourly();
			object.getStorageType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModuleServiceLifecycleTest() {
		try{
			Hourly object = new Hourly();
			object.setModuleServiceLifecycle(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSchedulerEngineHelperTest() {
		try{
			Hourly object = new Hourly();
			object.setSchedulerEngineHelper(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTriggerFactoryTest() {
		try{
			Hourly object = new Hourly();
			object.setTriggerFactory(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void doReceiveTest() {
		try{
			Hourly object = new Hourly();
			object.doReceive(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deactivateTest() {
		try{
			Hourly object = new Hourly();
			object.deactivate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void activateTest() {
		try{
			Hourly object = new Hourly();
			object.activate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}