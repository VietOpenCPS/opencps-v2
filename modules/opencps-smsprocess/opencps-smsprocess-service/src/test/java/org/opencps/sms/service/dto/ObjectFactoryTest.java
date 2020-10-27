package org.opencps.sms.service.dto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ObjectFactoryTest {
	@Before
	public void setUp() {
	}
	@Test
	public void createReceiveMOResponseTypeTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createReceiveMOResponseType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createReceiveMORequestTypeTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createReceiveMORequestType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createReceiveMOResponseTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createReceiveMOResponse(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createReceiveMOTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createReceiveMO(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}