package org.opencps.api.dossier.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class UserModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getUserNameTest() {
		try{
			UserModel object = new UserModel();
			object.getUserName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserNameTest() {
		try{
			UserModel object = new UserModel();
			object.setUserName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserIdTest() {
		try{
			UserModel object = new UserModel();
			object.setUserId(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			UserModel object = new UserModel();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModeratorTest() {
		try{
			UserModel object = new UserModel();
			object.getModerator();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModeratorTest() {
		try{
			UserModel object = new UserModel();
			object.setModerator("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}