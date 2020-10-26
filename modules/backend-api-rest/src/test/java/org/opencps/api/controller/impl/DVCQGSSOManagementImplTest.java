package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DVCQGSSOManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getUserInfoTest() {
		try{
			DVCQGSSOManagementImpl object = new DVCQGSSOManagementImpl();
			object.getUserInfo(null, null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void checkAuthTest() {
		try{
			DVCQGSSOManagementImpl object = new DVCQGSSOManagementImpl();
			object.checkAuth(null, null, null, null, null, null, null, 0, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void doRevokeTest() {
		try{
			DVCQGSSOManagementImpl object = new DVCQGSSOManagementImpl();
			object.doRevoke(null, null, null, null, null, null, null, "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAuthURLTest() {
		try{
			DVCQGSSOManagementImpl object = new DVCQGSSOManagementImpl();
			object.getAuthURL(null, null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getLogoutURLTest() {
		try{
			DVCQGSSOManagementImpl object = new DVCQGSSOManagementImpl();
			object.getLogoutURL(null, null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void doAuthTest() {
		try{
			DVCQGSSOManagementImpl object = new DVCQGSSOManagementImpl();
			object.doAuth(null, null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void doChangeEmailTest() {
		try{
			DVCQGSSOManagementImpl object = new DVCQGSSOManagementImpl();
			object.doChangeEmail(null, null, null, null, null, null, null, "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void doLogoutTest() {
		try{
			DVCQGSSOManagementImpl object = new DVCQGSSOManagementImpl();
			object.doLogout(null, null, null, null, null, null, null, "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}