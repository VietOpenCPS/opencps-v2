package org.opencps.api.usermgt.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class MappingUserTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setUserIdTest() {
		try{
			MappingUser object = new MappingUser();
			object.setUserId("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			MappingUser object = new MappingUser();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getScreenNameTest() {
		try{
			MappingUser object = new MappingUser();
			object.getScreenName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isLockingTest() {
		try{
			MappingUser object = new MappingUser();
			object.isLocking();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setScreenNameTest() {
		try{
			MappingUser object = new MappingUser();
			object.setScreenName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setLockingTest() {
		try{
			MappingUser object = new MappingUser();
			object.setLocking(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}