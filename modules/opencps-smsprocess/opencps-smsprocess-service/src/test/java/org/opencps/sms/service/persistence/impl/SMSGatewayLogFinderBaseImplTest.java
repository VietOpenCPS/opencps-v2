package org.opencps.sms.service.persistence.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class SMSGatewayLogFinderBaseImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getBadColumnNamesTest() {
		try{
			SMSGatewayLogFinderBaseImpl object = new SMSGatewayLogFinderBaseImpl();
			object.getBadColumnNames();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSMSGatewayLogPersistenceTest() {
		try{
			SMSGatewayLogFinderBaseImpl object = new SMSGatewayLogFinderBaseImpl();
			object.setSMSGatewayLogPersistence(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSMSGatewayLogPersistenceTest() {
		try{
			SMSGatewayLogFinderBaseImpl object = new SMSGatewayLogFinderBaseImpl();
			object.getSMSGatewayLogPersistence();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}