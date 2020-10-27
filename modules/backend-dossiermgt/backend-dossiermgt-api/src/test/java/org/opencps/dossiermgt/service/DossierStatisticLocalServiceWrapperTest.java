package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierStatisticLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void insertTest() {
		try{
			DossierStatisticLocalServiceWrapper object = new DossierStatisticLocalServiceWrapper(null);
			object.insert(Long.valueOf(0), 0, 0, 0, 0, 0, 0, 0, 0, 0, "abcde", "abcde", "abcde", "abcde", 0, true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			DossierStatisticLocalServiceWrapper object = new DossierStatisticLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			DossierStatisticLocalServiceWrapper object = new DossierStatisticLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			DossierStatisticLocalServiceWrapper object = new DossierStatisticLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			DossierStatisticLocalServiceWrapper object = new DossierStatisticLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			DossierStatisticLocalServiceWrapper object = new DossierStatisticLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			DossierStatisticLocalServiceWrapper object = new DossierStatisticLocalServiceWrapper(null);
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierStatisticTest() {
		try{
			DossierStatisticLocalServiceWrapper object = new DossierStatisticLocalServiceWrapper(null);
			object.getDossierStatistic(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneTest() {
		try{
			DossierStatisticLocalServiceWrapper object = new DossierStatisticLocalServiceWrapper(null);
			object.countLucene(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByG_M_YTest() {
		try{
			DossierStatisticLocalServiceWrapper object = new DossierStatisticLocalServiceWrapper(null);
			object.fetchByG_M_Y(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDossierStatisticByUuidAndGroupIdTest() {
		try{
			DossierStatisticLocalServiceWrapper object = new DossierStatisticLocalServiceWrapper(null);
			object.fetchDossierStatisticByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierStatisticByUuidAndGroupIdTest() {
		try{
			DossierStatisticLocalServiceWrapper object = new DossierStatisticLocalServiceWrapper(null);
			object.getDossierStatisticByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierStatisticsByUuidAndCompanyIdTest() {
		try{
			DossierStatisticLocalServiceWrapper object = new DossierStatisticLocalServiceWrapper(null);
			object.getDossierStatisticsByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierStatisticsByUuidAndCompanyIdTest14() {
		try{
			DossierStatisticLocalServiceWrapper object = new DossierStatisticLocalServiceWrapper(null);
			object.getDossierStatisticsByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierStatisticTest() {
		try{
			DossierStatisticLocalServiceWrapper object = new DossierStatisticLocalServiceWrapper(null);
			object.addDossierStatistic(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierStatisticTest() {
		try{
			DossierStatisticLocalServiceWrapper object = new DossierStatisticLocalServiceWrapper(null);
			object.createDossierStatistic(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierStatisticsTest() {
		try{
			DossierStatisticLocalServiceWrapper object = new DossierStatisticLocalServiceWrapper(null);
			object.getDossierStatistics(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierStatisticsCountTest() {
		try{
			DossierStatisticLocalServiceWrapper object = new DossierStatisticLocalServiceWrapper(null);
			object.getDossierStatisticsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierStatisticTest() {
		try{
			DossierStatisticLocalServiceWrapper object = new DossierStatisticLocalServiceWrapper(null);
			object.updateDossierStatistic(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDossierStatisticTest() {
		try{
			DossierStatisticLocalServiceWrapper object = new DossierStatisticLocalServiceWrapper(null);
			object.fetchDossierStatistic(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDossierStatisticTest() {
		try{
			DossierStatisticLocalServiceWrapper object = new DossierStatisticLocalServiceWrapper(null);
			object.deleteDossierStatistic(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDossierStatisticTest22() {
		try{
			DossierStatisticLocalServiceWrapper object = new DossierStatisticLocalServiceWrapper(null);
			object.deleteDossierStatistic(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierStatisticbyYearTest() {
		try{
			DossierStatisticLocalServiceWrapper object = new DossierStatisticLocalServiceWrapper(null);
			object.getDossierStatisticbyYear(Long.valueOf(0), Long.valueOf(0), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			DossierStatisticLocalServiceWrapper object = new DossierStatisticLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			DossierStatisticLocalServiceWrapper object = new DossierStatisticLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			DossierStatisticLocalServiceWrapper object = new DossierStatisticLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			DossierStatisticLocalServiceWrapper object = new DossierStatisticLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			DossierStatisticLocalServiceWrapper object = new DossierStatisticLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest29() {
		try{
			DossierStatisticLocalServiceWrapper object = new DossierStatisticLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			DossierStatisticLocalServiceWrapper object = new DossierStatisticLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest31() {
		try{
			DossierStatisticLocalServiceWrapper object = new DossierStatisticLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			DossierStatisticLocalServiceWrapper object = new DossierStatisticLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			DossierStatisticLocalServiceWrapper object = new DossierStatisticLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest34() {
		try{
			DossierStatisticLocalServiceWrapper object = new DossierStatisticLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}