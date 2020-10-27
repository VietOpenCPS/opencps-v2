package org.opencps.usermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class UserTrackPathLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void dynamicQueryTest() {
		try{
			UserTrackPathLocalServiceWrapper object = new UserTrackPathLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest2() {
		try{
			UserTrackPathLocalServiceWrapper object = new UserTrackPathLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			UserTrackPathLocalServiceWrapper object = new UserTrackPathLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			UserTrackPathLocalServiceWrapper object = new UserTrackPathLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			UserTrackPathLocalServiceWrapper object = new UserTrackPathLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			UserTrackPathLocalServiceWrapper object = new UserTrackPathLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			UserTrackPathLocalServiceWrapper object = new UserTrackPathLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			UserTrackPathLocalServiceWrapper object = new UserTrackPathLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			UserTrackPathLocalServiceWrapper object = new UserTrackPathLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest10() {
		try{
			UserTrackPathLocalServiceWrapper object = new UserTrackPathLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			UserTrackPathLocalServiceWrapper object = new UserTrackPathLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest12() {
		try{
			UserTrackPathLocalServiceWrapper object = new UserTrackPathLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			UserTrackPathLocalServiceWrapper object = new UserTrackPathLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			UserTrackPathLocalServiceWrapper object = new UserTrackPathLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest15() {
		try{
			UserTrackPathLocalServiceWrapper object = new UserTrackPathLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserTrackPathByUuidAndCompanyIdTest() {
		try{
			UserTrackPathLocalServiceWrapper object = new UserTrackPathLocalServiceWrapper(null);
			object.getUserTrackPathByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchUserTrackPathByUuidAndCompanyIdTest() {
		try{
			UserTrackPathLocalServiceWrapper object = new UserTrackPathLocalServiceWrapper(null);
			object.fetchUserTrackPathByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserTrackPathsTest() {
		try{
			UserTrackPathLocalServiceWrapper object = new UserTrackPathLocalServiceWrapper(null);
			object.getUserTrackPaths(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateUserTrackPathTest() {
		try{
			UserTrackPathLocalServiceWrapper object = new UserTrackPathLocalServiceWrapper(null);
			object.updateUserTrackPath(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateUserTrackPathTest20() {
		try{
			UserTrackPathLocalServiceWrapper object = new UserTrackPathLocalServiceWrapper(null);
			object.updateUserTrackPath(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createUserTrackPathTest() {
		try{
			UserTrackPathLocalServiceWrapper object = new UserTrackPathLocalServiceWrapper(null);
			object.createUserTrackPath(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserTrackPathsCountTest() {
		try{
			UserTrackPathLocalServiceWrapper object = new UserTrackPathLocalServiceWrapper(null);
			object.getUserTrackPathsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchUserTrackPathTest() {
		try{
			UserTrackPathLocalServiceWrapper object = new UserTrackPathLocalServiceWrapper(null);
			object.fetchUserTrackPath(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteUserTrackPathTest() {
		try{
			UserTrackPathLocalServiceWrapper object = new UserTrackPathLocalServiceWrapper(null);
			object.deleteUserTrackPath(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteUserTrackPathTest25() {
		try{
			UserTrackPathLocalServiceWrapper object = new UserTrackPathLocalServiceWrapper(null);
			object.deleteUserTrackPath(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addUserTrackPathTest() {
		try{
			UserTrackPathLocalServiceWrapper object = new UserTrackPathLocalServiceWrapper(null);
			object.addUserTrackPath(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserTrackPathTest() {
		try{
			UserTrackPathLocalServiceWrapper object = new UserTrackPathLocalServiceWrapper(null);
			object.getUserTrackPath(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}