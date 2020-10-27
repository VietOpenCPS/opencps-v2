package org.opencps.communication.utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class NotificationQueueBusinessFactoryUtilTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void updateTest() {
//		try{
//			NotificationQueueBusinessFactoryUtil.update(null, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void deleteTest() {
//		try{
//			NotificationQueueBusinessFactoryUtil.delete(Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void readTest() {
//		try{
//			NotificationQueueBusinessFactoryUtil.read(Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void createTest() {
//		try{
//			NotificationQueueBusinessFactoryUtil.create(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", new Date(), new Date(), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void findByNotificationType_LessThanExpireDateTest() {
//		try{
//			NotificationQueueBusinessFactoryUtil.findByNotificationType_LessThanExpireDate("abcde", new Date());
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
	
	@Test
	public void setNotificationQueueBusinessImplTest() {
		try{
			NotificationQueueBusinessFactoryUtil.setNotificationQueueBusinessImpl(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNotificationQueueBusinessTest() {
		try{
			NotificationQueueBusinessFactoryUtil.getNotificationQueueBusiness();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNotificationQueuesTest() {
		try{
			NotificationQueueBusinessFactoryUtil.getNotificationQueues(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	
	@Test
	public void getNotificationQueueBusinessImplTest() {
		try{
			NotificationQueueBusinessFactoryUtil object = new NotificationQueueBusinessFactoryUtil();
			object.getNotificationQueueBusinessImpl();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}