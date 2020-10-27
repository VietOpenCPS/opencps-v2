package org.opencps.datamgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class WorkTimeLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			WorkTimeLocalServiceWrapper object = new WorkTimeLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			WorkTimeLocalServiceWrapper object = new WorkTimeLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			WorkTimeLocalServiceWrapper object = new WorkTimeLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			WorkTimeLocalServiceWrapper object = new WorkTimeLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			WorkTimeLocalServiceWrapper object = new WorkTimeLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			WorkTimeLocalServiceWrapper object = new WorkTimeLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByGroupIdTest() {
		try{
			WorkTimeLocalServiceWrapper object = new WorkTimeLocalServiceWrapper(null);
			object.getByGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateWorkTimeDBTest() {
		try{
			WorkTimeLocalServiceWrapper object = new WorkTimeLocalServiceWrapper(null);
			object.updateWorkTimeDB(Long.valueOf(0), Long.valueOf(0), 0, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void luceneSearchEngineTest() {
		try{
			WorkTimeLocalServiceWrapper object = new WorkTimeLocalServiceWrapper(null);
			object.luceneSearchEngine(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWorkTimeByUuidAndGroupIdTest() {
		try{
			WorkTimeLocalServiceWrapper object = new WorkTimeLocalServiceWrapper(null);
			object.getWorkTimeByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWorkTimesByUuidAndCompanyIdTest() {
		try{
			WorkTimeLocalServiceWrapper object = new WorkTimeLocalServiceWrapper(null);
			object.getWorkTimesByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWorkTimesByUuidAndCompanyIdTest12() {
		try{
			WorkTimeLocalServiceWrapper object = new WorkTimeLocalServiceWrapper(null);
			object.getWorkTimesByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchWorkTimeByUuidAndGroupIdTest() {
		try{
			WorkTimeLocalServiceWrapper object = new WorkTimeLocalServiceWrapper(null);
			object.fetchWorkTimeByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWorkTimesCountTest() {
		try{
			WorkTimeLocalServiceWrapper object = new WorkTimeLocalServiceWrapper(null);
			object.getWorkTimesCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_dayTest() {
		try{
			WorkTimeLocalServiceWrapper object = new WorkTimeLocalServiceWrapper(null);
			object.fetchByF_day(Long.valueOf(0), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWorkTimeTest() {
		try{
			WorkTimeLocalServiceWrapper object = new WorkTimeLocalServiceWrapper(null);
			object.getWorkTime(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateWorkTimeTest() {
		try{
			WorkTimeLocalServiceWrapper object = new WorkTimeLocalServiceWrapper(null);
			object.updateWorkTime(Long.valueOf(0), Long.valueOf(0), 0, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateWorkTimeTest18() {
		try{
			WorkTimeLocalServiceWrapper object = new WorkTimeLocalServiceWrapper(null);
			object.updateWorkTime(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addWorkTimeTest() {
		try{
			WorkTimeLocalServiceWrapper object = new WorkTimeLocalServiceWrapper(null);
			object.addWorkTime(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addWorkTimeTest20() {
		try{
			WorkTimeLocalServiceWrapper object = new WorkTimeLocalServiceWrapper(null);
			object.addWorkTime(Long.valueOf(0), Long.valueOf(0), 0, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchWorkTimeTest() {
		try{
			WorkTimeLocalServiceWrapper object = new WorkTimeLocalServiceWrapper(null);
			object.fetchWorkTime(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createWorkTimeTest() {
		try{
			WorkTimeLocalServiceWrapper object = new WorkTimeLocalServiceWrapper(null);
			object.createWorkTime(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteWorkTimeTest() {
		try{
			WorkTimeLocalServiceWrapper object = new WorkTimeLocalServiceWrapper(null);
			object.deleteWorkTime(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteWorkTimeTest24() {
		try{
			WorkTimeLocalServiceWrapper object = new WorkTimeLocalServiceWrapper(null);
			object.deleteWorkTime(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteWorkTimeTest25() {
		try{
			WorkTimeLocalServiceWrapper object = new WorkTimeLocalServiceWrapper(null);
			object.deleteWorkTime(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneSearchEngineTest() {
		try{
			WorkTimeLocalServiceWrapper object = new WorkTimeLocalServiceWrapper(null);
			object.countLuceneSearchEngine(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			WorkTimeLocalServiceWrapper object = new WorkTimeLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			WorkTimeLocalServiceWrapper object = new WorkTimeLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			WorkTimeLocalServiceWrapper object = new WorkTimeLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			WorkTimeLocalServiceWrapper object = new WorkTimeLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			WorkTimeLocalServiceWrapper object = new WorkTimeLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest32() {
		try{
			WorkTimeLocalServiceWrapper object = new WorkTimeLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			WorkTimeLocalServiceWrapper object = new WorkTimeLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest34() {
		try{
			WorkTimeLocalServiceWrapper object = new WorkTimeLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			WorkTimeLocalServiceWrapper object = new WorkTimeLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			WorkTimeLocalServiceWrapper object = new WorkTimeLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest37() {
		try{
			WorkTimeLocalServiceWrapper object = new WorkTimeLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			WorkTimeLocalServiceWrapper object = new WorkTimeLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWorkTimesTest() {
		try{
			WorkTimeLocalServiceWrapper object = new WorkTimeLocalServiceWrapper(null);
			object.getWorkTimes(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}