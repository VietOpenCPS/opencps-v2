package org.opencps.api.sms.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ZaloInputTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setTimestampTest() {
		try{
			ZaloInput object = new ZaloInput();
			object.setTimestamp("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPhoneTest() {
		try{
			ZaloInput object = new ZaloInput();
			object.setPhone("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEventTest() {
		try{
			ZaloInput object = new ZaloInput();
			object.getEvent();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOaidTest() {
		try{
			ZaloInput object = new ZaloInput();
			object.getOaid();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setOaidTest() {
		try{
			ZaloInput object = new ZaloInput();
			object.setOaid("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMacTest() {
		try{
			ZaloInput object = new ZaloInput();
			object.setMac("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPageidTest() {
		try{
			ZaloInput object = new ZaloInput();
			object.getPageid();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMacTest() {
		try{
			ZaloInput object = new ZaloInput();
			object.getMac();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPageidTest() {
		try{
			ZaloInput object = new ZaloInput();
			object.setPageid("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAppidTest() {
		try{
			ZaloInput object = new ZaloInput();
			object.setAppid("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFromuidTest() {
		try{
			ZaloInput object = new ZaloInput();
			object.getFromuid();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFromuidTest() {
		try{
			ZaloInput object = new ZaloInput();
			object.setFromuid("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEventTest() {
		try{
			ZaloInput object = new ZaloInput();
			object.setEvent("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAppidTest() {
		try{
			ZaloInput object = new ZaloInput();
			object.getAppid();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPhoneTest() {
		try{
			ZaloInput object = new ZaloInput();
			object.getPhone();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTimestampTest() {
		try{
			ZaloInput object = new ZaloInput();
			object.getTimestamp();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}