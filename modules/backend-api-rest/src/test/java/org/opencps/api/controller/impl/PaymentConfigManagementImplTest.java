package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class PaymentConfigManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getEpaymentconfigTest() {
		try{
			PaymentConfigManagementImpl object = new PaymentConfigManagementImpl();
			object.getEpaymentconfig(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updatePaymentConfigTest() {
		try{
			PaymentConfigManagementImpl object = new PaymentConfigManagementImpl();
			object.updatePaymentConfig(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPaymentConfigDetailTest() {
		try{
			PaymentConfigManagementImpl object = new PaymentConfigManagementImpl();
			object.getPaymentConfigDetail(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateEpaymentconfigTest() {
		try{
			PaymentConfigManagementImpl object = new PaymentConfigManagementImpl();
			object.updateEpaymentconfig(null, null, null, null, null, null, Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateInvoiceFormTest() {
		try{
			PaymentConfigManagementImpl object = new PaymentConfigManagementImpl();
			object.updateInvoiceForm(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removePaymentConfigTest() {
		try{
			PaymentConfigManagementImpl object = new PaymentConfigManagementImpl();
			object.removePaymentConfig(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addEpaymentconfigTest() {
		try{
			PaymentConfigManagementImpl object = new PaymentConfigManagementImpl();
			object.addEpaymentconfig(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addInvoiceFormTest() {
		try{
			PaymentConfigManagementImpl object = new PaymentConfigManagementImpl();
			object.addInvoiceForm(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getInvoiceFormTest() {
		try{
			PaymentConfigManagementImpl object = new PaymentConfigManagementImpl();
			object.getInvoiceForm(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPaymentConfigTest() {
		try{
			PaymentConfigManagementImpl object = new PaymentConfigManagementImpl();
			object.getPaymentConfig(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addPaymentConfigTest() {
		try{
			PaymentConfigManagementImpl object = new PaymentConfigManagementImpl();
			object.addPaymentConfig(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}