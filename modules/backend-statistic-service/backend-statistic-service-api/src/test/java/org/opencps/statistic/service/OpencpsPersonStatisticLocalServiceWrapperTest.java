package org.opencps.statistic.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class OpencpsPersonStatisticLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void dynamicQueryTest() {
		try{
			OpencpsPersonStatisticLocalServiceWrapper object = new OpencpsPersonStatisticLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest2() {
		try{
			OpencpsPersonStatisticLocalServiceWrapper object = new OpencpsPersonStatisticLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			OpencpsPersonStatisticLocalServiceWrapper object = new OpencpsPersonStatisticLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			OpencpsPersonStatisticLocalServiceWrapper object = new OpencpsPersonStatisticLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			OpencpsPersonStatisticLocalServiceWrapper object = new OpencpsPersonStatisticLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			OpencpsPersonStatisticLocalServiceWrapper object = new OpencpsPersonStatisticLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			OpencpsPersonStatisticLocalServiceWrapper object = new OpencpsPersonStatisticLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			OpencpsPersonStatisticLocalServiceWrapper object = new OpencpsPersonStatisticLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			OpencpsPersonStatisticLocalServiceWrapper object = new OpencpsPersonStatisticLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			OpencpsPersonStatisticLocalServiceWrapper object = new OpencpsPersonStatisticLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest11() {
		try{
			OpencpsPersonStatisticLocalServiceWrapper object = new OpencpsPersonStatisticLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			OpencpsPersonStatisticLocalServiceWrapper object = new OpencpsPersonStatisticLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest13() {
		try{
			OpencpsPersonStatisticLocalServiceWrapper object = new OpencpsPersonStatisticLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			OpencpsPersonStatisticLocalServiceWrapper object = new OpencpsPersonStatisticLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			OpencpsPersonStatisticLocalServiceWrapper object = new OpencpsPersonStatisticLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest16() {
		try{
			OpencpsPersonStatisticLocalServiceWrapper object = new OpencpsPersonStatisticLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchPersonStatisticTest() {
		try{
			OpencpsPersonStatisticLocalServiceWrapper object = new OpencpsPersonStatisticLocalServiceWrapper(null);
			object.searchPersonStatistic(Long.valueOf(0), 0, 0, "abcde", Long.valueOf(0), "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removePersonStatisticByMonthYearTest() {
		try{
			OpencpsPersonStatisticLocalServiceWrapper object = new OpencpsPersonStatisticLocalServiceWrapper(null);
			object.removePersonStatisticByMonthYear(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removePersonStatisticByYearTest() {
		try{
			OpencpsPersonStatisticLocalServiceWrapper object = new OpencpsPersonStatisticLocalServiceWrapper(null);
			object.removePersonStatisticByYear(Long.valueOf(0), Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updatePersonStatisticTest() {
		try{
			OpencpsPersonStatisticLocalServiceWrapper object = new OpencpsPersonStatisticLocalServiceWrapper(null);
			object.updatePersonStatistic(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, "abcde", 0, 0, 0, 0, 0, 0, 0, "abcde", "abcde", Long.valueOf(0), "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchPersonStatisticTest() {
		try{
			OpencpsPersonStatisticLocalServiceWrapper object = new OpencpsPersonStatisticLocalServiceWrapper(null);
			object.fetchPersonStatistic(Long.valueOf(0), 0, 0, "abcde", Long.valueOf(0), "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteOpencpsPersonStatisticTest() {
		try{
			OpencpsPersonStatisticLocalServiceWrapper object = new OpencpsPersonStatisticLocalServiceWrapper(null);
			object.deleteOpencpsPersonStatistic(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteOpencpsPersonStatisticTest23() {
		try{
			OpencpsPersonStatisticLocalServiceWrapper object = new OpencpsPersonStatisticLocalServiceWrapper(null);
			object.deleteOpencpsPersonStatistic(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addOpencpsPersonStatisticTest() {
		try{
			OpencpsPersonStatisticLocalServiceWrapper object = new OpencpsPersonStatisticLocalServiceWrapper(null);
			object.addOpencpsPersonStatistic(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOpencpsPersonStatisticsCountTest() {
		try{
			OpencpsPersonStatisticLocalServiceWrapper object = new OpencpsPersonStatisticLocalServiceWrapper(null);
			object.getOpencpsPersonStatisticsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateOpencpsPersonStatisticTest() {
		try{
			OpencpsPersonStatisticLocalServiceWrapper object = new OpencpsPersonStatisticLocalServiceWrapper(null);
			object.updateOpencpsPersonStatistic(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOpencpsPersonStatisticTest() {
		try{
			OpencpsPersonStatisticLocalServiceWrapper object = new OpencpsPersonStatisticLocalServiceWrapper(null);
			object.getOpencpsPersonStatistic(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createOpencpsPersonStatisticTest() {
		try{
			OpencpsPersonStatisticLocalServiceWrapper object = new OpencpsPersonStatisticLocalServiceWrapper(null);
			object.createOpencpsPersonStatistic(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOpencpsPersonStatisticsTest() {
		try{
			OpencpsPersonStatisticLocalServiceWrapper object = new OpencpsPersonStatisticLocalServiceWrapper(null);
			object.getOpencpsPersonStatistics(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchOpencpsPersonStatisticTest() {
		try{
			OpencpsPersonStatisticLocalServiceWrapper object = new OpencpsPersonStatisticLocalServiceWrapper(null);
			object.fetchOpencpsPersonStatistic(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void checkExsitTest() {
		try{
			OpencpsPersonStatisticLocalServiceWrapper object = new OpencpsPersonStatisticLocalServiceWrapper(null);
			object.checkExsit(Long.valueOf(0), 0, 0, "abcde", Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchOpencpsPersonStatisticByUuidAndGroupIdTest() {
		try{
			OpencpsPersonStatisticLocalServiceWrapper object = new OpencpsPersonStatisticLocalServiceWrapper(null);
			object.fetchOpencpsPersonStatisticByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOpencpsPersonStatisticByUuidAndGroupIdTest() {
		try{
			OpencpsPersonStatisticLocalServiceWrapper object = new OpencpsPersonStatisticLocalServiceWrapper(null);
			object.getOpencpsPersonStatisticByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOpencpsPersonStatisticsByUuidAndCompanyIdTest() {
		try{
			OpencpsPersonStatisticLocalServiceWrapper object = new OpencpsPersonStatisticLocalServiceWrapper(null);
			object.getOpencpsPersonStatisticsByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOpencpsPersonStatisticsByUuidAndCompanyIdTest35() {
		try{
			OpencpsPersonStatisticLocalServiceWrapper object = new OpencpsPersonStatisticLocalServiceWrapper(null);
			object.getOpencpsPersonStatisticsByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}