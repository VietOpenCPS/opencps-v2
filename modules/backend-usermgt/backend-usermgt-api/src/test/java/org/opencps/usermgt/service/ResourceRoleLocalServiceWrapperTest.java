package org.opencps.usermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ResourceRoleLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			ResourceRoleLocalServiceWrapper object = new ResourceRoleLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			ResourceRoleLocalServiceWrapper object = new ResourceRoleLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			ResourceRoleLocalServiceWrapper object = new ResourceRoleLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			ResourceRoleLocalServiceWrapper object = new ResourceRoleLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			ResourceRoleLocalServiceWrapper object = new ResourceRoleLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			ResourceRoleLocalServiceWrapper object = new ResourceRoleLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void luceneSearchEngineTest() {
		try{
			ResourceRoleLocalServiceWrapper object = new ResourceRoleLocalServiceWrapper(null);
			object.luceneSearchEngine(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_className_classPKTest() {
		try{
			ResourceRoleLocalServiceWrapper object = new ResourceRoleLocalServiceWrapper(null);
			object.findByF_className_classPK(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneSearchEngineTest() {
		try{
			ResourceRoleLocalServiceWrapper object = new ResourceRoleLocalServiceWrapper(null);
			object.countLuceneSearchEngine(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			ResourceRoleLocalServiceWrapper object = new ResourceRoleLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			ResourceRoleLocalServiceWrapper object = new ResourceRoleLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			ResourceRoleLocalServiceWrapper object = new ResourceRoleLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			ResourceRoleLocalServiceWrapper object = new ResourceRoleLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			ResourceRoleLocalServiceWrapper object = new ResourceRoleLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest15() {
		try{
			ResourceRoleLocalServiceWrapper object = new ResourceRoleLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			ResourceRoleLocalServiceWrapper object = new ResourceRoleLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest17() {
		try{
			ResourceRoleLocalServiceWrapper object = new ResourceRoleLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			ResourceRoleLocalServiceWrapper object = new ResourceRoleLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			ResourceRoleLocalServiceWrapper object = new ResourceRoleLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest20() {
		try{
			ResourceRoleLocalServiceWrapper object = new ResourceRoleLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			ResourceRoleLocalServiceWrapper object = new ResourceRoleLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getResourceRolesTest() {
		try{
			ResourceRoleLocalServiceWrapper object = new ResourceRoleLocalServiceWrapper(null);
			object.getResourceRoles(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchResourceRoleByUuidAndGroupIdTest() {
		try{
			ResourceRoleLocalServiceWrapper object = new ResourceRoleLocalServiceWrapper(null);
			object.fetchResourceRoleByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_className_classPK_roleIdTest() {
		try{
			ResourceRoleLocalServiceWrapper object = new ResourceRoleLocalServiceWrapper(null);
			object.fetchByF_className_classPK_roleId(Long.valueOf(0), "abcde", "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getResourceRolesByUuidAndCompanyIdTest() {
		try{
			ResourceRoleLocalServiceWrapper object = new ResourceRoleLocalServiceWrapper(null);
			object.getResourceRolesByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getResourceRolesByUuidAndCompanyIdTest26() {
		try{
			ResourceRoleLocalServiceWrapper object = new ResourceRoleLocalServiceWrapper(null);
			object.getResourceRolesByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteResourceRoleTest() {
		try{
			ResourceRoleLocalServiceWrapper object = new ResourceRoleLocalServiceWrapper(null);
			object.deleteResourceRole(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteResourceRoleTest28() {
		try{
			ResourceRoleLocalServiceWrapper object = new ResourceRoleLocalServiceWrapper(null);
			object.deleteResourceRole(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteResourceRoleTest29() {
		try{
			ResourceRoleLocalServiceWrapper object = new ResourceRoleLocalServiceWrapper(null);
			object.deleteResourceRole(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateResourceRoleTest() {
		try{
			ResourceRoleLocalServiceWrapper object = new ResourceRoleLocalServiceWrapper(null);
			object.updateResourceRole(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateResourceRoleTest31() {
		try{
			ResourceRoleLocalServiceWrapper object = new ResourceRoleLocalServiceWrapper(null);
			object.updateResourceRole(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createResourceRoleTest() {
		try{
			ResourceRoleLocalServiceWrapper object = new ResourceRoleLocalServiceWrapper(null);
			object.createResourceRole(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchResourceRoleTest() {
		try{
			ResourceRoleLocalServiceWrapper object = new ResourceRoleLocalServiceWrapper(null);
			object.fetchResourceRole(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getResourceRoleByUuidAndGroupIdTest() {
		try{
			ResourceRoleLocalServiceWrapper object = new ResourceRoleLocalServiceWrapper(null);
			object.getResourceRoleByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getResourceRolesCountTest() {
		try{
			ResourceRoleLocalServiceWrapper object = new ResourceRoleLocalServiceWrapper(null);
			object.getResourceRolesCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addResourceRoleTest() {
		try{
			ResourceRoleLocalServiceWrapper object = new ResourceRoleLocalServiceWrapper(null);
			object.addResourceRole(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addResourceRoleTest37() {
		try{
			ResourceRoleLocalServiceWrapper object = new ResourceRoleLocalServiceWrapper(null);
			object.addResourceRole(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getResourceRoleTest() {
		try{
			ResourceRoleLocalServiceWrapper object = new ResourceRoleLocalServiceWrapper(null);
			object.getResourceRole(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}