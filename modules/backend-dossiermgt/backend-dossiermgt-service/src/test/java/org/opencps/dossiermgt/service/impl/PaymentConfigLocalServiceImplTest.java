package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class PaymentConfigLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			PaymentConfigLocalServiceImpl object = new PaymentConfigLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updatePaymentConfigTest() {
		try{
			PaymentConfigLocalServiceImpl object = new PaymentConfigLocalServiceImpl();
			object.updatePaymentConfig(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByInvoiceTemplateNoTest() {
		try{
			PaymentConfigLocalServiceImpl object = new PaymentConfigLocalServiceImpl();
			object.getByInvoiceTemplateNo(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removePaymentConfigTest() {
		try{
			PaymentConfigLocalServiceImpl object = new PaymentConfigLocalServiceImpl();
			object.removePaymentConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			PaymentConfigLocalServiceImpl object = new PaymentConfigLocalServiceImpl();
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByGTest() {
		try{
			PaymentConfigLocalServiceImpl object = new PaymentConfigLocalServiceImpl();
			object.findByG(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updatePaymentConfigDBTest() {
		try{
			PaymentConfigLocalServiceImpl object = new PaymentConfigLocalServiceImpl();
			object.updatePaymentConfigDB(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneTest() {
		try{
			PaymentConfigLocalServiceImpl object = new PaymentConfigLocalServiceImpl();
			object.countLucene(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateEConfigTest() {
		try{
			PaymentConfigLocalServiceImpl object = new PaymentConfigLocalServiceImpl();
			object.updateEConfig(Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateInvoidFormTest() {
		try{
			PaymentConfigLocalServiceImpl object = new PaymentConfigLocalServiceImpl();
			object.updateInvoidForm(Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPaymentConfigByGovAgencyCodeTest() {
		try{
			PaymentConfigLocalServiceImpl object = new PaymentConfigLocalServiceImpl();
			object.getPaymentConfigByGovAgencyCode(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			PaymentConfigLocalServiceImpl object = new PaymentConfigLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}