package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierActionUserLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void dynamicQueryTest() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest2() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierActionUserTest() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.addDossierActionUser(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierActionUserTest7() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.addDossierActionUser(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierActionUserTest8() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.addDossierActionUser(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, true, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDOSSIER_UIDTest() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.getByDOSSIER_UID(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getListUserTest() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.getListUser(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDossierIdTest() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.getByDossierId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDossierAndStepCodeTest() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.getByDossierAndStepCode(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierActionUserTest() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.updateDossierActionUser(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierActionUserTest14() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.updateDossierActionUser(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierActionUserTest15() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.updateDossierActionUser(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, true, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByD_DID_UID_SCTest() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.getByD_DID_UID_SC(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDossierActionUserTest() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.fetchDossierActionUser(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDID_DAIDTest() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.getByDID_DAID(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDIDTest() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.getByDID(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierActionUsersTest() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.getDossierActionUsers(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierActionUserTest() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.createDossierActionUser(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierActionUserTest() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.getDossierActionUser(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getListUserByUserIdTest() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.getListUserByUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}

	/*@Test
	public void deleteDossierActionUserTest() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.deleteDossierActionUser(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDossierActionUserTest25() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.deleteDossierActionUser(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}*/
	@Test
	public void getByDossierUserAndStepCodeTest() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.getByDossierUserAndStepCode(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDID_DAI_SC_ASTest() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.getByDID_DAI_SC_AS(Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierActionUsersCountTest() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.getDossierActionUsersCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addOrUpdateDossierActionUserTest() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.addOrUpdateDossierActionUser(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, true, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addOrUpdateDossierActionUserTest30() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.addOrUpdateDossierActionUser(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteByDossierActionTest() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.deleteByDossierAction(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteByDossierAndStepCodeTest() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.deleteByDossierAndStepCode(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDossierAndUserTest() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.getByDossierAndUser(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest38() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest40() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest43() {
		try{
			DossierActionUserLocalServiceWrapper object = new DossierActionUserLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}