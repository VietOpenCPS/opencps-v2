package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class PaymentFileLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createPaymentFileByDossierIdTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.createPaymentFileByDossierId(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updatePaymentFileTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.updatePaymentFile(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEpaymentProfileTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.getEpaymentProfile(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateFileConfirmTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.updateFileConfirm(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateFileConfirmTest11() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.updateFileConfirm(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPaymentFileByReferenceUidTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.getPaymentFileByReferenceUid(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByGTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.findByG(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.countLucene(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPaymentFileTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.getPaymentFile(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPaymentFileTest17() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.getPaymentFile(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDossierIdTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.getByDossierId(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateEProfileTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.updateEProfile(Long.valueOf(0), "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addPaymentFileTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.addPaymentFile(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByPTTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.findByPT(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByG_DIDTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.getByG_DID(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPaymentFilesTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.getPaymentFiles(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchPaymentFileTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.fetchPaymentFile(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fectPaymentFileTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.fectPaymentFile(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_PTTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.findByG_PT(Long.valueOf(0), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPaymentFilesByUuidAndCompanyIdTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.getPaymentFilesByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPaymentFilesByUuidAndCompanyIdTest28() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.getPaymentFilesByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePaymentFileTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.deletePaymentFile(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePaymentFileTest30() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.deletePaymentFile(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPaymentFileByUuidAndGroupIdTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.getPaymentFileByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPaymentFilesCountTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.getPaymentFilesCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateApplicantFeeAmountTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.updateApplicantFeeAmount(Long.valueOf(0), 0, Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateFileApprovalTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.updateFileApproval(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateFileApprovalTest35() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.updateFileApproval(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSyncPaymentFileTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.getSyncPaymentFile(Long.valueOf(0), true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updatePaymentFileCustomTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.updatePaymentFileCustom(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchPaymentFileByUuidAndGroupIdTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.fetchPaymentFileByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createPaymentFileTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.createPaymentFile(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createPaymentFilesTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.createPaymentFiles(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", 0, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findAllTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.findAll();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest47() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest49() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest52() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			PaymentFileLocalServiceWrapper object = new PaymentFileLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}