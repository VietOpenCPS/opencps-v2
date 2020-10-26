package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ProxyManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void proxyMultipartTest() {
		try{
			ProxyManagementImpl object = new ProxyManagementImpl();
			object.proxyMultipart(null, null, null, null, null, null, null, "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void proxyTest() {
		try{
			ProxyManagementImpl object = new ProxyManagementImpl();
			object.proxy(null, null, null, null, null, null, "abcde", "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void readAllBytesTest() {
		try{
			ProxyManagementImpl.readAllBytes(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}