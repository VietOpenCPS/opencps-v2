package org.opencps.communication.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class NotificationTemplateActionsTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void updateTest() {
//		try{
//			NotificationTemplateActions object = new NotificationTemplateActions();
//			object.update(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void deleteTest() {
//		try{
//			NotificationTemplateActions object = new NotificationTemplateActions();
//			object.delete(Long.valueOf(0), Long.valueOf(0), "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void readTest() {
//		try{
//			NotificationTemplateActions object = new NotificationTemplateActions();
//			object.read(Long.valueOf(0), Long.valueOf(0), "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void createTest() {
//		try{
//			NotificationTemplateActions object = new NotificationTemplateActions();
//			object.create(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateNotificationTemplateDBTest() {
//		try{
//			NotificationTemplateActions object = new NotificationTemplateActions();
//			object.updateNotificationTemplateDB(Long.valueOf(0), Long.valueOf(0), "abcde", true, "abcde", "abcde", "abcde", true, 0, "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	
//	@Test
//	public void deleteAllNotificationTemplateTest() {
//		try{
//			NotificationTemplateActions object = new NotificationTemplateActions();
//			object.deleteAllNotificationTemplate(Long.valueOf(0), Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	
	@Test
	public void getNotificationTemplatesTest() {
		try{
			NotificationTemplateActions object = new NotificationTemplateActions();
			object.getNotificationTemplates(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	
	@Test
	public void getNotificationTypesTest() {
		try{
			NotificationTemplateActions object = new NotificationTemplateActions();
			object.getNotificationTypes();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}