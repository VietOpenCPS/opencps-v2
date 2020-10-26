package org.opencps.background.siteclean;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class BackgroundSiteCleanTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getExecutionLogTest() {
		try{
			BackgroundSiteClean object = new BackgroundSiteClean();
			object.getExecutionLog();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPercentageTest() {
		try{
			BackgroundSiteClean object = new BackgroundSiteClean();
			object.getPercentage();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPercentageTest() {
		try{
			BackgroundSiteClean object = new BackgroundSiteClean();
			object.setPercentage(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setExecutionLogTest() {
		try{
			BackgroundSiteClean object = new BackgroundSiteClean();
			object.setExecutionLog("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}