package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierDocumentLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			DossierDocumentLocalServiceWrapper object = new DossierDocumentLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			DossierDocumentLocalServiceWrapper object = new DossierDocumentLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			DossierDocumentLocalServiceWrapper object = new DossierDocumentLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			DossierDocumentLocalServiceWrapper object = new DossierDocumentLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			DossierDocumentLocalServiceWrapper object = new DossierDocumentLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			DossierDocumentLocalServiceWrapper object = new DossierDocumentLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierDocTest() {
		try{
			DossierDocumentLocalServiceWrapper object = new DossierDocumentLocalServiceWrapper(null);
			object.updateDossierDoc(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierDocTest8() {
		try{
			DossierDocumentLocalServiceWrapper object = new DossierDocumentLocalServiceWrapper(null);
			object.updateDossierDoc(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierDocTest() {
		try{
			DossierDocumentLocalServiceWrapper object = new DossierDocumentLocalServiceWrapper(null);
			object.addDossierDoc(Long.valueOf(0), Long.valueOf(0), "abcde", Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierDocTest10() {
		try{
			DossierDocumentLocalServiceWrapper object = new DossierDocumentLocalServiceWrapper(null);
			object.addDossierDoc(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierDocTest11() {
		try{
			DossierDocumentLocalServiceWrapper object = new DossierDocumentLocalServiceWrapper(null);
			object.addDossierDoc(Long.valueOf(0), Long.valueOf(0), "abcde", Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByActiocIdTest() {
		try{
			DossierDocumentLocalServiceWrapper object = new DossierDocumentLocalServiceWrapper(null);
			object.getByActiocId(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierDocumentListTest() {
		try{
			DossierDocumentLocalServiceWrapper object = new DossierDocumentLocalServiceWrapper(null);
			object.getDossierDocumentList(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByG_DocTypeListTest() {
		try{
			DossierDocumentLocalServiceWrapper object = new DossierDocumentLocalServiceWrapper(null);
			object.getByG_DocTypeList(Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDocByReferenceUidTest() {
		try{
			DossierDocumentLocalServiceWrapper object = new DossierDocumentLocalServiceWrapper(null);
			object.getDocByReferenceUid(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDossierDocumentByUuidAndGroupIdTest() {
		try{
			DossierDocumentLocalServiceWrapper object = new DossierDocumentLocalServiceWrapper(null);
			object.fetchDossierDocumentByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierDocumentByUuidAndGroupIdTest() {
		try{
			DossierDocumentLocalServiceWrapper object = new DossierDocumentLocalServiceWrapper(null);
			object.getDossierDocumentByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierDocumentTest() {
		try{
			DossierDocumentLocalServiceWrapper object = new DossierDocumentLocalServiceWrapper(null);
			object.createDossierDocument(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierDocumentTest() {
		try{
			DossierDocumentLocalServiceWrapper object = new DossierDocumentLocalServiceWrapper(null);
			object.getDossierDocument(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierDocumentTest() {
		try{
			DossierDocumentLocalServiceWrapper object = new DossierDocumentLocalServiceWrapper(null);
			object.updateDossierDocument(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countDossierDocumentListTest() {
		try{
			DossierDocumentLocalServiceWrapper object = new DossierDocumentLocalServiceWrapper(null);
			object.countDossierDocumentList(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierDocumentsCountTest() {
		try{
			DossierDocumentLocalServiceWrapper object = new DossierDocumentLocalServiceWrapper(null);
			object.getDossierDocumentsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierDocumentTest() {
		try{
			DossierDocumentLocalServiceWrapper object = new DossierDocumentLocalServiceWrapper(null);
			object.addDossierDocument(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierDocumentsTest() {
		try{
			DossierDocumentLocalServiceWrapper object = new DossierDocumentLocalServiceWrapper(null);
			object.getDossierDocuments(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDossierDocumentTest() {
		try{
			DossierDocumentLocalServiceWrapper object = new DossierDocumentLocalServiceWrapper(null);
			object.deleteDossierDocument(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDossierDocumentTest26() {
		try{
			DossierDocumentLocalServiceWrapper object = new DossierDocumentLocalServiceWrapper(null);
			object.deleteDossierDocument(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDossierDocumentTest() {
		try{
			DossierDocumentLocalServiceWrapper object = new DossierDocumentLocalServiceWrapper(null);
			object.fetchDossierDocument(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			DossierDocumentLocalServiceWrapper object = new DossierDocumentLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			DossierDocumentLocalServiceWrapper object = new DossierDocumentLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			DossierDocumentLocalServiceWrapper object = new DossierDocumentLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			DossierDocumentLocalServiceWrapper object = new DossierDocumentLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest32() {
		try{
			DossierDocumentLocalServiceWrapper object = new DossierDocumentLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			DossierDocumentLocalServiceWrapper object = new DossierDocumentLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest34() {
		try{
			DossierDocumentLocalServiceWrapper object = new DossierDocumentLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			DossierDocumentLocalServiceWrapper object = new DossierDocumentLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			DossierDocumentLocalServiceWrapper object = new DossierDocumentLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest37() {
		try{
			DossierDocumentLocalServiceWrapper object = new DossierDocumentLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			DossierDocumentLocalServiceWrapper object = new DossierDocumentLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}