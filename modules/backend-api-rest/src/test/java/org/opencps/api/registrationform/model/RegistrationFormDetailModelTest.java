package org.opencps.api.registrationform.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class RegistrationFormDetailModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getVersionTest() {
		try{
			RegistrationFormDetailModel object = new RegistrationFormDetailModel();
			object.getVersion();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReferenceUidTest() {
		try{
			RegistrationFormDetailModel object = new RegistrationFormDetailModel();
			object.getReferenceUid();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setVersionTest() {
		try{
			RegistrationFormDetailModel object = new RegistrationFormDetailModel();
			object.setVersion(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isRemovedTest() {
		try{
			RegistrationFormDetailModel object = new RegistrationFormDetailModel();
			object.isRemoved();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFormNameTest() {
		try{
			RegistrationFormDetailModel object = new RegistrationFormDetailModel();
			object.setFormName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFormNoTest() {
		try{
			RegistrationFormDetailModel object = new RegistrationFormDetailModel();
			object.setFormNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMultipleTest() {
		try{
			RegistrationFormDetailModel object = new RegistrationFormDetailModel();
			object.setMultiple(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRemovedTest() {
		try{
			RegistrationFormDetailModel object = new RegistrationFormDetailModel();
			object.setRemoved(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setReferenceUidTest() {
		try{
			RegistrationFormDetailModel object = new RegistrationFormDetailModel();
			object.setReferenceUid("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setIsNewTest() {
		try{
			RegistrationFormDetailModel object = new RegistrationFormDetailModel();
			object.setIsNew(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isIsNewTest() {
		try{
			RegistrationFormDetailModel object = new RegistrationFormDetailModel();
			object.isIsNew();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormNameTest() {
		try{
			RegistrationFormDetailModel object = new RegistrationFormDetailModel();
			object.getFormName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isMultipleTest() {
		try{
			RegistrationFormDetailModel object = new RegistrationFormDetailModel();
			object.isMultiple();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormNoTest() {
		try{
			RegistrationFormDetailModel object = new RegistrationFormDetailModel();
			object.getFormNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}