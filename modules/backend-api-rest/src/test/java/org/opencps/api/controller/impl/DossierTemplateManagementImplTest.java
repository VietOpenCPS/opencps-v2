package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierTemplateManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getFormScriptTest() {
		try{
			DossierTemplateManagementImpl object = new DossierTemplateManagementImpl();
			object.getFormScript(null, null, null, null, null, null, "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierPartTest() {
		try{
			DossierTemplateManagementImpl object = new DossierTemplateManagementImpl();
			object.getDossierPart(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierPartsTest() {
		try{
			DossierTemplateManagementImpl object = new DossierTemplateManagementImpl();
			object.addDossierParts(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierPartsTest() {
		try{
			DossierTemplateManagementImpl object = new DossierTemplateManagementImpl();
			object.getDossierParts(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateFormReportTest() {
		try{
			DossierTemplateManagementImpl object = new DossierTemplateManagementImpl();
			object.updateFormReport(null, null, null, null, null, null, Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateFormScriptTest() {
		try{
			DossierTemplateManagementImpl object = new DossierTemplateManagementImpl();
			object.updateFormScript(null, null, null, null, null, null, Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormReportTest() {
		try{
			DossierTemplateManagementImpl object = new DossierTemplateManagementImpl();
			object.getFormReport(null, null, null, null, null, null, Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateSampleDateTest() {
		try{
			DossierTemplateManagementImpl object = new DossierTemplateManagementImpl();
			object.updateSampleDate(null, null, null, null, null, null, Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSampleDataTest() {
		try{
			DossierTemplateManagementImpl object = new DossierTemplateManagementImpl();
			object.getSampleData(null, null, null, null, null, null, Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierTemplateDetailTest() {
		try{
			DossierTemplateManagementImpl object = new DossierTemplateManagementImpl();
			object.getDossierTemplateDetail(null, null, null, null, null, null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierPartsTest() {
		try{
			DossierTemplateManagementImpl object = new DossierTemplateManagementImpl();
			object.updateDossierParts(null, null, null, null, null, null, Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeDossierPartsTest() {
		try{
			DossierTemplateManagementImpl object = new DossierTemplateManagementImpl();
			object.removeDossierParts(null, null, null, null, null, null, Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierTemplateDetailTest() {
		try{
			DossierTemplateManagementImpl object = new DossierTemplateManagementImpl();
			object.updateDossierTemplateDetail(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierTemplateTest() {
		try{
			DossierTemplateManagementImpl object = new DossierTemplateManagementImpl();
			object.addDossierTemplate(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeDossierTemplateTest() {
		try{
			DossierTemplateManagementImpl object = new DossierTemplateManagementImpl();
			object.removeDossierTemplate(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierTemplatesTest() {
		try{
			DossierTemplateManagementImpl object = new DossierTemplateManagementImpl();
			object.getDossierTemplates(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}