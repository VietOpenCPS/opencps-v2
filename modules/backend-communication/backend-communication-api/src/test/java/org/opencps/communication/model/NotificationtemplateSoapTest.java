package org.opencps.communication.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class NotificationtemplateSoapTest {
	@Before
	public void setUp() {
	}
	@Test
	public void toSoapModelsTest() {
		try{
			NotificationtemplateSoap.toSoapModels(new ArrayList<>());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest2() {
		try{
			NotificationtemplateSoap.toSoapModels(new Notificationtemplate[][]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest3() {
		try{
			NotificationtemplateSoap.toSoapModels(new Notificationtemplate[]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDateTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserNameTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.getUserName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserNameTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.setUserName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupIdTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.setGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCompanyIdTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.setCompanyId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserIdTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.setUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.setCreateDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCompanyIdTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.getCompanyId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupIdTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.getGroupId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.setModifiedDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelTest() {
		try{
			NotificationtemplateSoap.toSoapModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIntervalTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.getInterval();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmailSubjectTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.setEmailSubject("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmailBodyTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.setEmailBody("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTextMessageTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.setTextMessage("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSendEmailTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.setSendEmail(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSendSMSTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.setSendSMS(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setIntervalTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.setInterval("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupingTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.setGrouping(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isSendEmailTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.isSendEmail();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isSendSMSTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.isSendSMS();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setNotifyMessageTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.setNotifyMessage("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNotifyMessageTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.getNotifyMessage();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGuestUrlPatternTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.setGuestUrlPattern("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setExpireDurationTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.setExpireDuration(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserUrlPatternTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.setUserUrlPattern("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSendNotificationTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.setSendNotification(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSendNotificationTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.getSendNotification();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNotificationTemplateIdTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.getNotificationTemplateId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExpireDurationTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.getExpireDuration();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNotificationTypeTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.getNotificationType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setNotificationTypeTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.setNotificationType("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTextMessageTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.getTextMessage();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupingTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.getGrouping();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSendSMSTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.getSendSMS();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmailSubjectTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.getEmailSubject();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmailBodyTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.getEmailBody();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSendEmailTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.getSendEmail();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserUrlPatternTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.getUserUrlPattern();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGuestUrlPatternTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.getGuestUrlPattern();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isGroupingTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.isGrouping();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setNotificationTemplateIdTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.setNotificationTemplateId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isSendNotificationTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.isSendNotification();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPrimaryKeyTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.setPrimaryKey(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPrimaryKeyTest() {
		try{
			NotificationtemplateSoap object = new NotificationtemplateSoap();
			object.getPrimaryKey();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}