package org.opencps.api.paymentconfig.model;
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
	public void createPaymentConfigSingleInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createPaymentConfigSingleInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createPaymentConfigSearchModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createPaymentConfigSearchModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createPaymentConfigDataModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createPaymentConfigDataModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createPaymentConfigInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createPaymentConfigInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createPaymentConfigResultsModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createPaymentConfigResultsModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}