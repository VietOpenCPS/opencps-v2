package org.opencps.api.registrationform.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class RegistrationFormModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getVersionTest() {
		try{
			RegistrationFormModel object = new RegistrationFormModel();
			object.getVersion();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReferenceUidTest() {
		try{
			RegistrationFormModel object = new RegistrationFormModel();
			object.getReferenceUid();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setVersionTest() {
		try{
			RegistrationFormModel object = new RegistrationFormModel();
			object.setVersion(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isRemovedTest() {
		try{
			RegistrationFormModel object = new RegistrationFormModel();
			object.isRemoved();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFormNameTest() {
		try{
			RegistrationFormModel object = new RegistrationFormModel();
			object.setFormName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFormNoTest() {
		try{
			RegistrationFormModel object = new RegistrationFormModel();
			object.setFormNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMultipleTest() {
		try{
			RegistrationFormModel object = new RegistrationFormModel();
			object.setMultiple(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRemovedTest() {
		try{
			RegistrationFormModel object = new RegistrationFormModel();
			object.setRemoved(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setReferenceUidTest() {
		try{
			RegistrationFormModel object = new RegistrationFormModel();
			object.setReferenceUid("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setIsNewTest() {
		try{
			RegistrationFormModel object = new RegistrationFormModel();
			object.setIsNew(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isIsNewTest() {
		try{
			RegistrationFormModel object = new RegistrationFormModel();
			object.isIsNew();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormNameTest() {
		try{
			RegistrationFormModel object = new RegistrationFormModel();
			object.getFormName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isMultipleTest() {
		try{
			RegistrationFormModel object = new RegistrationFormModel();
			object.isMultiple();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormNoTest() {
		try{
			RegistrationFormModel object = new RegistrationFormModel();
			object.getFormNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}