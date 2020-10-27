package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ServiceConfigLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByGovAgencyCodeTest() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.getByGovAgencyCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateServiceConfigTest() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.updateServiceConfig(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, "abcde", true, true, true, true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateServiceConfigTest9() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.updateServiceConfig(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceConfigsTest() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.getServiceConfigs(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchServiceConfigTest() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.fetchServiceConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByServiceInfoTest() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.getByServiceInfo(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByGroupIdTest() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.getByGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceConfigTest() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.getServiceConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateServiceConfigDBTest() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.updateServiceConfigDB(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", 0, "abcde", true, true, true, true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createServiceConfigTest() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.createServiceConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneTest() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.countLucene(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getBySICodeAndGACTest() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.getBySICodeAndGAC(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceConfigsByUuidAndCompanyIdTest() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.getServiceConfigsByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceConfigsByUuidAndCompanyIdTest21() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.getServiceConfigsByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchServiceConfigByUuidAndGroupIdTest() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.fetchServiceConfigByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest28() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest30() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest33() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addServiceConfigTest() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.addServiceConfig(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByLevelTest() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.getByLevel(Long.valueOf(0), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countBySIAndGACTest() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.countBySIAndGAC(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByGovAgencyTest() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.countByGovAgency("abcde", "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByBySIAndGACTest() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.findByBySIAndGAC(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceConfigsCountTest() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.getServiceConfigsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchByGovAgencyTest() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.searchByGovAgency("abcde", "abcde", Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceConfigByUuidAndGroupIdTest() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.getServiceConfigByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeServiceConfigByIdTest() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.removeServiceConfigById(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteServiceConfigTest() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.deleteServiceConfig(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteServiceConfigTest45() {
		try{
			ServiceConfigLocalServiceWrapper object = new ServiceConfigLocalServiceWrapper(null);
			object.deleteServiceConfig(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}