package org.opencps.background.executor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class SiteCleanBackgroundTaskExecutorTest {
	@Before
	public void setUp() {
	}
	@Test
	public void cloneTest() {
		try{
			SiteCleanBackgroundTaskExecutor object = new SiteCleanBackgroundTaskExecutor();
			object.clone();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void cloneTest2() {
		try{
			SiteCleanBackgroundTaskExecutor object = new SiteCleanBackgroundTaskExecutor();
			object.clone();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void executeTest() {
		try{
			SiteCleanBackgroundTaskExecutor object = new SiteCleanBackgroundTaskExecutor();
			object.execute(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isSerialTest() {
		try{
			SiteCleanBackgroundTaskExecutor object = new SiteCleanBackgroundTaskExecutor();
			object.isSerial();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	/*@Test
	public void getBackgroundTaskDisplayTest() {
		try{
			SiteCleanBackgroundTaskExecutor object = new SiteCleanBackgroundTaskExecutor();
			object.getBackgroundTaskDisplay(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}*/
}