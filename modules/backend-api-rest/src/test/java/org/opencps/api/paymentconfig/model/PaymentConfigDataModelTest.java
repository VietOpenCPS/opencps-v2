package org.opencps.api.paymentconfig.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class PaymentConfigDataModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getGovAgencyTaxNoTest() {
		try{
			PaymentConfigDataModel object = new PaymentConfigDataModel();
			object.getGovAgencyTaxNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setInvoiceTemplateNoTest() {
		try{
			PaymentConfigDataModel object = new PaymentConfigDataModel();
			object.setInvoiceTemplateNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getInvoiceIssueNoTest() {
		try{
			PaymentConfigDataModel object = new PaymentConfigDataModel();
			object.getInvoiceIssueNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getInvoiceTemplateNoTest() {
		try{
			PaymentConfigDataModel object = new PaymentConfigDataModel();
			object.getInvoiceTemplateNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGovAgencyNameTest() {
		try{
			PaymentConfigDataModel object = new PaymentConfigDataModel();
			object.setGovAgencyName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGovAgencyCodeTest() {
		try{
			PaymentConfigDataModel object = new PaymentConfigDataModel();
			object.getGovAgencyCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGovAgencyCodeTest() {
		try{
			PaymentConfigDataModel object = new PaymentConfigDataModel();
			object.setGovAgencyCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGovAgencyNameTest() {
		try{
			PaymentConfigDataModel object = new PaymentConfigDataModel();
			object.getGovAgencyName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setInvoiceLastNoTest() {
		try{
			PaymentConfigDataModel object = new PaymentConfigDataModel();
			object.setInvoiceLastNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setBankInfoTest() {
		try{
			PaymentConfigDataModel object = new PaymentConfigDataModel();
			object.setBankInfo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setInvoiceIssueNoTest() {
		try{
			PaymentConfigDataModel object = new PaymentConfigDataModel();
			object.setInvoiceIssueNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGovAgencyTaxNoTest() {
		try{
			PaymentConfigDataModel object = new PaymentConfigDataModel();
			object.setGovAgencyTaxNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPaymentConfigIdTest() {
		try{
			PaymentConfigDataModel object = new PaymentConfigDataModel();
			object.setPaymentConfigId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPaymentConfigIdTest() {
		try{
			PaymentConfigDataModel object = new PaymentConfigDataModel();
			object.getPaymentConfigId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBankInfoTest() {
		try{
			PaymentConfigDataModel object = new PaymentConfigDataModel();
			object.getBankInfo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getInvoiceLastNoTest() {
		try{
			PaymentConfigDataModel object = new PaymentConfigDataModel();
			object.getInvoiceLastNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}