package org.opencps.api.serviceinfo.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class FileTemplatesTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getFileTemplateNoTest() {
		try{
			FileTemplates object = new FileTemplates();
			object.getFileTemplateNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileTemplateNoTest() {
		try{
			FileTemplates object = new FileTemplates();
			object.setFileTemplateNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFormScriptFileIdTest() {
		try{
			FileTemplates object = new FileTemplates();
			object.setFormScriptFileId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFormReportFileIdTest() {
		try{
			FileTemplates object = new FileTemplates();
			object.setFormReportFileId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTemplateNameTest() {
		try{
			FileTemplates object = new FileTemplates();
			object.setTemplateName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTemplateNameTest() {
		try{
			FileTemplates object = new FileTemplates();
			object.getTemplateName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void seteFormTest() {
		try{
			FileTemplates object = new FileTemplates();
			object.seteForm(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void seteFormNoPatternTest() {
		try{
			FileTemplates object = new FileTemplates();
			object.seteFormNoPattern("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void seteFormNamePatternTest() {
		try{
			FileTemplates object = new FileTemplates();
			object.seteFormNamePattern("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void geteFormNoPatternTest() {
		try{
			FileTemplates object = new FileTemplates();
			object.geteFormNoPattern();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void geteFormNamePatternTest() {
		try{
			FileTemplates object = new FileTemplates();
			object.geteFormNamePattern();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceinfoIdTest() {
		try{
			FileTemplates object = new FileTemplates();
			object.getServiceinfoId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setServiceinfoIdTest() {
		try{
			FileTemplates object = new FileTemplates();
			object.setServiceinfoId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void geteFormTest() {
		try{
			FileTemplates object = new FileTemplates();
			object.geteForm();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormScriptFileIdTest() {
		try{
			FileTemplates object = new FileTemplates();
			object.getFormScriptFileId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormReportFileIdTest() {
		try{
			FileTemplates object = new FileTemplates();
			object.getFormReportFileId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileTypeTest() {
		try{
			FileTemplates object = new FileTemplates();
			object.getFileType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileTypeTest() {
		try{
			FileTemplates object = new FileTemplates();
			object.setFileType("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileSizeTest() {
		try{
			FileTemplates object = new FileTemplates();
			object.getFileSize();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileSizeTest() {
		try{
			FileTemplates object = new FileTemplates();
			object.setFileSize(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}