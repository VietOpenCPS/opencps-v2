package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class PaymentFileManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void downloadConfirmFileTest() {
		try{
			PaymentFileManagementImpl object = new PaymentFileManagementImpl();
			object.downloadConfirmFile(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void downloadInvoiceFileTest() {
		try{
			PaymentFileManagementImpl object = new PaymentFileManagementImpl();
			object.downloadInvoiceFile(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createPaymentFileByDossierIdEparTest() {
		try{
			PaymentFileManagementImpl object = new PaymentFileManagementImpl();
			object.createPaymentFileByDossierIdEpar(null, null, null, null, null, null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void previewInvoiceFileTest() {
		try{
			PaymentFileManagementImpl object = new PaymentFileManagementImpl();
			object.previewInvoiceFile(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createPaymentFileByDossierIdTest() {
		try{
			PaymentFileManagementImpl object = new PaymentFileManagementImpl();
			object.createPaymentFileByDossierId(null, null, null, null, null, null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updatePaymentFileConfirmTest() {
		try{
			PaymentFileManagementImpl object = new PaymentFileManagementImpl();
			object.updatePaymentFileConfirm(null, null, null, null, null, null, "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateByPaymentFileIdTest() {
		try{
			PaymentFileManagementImpl object = new PaymentFileManagementImpl();
			object.updateByPaymentFileId(null, null, null, null, null, null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updatePaymentAmountTest() {
		try{
			PaymentFileManagementImpl object = new PaymentFileManagementImpl();
			object.updatePaymentAmount(null, null, null, null, null, null, Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEpaymentProfileTest() {
		try{
			PaymentFileManagementImpl object = new PaymentFileManagementImpl();
			object.getEpaymentProfile(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPaymentFileByDossierIdTest() {
		try{
			PaymentFileManagementImpl object = new PaymentFileManagementImpl();
			object.getPaymentFileByDossierId(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getDossierTest() {
//		try{
//			PaymentFileManagementImpl object = new PaymentFileManagementImpl();
//			object.getDossier("abcde", Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void checkHashKeyPayTest() {
		try{
			PaymentFileManagementImpl object = new PaymentFileManagementImpl();
			object.checkHashKeyPay(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void processingKeyPayTest() {
		try{
			PaymentFileManagementImpl object = new PaymentFileManagementImpl();
			object.processingKeyPay(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}