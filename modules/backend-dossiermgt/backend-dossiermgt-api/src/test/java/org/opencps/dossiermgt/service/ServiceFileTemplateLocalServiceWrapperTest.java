package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ServiceFileTemplateLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void dynamicQueryTest() {
		try{
			ServiceFileTemplateLocalServiceWrapper object = new ServiceFileTemplateLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest2() {
		try{
			ServiceFileTemplateLocalServiceWrapper object = new ServiceFileTemplateLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			ServiceFileTemplateLocalServiceWrapper object = new ServiceFileTemplateLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			ServiceFileTemplateLocalServiceWrapper object = new ServiceFileTemplateLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_serviceInfoId_fileTemplateNoTest() {
		try{
			ServiceFileTemplateLocalServiceWrapper object = new ServiceFileTemplateLocalServiceWrapper(null);
			object.fetchByF_serviceInfoId_fileTemplateNo(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			ServiceFileTemplateLocalServiceWrapper object = new ServiceFileTemplateLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceFileTemplateTest() {
		try{
			ServiceFileTemplateLocalServiceWrapper object = new ServiceFileTemplateLocalServiceWrapper(null);
			object.getServiceFileTemplate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateServiceFileTemplateTest() {
		try{
			ServiceFileTemplateLocalServiceWrapper object = new ServiceFileTemplateLocalServiceWrapper(null);
			object.updateServiceFileTemplate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateServiceFileTemplateTest9() {
		try{
			ServiceFileTemplateLocalServiceWrapper object = new ServiceFileTemplateLocalServiceWrapper(null);
			object.updateServiceFileTemplate(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addServiceFileTemplateTest() {
		try{
			ServiceFileTemplateLocalServiceWrapper object = new ServiceFileTemplateLocalServiceWrapper(null);
			object.addServiceFileTemplate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addServiceFileTemplateTest11() {
		try{
			ServiceFileTemplateLocalServiceWrapper object = new ServiceFileTemplateLocalServiceWrapper(null);
			object.addServiceFileTemplate(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addServiceFileTemplateTest12() {
		try{
			ServiceFileTemplateLocalServiceWrapper object = new ServiceFileTemplateLocalServiceWrapper(null);
			object.addServiceFileTemplate(Long.valueOf(0), "abcde", "abcde", Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeServiceFileTemplateTest() {
		try{
			ServiceFileTemplateLocalServiceWrapper object = new ServiceFileTemplateLocalServiceWrapper(null);
			object.removeServiceFileTemplate(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateServiceFileTemplateDBTest() {
		try{
			ServiceFileTemplateLocalServiceWrapper object = new ServiceFileTemplateLocalServiceWrapper(null);
			object.updateServiceFileTemplateDB(Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), true, Long.valueOf(0), Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByServiceInfoIdTest() {
		try{
			ServiceFileTemplateLocalServiceWrapper object = new ServiceFileTemplateLocalServiceWrapper(null);
			object.getByServiceInfoId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			ServiceFileTemplateLocalServiceWrapper object = new ServiceFileTemplateLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			ServiceFileTemplateLocalServiceWrapper object = new ServiceFileTemplateLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			ServiceFileTemplateLocalServiceWrapper object = new ServiceFileTemplateLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			ServiceFileTemplateLocalServiceWrapper object = new ServiceFileTemplateLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest20() {
		try{
			ServiceFileTemplateLocalServiceWrapper object = new ServiceFileTemplateLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			ServiceFileTemplateLocalServiceWrapper object = new ServiceFileTemplateLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest22() {
		try{
			ServiceFileTemplateLocalServiceWrapper object = new ServiceFileTemplateLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			ServiceFileTemplateLocalServiceWrapper object = new ServiceFileTemplateLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			ServiceFileTemplateLocalServiceWrapper object = new ServiceFileTemplateLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest25() {
		try{
			ServiceFileTemplateLocalServiceWrapper object = new ServiceFileTemplateLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByServiceTest() {
		try{
			ServiceFileTemplateLocalServiceWrapper object = new ServiceFileTemplateLocalServiceWrapper(null);
			object.getByService(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByServiceInfoIdTest() {
		try{
			ServiceFileTemplateLocalServiceWrapper object = new ServiceFileTemplateLocalServiceWrapper(null);
			object.countByServiceInfoId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByService_EFormTest() {
		try{
			ServiceFileTemplateLocalServiceWrapper object = new ServiceFileTemplateLocalServiceWrapper(null);
			object.countByService_EForm(Long.valueOf(0), true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createServiceFileTemplateTest() {
		try{
			ServiceFileTemplateLocalServiceWrapper object = new ServiceFileTemplateLocalServiceWrapper(null);
			object.createServiceFileTemplate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}

	/*@Test
	public void deleteServiceFileTemplateTest() {
		try{
			ServiceFileTemplateLocalServiceWrapper object = new ServiceFileTemplateLocalServiceWrapper(null);
			object.deleteServiceFileTemplate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteServiceFileTemplateTest31() {
		try{
			ServiceFileTemplateLocalServiceWrapper object = new ServiceFileTemplateLocalServiceWrapper(null);
			object.deleteServiceFileTemplate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}*/
	@Test
	public void fetchServiceFileTemplateTest() {
		try{
			ServiceFileTemplateLocalServiceWrapper object = new ServiceFileTemplateLocalServiceWrapper(null);
			object.fetchServiceFileTemplate(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByService_EFormTest() {
		try{
			ServiceFileTemplateLocalServiceWrapper object = new ServiceFileTemplateLocalServiceWrapper(null);
			object.getByService_EForm(Long.valueOf(0), true, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceFileTemplatesTest() {
		try{
			ServiceFileTemplateLocalServiceWrapper object = new ServiceFileTemplateLocalServiceWrapper(null);
			object.getServiceFileTemplates(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceFileTemplatesCountTest() {
		try{
			ServiceFileTemplateLocalServiceWrapper object = new ServiceFileTemplateLocalServiceWrapper(null);
			object.getServiceFileTemplatesCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}