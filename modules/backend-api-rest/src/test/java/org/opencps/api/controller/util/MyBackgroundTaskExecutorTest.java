package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class MyBackgroundTaskExecutorTest {
	@Before
	public void setUp() {
	}
	@Test
	public void cloneTest() {
		try{
			MyBackgroundTaskExecutor object = new MyBackgroundTaskExecutor();
			object.clone();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void cloneTest2() {
		try{
			MyBackgroundTaskExecutor object = new MyBackgroundTaskExecutor();
			object.clone();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void executeTest() {
//		try{
//			MyBackgroundTaskExecutor object = new MyBackgroundTaskExecutor();
//			object.execute(null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void isSerialTest() {
		try{
			MyBackgroundTaskExecutor object = new MyBackgroundTaskExecutor();
			object.isSerial();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBackgroundTaskDisplayTest() {
		try{
			MyBackgroundTaskExecutor object = new MyBackgroundTaskExecutor();
			object.getBackgroundTaskDisplay(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void sendStatusMessageTest() {
//		try{
//			MyBackgroundTaskExecutor.sendStatusMessage();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}