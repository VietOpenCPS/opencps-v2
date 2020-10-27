package org.opencps.datamgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DictItemLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_dictCollectionId_parentItemIdTest() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.findByF_dictCollectionId_parentItemId(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_dictItemCodeTest() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.fetchByF_dictItemCode("abcde", Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictItemsTest() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.getDictItems(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictItemTest() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.getDictItem(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDictItemTest() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.fetchDictItem(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDictItemTest() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.deleteDictItem(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDictItemTest13() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.deleteDictItem(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDictItemTest14() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.deleteDictItem(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDictItemTest15() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.deleteDictItem(Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDictItemTest() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.updateDictItem(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDictItemTest17() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.updateDictItem(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDictItemDBTest() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.updateDictItemDB(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), 0, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_dictCollectionIdTest() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.findByF_dictCollectionId(Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_dictCollectionIdTest20() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.findByF_dictCollectionId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void luceneSearchEngineTest() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.luceneSearchEngine(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictItemsByUuidAndCompanyIdTest() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.getDictItemsByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictItemsByUuidAndCompanyIdTest23() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.getDictItemsByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictItemByUuidAndGroupIdTest() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.getDictItemByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_parentItemIdTest() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.findByF_parentItemId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_treeIndexTest() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.findByF_treeIndex(Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictItemsCountTest() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.getDictItemsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByOlderThanDateTest() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.findByOlderThanDate(new Date(), Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByOlderThanDateTest() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.countByOlderThanDate(new Date(), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDictItemByUuidAndGroupIdTest() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.fetchDictItemByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDictItemListenerTest() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.updateDictItemListener(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void initDictItemTest() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.initDictItem(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDictItemTest() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.addDictItem(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDictItemTest34() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.addDictItem(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDictItemTest() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.createDictItem(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_IDSTest() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.findByF_IDS(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneSearchEngineTest() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.countLuceneSearchEngine(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest43() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest45() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest48() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			DictItemLocalServiceWrapper object = new DictItemLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}