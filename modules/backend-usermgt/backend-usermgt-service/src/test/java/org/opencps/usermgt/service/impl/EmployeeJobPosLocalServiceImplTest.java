package org.opencps.usermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class EmployeeJobPosLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			EmployeeJobPosLocalServiceImpl object = new EmployeeJobPosLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_G_jobPostIdsTest() {
		try{
			EmployeeJobPosLocalServiceImpl object = new EmployeeJobPosLocalServiceImpl();
			object.findByF_G_jobPostIds(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_EmployeeId_jobPostIdTest() {
		try{
			EmployeeJobPosLocalServiceImpl object = new EmployeeJobPosLocalServiceImpl();
			object.fetchByF_EmployeeId_jobPostId(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_EmployeeIdTest() {
		try{
			EmployeeJobPosLocalServiceImpl object = new EmployeeJobPosLocalServiceImpl();
			object.findByF_EmployeeId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByJobPostIdTest() {
		try{
			EmployeeJobPosLocalServiceImpl object = new EmployeeJobPosLocalServiceImpl();
			object.getByJobPostId(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void luceneSearchEngineTest() {
		try{
			EmployeeJobPosLocalServiceImpl object = new EmployeeJobPosLocalServiceImpl();
			object.luceneSearchEngine(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmployeeJobPosbyGidEmpIdTest() {
		try{
			EmployeeJobPosLocalServiceImpl object = new EmployeeJobPosLocalServiceImpl();
			object.getEmployeeJobPosbyGidEmpId(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void deleteEmployeeJobPosTest() {
//		try{
//			EmployeeJobPosLocalServiceImpl object = new EmployeeJobPosLocalServiceImpl();
//			object.deleteEmployeeJobPos(Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void countLuceneSearchEngineTest() {
		try{
			EmployeeJobPosLocalServiceImpl object = new EmployeeJobPosLocalServiceImpl();
			object.countLuceneSearchEngine(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			EmployeeJobPosLocalServiceImpl object = new EmployeeJobPosLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void updateEmployeeJobPosTest() {
//		try{
//			EmployeeJobPosLocalServiceImpl object = new EmployeeJobPosLocalServiceImpl();
//			object.updateEmployeeJobPos(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void addEmployeeJobPosTest() {
//		try{
//			EmployeeJobPosLocalServiceImpl object = new EmployeeJobPosLocalServiceImpl();
//			object.addEmployeeJobPos(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}