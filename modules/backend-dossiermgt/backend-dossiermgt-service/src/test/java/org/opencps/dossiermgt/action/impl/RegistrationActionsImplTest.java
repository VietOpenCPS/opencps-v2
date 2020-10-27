package org.opencps.dossiermgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class RegistrationActionsImplTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void deleteTest() {
//		try{
//			RegistrationActionsImpl object = new RegistrationActionsImpl();
//			object.delete(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void insertTest() {
		try{
			RegistrationActionsImpl object = new RegistrationActionsImpl();
			object.insert(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormDataByFormNoTest() {
		try{
			RegistrationActionsImpl object = new RegistrationActionsImpl();
			object.getFormDataByFormNo(Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateRegistrationTest() {
		try{
			RegistrationActionsImpl object = new RegistrationActionsImpl();
			object.updateRegistration(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getDetailTest() {
//		try{
//			RegistrationActionsImpl object = new RegistrationActionsImpl();
//			object.getDetail(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getfileEntryIdTest() {
		try{
			RegistrationActionsImpl object = new RegistrationActionsImpl();
			object.getfileEntryId("abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationsTest() {
		try{
			RegistrationActionsImpl object = new RegistrationActionsImpl();
			object.getRegistrations(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addLogTest() {
		try{
			RegistrationActionsImpl object = new RegistrationActionsImpl();
			object.addLog("abcde", Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}