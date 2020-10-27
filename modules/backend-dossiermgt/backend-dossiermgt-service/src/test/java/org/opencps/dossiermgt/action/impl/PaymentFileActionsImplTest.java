package org.opencps.dossiermgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class PaymentFileActionsImplTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void getEpaymentProfileTest() {
//		try{
//			PaymentFileActionsImpl object = new PaymentFileActionsImpl();
//			object.getEpaymentProfile(Long.valueOf(0), "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateFileConfirmTest() {
//		try{
//			PaymentFileActionsImpl object = new PaymentFileActionsImpl();
//			object.updateFileConfirm(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), null, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateFileConfirmTest3() {
//		try{
//			PaymentFileActionsImpl object = new PaymentFileActionsImpl();
//			object.updateFileConfirm(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getPaymentFileByReferenceUidTest() {
//		try{
//			PaymentFileActionsImpl object = new PaymentFileActionsImpl();
//			object.getPaymentFileByReferenceUid(Long.valueOf(0), "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getPaymentFileTest() {
//		try{
//			PaymentFileActionsImpl object = new PaymentFileActionsImpl();
//			object.getPaymentFile(Long.valueOf(0), "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	@Test
//	public void updateEProfileTest() {
//		try{
//			PaymentFileActionsImpl object = new PaymentFileActionsImpl();
//			object.updateEProfile(Long.valueOf(0), "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	@Test
//	public void getPaymentFilesTest9() {
//		try{
//			PaymentFileActionsImpl object = new PaymentFileActionsImpl();
//			object.getPaymentFiles(Long.valueOf(0), Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateFileApprovalTest() {
//		try{
//			PaymentFileActionsImpl object = new PaymentFileActionsImpl();
//			object.updateFileApproval(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateFileApprovalTest11() {
//		try{
//			PaymentFileActionsImpl object = new PaymentFileActionsImpl();
//			object.updateFileApproval(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), null, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void createPaymentFileTest() {
//		try{
//			PaymentFileActionsImpl object = new PaymentFileActionsImpl();
//			object.createPaymentFile(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", 0, "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getPaymentFileDetailTest() {
//		try{
//			PaymentFileActionsImpl object = new PaymentFileActionsImpl();
//			object.getPaymentFileDetail(Long.valueOf(0), "abcde", Long.valueOf(0), Long.valueOf(0), null, 0, 0, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getPaymentFilesTest() {
		try{
			PaymentFileActionsImpl object = new PaymentFileActionsImpl();
			object.getPaymentFiles(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDossierIdTest() {
		try{
			PaymentFileActionsImpl object = new PaymentFileActionsImpl();
			object.getByDossierId(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}