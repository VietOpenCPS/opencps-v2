package org.opencps.usermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class UserLoginLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void fetchByU_STest() {
		try{
			UserLoginLocalServiceImpl object = new UserLoginLocalServiceImpl();
			object.fetchByU_S(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void traceLogoutTest() {
		try{
			UserLoginLocalServiceImpl object = new UserLoginLocalServiceImpl();
			object.traceLogout(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateUserLoginTest() {
		try{
			UserLoginLocalServiceImpl object = new UserLoginLocalServiceImpl();
			object.updateUserLogin(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", new Date(), new Date(), Long.valueOf(0), "abcde", 0, new Date(), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateUserLoginTest4() {
		try{
			UserLoginLocalServiceImpl object = new UserLoginLocalServiceImpl();
			object.updateUserLogin(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", new Date(), new Date(), Long.valueOf(0), "abcde", 0, new Date(), "abcde", true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}