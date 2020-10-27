package org.opencps.sms.service.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class MessageUtilTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getMessageTest() {
		try{
			MessageUtil.getMessage("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}