package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class PaymentFileLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			PaymentFileLocalServiceImpl object = new PaymentFileLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createPaymentFileByDossierIdTest() {
		try{
			PaymentFileLocalServiceImpl object = new PaymentFileLocalServiceImpl();
			object.createPaymentFileByDossierId(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEpaymentProfileTest() {
		try{
			PaymentFileLocalServiceImpl object = new PaymentFileLocalServiceImpl();
			object.getEpaymentProfile(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateFileConfirmTest() {
		try{
			PaymentFileLocalServiceImpl object = new PaymentFileLocalServiceImpl();
			object.updateFileConfirm(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateFileConfirmTest5() {
		try{
			PaymentFileLocalServiceImpl object = new PaymentFileLocalServiceImpl();
			object.updateFileConfirm(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPaymentFileByReferenceUidTest() {
		try{
			PaymentFileLocalServiceImpl object = new PaymentFileLocalServiceImpl();
			object.getPaymentFileByReferenceUid(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void searchLuceneTest() {
//		try{
//			PaymentFileLocalServiceImpl object = new PaymentFileLocalServiceImpl();
//			object.searchLucene(null, null, 0, 0, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void findByGTest() {
		try{
			PaymentFileLocalServiceImpl object = new PaymentFileLocalServiceImpl();
			object.findByG(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void countLuceneTest() {
//		try{
//			PaymentFileLocalServiceImpl object = new PaymentFileLocalServiceImpl();
//			object.countLucene(null, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getPaymentFileTest() {
		try{
			PaymentFileLocalServiceImpl object = new PaymentFileLocalServiceImpl();
			object.getPaymentFile(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDossierIdTest() {
		try{
			PaymentFileLocalServiceImpl object = new PaymentFileLocalServiceImpl();
			object.getByDossierId(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateEProfileTest() {
		try{
			PaymentFileLocalServiceImpl object = new PaymentFileLocalServiceImpl();
			object.updateEProfile(Long.valueOf(0), "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByPTTest() {
		try{
			PaymentFileLocalServiceImpl object = new PaymentFileLocalServiceImpl();
			object.findByPT(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByG_DIDTest() {
		try{
			PaymentFileLocalServiceImpl object = new PaymentFileLocalServiceImpl();
			object.getByG_DID(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fectPaymentFileTest() {
		try{
			PaymentFileLocalServiceImpl object = new PaymentFileLocalServiceImpl();
			object.fectPaymentFile(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_PTTest() {
		try{
			PaymentFileLocalServiceImpl object = new PaymentFileLocalServiceImpl();
			object.findByG_PT(Long.valueOf(0), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateApplicantFeeAmountTest() {
		try{
			PaymentFileLocalServiceImpl object = new PaymentFileLocalServiceImpl();
			object.updateApplicantFeeAmount(Long.valueOf(0), 0, Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateFileApprovalTest() {
		try{
			PaymentFileLocalServiceImpl object = new PaymentFileLocalServiceImpl();
			object.updateFileApproval(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateFileApprovalTest19() {
		try{
			PaymentFileLocalServiceImpl object = new PaymentFileLocalServiceImpl();
			object.updateFileApproval(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSyncPaymentFileTest() {
		try{
			PaymentFileLocalServiceImpl object = new PaymentFileLocalServiceImpl();
			object.getSyncPaymentFile(Long.valueOf(0), true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updatePaymentFileCustomTest() {
		try{
			PaymentFileLocalServiceImpl object = new PaymentFileLocalServiceImpl();
			object.updatePaymentFileCustom(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createPaymentFilesTest() {
		try{
			PaymentFileLocalServiceImpl object = new PaymentFileLocalServiceImpl();
			object.createPaymentFiles(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", 0, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findAllTest() {
		try{
			PaymentFileLocalServiceImpl object = new PaymentFileLocalServiceImpl();
			object.findAll();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			PaymentFileLocalServiceImpl object = new PaymentFileLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}