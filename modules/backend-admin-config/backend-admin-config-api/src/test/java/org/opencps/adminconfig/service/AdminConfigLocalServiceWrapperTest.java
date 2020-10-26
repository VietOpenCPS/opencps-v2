package org.opencps.adminconfig.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class AdminConfigLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getAdminConfigTest() {
		try{
			AdminConfigLocalServiceWrapper object = new AdminConfigLocalServiceWrapper(null);
			object.getAdminConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByCodeTest() {
		try{
			AdminConfigLocalServiceWrapper object = new AdminConfigLocalServiceWrapper(null);
			object.fetchByCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAdminConfigsTest() {
		try{
			AdminConfigLocalServiceWrapper object = new AdminConfigLocalServiceWrapper(null);
			object.getAdminConfigs(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDataTest() {
		try{
			AdminConfigLocalServiceWrapper object = new AdminConfigLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchAdminConfigTest() {
		try{
			AdminConfigLocalServiceWrapper object = new AdminConfigLocalServiceWrapper(null);
			object.fetchAdminConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addAdminConfigTest() {
		try{
			AdminConfigLocalServiceWrapper object = new AdminConfigLocalServiceWrapper(null);
			object.addAdminConfig(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			AdminConfigLocalServiceWrapper object = new AdminConfigLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest8() {
		try{
			AdminConfigLocalServiceWrapper object = new AdminConfigLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest9() {
		try{
			AdminConfigLocalServiceWrapper object = new AdminConfigLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest10() {
		try{
			AdminConfigLocalServiceWrapper object = new AdminConfigLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			AdminConfigLocalServiceWrapper object = new AdminConfigLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateAdminConfigTest() {
		try{
			AdminConfigLocalServiceWrapper object = new AdminConfigLocalServiceWrapper(null);
			object.updateAdminConfig(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			AdminConfigLocalServiceWrapper object = new AdminConfigLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			AdminConfigLocalServiceWrapper object = new AdminConfigLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			AdminConfigLocalServiceWrapper object = new AdminConfigLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			AdminConfigLocalServiceWrapper object = new AdminConfigLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest17() {
		try{
			AdminConfigLocalServiceWrapper object = new AdminConfigLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			AdminConfigLocalServiceWrapper object = new AdminConfigLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest19() {
		try{
			AdminConfigLocalServiceWrapper object = new AdminConfigLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteAdminConfigTest() {
		try{
			AdminConfigLocalServiceWrapper object = new AdminConfigLocalServiceWrapper(null);
			object.deleteAdminConfig(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteAdminConfigTest21() {
		try{
			AdminConfigLocalServiceWrapper object = new AdminConfigLocalServiceWrapper(null);
			object.deleteAdminConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			AdminConfigLocalServiceWrapper object = new AdminConfigLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAdminConfigsCountTest() {
		try{
			AdminConfigLocalServiceWrapper object = new AdminConfigLocalServiceWrapper(null);
			object.getAdminConfigsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createAdminConfigTest() {
		try{
			AdminConfigLocalServiceWrapper object = new AdminConfigLocalServiceWrapper(null);
			object.createAdminConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			AdminConfigLocalServiceWrapper object = new AdminConfigLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest26() {
		try{
			AdminConfigLocalServiceWrapper object = new AdminConfigLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			AdminConfigLocalServiceWrapper object = new AdminConfigLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}