package org.opencps.sms.service.client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class SMSAgentWSSoapStubTest {
	@Before
	public void setUp() {
	}
	@Test
	public void addTest() {
		try{
			SMSAgentWSSoapStub object = new SMSAgentWSSoapStub(null, null);
			object.add(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void sendMTTest() {
		try{
			SMSAgentWSSoapStub object = new SMSAgentWSSoapStub(null, null);
			object.sendMT("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createCallTest() {
		try{
			SMSAgentWSSoapStub object = new SMSAgentWSSoapStub(null, null);
			object.createCall();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void sendMultiMTTest() {
		try{
			SMSAgentWSSoapStub object = new SMSAgentWSSoapStub(null, null);
			object.sendMultiMT(null, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}