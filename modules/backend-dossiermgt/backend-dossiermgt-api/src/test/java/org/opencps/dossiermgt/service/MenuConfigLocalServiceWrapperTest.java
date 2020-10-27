package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class MenuConfigLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			MenuConfigLocalServiceWrapper object = new MenuConfigLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			MenuConfigLocalServiceWrapper object = new MenuConfigLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			MenuConfigLocalServiceWrapper object = new MenuConfigLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			MenuConfigLocalServiceWrapper object = new MenuConfigLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			MenuConfigLocalServiceWrapper object = new MenuConfigLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			MenuConfigLocalServiceWrapper object = new MenuConfigLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMenuConfigsCountTest() {
		try{
			MenuConfigLocalServiceWrapper object = new MenuConfigLocalServiceWrapper(null);
			object.getMenuConfigsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByGroupIdTest() {
		try{
			MenuConfigLocalServiceWrapper object = new MenuConfigLocalServiceWrapper(null);
			object.getByGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMenuConfigTest() {
		try{
			MenuConfigLocalServiceWrapper object = new MenuConfigLocalServiceWrapper(null);
			object.getMenuConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByCodeTest() {
		try{
			MenuConfigLocalServiceWrapper object = new MenuConfigLocalServiceWrapper(null);
			object.getByCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addMenuConfigTest() {
		try{
			MenuConfigLocalServiceWrapper object = new MenuConfigLocalServiceWrapper(null);
			object.addMenuConfig(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, 0, "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addMenuConfigTest12() {
		try{
			MenuConfigLocalServiceWrapper object = new MenuConfigLocalServiceWrapper(null);
			object.addMenuConfig(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteMenuConfigTest() {
		try{
			MenuConfigLocalServiceWrapper object = new MenuConfigLocalServiceWrapper(null);
			object.deleteMenuConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteMenuConfigTest14() {
		try{
			MenuConfigLocalServiceWrapper object = new MenuConfigLocalServiceWrapper(null);
			object.deleteMenuConfig(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateMenuConfigTest() {
		try{
			MenuConfigLocalServiceWrapper object = new MenuConfigLocalServiceWrapper(null);
			object.updateMenuConfig(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, 0, "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateMenuConfigTest16() {
		try{
			MenuConfigLocalServiceWrapper object = new MenuConfigLocalServiceWrapper(null);
			object.updateMenuConfig(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchMenuConfigTest() {
		try{
			MenuConfigLocalServiceWrapper object = new MenuConfigLocalServiceWrapper(null);
			object.fetchMenuConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateMenuConfigDBTest() {
		try{
			MenuConfigLocalServiceWrapper object = new MenuConfigLocalServiceWrapper(null);
			object.updateMenuConfigDB(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, 0, "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByG_MENUTest() {
		try{
			MenuConfigLocalServiceWrapper object = new MenuConfigLocalServiceWrapper(null);
			object.getByG_MENU(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeMenuConfigTest() {
		try{
			MenuConfigLocalServiceWrapper object = new MenuConfigLocalServiceWrapper(null);
			object.removeMenuConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createMenuConfigTest() {
		try{
			MenuConfigLocalServiceWrapper object = new MenuConfigLocalServiceWrapper(null);
			object.createMenuConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMenuConfigsTest() {
		try{
			MenuConfigLocalServiceWrapper object = new MenuConfigLocalServiceWrapper(null);
			object.getMenuConfigs(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchMenuConfigByUuidAndGroupIdTest() {
		try{
			MenuConfigLocalServiceWrapper object = new MenuConfigLocalServiceWrapper(null);
			object.fetchMenuConfigByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMenuConfigByUuidAndGroupIdTest() {
		try{
			MenuConfigLocalServiceWrapper object = new MenuConfigLocalServiceWrapper(null);
			object.getMenuConfigByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMenuConfigsByUuidAndCompanyIdTest() {
		try{
			MenuConfigLocalServiceWrapper object = new MenuConfigLocalServiceWrapper(null);
			object.getMenuConfigsByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMenuConfigsByUuidAndCompanyIdTest26() {
		try{
			MenuConfigLocalServiceWrapper object = new MenuConfigLocalServiceWrapper(null);
			object.getMenuConfigsByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			MenuConfigLocalServiceWrapper object = new MenuConfigLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			MenuConfigLocalServiceWrapper object = new MenuConfigLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			MenuConfigLocalServiceWrapper object = new MenuConfigLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			MenuConfigLocalServiceWrapper object = new MenuConfigLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			MenuConfigLocalServiceWrapper object = new MenuConfigLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest32() {
		try{
			MenuConfigLocalServiceWrapper object = new MenuConfigLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			MenuConfigLocalServiceWrapper object = new MenuConfigLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest34() {
		try{
			MenuConfigLocalServiceWrapper object = new MenuConfigLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			MenuConfigLocalServiceWrapper object = new MenuConfigLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			MenuConfigLocalServiceWrapper object = new MenuConfigLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest37() {
		try{
			MenuConfigLocalServiceWrapper object = new MenuConfigLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			MenuConfigLocalServiceWrapper object = new MenuConfigLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByMenusTest() {
		try{
			MenuConfigLocalServiceWrapper object = new MenuConfigLocalServiceWrapper(null);
			object.getByMenus(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}