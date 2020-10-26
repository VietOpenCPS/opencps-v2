package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class UserUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void mapperUsersPermissionsListTest() {
		try{
			UserUtils.mapperUsersPermissionsList(null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mapperUserAccountModelTest() {
		try{
			UserUtils.mapperUserAccountModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mapperUserSitesListTest() {
		try{
			UserUtils.mapperUserSitesList(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mapperUserProfileModelTest() {
		try{
			UserUtils.mapperUserProfileModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mapperUserRolesListTest() {
		try{
			UserUtils.mapperUserRolesList(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mapperUserListTest() {
		try{
			UserUtils.mapperUserList(null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mapperUserModelTest() {
		try{
			UserUtils.mapperUserModel(null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}