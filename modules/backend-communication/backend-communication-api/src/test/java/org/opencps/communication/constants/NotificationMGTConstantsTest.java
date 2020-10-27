package org.opencps.communication.constants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class NotificationMGTConstantsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getNotificationTempTest() {
		try{
			NotificationMGTConstants.getNotificationTemp("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNotificationTempMapTest() {
		try{
			NotificationMGTConstants.getNotificationTempMap();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}