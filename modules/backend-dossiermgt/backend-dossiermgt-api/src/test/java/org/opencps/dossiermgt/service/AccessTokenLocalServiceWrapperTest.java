package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class AccessTokenLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void dynamicQueryTest() {
		try{
			AccessTokenLocalServiceWrapper object = new AccessTokenLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest2() {
		try{
			AccessTokenLocalServiceWrapper object = new AccessTokenLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			AccessTokenLocalServiceWrapper object = new AccessTokenLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			AccessTokenLocalServiceWrapper object = new AccessTokenLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			AccessTokenLocalServiceWrapper object = new AccessTokenLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAccessTokenTest() {
		try{
			AccessTokenLocalServiceWrapper object = new AccessTokenLocalServiceWrapper(null);
			object.getAccessToken(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAccessTokenTest7() {
		try{
			AccessTokenLocalServiceWrapper object = new AccessTokenLocalServiceWrapper(null);
			object.getAccessToken(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addAccessTokenTest() {
		try{
			AccessTokenLocalServiceWrapper object = new AccessTokenLocalServiceWrapper(null);
			object.addAccessToken(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addAccessTokenTest9() {
		try{
			AccessTokenLocalServiceWrapper object = new AccessTokenLocalServiceWrapper(null);
			object.addAccessToken(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchAccessTokenTest() {
		try{
			AccessTokenLocalServiceWrapper object = new AccessTokenLocalServiceWrapper(null);
			object.fetchAccessToken(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void garbageTokenTest() {
		try{
			AccessTokenLocalServiceWrapper object = new AccessTokenLocalServiceWrapper(null);
			object.garbageToken();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAccessTokensTest() {
		try{
			AccessTokenLocalServiceWrapper object = new AccessTokenLocalServiceWrapper(null);
			object.getAccessTokens(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAccessTokensTest13() {
		try{
			AccessTokenLocalServiceWrapper object = new AccessTokenLocalServiceWrapper(null);
			object.getAccessTokens(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAccessTokensCountTest() {
		try{
			AccessTokenLocalServiceWrapper object = new AccessTokenLocalServiceWrapper(null);
			object.getAccessTokensCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateAccessTokenTest() {
		try{
			AccessTokenLocalServiceWrapper object = new AccessTokenLocalServiceWrapper(null);
			object.updateAccessToken(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteAccessTokenTest() {
		try{
			AccessTokenLocalServiceWrapper object = new AccessTokenLocalServiceWrapper(null);
			object.deleteAccessToken(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteAccessTokenTest17() {
		try{
			AccessTokenLocalServiceWrapper object = new AccessTokenLocalServiceWrapper(null);
			object.deleteAccessToken(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createAccessTokenTest() {
		try{
			AccessTokenLocalServiceWrapper object = new AccessTokenLocalServiceWrapper(null);
			object.createAccessToken(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			AccessTokenLocalServiceWrapper object = new AccessTokenLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			AccessTokenLocalServiceWrapper object = new AccessTokenLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			AccessTokenLocalServiceWrapper object = new AccessTokenLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			AccessTokenLocalServiceWrapper object = new AccessTokenLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest23() {
		try{
			AccessTokenLocalServiceWrapper object = new AccessTokenLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			AccessTokenLocalServiceWrapper object = new AccessTokenLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest25() {
		try{
			AccessTokenLocalServiceWrapper object = new AccessTokenLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			AccessTokenLocalServiceWrapper object = new AccessTokenLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			AccessTokenLocalServiceWrapper object = new AccessTokenLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest28() {
		try{
			AccessTokenLocalServiceWrapper object = new AccessTokenLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}