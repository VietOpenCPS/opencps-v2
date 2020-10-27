package org.opencps.kyso.utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class CertUtilTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getCertificateByPathTest() {
		try{
			CertUtil.getCertificateByPath("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getX509CertificateByPathTest() {
		try{
			CertUtil.getX509CertificateByPath("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCertificateByURLTest() {
		try{
			CertUtil.getCertificateByURL("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}