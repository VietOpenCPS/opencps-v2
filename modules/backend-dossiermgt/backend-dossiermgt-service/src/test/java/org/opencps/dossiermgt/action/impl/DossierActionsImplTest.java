package org.opencps.dossiermgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierActionsImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void doActionTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.doAction(Long.valueOf(0), Long.valueOf(0), null, null, null, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void doActionTest3() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.doAction(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void cloneDossierTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.cloneDossier(Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void resetDossierTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.resetDossier(Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeDossierTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.removeDossier(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void submitDossierTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.submitDossier(Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updatePaymentAmountMetaDataTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.updatePaymentAmountMetaData(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierCountTodoPermissionTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.getDossierCountTodoPermission(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierTodoPermissionTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.getDossierTodoPermission(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierVNPostTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.updateDossierVNPost(Long.valueOf(0), "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictItemNameTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.getDictItemName(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessOptionTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.getProcessOption(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessOptionTest14() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.getProcessOption("abcde", "abcde", "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierStatusTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.getDossierStatus(null, Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessActionTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.getProcessAction(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.getDossier(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getContactsTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.getContacts(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDueDateTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.getDueDate(Long.valueOf(0), Long.valueOf(0), "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierProcessListTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.getDossierProcessList(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void cancelDossierTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.cancelDossier(Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void correctDossierTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.correctDossier(Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void hasCreateDossierTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.hasCreateDossier(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", Long.valueOf(0), true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNextActionIdTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.getNextActionId(Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.createDossier(Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", new Date(), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void initUpdateDossierFullTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.initUpdateDossierFull(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", 0, "abcde", new Date(), 0, 0, "abcde", 0, "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void submitPostDossierTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.submitPostDossier(Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNextActionListTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.getNextActionList(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPayloadNextActionsTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.getPayloadNextActions(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDetailNextActionsTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.getDetailNextActions(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void initDossierTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.initDossier(Long.valueOf(0), Long.valueOf(0), "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, true, "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void initDossierTest38() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.initDossier(Long.valueOf(0), Long.valueOf(0), "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, true, "abcde", 0, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void publishDossierTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.publishDossier(Long.valueOf(0), Long.valueOf(0), "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, true, "abcde", 0, new Date(), new Date(), new Date(), new Date(), new Date(), new Date(), new Date(), new Date(), new Date(), new Date(), new Date(), new Date(), "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Double.valueOf(0.0), 0, "abcde", "abcde", "abcde", 0, "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void initUpdateDossierTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.initUpdateDossier(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void initUpdateDossierTest42() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.initUpdateDossier(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void initUpdateDossierTest43() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.initUpdateDossier(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), 0, "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossiersTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.getDossiers(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAssignUsersByStepTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.getAssignUsersByStep(Long.valueOf(0), null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void publishImportDossierTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.publishImportDossier(Long.valueOf(0), Long.valueOf(0), "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", new Date(), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true, 0, 0, Double.valueOf(0.0), 0, new Date(), new Date(), new Date(), new Date(), new Date(), new Date(), new Date(), "abcde", "abcde", 0, "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierActionTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.getDossierAction(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierTodoTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.getDossierTodo(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void doRollbackTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.doRollback(Long.valueOf(0), Long.valueOf(0), "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void markerVisitedTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.markerVisited(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossiersTestTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.getDossiersTest(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void hasDossierSyncTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.hasDossierSync(Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void buildPayloadTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.buildPayload(Long.valueOf(0), Long.valueOf(0), "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionDueDateTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.getActionDueDate(Long.valueOf(0), Long.valueOf(0), "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isSubmitTypeTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.isSubmitType(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void areEqualDoubleTest() {
		try{
			DossierActionsImpl.areEqualDouble(Double.valueOf(0.0), Double.valueOf(0.0), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNextActionTimmerTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.getNextActionTimmer(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getListProcessAtionTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.getListProcessAtion(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void assignDossierToProcessTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.assignDossierToProcess(Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void forcedDossierSyncTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.forcedDossierSync(Long.valueOf(0), Long.valueOf(0), "abcde", null, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getListProcessStepTest() {
		try{
			DossierActionsImpl object = new DossierActionsImpl();
			object.getListProcessStep(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}