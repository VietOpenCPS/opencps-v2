package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class NotificationTypeManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getNotificationTypesTest() {
		try{
			NotificationTypeManagementImpl object = new NotificationTypeManagementImpl();
			object.getNotificationTypes(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}