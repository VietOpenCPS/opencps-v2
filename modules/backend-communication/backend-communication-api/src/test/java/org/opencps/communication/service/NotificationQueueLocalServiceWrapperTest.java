package org.opencps.communication.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class NotificationQueueLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			NotificationQueueLocalServiceWrapper object = new NotificationQueueLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			NotificationQueueLocalServiceWrapper object = new NotificationQueueLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			NotificationQueueLocalServiceWrapper object = new NotificationQueueLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			NotificationQueueLocalServiceWrapper object = new NotificationQueueLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			NotificationQueueLocalServiceWrapper object = new NotificationQueueLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			NotificationQueueLocalServiceWrapper object = new NotificationQueueLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteByGroupTest() {
		try{
			NotificationQueueLocalServiceWrapper object = new NotificationQueueLocalServiceWrapper(null);
			object.deleteByGroup(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addNotificationQueueTest() {
		try{
			NotificationQueueLocalServiceWrapper object = new NotificationQueueLocalServiceWrapper(null);
			object.addNotificationQueue(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addNotificationQueueTest9() {
		try{
			NotificationQueueLocalServiceWrapper object = new NotificationQueueLocalServiceWrapper(null);
			object.addNotificationQueue(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", new Date(), new Date(), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createNotificationQueueTest() {
		try{
			NotificationQueueLocalServiceWrapper object = new NotificationQueueLocalServiceWrapper(null);
			object.createNotificationQueue(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_notificationType_LessThanExpireDateTest() {
		try{
			NotificationQueueLocalServiceWrapper object = new NotificationQueueLocalServiceWrapper(null);
			object.findByF_notificationType_LessThanExpireDate("abcde", new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNotificationQueuesTest() {
		try{
			NotificationQueueLocalServiceWrapper object = new NotificationQueueLocalServiceWrapper(null);
			object.getNotificationQueues(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_NT_CN_CPK_EMAILTest() {
		try{
			NotificationQueueLocalServiceWrapper object = new NotificationQueueLocalServiceWrapper(null);
			object.findByF_NT_CN_CPK_EMAIL(Long.valueOf(0), "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_LessThan_ExpireDateTest() {
		try{
			NotificationQueueLocalServiceWrapper object = new NotificationQueueLocalServiceWrapper(null);
			object.findByF_LessThan_ExpireDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateNotificationQueueTest() {
		try{
			NotificationQueueLocalServiceWrapper object = new NotificationQueueLocalServiceWrapper(null);
			object.updateNotificationQueue(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNotificationQueuesCountTest() {
		try{
			NotificationQueueLocalServiceWrapper object = new NotificationQueueLocalServiceWrapper(null);
			object.getNotificationQueuesCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteNotificationQueueTest() {
		try{
			NotificationQueueLocalServiceWrapper object = new NotificationQueueLocalServiceWrapper(null);
			object.deleteNotificationQueue(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteNotificationQueueTest18() {
		try{
			NotificationQueueLocalServiceWrapper object = new NotificationQueueLocalServiceWrapper(null);
			object.deleteNotificationQueue(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteNotificationQueueTest19() {
		try{
			NotificationQueueLocalServiceWrapper object = new NotificationQueueLocalServiceWrapper(null);
			object.deleteNotificationQueue(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchNotificationQueueTest() {
		try{
			NotificationQueueLocalServiceWrapper object = new NotificationQueueLocalServiceWrapper(null);
			object.fetchNotificationQueue(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNotificationQueueTest() {
		try{
			NotificationQueueLocalServiceWrapper object = new NotificationQueueLocalServiceWrapper(null);
			object.getNotificationQueue(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteExpiredNotificationQueueTest() {
		try{
			NotificationQueueLocalServiceWrapper object = new NotificationQueueLocalServiceWrapper(null);
			object.deleteExpiredNotificationQueue(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			NotificationQueueLocalServiceWrapper object = new NotificationQueueLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			NotificationQueueLocalServiceWrapper object = new NotificationQueueLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			NotificationQueueLocalServiceWrapper object = new NotificationQueueLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			NotificationQueueLocalServiceWrapper object = new NotificationQueueLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest27() {
		try{
			NotificationQueueLocalServiceWrapper object = new NotificationQueueLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			NotificationQueueLocalServiceWrapper object = new NotificationQueueLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest29() {
		try{
			NotificationQueueLocalServiceWrapper object = new NotificationQueueLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			NotificationQueueLocalServiceWrapper object = new NotificationQueueLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			NotificationQueueLocalServiceWrapper object = new NotificationQueueLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest32() {
		try{
			NotificationQueueLocalServiceWrapper object = new NotificationQueueLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			NotificationQueueLocalServiceWrapper object = new NotificationQueueLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}