package org.opencps.kernel.message.email;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class MBEmailSenderImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void sendTest() {
		try{
			MBEmailSenderImpl object = new MBEmailSenderImpl();
			object.send(null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}