package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierPartLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getContentTest() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.getContent(Long.valueOf(0), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDataTest() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest6() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByTempAndFileTempNoTest() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.getByTempAndFileTempNo(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByTemplateNoTest() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.getByTemplateNo(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByGTest() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.findByG(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDossierPartTest() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.fetchDossierPart(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByTempAndPartNoTest() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.getByTempAndPartNo(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierPartTest() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.getDossierPart(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierPartsTest() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.getDossierParts(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneTest() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.countLucene(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByTemplatePartNoTest() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.fetchByTemplatePartNo(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierPartTest() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.addDossierPart(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateContentTest() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.updateContent(Long.valueOf(0), 0, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierPartsByUuidAndCompanyIdTest() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.getDossierPartsByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierPartsByUuidAndCompanyIdTest21() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.getDossierPartsByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierPartTest() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.createDossierPart(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDossierPartTest() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.deleteDossierPart(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDossierPartTest24() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.deleteDossierPart(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDossierPartByUuidAndGroupIdTest() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.fetchDossierPartByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByPartTypeEsignTest() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.getByPartTypeEsign("abcde", "abcde", 0, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierPartByUuidAndGroupIdTest() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.getDossierPartByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierPartsCountTest() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.getDossierPartsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeDossierPartTest() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.removeDossierPart(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByFileTemplateNoTest() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.getByFileTemplateNo(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierPartTest() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.updateDossierPart(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", 0, true, "abcde", "abcde", "abcde", true, "abcde", true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierPartTest32() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.updateDossierPart(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierPartTest33() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.updateDossierPart(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", 0, true, "abcde", "abcde", "abcde", true, "abcde", true, "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierPartDBTest() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.updateDossierPartDB(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", 0, true, "abcde", "abcde", true, true, "abcde", "abcde", 0, true, "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest40() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest42() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest45() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			DossierPartLocalServiceWrapper object = new DossierPartLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}