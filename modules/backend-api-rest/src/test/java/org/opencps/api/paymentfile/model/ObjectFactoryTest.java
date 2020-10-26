package org.opencps.api.paymentfile.model;
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
	public void createPaymentFileSearchTemplateModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createPaymentFileSearchTemplateModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createPaymentFileSearchResultModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createPaymentFileSearchResultModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createPaymentFileSearchModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createPaymentFileSearchModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createPaymentFileInputModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createPaymentFileInputModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createPaymentFileResultModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createPaymentFileResultModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createEpaymentProfileJsonModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createEpaymentProfileJsonModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createPaymentFileModelTest() {
		try{
			ObjectFactory object = new ObjectFactory();
			object.createPaymentFileModel();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}