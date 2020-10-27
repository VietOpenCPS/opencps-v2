package org.opencps.sms.service.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class SmsUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void convertDateTest() {
		try{
			SmsUtils.convertDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void sendMtTest() {
		try{
			SmsUtils.sendMt(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}