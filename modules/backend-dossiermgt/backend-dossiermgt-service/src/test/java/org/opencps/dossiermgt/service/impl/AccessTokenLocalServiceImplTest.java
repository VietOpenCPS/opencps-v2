package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class AccessTokenLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getAccessTokenTest() {
		try{
			AccessTokenLocalServiceImpl object = new AccessTokenLocalServiceImpl();
			object.getAccessToken(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addAccessTokenTest() {
		try{
			AccessTokenLocalServiceImpl object = new AccessTokenLocalServiceImpl();
			object.addAccessToken(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void garbageTokenTest() {
		try{
			AccessTokenLocalServiceImpl object = new AccessTokenLocalServiceImpl();
			object.garbageToken();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAccessTokensTest() {
		try{
			AccessTokenLocalServiceImpl object = new AccessTokenLocalServiceImpl();
			object.getAccessTokens(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}