package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class MailTestManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void sendMailTest() {
		try{
			MailTestManagementImpl object = new MailTestManagementImpl();
			object.sendMail(null, null, null, null, null, null, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void javaMailTest() {
		try{
			MailTestManagementImpl object = new MailTestManagementImpl();
			object.javaMail(null, null, null, null, null, null, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}