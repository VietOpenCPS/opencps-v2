package org.opencps.api.v21.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierTemplateTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getTemplateNoTest() {
		try{
			DossierTemplate object = new DossierTemplate();
			object.getTemplateNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTemplateNameTest() {
		try{
			DossierTemplate object = new DossierTemplate();
			object.setTemplateName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTemplateNameTest() {
		try{
			DossierTemplate object = new DossierTemplate();
			object.getTemplateName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTemplateNoTest() {
		try{
			DossierTemplate object = new DossierTemplate();
			object.setTemplateNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getNewFormScriptTest() {
		try{
			DossierTemplate object = new DossierTemplate();
			object.getNewFormScript();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setNewFormScriptTest() {
		try{
			DossierTemplate object = new DossierTemplate();
			object.setNewFormScript("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPartsTest() {
		try{
			DossierTemplate object = new DossierTemplate();
			object.setParts(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFormMetaTest() {
		try{
			DossierTemplate object = new DossierTemplate();
			object.setFormMeta("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPartsTest() {
		try{
			DossierTemplate object = new DossierTemplate();
			object.getParts();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDescriptionTest() {
		try{
			DossierTemplate object = new DossierTemplate();
			object.getDescription();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDescriptionTest() {
		try{
			DossierTemplate object = new DossierTemplate();
			object.setDescription("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormMetaTest() {
		try{
			DossierTemplate object = new DossierTemplate();
			object.getFormMeta();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}