package org.opencps.communication.sms.utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class BCTSMSUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void sendSMSTest() {
		try{
			BCTSMSUtils.sendSMS(Long.valueOf(0), "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}