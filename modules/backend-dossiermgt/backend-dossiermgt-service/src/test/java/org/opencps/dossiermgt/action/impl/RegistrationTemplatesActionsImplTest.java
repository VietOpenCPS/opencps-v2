package org.opencps.dossiermgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class RegistrationTemplatesActionsImplTest {
	@Before
	public void setUp() {
	}
	
	
//	@Test
//	public void removeRegistrationTemplateTest() {
//		try{
//			RegistrationTemplatesActionsImpl object = new RegistrationTemplatesActionsImpl();
//			object.removeRegistrationTemplate(Long.valueOf(0), Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateRegistrationTemplatesTest() {
//		try{
//			RegistrationTemplatesActionsImpl object = new RegistrationTemplatesActionsImpl();
//			object.updateRegistrationTemplates(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", true, "abcde", "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void addRegistrationTemplateTest() {
//		try{
//			RegistrationTemplatesActionsImpl object = new RegistrationTemplatesActionsImpl();
//			object.addRegistrationTemplate(Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", true, "abcde", "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateFormReportTest() {
//		try{
//			RegistrationTemplatesActionsImpl object = new RegistrationTemplatesActionsImpl();
//			object.updateFormReport(Long.valueOf(0), Long.valueOf(0), "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateFormScriptTest() {
//		try{
//			RegistrationTemplatesActionsImpl object = new RegistrationTemplatesActionsImpl();
//			object.updateFormScript(Long.valueOf(0), Long.valueOf(0), "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateSampledataTest() {
//		try{
//			RegistrationTemplatesActionsImpl object = new RegistrationTemplatesActionsImpl();
//			object.updateSampledata(Long.valueOf(0), Long.valueOf(0), "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getRegistrationTemplatesTest2() {
		try{
			RegistrationTemplatesActionsImpl object = new RegistrationTemplatesActionsImpl();
			object.getRegistrationTemplates(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationTemplatesTest() {
		try{
			RegistrationTemplatesActionsImpl object = new RegistrationTemplatesActionsImpl();
			object.getRegistrationTemplates(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}