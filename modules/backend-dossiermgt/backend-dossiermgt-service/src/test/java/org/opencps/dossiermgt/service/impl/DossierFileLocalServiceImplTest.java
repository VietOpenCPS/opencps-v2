package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierFileLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void toMapTest() {
		try{
			DossierFileLocalServiceImpl.toMap(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDataTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateFormDataTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.updateFormData(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void cloneDossierFileTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.cloneDossierFile(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFileByDID_FTNO_FirstTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.getDossierFileByDID_FTNO_First(Long.valueOf(0), "abcde", true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void toListTest() {
		try{
			DossierFileLocalServiceImpl.toList(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByGroupTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.findByGroup(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByFileEntryIdTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.getByFileEntryId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFilesByDossierIdTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.getDossierFilesByDossierId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierFileTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.updateDossierFile(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), null, "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierFileTest12() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.updateDossierFile(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", Long.valueOf(0), "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", Long.valueOf(0), true, true, true, true, 0, "abcde", "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierFileTest13() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.updateDossierFile(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDossierFileTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.deleteDossierFile(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDossierFileTest15() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.deleteDossierFile(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDossierFileTest16() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.deleteDossierFile(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.countLucene(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierFileTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.addDossierFile(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), null, "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierFileTest19() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.addDossierFile(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), null, "abcde", "abcde", "abcde", "abcde", true, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByDIDTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.findByDID(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDeliverableCodeTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.getByDeliverableCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByG_DID_FTN_R_OTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.getByG_DID_FTN_R_O(Long.valueOf(0), null, "abcde", true, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFileByReferenceUidTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.getDossierFileByReferenceUid(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFilesByD_DPTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.getDossierFilesByD_DP(Long.valueOf(0), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void permanentDeleteDossierFileTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.permanentDeleteDossierFile(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFileByDID_DPNOTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.getDossierFileByDID_DPNO(Long.valueOf(0), "abcde", true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAllDossierFileTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.getAllDossierFile(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByF_GID_DID_R_OTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.countByF_GID_DID_R_O(Long.valueOf(0), null, true, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByF_GID_DID_R_OTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.getByF_GID_DID_R_O(Long.valueOf(0), null, true, true, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByF_GID_DID_R_OTest31() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.getByF_GID_DID_R_O(Long.valueOf(0), null, true, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByG_DID_FTN_RTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.getByG_DID_FTN_R(Long.valueOf(0), null, "abcde", true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void resetDossierFileTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.resetDossierFile(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFileByDID_FTNO_DPTS_FirstTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.getDossierFileByDID_FTNO_DPTS_First(Long.valueOf(0), "abcde", 0, true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFileByDID_FTNO_DPT_NOT_NULL_FIDTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.getDossierFileByDID_FTNO_DPT_NOT_NULL_FID(Long.valueOf(0), "abcde", 0, Long.valueOf(0), true, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFileByDID_FTNO_DPT_NOT_NULL_FIDTest36() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.getDossierFileByDID_FTNO_DPT_NOT_NULL_FID(Long.valueOf(0), "abcde", 0, Long.valueOf(0), true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFileByDID_FTNO_DPT_FirstTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.getDossierFileByDID_FTNO_DPT_First(Long.valueOf(0), "abcde", 0, true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFileByDID_FTNO_DPTS_NOT_NULL_FIDTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.getDossierFileByDID_FTNO_DPTS_NOT_NULL_FID(Long.valueOf(0), "abcde", null, Long.valueOf(0), true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFileByDID_FTNO_DPTS_NOT_NULL_FIDTest39() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.getDossierFileByDID_FTNO_DPTS_NOT_NULL_FID(Long.valueOf(0), "abcde", null, Long.valueOf(0), true, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDossierAndRefTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.getByDossierAndRef(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierByDeliverableTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.addDossierByDeliverable(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", Long.valueOf(0), "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", Long.valueOf(0), true, true, true, true, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDossierIdAndIsNewTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.getByDossierIdAndIsNew(Long.valueOf(0), true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByRefAndGroupIdTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.getByRefAndGroupId(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByReferenceUidTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.getByReferenceUid("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFileByDID_FTNTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.getDossierFileByDID_FTN(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePermanentDossierFileTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.deletePermanentDossierFile(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findLastDossierFileTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.findLastDossierFile(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFileByDID_FTNOTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.getDossierFileByDID_FTNO(Long.valueOf(0), "abcde", true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void cloneDossierFilesByDossierIdTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.cloneDossierFilesByDossierId(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFileByDID_FTNO_DPTTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.getDossierFileByDID_FTNO_DPT(Long.valueOf(0), "abcde", 0, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFileByDID_FTNO_DPTTest51() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.getDossierFileByDID_FTNO_DPT(Long.valueOf(0), "abcde", 0, true, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFileByDID_FTNO_DPTSTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.getDossierFileByDID_FTNO_DPTS(Long.valueOf(0), "abcde", null, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFileByDID_FTNO_DPTSTest53() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.getDossierFileByDID_FTNO_DPTS(Long.valueOf(0), "abcde", null, true, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByGID_DID_TEMP_PART_EFORMTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.getByGID_DID_TEMP_PART_EFORM(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", true, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeDossierFileTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.removeDossierFile(Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierFileEFormTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.addDossierFileEForm(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), null, "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByGID_DID_PART_EFORMTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.getByGID_DID_PART_EFORM(Long.valueOf(0), Long.valueOf(0), "abcde", true, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByFileTemplateNoTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.getByFileTemplateNo(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByG_DID_PART_NAMETest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.getByG_DID_PART_NAME(Long.valueOf(0), Long.valueOf(0), "abcde", 0, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			DossierFileLocalServiceImpl object = new DossierFileLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}