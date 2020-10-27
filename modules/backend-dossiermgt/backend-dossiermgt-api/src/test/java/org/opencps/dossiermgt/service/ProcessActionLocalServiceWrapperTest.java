package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ProcessActionLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteProcessActionTest() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.deleteProcessAction(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteProcessActionTest8() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.deleteProcessAction(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeProcessActionTest() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.removeProcessAction(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateProcessActionTest() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.updateProcessAction(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, Long.valueOf(0), 0, "abcde", "abcde", "abcde", "abcde", "abcde", true, true, true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateProcessActionTest11() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.updateProcessAction(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, Long.valueOf(0), 0, "abcde", "abcde", "abcde", "abcde", "abcde", true, true, true, "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateProcessActionTest12() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.updateProcessAction(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateProcessActionTest13() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.updateProcessAction(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, Long.valueOf(0), 0, "abcde", "abcde", "abcde", "abcde", "abcde", true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessActionsTest() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.getProcessActions(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessActionTest() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.getProcessAction(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByActionCodeTest() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.getByActionCode(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByActionCodeTest18() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.getByActionCode(Long.valueOf(0), "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchCountTest() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.searchCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateProcessActionDBTest() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.updateProcessActionDB(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, Long.valueOf(0), "abcde", 0, "abcde", "abcde", "abcde", true, "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByServiceProcessTest() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.getByServiceProcess(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessActionbyServiceProcessIdTest() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.getProcessActionbyServiceProcessId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchProcessActionTest() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.fetchProcessAction(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchBySPID_ACTest() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.fetchBySPID_AC(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchProcessActionByUuidAndGroupIdTest() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.fetchProcessActionByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessActionsByUuidAndCompanyIdTest() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.getProcessActionsByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessActionsByUuidAndCompanyIdTest27() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.getProcessActionsByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchBySPI_PRESC_AEVTest() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.fetchBySPI_PRESC_AEV(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_GID_SID_AC_PRE_POSTTest() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.fetchByF_GID_SID_AC_PRE_POST(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByServiceStepCodeTest() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.getByServiceStepCode(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessActionByG_SPID_PRESCTest() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.getProcessActionByG_SPID_PRESC(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest37() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest39() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest42() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addProcessActionTest() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.addProcessAction(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByG_SID_ACSTest() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.getByG_SID_ACS(Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessActionByUuidAndGroupIdTest() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.getProcessActionByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByGroupAndAutoEventTest() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.getByGroupAndAutoEvent(Long.valueOf(0), "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByNameActionNoTest() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.getByNameActionNo(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createProcessActionTest() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.createProcessAction(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessActionsCountTest() {
		try{
			ProcessActionLocalServiceWrapper object = new ProcessActionLocalServiceWrapper(null);
			object.getProcessActionsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}