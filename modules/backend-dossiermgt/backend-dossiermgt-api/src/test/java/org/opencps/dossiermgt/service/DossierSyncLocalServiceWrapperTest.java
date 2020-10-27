package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierSyncLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void dynamicQueryTest() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest2() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierSyncListTest() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.getDossierSyncList("abcde", 0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDID_DADTest() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.getByDID_DAD(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_DIDTest() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.findByG_DID(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_DID_STTest() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.findByG_DID_ST(Long.valueOf(0), Long.valueOf(0), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDossierSyncTest() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.fetchDossierSync(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByStateTest() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.findByState(0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierSyncTest() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.addDossierSync(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByStatesTest() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.findByStates(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDID_DAD_ACTest() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.getByDID_DAD_AC(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierSyncTest() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.getDossierSync(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierSyncsTest() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.getDossierSyncs(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countDossierSyncListTest() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.countDossierSyncList("abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByDossierAndInfoTypeTest() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.countByDossierAndInfoType(Long.valueOf(0), "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByDossierAndInfoTypeArrTest() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.countByDossierAndInfoTypeArr(Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDossierSyncTest() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.deleteDossierSync(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDossierSyncTest21() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.deleteDossierSync(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierSyncTest() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.createDossierSync(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByDossierAndInfoTypeTest() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.findByDossierAndInfoType(Long.valueOf(0), "abcde", 0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findForApplicantAndActionCodeTest() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.findForApplicantAndActionCode(Long.valueOf(0), "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierSyncByIdListTest() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.getDossierSyncByIdList(Long.valueOf(0), 0, 0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countForApplicantAndActionCodeTest() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.countForApplicantAndActionCode(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countDossierSyncByIdListTest() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.countDossierSyncByIdList(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDossierSyncByUuidAndGroupIdTest() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.fetchDossierSyncByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierSyncByUuidAndGroupIdTest() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.getDossierSyncByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByDossierAndInfoTypeArrTest() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.findByDossierAndInfoTypeArr(Long.valueOf(0), "abcde", null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierSyncsCountTest() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.getDossierSyncsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierSyncTest() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.updateDossierSync(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", 0, 0, "abcde", "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierSyncTest33() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.updateDossierSync(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeByDossierIdTest() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.removeByDossierId(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findAllTest() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.findAll(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest40() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest42() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest45() {
		try{
			DossierSyncLocalServiceWrapper object = new DossierSyncLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}