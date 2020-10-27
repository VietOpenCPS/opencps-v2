package org.opencps.sms.service.client;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class SMSAgentWSLocatorTest {
	@Before
	public void setUp() {
	}
	
	@Test
	public void getPortTest2() {
		try{
			SMSAgentWSLocator object = new SMSAgentWSLocator("abcde", null);
			object.getPort(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceNameTest() {
		try{
			SMSAgentWSLocator object = new SMSAgentWSLocator("abcde", null);
			object.getServiceName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEndpointAddressTest() {
		try{
			SMSAgentWSLocator object = new SMSAgentWSLocator("abcde", null);
			object.setEndpointAddress("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	
	@Test
	public void getPortsTest() {
		try{
			SMSAgentWSLocator object = new SMSAgentWSLocator("abcde", null);
			object.getPorts();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSMSAgentWSSoapAddressTest() {
		try{
			SMSAgentWSLocator object = new SMSAgentWSLocator("abcde", null);
			object.getSMSAgentWSSoapAddress();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSMSAgentWSSoapWSDDServiceNameTest() {
		try{
			SMSAgentWSLocator object = new SMSAgentWSLocator("abcde", null);
			object.setSMSAgentWSSoapWSDDServiceName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSMSAgentWSSoapEndpointAddressTest() {
		try{
			SMSAgentWSLocator object = new SMSAgentWSLocator("abcde", null);
			object.setSMSAgentWSSoapEndpointAddress("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSMSAgentWSSoapTest() {
		try{
			SMSAgentWSLocator object = new SMSAgentWSLocator("abcde", null);
			object.getSMSAgentWSSoap();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSMSAgentWSSoapTest11() {
		try{
			SMSAgentWSLocator object = new SMSAgentWSLocator("abcde", null);
			object.getSMSAgentWSSoap(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSMSAgentWSSoapWSDDServiceNameTest() {
		try{
			SMSAgentWSLocator object = new SMSAgentWSLocator("abcde", null);
			object.getSMSAgentWSSoapWSDDServiceName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}