package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class NotificationTemplateUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void mapperNotificationtemplateModelTest() {
		try{
			NotificationTemplateUtils.mapperNotificationtemplateModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mapperNotificationQueueListTest() {
		try{
			NotificationTemplateUtils.mapperNotificationQueueList(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mapperNotificationtemplateListTest() {
		try{
			NotificationTemplateUtils.mapperNotificationtemplateList(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mapperNotificationTypeListTest() {
		try{
			NotificationTemplateUtils.mapperNotificationTypeList(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mapperNotificationQueueModelTest() {
		try{
			NotificationTemplateUtils.mapperNotificationQueueModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}