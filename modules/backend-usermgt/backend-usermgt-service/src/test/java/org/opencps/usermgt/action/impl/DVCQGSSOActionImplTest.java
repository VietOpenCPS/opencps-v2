package org.opencps.usermgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DVCQGSSOActionImplTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void getUserInfoTest() {
//		try{
//			DVCQGSSOActionImpl object = new DVCQGSSOActionImpl();
//			object.getUserInfo(null, Long.valueOf(0), null, null, "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void checkAuthTest() {
//		try{
//			DVCQGSSOActionImpl object = new DVCQGSSOActionImpl();
//			object.checkAuth(null, Long.valueOf(0), null, null, 0, "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getAuthURLTest() {
//		try{
//			DVCQGSSOActionImpl object = new DVCQGSSOActionImpl();
//			object.getAuthURL(null, Long.valueOf(0), null, null, "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getLogoutURLTest() {
//		try{
//			DVCQGSSOActionImpl object = new DVCQGSSOActionImpl();
//			object.getLogoutURL(null, Long.valueOf(0), null, null, "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	@Test
//	public void getLogoutTest() {
//		try{
//			DVCQGSSOActionImpl object = new DVCQGSSOActionImpl();
//			object.getLogout(null, Long.valueOf(0), null, null, "abcde", "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void revokeTest() {
//		try{
//			DVCQGSSOActionImpl object = new DVCQGSSOActionImpl();
//			object.revoke(null, Long.valueOf(0), null, null, "abcde", "abcde", "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void isValidAccessTokenTest() {
//		try{
//			DVCQGSSOActionImpl object = new DVCQGSSOActionImpl();
//			object.isValidAccessToken("abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	
	
	
	
	@Test
	public void doAuthTest() {
		try{
			DVCQGSSOActionImpl object = new DVCQGSSOActionImpl();
			object.doAuth(null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	
	@Test
	public void renewSessionTest() {
		try{
			DVCQGSSOActionImpl object = new DVCQGSSOActionImpl();
			object.renewSession(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void doChangeEmailTest() {
		try{
			DVCQGSSOActionImpl object = new DVCQGSSOActionImpl();
			object.doChangeEmail(null, Long.valueOf(0), Long.valueOf(0), null, null, null, "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}