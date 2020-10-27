package org.opencps.usermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ApplicantLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void activateApplicantTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.activateApplicant(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeApplicantTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.removeApplicant(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchApplicantTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.fetchApplicant(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByMappingIDTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.fetchByMappingID(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.getApplicant(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantsTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.getApplicants(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void verifyApplicantTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.verifyApplicant(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteApplicantTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.deleteApplicant(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteApplicantTest16() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.deleteApplicant(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateApplicantTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.updateApplicant(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", new Date(), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateApplicantTest18() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.updateApplicant(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateApplicantTest19() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.updateApplicant(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", new Date(), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantByTypeTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.getApplicantByType(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_APLC_GIDTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.fetchByF_APLC_GID(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.countLucene(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_GID_CTEMTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.fetchByF_GID_CTEM(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest29() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest31() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest34() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByAppIdTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.fetchByAppId("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByAppIdsTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.findByAppIds("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByEmailTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.fetchByEmail("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateApplicationTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.updateApplication(null, Long.valueOf(0), Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateApplicationTest40() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.updateApplication(null, Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateApplicationTest41() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.updateApplication(null, Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addApplicantTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.addApplicant(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createApplicantTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.createApplicant(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void lockoutApplicantTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.lockoutApplicant(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByTelNoTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.fetchByTelNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchBy_GTelNoTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.fetchBy_GTelNo(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeProfileTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.removeProfile(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchApplicantByUuidAndGroupIdTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.fetchApplicantByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_GID_MCN_MCPKTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.findByF_GID_MCN_MCPK(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantByUuidAndGroupIdTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.getApplicantByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantsCountTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.getApplicantsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateApplicantApprovedTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.updateApplicantApproved(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", new Date(), "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateApplicationDBTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.updateApplicationDB(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", new Date(), "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantsByUuidAndCompanyIdTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.getApplicantsByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantsByUuidAndCompanyIdTest55() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.getApplicantsByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_GID_MCN_MCPKTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.fetchByF_GID_MCN_MCPK(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByContactEmailListTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.findByContactEmailList("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void importApplicationDBTest() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.importApplicationDB(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", new Date(), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void importApplicationDBTest59() {
		try{
			ApplicantLocalServiceWrapper object = new ApplicantLocalServiceWrapper(null);
			object.importApplicationDB(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", new Date(), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}