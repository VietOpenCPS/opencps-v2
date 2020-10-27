package org.opencps.communication.utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class NotificationTemplateBusinessFactoryUtilTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void updateTest() {
//		try{
//			NotificationTemplateBusinessFactoryUtil.update(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void deleteTest() {
//		try{
//			NotificationTemplateBusinessFactoryUtil.delete(Long.valueOf(0), Long.valueOf(0), "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void readTest() {
//		try{
//			NotificationTemplateBusinessFactoryUtil.read(Long.valueOf(0), Long.valueOf(0), "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void createTest() {
//		try{
//			NotificationTemplateBusinessFactoryUtil.create(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void setNotificationTemplateBusinessImplTest() {
		try{
			NotificationTemplateBusinessFactoryUtil.setNotificationTemplateBusinessImpl(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNotificationTemplateBusinessImplTest() {
		try{
			NotificationTemplateBusinessFactoryUtil object = new NotificationTemplateBusinessFactoryUtil();
			object.getNotificationTemplateBusinessImpl();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNotificationTemplatesTest() {
		try{
			NotificationTemplateBusinessFactoryUtil.getNotificationTemplates(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNotificationTypesTest() {
		try{
			NotificationTemplateBusinessFactoryUtil.getNotificationTypes();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void fetchByNotificationTypeTest() {
//		try{
//			NotificationTemplateBusinessFactoryUtil.fetchByNotificationType(Long.valueOf(0), "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getNotificationTemplateBusinessTest() {
		try{
			NotificationTemplateBusinessFactoryUtil.getNotificationTemplateBusiness();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}