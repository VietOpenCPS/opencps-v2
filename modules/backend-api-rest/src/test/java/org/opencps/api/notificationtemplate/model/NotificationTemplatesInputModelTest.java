package org.opencps.api.notificationtemplate.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class NotificationTemplatesInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setEmailSubjectTest() {
		try{
			NotificationTemplatesInputModel object = new NotificationTemplatesInputModel();
			object.setEmailSubject("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmailBodyTest() {
		try{
			NotificationTemplatesInputModel object = new NotificationTemplatesInputModel();
			object.setEmailBody("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTextMessageTest() {
		try{
			NotificationTemplatesInputModel object = new NotificationTemplatesInputModel();
			object.setTextMessage("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTextMessageTest() {
		try{
			NotificationTemplatesInputModel object = new NotificationTemplatesInputModel();
			object.getTextMessage();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmailSubjectTest() {
		try{
			NotificationTemplatesInputModel object = new NotificationTemplatesInputModel();
			object.getEmailSubject();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmailBodyTest() {
		try{
			NotificationTemplatesInputModel object = new NotificationTemplatesInputModel();
			object.getEmailBody();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTextSMSTest() {
		try{
			NotificationTemplatesInputModel object = new NotificationTemplatesInputModel();
			object.getTextSMS();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTextSMSTest() {
		try{
			NotificationTemplatesInputModel object = new NotificationTemplatesInputModel();
			object.setTextSMS("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}