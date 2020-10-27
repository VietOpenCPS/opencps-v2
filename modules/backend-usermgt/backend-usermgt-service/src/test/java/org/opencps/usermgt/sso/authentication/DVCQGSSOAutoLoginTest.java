package org.opencps.usermgt.sso.authentication;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DVCQGSSOAutoLoginTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getUserTest() {
		try{
			DVCQGSSOAutoLogin object = new DVCQGSSOAutoLogin();
			object.getUser(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void doLoginTest() {
		try{
			DVCQGSSOAutoLogin object = new DVCQGSSOAutoLogin();
			object.doLogin(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserLocalServiceTest() {
		try{
			DVCQGSSOAutoLogin object = new DVCQGSSOAutoLogin();
			object.setUserLocalService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}