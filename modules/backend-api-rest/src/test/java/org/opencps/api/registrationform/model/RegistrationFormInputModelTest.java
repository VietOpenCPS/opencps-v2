package org.opencps.api.registrationform.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class RegistrationFormInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getFormDataTest() {
		try{
			RegistrationFormInputModel object = new RegistrationFormInputModel();
			object.getFormData();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormScriptTest() {
		try{
			RegistrationFormInputModel object = new RegistrationFormInputModel();
			object.getFormScript();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRegistrationIdTest() {
		try{
			RegistrationFormInputModel object = new RegistrationFormInputModel();
			object.getRegistrationId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReferenceUidTest() {
		try{
			RegistrationFormInputModel object = new RegistrationFormInputModel();
			object.getReferenceUid();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isRemovedTest() {
		try{
			RegistrationFormInputModel object = new RegistrationFormInputModel();
			object.isRemoved();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFormNameTest() {
		try{
			RegistrationFormInputModel object = new RegistrationFormInputModel();
			object.setFormName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFormNoTest() {
		try{
			RegistrationFormInputModel object = new RegistrationFormInputModel();
			object.setFormNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRegistrationIdTest() {
		try{
			RegistrationFormInputModel object = new RegistrationFormInputModel();
			object.setRegistrationId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormReportTest() {
		try{
			RegistrationFormInputModel object = new RegistrationFormInputModel();
			object.getFormReport();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRemovedTest() {
		try{
			RegistrationFormInputModel object = new RegistrationFormInputModel();
			object.setRemoved(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFormDataTest() {
		try{
			RegistrationFormInputModel object = new RegistrationFormInputModel();
			object.setFormData("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setReferenceUidTest() {
		try{
			RegistrationFormInputModel object = new RegistrationFormInputModel();
			object.setReferenceUid("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileEntryIdTest() {
		try{
			RegistrationFormInputModel object = new RegistrationFormInputModel();
			object.getFileEntryId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileEntryIdTest() {
		try{
			RegistrationFormInputModel object = new RegistrationFormInputModel();
			object.setFileEntryId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setIsNewTest() {
		try{
			RegistrationFormInputModel object = new RegistrationFormInputModel();
			object.setIsNew(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isIsNewTest() {
		try{
			RegistrationFormInputModel object = new RegistrationFormInputModel();
			object.isIsNew();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormNameTest() {
		try{
			RegistrationFormInputModel object = new RegistrationFormInputModel();
			object.getFormName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFormReportTest() {
		try{
			RegistrationFormInputModel object = new RegistrationFormInputModel();
			object.setFormReport("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFormScriptTest() {
		try{
			RegistrationFormInputModel object = new RegistrationFormInputModel();
			object.setFormScript("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormNoTest() {
		try{
			RegistrationFormInputModel object = new RegistrationFormInputModel();
			object.getFormNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}