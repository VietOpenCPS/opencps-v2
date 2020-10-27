package org.opencps.usermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ResourceUserLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			ResourceUserLocalServiceWrapper object = new ResourceUserLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			ResourceUserLocalServiceWrapper object = new ResourceUserLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			ResourceUserLocalServiceWrapper object = new ResourceUserLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			ResourceUserLocalServiceWrapper object = new ResourceUserLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			ResourceUserLocalServiceWrapper object = new ResourceUserLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			ResourceUserLocalServiceWrapper object = new ResourceUserLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void luceneSearchEngineTest() {
		try{
			ResourceUserLocalServiceWrapper object = new ResourceUserLocalServiceWrapper(null);
			object.luceneSearchEngine(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_className_classPKTest() {
		try{
			ResourceUserLocalServiceWrapper object = new ResourceUserLocalServiceWrapper(null);
			object.findByF_className_classPK(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneSearchEngineTest() {
		try{
			ResourceUserLocalServiceWrapper object = new ResourceUserLocalServiceWrapper(null);
			object.countLuceneSearchEngine(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			ResourceUserLocalServiceWrapper object = new ResourceUserLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			ResourceUserLocalServiceWrapper object = new ResourceUserLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			ResourceUserLocalServiceWrapper object = new ResourceUserLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			ResourceUserLocalServiceWrapper object = new ResourceUserLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			ResourceUserLocalServiceWrapper object = new ResourceUserLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest15() {
		try{
			ResourceUserLocalServiceWrapper object = new ResourceUserLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			ResourceUserLocalServiceWrapper object = new ResourceUserLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest17() {
		try{
			ResourceUserLocalServiceWrapper object = new ResourceUserLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			ResourceUserLocalServiceWrapper object = new ResourceUserLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			ResourceUserLocalServiceWrapper object = new ResourceUserLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest20() {
		try{
			ResourceUserLocalServiceWrapper object = new ResourceUserLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			ResourceUserLocalServiceWrapper object = new ResourceUserLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getResourceUsersTest() {
		try{
			ResourceUserLocalServiceWrapper object = new ResourceUserLocalServiceWrapper(null);
			object.getResourceUsers(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_className_classPK_toUserIdTest() {
		try{
			ResourceUserLocalServiceWrapper object = new ResourceUserLocalServiceWrapper(null);
			object.fetchByF_className_classPK_toUserId(Long.valueOf(0), "abcde", "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getResourceUsersByUuidAndCompanyIdTest() {
		try{
			ResourceUserLocalServiceWrapper object = new ResourceUserLocalServiceWrapper(null);
			object.getResourceUsersByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getResourceUsersByUuidAndCompanyIdTest25() {
		try{
			ResourceUserLocalServiceWrapper object = new ResourceUserLocalServiceWrapper(null);
			object.getResourceUsersByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchResourceUserByUuidAndGroupIdTest() {
		try{
			ResourceUserLocalServiceWrapper object = new ResourceUserLocalServiceWrapper(null);
			object.fetchResourceUserByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getResourceUserByUuidAndGroupIdTest() {
		try{
			ResourceUserLocalServiceWrapper object = new ResourceUserLocalServiceWrapper(null);
			object.getResourceUserByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchResourceUserTest() {
		try{
			ResourceUserLocalServiceWrapper object = new ResourceUserLocalServiceWrapper(null);
			object.fetchResourceUser(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getResourceUsersCountTest() {
		try{
			ResourceUserLocalServiceWrapper object = new ResourceUserLocalServiceWrapper(null);
			object.getResourceUsersCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateResourceUserTest() {
		try{
			ResourceUserLocalServiceWrapper object = new ResourceUserLocalServiceWrapper(null);
			object.updateResourceUser(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateResourceUserTest31() {
		try{
			ResourceUserLocalServiceWrapper object = new ResourceUserLocalServiceWrapper(null);
			object.updateResourceUser(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteResourceUserTest() {
		try{
			ResourceUserLocalServiceWrapper object = new ResourceUserLocalServiceWrapper(null);
			object.deleteResourceUser(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteResourceUserTest33() {
		try{
			ResourceUserLocalServiceWrapper object = new ResourceUserLocalServiceWrapper(null);
			object.deleteResourceUser(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteResourceUserTest34() {
		try{
			ResourceUserLocalServiceWrapper object = new ResourceUserLocalServiceWrapper(null);
			object.deleteResourceUser(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createResourceUserTest() {
		try{
			ResourceUserLocalServiceWrapper object = new ResourceUserLocalServiceWrapper(null);
			object.createResourceUser(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getResourceUserTest() {
		try{
			ResourceUserLocalServiceWrapper object = new ResourceUserLocalServiceWrapper(null);
			object.getResourceUser(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addResourceUserTest() {
		try{
			ResourceUserLocalServiceWrapper object = new ResourceUserLocalServiceWrapper(null);
			object.addResourceUser(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addResourceUserTest38() {
		try{
			ResourceUserLocalServiceWrapper object = new ResourceUserLocalServiceWrapper(null);
			object.addResourceUser(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}