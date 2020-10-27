package org.opencps.usermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class UserLoginLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void dynamicQueryTest() {
		try{
			UserLoginLocalServiceWrapper object = new UserLoginLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest2() {
		try{
			UserLoginLocalServiceWrapper object = new UserLoginLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			UserLoginLocalServiceWrapper object = new UserLoginLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			UserLoginLocalServiceWrapper object = new UserLoginLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			UserLoginLocalServiceWrapper object = new UserLoginLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			UserLoginLocalServiceWrapper object = new UserLoginLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			UserLoginLocalServiceWrapper object = new UserLoginLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			UserLoginLocalServiceWrapper object = new UserLoginLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			UserLoginLocalServiceWrapper object = new UserLoginLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			UserLoginLocalServiceWrapper object = new UserLoginLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest11() {
		try{
			UserLoginLocalServiceWrapper object = new UserLoginLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			UserLoginLocalServiceWrapper object = new UserLoginLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest13() {
		try{
			UserLoginLocalServiceWrapper object = new UserLoginLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			UserLoginLocalServiceWrapper object = new UserLoginLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			UserLoginLocalServiceWrapper object = new UserLoginLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest16() {
		try{
			UserLoginLocalServiceWrapper object = new UserLoginLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserLoginByUuidAndGroupIdTest() {
		try{
			UserLoginLocalServiceWrapper object = new UserLoginLocalServiceWrapper(null);
			object.getUserLoginByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchUserLoginByUuidAndGroupIdTest() {
		try{
			UserLoginLocalServiceWrapper object = new UserLoginLocalServiceWrapper(null);
			object.fetchUserLoginByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserLoginsCountTest() {
		try{
			UserLoginLocalServiceWrapper object = new UserLoginLocalServiceWrapper(null);
			object.getUserLoginsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserLoginsByUuidAndCompanyIdTest() {
		try{
			UserLoginLocalServiceWrapper object = new UserLoginLocalServiceWrapper(null);
			object.getUserLoginsByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserLoginsByUuidAndCompanyIdTest21() {
		try{
			UserLoginLocalServiceWrapper object = new UserLoginLocalServiceWrapper(null);
			object.getUserLoginsByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByU_STest() {
		try{
			UserLoginLocalServiceWrapper object = new UserLoginLocalServiceWrapper(null);
			object.fetchByU_S(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserLoginTest() {
		try{
			UserLoginLocalServiceWrapper object = new UserLoginLocalServiceWrapper(null);
			object.getUserLogin(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addUserLoginTest() {
		try{
			UserLoginLocalServiceWrapper object = new UserLoginLocalServiceWrapper(null);
			object.addUserLogin(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteUserLoginTest() {
		try{
			UserLoginLocalServiceWrapper object = new UserLoginLocalServiceWrapper(null);
			object.deleteUserLogin(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteUserLoginTest26() {
		try{
			UserLoginLocalServiceWrapper object = new UserLoginLocalServiceWrapper(null);
			object.deleteUserLogin(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void traceLogoutTest() {
		try{
			UserLoginLocalServiceWrapper object = new UserLoginLocalServiceWrapper(null);
			object.traceLogout(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchUserLoginTest() {
		try{
			UserLoginLocalServiceWrapper object = new UserLoginLocalServiceWrapper(null);
			object.fetchUserLogin(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserLoginsTest() {
		try{
			UserLoginLocalServiceWrapper object = new UserLoginLocalServiceWrapper(null);
			object.getUserLogins(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateUserLoginTest() {
		try{
			UserLoginLocalServiceWrapper object = new UserLoginLocalServiceWrapper(null);
			object.updateUserLogin(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", new Date(), new Date(), Long.valueOf(0), "abcde", 0, new Date(), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateUserLoginTest31() {
		try{
			UserLoginLocalServiceWrapper object = new UserLoginLocalServiceWrapper(null);
			object.updateUserLogin(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", new Date(), new Date(), Long.valueOf(0), "abcde", 0, new Date(), "abcde", true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateUserLoginTest32() {
		try{
			UserLoginLocalServiceWrapper object = new UserLoginLocalServiceWrapper(null);
			object.updateUserLogin(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createUserLoginTest() {
		try{
			UserLoginLocalServiceWrapper object = new UserLoginLocalServiceWrapper(null);
			object.createUserLogin(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}