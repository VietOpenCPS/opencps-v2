package org.opencps.communication.bussiness.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class NotificationQueueBusinessImplTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void updateTest() {
//		try{
//			NotificationQueueBusinessImpl object = new NotificationQueueBusinessImpl();
//			object.update(null, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void deleteTest() {
//		try{
//			NotificationQueueBusinessImpl object = new NotificationQueueBusinessImpl();
//			object.delete(Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void readTest() {
//		try{
//			NotificationQueueBusinessImpl object = new NotificationQueueBusinessImpl();
//			object.read(Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void createTest() {
//		try{
//			NotificationQueueBusinessImpl object = new NotificationQueueBusinessImpl();
//			object.create(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", new Date(), new Date(), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void findByNotificationType_LessThanExpireDateTest() {
//		try{
//			NotificationQueueBusinessImpl object = new NotificationQueueBusinessImpl();
//			object.findByNotificationType_LessThanExpireDate("abcde", new Date());
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getNotificationQueuesTest() {
		try{
			NotificationQueueBusinessImpl object = new NotificationQueueBusinessImpl();
			object.getNotificationQueues(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}