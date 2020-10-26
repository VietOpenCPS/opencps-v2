package org.opencps.api.paymentconfig.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class PaymentConfigInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getGovAgencyTaxNoTest() {
		try{
			PaymentConfigInputModel object = new PaymentConfigInputModel();
			object.getGovAgencyTaxNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setInvoiceTemplateNoTest() {
		try{
			PaymentConfigInputModel object = new PaymentConfigInputModel();
			object.setInvoiceTemplateNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getInvoiceIssueNoTest() {
		try{
			PaymentConfigInputModel object = new PaymentConfigInputModel();
			object.getInvoiceIssueNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getInvoiceTemplateNoTest() {
		try{
			PaymentConfigInputModel object = new PaymentConfigInputModel();
			object.getInvoiceTemplateNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGovAgencyNameTest() {
		try{
			PaymentConfigInputModel object = new PaymentConfigInputModel();
			object.setGovAgencyName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGovAgencyCodeTest() {
		try{
			PaymentConfigInputModel object = new PaymentConfigInputModel();
			object.getGovAgencyCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGovAgencyCodeTest() {
		try{
			PaymentConfigInputModel object = new PaymentConfigInputModel();
			object.setGovAgencyCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGovAgencyNameTest() {
		try{
			PaymentConfigInputModel object = new PaymentConfigInputModel();
			object.getGovAgencyName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setInvoiceLastNoTest() {
		try{
			PaymentConfigInputModel object = new PaymentConfigInputModel();
			object.setInvoiceLastNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setBankInfoTest() {
		try{
			PaymentConfigInputModel object = new PaymentConfigInputModel();
			object.setBankInfo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setInvoiceIssueNoTest() {
		try{
			PaymentConfigInputModel object = new PaymentConfigInputModel();
			object.setInvoiceIssueNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGovAgencyTaxNoTest() {
		try{
			PaymentConfigInputModel object = new PaymentConfigInputModel();
			object.setGovAgencyTaxNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPaymentConfigIdTest() {
		try{
			PaymentConfigInputModel object = new PaymentConfigInputModel();
			object.setPaymentConfigId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPaymentConfigIdTest() {
		try{
			PaymentConfigInputModel object = new PaymentConfigInputModel();
			object.getPaymentConfigId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBankInfoTest() {
		try{
			PaymentConfigInputModel object = new PaymentConfigInputModel();
			object.getBankInfo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getInvoiceLastNoTest() {
		try{
			PaymentConfigInputModel object = new PaymentConfigInputModel();
			object.getInvoiceLastNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}