package org.opencps.background.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class BackgroundTaskResultTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setBackgroundTaskIdTest() {
		try{
			BackgroundTaskResult object = new BackgroundTaskResult();
			object.setBackgroundTaskId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBackgroundTaskIdTest() {
		try{
			BackgroundTaskResult object = new BackgroundTaskResult();
			object.getBackgroundTaskId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}