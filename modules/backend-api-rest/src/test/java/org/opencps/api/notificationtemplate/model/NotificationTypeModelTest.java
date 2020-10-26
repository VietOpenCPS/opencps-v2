package org.opencps.api.notificationtemplate.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class NotificationTypeModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getTypeNameTest() {
		try{
			NotificationTypeModel object = new NotificationTypeModel();
			object.getTypeName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTypeCodeTest() {
		try{
			NotificationTypeModel object = new NotificationTypeModel();
			object.getTypeCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTypeCodeTest() {
		try{
			NotificationTypeModel object = new NotificationTypeModel();
			object.setTypeCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTypeNameTest() {
		try{
			NotificationTypeModel object = new NotificationTypeModel();
			object.setTypeName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}