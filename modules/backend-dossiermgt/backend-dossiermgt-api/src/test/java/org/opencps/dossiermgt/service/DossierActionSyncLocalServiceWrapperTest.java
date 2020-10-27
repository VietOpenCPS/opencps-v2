package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierActionSyncLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void dynamicQueryTest() {
		try{
			DossierActionSyncLocalServiceWrapper object = new DossierActionSyncLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest2() {
		try{
			DossierActionSyncLocalServiceWrapper object = new DossierActionSyncLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			DossierActionSyncLocalServiceWrapper object = new DossierActionSyncLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			DossierActionSyncLocalServiceWrapper object = new DossierActionSyncLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			DossierActionSyncLocalServiceWrapper object = new DossierActionSyncLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierActionSyncsByUuidAndCompanyIdTest() {
		try{
			DossierActionSyncLocalServiceWrapper object = new DossierActionSyncLocalServiceWrapper(null);
			object.getDossierActionSyncsByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierActionSyncsByUuidAndCompanyIdTest7() {
		try{
			DossierActionSyncLocalServiceWrapper object = new DossierActionSyncLocalServiceWrapper(null);
			object.getDossierActionSyncsByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDossierActionSyncByUuidAndGroupIdTest() {
		try{
			DossierActionSyncLocalServiceWrapper object = new DossierActionSyncLocalServiceWrapper(null);
			object.fetchDossierActionSyncByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierActionSyncByUuidAndGroupIdTest() {
		try{
			DossierActionSyncLocalServiceWrapper object = new DossierActionSyncLocalServiceWrapper(null);
			object.getDossierActionSyncByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDossierActionSyncTest() {
		try{
			DossierActionSyncLocalServiceWrapper object = new DossierActionSyncLocalServiceWrapper(null);
			object.deleteDossierActionSync(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDossierActionSyncTest11() {
		try{
			DossierActionSyncLocalServiceWrapper object = new DossierActionSyncLocalServiceWrapper(null);
			object.deleteDossierActionSync(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierActionSyncTest() {
		try{
			DossierActionSyncLocalServiceWrapper object = new DossierActionSyncLocalServiceWrapper(null);
			object.getDossierActionSync(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierActionSyncTest() {
		try{
			DossierActionSyncLocalServiceWrapper object = new DossierActionSyncLocalServiceWrapper(null);
			object.createDossierActionSync(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierActionSyncsTest() {
		try{
			DossierActionSyncLocalServiceWrapper object = new DossierActionSyncLocalServiceWrapper(null);
			object.getDossierActionSyncs(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierActionSyncTest() {
		try{
			DossierActionSyncLocalServiceWrapper object = new DossierActionSyncLocalServiceWrapper(null);
			object.addDossierActionSync(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierActionSyncsCountTest() {
		try{
			DossierActionSyncLocalServiceWrapper object = new DossierActionSyncLocalServiceWrapper(null);
			object.getDossierActionSyncsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierActionSyncTest() {
		try{
			DossierActionSyncLocalServiceWrapper object = new DossierActionSyncLocalServiceWrapper(null);
			object.updateDossierActionSync(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDossierActionSyncTest() {
		try{
			DossierActionSyncLocalServiceWrapper object = new DossierActionSyncLocalServiceWrapper(null);
			object.fetchDossierActionSync(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			DossierActionSyncLocalServiceWrapper object = new DossierActionSyncLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			DossierActionSyncLocalServiceWrapper object = new DossierActionSyncLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			DossierActionSyncLocalServiceWrapper object = new DossierActionSyncLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			DossierActionSyncLocalServiceWrapper object = new DossierActionSyncLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			DossierActionSyncLocalServiceWrapper object = new DossierActionSyncLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest24() {
		try{
			DossierActionSyncLocalServiceWrapper object = new DossierActionSyncLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			DossierActionSyncLocalServiceWrapper object = new DossierActionSyncLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest26() {
		try{
			DossierActionSyncLocalServiceWrapper object = new DossierActionSyncLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			DossierActionSyncLocalServiceWrapper object = new DossierActionSyncLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			DossierActionSyncLocalServiceWrapper object = new DossierActionSyncLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest29() {
		try{
			DossierActionSyncLocalServiceWrapper object = new DossierActionSyncLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}