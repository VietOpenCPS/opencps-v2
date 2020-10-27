package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DossierUserLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void dynamicQueryTest() {
		try{
			DossierUserLocalServiceWrapper object = new DossierUserLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest2() {
		try{
			DossierUserLocalServiceWrapper object = new DossierUserLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			DossierUserLocalServiceWrapper object = new DossierUserLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			DossierUserLocalServiceWrapper object = new DossierUserLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			DossierUserLocalServiceWrapper object = new DossierUserLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDossierUserTest() {
		try{
			DossierUserLocalServiceWrapper object = new DossierUserLocalServiceWrapper(null);
			object.fetchDossierUser(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierUserTest() {
		try{
			DossierUserLocalServiceWrapper object = new DossierUserLocalServiceWrapper(null);
			object.addDossierUser(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierUserTest8() {
		try{
			DossierUserLocalServiceWrapper object = new DossierUserLocalServiceWrapper(null);
			object.addDossierUser(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), 0, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByDID_UDTest() {
		try{
			DossierUserLocalServiceWrapper object = new DossierUserLocalServiceWrapper(null);
			object.findByDID_UD(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByDIDTest() {
		try{
			DossierUserLocalServiceWrapper object = new DossierUserLocalServiceWrapper(null);
			object.findByDID(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierUserTest() {
		try{
			DossierUserLocalServiceWrapper object = new DossierUserLocalServiceWrapper(null);
			object.updateDossierUser(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierUserTest12() {
		try{
			DossierUserLocalServiceWrapper object = new DossierUserLocalServiceWrapper(null);
			object.updateDossierUser(Long.valueOf(0), Long.valueOf(0), 0, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierUsersTest() {
		try{
			DossierUserLocalServiceWrapper object = new DossierUserLocalServiceWrapper(null);
			object.getDossierUsers(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierUserTest() {
		try{
			DossierUserLocalServiceWrapper object = new DossierUserLocalServiceWrapper(null);
			object.getDossierUser(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDossierUserTest() {
		try{
			DossierUserLocalServiceWrapper object = new DossierUserLocalServiceWrapper(null);
			object.getByDossierUser(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierUserTest() {
		try{
			DossierUserLocalServiceWrapper object = new DossierUserLocalServiceWrapper(null);
			object.createDossierUser(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}

	/*@Test
	public void deleteDossierUserTest() {
		try{
			DossierUserLocalServiceWrapper object = new DossierUserLocalServiceWrapper(null);
			object.deleteDossierUser(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}*/
//	@Test
//	public void deleteDossierUserTest18() {
//		try{
//			DossierUserLocalServiceWrapper object = new DossierUserLocalServiceWrapper(null);
//			object.deleteDossierUser(DossierUserUtil.create(new DossierUserPK()));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void deleteDossierUserTest19() {
		try{
			DossierUserLocalServiceWrapper object = new DossierUserLocalServiceWrapper(null);
			object.deleteDossierUser(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierUsersCountTest() {
		try{
			DossierUserLocalServiceWrapper object = new DossierUserLocalServiceWrapper(null);
			object.getDossierUsersCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			DossierUserLocalServiceWrapper object = new DossierUserLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			DossierUserLocalServiceWrapper object = new DossierUserLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			DossierUserLocalServiceWrapper object = new DossierUserLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			DossierUserLocalServiceWrapper object = new DossierUserLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest25() {
		try{
			DossierUserLocalServiceWrapper object = new DossierUserLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			DossierUserLocalServiceWrapper object = new DossierUserLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest27() {
		try{
			DossierUserLocalServiceWrapper object = new DossierUserLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			DossierUserLocalServiceWrapper object = new DossierUserLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			DossierUserLocalServiceWrapper object = new DossierUserLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest30() {
		try{
			DossierUserLocalServiceWrapper object = new DossierUserLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}