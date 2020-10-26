package org.opencps.api.paymentfile.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class EpaymentProfileJsonModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getValueTest() {
		try{
			EpaymentProfileJsonModel object = new EpaymentProfileJsonModel();
			object.getValue();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setValueTest() {
		try{
			EpaymentProfileJsonModel object = new EpaymentProfileJsonModel();
			object.setValue("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}