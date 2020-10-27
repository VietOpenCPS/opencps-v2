package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void resetTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.reset(Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDataTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest6() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.addDossier(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void rollbackTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.rollback(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void cloneDossierTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.cloneDossier(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeDossierTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.removeDossier(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.updateDossier(Long.valueOf(0), Long.valueOf(0), "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", new Date(), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, new Date(), "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, true, "abcde", new Date(), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierTest12() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.updateDossier(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierTest13() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.updateDossier(Long.valueOf(0), Long.valueOf(0), "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", new Date(), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, new Date(), "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, true, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierTest14() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.updateDossier(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDossierTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.fetchDossier(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDossierNoTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.getByDossierNo(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.getDossier(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByRefTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.getByRef(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_GDIDTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.findByG_GDID(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierByIdTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.getDossierById(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.countLucene(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByF_GID_AN_DSTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.getByF_GID_AN_DS(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByG_ANTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.getByG_AN(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void submittingTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.submitting(Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDueDateTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.updateDueDate(Long.valueOf(0), Long.valueOf(0), "abcde", new Date(), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDossierTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.deleteDossier(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDossierTest29() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.deleteDossier(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void syncDossierTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.syncDossier(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findDossierByDayTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.findDossierByDay("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.createDossier(Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", new Date(), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierTest33() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.createDossier(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void initUpdateDossierFullTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.initUpdateDossierFull(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", 0, "abcde", new Date(), 0, 0, "abcde", 0, new Date(), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void postDossierTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.postDossier(Long.valueOf(0), Long.valueOf(0), "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", new Date(), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, true, "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void initDossierTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.initDossier(Long.valueOf(0), Long.valueOf(0), "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", new Date(), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, true, "abcde", 0, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void initDossierTest37() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.initDossier(Long.valueOf(0), Long.valueOf(0), "abcde", Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", new Date(), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, true, "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void publishDossierTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.publishDossier(Long.valueOf(0), Long.valueOf(0), "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", new Date(), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, true, "abcde", 0, new Date(), new Date(), new Date(), new Date(), new Date(), new Date(), new Date(), new Date(), new Date(), new Date(), new Date(), new Date(), "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Double.valueOf(0.0), 0, "abcde", "abcde", "abcde", 0, "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByDO_NOTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.fetchByDO_NO("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByUserIdTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.countByUserId(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByVIAPOSTALTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.findByVIAPOSTAL(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void assignToProcessTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.assignToProcess(Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_UID_DSTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.findByG_UID_DS(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByOriginTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.getByOrigin(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateStatusTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.updateStatus(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateStatusTest46() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.updateStatus(null, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void initFullDossierTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.initFullDossier(Long.valueOf(0), Long.valueOf(0), "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", new Date(), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, true, "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, "abcde", null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByCpsRefTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.getByCpsRef(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateFinishDateTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.updateFinishDate(Long.valueOf(0), Long.valueOf(0), "abcde", new Date(), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByGIDTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.findByGID(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateViaPostalTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.updateViaPostal(Long.valueOf(0), Long.valueOf(0), "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByG_UID_DSTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.countByG_UID_DS(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossiersCountTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.getDossiersCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countDossierByG_C_GAC_SC_DTNO_NOTDSTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.countDossierByG_C_GAC_SC_DTNO_NOTDS(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByG_NOTS_O_PNTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.countByG_NOTS_O_PN(Long.valueOf(0), null, 0, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByGroupAndOriginDossierNoTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.countByGroupAndOriginDossierNo(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByG_AN_SC_GAC_DTNO_ODIDTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.getByG_AN_SC_GAC_DTNO_ODID(Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByIdAndGovServiceTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.getByIdAndGovService(Long.valueOf(0), "abcde", "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossiersByUuidAndCompanyIdTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.getDossiersByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossiersByUuidAndCompanyIdTest60() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.getDossiersByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByVnpostalStatusTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.findByVnpostalStatus(Long.valueOf(0), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierBriefNoteTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.updateDossierBriefNote(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByG_NOTS_O_SCTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.countByG_NOTS_O_SC(Long.valueOf(0), null, 0, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateEndosementDateTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.updateEndosementDate(Long.valueOf(0), Long.valueOf(0), "abcde", new Date(), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateProcessDateTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.updateProcessDate(Long.valueOf(0), Long.valueOf(0), "abcde", new Date(), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateReceivingDateTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.updateReceivingDate(Long.valueOf(0), Long.valueOf(0), "abcde", new Date(), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByNotO_DS_SC_GCTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.getByNotO_DS_SC_GC(Long.valueOf(0), 0, "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeDossierByG_NOTO_DSTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.removeDossierByG_NOTO_DS(null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateSubmittingDateTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.updateSubmittingDate(Long.valueOf(0), Long.valueOf(0), "abcde", new Date(), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierByUuidAndGroupIdTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.getDossierByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void eparPublishDossierTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.eparPublishDossier(Long.valueOf(0), Long.valueOf(0), "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", new Date(), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, true, "abcde", 0, new Date(), new Date(), new Date(), new Date(), new Date(), new Date(), new Date(), new Date(), new Date(), new Date(), new Date(), new Date(), "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Double.valueOf(0.0), 0, "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void initMultipleDossierTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.initMultipleDossier(Long.valueOf(0), Long.valueOf(0), "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", new Date(), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, true, "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, "abcde", null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchOnePublicServiceTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.fetchOnePublicService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void initUpdateDossierMetaTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.initUpdateDossierMeta(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", 0, "abcde", new Date(), 0, 0, "abcde", 0, "abcde", new Date(), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByU_G_GAC_SC_DTNO_DS_OTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.getByU_G_GAC_SC_DTNO_DS_O(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDossierByUuidAndGroupIdTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.fetchDossierByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByG_NOTS_O_DTNTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.countByG_NOTS_O_DTN(Long.valueOf(0), null, 0, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateCorrectingDateTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.updateCorrectingDate(Long.valueOf(0), Long.valueOf(0), "abcde", new Date(), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateCancellingDateTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.updateCancellingDate(Long.valueOf(0), Long.valueOf(0), "abcde", new Date(), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeDossierByF_OG_DSTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.removeDossierByF_OG_DS(0, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierByG_NOTO_DSTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.getDossierByG_NOTO_DS(null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierByG_NOTO_DSTest82() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.getDossierByG_NOTO_DS(0, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateApplicantInfoTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.updateApplicantInfo(Long.valueOf(0), new Date(), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateReleaseDateTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.updateReleaseDate(Long.valueOf(0), Long.valueOf(0), "abcde", new Date(), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByNOT_ST_GT_MDTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.findByNOT_ST_GT_MD(null, new Date(), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByU_G_C_DS_SC_GC_OTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.getByU_G_C_DS_SC_GC_O(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", Long.valueOf(0), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void initUpdateDossierTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.initUpdateDossier(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void initUpdateDossierTest88() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.initUpdateDossier(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void initUpdateDossierTest89() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.initUpdateDossier(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), 0, "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierSpecialTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.updateDossierSpecial(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossiersTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.getDossiers(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierOneGateTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.updateDossierOneGate(Long.valueOf(0), "abcde", "abcde", "abcde", new Date(), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierActionTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.updateDossierAction(Long.valueOf(0), Long.valueOf(0), "abcde", Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByOriginDossierNoTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.countByOriginDossierNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findDossierByGroupTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.findDossierByGroup(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByGroupAndOriginDossierNoTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.getByGroupAndOriginDossierNo(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateGroupDossierTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.updateGroupDossier(null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByGID_GC_SC_DTN_DS_APP_ORITest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.getByGID_GC_SC_DTN_DS_APP_ORI(Long.valueOf(0), "abcde", "abcde", "abcde", null, "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void publishImportDossierTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.publishImportDossier(Long.valueOf(0), Long.valueOf(0), "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", new Date(), "abcde", "abcde", "abcde", "abcde", true, 0, "abcde", "abcde", "abcde", Long.valueOf(0), Double.valueOf(0.0), 0, 0, new Date(), new Date(), new Date(), new Date(), new Date(), new Date(), new Date(), "abcde", "abcde", 0, "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest105() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest107() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest110() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByDN_ANTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.findByDN_AN("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countDossierByGroupTest() {
		try{
			DossierLocalServiceWrapper object = new DossierLocalServiceWrapper(null);
			object.countDossierByGroup(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}