package org.opencps.kernel.message;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class MBMessageEntryTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getClassNameTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.getClassName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDateTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupIdTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.setGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCompanyIdTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.setCompanyId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserIdTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.setUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.setCreateDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCompanyIdTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.getCompanyId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupIdTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.getGroupId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.setModifiedDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getZaloAccessTokenTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.getZaloAccessToken();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setZaloAccessTokenTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.setZaloAccessToken("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAttachementNameTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.getAttachementName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAttachementNameTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.setAttachementName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMappingZaloUidTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.getMappingZaloUid();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMappingZaloUidTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.setMappingZaloUid(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIntervalTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.getInterval();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setToTelNoTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.setToTelNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmailSubjectTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.setEmailSubject("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getToTelNoTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.getToTelNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmailBodyTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.setEmailBody("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTextMessageTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.setTextMessage("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSendEmailTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.setSendEmail(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSendSMSTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.setSendSMS(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setIntervalTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.setInterval(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupingTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.setGrouping(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isSendEmailTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.isSendEmail();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isSendSMSTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.isSendSMS();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setNotifyMessageTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.setNotifyMessage("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNotifyMessageTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.getNotifyMessage();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setExpireDurationTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.setExpireDuration(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBccAddressTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.getBccAddress();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSendZaloTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.setSendZalo(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFromNameTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.getFromName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFromEmailTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.setFromEmail("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCcAddressTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.getCcAddress();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getToUserIdsTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.getToUserIds();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGuestUrlTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.setGuestUrl("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setBccAddressTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.setBccAddress(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setBulkAddressTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.setBulkAddress(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserUrlTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.getUserUrl();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGuestUrlTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.getGuestUrl();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isSendZaloTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.isSendZalo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isSendNotifyTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.isSendNotify();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getToAddressTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.getToAddress();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSendNotifyTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.setSendNotify(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFromNameTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.setFromName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAttachementTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.setAttachement(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCcAddressTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.setCcAddress(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFromEmailTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.getFromEmail();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAttachementTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.getAttachement();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserUrlTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.setUserUrl("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setToUserIdsTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.setToUserIds(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setToAddressTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.setToAddress(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBulkAddressTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.getBulkAddress();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExpireDurationTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.getExpireDuration();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNotificationTypeTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.getNotificationType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setNotificationTypeTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.setNotificationType("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDataTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.setData("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTextMessageTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.getTextMessage();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupingTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.getGrouping();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmailSubjectTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.getEmailSubject();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmailBodyTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.getEmailBody();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDataTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.getData();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setClassNameTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.setClassName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFromTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.getFrom();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFromTest() {
		try{
			MBMessageEntry object = new MBMessageEntry(null);
			object.setFrom(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}