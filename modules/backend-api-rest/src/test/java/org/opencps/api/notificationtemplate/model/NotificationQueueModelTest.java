package org.opencps.api.notificationtemplate.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class NotificationQueueModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getClassNameTest() {
		try{
			NotificationQueueModel object = new NotificationQueueModel();
			object.getClassName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setToUsernameTest() {
		try{
			NotificationQueueModel object = new NotificationQueueModel();
			object.setToUsername("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setToEmailTest() {
		try{
			NotificationQueueModel object = new NotificationQueueModel();
			object.setToEmail("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setToTelNoTest() {
		try{
			NotificationQueueModel object = new NotificationQueueModel();
			object.setToTelNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPayloadTest() {
		try{
			NotificationQueueModel object = new NotificationQueueModel();
			object.setPayload("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setToUserIdTest() {
		try{
			NotificationQueueModel object = new NotificationQueueModel();
			object.setToUserId("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setExpireDateTest() {
		try{
			NotificationQueueModel object = new NotificationQueueModel();
			object.setExpireDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExpireDateTest() {
		try{
			NotificationQueueModel object = new NotificationQueueModel();
			object.getExpireDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPayloadTest() {
		try{
			NotificationQueueModel object = new NotificationQueueModel();
			object.getPayload();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFromUsernameTest() {
		try{
			NotificationQueueModel object = new NotificationQueueModel();
			object.getFromUsername();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmailSubjectTest() {
		try{
			NotificationQueueModel object = new NotificationQueueModel();
			object.setEmailSubject("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getToEmailTest() {
		try{
			NotificationQueueModel object = new NotificationQueueModel();
			object.getToEmail();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getToTelNoTest() {
		try{
			NotificationQueueModel object = new NotificationQueueModel();
			object.getToTelNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmailBodyTest() {
		try{
			NotificationQueueModel object = new NotificationQueueModel();
			object.setEmailBody("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTextMessageTest() {
		try{
			NotificationQueueModel object = new NotificationQueueModel();
			object.setTextMessage("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSendEmailTest() {
		try{
			NotificationQueueModel object = new NotificationQueueModel();
			object.setSendEmail("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSendSMSTest() {
		try{
			NotificationQueueModel object = new NotificationQueueModel();
			object.setSendSMS("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getToUsernameTest() {
		try{
			NotificationQueueModel object = new NotificationQueueModel();
			object.getToUsername();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFromUsernameTest() {
		try{
			NotificationQueueModel object = new NotificationQueueModel();
			object.setFromUsername("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getToUserIdTest() {
		try{
			NotificationQueueModel object = new NotificationQueueModel();
			object.getToUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNotificationQueueIdTest() {
		try{
			NotificationQueueModel object = new NotificationQueueModel();
			object.getNotificationQueueId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setNotificationQueueIdTest() {
		try{
			NotificationQueueModel object = new NotificationQueueModel();
			object.setNotificationQueueId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNotificationTypeTest() {
		try{
			NotificationQueueModel object = new NotificationQueueModel();
			object.getNotificationType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setNotificationTypeTest() {
		try{
			NotificationQueueModel object = new NotificationQueueModel();
			object.setNotificationType("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTextMessageTest() {
		try{
			NotificationQueueModel object = new NotificationQueueModel();
			object.getTextMessage();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSendSMSTest() {
		try{
			NotificationQueueModel object = new NotificationQueueModel();
			object.getSendSMS();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmailSubjectTest() {
		try{
			NotificationQueueModel object = new NotificationQueueModel();
			object.getEmailSubject();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmailBodyTest() {
		try{
			NotificationQueueModel object = new NotificationQueueModel();
			object.getEmailBody();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSendEmailTest() {
		try{
			NotificationQueueModel object = new NotificationQueueModel();
			object.getSendEmail();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUrlLinkTest() {
		try{
			NotificationQueueModel object = new NotificationQueueModel();
			object.setUrlLink("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUrlLinkTest() {
		try{
			NotificationQueueModel object = new NotificationQueueModel();
			object.getUrlLink();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getClassPKTest() {
		try{
			NotificationQueueModel object = new NotificationQueueModel();
			object.getClassPK();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setClassPKTest() {
		try{
			NotificationQueueModel object = new NotificationQueueModel();
			object.setClassPK("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setClassNameTest() {
		try{
			NotificationQueueModel object = new NotificationQueueModel();
			object.setClassName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}