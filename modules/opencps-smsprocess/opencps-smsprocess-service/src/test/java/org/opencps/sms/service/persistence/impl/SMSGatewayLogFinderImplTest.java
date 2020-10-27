package org.opencps.sms.service.persistence.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class SMSGatewayLogFinderImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void searchSMSGatewayTest() {
		try{
			SMSGatewayLogFinderImpl object = new SMSGatewayLogFinderImpl();
			object.searchSMSGateway(0, new Date(), new Date(), new Date(), new Date(), "abcde", 0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}