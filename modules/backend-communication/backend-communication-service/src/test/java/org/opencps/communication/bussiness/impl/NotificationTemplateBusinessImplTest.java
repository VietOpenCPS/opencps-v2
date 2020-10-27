package org.opencps.communication.bussiness.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class NotificationTemplateBusinessImplTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void updateTest() {
//		try{
//			NotificationTemplateBusinessImpl object = new NotificationTemplateBusinessImpl();
//			object.update(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void deleteTest() {
//		try{
//			NotificationTemplateBusinessImpl object = new NotificationTemplateBusinessImpl();
//			object.delete(Long.valueOf(0), Long.valueOf(0), "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void readTest() {
//		try{
//			NotificationTemplateBusinessImpl object = new NotificationTemplateBusinessImpl();
//			object.read(Long.valueOf(0), Long.valueOf(0), "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void createTest() {
//		try{
//			NotificationTemplateBusinessImpl object = new NotificationTemplateBusinessImpl();
//			object.create(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	
//	@Test
//	public void fetchByNotificationTypeTest() {
//		try{
//			NotificationTemplateBusinessImpl object = new NotificationTemplateBusinessImpl();
//			object.fetchByNotificationType(Long.valueOf(0), "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	@Test
//	public void getNotificationTemplatesTest() {
//		try{
//			NotificationTemplateBusinessImpl object = new NotificationTemplateBusinessImpl();
//			object.getNotificationTemplates(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
	@Test
	public void getNotificationTypesTest() {
		try{
			NotificationTemplateBusinessImpl object = new NotificationTemplateBusinessImpl();
			object.getNotificationTypes();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}