package org.opencps.datamgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DictCollectionLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_dictCollectionCodeTest() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.fetchByF_dictCollectionCode("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDictCollectionTest() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.createDictCollection(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDictCollectionTest() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.fetchDictCollection(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void activeTest() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.active(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByGTest() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.findByG(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void inactiveTest() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.inactive(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDictCollectionDBTest() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.updateDictCollectionDB(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDictCollectionTest() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.deleteDictCollection(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDictCollectionTest15() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.deleteDictCollection(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDictCollectionTest16() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.deleteDictCollection(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDictCollectionTest() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.updateDictCollection(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDictCollectionTest18() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.updateDictCollection(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDictCollectionTest() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.addDictCollection(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDictCollectionTest20() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.addDictCollection(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictCollectionTest() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.getDictCollection(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void luceneSearchEngineTest() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.luceneSearchEngine(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictCollectionsTest() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.getDictCollections(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void initDictCollectionTest() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.initDictCollection(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDictCollectionPublishTest() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.updateDictCollectionPublish(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findOlderThanDateTest() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.findOlderThanDate(new Date(), Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countOlderThanDateTest() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.countOlderThanDate(new Date(), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictCollectionsCountTest() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.getDictCollectionsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDictCollectionByUuidAndGroupIdTest() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.fetchDictCollectionByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictCollectionByUuidAndGroupIdTest() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.getDictCollectionByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictCollectionsByUuidAndCompanyIdTest() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.getDictCollectionsByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictCollectionsByUuidAndCompanyIdTest32() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.getDictCollectionsByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void changeStatusTest() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.changeStatus(Long.valueOf(0), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneSearchEngineTest() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.countLuceneSearchEngine(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest40() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest42() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest45() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			DictCollectionLocalServiceWrapper object = new DictCollectionLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}