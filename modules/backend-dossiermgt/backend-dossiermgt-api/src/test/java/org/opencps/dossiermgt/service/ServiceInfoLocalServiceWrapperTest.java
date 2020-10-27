package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ServiceInfoLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createServiceInfoTest() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.createServiceInfo(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeServiceInfoTest() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.removeServiceInfo(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateServiceInfoTest() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.updateServiceInfo(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, true, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateServiceInfoTest10() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.updateServiceInfo(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteServiceInfoTest() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.deleteServiceInfo(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteServiceInfoTest12() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.deleteServiceInfo(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByGroupTest() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.findByGroup(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByCodeTest() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.getByCode(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateServiceInfoDBTest() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.updateServiceInfoDB(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceInfosByGroupIdTest() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.getServiceInfosByGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceInfosByGroupIdTest18() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.getServiceInfosByGroupId(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneTest() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.countLucene(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceInfosByUuidAndCompanyIdTest() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.getServiceInfosByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceInfosByUuidAndCompanyIdTest21() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.getServiceInfosByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByGroupAndPublicTest() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.findByGroupAndPublic(Long.valueOf(0), true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest28() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest30() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest33() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceInfosTest() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.getServiceInfos(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addServiceInfoTest() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.addServiceInfo(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addServiceInfoTest37() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.addServiceInfo(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, true, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchServiceInfoTest() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.fetchServiceInfo(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByF_GID_SCTest() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.getByF_GID_SC(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceInfoTest() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.getServiceInfo(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByDomainTest() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.fetchByDomain(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countServiceInfosByGroupIdTest() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.countServiceInfosByGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceInfoByUuidAndGroupIdTest() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.getServiceInfoByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceInfosCountTest() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.getServiceInfosCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchServiceInfoByUuidAndGroupIdTest() {
		try{
			ServiceInfoLocalServiceWrapper object = new ServiceInfoLocalServiceWrapper(null);
			object.fetchServiceInfoByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}