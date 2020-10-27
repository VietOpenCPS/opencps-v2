package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class RegistrationLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void insertTest() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.insert(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDataTest() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest6() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByApplicantAndAgencyTest() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.getByApplicantAndAgency(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateRegistrationTest() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.updateRegistration(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateRegistrationTest10() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.updateRegistration(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchRegistrationTest() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.fetchRegistration(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.searchLucene(Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationTest() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.getRegistration(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationByG_REGIDTest() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.getRegistrationByG_REGID(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationsByUuidAndCompanyIdTest() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.getRegistrationsByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationsByUuidAndCompanyIdTest16() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.getRegistrationsByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchRegistrationByUuidAndGroupIdTest() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.fetchRegistrationByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest23() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest25() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest28() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getfileEntryIdTest() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.getfileEntryId("abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateSubmittingTest() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.updateSubmitting(Long.valueOf(0), true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationsTest() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.getRegistrations(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void registrationSyncTest() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.registrationSync(Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addRegistrationTest() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.addRegistration(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLucenseTest() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.countLucense(Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createRegistrationTest() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.createRegistration(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationByGID_UID_LastTest() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.getRegistrationByGID_UID_Last(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteRegistrationTest() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.deleteRegistration(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteRegistrationTest39() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.deleteRegistration(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getLastSubmittingByUserTest() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.getLastSubmittingByUser(Long.valueOf(0), Long.valueOf(0), true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationByGID_UIDTest() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.getRegistrationByGID_UID(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationByUuidAndGroupIdTest() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.getRegistrationByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByApplicantIdNoTest() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.getByApplicantIdNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getdByF_submittingTest() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.getdByF_submitting(Long.valueOf(0), true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByRegistrationStateTest() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.getByRegistrationState(Long.valueOf(0), Long.valueOf(0), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationsCountTest() {
		try{
			RegistrationLocalServiceWrapper object = new RegistrationLocalServiceWrapper(null);
			object.getRegistrationsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}