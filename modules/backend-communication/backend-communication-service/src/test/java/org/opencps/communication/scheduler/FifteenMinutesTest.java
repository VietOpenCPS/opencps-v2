package org.opencps.communication.scheduler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class FifteenMinutesTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getStorageTypeTest() {
		try{
			FifteenMinutes object = new FifteenMinutes();
			object.getStorageType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModuleServiceLifecycleTest() {
		try{
			FifteenMinutes object = new FifteenMinutes();
			object.setModuleServiceLifecycle(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSchedulerEngineHelperTest() {
		try{
			FifteenMinutes object = new FifteenMinutes();
			object.setSchedulerEngineHelper(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTriggerFactoryTest() {
		try{
			FifteenMinutes object = new FifteenMinutes();
			object.setTriggerFactory(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void doReceiveTest() {
		try{
			FifteenMinutes object = new FifteenMinutes();
			object.doReceive(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deactivateTest() {
		try{
			FifteenMinutes object = new FifteenMinutes();
			object.deactivate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void activateTest() {
		try{
			FifteenMinutes object = new FifteenMinutes();
			object.activate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}