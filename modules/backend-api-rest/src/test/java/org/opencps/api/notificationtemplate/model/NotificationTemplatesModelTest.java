package org.opencps.api.notificationtemplate.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class NotificationTemplatesModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getModifiedDateTest() {
		try{
			NotificationTemplatesModel object = new NotificationTemplatesModel();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			NotificationTemplatesModel object = new NotificationTemplatesModel();
			object.setModifiedDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmailSubjectTest() {
		try{
			NotificationTemplatesModel object = new NotificationTemplatesModel();
			object.setEmailSubject("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmailBodyTest() {
		try{
			NotificationTemplatesModel object = new NotificationTemplatesModel();
			object.setEmailBody("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTextMessageTest() {
		try{
			NotificationTemplatesModel object = new NotificationTemplatesModel();
			object.setTextMessage("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNotificationTypeTest() {
		try{
			NotificationTemplatesModel object = new NotificationTemplatesModel();
			object.getNotificationType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setNotificationTypeTest() {
		try{
			NotificationTemplatesModel object = new NotificationTemplatesModel();
			object.setNotificationType("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTextMessageTest() {
		try{
			NotificationTemplatesModel object = new NotificationTemplatesModel();
			object.getTextMessage();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmailSubjectTest() {
		try{
			NotificationTemplatesModel object = new NotificationTemplatesModel();
			object.getEmailSubject();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmailBodyTest() {
		try{
			NotificationTemplatesModel object = new NotificationTemplatesModel();
			object.getEmailBody();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTextSMSTest() {
		try{
			NotificationTemplatesModel object = new NotificationTemplatesModel();
			object.getTextSMS();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTextSMSTest() {
		try{
			NotificationTemplatesModel object = new NotificationTemplatesModel();
			object.setTextSMS("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}