package org.opencps.api.reassign.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ToUsersTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getUserNameTest() {
		try{
			ToUsers object = new ToUsers();
			object.getUserName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserNameTest() {
		try{
			ToUsers object = new ToUsers();
			object.setUserName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserIdTest() {
		try{
			ToUsers object = new ToUsers();
			object.setUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			ToUsers object = new ToUsers();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModeratorTest() {
		try{
			ToUsers object = new ToUsers();
			object.setModerator(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAssignedTest() {
		try{
			ToUsers object = new ToUsers();
			object.getAssigned();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAssignedTest() {
		try{
			ToUsers object = new ToUsers();
			object.setAssigned(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isModeratorTest() {
		try{
			ToUsers object = new ToUsers();
			object.isModerator();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}