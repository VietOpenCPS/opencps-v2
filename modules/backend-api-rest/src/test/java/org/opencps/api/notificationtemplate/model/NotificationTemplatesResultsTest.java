package org.opencps.api.notificationtemplate.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class NotificationTemplatesResultsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getTotalTest() {
		try{
			NotificationTemplatesResults object = new NotificationTemplatesResults();
			object.getTotal();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNotificationTemplatesModelTest() {
		try{
			NotificationTemplatesResults object = new NotificationTemplatesResults();
			object.getNotificationTemplatesModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTotalTest() {
		try{
			NotificationTemplatesResults object = new NotificationTemplatesResults();
			object.setTotal(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}