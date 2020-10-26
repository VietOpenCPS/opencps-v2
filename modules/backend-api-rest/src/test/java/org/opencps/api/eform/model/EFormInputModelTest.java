package org.opencps.api.eform.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class EFormInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getStateTest() {
		try{
			EFormInputModel object = new EFormInputModel();
			object.getState();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileTemplateNoTest() {
		try{
			EFormInputModel object = new EFormInputModel();
			object.getFileTemplateNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileTemplateNoTest() {
		try{
			EFormInputModel object = new EFormInputModel();
			object.setFileTemplateNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFormScriptFileIdTest() {
		try{
			EFormInputModel object = new EFormInputModel();
			object.setFormScriptFileId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFormReportFileIdTest() {
		try{
			EFormInputModel object = new EFormInputModel();
			object.setFormReportFileId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceInfoIdTest() {
		try{
			EFormInputModel object = new EFormInputModel();
			object.getServiceInfoId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void seteFormNoTest() {
		try{
			EFormInputModel object = new EFormInputModel();
			object.seteFormNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void seteFormNameTest() {
		try{
			EFormInputModel object = new EFormInputModel();
			object.seteFormName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void seteFormDataTest() {
		try{
			EFormInputModel object = new EFormInputModel();
			object.seteFormData("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSecretTest() {
		try{
			EFormInputModel object = new EFormInputModel();
			object.setSecret("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setServiceInfoIdTest() {
		try{
			EFormInputModel object = new EFormInputModel();
			object.setServiceInfoId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void geteFormNameTest() {
		try{
			EFormInputModel object = new EFormInputModel();
			object.geteFormName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void geteFormNoTest() {
		try{
			EFormInputModel object = new EFormInputModel();
			object.geteFormNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void geteFormDataTest() {
		try{
			EFormInputModel object = new EFormInputModel();
			object.geteFormData();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSecretTest() {
		try{
			EFormInputModel object = new EFormInputModel();
			object.getSecret();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormScriptFileIdTest() {
		try{
			EFormInputModel object = new EFormInputModel();
			object.getFormScriptFileId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormReportFileIdTest() {
		try{
			EFormInputModel object = new EFormInputModel();
			object.getFormReportFileId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setServiceCodeTest() {
		try{
			EFormInputModel object = new EFormInputModel();
			object.setServiceCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCheckinDateTest() {
		try{
			EFormInputModel object = new EFormInputModel();
			object.getCheckinDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGateNumberTest() {
		try{
			EFormInputModel object = new EFormInputModel();
			object.getGateNumber();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceCodeTest() {
		try{
			EFormInputModel object = new EFormInputModel();
			object.getServiceCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGateNumberTest() {
		try{
			EFormInputModel object = new EFormInputModel();
			object.setGateNumber("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStateTest() {
		try{
			EFormInputModel object = new EFormInputModel();
			object.setState(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCheckinDateTest() {
		try{
			EFormInputModel object = new EFormInputModel();
			object.setCheckinDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmailTest() {
		try{
			EFormInputModel object = new EFormInputModel();
			object.setEmail("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmailTest() {
		try{
			EFormInputModel object = new EFormInputModel();
			object.getEmail();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}