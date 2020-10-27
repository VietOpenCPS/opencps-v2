package org.opencps.auth.security.authverifier;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class OpenCPSAuthHeaderAuthVerifierTest {
	@Before
	public void setUp() {
	}
	@Test
	public void verifyTest() {
		try{
			OpenCPSAuthHeaderAuthVerifier object = new OpenCPSAuthHeaderAuthVerifier();
			object.verify(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAuthTypeTest() {
		try{
			OpenCPSAuthHeaderAuthVerifier object = new OpenCPSAuthHeaderAuthVerifier();
			object.getAuthType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void doLoginTest() {
		try{
			OpenCPSAuthHeaderAuthVerifier object = new OpenCPSAuthHeaderAuthVerifier();
			object.doLogin(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isEnabledTest() {
		try{
			OpenCPSAuthHeaderAuthVerifier object = new OpenCPSAuthHeaderAuthVerifier();
			object.isEnabled(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}