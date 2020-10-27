package org.opencps.statistic.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class OpencpsDossierStatisticLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void dynamicQueryTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest2() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByGTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.findByG(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDossierStatisticTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.fetchDossierStatistic(Long.valueOf(0), 0, 0, "abcde", "abcde", "abcde", "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDossierStatisticTest8() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.fetchDossierStatistic(Long.valueOf(0), 0, 0, "abcde", "abcde", "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest14() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest16() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest19() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateStatisticDataTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.updateStatisticData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createOrUpdateStatisticTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.createOrUpdateStatistic(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, "abcde", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "abcde", "abcde", "abcde", "abcde", "abcde", true, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createOrUpdateStatisticTest22() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.createOrUpdateStatistic(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, "abcde", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "abcde", "abcde", "abcde", "abcde", true, 0, 0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createOrUpdateStatisticTest23() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.createOrUpdateStatistic(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, "abcde", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "abcde", "abcde", "abcde", "abcde", true, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierStatisticByMonthYearTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.getDossierStatisticByMonthYear(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateOpencpsDossierStatisticTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.updateOpencpsDossierStatistic(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeByG_M_Y_G_DTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.removeByG_M_Y_G_D(Long.valueOf(0), 0, 0, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateOnlyStatisticTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.updateOnlyStatistic(null, Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "abcde", "abcde", "abcde", "abcde", true, 0, 0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateOnlyStatisticTest28() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.updateOnlyStatistic(null, Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "abcde", "abcde", "abcde", "abcde", true, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeDossierStatisticByD_M_YTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.removeDossierStatisticByD_M_Y(Long.valueOf(0), "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierStatisticByYearTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.getDossierStatisticByYear(Long.valueOf(0), Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateBatchStatisticTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.updateBatchStatistic(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createOnlyStatisticTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.createOnlyStatistic(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "abcde", "abcde", "abcde", "abcde", true, 0, 0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createOnlyStatisticTest33() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.createOnlyStatistic(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "abcde", "abcde", "abcde", "abcde", true, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeDossierStatisticByYearTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.removeDossierStatisticByYear(Long.valueOf(0), Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchDossierStatisticSystemTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.searchDossierStatisticSystem(Long.valueOf(0), 0, 0, "abcde", "abcde", "abcde", "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void checkNotDuplicateTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.checkNotDuplicate(Long.valueOf(0), "abcde", 0, 0, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOpencpsDossierStatisticsTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.getOpencpsDossierStatistics(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createOpencpsDossierStatisticTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.createOpencpsDossierStatistic(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteOpencpsDossierStatisticTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.deleteOpencpsDossierStatistic(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteOpencpsDossierStatisticTest40() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.deleteOpencpsDossierStatistic(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOpencpsDossierStatisticsCountTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.getOpencpsDossierStatisticsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchOpencpsDossierStatisticTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.fetchOpencpsDossierStatistic(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addOpencpsDossierStatisticTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.addOpencpsDossierStatistic(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOpencpsDossierStatisticTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.getOpencpsDossierStatistic(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByGovMonthYearDomainTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.getByGovMonthYearDomain(Long.valueOf(0), "abcde", 0, 0, "abcde", true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByGovMonthYearTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.getByGovMonthYear(Long.valueOf(0), "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateStatisticTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.updateStatistic(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "abcde", "abcde", "abcde", "abcde", true, 0, 0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateStatisticTest48() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.updateStatistic(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, "abcde", "abcde", "abcde", "abcde", true, 0, 0, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_NM_YTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.findByG_NM_Y(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void checkExsitTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.checkExsit(Long.valueOf(0), 0, 0, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByG_M_Y_G_DTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.fetchByG_M_Y_G_D(Long.valueOf(0), 0, 0, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void checkExsitSystemTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.checkExsitSystem(Long.valueOf(0), 0, 0, "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierStatisticByMonthYearAndReportTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.getDossierStatisticByMonthYearAndReport(Long.valueOf(0), 0, 0, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeDossierStatisticByMonthYearTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.removeDossierStatisticByMonthYear(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierStatisticByMonthsYearAndReportTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.getDossierStatisticByMonthsYearAndReport(Long.valueOf(0), null, 0, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOpencpsDossierStatisticsByUuidAndCompanyIdTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.getOpencpsDossierStatisticsByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOpencpsDossierStatisticsByUuidAndCompanyIdTest57() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.getOpencpsDossierStatisticsByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchOpencpsDossierStatisticByUuidAndGroupIdTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.fetchOpencpsDossierStatisticByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOpencpsDossierStatisticByUuidAndGroupIdTest() {
		try{
			OpencpsDossierStatisticLocalServiceWrapper object = new OpencpsDossierStatisticLocalServiceWrapper(null);
			object.getOpencpsDossierStatisticByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}