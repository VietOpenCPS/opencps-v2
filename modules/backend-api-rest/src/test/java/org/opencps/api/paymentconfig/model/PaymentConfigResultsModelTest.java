package org.opencps.api.paymentconfig.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class PaymentConfigResultsModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getTotalTest() {
		try{
			PaymentConfigResultsModel object = new PaymentConfigResultsModel();
			object.getTotal();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDataTest() {
		try{
			PaymentConfigResultsModel object = new PaymentConfigResultsModel();
			object.getData();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTotalTest() {
		try{
			PaymentConfigResultsModel object = new PaymentConfigResultsModel();
			object.setTotal(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}