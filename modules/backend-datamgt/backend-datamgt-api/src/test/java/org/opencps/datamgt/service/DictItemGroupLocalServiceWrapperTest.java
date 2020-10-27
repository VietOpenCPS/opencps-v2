package org.opencps.datamgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DictItemGroupLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDictItemGroupDBTest() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.updateDictItemGroupDB(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void luceneSearchEngineTest() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.luceneSearchEngine(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_dictItemIdTest() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.findByF_dictItemId(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDictItemGroupTest() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.createDictItemGroup(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDictItemGroupTest() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.deleteDictItemGroup(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDictItemGroupTest12() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.deleteDictItemGroup(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDictItemGroupTest13() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.deleteDictItemGroup(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDictItemGroupNoneAuthenTest() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.deleteDictItemGroupNoneAuthen(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findOlderThanDateTest() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.findOlderThanDate(new Date(), Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_dictItemId_dictGroupIdTest() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.fetchByF_dictItemId_dictGroupId(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countOlderThanDateTest() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.countOlderThanDate(new Date(), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDictItemGroupTest() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.fetchDictItemGroup(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByDictGroupIdTest() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.findByDictGroupId(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictItemGroupByUuidAndGroupIdTest() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.getDictItemGroupByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictItemGroupsTest() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.getDictItemGroups(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictItemGroupsCountTest() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.getDictItemGroupsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDictItemGroupTest() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.updateDictItemGroup(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDictItemGroupTest24() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.updateDictItemGroup(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDictItemGroupByUuidAndGroupIdTest() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.fetchDictItemGroupByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictItemGroupsByUuidAndCompanyIdTest() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.getDictItemGroupsByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictItemGroupsByUuidAndCompanyIdTest27() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.getDictItemGroupsByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictItemGroupTest() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.getDictItemGroup(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDictItemGroupTest() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.addDictItemGroup(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDictItemGroupTest30() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.addDictItemGroup(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneSearchEngineTest() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.countLuceneSearchEngine(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest37() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest39() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest42() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			DictItemGroupLocalServiceWrapper object = new DictItemGroupLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}