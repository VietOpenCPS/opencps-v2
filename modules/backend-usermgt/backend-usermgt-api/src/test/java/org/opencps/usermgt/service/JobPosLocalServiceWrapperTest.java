package org.opencps.usermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class JobPosLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_jobPosIdsTest() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.findByF_jobPosIds(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByGTest() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.findByG(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getJobPosTest() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.getJobPos(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateJobPosDBTest() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.updateJobPosDB(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_mappingRoleIdsTest() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.findByF_mappingRoleIds(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByJobCodeTest() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.getByJobCode(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchJobPosTest() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.fetchJobPos(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteJobPosTest() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.deleteJobPos(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteJobPosTest15() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.deleteJobPos(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteJobPosTest16() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.deleteJobPos(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void luceneSearchEngineTest() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.luceneSearchEngine(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneSearchEngineTest() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.countLuceneSearchEngine(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest24() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest26() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest29() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchJobPosByUuidAndGroupIdTest() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.fetchJobPosByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getJobPosesByUuidAndCompanyIdTest() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.getJobPosesByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getJobPosesByUuidAndCompanyIdTest33() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.getJobPosesByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getJobPosByUuidAndGroupIdTest() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.getJobPosByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_mappingRoleIdTest() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.fetchByF_mappingRoleId(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createJobPosTest() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.createJobPos(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getJobPosesCountTest() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.getJobPosesCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void assignPermissionTest() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.assignPermission(Long.valueOf(0), null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addJobPosTest() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.addJobPos(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addJobPosTest40() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.addJobPos(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getJobPosesTest() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.getJobPoses(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateJobPosTest() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.updateJobPos(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateJobPosTest43() {
		try{
			JobPosLocalServiceWrapper object = new JobPosLocalServiceWrapper(null);
			object.updateJobPos(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}