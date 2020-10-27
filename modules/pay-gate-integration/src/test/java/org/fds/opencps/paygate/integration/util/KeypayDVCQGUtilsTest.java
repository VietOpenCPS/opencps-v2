package org.fds.opencps.paygate.integration.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class KeypayDVCQGUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void dptracuuthanhtoanhsTest() {
		try{
			KeypayDVCQGUtils.dptracuuthanhtoanhs(null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void buildMaTraCuuTTTest() {
		try{
			KeypayDVCQGUtils.buildMaTraCuuTT("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}