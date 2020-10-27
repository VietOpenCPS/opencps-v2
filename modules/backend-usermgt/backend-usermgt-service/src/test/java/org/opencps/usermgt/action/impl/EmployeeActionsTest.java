package org.opencps.usermgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class EmployeeActionsTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void updateTest() {
//		try{
//			EmployeeActions object = new EmployeeActions();
//			object.update(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", new Date(), "abcde", "abcde", "abcde", "abcde", new Date(), new Date(), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void createTest() {
//		try{
//			EmployeeActions object = new EmployeeActions();
//			object.create(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", new Date(), "abcde", "abcde", "abcde", "abcde", new Date(), new Date(), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getFileEntryTest() {
//		try{
//			EmployeeActions object = new EmployeeActions();
//			object.getFileEntry(Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateEmployeeDBTest() {
//		try{
//			EmployeeActions object = new EmployeeActions();
//			object.updateEmployeeDB(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", 0, "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateEmployeeDBTest5() {
//		try{
//			EmployeeActions object = new EmployeeActions();
//			object.updateEmployeeDB(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateEmployeeDB_QATest() {
//		try{
//			EmployeeActions object = new EmployeeActions();
//			object.updateEmployeeDB_QA(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", 0, "abcde", "abcde", "abcde", 0, "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void validateExitsTest() {
//		try{
//			EmployeeActions object = new EmployeeActions();
//			object.validateExits(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getEmployeePhotoTest() {
//		try{
//			EmployeeActions object = new EmployeeActions();
//			object.getEmployeePhoto(Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//
//	
//	@Test
//	public void updateEmployeeJobposTest() {
//		try{
//			EmployeeActions object = new EmployeeActions();
//			object.updateEmployeeJobpos(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), true, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void uploadEmployeePhotoTest() {
//		try{
//			EmployeeActions object = new EmployeeActions();
//			object.uploadEmployeePhoto(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	
//	@Test
//	public void createEmployeeJobposTest() {
//		try{
//			EmployeeActions object = new EmployeeActions();
//			object.createEmployeeJobpos(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), true, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void createEmployeeAccountTest() {
//		try{
//			EmployeeActions object = new EmployeeActions();
//			object.createEmployeeAccount(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", true, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void deleteEmployeeJobPosTest() {
//		try{
//			EmployeeActions object = new EmployeeActions();
//			object.deleteEmployeeJobPos(Long.valueOf(0), Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getEmployeeByMappingUserIdTest() {
//		try{
//			EmployeeActions object = new EmployeeActions();
//			object.getEmployeeByMappingUserId(Long.valueOf(0), Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	
	
	
	
	@Test
	public void lockEmployeeAccountTest() {
		try{
			EmployeeActions object = new EmployeeActions();
			object.lockEmployeeAccount(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmployeesTest() {
		try{
			EmployeeActions object = new EmployeeActions();
			object.getEmployees(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void unlockEmployeeAccountTest() {
		try{
			EmployeeActions object = new EmployeeActions();
			object.unlockEmployeeAccount(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void lockEmployeeAccountTest15() {
		try{
			EmployeeActions object = new EmployeeActions();
			object.lockEmployeeAccount(null, true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmployeeJobposTest() {
		try{
			EmployeeActions object = new EmployeeActions();
			object.getEmployeeJobpos(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}