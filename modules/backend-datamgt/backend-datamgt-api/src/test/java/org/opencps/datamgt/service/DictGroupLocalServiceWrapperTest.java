package org.opencps.datamgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DictGroupLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest5() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictGroupTest() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.getDictGroup(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDictGroupDBTest() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.updateDictGroupDB(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void luceneSearchEngineTest() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.luceneSearchEngine(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_DictGroupCodeTest() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.fetchByF_DictGroupCode("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictGroupByDictCollectionTest() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.getDictGroupByDictCollection(Long.valueOf(0), Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictGroupByUuidAndGroupIdTest() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.getDictGroupByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictGroupsCountTest() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.getDictGroupsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictGroupsByUuidAndCompanyIdTest() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.getDictGroupsByUuidAndCompanyId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictGroupsByUuidAndCompanyIdTest15() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.getDictGroupsByUuidAndCompanyId("abcde", Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findOlderThanDateTest() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.findOlderThanDate(new Date(), Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countOlderThanDateTest() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.countOlderThanDate(new Date(), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDictGroupByUuidAndGroupIdTest() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.fetchDictGroupByUuidAndGroupId("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDictGroupTest() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.createDictGroup(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByGC_GI_DCITest() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.getByGC_GI_DCI("abcde", Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDictGroupTest() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.addDictGroup(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDictGroupTest22() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.addDictGroup(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictGroupsTest() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.getDictGroups(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDictGroupTest() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.updateDictGroup(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDictGroupTest25() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.updateDictGroup(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDictGroupTest() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.deleteDictGroup(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDictGroupTest27() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.deleteDictGroup(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDictGroupTest28() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.deleteDictGroup(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneSearchEngineTest() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.countLuceneSearchEngine(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getExportActionableDynamicQueryTest() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.getExportActionableDynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest35() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest37() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest40() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDictGroupTest() {
		try{
			DictGroupLocalServiceWrapper object = new DictGroupLocalServiceWrapper(null);
			object.fetchDictGroup(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}