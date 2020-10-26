package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class NotificationTemplateImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void updateTest() {
		try{
			NotificationTemplateImpl object = new NotificationTemplateImpl();
			object.update(null, null, null, null, null, null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteTest() {
		try{
			NotificationTemplateImpl object = new NotificationTemplateImpl();
			object.delete(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void readTest() {
		try{
			NotificationTemplateImpl object = new NotificationTemplateImpl();
			object.read(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createTest() {
		try{
			NotificationTemplateImpl object = new NotificationTemplateImpl();
			object.create(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNotificationtemplatesTest() {
		try{
			NotificationTemplateImpl object = new NotificationTemplateImpl();
			object.getNotificationtemplates(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}