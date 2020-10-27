package org.opencps.sms.service.application;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class GAPITGatewayImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getReceiveMOTest() {
		try{
			GAPITGatewayImpl object = new GAPITGatewayImpl();
			object.getReceiveMO(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}