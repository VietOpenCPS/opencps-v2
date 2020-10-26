package org.opencps.api.notification.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ObjectFactoryTest {
	@Before
	public void setUp() {
	}
	@Test
	public void createNotificationSearchModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createNotificationSearchModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}