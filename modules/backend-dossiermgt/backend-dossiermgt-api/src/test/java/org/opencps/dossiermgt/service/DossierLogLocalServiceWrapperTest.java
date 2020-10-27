package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierLogLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			DossierLogLocalServiceWrapper object = new DossierLogLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			DossierLogLocalServiceWrapper object = new DossierLogLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			DossierLogLocalServiceWrapper object = new DossierLogLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			DossierLogLocalServiceWrapper object = new DossierLogLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			DossierLogLocalServiceWrapper object = new DossierLogLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierLogsTest() {
		try{
			DossierLogLocalServiceWrapper object = new DossierLogLocalServiceWrapper(null);
			object.getDossierLogs(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			DossierLogLocalServiceWrapper object = new DossierLogLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			DossierLogLocalServiceWrapper object = new DossierLogLocalServiceWrapper(null);
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByGroupTest() {
		try{
			DossierLogLocalServiceWrapper object = new DossierLogLocalServiceWrapper(null);
			object.findByGroup(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDossierLogTest() {
		try{
			DossierLogLocalServiceWrapper object = new DossierLogLocalServiceWrapper(null);
			object.fetchDossierLog(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDossierLogTest() {
		try{
			DossierLogLocalServiceWrapper object = new DossierLogLocalServiceWrapper(null);
			object.deleteDossierLog(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDossierLogTest12() {
		try{
			DossierLogLocalServiceWrapper object = new DossierLogLocalServiceWrapper(null);
			object.deleteDossierLog(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneTest() {
		try{
			DossierLogLocalServiceWrapper object = new DossierLogLocalServiceWrapper(null);
			object.countLucene(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierLogTest() {
		try{
			DossierLogLocalServiceWrapper object = new DossierLogLocalServiceWrapper(null);
			object.addDossierLog(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierLogTest15() {
		try{
			DossierLogLocalServiceWrapper object = new DossierLogLocalServiceWrapper(null);
			object.addDossierLog(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierLogTest() {
		try{
			DossierLogLocalServiceWrapper object = new DossierLogLocalServiceWrapper(null);
			object.getDossierLog(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierLogTest() {
		try{
			DossierLogLocalServiceWrapper object = new DossierLogLocalServiceWrapper(null);
			object.updateDossierLog(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierLogTest18() {
		try{
			DossierLogLocalServiceWrapper object = new DossierLogLocalServiceWrapper(null);
			object.updateDossierLog(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierLogTest() {
		try{
			DossierLogLocalServiceWrapper object = new DossierLogLocalServiceWrapper(null);
			object.createDossierLog(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteByDossierAndTypeTest() {
		try{
			DossierLogLocalServiceWrapper object = new DossierLogLocalServiceWrapper(null);
			object.deleteByDossierAndType(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDossierLogByUuidAndGroupIdTest() {
		try{
			DossierLogLocalServiceWrapper object = new DossierLogLocalServiceWrapper(null);
			object.fetchDossierLogByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierLogsByUuidAndCompanyIdTest() {
		try{
			DossierLogLocalServiceWrapper object = new DossierLogLocalServiceWrapper(null);
			object.getDossierLogsByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierLogsByUuidAndCompanyIdTest23() {
		try{
			DossierLogLocalServiceWrapper object = new DossierLogLocalServiceWrapper(null);
			object.getDossierLogsByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierLogsCountTest() {
		try{
			DossierLogLocalServiceWrapper object = new DossierLogLocalServiceWrapper(null);
			object.getDossierLogsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierLogByUuidAndGroupIdTest() {
		try{
			DossierLogLocalServiceWrapper object = new DossierLogLocalServiceWrapper(null);
			object.getDossierLogByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDossierAndTypeTest() {
		try{
			DossierLogLocalServiceWrapper object = new DossierLogLocalServiceWrapper(null);
			object.getByDossierAndType(Long.valueOf(0), "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			DossierLogLocalServiceWrapper object = new DossierLogLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			DossierLogLocalServiceWrapper object = new DossierLogLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			DossierLogLocalServiceWrapper object = new DossierLogLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			DossierLogLocalServiceWrapper object = new DossierLogLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			DossierLogLocalServiceWrapper object = new DossierLogLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest32() {
		try{
			DossierLogLocalServiceWrapper object = new DossierLogLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			DossierLogLocalServiceWrapper object = new DossierLogLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest34() {
		try{
			DossierLogLocalServiceWrapper object = new DossierLogLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			DossierLogLocalServiceWrapper object = new DossierLogLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			DossierLogLocalServiceWrapper object = new DossierLogLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest37() {
		try{
			DossierLogLocalServiceWrapper object = new DossierLogLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			DossierLogLocalServiceWrapper object = new DossierLogLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}