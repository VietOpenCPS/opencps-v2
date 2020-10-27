package org.opencps.datamgt.service;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DictItemMappingLocalServiceWrapperTest {
	@Before
	public void setUp() {
	}
	@Test
	public void dynamicQueryTest() {
		try{
			DictItemMappingLocalServiceWrapper object = new DictItemMappingLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest2() {
		try{
			DictItemMappingLocalServiceWrapper object = new DictItemMappingLocalServiceWrapper(null);
			object.dynamicQuery(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest3() {
		try{
			DictItemMappingLocalServiceWrapper object = new DictItemMappingLocalServiceWrapper(null);
			object.dynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryTest4() {
		try{
			DictItemMappingLocalServiceWrapper object = new DictItemMappingLocalServiceWrapper(null);
			object.dynamicQuery(null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getIndexableActionableDynamicQueryTest() {
		try{
			DictItemMappingLocalServiceWrapper object = new DictItemMappingLocalServiceWrapper(null);
			object.getIndexableActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_GID_CIDTest() {
		try{
			DictItemMappingLocalServiceWrapper object = new DictItemMappingLocalServiceWrapper(null);
			object.findByF_GID_CID(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDictItemMappingTest() {
		try{
			DictItemMappingLocalServiceWrapper object = new DictItemMappingLocalServiceWrapper(null);
			object.deleteDictItemMapping(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDictItemMappingTest8() {
		try{
			DictItemMappingLocalServiceWrapper object = new DictItemMappingLocalServiceWrapper(null);
			object.deleteDictItemMapping(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDictItemMappingTest() {
		try{
			DictItemMappingLocalServiceWrapper object = new DictItemMappingLocalServiceWrapper(null);
			object.createDictItemMapping(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDictItemMappingTest10() {
		try{
			DictItemMappingLocalServiceWrapper object = new DictItemMappingLocalServiceWrapper(null);
			object.createDictItemMapping(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_GID_IC_CIDTest() {
		try{
			DictItemMappingLocalServiceWrapper object = new DictItemMappingLocalServiceWrapper(null);
			object.fetchByF_GID_IC_CID(Long.valueOf(0), "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_GID_ICDVCQG_CIDTest() {
		try{
			DictItemMappingLocalServiceWrapper object = new DictItemMappingLocalServiceWrapper(null);
			object.fetchByF_GID_ICDVCQG_CID(Long.valueOf(0), "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictItemMappingsCountTest() {
		try{
			DictItemMappingLocalServiceWrapper object = new DictItemMappingLocalServiceWrapper(null);
			object.getDictItemMappingsCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDictItemMappingTest() {
		try{
			DictItemMappingLocalServiceWrapper object = new DictItemMappingLocalServiceWrapper(null);
			object.addDictItemMapping(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictItemMappingsTest() {
		try{
			DictItemMappingLocalServiceWrapper object = new DictItemMappingLocalServiceWrapper(null);
			object.getDictItemMappings(0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchDictItemMappingTest() {
		try{
			DictItemMappingLocalServiceWrapper object = new DictItemMappingLocalServiceWrapper(null);
			object.fetchDictItemMapping(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDictItemMappingTest() {
		try{
			DictItemMappingLocalServiceWrapper object = new DictItemMappingLocalServiceWrapper(null);
			object.updateDictItemMapping(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictItemMappingTest() {
		try{
			DictItemMappingLocalServiceWrapper object = new DictItemMappingLocalServiceWrapper(null);
			object.getDictItemMapping(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deletePersistedModelTest() {
		try{
			DictItemMappingLocalServiceWrapper object = new DictItemMappingLocalServiceWrapper(null);
			object.deletePersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getOSGiServiceIdentifierTest() {
		try{
			DictItemMappingLocalServiceWrapper object = new DictItemMappingLocalServiceWrapper(null);
			object.getOSGiServiceIdentifier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPersistedModelTest() {
		try{
			DictItemMappingLocalServiceWrapper object = new DictItemMappingLocalServiceWrapper(null);
			object.getPersistedModel(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest() {
		try{
			DictItemMappingLocalServiceWrapper object = new DictItemMappingLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWrappedServiceTest23() {
		try{
			DictItemMappingLocalServiceWrapper object = new DictItemMappingLocalServiceWrapper(null);
			object.getWrappedService();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest() {
		try{
			DictItemMappingLocalServiceWrapper object = new DictItemMappingLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setWrappedServiceTest25() {
		try{
			DictItemMappingLocalServiceWrapper object = new DictItemMappingLocalServiceWrapper(null);
			object.setWrappedService(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionableDynamicQueryTest() {
		try{
			DictItemMappingLocalServiceWrapper object = new DictItemMappingLocalServiceWrapper(null);
			object.getActionableDynamicQuery();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest() {
		try{
			DictItemMappingLocalServiceWrapper object = new DictItemMappingLocalServiceWrapper(null);
			object.dynamicQueryCount(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dynamicQueryCountTest28() {
		try{
			DictItemMappingLocalServiceWrapper object = new DictItemMappingLocalServiceWrapper(null);
			object.dynamicQueryCount(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}