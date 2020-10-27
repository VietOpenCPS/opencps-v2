package org.opencps.statistic.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class OpencpsVotingStatisticLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void dynamicQueryTest() {
		try{
			OpencpsVotingStatisticLocalServiceWrapper object = new OpencpsVotingStatisticLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest2() {
		try{
			OpencpsVotingStatisticLocalServiceWrapper object = new OpencpsVotingStatisticLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			OpencpsVotingStatisticLocalServiceWrapper object = new OpencpsVotingStatisticLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			OpencpsVotingStatisticLocalServiceWrapper object = new OpencpsVotingStatisticLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			OpencpsVotingStatisticLocalServiceWrapper object = new OpencpsVotingStatisticLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			OpencpsVotingStatisticLocalServiceWrapper object = new OpencpsVotingStatisticLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			OpencpsVotingStatisticLocalServiceWrapper object = new OpencpsVotingStatisticLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			OpencpsVotingStatisticLocalServiceWrapper object = new OpencpsVotingStatisticLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			OpencpsVotingStatisticLocalServiceWrapper object = new OpencpsVotingStatisticLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			OpencpsVotingStatisticLocalServiceWrapper object = new OpencpsVotingStatisticLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest11() {
		try{
			OpencpsVotingStatisticLocalServiceWrapper object = new OpencpsVotingStatisticLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			OpencpsVotingStatisticLocalServiceWrapper object = new OpencpsVotingStatisticLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest13() {
		try{
			OpencpsVotingStatisticLocalServiceWrapper object = new OpencpsVotingStatisticLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			OpencpsVotingStatisticLocalServiceWrapper object = new OpencpsVotingStatisticLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			OpencpsVotingStatisticLocalServiceWrapper object = new OpencpsVotingStatisticLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest16() {
		try{
			OpencpsVotingStatisticLocalServiceWrapper object = new OpencpsVotingStatisticLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchVotingStatisticTest() {
		try{
			OpencpsVotingStatisticLocalServiceWrapper object = new OpencpsVotingStatisticLocalServiceWrapper(null);
			object.searchVotingStatistic(Long.valueOf(0), 0, 0, "abcde", "abcde", "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeVotingStatisticByMonthYearTest() {
		try{
			OpencpsVotingStatisticLocalServiceWrapper object = new OpencpsVotingStatisticLocalServiceWrapper(null);
			object.removeVotingStatisticByMonthYear(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateVotingStatisticTest() {
		try{
			OpencpsVotingStatisticLocalServiceWrapper object = new OpencpsVotingStatisticLocalServiceWrapper(null);
			object.updateVotingStatistic(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, "abcde", 0, 0, 0, 0, 0, 0, 0, "abcde", "abcde", "abcde", "abcde", "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeVotingStatisticByD_M_YTest() {
		try{
			OpencpsVotingStatisticLocalServiceWrapper object = new OpencpsVotingStatisticLocalServiceWrapper(null);
			object.removeVotingStatisticByD_M_Y(Long.valueOf(0), "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeVotingStatisticByYearTest() {
		try{
			OpencpsVotingStatisticLocalServiceWrapper object = new OpencpsVotingStatisticLocalServiceWrapper(null);
			object.removeVotingStatisticByYear(Long.valueOf(0), Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByG_M_Y_G_D_VCTest() {
		try{
			OpencpsVotingStatisticLocalServiceWrapper object = new OpencpsVotingStatisticLocalServiceWrapper(null);
			object.fetchByG_M_Y_G_D_VC(Long.valueOf(0), 0, 0, "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchVotingStatisticTest() {
		try{
			OpencpsVotingStatisticLocalServiceWrapper object = new OpencpsVotingStatisticLocalServiceWrapper(null);
			object.fetchVotingStatistic(Long.valueOf(0), 0, 0, "abcde", "abcde", "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOpencpsVotingStatisticsCountTest() {
		try{
			OpencpsVotingStatisticLocalServiceWrapper object = new OpencpsVotingStatisticLocalServiceWrapper(null);
			object.getOpencpsVotingStatisticsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteOpencpsVotingStatisticTest() {
		try{
			OpencpsVotingStatisticLocalServiceWrapper object = new OpencpsVotingStatisticLocalServiceWrapper(null);
			object.deleteOpencpsVotingStatistic(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteOpencpsVotingStatisticTest26() {
		try{
			OpencpsVotingStatisticLocalServiceWrapper object = new OpencpsVotingStatisticLocalServiceWrapper(null);
			object.deleteOpencpsVotingStatistic(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchOpencpsVotingStatisticTest() {
		try{
			OpencpsVotingStatisticLocalServiceWrapper object = new OpencpsVotingStatisticLocalServiceWrapper(null);
			object.fetchOpencpsVotingStatistic(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOpencpsVotingStatisticTest() {
		try{
			OpencpsVotingStatisticLocalServiceWrapper object = new OpencpsVotingStatisticLocalServiceWrapper(null);
			object.getOpencpsVotingStatistic(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOpencpsVotingStatisticsTest() {
		try{
			OpencpsVotingStatisticLocalServiceWrapper object = new OpencpsVotingStatisticLocalServiceWrapper(null);
			object.getOpencpsVotingStatistics(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addOpencpsVotingStatisticTest() {
		try{
			OpencpsVotingStatisticLocalServiceWrapper object = new OpencpsVotingStatisticLocalServiceWrapper(null);
			object.addOpencpsVotingStatistic(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createOpencpsVotingStatisticTest() {
		try{
			OpencpsVotingStatisticLocalServiceWrapper object = new OpencpsVotingStatisticLocalServiceWrapper(null);
			object.createOpencpsVotingStatistic(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateOpencpsVotingStatisticTest() {
		try{
			OpencpsVotingStatisticLocalServiceWrapper object = new OpencpsVotingStatisticLocalServiceWrapper(null);
			object.updateOpencpsVotingStatistic(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void checkExsitTest() {
		try{
			OpencpsVotingStatisticLocalServiceWrapper object = new OpencpsVotingStatisticLocalServiceWrapper(null);
			object.checkExsit(Long.valueOf(0), 0, 0, "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByG_M_Y_G_DTest() {
		try{
			OpencpsVotingStatisticLocalServiceWrapper object = new OpencpsVotingStatisticLocalServiceWrapper(null);
			object.fetchByG_M_Y_G_D(Long.valueOf(0), 0, 0, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchOpencpsVotingStatisticByUuidAndGroupIdTest() {
		try{
			OpencpsVotingStatisticLocalServiceWrapper object = new OpencpsVotingStatisticLocalServiceWrapper(null);
			object.fetchOpencpsVotingStatisticByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOpencpsVotingStatisticsByUuidAndCompanyIdTest() {
		try{
			OpencpsVotingStatisticLocalServiceWrapper object = new OpencpsVotingStatisticLocalServiceWrapper(null);
			object.getOpencpsVotingStatisticsByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOpencpsVotingStatisticsByUuidAndCompanyIdTest37() {
		try{
			OpencpsVotingStatisticLocalServiceWrapper object = new OpencpsVotingStatisticLocalServiceWrapper(null);
			object.getOpencpsVotingStatisticsByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOpencpsVotingStatisticByUuidAndGroupIdTest() {
		try{
			OpencpsVotingStatisticLocalServiceWrapper object = new OpencpsVotingStatisticLocalServiceWrapper(null);
			object.getOpencpsVotingStatisticByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}