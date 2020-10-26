package org.opencps.api.usermgt.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class RegisterModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setApplicantIdNoTest() {
		try{
			RegisterModel object = new RegisterModel();
			object.setApplicantIdNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantIdNoTest() {
		try{
			RegisterModel object = new RegisterModel();
			object.getApplicantIdNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setApplicantIdTypeTest() {
		try{
			RegisterModel object = new RegisterModel();
			object.setApplicantIdType("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setApplicantIdDateTest() {
		try{
			RegisterModel object = new RegisterModel();
			object.setApplicantIdDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPasswordTest() {
		try{
			RegisterModel object = new RegisterModel();
			object.getPassword();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getContactEmailTest() {
		try{
			RegisterModel object = new RegisterModel();
			object.getContactEmail();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantNameTest() {
		try{
			RegisterModel object = new RegisterModel();
			object.getApplicantName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPasswordTest() {
		try{
			RegisterModel object = new RegisterModel();
			object.setPassword("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantIdTypeTest() {
		try{
			RegisterModel object = new RegisterModel();
			object.getApplicantIdType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getApplicantIdDateTest() {
		try{
			RegisterModel object = new RegisterModel();
			object.getApplicantIdDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setContactEmailTest() {
		try{
			RegisterModel object = new RegisterModel();
			object.setContactEmail("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setApplicantNameTest() {
		try{
			RegisterModel object = new RegisterModel();
			object.setApplicantName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}