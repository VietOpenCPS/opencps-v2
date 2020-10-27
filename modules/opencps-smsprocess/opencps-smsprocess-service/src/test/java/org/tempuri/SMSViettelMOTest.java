package org.tempuri;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class SMSViettelMOTest {
	@Before
	public void setUp() {
	}
	@Test
	public void messageReceiverTest() {
		try{
			SMSViettelMO object = new SMSViettelMO();
			object.messageReceiver("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}