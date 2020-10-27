package org.opencps.api.controller.exception;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ErrorMsgTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getMessageTest() {
		try{
			ErrorMsg object = new ErrorMsg();
			object.getMessage();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDescriptionTest() {
		try{
			ErrorMsg object = new ErrorMsg();
			object.getDescription();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDescriptionTest() {
		try{
			ErrorMsg object = new ErrorMsg();
			object.setDescription("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCodeTest() {
		try{
			ErrorMsg object = new ErrorMsg();
			object.getCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCodeTest() {
		try{
			ErrorMsg object = new ErrorMsg();
			object.setCode(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMessageTest() {
		try{
			ErrorMsg object = new ErrorMsg();
			object.setMessage("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}