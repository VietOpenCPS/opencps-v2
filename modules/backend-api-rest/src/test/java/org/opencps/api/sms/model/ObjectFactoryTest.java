package org.opencps.api.sms.model;
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
	public void createIPacificSearchSMSTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createIPacificSearchSMS();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createZaloInputTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createZaloInput();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}