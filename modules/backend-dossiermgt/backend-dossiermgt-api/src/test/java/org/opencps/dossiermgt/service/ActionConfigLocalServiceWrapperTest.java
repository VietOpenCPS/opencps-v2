package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ActionConfigLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			ActionConfigLocalServiceWrapper object = new ActionConfigLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			ActionConfigLocalServiceWrapper object = new ActionConfigLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			ActionConfigLocalServiceWrapper object = new ActionConfigLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			ActionConfigLocalServiceWrapper object = new ActionConfigLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			ActionConfigLocalServiceWrapper object = new ActionConfigLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			ActionConfigLocalServiceWrapper object = new ActionConfigLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateActionConfigTest() {
		try{
			ActionConfigLocalServiceWrapper object = new ActionConfigLocalServiceWrapper(null);
			object.updateActionConfig(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateActionConfigTest8() {
		try{
			ActionConfigLocalServiceWrapper object = new ActionConfigLocalServiceWrapper(null);
			object.updateActionConfig(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", true, "abcde", "abcde", true, 0, 0, true, true, "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchActionConfigTest() {
		try{
			ActionConfigLocalServiceWrapper object = new ActionConfigLocalServiceWrapper(null);
			object.fetchActionConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteActionConfigTest() {
		try{
			ActionConfigLocalServiceWrapper object = new ActionConfigLocalServiceWrapper(null);
			object.deleteActionConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteActionConfigTest11() {
		try{
			ActionConfigLocalServiceWrapper object = new ActionConfigLocalServiceWrapper(null);
			object.deleteActionConfig(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByGroupIdTest() {
		try{
			ActionConfigLocalServiceWrapper object = new ActionConfigLocalServiceWrapper(null);
			object.getByGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByCodeTest() {
		try{
			ActionConfigLocalServiceWrapper object = new ActionConfigLocalServiceWrapper(null);
			object.getByCode(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addActionConfigTest() {
		try{
			ActionConfigLocalServiceWrapper object = new ActionConfigLocalServiceWrapper(null);
			object.addActionConfig(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addActionConfigTest15() {
		try{
			ActionConfigLocalServiceWrapper object = new ActionConfigLocalServiceWrapper(null);
			object.addActionConfig(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", true, "abcde", "abcde", true, 0, 0, true, true, "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionConfigsTest() {
		try{
			ActionConfigLocalServiceWrapper object = new ActionConfigLocalServiceWrapper(null);
			object.getActionConfigs(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByG_ETTest() {
		try{
			ActionConfigLocalServiceWrapper object = new ActionConfigLocalServiceWrapper(null);
			object.getByG_ET(Long.valueOf(0), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormTest() {
		try{
			ActionConfigLocalServiceWrapper object = new ActionConfigLocalServiceWrapper(null);
			object.getForm(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateActionConfigDBTest() {
		try{
			ActionConfigLocalServiceWrapper object = new ActionConfigLocalServiceWrapper(null);
			object.updateActionConfigDB(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", true, "abcde", true, 0, 0, 0, 0, true, "abcde", "abcde", "abcde", "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionConfigTest() {
		try{
			ActionConfigLocalServiceWrapper object = new ActionConfigLocalServiceWrapper(null);
			object.getActionConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchActionConfigByUuidAndGroupIdTest() {
		try{
			ActionConfigLocalServiceWrapper object = new ActionConfigLocalServiceWrapper(null);
			object.fetchActionConfigByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionConfigsByUuidAndCompanyIdTest() {
		try{
			ActionConfigLocalServiceWrapper object = new ActionConfigLocalServiceWrapper(null);
			object.getActionConfigsByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionConfigsByUuidAndCompanyIdTest23() {
		try{
			ActionConfigLocalServiceWrapper object = new ActionConfigLocalServiceWrapper(null);
			object.getActionConfigsByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionConfigByUuidAndGroupIdTest() {
		try{
			ActionConfigLocalServiceWrapper object = new ActionConfigLocalServiceWrapper(null);
			object.getActionConfigByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createActionConfigTest() {
		try{
			ActionConfigLocalServiceWrapper object = new ActionConfigLocalServiceWrapper(null);
			object.createActionConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeActionConfigTest() {
		try{
			ActionConfigLocalServiceWrapper object = new ActionConfigLocalServiceWrapper(null);
			object.removeActionConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionConfigsCountTest() {
		try{
			ActionConfigLocalServiceWrapper object = new ActionConfigLocalServiceWrapper(null);
			object.getActionConfigsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			ActionConfigLocalServiceWrapper object = new ActionConfigLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			ActionConfigLocalServiceWrapper object = new ActionConfigLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			ActionConfigLocalServiceWrapper object = new ActionConfigLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			ActionConfigLocalServiceWrapper object = new ActionConfigLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			ActionConfigLocalServiceWrapper object = new ActionConfigLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest33() {
		try{
			ActionConfigLocalServiceWrapper object = new ActionConfigLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			ActionConfigLocalServiceWrapper object = new ActionConfigLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest35() {
		try{
			ActionConfigLocalServiceWrapper object = new ActionConfigLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			ActionConfigLocalServiceWrapper object = new ActionConfigLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			ActionConfigLocalServiceWrapper object = new ActionConfigLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest38() {
		try{
			ActionConfigLocalServiceWrapper object = new ActionConfigLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			ActionConfigLocalServiceWrapper object = new ActionConfigLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}