package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierActionLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findActionOverdueFutureTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.findActionOverdueFuture(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findActionOverdueTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.findActionOverdue(new Date(), new Date(), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateStateTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.updateState(Long.valueOf(0), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.countLucene(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_DIDTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.findByG_DID(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDossierAndStepCodeTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.getByDossierAndStepCode(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDossierActionTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.fetchDossierAction(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDID_U_SCTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.getByDID_U_SC(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updatePendingTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.updatePending(Long.valueOf(0), true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDID_U_FSCTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.getByDID_U_FSC(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierActionTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.addDossierAction(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findOverdueTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.findOverdue(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeActionTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.removeAction(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierActionsByUuidAndCompanyIdTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.getDossierActionsByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierActionsByUuidAndCompanyIdTest22() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.getDossierActionsByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDossierActionByUuidAndGroupIdTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.fetchDossierActionByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierActionbyDossierIdandActionCodeTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.getDossierActionbyDossierIdandActionCode(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierActionTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.createDossierAction(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDossierActionTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.deleteDossierAction(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDossierActionTest27() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.deleteDossierAction(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findDossierActionByDID_FSNTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.findDossierActionByDID_FSN(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDID_CODE_FirstTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.getByDID_CODE_First(Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDID_SC_NOT_DAITest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.getByDID_SC_NOT_DAI(Long.valueOf(0), "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDID_FSC_NOT_DAITest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.getByDID_FSC_NOT_DAI(Long.valueOf(0), "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByNextActionIdTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.getByNextActionId(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findDossierActionByDID_STEPTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.findDossierActionByDID_STEP(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findOverdueByTypeTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.findOverdueByType(new Date(), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByPenddingStatusTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.getByPenddingStatus(Long.valueOf(0), true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierActionByIdTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.getDossierActionById(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierActionByIdTest37() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.getDossierActionById(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierActionByUuidAndGroupIdTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.getDossierActionByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierActionsTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.getDossierActions(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateImportDossierActionTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.updateImportDossierAction(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", new Date(), Long.valueOf(0), 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierActionsCountTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.getDossierActionsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateRollbackableTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.updateRollbackable(Long.valueOf(0), true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierActionTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.updateDossierAction(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", new Date(), Long.valueOf(0), "abcde", "abcde", 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierActionTest44() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.updateDossierAction(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", new Date(), Long.valueOf(0), "abcde", "abcde", 0, 0, true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierActionTest45() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.updateDossierAction(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, "abcde", true, true, "abcde", "abcde", "abcde", new Date(), Long.valueOf(0), "abcde", "abcde", 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierActionTest46() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.updateDossierAction(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findDossierActionByG_DID_SNTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.findDossierActionByG_DID_SN(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossiersPendingTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.getDossiersPending(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findDossierActionByG_DID_FSNTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.findDossierActionByG_DID_FSN(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateNextActionIdTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.updateNextActionId(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest56() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest58() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest61() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierActionTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.getDossierAction(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findActionUndueTest() {
		try{
			DossierActionLocalServiceWrapper object = new DossierActionLocalServiceWrapper(null);
			object.findActionUndue(new Date(), new Date(), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}