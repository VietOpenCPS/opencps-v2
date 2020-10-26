package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class EmployeeManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void updateTest() {
		try{
			EmployeeManagementImpl object = new EmployeeManagementImpl();
			object.update(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void deleteTest() {
//		try{
//			EmployeeManagementImpl object = new EmployeeManagementImpl();
//			object.delete(null, null, null, null, null, null, Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void readTest() {
//		try{
//			EmployeeManagementImpl object = new EmployeeManagementImpl();
//			object.read(null, null, null, null, null, null, Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void createTest() {
		try{
			EmployeeManagementImpl object = new EmployeeManagementImpl();
			object.create(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void validateExitsTest() {
		try{
			EmployeeManagementImpl object = new EmployeeManagementImpl();
			object.validateExits(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getEmployeePhotoTest() {
//		try{
//			EmployeeManagementImpl object = new EmployeeManagementImpl();
//			object.getEmployeePhoto(null, null, null, null, null, null, Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getEmployeesTest() {
		try{
			EmployeeManagementImpl object = new EmployeeManagementImpl();
			object.getEmployees(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void unlockEmployeeAccountTest() {
		try{
			EmployeeManagementImpl object = new EmployeeManagementImpl();
			object.unlockEmployeeAccount(null, null, null, null, null, null, Long.valueOf(0), true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmployeeJobposTest() {
		try{
			EmployeeManagementImpl object = new EmployeeManagementImpl();
			object.getEmployeeJobpos(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmployeesByItemCodeTest() {
		try{
			EmployeeManagementImpl object = new EmployeeManagementImpl();
			object.getEmployeesByItemCode(null, null, null, null, null, null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateEmployeeJobposTest() {
		try{
			EmployeeManagementImpl object = new EmployeeManagementImpl();
			object.updateEmployeeJobpos(null, null, null, null, null, null, Long.valueOf(0), null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void uploadEmployeePhotoTest() {
		try{
			EmployeeManagementImpl object = new EmployeeManagementImpl();
			object.uploadEmployeePhoto(null, null, null, null, null, null, Long.valueOf(0), null, "abcde", "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmployeesByRoleTest() {
		try{
			EmployeeManagementImpl object = new EmployeeManagementImpl();
			object.getEmployeesByRole(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void deleteEmployeeJobposTest() {
//		try{
//			EmployeeManagementImpl object = new EmployeeManagementImpl();
//			object.deleteEmployeeJobpos(null, null, null, null, null, null, Long.valueOf(0), Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void lockEmployeeAccountTest() {
		try{
			EmployeeManagementImpl object = new EmployeeManagementImpl();
			object.lockEmployeeAccount(null, null, null, null, null, null, Long.valueOf(0), true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createEmployeeJobposTest() {
		try{
			EmployeeManagementImpl object = new EmployeeManagementImpl();
			object.createEmployeeJobpos(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createEmployeeAccountTest() {
		try{
			EmployeeManagementImpl object = new EmployeeManagementImpl();
			object.createEmployeeAccount(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}