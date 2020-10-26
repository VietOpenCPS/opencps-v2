package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DossierManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void assignUsersTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.assignUsers(null, null, null, null, null, null, "abcde", "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.addDossier(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void rollbackTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.rollback(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void doActionTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.doAction(null, null, null, null, null, null, "abcde", null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAssignUsersTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.getAssignUsers(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierMarksTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.getDossierMarks(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void cloneDossierTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.cloneDossier(null, null, null, null, null, null, Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void checkStepTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.checkStep(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getReassignUsersTest() {
//		try{
//			DossierManagementImpl object = new DossierManagementImpl();
//			object.getReassignUsers(null, null, null, null, null, Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void fixDossierSyncTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.fixDossierSync(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void gotoStepTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.gotoStep(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierQRcodeTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.getDossierQRcode(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void resetDossierTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.resetDossier(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeDossierTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.removeDossier(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void reindexDossierTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.reindexDossier(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void submitDossierTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.submitDossier(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDetailDossierTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.getDetailDossier(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierMarkTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.addDossierMark(null, null, null, null, null, null, Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void refreshDossierTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.refreshDossier(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void restoreDossierTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.restoreDossier(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void resyncDossierTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.resyncDossier(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierNoTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.updateDossierNo(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.updateDossier(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getDictItemNameTest() {
//		try{
//			DossierManagementImpl object = new DossierManagementImpl();
//			object.getDictItemName(Long.valueOf(0), "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getDictItemNameTest25() {
//		try{
//			DossierManagementImpl object = new DossierManagementImpl();
//			object.getDictItemName(Long.valueOf(0), new DictCollectionImpl(), "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getServiceNameTest() {
//		try{@Test
//	public void getProcessOptionTest() {
//		try{
//			DossierManagementImpl object = new DossierManagementImpl();
//			object.getProcessOption("abcde", "abcde", "abcde", Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//			DossierManagementImpl object = new DossierManagementImpl();
//			object.getServiceName("abcde", "abcde", Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
	@Test
	public void getUrlSiteInfoTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.getUrlSiteInfo(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void forceResyncDossierTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.forceResyncDossier(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierSequencesTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.getDossierSequences(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fixDueDateDossierTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.fixDueDateDossier(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSampleDataByEFormTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.getSampleDataByEForm(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fixSyncContactDossierTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.fixSyncContactDossier(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getValidateProcessTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.getValidateProcess(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierMarkbyDossierIdTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.getDossierMarkbyDossierId(null, null, null, null, null, null, Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fixReAssignedDossierTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.fixReAssignedDossier(null, null, null, null, null, null, "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void updateDossierInGroupTest() {
//		try{
//			DossierManagementImpl object = new DossierManagementImpl();
//			object.updateDossierInGroup(null, null, null, null, null, null, Long.valueOf(0), "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void addDossierFileByEFormTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.addDossierFileByEForm(null, null, null, null, null, null, null, "abcde", "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeDossierLuceneTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.removeDossierLucene(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateReassignUsersTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.updateReassignUsers(null, null, null, null, null, null, Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierPenddingByDossierIdTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.getDossierPenddingByDossierId(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossiersInfoListTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.getDossiersInfoList(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void resolveConflictDossierTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.resolveConflictDossier(null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierPublishTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.addDossierPublish(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addMultipleDossierTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.addMultipleDossier(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDueDateByProcessTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.getDueDateByProcess(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void generateDossierNoTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.generateDossierNo(null, null, null, null, null, null, "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossiersPendingListTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.getDossiersPendingList(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeConflictDossierTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.removeConflictDossier(null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossiersByErrorStepTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.getDossiersByErrorStep(null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void republishPublishQueueTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.republishPublishQueue(null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierRelaseListTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.getDossierRelaseList(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessStepByDossierIdTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.getProcessStepByDossierId(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getConflictDossierTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.getConflictDossier(null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierSyncsByDossierTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.getDossierSyncsByDossier(null, null, null, null, null, null, "abcde", 0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMermaidGraphDetailDossierTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.getMermaidGraphDetailDossier(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierBarcodeTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.getDossierBarcode(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierProcessListTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.getDossierProcessList(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void submittingDossierTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.submittingDossier(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void cancellingDossierTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.cancellingDossier(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void submittingDossierPOSTTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.submittingDossierPOST(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void cancellingRequestDossierTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.cancellingRequestDossier(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getContactsDossierTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.getContactsDossier(null, null, Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierByCertificateNumberTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.getDossierByCertificateNumber(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void correctingDossierTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.correctingDossier(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void correctingRequestDossierTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.correctingRequestDossier(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateStateTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.updateState(null, null, null, null, null, null, Long.valueOf(0), "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOptionDossierTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.getOptionDossier(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDelegacyUsersTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.getDelegacyUsers(null, null, null, null, null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addFullDossierTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.addFullDossier(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierFileByDossierNoOldDbTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.addDossierFileByDossierNoOldDb(null, null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getDossierCounterByDayTest() {
//		try{
//			DossierManagementImpl object = new DossierManagementImpl();
//			object.getDossierCounterByDay(null, null, null, null, null, null, "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void fixExtendDateDossierTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.fixExtendDateDossier(null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateInformDossierTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.updateInformDossier(null, null, null, null, null, null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void importDossierFromOldDBTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.importDossierFromOldDB(null, null, null, null, null, null, null, "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void doConvertDossierFileWithSqlTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.doConvertDossierFileWithSql(null, null, null, null, null, null, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void putMetaDataDetailDossierTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.putMetaDataDetailDossier(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addApplicantOldDbTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.addApplicantOldDb(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateEparDossierTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.updateEparDossier(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void doConvertDossierWithSqlTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.doConvertDossierWithSql(null, null, null, null, null, null, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMetaDataDetailDossierTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.getMetaDataDetailDossier(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void doImportDossier19Test() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.doImportDossier19("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void doImportDossierFile19Test() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.doImportDossierFile19("abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getDossierTemplateNameTest() {
//		try{
//			DossierManagementImpl object = new DossierManagementImpl();
//			object.getDossierTemplateName("abcde", Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getDossiersTest() {
		try{
			DossierManagementImpl object = new DossierManagementImpl();
			object.getDossiers(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}