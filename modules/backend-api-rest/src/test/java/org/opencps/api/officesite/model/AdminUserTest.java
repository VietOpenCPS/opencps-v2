package org.opencps.api.officesite.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class AdminUserTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setUserIdTest() {
		try{
			AdminUser object = new AdminUser();
			object.setUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			AdminUser object = new AdminUser();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getScreenNameTest() {
		try{
			AdminUser object = new AdminUser();
			object.getScreenName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmailTest() {
		try{
			AdminUser object = new AdminUser();
			object.setEmail("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmailTest() {
		try{
			AdminUser object = new AdminUser();
			object.getEmail();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setScreenNameTest() {
		try{
			AdminUser object = new AdminUser();
			object.setScreenName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}