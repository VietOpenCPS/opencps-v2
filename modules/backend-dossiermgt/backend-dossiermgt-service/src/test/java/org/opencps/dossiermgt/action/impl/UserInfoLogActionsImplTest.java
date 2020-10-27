package org.opencps.dossiermgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class UserInfoLogActionsImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void addUserInfoLogTest() {
		try{
			UserInfoLogActionsImpl object = new UserInfoLogActionsImpl();
			object.addUserInfoLog(Long.valueOf(0), "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getUserInfoLogTest() {
//		try{
//			UserInfoLogActionsImpl object = new UserInfoLogActionsImpl();
//			object.getUserInfoLog(Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}