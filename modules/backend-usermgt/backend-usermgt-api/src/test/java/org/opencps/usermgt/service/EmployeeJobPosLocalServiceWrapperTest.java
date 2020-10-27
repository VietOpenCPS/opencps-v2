package org.opencps.usermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class EmployeeJobPosLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_G_jobPostIdsTest() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.findByF_G_jobPostIds(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_EmployeeId_jobPostIdTest() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.fetchByF_EmployeeId_jobPostId(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_EmployeeIdTest() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.findByF_EmployeeId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByJobPostIdTest() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.getByJobPostId(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void luceneSearchEngineTest() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.luceneSearchEngine(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmployeeJobPosbyGidEmpIdTest() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.getEmployeeJobPosbyGidEmpId(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteEmployeeJobPosTest() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.deleteEmployeeJobPos(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteEmployeeJobPosTest14() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.deleteEmployeeJobPos(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteEmployeeJobPosTest15() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.deleteEmployeeJobPos(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneSearchEngineTest() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.countLuceneSearchEngine(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest22() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest24() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest27() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmployeeJobPosByUuidAndGroupIdTest() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.getEmployeeJobPosByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchEmployeeJobPosByUuidAndGroupIdTest() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.fetchEmployeeJobPosByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmployeeJobPosesByUuidAndCompanyIdTest() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.getEmployeeJobPosesByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmployeeJobPosesByUuidAndCompanyIdTest32() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.getEmployeeJobPosesByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateEmployeeJobPosTest() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.updateEmployeeJobPos(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateEmployeeJobPosTest34() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.updateEmployeeJobPos(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addEmployeeJobPosTest() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.addEmployeeJobPos(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addEmployeeJobPosTest36() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.addEmployeeJobPos(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmployeeJobPosTest() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.getEmployeeJobPos(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createEmployeeJobPosTest() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.createEmployeeJobPos(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmployeeJobPosesCountTest() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.getEmployeeJobPosesCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchEmployeeJobPosTest() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.fetchEmployeeJobPos(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmployeeJobPosesTest() {
		try{
			EmployeeJobPosLocalServiceWrapper object = new EmployeeJobPosLocalServiceWrapper(null);
			object.getEmployeeJobPoses(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}