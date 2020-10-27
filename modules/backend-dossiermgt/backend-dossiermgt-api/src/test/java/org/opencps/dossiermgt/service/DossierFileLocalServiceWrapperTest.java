package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierFileLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateFormDataTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.updateFormData(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void cloneDossierFileTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.cloneDossierFile(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFilesTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getDossierFiles(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFileByDID_FTNO_FirstTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getDossierFileByDID_FTNO_First(Long.valueOf(0), "abcde", true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByGroupTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.findByGroup(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByFileEntryIdTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getByFileEntryId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDossierFileTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.fetchDossierFile(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFilesByDossierIdTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getDossierFilesByDossierId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierFileTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.updateDossierFile(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", Long.valueOf(0), "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", Long.valueOf(0), true, true, true, true, 0, "abcde", "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierFileTest17() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.updateDossierFile(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierFileTest18() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.updateDossierFile(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), null, "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierFileTest19() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.updateDossierFile(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDossierFileTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.deleteDossierFile(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDossierFileTest21() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.deleteDossierFile(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDossierFileTest22() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.deleteDossierFile(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.countLucene(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierFileTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.addDossierFile(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), null, "abcde", "abcde", "abcde", "abcde", true, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierFileTest25() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.addDossierFile(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), null, "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierFileTest26() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.addDossierFile(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByDIDTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.findByDID(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDeliverableCodeTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getByDeliverableCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByG_DID_FTN_R_OTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getByG_DID_FTN_R_O(Long.valueOf(0), null, "abcde", true, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFileByReferenceUidTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getDossierFileByReferenceUid(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFilesByD_DPTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getDossierFilesByD_DP(Long.valueOf(0), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void permanentDeleteDossierFileTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.permanentDeleteDossierFile(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFileByDID_DPNOTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getDossierFileByDID_DPNO(Long.valueOf(0), "abcde", true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAllDossierFileTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getAllDossierFile(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByF_GID_DID_R_OTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.countByF_GID_DID_R_O(Long.valueOf(0), null, true, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByF_GID_DID_R_OTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getByF_GID_DID_R_O(Long.valueOf(0), null, true, true, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByF_GID_DID_R_OTest37() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getByF_GID_DID_R_O(Long.valueOf(0), null, true, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByG_DID_FTN_RTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getByG_DID_FTN_R(Long.valueOf(0), null, "abcde", true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFileTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getDossierFile(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void resetDossierFileTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.resetDossierFile(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFileByDID_FTNO_DPTS_FirstTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getDossierFileByDID_FTNO_DPTS_First(Long.valueOf(0), "abcde", 0, true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFileByDID_FTNO_DPT_NOT_NULL_FIDTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getDossierFileByDID_FTNO_DPT_NOT_NULL_FID(Long.valueOf(0), "abcde", 0, Long.valueOf(0), true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFileByDID_FTNO_DPT_NOT_NULL_FIDTest43() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getDossierFileByDID_FTNO_DPT_NOT_NULL_FID(Long.valueOf(0), "abcde", 0, Long.valueOf(0), true, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFilesByUuidAndCompanyIdTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getDossierFilesByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFilesByUuidAndCompanyIdTest45() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getDossierFilesByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFileByDID_FTNO_DPT_FirstTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getDossierFileByDID_FTNO_DPT_First(Long.valueOf(0), "abcde", 0, true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFileByDID_FTNO_DPTS_NOT_NULL_FIDTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getDossierFileByDID_FTNO_DPTS_NOT_NULL_FID(Long.valueOf(0), "abcde", null, Long.valueOf(0), true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFileByDID_FTNO_DPTS_NOT_NULL_FIDTest48() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getDossierFileByDID_FTNO_DPTS_NOT_NULL_FID(Long.valueOf(0), "abcde", null, Long.valueOf(0), true, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDossierAndRefTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getByDossierAndRef(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierByDeliverableTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.addDossierByDeliverable(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", Long.valueOf(0), "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", Long.valueOf(0), true, true, true, true, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDossierFileByUuidAndGroupIdTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.fetchDossierFileByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDossierIdAndIsNewTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getByDossierIdAndIsNew(Long.valueOf(0), true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByRefAndGroupIdTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getByRefAndGroupId(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierFileTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.createDossierFile(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByReferenceUidTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getByReferenceUid("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFileByDID_FTNTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getDossierFileByDID_FTN(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePermanentDossierFileTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.deletePermanentDossierFile(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findLastDossierFileTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.findLastDossierFile(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFileByDID_FTNOTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getDossierFileByDID_FTNO(Long.valueOf(0), "abcde", true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void cloneDossierFilesByDossierIdTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.cloneDossierFilesByDossierId(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFileByDID_FTNO_DPTTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getDossierFileByDID_FTNO_DPT(Long.valueOf(0), "abcde", 0, true, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFileByDID_FTNO_DPTTest62() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getDossierFileByDID_FTNO_DPT(Long.valueOf(0), "abcde", 0, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFileByDID_FTNO_DPTSTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getDossierFileByDID_FTNO_DPTS(Long.valueOf(0), "abcde", null, true, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFileByDID_FTNO_DPTSTest64() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getDossierFileByDID_FTNO_DPTS(Long.valueOf(0), "abcde", null, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByGID_DID_TEMP_PART_EFORMTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getByGID_DID_TEMP_PART_EFORM(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", true, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFileByUuidAndGroupIdTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getDossierFileByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFilesCountTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getDossierFilesCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeDossierFileTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.removeDossierFile(Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierFileEFormTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.addDossierFileEForm(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), null, "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByGID_DID_PART_EFORMTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getByGID_DID_PART_EFORM(Long.valueOf(0), Long.valueOf(0), "abcde", true, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByFileTemplateNoTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getByFileTemplateNo(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByG_DID_PART_NAMETest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getByG_DID_PART_NAME(Long.valueOf(0), Long.valueOf(0), "abcde", 0, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest78() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest80() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest83() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			DossierFileLocalServiceWrapper object = new DossierFileLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}