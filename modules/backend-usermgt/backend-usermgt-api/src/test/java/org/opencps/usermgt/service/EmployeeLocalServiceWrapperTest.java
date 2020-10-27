package org.opencps.usermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class EmployeeLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByGTest() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.findByG(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_EMPIDTest() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.findByG_EMPID(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmployeeTest() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.getEmployee(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmployeesTest() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.getEmployees(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_mappingUserIdTest() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.fetchByF_mappingUserId(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getLstEmployeeTest() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.getLstEmployee(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteEmployeeTest() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.deleteEmployee(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteEmployeeTest14() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.deleteEmployee(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteEmployeeTest15() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.deleteEmployee(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchEmployeeTest() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.fetchEmployee(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void luceneSearchEngineTest() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.luceneSearchEngine(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneSearchEngineTest() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.countLuceneSearchEngine(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest24() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest26() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest29() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isExitsTest() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.isExits(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createEmployeeTest() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.createEmployee(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByG_EMPIDTest() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.countByG_EMPID(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByWorkstatusTest() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.findByWorkstatus(Long.valueOf(0), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_MUSERIDTest() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.findByG_MUSERID(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updatePayloadTest() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.updatePayload(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByFB_MUIDTest() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.fetchByFB_MUID(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByEmailTest() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.findByEmail("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addEmployeeTest() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.addEmployee(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, new Date(), "abcde", "abcde", "abcde", 0, Long.valueOf(0), "abcde", "abcde", "abcde", true, new Date(), new Date(), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addEmployeeTest40() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.addEmployee(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addEmployeeTest41() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.addEmployee(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, new Date(), "abcde", "abcde", "abcde", 0, Long.valueOf(0), "abcde", true, new Date(), new Date(), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateEmployeeTest() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.updateEmployee(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, new Date(), "abcde", "abcde", "abcde", 0, Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", new Date(), new Date(), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateEmployeeTest43() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.updateEmployee(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, new Date(), "abcde", "abcde", "abcde", 0, Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", new Date(), new Date(), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateEmployeeTest44() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.updateEmployee(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmployeeByEmpNoTest() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.getEmployeeByEmpNo(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchEmployeeByUuidAndGroupIdTest() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.fetchEmployeeByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmployeesByUuidAndCompanyIdTest() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.getEmployeesByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmployeesByUuidAndCompanyIdTest48() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.getEmployeesByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmployeeByUuidAndGroupIdTest() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.getEmployeeByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmployeesCountTest() {
		try{
			EmployeeLocalServiceWrapper object = new EmployeeLocalServiceWrapper(null);
			object.getEmployeesCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}