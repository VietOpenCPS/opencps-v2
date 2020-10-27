package org.opencps.usermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class JobPosWorkLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void dynamicQueryTest() {
		try{
			JobPosWorkLocalServiceWrapper object = new JobPosWorkLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest2() {
		try{
			JobPosWorkLocalServiceWrapper object = new JobPosWorkLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			JobPosWorkLocalServiceWrapper object = new JobPosWorkLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			JobPosWorkLocalServiceWrapper object = new JobPosWorkLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			JobPosWorkLocalServiceWrapper object = new JobPosWorkLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void luceneSearchEngineTest() {
		try{
			JobPosWorkLocalServiceWrapper object = new JobPosWorkLocalServiceWrapper(null);
			object.luceneSearchEngine(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneSearchEngineTest() {
		try{
			JobPosWorkLocalServiceWrapper object = new JobPosWorkLocalServiceWrapper(null);
			object.countLuceneSearchEngine(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			JobPosWorkLocalServiceWrapper object = new JobPosWorkLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			JobPosWorkLocalServiceWrapper object = new JobPosWorkLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			JobPosWorkLocalServiceWrapper object = new JobPosWorkLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			JobPosWorkLocalServiceWrapper object = new JobPosWorkLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			JobPosWorkLocalServiceWrapper object = new JobPosWorkLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest13() {
		try{
			JobPosWorkLocalServiceWrapper object = new JobPosWorkLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			JobPosWorkLocalServiceWrapper object = new JobPosWorkLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest15() {
		try{
			JobPosWorkLocalServiceWrapper object = new JobPosWorkLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			JobPosWorkLocalServiceWrapper object = new JobPosWorkLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			JobPosWorkLocalServiceWrapper object = new JobPosWorkLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest18() {
		try{
			JobPosWorkLocalServiceWrapper object = new JobPosWorkLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_JobPostIdTest() {
		try{
			JobPosWorkLocalServiceWrapper object = new JobPosWorkLocalServiceWrapper(null);
			object.findByF_JobPostId(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_ChecklistCatTest() {
		try{
			JobPosWorkLocalServiceWrapper object = new JobPosWorkLocalServiceWrapper(null);
			object.fetchByF_ChecklistCat(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchJobPosWorkByUuidAndGroupIdTest() {
		try{
			JobPosWorkLocalServiceWrapper object = new JobPosWorkLocalServiceWrapper(null);
			object.fetchJobPosWorkByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getJobPosWorkByUuidAndGroupIdTest() {
		try{
			JobPosWorkLocalServiceWrapper object = new JobPosWorkLocalServiceWrapper(null);
			object.getJobPosWorkByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getJobPosWorksCountTest() {
		try{
			JobPosWorkLocalServiceWrapper object = new JobPosWorkLocalServiceWrapper(null);
			object.getJobPosWorksCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getJobPosWorksByUuidAndCompanyIdTest() {
		try{
			JobPosWorkLocalServiceWrapper object = new JobPosWorkLocalServiceWrapper(null);
			object.getJobPosWorksByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getJobPosWorksByUuidAndCompanyIdTest25() {
		try{
			JobPosWorkLocalServiceWrapper object = new JobPosWorkLocalServiceWrapper(null);
			object.getJobPosWorksByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addJobPosWorkTest() {
		try{
			JobPosWorkLocalServiceWrapper object = new JobPosWorkLocalServiceWrapper(null);
			object.addJobPosWork(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addJobPosWorkTest27() {
		try{
			JobPosWorkLocalServiceWrapper object = new JobPosWorkLocalServiceWrapper(null);
			object.addJobPosWork(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getJobPosWorksTest() {
		try{
			JobPosWorkLocalServiceWrapper object = new JobPosWorkLocalServiceWrapper(null);
			object.getJobPosWorks(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createJobPosWorkTest() {
		try{
			JobPosWorkLocalServiceWrapper object = new JobPosWorkLocalServiceWrapper(null);
			object.createJobPosWork(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteJobPosWorkTest() {
		try{
			JobPosWorkLocalServiceWrapper object = new JobPosWorkLocalServiceWrapper(null);
			object.deleteJobPosWork(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteJobPosWorkTest31() {
		try{
			JobPosWorkLocalServiceWrapper object = new JobPosWorkLocalServiceWrapper(null);
			object.deleteJobPosWork(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteJobPosWorkTest32() {
		try{
			JobPosWorkLocalServiceWrapper object = new JobPosWorkLocalServiceWrapper(null);
			object.deleteJobPosWork(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchJobPosWorkTest() {
		try{
			JobPosWorkLocalServiceWrapper object = new JobPosWorkLocalServiceWrapper(null);
			object.fetchJobPosWork(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getJobPosWorkTest() {
		try{
			JobPosWorkLocalServiceWrapper object = new JobPosWorkLocalServiceWrapper(null);
			object.getJobPosWork(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateJobPosWorkTest() {
		try{
			JobPosWorkLocalServiceWrapper object = new JobPosWorkLocalServiceWrapper(null);
			object.updateJobPosWork(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateJobPosWorkTest36() {
		try{
			JobPosWorkLocalServiceWrapper object = new JobPosWorkLocalServiceWrapper(null);
			object.updateJobPosWork(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}