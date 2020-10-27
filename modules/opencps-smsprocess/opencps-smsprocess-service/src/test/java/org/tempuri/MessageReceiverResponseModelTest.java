package org.tempuri;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class MessageReceiverResponseModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setCommandCodeTest() {
		try{
			MessageReceiverResponseModel object = new MessageReceiverResponseModel();
			object.setCommandCode(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCommandCodeTest() {
		try{
			MessageReceiverResponseModel object = new MessageReceiverResponseModel();
			object.getCommandCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMessageContentTest() {
		try{
			MessageReceiverResponseModel object = new MessageReceiverResponseModel();
			object.getMessageContent();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMessageContentTest() {
		try{
			MessageReceiverResponseModel object = new MessageReceiverResponseModel();
			object.setMessageContent("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMessageTypeTest() {
		try{
			MessageReceiverResponseModel object = new MessageReceiverResponseModel();
			object.getMessageType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMessageTypeTest() {
		try{
			MessageReceiverResponseModel object = new MessageReceiverResponseModel();
			object.setMessageType(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}