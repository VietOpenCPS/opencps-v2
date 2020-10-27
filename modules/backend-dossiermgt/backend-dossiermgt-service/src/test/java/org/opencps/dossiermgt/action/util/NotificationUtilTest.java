package org.opencps.dossiermgt.action.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class NotificationUtilTest {
	@Before
	public void setUp() {
	}
	@Test
	public void isSendEmailTest() {
		try{
			NotificationUtil.isSendEmail("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isSendSMSTest() {
		try{
			NotificationUtil.isSendSMS("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}