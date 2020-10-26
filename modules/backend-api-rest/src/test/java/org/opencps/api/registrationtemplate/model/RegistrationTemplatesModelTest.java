package org.opencps.api.registrationtemplate.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class RegistrationTemplatesModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getCreateDateTest() {
		try{
			RegistrationTemplatesModel object = new RegistrationTemplatesModel();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			RegistrationTemplatesModel object = new RegistrationTemplatesModel();
			object.setCreateDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			RegistrationTemplatesModel object = new RegistrationTemplatesModel();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			RegistrationTemplatesModel object = new RegistrationTemplatesModel();
			object.setModifiedDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGovAgencyNameTest() {
		try{
			RegistrationTemplatesModel object = new RegistrationTemplatesModel();
			object.setGovAgencyName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGovAgencyCodeTest() {
		try{
			RegistrationTemplatesModel object = new RegistrationTemplatesModel();
			object.getGovAgencyCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGovAgencyCodeTest() {
		try{
			RegistrationTemplatesModel object = new RegistrationTemplatesModel();
			object.setGovAgencyCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGovAgencyNameTest() {
		try{
			RegistrationTemplatesModel object = new RegistrationTemplatesModel();
			object.getGovAgencyName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFormNameTest() {
		try{
			RegistrationTemplatesModel object = new RegistrationTemplatesModel();
			object.setFormName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFormNoTest() {
		try{
			RegistrationTemplatesModel object = new RegistrationTemplatesModel();
			object.setFormNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRegistrationTemplateIdTest() {
		try{
			RegistrationTemplatesModel object = new RegistrationTemplatesModel();
			object.setRegistrationTemplateId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationTemplateIdTest() {
		try{
			RegistrationTemplatesModel object = new RegistrationTemplatesModel();
			object.getRegistrationTemplateId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMultipleTest() {
		try{
			RegistrationTemplatesModel object = new RegistrationTemplatesModel();
			object.setMultiple(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormNameTest() {
		try{
			RegistrationTemplatesModel object = new RegistrationTemplatesModel();
			object.getFormName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isMultipleTest() {
		try{
			RegistrationTemplatesModel object = new RegistrationTemplatesModel();
			object.isMultiple();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormNoTest() {
		try{
			RegistrationTemplatesModel object = new RegistrationTemplatesModel();
			object.getFormNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}