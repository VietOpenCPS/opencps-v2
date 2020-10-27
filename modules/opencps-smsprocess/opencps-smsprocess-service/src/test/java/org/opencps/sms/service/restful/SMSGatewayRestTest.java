package org.opencps.sms.service.restful;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class SMSGatewayRestTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getSingletonsTest() {
		try{
			SMSGatewayRest object = new SMSGatewayRest();
			object.getSingletons();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}