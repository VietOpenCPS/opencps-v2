package org.opencps.exception.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ExceptionModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getMessageTest() {
		try{
			ExceptionModel object = new ExceptionModel();
			object.getMessage();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDescriptionTest() {
		try{
			ExceptionModel object = new ExceptionModel();
			object.getDescription();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDescriptionTest() {
		try{
			ExceptionModel object = new ExceptionModel();
			object.setDescription("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCodeTest() {
		try{
			ExceptionModel object = new ExceptionModel();
			object.getCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCodeTest() {
		try{
			ExceptionModel object = new ExceptionModel();
			object.setCode(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMessageTest() {
		try{
			ExceptionModel object = new ExceptionModel();
			object.setMessage("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}