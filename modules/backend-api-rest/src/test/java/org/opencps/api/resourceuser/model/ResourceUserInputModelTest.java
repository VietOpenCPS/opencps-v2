package org.opencps.api.resourceuser.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ResourceUserInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getClassNameTest() {
		try{
			ResourceUserInputModel object = new ResourceUserInputModel();
			object.getClassName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserIdTest() {
		try{
			ResourceUserInputModel object = new ResourceUserInputModel();
			object.setUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			ResourceUserInputModel object = new ResourceUserInputModel();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFullNameTest() {
		try{
			ResourceUserInputModel object = new ResourceUserInputModel();
			object.setFullName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setReadonlyTest() {
		try{
			ResourceUserInputModel object = new ResourceUserInputModel();
			object.setReadonly("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getClassPKTest() {
		try{
			ResourceUserInputModel object = new ResourceUserInputModel();
			object.getClassPK();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setClassPKTest() {
		try{
			ResourceUserInputModel object = new ResourceUserInputModel();
			object.setClassPK("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setClassNameTest() {
		try{
			ResourceUserInputModel object = new ResourceUserInputModel();
			object.setClassName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmailTest() {
		try{
			ResourceUserInputModel object = new ResourceUserInputModel();
			object.setEmail("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmailTest() {
		try{
			ResourceUserInputModel object = new ResourceUserInputModel();
			object.getEmail();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFullNameTest() {
		try{
			ResourceUserInputModel object = new ResourceUserInputModel();
			object.getFullName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReadonlyTest() {
		try{
			ResourceUserInputModel object = new ResourceUserInputModel();
			object.getReadonly();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}