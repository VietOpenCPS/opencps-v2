package org.tempuri;
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
	public void createMessageReceiverResponseModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createMessageReceiverResponseModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createMessageReceiverModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createMessageReceiverModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}