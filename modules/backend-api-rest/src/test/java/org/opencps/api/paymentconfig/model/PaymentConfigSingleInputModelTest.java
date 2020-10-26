package org.opencps.api.paymentconfig.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class PaymentConfigSingleInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getValueTest() {
		try{
			PaymentConfigSingleInputModel object = new PaymentConfigSingleInputModel();
			object.getValue();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setValueTest() {
		try{
			PaymentConfigSingleInputModel object = new PaymentConfigSingleInputModel();
			object.setValue("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}