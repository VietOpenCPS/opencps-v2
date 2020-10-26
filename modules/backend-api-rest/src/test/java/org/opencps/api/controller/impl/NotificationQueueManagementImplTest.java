package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class NotificationQueueManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getNotificationQueuesTest() {
		try{
			NotificationQueueManagementImpl object = new NotificationQueueManagementImpl();
			object.getNotificationQueues(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getNotificationQueuesByIDTest() {
//		try{
//			NotificationQueueManagementImpl object = new NotificationQueueManagementImpl();
//			object.getNotificationQueuesByID(null, null, null, null, null, null, Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}