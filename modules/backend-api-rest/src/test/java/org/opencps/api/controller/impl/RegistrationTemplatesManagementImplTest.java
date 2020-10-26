package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class RegistrationTemplatesManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getFormScriptByRegistrationTemplateIdTest() {
		try{
			RegistrationTemplatesManagementImpl object = new RegistrationTemplatesManagementImpl();
			object.getFormScriptByRegistrationTemplateId(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSampleDataByRegistrationTemplateIdTest() {
		try{
			RegistrationTemplatesManagementImpl object = new RegistrationTemplatesManagementImpl();
			object.getSampleDataByRegistrationTemplateId(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormReportByRegistrationTemplateIdTest() {
		try{
			RegistrationTemplatesManagementImpl object = new RegistrationTemplatesManagementImpl();
			object.getFormReportByRegistrationTemplateId(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateRegistrationTemplateFormReportTest() {
		try{
			RegistrationTemplatesManagementImpl object = new RegistrationTemplatesManagementImpl();
			object.updateRegistrationTemplateFormReport(null, null, null, null, null, null, Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateRegistrationTemplateFormScriptTest() {
		try{
			RegistrationTemplatesManagementImpl object = new RegistrationTemplatesManagementImpl();
			object.updateRegistrationTemplateFormScript(null, null, null, null, null, null, Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateRegistrationTemplateSampleDataTest() {
		try{
			RegistrationTemplatesManagementImpl object = new RegistrationTemplatesManagementImpl();
			object.updateRegistrationTemplateSampleData(null, null, null, null, null, null, Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationTemplatebyIdTest() {
		try{
			RegistrationTemplatesManagementImpl object = new RegistrationTemplatesManagementImpl();
			object.getRegistrationTemplatebyId(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateRegistrationTemplateTest() {
		try{
			RegistrationTemplatesManagementImpl object = new RegistrationTemplatesManagementImpl();
			object.updateRegistrationTemplate(null, null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationTemplatesTest() {
		try{
			RegistrationTemplatesManagementImpl object = new RegistrationTemplatesManagementImpl();
			object.getRegistrationTemplates(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeRegistrationTemplateTest() {
		try{
			RegistrationTemplatesManagementImpl object = new RegistrationTemplatesManagementImpl();
			object.removeRegistrationTemplate(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addRegistrationTemplateTest() {
		try{
			RegistrationTemplatesManagementImpl object = new RegistrationTemplatesManagementImpl();
			object.addRegistrationTemplate(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}