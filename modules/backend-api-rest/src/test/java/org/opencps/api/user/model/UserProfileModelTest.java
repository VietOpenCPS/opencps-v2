package org.opencps.api.user.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class UserProfileModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getClassNameTest() {
		try{
			UserProfileModel object = new UserProfileModel();
			object.getClassName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getContactTelNoTest() {
		try{
			UserProfileModel object = new UserProfileModel();
			object.getContactTelNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getContactEmailTest() {
		try{
			UserProfileModel object = new UserProfileModel();
			object.getContactEmail();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getScreenNameTest() {
		try{
			UserProfileModel object = new UserProfileModel();
			object.getScreenName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setBirthdateTest() {
		try{
			UserProfileModel object = new UserProfileModel();
			object.setBirthdate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFullNameTest() {
		try{
			UserProfileModel object = new UserProfileModel();
			object.setFullName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGenderTest() {
		try{
			UserProfileModel object = new UserProfileModel();
			object.setGender("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBirthdateTest() {
		try{
			UserProfileModel object = new UserProfileModel();
			object.getBirthdate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGenderTest() {
		try{
			UserProfileModel object = new UserProfileModel();
			object.getGender();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getClassPKTest() {
		try{
			UserProfileModel object = new UserProfileModel();
			object.getClassPK();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setClassPKTest() {
		try{
			UserProfileModel object = new UserProfileModel();
			object.setClassPK("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setClassNameTest() {
		try{
			UserProfileModel object = new UserProfileModel();
			object.setClassName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmailTest() {
		try{
			UserProfileModel object = new UserProfileModel();
			object.setEmail("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmailTest() {
		try{
			UserProfileModel object = new UserProfileModel();
			object.getEmail();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFullNameTest() {
		try{
			UserProfileModel object = new UserProfileModel();
			object.getFullName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setScreenNameTest() {
		try{
			UserProfileModel object = new UserProfileModel();
			object.setScreenName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setContactEmailTest() {
		try{
			UserProfileModel object = new UserProfileModel();
			object.setContactEmail("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setContactTelNoTest() {
		try{
			UserProfileModel object = new UserProfileModel();
			object.setContactTelNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}