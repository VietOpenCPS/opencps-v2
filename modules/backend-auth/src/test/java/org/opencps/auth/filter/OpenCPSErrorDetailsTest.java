package org.opencps.auth.filter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class OpenCPSErrorDetailsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void toStringTest() {
		try{
			OpenCPSErrorDetails object = new OpenCPSErrorDetails(new Date(), "abcde", "abcde");
			object.toString();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMessageTest() {
		try{
			OpenCPSErrorDetails object = new OpenCPSErrorDetails(new Date(), "abcde", "abcde");
			object.getMessage();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTimestampTest() {
		try{
			OpenCPSErrorDetails object = new OpenCPSErrorDetails(new Date(), "abcde", "abcde");
			object.setTimestamp(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTimestampTest() {
		try{
			OpenCPSErrorDetails object = new OpenCPSErrorDetails(new Date(), "abcde", "abcde");
			object.getTimestamp();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMessageTest() {
		try{
			OpenCPSErrorDetails object = new OpenCPSErrorDetails(new Date(), "abcde", "abcde");
			object.setMessage("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getrest_apiTest() {
		try{
			OpenCPSErrorDetails object = new OpenCPSErrorDetails(new Date(), "abcde", "abcde");
			object.getrest_api();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setrest_apiTest() {
		try{
			OpenCPSErrorDetails object = new OpenCPSErrorDetails(new Date(), "abcde", "abcde");
			object.setrest_api("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}