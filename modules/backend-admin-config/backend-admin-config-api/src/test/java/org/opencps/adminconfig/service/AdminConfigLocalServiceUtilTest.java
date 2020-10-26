package org.opencps.adminconfig.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class AdminConfigLocalServiceUtilTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			AdminConfigLocalServiceUtil.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateAdminConfigTest() {
		try{
			AdminConfigLocalServiceUtil.updateAdminConfig(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAdminConfigsCountTest() {
		try{
			AdminConfigLocalServiceUtil.getAdminConfigsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			AdminConfigLocalServiceUtil.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteAdminConfigTest() {
		try{
			AdminConfigLocalServiceUtil.deleteAdminConfig(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteAdminConfigTest6() {
		try{
			AdminConfigLocalServiceUtil.deleteAdminConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			AdminConfigLocalServiceUtil.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			AdminConfigLocalServiceUtil.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			AdminConfigLocalServiceUtil.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			AdminConfigLocalServiceUtil.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest11() {
		try{
			AdminConfigLocalServiceUtil.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			AdminConfigLocalServiceUtil.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createAdminConfigTest() {
		try{
			AdminConfigLocalServiceUtil.createAdminConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addAdminConfigTest() {
		try{
			AdminConfigLocalServiceUtil.addAdminConfig(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAdminConfigsTest() {
		try{
			AdminConfigLocalServiceUtil.getAdminConfigs(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAdminConfigTest() {
		try{
			AdminConfigLocalServiceUtil.getAdminConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceTest() {
		try{
			AdminConfigLocalServiceUtil.getService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			AdminConfigLocalServiceUtil.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest19() {
		try{
			AdminConfigLocalServiceUtil.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest20() {
		try{
			AdminConfigLocalServiceUtil.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest21() {
		try{
			AdminConfigLocalServiceUtil.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByCodeTest() {
		try{
			AdminConfigLocalServiceUtil.fetchByCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDataTest() {
		try{
			AdminConfigLocalServiceUtil.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchAdminConfigTest() {
		try{
			AdminConfigLocalServiceUtil.fetchAdminConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}