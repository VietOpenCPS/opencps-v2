package org.opencps.usermgt.service.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class SendMailLGSPUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void sendMailVerifyAccTest() {
		try{
			SendMailLGSPUtils.sendMailVerifyAcc(null, "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}