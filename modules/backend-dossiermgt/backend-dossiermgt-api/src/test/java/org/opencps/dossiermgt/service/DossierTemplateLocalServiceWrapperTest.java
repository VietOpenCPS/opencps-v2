package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierTemplateLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierTemplateTest() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.createDossierTemplate(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByTemplateNoTest() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.getByTemplateNo(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByGTest() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.findByG(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierTemplateTest() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.addDossierTemplate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeDossierTemplateTest() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.removeDossierTemplate(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDossierTemplateTest() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.fetchDossierTemplate(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneTest() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.countLucene(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierTemplateByUuidAndGroupIdTest() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.getDossierTemplateByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierTemplatesByUuidAndCompanyIdTest() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.getDossierTemplatesByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierTemplatesByUuidAndCompanyIdTest17() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.getDossierTemplatesByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDossierTemplateByUuidAndGroupIdTest() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.fetchDossierTemplateByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDossierTemplateTest() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.deleteDossierTemplate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDossierTemplateTest20() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.deleteDossierTemplate(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierTemplatesCountTest() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.getDossierTemplatesCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierTemplateTest() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.getDossierTemplate(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierTemplateTest() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.updateDossierTemplate(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierTemplateTest24() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.updateDossierTemplate(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierTemplateTest25() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.updateDossierTemplate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierTemplateDBTest() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.updateDossierTemplateDB(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierTemplateDBTest27() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.updateDossierTemplateDB(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest33() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest35() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest38() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierTemplatesTest() {
		try{
			DossierTemplateLocalServiceWrapper object = new DossierTemplateLocalServiceWrapper(null);
			object.getDossierTemplates(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}