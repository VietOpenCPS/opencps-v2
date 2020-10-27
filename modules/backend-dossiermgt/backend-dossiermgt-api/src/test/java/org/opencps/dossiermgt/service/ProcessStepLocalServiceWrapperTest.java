package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ProcessStepLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateProcessStepTest() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.updateProcessStep(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", "abcde", Double.valueOf(0.0), "abcde", "abcde", "abcde", true, "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateProcessStepTest8() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.updateProcessStep(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteProcessStepTest() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.deleteProcessStep(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteProcessStepTest10() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.deleteProcessStep(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeProcessStepTest() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.removeProcessStep(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessStepTest() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.getProcessStep(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchBySC_GIDTest() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.fetchBySC_GID("abcde", Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_SP_SNOTest() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.findByG_SP_SNO(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateProcessStepDBTest() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.updateProcessStepDB(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Double.valueOf(0.0), "abcde", "abcde", "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessStepbyServiceProcessIdTest() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.getProcessStepbyServiceProcessId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneTest() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.countLucene(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessStepsByUuidAndCompanyIdTest() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.getProcessStepsByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessStepsByUuidAndCompanyIdTest20() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.getProcessStepsByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByProcessAndStatusTest() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.getByProcessAndStatus(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest27() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest29() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest32() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addProcessStepTest() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.addProcessStep(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessStepsTest() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.getProcessSteps(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByG_SP_SCSTest() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.getByG_SP_SCS(Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBySC_SPIDTest() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.getBySC_SPID("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchProcessStepTest() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.fetchProcessStep(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchProcessStepByUuidAndGroupIdTest() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.fetchProcessStepByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessStepsCountTest() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.getProcessStepsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByStatusAnsSubStatusTest() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.getByStatusAnsSubStatus("abcde", "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessStepByUuidAndGroupIdTest() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.getProcessStepByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createProcessStepTest() {
		try{
			ProcessStepLocalServiceWrapper object = new ProcessStepLocalServiceWrapper(null);
			object.createProcessStep(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}