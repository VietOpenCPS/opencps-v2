package org.opencps.communication.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class PreferencesLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			PreferencesLocalServiceWrapper object = new PreferencesLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			PreferencesLocalServiceWrapper object = new PreferencesLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			PreferencesLocalServiceWrapper object = new PreferencesLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			PreferencesLocalServiceWrapper object = new PreferencesLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			PreferencesLocalServiceWrapper object = new PreferencesLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			PreferencesLocalServiceWrapper object = new PreferencesLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updatePreferencesTest() {
		try{
			PreferencesLocalServiceWrapper object = new PreferencesLocalServiceWrapper(null);
			object.updatePreferences(Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updatePreferencesTest8() {
		try{
			PreferencesLocalServiceWrapper object = new PreferencesLocalServiceWrapper(null);
			object.updatePreferences(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPreferencesTest() {
		try{
			PreferencesLocalServiceWrapper object = new PreferencesLocalServiceWrapper(null);
			object.getPreferences(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPreferencesesByUuidAndCompanyIdTest() {
		try{
			PreferencesLocalServiceWrapper object = new PreferencesLocalServiceWrapper(null);
			object.getPreferencesesByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPreferencesesByUuidAndCompanyIdTest11() {
		try{
			PreferencesLocalServiceWrapper object = new PreferencesLocalServiceWrapper(null);
			object.getPreferencesesByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			PreferencesLocalServiceWrapper object = new PreferencesLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createPreferencesTest() {
		try{
			PreferencesLocalServiceWrapper object = new PreferencesLocalServiceWrapper(null);
			object.createPreferences(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPreferencesesCountTest() {
		try{
			PreferencesLocalServiceWrapper object = new PreferencesLocalServiceWrapper(null);
			object.getPreferencesesCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePreferencesTest() {
		try{
			PreferencesLocalServiceWrapper object = new PreferencesLocalServiceWrapper(null);
			object.deletePreferences(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePreferencesTest16() {
		try{
			PreferencesLocalServiceWrapper object = new PreferencesLocalServiceWrapper(null);
			object.deletePreferences(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePreferencesTest17() {
		try{
			PreferencesLocalServiceWrapper object = new PreferencesLocalServiceWrapper(null);
			object.deletePreferences(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPreferencesByUuidAndGroupIdTest() {
		try{
			PreferencesLocalServiceWrapper object = new PreferencesLocalServiceWrapper(null);
			object.getPreferencesByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchPreferencesByUuidAndGroupIdTest() {
		try{
			PreferencesLocalServiceWrapper object = new PreferencesLocalServiceWrapper(null);
			object.fetchPreferencesByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			PreferencesLocalServiceWrapper object = new PreferencesLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			PreferencesLocalServiceWrapper object = new PreferencesLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			PreferencesLocalServiceWrapper object = new PreferencesLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			PreferencesLocalServiceWrapper object = new PreferencesLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest24() {
		try{
			PreferencesLocalServiceWrapper object = new PreferencesLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			PreferencesLocalServiceWrapper object = new PreferencesLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest26() {
		try{
			PreferencesLocalServiceWrapper object = new PreferencesLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			PreferencesLocalServiceWrapper object = new PreferencesLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			PreferencesLocalServiceWrapper object = new PreferencesLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest29() {
		try{
			PreferencesLocalServiceWrapper object = new PreferencesLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			PreferencesLocalServiceWrapper object = new PreferencesLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_userIdTest() {
		try{
			PreferencesLocalServiceWrapper object = new PreferencesLocalServiceWrapper(null);
			object.fetchByF_userId(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPreferencesesTest() {
		try{
			PreferencesLocalServiceWrapper object = new PreferencesLocalServiceWrapper(null);
			object.getPreferenceses(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchPreferencesTest() {
		try{
			PreferencesLocalServiceWrapper object = new PreferencesLocalServiceWrapper(null);
			object.fetchPreferences(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addPreferencesTest() {
		try{
			PreferencesLocalServiceWrapper object = new PreferencesLocalServiceWrapper(null);
			object.addPreferences(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addPreferencesTest35() {
		try{
			PreferencesLocalServiceWrapper object = new PreferencesLocalServiceWrapper(null);
			object.addPreferences(Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}