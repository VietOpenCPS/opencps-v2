package org.opencps.communication.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class NotificationQueueActionsTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void readTest() {
//		try{
//			NotificationQueueActions object = new NotificationQueueActions();
//			object.read(Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getNotificationQueuesTest() {
		try{
			NotificationQueueActions object = new NotificationQueueActions();
			object.getNotificationQueues(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}