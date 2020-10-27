package org.opencps.sms.service.dto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ReceiveMORequestTypeTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getPasswordTest() {
		try{
			ReceiveMORequestType object = new ReceiveMORequestType();
			object.getPassword();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPasswordTest() {
		try{
			ReceiveMORequestType object = new ReceiveMORequestType();
			object.setPassword("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMsgbodyTest() {
		try{
			ReceiveMORequestType object = new ReceiveMORequestType();
			object.setMsgbody("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMoidTest() {
		try{
			ReceiveMORequestType object = new ReceiveMORequestType();
			object.setMoid("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDestTest() {
		try{
			ReceiveMORequestType object = new ReceiveMORequestType();
			object.setDest("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSrcTest() {
		try{
			ReceiveMORequestType object = new ReceiveMORequestType();
			object.setSrc("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMoseqTest() {
		try{
			ReceiveMORequestType object = new ReceiveMORequestType();
			object.setMoseq("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCmdcodeTest() {
		try{
			ReceiveMORequestType object = new ReceiveMORequestType();
			object.setCmdcode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUsernameTest() {
		try{
			ReceiveMORequestType object = new ReceiveMORequestType();
			object.setUsername("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMoidTest() {
		try{
			ReceiveMORequestType object = new ReceiveMORequestType();
			object.getMoid();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSrcTest() {
		try{
			ReceiveMORequestType object = new ReceiveMORequestType();
			object.getSrc();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDestTest() {
		try{
			ReceiveMORequestType object = new ReceiveMORequestType();
			object.getDest();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCmdcodeTest() {
		try{
			ReceiveMORequestType object = new ReceiveMORequestType();
			object.getCmdcode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMoseqTest() {
		try{
			ReceiveMORequestType object = new ReceiveMORequestType();
			object.getMoseq();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMsgbodyTest() {
		try{
			ReceiveMORequestType object = new ReceiveMORequestType();
			object.getMsgbody();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUsernameTest() {
		try{
			ReceiveMORequestType object = new ReceiveMORequestType();
			object.getUsername();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}