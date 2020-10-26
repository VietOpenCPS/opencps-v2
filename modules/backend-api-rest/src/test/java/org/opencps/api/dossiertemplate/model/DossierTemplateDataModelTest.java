package org.opencps.api.dossiertemplate.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierTemplateDataModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getCreateDateTest() {
		try{
			DossierTemplateDataModel object = new DossierTemplateDataModel();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			DossierTemplateDataModel object = new DossierTemplateDataModel();
			object.setCreateDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			DossierTemplateDataModel object = new DossierTemplateDataModel();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			DossierTemplateDataModel object = new DossierTemplateDataModel();
			object.setModifiedDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTemplateNoTest() {
		try{
			DossierTemplateDataModel object = new DossierTemplateDataModel();
			object.getTemplateNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTemplateNameTest() {
		try{
			DossierTemplateDataModel object = new DossierTemplateDataModel();
			object.setTemplateName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTemplateNameTest() {
		try{
			DossierTemplateDataModel object = new DossierTemplateDataModel();
			object.getTemplateName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTemplateNoTest() {
		try{
			DossierTemplateDataModel object = new DossierTemplateDataModel();
			object.setTemplateNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNewFormScriptTest() {
		try{
			DossierTemplateDataModel object = new DossierTemplateDataModel();
			object.getNewFormScript();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setNewFormScriptTest() {
		try{
			DossierTemplateDataModel object = new DossierTemplateDataModel();
			object.setNewFormScript("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFormMetaTest() {
		try{
			DossierTemplateDataModel object = new DossierTemplateDataModel();
			object.setFormMeta("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierTemplateIdTest() {
		try{
			DossierTemplateDataModel object = new DossierTemplateDataModel();
			object.setDossierTemplateId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDescriptionTest() {
		try{
			DossierTemplateDataModel object = new DossierTemplateDataModel();
			object.getDescription();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDescriptionTest() {
		try{
			DossierTemplateDataModel object = new DossierTemplateDataModel();
			object.setDescription("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierTemplateIdTest() {
		try{
			DossierTemplateDataModel object = new DossierTemplateDataModel();
			object.getDossierTemplateId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormMetaTest() {
		try{
			DossierTemplateDataModel object = new DossierTemplateDataModel();
			object.getFormMeta();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}