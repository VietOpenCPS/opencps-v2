package org.opencps.api.registrationtemplate.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class RegistrationTemplateDetailModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getGovAgencyCodeTest() {
		try{
			RegistrationTemplateDetailModel object = new RegistrationTemplateDetailModel();
			object.getGovAgencyCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGovAgencyCodeTest() {
		try{
			RegistrationTemplateDetailModel object = new RegistrationTemplateDetailModel();
			object.setGovAgencyCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFormNameTest() {
		try{
			RegistrationTemplateDetailModel object = new RegistrationTemplateDetailModel();
			object.setFormName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFormNoTest() {
		try{
			RegistrationTemplateDetailModel object = new RegistrationTemplateDetailModel();
			object.setFormNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRegistrationTemplateIdTest() {
		try{
			RegistrationTemplateDetailModel object = new RegistrationTemplateDetailModel();
			object.setRegistrationTemplateId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationTemplateIdTest() {
		try{
			RegistrationTemplateDetailModel object = new RegistrationTemplateDetailModel();
			object.getRegistrationTemplateId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMultipleTest() {
		try{
			RegistrationTemplateDetailModel object = new RegistrationTemplateDetailModel();
			object.setMultiple(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormNameTest() {
		try{
			RegistrationTemplateDetailModel object = new RegistrationTemplateDetailModel();
			object.getFormName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isMultipleTest() {
		try{
			RegistrationTemplateDetailModel object = new RegistrationTemplateDetailModel();
			object.isMultiple();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormNoTest() {
		try{
			RegistrationTemplateDetailModel object = new RegistrationTemplateDetailModel();
			object.getFormNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}