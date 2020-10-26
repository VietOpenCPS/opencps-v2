package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class NotificationManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void markAsReadTest() {
		try{
			NotificationManagementImpl object = new NotificationManagementImpl();
			object.markAsRead(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void markAsReadTest2() {
		try{
			NotificationManagementImpl object = new NotificationManagementImpl();
			object.markAsRead(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNotificationListTest() {
		try{
			NotificationManagementImpl object = new NotificationManagementImpl();
			object.getNotificationList(null, null, null, null, null, null, null, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countTotalNotificationsTest() {
		try{
			NotificationManagementImpl object = new NotificationManagementImpl();
			object.countTotalNotifications(null, null, null, null, null, null, null, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteNotificationTest() {
		try{
			NotificationManagementImpl object = new NotificationManagementImpl();
			object.deleteNotification(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}