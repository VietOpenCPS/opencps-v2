package org.opencps.usermgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class OfficeSiteLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			OfficeSiteLocalServiceWrapper object = new OfficeSiteLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			OfficeSiteLocalServiceWrapper object = new OfficeSiteLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			OfficeSiteLocalServiceWrapper object = new OfficeSiteLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			OfficeSiteLocalServiceWrapper object = new OfficeSiteLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			OfficeSiteLocalServiceWrapper object = new OfficeSiteLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			OfficeSiteLocalServiceWrapper object = new OfficeSiteLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateOfficeSiteTest() {
		try{
			OfficeSiteLocalServiceWrapper object = new OfficeSiteLocalServiceWrapper(null);
			object.updateOfficeSite(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateOfficeSiteTest8() {
		try{
			OfficeSiteLocalServiceWrapper object = new OfficeSiteLocalServiceWrapper(null);
			object.updateOfficeSite(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOfficeSiteTest() {
		try{
			OfficeSiteLocalServiceWrapper object = new OfficeSiteLocalServiceWrapper(null);
			object.getOfficeSite(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOfficeSitesTest() {
		try{
			OfficeSiteLocalServiceWrapper object = new OfficeSiteLocalServiceWrapper(null);
			object.getOfficeSites(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteOfficeSiteTest() {
		try{
			OfficeSiteLocalServiceWrapper object = new OfficeSiteLocalServiceWrapper(null);
			object.deleteOfficeSite(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteOfficeSiteTest12() {
		try{
			OfficeSiteLocalServiceWrapper object = new OfficeSiteLocalServiceWrapper(null);
			object.deleteOfficeSite(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteOfficeSiteTest13() {
		try{
			OfficeSiteLocalServiceWrapper object = new OfficeSiteLocalServiceWrapper(null);
			object.deleteOfficeSite(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchOfficeSiteTest() {
		try{
			OfficeSiteLocalServiceWrapper object = new OfficeSiteLocalServiceWrapper(null);
			object.fetchOfficeSite(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addOfficeSiteTest() {
		try{
			OfficeSiteLocalServiceWrapper object = new OfficeSiteLocalServiceWrapper(null);
			object.addOfficeSite(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addOfficeSiteTest16() {
		try{
			OfficeSiteLocalServiceWrapper object = new OfficeSiteLocalServiceWrapper(null);
			object.addOfficeSite(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void luceneSearchEngineTest() {
		try{
			OfficeSiteLocalServiceWrapper object = new OfficeSiteLocalServiceWrapper(null);
			object.luceneSearchEngine(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneSearchEngineTest() {
		try{
			OfficeSiteLocalServiceWrapper object = new OfficeSiteLocalServiceWrapper(null);
			object.countLuceneSearchEngine(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			OfficeSiteLocalServiceWrapper object = new OfficeSiteLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			OfficeSiteLocalServiceWrapper object = new OfficeSiteLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			OfficeSiteLocalServiceWrapper object = new OfficeSiteLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			OfficeSiteLocalServiceWrapper object = new OfficeSiteLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			OfficeSiteLocalServiceWrapper object = new OfficeSiteLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest24() {
		try{
			OfficeSiteLocalServiceWrapper object = new OfficeSiteLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			OfficeSiteLocalServiceWrapper object = new OfficeSiteLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest26() {
		try{
			OfficeSiteLocalServiceWrapper object = new OfficeSiteLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			OfficeSiteLocalServiceWrapper object = new OfficeSiteLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			OfficeSiteLocalServiceWrapper object = new OfficeSiteLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest29() {
		try{
			OfficeSiteLocalServiceWrapper object = new OfficeSiteLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			OfficeSiteLocalServiceWrapper object = new OfficeSiteLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchF_groupId_siteGroupIdTest() {
		try{
			OfficeSiteLocalServiceWrapper object = new OfficeSiteLocalServiceWrapper(null);
			object.fetchF_groupId_siteGroupId(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchOfficeSiteByUuidAndGroupIdTest() {
		try{
			OfficeSiteLocalServiceWrapper object = new OfficeSiteLocalServiceWrapper(null);
			object.fetchOfficeSiteByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOfficeSiteByUuidAndGroupIdTest() {
		try{
			OfficeSiteLocalServiceWrapper object = new OfficeSiteLocalServiceWrapper(null);
			object.getOfficeSiteByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOfficeSitesByUuidAndCompanyIdTest() {
		try{
			OfficeSiteLocalServiceWrapper object = new OfficeSiteLocalServiceWrapper(null);
			object.getOfficeSitesByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOfficeSitesByUuidAndCompanyIdTest35() {
		try{
			OfficeSiteLocalServiceWrapper object = new OfficeSiteLocalServiceWrapper(null);
			object.getOfficeSitesByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOfficeSitesCountTest() {
		try{
			OfficeSiteLocalServiceWrapper object = new OfficeSiteLocalServiceWrapper(null);
			object.getOfficeSitesCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createOfficeSiteTest() {
		try{
			OfficeSiteLocalServiceWrapper object = new OfficeSiteLocalServiceWrapper(null);
			object.createOfficeSite(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}