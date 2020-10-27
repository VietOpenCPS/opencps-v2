package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierMarkLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			DossierMarkLocalServiceWrapper object = new DossierMarkLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			DossierMarkLocalServiceWrapper object = new DossierMarkLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			DossierMarkLocalServiceWrapper object = new DossierMarkLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			DossierMarkLocalServiceWrapper object = new DossierMarkLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			DossierMarkLocalServiceWrapper object = new DossierMarkLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierMarksTest() {
		try{
			DossierMarkLocalServiceWrapper object = new DossierMarkLocalServiceWrapper(null);
			object.getDossierMarks(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierMarksTest7() {
		try{
			DossierMarkLocalServiceWrapper object = new DossierMarkLocalServiceWrapper(null);
			object.getDossierMarks(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierMarkTest() {
		try{
			DossierMarkLocalServiceWrapper object = new DossierMarkLocalServiceWrapper(null);
			object.addDossierMark(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierMarkTest9() {
		try{
			DossierMarkLocalServiceWrapper object = new DossierMarkLocalServiceWrapper(null);
			object.addDossierMark(Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			DossierMarkLocalServiceWrapper object = new DossierMarkLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierMarksByFileMarkTest() {
		try{
			DossierMarkLocalServiceWrapper object = new DossierMarkLocalServiceWrapper(null);
			object.getDossierMarksByFileMark(Long.valueOf(0), Long.valueOf(0), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierMarkbyDossierIdTest() {
		try{
			DossierMarkLocalServiceWrapper object = new DossierMarkLocalServiceWrapper(null);
			object.getDossierMarkbyDossierId(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDossierMarkTest() {
		try{
			DossierMarkLocalServiceWrapper object = new DossierMarkLocalServiceWrapper(null);
			object.fetchDossierMark(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierMarkTest() {
		try{
			DossierMarkLocalServiceWrapper object = new DossierMarkLocalServiceWrapper(null);
			object.getDossierMark(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierMarksByUuidAndCompanyIdTest() {
		try{
			DossierMarkLocalServiceWrapper object = new DossierMarkLocalServiceWrapper(null);
			object.getDossierMarksByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierMarksByUuidAndCompanyIdTest16() {
		try{
			DossierMarkLocalServiceWrapper object = new DossierMarkLocalServiceWrapper(null);
			object.getDossierMarksByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierMarkTest() {
		try{
			DossierMarkLocalServiceWrapper object = new DossierMarkLocalServiceWrapper(null);
			object.createDossierMark(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDossierMarkByUuidAndGroupIdTest() {
		try{
			DossierMarkLocalServiceWrapper object = new DossierMarkLocalServiceWrapper(null);
			object.fetchDossierMarkByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDossierMarkTest() {
		try{
			DossierMarkLocalServiceWrapper object = new DossierMarkLocalServiceWrapper(null);
			object.deleteDossierMark(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDossierMarkTest20() {
		try{
			DossierMarkLocalServiceWrapper object = new DossierMarkLocalServiceWrapper(null);
			object.deleteDossierMark(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierMarksCountTest() {
		try{
			DossierMarkLocalServiceWrapper object = new DossierMarkLocalServiceWrapper(null);
			object.getDossierMarksCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierMarkTest() {
		try{
			DossierMarkLocalServiceWrapper object = new DossierMarkLocalServiceWrapper(null);
			object.updateDossierMark(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierMarkByUuidAndGroupIdTest() {
		try{
			DossierMarkLocalServiceWrapper object = new DossierMarkLocalServiceWrapper(null);
			object.getDossierMarkByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addBatchDossierMarkTest() {
		try{
			DossierMarkLocalServiceWrapper object = new DossierMarkLocalServiceWrapper(null);
			object.addBatchDossierMark(Long.valueOf(0), null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			DossierMarkLocalServiceWrapper object = new DossierMarkLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			DossierMarkLocalServiceWrapper object = new DossierMarkLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			DossierMarkLocalServiceWrapper object = new DossierMarkLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			DossierMarkLocalServiceWrapper object = new DossierMarkLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			DossierMarkLocalServiceWrapper object = new DossierMarkLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest30() {
		try{
			DossierMarkLocalServiceWrapper object = new DossierMarkLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			DossierMarkLocalServiceWrapper object = new DossierMarkLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest32() {
		try{
			DossierMarkLocalServiceWrapper object = new DossierMarkLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			DossierMarkLocalServiceWrapper object = new DossierMarkLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			DossierMarkLocalServiceWrapper object = new DossierMarkLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest35() {
		try{
			DossierMarkLocalServiceWrapper object = new DossierMarkLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			DossierMarkLocalServiceWrapper object = new DossierMarkLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}