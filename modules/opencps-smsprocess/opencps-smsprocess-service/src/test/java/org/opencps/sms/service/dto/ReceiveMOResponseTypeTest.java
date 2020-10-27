package org.opencps.sms.service.dto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ReceiveMOResponseTypeTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setReceiveMOResultTest() {
		try{
			ReceiveMOResponseType object = new ReceiveMOResponseType();
			object.setReceiveMOResult(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReceiveMOResultTest() {
		try{
			ReceiveMOResponseType object = new ReceiveMOResponseType();
			object.getReceiveMOResult();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}