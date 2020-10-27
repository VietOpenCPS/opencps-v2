package org.opencps.dossiermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DeliverableTypeRoleLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			DeliverableTypeRoleLocalServiceWrapper object = new DeliverableTypeRoleLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			DeliverableTypeRoleLocalServiceWrapper object = new DeliverableTypeRoleLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			DeliverableTypeRoleLocalServiceWrapper object = new DeliverableTypeRoleLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			DeliverableTypeRoleLocalServiceWrapper object = new DeliverableTypeRoleLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			DeliverableTypeRoleLocalServiceWrapper object = new DeliverableTypeRoleLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			DeliverableTypeRoleLocalServiceWrapper object = new DeliverableTypeRoleLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableTypeRoleTest() {
		try{
			DeliverableTypeRoleLocalServiceWrapper object = new DeliverableTypeRoleLocalServiceWrapper(null);
			object.getDeliverableTypeRole(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDeliverableTypeRoleDBTest() {
		try{
			DeliverableTypeRoleLocalServiceWrapper object = new DeliverableTypeRoleLocalServiceWrapper(null);
			object.updateDeliverableTypeRoleDB(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRoleIdByTypesTest() {
		try{
			DeliverableTypeRoleLocalServiceWrapper object = new DeliverableTypeRoleLocalServiceWrapper(null);
			object.getRoleIdByTypes(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRolesByTypeTest() {
		try{
			DeliverableTypeRoleLocalServiceWrapper object = new DeliverableTypeRoleLocalServiceWrapper(null);
			object.getRolesByType(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDeliverableTypeRoleByUuidAndGroupIdTest() {
		try{
			DeliverableTypeRoleLocalServiceWrapper object = new DeliverableTypeRoleLocalServiceWrapper(null);
			object.fetchDeliverableTypeRoleByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableTypeRoleByUuidAndGroupIdTest() {
		try{
			DeliverableTypeRoleLocalServiceWrapper object = new DeliverableTypeRoleLocalServiceWrapper(null);
			object.getDeliverableTypeRoleByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableTypeRolesByUuidAndCompanyIdTest() {
		try{
			DeliverableTypeRoleLocalServiceWrapper object = new DeliverableTypeRoleLocalServiceWrapper(null);
			object.getDeliverableTypeRolesByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableTypeRolesByUuidAndCompanyIdTest14() {
		try{
			DeliverableTypeRoleLocalServiceWrapper object = new DeliverableTypeRoleLocalServiceWrapper(null);
			object.getDeliverableTypeRolesByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDeliverableTypeRoleTest() {
		try{
			DeliverableTypeRoleLocalServiceWrapper object = new DeliverableTypeRoleLocalServiceWrapper(null);
			object.deleteDeliverableTypeRole(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDeliverableTypeRoleTest16() {
		try{
			DeliverableTypeRoleLocalServiceWrapper object = new DeliverableTypeRoleLocalServiceWrapper(null);
			object.deleteDeliverableTypeRole(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDeliverableTypeRoleTest() {
		try{
			DeliverableTypeRoleLocalServiceWrapper object = new DeliverableTypeRoleLocalServiceWrapper(null);
			object.createDeliverableTypeRole(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableTypeRolesCountTest() {
		try{
			DeliverableTypeRoleLocalServiceWrapper object = new DeliverableTypeRoleLocalServiceWrapper(null);
			object.getDeliverableTypeRolesCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDeliverableTypeRoleTest() {
		try{
			DeliverableTypeRoleLocalServiceWrapper object = new DeliverableTypeRoleLocalServiceWrapper(null);
			object.fetchDeliverableTypeRole(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDeliverableTypeRoleTest() {
		try{
			DeliverableTypeRoleLocalServiceWrapper object = new DeliverableTypeRoleLocalServiceWrapper(null);
			object.updateDeliverableTypeRole(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDeliverableTypeRoleTest() {
		try{
			DeliverableTypeRoleLocalServiceWrapper object = new DeliverableTypeRoleLocalServiceWrapper(null);
			object.addDeliverableTypeRole(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableTypeRolesTest() {
		try{
			DeliverableTypeRoleLocalServiceWrapper object = new DeliverableTypeRoleLocalServiceWrapper(null);
			object.getDeliverableTypeRoles(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			DeliverableTypeRoleLocalServiceWrapper object = new DeliverableTypeRoleLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			DeliverableTypeRoleLocalServiceWrapper object = new DeliverableTypeRoleLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			DeliverableTypeRoleLocalServiceWrapper object = new DeliverableTypeRoleLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			DeliverableTypeRoleLocalServiceWrapper object = new DeliverableTypeRoleLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			DeliverableTypeRoleLocalServiceWrapper object = new DeliverableTypeRoleLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest28() {
		try{
			DeliverableTypeRoleLocalServiceWrapper object = new DeliverableTypeRoleLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			DeliverableTypeRoleLocalServiceWrapper object = new DeliverableTypeRoleLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest30() {
		try{
			DeliverableTypeRoleLocalServiceWrapper object = new DeliverableTypeRoleLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			DeliverableTypeRoleLocalServiceWrapper object = new DeliverableTypeRoleLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			DeliverableTypeRoleLocalServiceWrapper object = new DeliverableTypeRoleLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest33() {
		try{
			DeliverableTypeRoleLocalServiceWrapper object = new DeliverableTypeRoleLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			DeliverableTypeRoleLocalServiceWrapper object = new DeliverableTypeRoleLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}