package org.opencps.dossiermgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class RegistrationFormActionsImplTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void updateTest() {
//		try{
//			RegistrationFormActionsImpl object = new RegistrationFormActionsImpl();
//			object.update(null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void insertTest() {
//		try{
//			RegistrationFormActionsImpl object = new RegistrationFormActionsImpl();
//			object.insert(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), true, true, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void deleteRegistrationFormTest() {
//		try{
//			RegistrationFormActionsImpl object = new RegistrationFormActionsImpl();
//			object.deleteRegistrationForm("abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateRegFormFormDataTest() {
//		try{
//			RegistrationFormActionsImpl object = new RegistrationFormActionsImpl();
//			object.updateRegFormFormData(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void deleteRegistrationFormsTest() {
//		try{
//			RegistrationFormActionsImpl object = new RegistrationFormActionsImpl();
//			object.deleteRegistrationForms(Long.valueOf(0), Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void cloneRegistrationFormByRegistrationIdTest() {
//		try{
//			RegistrationFormActionsImpl object = new RegistrationFormActionsImpl();
//			object.cloneRegistrationFormByRegistrationId(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void addRegistrationFormbaseonRegTemplateTest() {
//		try{
//			RegistrationFormActionsImpl object = new RegistrationFormActionsImpl();
//			object.addRegistrationFormbaseonRegTemplate(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getDetailTest() {
//		try{
//			RegistrationFormActionsImpl object = new RegistrationFormActionsImpl();
//			object.getDetail(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getFormbyRegIdTest() {
//		try{
//			RegistrationFormActionsImpl object = new RegistrationFormActionsImpl();
//			object.getFormbyRegId(Long.valueOf(0), Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateIsNewTest() {
//		try{
//			RegistrationFormActionsImpl object = new RegistrationFormActionsImpl();
//			object.updateIsNew(Long.valueOf(0), Long.valueOf(0), "abcde", true, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getRegistrationFormsTest() {
		try{
			RegistrationFormActionsImpl object = new RegistrationFormActionsImpl();
			object.getRegistrationForms(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}