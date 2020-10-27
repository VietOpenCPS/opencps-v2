package org.opencps.communication.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class NotificationQueueSoapTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getClassNameTest() {
		try{
			NotificationQueueSoap object = new NotificationQueueSoap();
			object.getClassName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest() {
		try{
			NotificationQueueSoap.toSoapModels(new ArrayList<>());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest3() {
		try{
			NotificationQueueSoap.toSoapModels(new NotificationQueue[][]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelsTest4() {
		try{
			NotificationQueueSoap.toSoapModels(new NotificationQueue[]{});
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDateTest() {
		try{
			NotificationQueueSoap object = new NotificationQueueSoap();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserNameTest() {
		try{
			NotificationQueueSoap object = new NotificationQueueSoap();
			object.getUserName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserNameTest() {
		try{
			NotificationQueueSoap object = new NotificationQueueSoap();
			object.setUserName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGroupIdTest() {
		try{
			NotificationQueueSoap object = new NotificationQueueSoap();
			object.setGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCompanyIdTest() {
		try{
			NotificationQueueSoap object = new NotificationQueueSoap();
			object.setCompanyId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserIdTest() {
		try{
			NotificationQueueSoap object = new NotificationQueueSoap();
			object.setUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			NotificationQueueSoap object = new NotificationQueueSoap();
			object.setCreateDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCompanyIdTest() {
		try{
			NotificationQueueSoap object = new NotificationQueueSoap();
			object.getCompanyId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			NotificationQueueSoap object = new NotificationQueueSoap();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			NotificationQueueSoap object = new NotificationQueueSoap();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGroupIdTest() {
		try{
			NotificationQueueSoap object = new NotificationQueueSoap();
			object.getGroupId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			NotificationQueueSoap object = new NotificationQueueSoap();
			object.setModifiedDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toSoapModelTest() {
		try{
			NotificationQueueSoap.toSoapModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setToUsernameTest() {
		try{
			NotificationQueueSoap object = new NotificationQueueSoap();
			object.setToUsername("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setToEmailTest() {
		try{
			NotificationQueueSoap object = new NotificationQueueSoap();
			object.setToEmail("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setToTelNoTest() {
		try{
			NotificationQueueSoap object = new NotificationQueueSoap();
			object.setToTelNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPayloadTest() {
		try{
			NotificationQueueSoap object = new NotificationQueueSoap();
			object.setPayload("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setToUserIdTest() {
		try{
			NotificationQueueSoap object = new NotificationQueueSoap();
			object.setToUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setExpireDateTest() {
		try{
			NotificationQueueSoap object = new NotificationQueueSoap();
			object.setExpireDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExpireDateTest() {
		try{
			NotificationQueueSoap object = new NotificationQueueSoap();
			object.getExpireDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPayloadTest() {
		try{
			NotificationQueueSoap object = new NotificationQueueSoap();
			object.getPayload();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFromUsernameTest() {
		try{
			NotificationQueueSoap object = new NotificationQueueSoap();
			object.getFromUsername();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getToEmailTest() {
		try{
			NotificationQueueSoap object = new NotificationQueueSoap();
			object.getToEmail();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getToTelNoTest() {
		try{
			NotificationQueueSoap object = new NotificationQueueSoap();
			object.getToTelNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getToUsernameTest() {
		try{
			NotificationQueueSoap object = new NotificationQueueSoap();
			object.getToUsername();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFromUsernameTest() {
		try{
			NotificationQueueSoap object = new NotificationQueueSoap();
			object.setFromUsername("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getToUserIdTest() {
		try{
			NotificationQueueSoap object = new NotificationQueueSoap();
			object.getToUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNotificationQueueIdTest() {
		try{
			NotificationQueueSoap object = new NotificationQueueSoap();
			object.getNotificationQueueId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setNotificationQueueIdTest() {
		try{
			NotificationQueueSoap object = new NotificationQueueSoap();
			object.setNotificationQueueId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPublicationDateTest() {
		try{
			NotificationQueueSoap object = new NotificationQueueSoap();
			object.setPublicationDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPublicationDateTest() {
		try{
			NotificationQueueSoap object = new NotificationQueueSoap();
			object.getPublicationDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNotificationTypeTest() {
		try{
			NotificationQueueSoap object = new NotificationQueueSoap();
			object.getNotificationType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setNotificationTypeTest() {
		try{
			NotificationQueueSoap object = new NotificationQueueSoap();
			object.setNotificationType("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getClassPKTest() {
		try{
			NotificationQueueSoap object = new NotificationQueueSoap();
			object.getClassPK();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setClassPKTest() {
		try{
			NotificationQueueSoap object = new NotificationQueueSoap();
			object.setClassPK("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setClassNameTest() {
		try{
			NotificationQueueSoap object = new NotificationQueueSoap();
			object.setClassName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPrimaryKeyTest() {
		try{
			NotificationQueueSoap object = new NotificationQueueSoap();
			object.setPrimaryKey(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPrimaryKeyTest() {
		try{
			NotificationQueueSoap object = new NotificationQueueSoap();
			object.getPrimaryKey();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}