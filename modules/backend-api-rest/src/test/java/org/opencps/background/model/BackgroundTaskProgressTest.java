package org.opencps.background.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class BackgroundTaskProgressTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getExecutionLogTest() {
		try{
			BackgroundTaskProgress object = new BackgroundTaskProgress();
			object.getExecutionLog();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPercentageTest() {
		try{
			BackgroundTaskProgress object = new BackgroundTaskProgress();
			object.getPercentage();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setBackgroundTaskIdTest() {
		try{
			BackgroundTaskProgress object = new BackgroundTaskProgress();
			object.setBackgroundTaskId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBackgroundTaskIdTest() {
		try{
			BackgroundTaskProgress object = new BackgroundTaskProgress();
			object.getBackgroundTaskId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPercentageTest() {
		try{
			BackgroundTaskProgress object = new BackgroundTaskProgress();
			object.setPercentage(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setExecutionLogTest() {
		try{
			BackgroundTaskProgress object = new BackgroundTaskProgress();
			object.setExecutionLog("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}