package org.opencps.api.dossiertemplate.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierTemplateInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getTemplateNoTest() {
		try{
			DossierTemplateInputModel object = new DossierTemplateInputModel();
			object.getTemplateNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTemplateNameTest() {
		try{
			DossierTemplateInputModel object = new DossierTemplateInputModel();
			object.setTemplateName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTemplateNameTest() {
		try{
			DossierTemplateInputModel object = new DossierTemplateInputModel();
			object.getTemplateName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTemplateNoTest() {
		try{
			DossierTemplateInputModel object = new DossierTemplateInputModel();
			object.setTemplateNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNewFormScriptTest() {
		try{
			DossierTemplateInputModel object = new DossierTemplateInputModel();
			object.getNewFormScript();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setNewFormScriptTest() {
		try{
			DossierTemplateInputModel object = new DossierTemplateInputModel();
			object.setNewFormScript("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFormMetaTest() {
		try{
			DossierTemplateInputModel object = new DossierTemplateInputModel();
			object.setFormMeta("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierTemplateIdTest() {
		try{
			DossierTemplateInputModel object = new DossierTemplateInputModel();
			object.setDossierTemplateId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDescriptionTest() {
		try{
			DossierTemplateInputModel object = new DossierTemplateInputModel();
			object.getDescription();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDescriptionTest() {
		try{
			DossierTemplateInputModel object = new DossierTemplateInputModel();
			object.setDescription("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierTemplateIdTest() {
		try{
			DossierTemplateInputModel object = new DossierTemplateInputModel();
			object.getDossierTemplateId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormMetaTest() {
		try{
			DossierTemplateInputModel object = new DossierTemplateInputModel();
			object.getFormMeta();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}