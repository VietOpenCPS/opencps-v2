package org.opencps.usermgt.service.impl;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class EmployeeLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			EmployeeLocalServiceImpl object = new EmployeeLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByGTest() {
		try{
			EmployeeLocalServiceImpl object = new EmployeeLocalServiceImpl();
			object.findByG(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_EMPIDTest() {
		try{
			EmployeeLocalServiceImpl object = new EmployeeLocalServiceImpl();
			object.findByG_EMPID(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_mappingUserIdTest() {
		try{
			EmployeeLocalServiceImpl object = new EmployeeLocalServiceImpl();
			object.fetchByF_mappingUserId(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getLstEmployeeTest() {
		try{
			EmployeeLocalServiceImpl object = new EmployeeLocalServiceImpl();
			object.getLstEmployee(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteEmployeeTest() {
		try{
			EmployeeLocalServiceImpl object = new EmployeeLocalServiceImpl();
			object.deleteEmployee(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void luceneSearchEngineTest() {
		try{
			EmployeeLocalServiceImpl object = new EmployeeLocalServiceImpl();
			object.luceneSearchEngine(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneSearchEngineTest() {
		try{
			EmployeeLocalServiceImpl object = new EmployeeLocalServiceImpl();
			object.countLuceneSearchEngine(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			EmployeeLocalServiceImpl object = new EmployeeLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isExitsTest() {
		try{
			EmployeeLocalServiceImpl object = new EmployeeLocalServiceImpl();
			object.isExits(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByG_EMPIDTest() {
		try{
			EmployeeLocalServiceImpl object = new EmployeeLocalServiceImpl();
			object.countByG_EMPID(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByWorkstatusTest() {
		try{
			EmployeeLocalServiceImpl object = new EmployeeLocalServiceImpl();
			object.findByWorkstatus(Long.valueOf(0), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByG_MUSERIDTest() {
		try{
			EmployeeLocalServiceImpl object = new EmployeeLocalServiceImpl();
			object.findByG_MUSERID(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updatePayloadTest() {
		try{
			EmployeeLocalServiceImpl object = new EmployeeLocalServiceImpl();
			object.updatePayload(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void fetchByFB_MUIDTest() {
//		try{
//			EmployeeLocalServiceImpl object = new EmployeeLocalServiceImpl();
//			object.fetchByFB_MUID(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void findByEmailTest() {
		try{
			EmployeeLocalServiceImpl object = new EmployeeLocalServiceImpl();
			object.findByEmail("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addEmployeeTest() {
		try{
			EmployeeLocalServiceImpl object = new EmployeeLocalServiceImpl();
			object.addEmployee(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, new Date(), "abcde", "abcde", "abcde", 0, Long.valueOf(0), "abcde", "abcde", "abcde", true, new Date(), new Date(), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addEmployeeTest18() {
		try{
			EmployeeLocalServiceImpl object = new EmployeeLocalServiceImpl();
			object.addEmployee(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, new Date(), "abcde", "abcde", "abcde", 0, Long.valueOf(0), "abcde", true, new Date(), new Date(), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateEmployeeTest() {
		try{
			EmployeeLocalServiceImpl object = new EmployeeLocalServiceImpl();
			object.updateEmployee(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, new Date(), "abcde", "abcde", "abcde", 0, Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", new Date(), new Date(), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateEmployeeTest20() {
		try{
			EmployeeLocalServiceImpl object = new EmployeeLocalServiceImpl();
			object.updateEmployee(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, new Date(), "abcde", "abcde", "abcde", 0, Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", new Date(), new Date(), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmployeeByEmpNoTest() {
		try{
			EmployeeLocalServiceImpl object = new EmployeeLocalServiceImpl();
			object.getEmployeeByEmpNo(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}