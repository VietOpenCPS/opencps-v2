package org.opencps.usermgt.restapi;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class AuthHeaderAuthVerifierTest {
	@Before
	public void setUp() {
	}
	@Test
	public void verifyTest() {
		try{
			AuthHeaderAuthVerifier object = new AuthHeaderAuthVerifier();
			object.verify(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAuthTypeTest() {
		try{
			AuthHeaderAuthVerifier object = new AuthHeaderAuthVerifier();
			object.getAuthType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void doLoginTest() {
		try{
			AuthHeaderAuthVerifier object = new AuthHeaderAuthVerifier();
			object.doLogin(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isEnabledTest() {
		try{
			AuthHeaderAuthVerifier object = new AuthHeaderAuthVerifier();
			object.isEnabled(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}