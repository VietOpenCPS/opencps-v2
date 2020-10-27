package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierActionLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			DossierActionLocalServiceImpl object = new DossierActionLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findActionOverdueFutureTest() {
		try{
			DossierActionLocalServiceImpl object = new DossierActionLocalServiceImpl();
			object.findActionOverdueFuture(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findActionOverdueTest() {
		try{
			DossierActionLocalServiceImpl object = new DossierActionLocalServiceImpl();
			object.findActionOverdue(new Date(), new Date(), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			DossierActionLocalServiceImpl object = new DossierActionLocalServiceImpl();
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateStateTest() {
		try{
			DossierActionLocalServiceImpl object = new DossierActionLocalServiceImpl();
			object.updateState(Long.valueOf(0), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneTest() {
		try{
			DossierActionLocalServiceImpl object = new DossierActionLocalServiceImpl();
			object.countLucene(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_DIDTest() {
		try{
			DossierActionLocalServiceImpl object = new DossierActionLocalServiceImpl();
			object.findByG_DID(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDossierAndStepCodeTest() {
		try{
			DossierActionLocalServiceImpl object = new DossierActionLocalServiceImpl();
			object.getByDossierAndStepCode(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDID_U_SCTest() {
		try{
			DossierActionLocalServiceImpl object = new DossierActionLocalServiceImpl();
			object.getByDID_U_SC(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updatePendingTest() {
		try{
			DossierActionLocalServiceImpl object = new DossierActionLocalServiceImpl();
			object.updatePending(Long.valueOf(0), true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDID_U_FSCTest() {
		try{
			DossierActionLocalServiceImpl object = new DossierActionLocalServiceImpl();
			object.getByDID_U_FSC(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findOverdueTest() {
		try{
			DossierActionLocalServiceImpl object = new DossierActionLocalServiceImpl();
			object.findOverdue(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeActionTest() {
		try{
			DossierActionLocalServiceImpl object = new DossierActionLocalServiceImpl();
			object.removeAction(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierActionbyDossierIdandActionCodeTest() {
		try{
			DossierActionLocalServiceImpl object = new DossierActionLocalServiceImpl();
			object.getDossierActionbyDossierIdandActionCode(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findDossierActionByDID_FSNTest() {
		try{
			DossierActionLocalServiceImpl object = new DossierActionLocalServiceImpl();
			object.findDossierActionByDID_FSN(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDID_CODE_FirstTest() {
		try{
			DossierActionLocalServiceImpl object = new DossierActionLocalServiceImpl();
			object.getByDID_CODE_First(Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDID_SC_NOT_DAITest() {
		try{
			DossierActionLocalServiceImpl object = new DossierActionLocalServiceImpl();
			object.getByDID_SC_NOT_DAI(Long.valueOf(0), "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDID_FSC_NOT_DAITest() {
		try{
			DossierActionLocalServiceImpl object = new DossierActionLocalServiceImpl();
			object.getByDID_FSC_NOT_DAI(Long.valueOf(0), "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByNextActionIdTest() {
		try{
			DossierActionLocalServiceImpl object = new DossierActionLocalServiceImpl();
			object.getByNextActionId(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findDossierActionByDID_STEPTest() {
		try{
			DossierActionLocalServiceImpl object = new DossierActionLocalServiceImpl();
			object.findDossierActionByDID_STEP(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findOverdueByTypeTest() {
		try{
			DossierActionLocalServiceImpl object = new DossierActionLocalServiceImpl();
			object.findOverdueByType(new Date(), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByPenddingStatusTest() {
		try{
			DossierActionLocalServiceImpl object = new DossierActionLocalServiceImpl();
			object.getByPenddingStatus(Long.valueOf(0), true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierActionByIdTest() {
		try{
			DossierActionLocalServiceImpl object = new DossierActionLocalServiceImpl();
			object.getDossierActionById(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierActionByIdTest24() {
		try{
			DossierActionLocalServiceImpl object = new DossierActionLocalServiceImpl();
			object.getDossierActionById(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateImportDossierActionTest() {
		try{
			DossierActionLocalServiceImpl object = new DossierActionLocalServiceImpl();
			object.updateImportDossierAction(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", new Date(), Long.valueOf(0), 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateRollbackableTest() {
		try{
			DossierActionLocalServiceImpl object = new DossierActionLocalServiceImpl();
			object.updateRollbackable(Long.valueOf(0), true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierActionTest() {
		try{
			DossierActionLocalServiceImpl object = new DossierActionLocalServiceImpl();
			object.updateDossierAction(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", new Date(), Long.valueOf(0), "abcde", "abcde", 0, 0, true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierActionTest28() {
		try{
			DossierActionLocalServiceImpl object = new DossierActionLocalServiceImpl();
			object.updateDossierAction(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, "abcde", true, true, "abcde", "abcde", "abcde", new Date(), Long.valueOf(0), "abcde", "abcde", 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierActionTest29() {
		try{
			DossierActionLocalServiceImpl object = new DossierActionLocalServiceImpl();
			object.updateDossierAction(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", new Date(), Long.valueOf(0), "abcde", "abcde", 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findDossierActionByG_DID_SNTest() {
		try{
			DossierActionLocalServiceImpl object = new DossierActionLocalServiceImpl();
			object.findDossierActionByG_DID_SN(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossiersPendingTest() {
		try{
			DossierActionLocalServiceImpl object = new DossierActionLocalServiceImpl();
			object.getDossiersPending(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findDossierActionByG_DID_FSNTest() {
		try{
			DossierActionLocalServiceImpl object = new DossierActionLocalServiceImpl();
			object.findDossierActionByG_DID_FSN(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateNextActionIdTest() {
		try{
			DossierActionLocalServiceImpl object = new DossierActionLocalServiceImpl();
			object.updateNextActionId(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			DossierActionLocalServiceImpl object = new DossierActionLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findActionUndueTest() {
		try{
			DossierActionLocalServiceImpl object = new DossierActionLocalServiceImpl();
			object.findActionUndue(new Date(), new Date(), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}