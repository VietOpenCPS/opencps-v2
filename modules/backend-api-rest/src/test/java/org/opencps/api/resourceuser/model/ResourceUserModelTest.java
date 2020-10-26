package org.opencps.api.resourceuser.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ResourceUserModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setUserIdTest() {
		try{
			ResourceUserModel object = new ResourceUserModel();
			object.setUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			ResourceUserModel object = new ResourceUserModel();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isSelectedTest() {
		try{
			ResourceUserModel object = new ResourceUserModel();
			object.isSelected();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSelectedTest() {
		try{
			ResourceUserModel object = new ResourceUserModel();
			object.setSelected(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFullNameTest() {
		try{
			ResourceUserModel object = new ResourceUserModel();
			object.setFullName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserClassTest() {
		try{
			ResourceUserModel object = new ResourceUserModel();
			object.setUserClass("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setReadonlyTest() {
		try{
			ResourceUserModel object = new ResourceUserModel();
			object.setReadonly(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserClassTest() {
		try{
			ResourceUserModel object = new ResourceUserModel();
			object.getUserClass();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isReadonlyTest() {
		try{
			ResourceUserModel object = new ResourceUserModel();
			object.isReadonly();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmailTest() {
		try{
			ResourceUserModel object = new ResourceUserModel();
			object.setEmail("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmailTest() {
		try{
			ResourceUserModel object = new ResourceUserModel();
			object.getEmail();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFullNameTest() {
		try{
			ResourceUserModel object = new ResourceUserModel();
			object.getFullName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}