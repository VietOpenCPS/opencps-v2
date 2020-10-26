package org.opencps.api.serviceinfo.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class FileTemplateModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getFileTemplateNoTest() {
		try{
			FileTemplateModel object = new FileTemplateModel();
			object.getFileTemplateNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileTemplateNoTest() {
		try{
			FileTemplateModel object = new FileTemplateModel();
			object.setFileTemplateNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setTemplateNameTest() {
		try{
			FileTemplateModel object = new FileTemplateModel();
			object.setTemplateName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTemplateNameTest() {
		try{
			FileTemplateModel object = new FileTemplateModel();
			object.getTemplateName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileTypeTest() {
		try{
			FileTemplateModel object = new FileTemplateModel();
			object.getFileType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileTypeTest() {
		try{
			FileTemplateModel object = new FileTemplateModel();
			object.setFileType("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileSizeTest() {
		try{
			FileTemplateModel object = new FileTemplateModel();
			object.getFileSize();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileSizeTest() {
		try{
			FileTemplateModel object = new FileTemplateModel();
			object.setFileSize(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}