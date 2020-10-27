package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ServiceProcessLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createServiceProcessTest() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.createServiceProcess(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeServiceProcessTest() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.removeServiceProcess(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateServiceProcessTest() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.updateServiceProcess(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateServiceProcessTest10() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.updateServiceProcess(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Double.valueOf(0.0), 0, Long.valueOf(0), true, "abcde", true, "abcde", true, true, "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void initServiceProcessTest() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.initServiceProcess(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void cloneServiceProcessTest() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.cloneServiceProcess(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void cloneServiceProcessTest13() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.cloneServiceProcess(Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceProcessesTest() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.getServiceProcesses(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByG_IDTest() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.getByG_ID(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByG_PNOTest() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.getByG_PNO(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateServiceProcessDBTest() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.updateServiceProcessDB(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Double.valueOf(0.0), 0, true, "abcde", "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchServiceProcessTest() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.fetchServiceProcess(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneTest() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.countLucene(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceByCodeTest() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.getServiceByCode(Long.valueOf(0), "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceProcessByUuidAndGroupIdTest() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.getServiceProcessByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceProcessesByUuidAndCompanyIdTest() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.getServiceProcessesByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceProcessesByUuidAndCompanyIdTest24() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.getServiceProcessesByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchServiceProcessByUuidAndGroupIdTest() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.fetchServiceProcessByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceProcessTest() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.getServiceProcess(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest32() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest34() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest37() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByServerNoTest() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.getByServerNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteServiceProcessTest() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.deleteServiceProcess(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteServiceProcessTest41() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.deleteServiceProcess(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceProcessesCountTest() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.getServiceProcessesCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addServiceProcessTest() {
		try{
			ServiceProcessLocalServiceWrapper object = new ServiceProcessLocalServiceWrapper(null);
			object.addServiceProcess(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}