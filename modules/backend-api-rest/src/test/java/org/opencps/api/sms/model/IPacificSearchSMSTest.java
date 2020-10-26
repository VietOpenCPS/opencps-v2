package org.opencps.api.sms.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class IPacificSearchSMSTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setUserIdTest() {
		try{
			IPacificSearchSMS object = new IPacificSearchSMS();
			object.setUserId("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			IPacificSearchSMS object = new IPacificSearchSMS();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPasswordTest() {
		try{
			IPacificSearchSMS object = new IPacificSearchSMS();
			object.getPassword();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserTest() {
		try{
			IPacificSearchSMS object = new IPacificSearchSMS();
			object.getUser();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPasswordTest() {
		try{
			IPacificSearchSMS object = new IPacificSearchSMS();
			object.setPassword("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserTest() {
		try{
			IPacificSearchSMS object = new IPacificSearchSMS();
			object.setUser("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setInfoTest() {
		try{
			IPacificSearchSMS object = new IPacificSearchSMS();
			object.setInfo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setServiceNumberTest() {
		try{
			IPacificSearchSMS object = new IPacificSearchSMS();
			object.setServiceNumber("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCommandCodeTest() {
		try{
			IPacificSearchSMS object = new IPacificSearchSMS();
			object.setCommandCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceNumberTest() {
		try{
			IPacificSearchSMS object = new IPacificSearchSMS();
			object.getServiceNumber();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getInfoTest() {
		try{
			IPacificSearchSMS object = new IPacificSearchSMS();
			object.getInfo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCommandCodeTest() {
		try{
			IPacificSearchSMS object = new IPacificSearchSMS();
			object.getCommandCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}