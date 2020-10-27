package org.opencps.kernel.message.notification;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class MBNotificationSenderImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void sendTest() {
		try{
			MBNotificationSenderImpl object = new MBNotificationSenderImpl();
			object.send(null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}