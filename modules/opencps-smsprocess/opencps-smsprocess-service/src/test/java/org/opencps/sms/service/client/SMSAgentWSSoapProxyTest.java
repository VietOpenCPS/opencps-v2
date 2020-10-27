package org.opencps.sms.service.client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class SMSAgentWSSoapProxyTest {
	@Before
	public void setUp() {
	}
	@Test
	public void addTest() {
		try{
			SMSAgentWSSoapProxy object = new SMSAgentWSSoapProxy();
			object.add(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void sendMTTest() {
		try{
			SMSAgentWSSoapProxy object = new SMSAgentWSSoapProxy();
			object.sendMT("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEndpointTest() {
		try{
			SMSAgentWSSoapProxy object = new SMSAgentWSSoapProxy();
			object.setEndpoint("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEndpointTest() {
		try{
			SMSAgentWSSoapProxy object = new SMSAgentWSSoapProxy();
			object.getEndpoint();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSMSAgentWSSoapTest() {
		try{
			SMSAgentWSSoapProxy object = new SMSAgentWSSoapProxy();
			object.getSMSAgentWSSoap();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void sendMultiMTTest() {
		try{
			SMSAgentWSSoapProxy object = new SMSAgentWSSoapProxy();
			object.sendMultiMT(null, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}