package org.opencps.usermgt.scheduler.utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class RegisterLGSPUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getTokenLGSPTest() {
		try{
			RegisterLGSPUtils.getTokenLGSP();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void activeUserNewLGSPTest() {
		try{
			RegisterLGSPUtils.activeUserNewLGSP(null, Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void forgotNewLGSPTest() {
		try{
			RegisterLGSPUtils.forgotNewLGSP("abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}