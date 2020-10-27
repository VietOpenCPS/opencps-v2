package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class UserInfoLogLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			UserInfoLogLocalServiceWrapper object = new UserInfoLogLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			UserInfoLogLocalServiceWrapper object = new UserInfoLogLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			UserInfoLogLocalServiceWrapper object = new UserInfoLogLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			UserInfoLogLocalServiceWrapper object = new UserInfoLogLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			UserInfoLogLocalServiceWrapper object = new UserInfoLogLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			UserInfoLogLocalServiceWrapper object = new UserInfoLogLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			UserInfoLogLocalServiceWrapper object = new UserInfoLogLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			UserInfoLogLocalServiceWrapper object = new UserInfoLogLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			UserInfoLogLocalServiceWrapper object = new UserInfoLogLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			UserInfoLogLocalServiceWrapper object = new UserInfoLogLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest11() {
		try{
			UserInfoLogLocalServiceWrapper object = new UserInfoLogLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			UserInfoLogLocalServiceWrapper object = new UserInfoLogLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest13() {
		try{
			UserInfoLogLocalServiceWrapper object = new UserInfoLogLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			UserInfoLogLocalServiceWrapper object = new UserInfoLogLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			UserInfoLogLocalServiceWrapper object = new UserInfoLogLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest16() {
		try{
			UserInfoLogLocalServiceWrapper object = new UserInfoLogLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			UserInfoLogLocalServiceWrapper object = new UserInfoLogLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addUserInfoLogTest() {
		try{
			UserInfoLogLocalServiceWrapper object = new UserInfoLogLocalServiceWrapper(null);
			object.addUserInfoLog(Long.valueOf(0), null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addUserInfoLogTest19() {
		try{
			UserInfoLogLocalServiceWrapper object = new UserInfoLogLocalServiceWrapper(null);
			object.addUserInfoLog(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserInfoLogTest() {
		try{
			UserInfoLogLocalServiceWrapper object = new UserInfoLogLocalServiceWrapper(null);
			object.getUserInfoLog(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserInfoLogsTest() {
		try{
			UserInfoLogLocalServiceWrapper object = new UserInfoLogLocalServiceWrapper(null);
			object.getUserInfoLogs(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserInfoLogsTest22() {
		try{
			UserInfoLogLocalServiceWrapper object = new UserInfoLogLocalServiceWrapper(null);
			object.getUserInfoLogs(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchUserInfoLogTest() {
		try{
			UserInfoLogLocalServiceWrapper object = new UserInfoLogLocalServiceWrapper(null);
			object.fetchUserInfoLog(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteUserInfoLogTest() {
		try{
			UserInfoLogLocalServiceWrapper object = new UserInfoLogLocalServiceWrapper(null);
			object.deleteUserInfoLog(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteUserInfoLogTest25() {
		try{
			UserInfoLogLocalServiceWrapper object = new UserInfoLogLocalServiceWrapper(null);
			object.deleteUserInfoLog(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserInfoLogsCountTest() {
		try{
			UserInfoLogLocalServiceWrapper object = new UserInfoLogLocalServiceWrapper(null);
			object.getUserInfoLogsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createUserInfoLogTest() {
		try{
			UserInfoLogLocalServiceWrapper object = new UserInfoLogLocalServiceWrapper(null);
			object.createUserInfoLog(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateUserInfoLogTest() {
		try{
			UserInfoLogLocalServiceWrapper object = new UserInfoLogLocalServiceWrapper(null);
			object.updateUserInfoLog(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}