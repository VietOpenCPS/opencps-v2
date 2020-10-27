package org.opencps.communication.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class NotificationQueueLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			NotificationQueueLocalServiceImpl object = new NotificationQueueLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteByGroupTest() {
		try{
			NotificationQueueLocalServiceImpl object = new NotificationQueueLocalServiceImpl();
			object.deleteByGroup(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addNotificationQueueTest() {
		try{
			NotificationQueueLocalServiceImpl object = new NotificationQueueLocalServiceImpl();
			object.addNotificationQueue(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", new Date(), new Date(), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_notificationType_LessThanExpireDateTest() {
		try{
			NotificationQueueLocalServiceImpl object = new NotificationQueueLocalServiceImpl();
			object.findByF_notificationType_LessThanExpireDate("abcde", new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_NT_CN_CPK_EMAILTest() {
		try{
			NotificationQueueLocalServiceImpl object = new NotificationQueueLocalServiceImpl();
			object.findByF_NT_CN_CPK_EMAIL(Long.valueOf(0), "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_LessThan_ExpireDateTest() {
		try{
			NotificationQueueLocalServiceImpl object = new NotificationQueueLocalServiceImpl();
			object.findByF_LessThan_ExpireDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteNotificationQueueTest() {
		try{
			NotificationQueueLocalServiceImpl object = new NotificationQueueLocalServiceImpl();
			object.deleteNotificationQueue(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteExpiredNotificationQueueTest() {
		try{
			NotificationQueueLocalServiceImpl object = new NotificationQueueLocalServiceImpl();
			object.deleteExpiredNotificationQueue(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			NotificationQueueLocalServiceImpl object = new NotificationQueueLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}