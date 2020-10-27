package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ServiceProcessRoleLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void dynamicQueryTest() {
		try{
			ServiceProcessRoleLocalServiceWrapper object = new ServiceProcessRoleLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest2() {
		try{
			ServiceProcessRoleLocalServiceWrapper object = new ServiceProcessRoleLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			ServiceProcessRoleLocalServiceWrapper object = new ServiceProcessRoleLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			ServiceProcessRoleLocalServiceWrapper object = new ServiceProcessRoleLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			ServiceProcessRoleLocalServiceWrapper object = new ServiceProcessRoleLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateServiceProcessRoleTest() {
		try{
			ServiceProcessRoleLocalServiceWrapper object = new ServiceProcessRoleLocalServiceWrapper(null);
			object.updateServiceProcessRole(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateServiceProcessRoleTest7() {
		try{
			ServiceProcessRoleLocalServiceWrapper object = new ServiceProcessRoleLocalServiceWrapper(null);
			object.updateServiceProcessRole(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), true, "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addServiceProcessRoleTest() {
		try{
			ServiceProcessRoleLocalServiceWrapper object = new ServiceProcessRoleLocalServiceWrapper(null);
			object.addServiceProcessRole(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeServiceProcessRoleTest() {
		try{
			ServiceProcessRoleLocalServiceWrapper object = new ServiceProcessRoleLocalServiceWrapper(null);
			object.removeServiceProcessRole(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceProcessRolesTest() {
		try{
			ServiceProcessRoleLocalServiceWrapper object = new ServiceProcessRoleLocalServiceWrapper(null);
			object.getServiceProcessRoles(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByS_P_IDTest() {
		try{
			ServiceProcessRoleLocalServiceWrapper object = new ServiceProcessRoleLocalServiceWrapper(null);
			object.findByS_P_ID(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateServiceProcessRoleDBTest() {
		try{
			ServiceProcessRoleLocalServiceWrapper object = new ServiceProcessRoleLocalServiceWrapper(null);
			object.updateServiceProcessRoleDB(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", true, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findBySPSTest() {
		try{
			ServiceProcessRoleLocalServiceWrapper object = new ServiceProcessRoleLocalServiceWrapper(null);
			object.findBySPS(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			ServiceProcessRoleLocalServiceWrapper object = new ServiceProcessRoleLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			ServiceProcessRoleLocalServiceWrapper object = new ServiceProcessRoleLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			ServiceProcessRoleLocalServiceWrapper object = new ServiceProcessRoleLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			ServiceProcessRoleLocalServiceWrapper object = new ServiceProcessRoleLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest18() {
		try{
			ServiceProcessRoleLocalServiceWrapper object = new ServiceProcessRoleLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			ServiceProcessRoleLocalServiceWrapper object = new ServiceProcessRoleLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest20() {
		try{
			ServiceProcessRoleLocalServiceWrapper object = new ServiceProcessRoleLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			ServiceProcessRoleLocalServiceWrapper object = new ServiceProcessRoleLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			ServiceProcessRoleLocalServiceWrapper object = new ServiceProcessRoleLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest23() {
		try{
			ServiceProcessRoleLocalServiceWrapper object = new ServiceProcessRoleLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}

	/*@Test
	public void deleteServiceProcessRoleTest() {
		try{
			ServiceProcessRoleLocalServiceWrapper object = new ServiceProcessRoleLocalServiceWrapper(null);
			object.deleteServiceProcessRole(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteServiceProcessRoleTest25() {
		try{
			ServiceProcessRoleLocalServiceWrapper object = new ServiceProcessRoleLocalServiceWrapper(null);
			object.deleteServiceProcessRole(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}*/
	@Test
	public void getServiceProcessRolesCountTest() {
		try{
			ServiceProcessRoleLocalServiceWrapper object = new ServiceProcessRoleLocalServiceWrapper(null);
			object.getServiceProcessRolesCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByServiceRoleCodeTest() {
		try{
			ServiceProcessRoleLocalServiceWrapper object = new ServiceProcessRoleLocalServiceWrapper(null);
			object.getByServiceRoleCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceProcessRoleTest() {
		try{
			ServiceProcessRoleLocalServiceWrapper object = new ServiceProcessRoleLocalServiceWrapper(null);
			object.getServiceProcessRole(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createServiceProcessRoleTest() {
		try{
			ServiceProcessRoleLocalServiceWrapper object = new ServiceProcessRoleLocalServiceWrapper(null);
			object.createServiceProcessRole(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchServiceProcessRoleTest() {
		try{
			ServiceProcessRoleLocalServiceWrapper object = new ServiceProcessRoleLocalServiceWrapper(null);
			object.fetchServiceProcessRole(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}