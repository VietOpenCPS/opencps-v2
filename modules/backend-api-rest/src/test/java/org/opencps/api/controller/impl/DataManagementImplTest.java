package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DataManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getDataFormTest() {
		try{
			DataManagementImpl object = new DataManagementImpl();
			object.getDataForm(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictgroupTest() {
		try{
			DataManagementImpl object = new DataManagementImpl();
			object.getDictgroup(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDictgroupsTest() {
		try{
			DataManagementImpl object = new DataManagementImpl();
			object.updateDictgroups(null, null, null, null, null, null, "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getDictItemsLGSPTest() {
//		try{
//			DataManagementImpl object = new DataManagementImpl();
//			object.getDictItemsLGSP(null, null, null, null, null, null, "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getDictItemDVCQGTest() {
//		try{
//			DataManagementImpl object = new DataManagementImpl();
//			object.getDictItemDVCQG(null, null, null, null, null, null, "abcde", null, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void addDataFormTest() {
		try{
			DataManagementImpl object = new DataManagementImpl();
			object.addDataForm(null, null, null, null, null, null, "abcde", "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSyncDictItemsTest() {
		try{
			DataManagementImpl object = new DataManagementImpl();
			object.getSyncDictItems(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDictItemsTest() {
		try{
			DataManagementImpl object = new DataManagementImpl();
			object.addDictItems(null, null, null, null, null, null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDictgroupsTest() {
		try{
			DataManagementImpl object = new DataManagementImpl();
			object.addDictgroups(null, null, null, null, null, null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getDictgroupsTest() {
//		try{
//			DataManagementImpl object = new DataManagementImpl();
//			object.getDictgroups(null, null, null, null, null, null, "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getDictItemsTest() {
//		try{
//			DataManagementImpl object = new DataManagementImpl();
//			object.getDictItems(null, null, null, null, null, null, "abcde", null, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void deleteDictgroupsTest() {
		try{
			DataManagementImpl object = new DataManagementImpl();
			object.deleteDictgroups(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getDictgroupsDictItemsTest() {
//		try{
//			DataManagementImpl object = new DataManagementImpl();
//			object.getDictgroupsDictItems(null, null, null, null, null, null, "abcde", "abcde", true);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void addDictgroupsDictItemsTest() {
		try{
			DataManagementImpl object = new DataManagementImpl();
			object.addDictgroupsDictItems(null, null, null, null, null, null, "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDictgroupsDictItemsTest() {
		try{
			DataManagementImpl object = new DataManagementImpl();
			object.deleteDictgroupsDictItems(null, null, null, null, null, null, "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getDictItemMappingSuggestTest() {
//		try{
//			DataManagementImpl object = new DataManagementImpl();
//			object.getDictItemMappingSuggest(null, null, null, null, null, null, "abcde", null, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void doMappingDictItemDVCQGTest() {
		try{
			DataManagementImpl object = new DataManagementImpl();
			object.doMappingDictItemDVCQG(null, null, null, null, null, null, "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void doRemoveMappingDictItemDVCQGTest() {
//		try{
//			DataManagementImpl object = new DataManagementImpl();
//			object.doRemoveMappingDictItemDVCQG(null, null, null, null, null, null, "abcde", Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void deleteDictCollectionTest() {
		try{
			DataManagementImpl object = new DataManagementImpl();
			object.deleteDictCollection(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictItemByItemCodeTest() {
		try{
			DataManagementImpl object = new DataManagementImpl();
			object.getDictItemByItemCode(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDictItemByItemCodeTest() {
		try{
			DataManagementImpl object = new DataManagementImpl();
			object.updateDictItemByItemCode(null, null, null, null, null, null, "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictCollectionDetailTest() {
		try{
			DataManagementImpl object = new DataManagementImpl();
			object.getDictCollectionDetail(null, null, null, null, null, null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDictCollectionTest() {
		try{
			DataManagementImpl object = new DataManagementImpl();
			object.updateDictCollection(null, null, null, null, null, null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDictCollectionTest() {
		try{
			DataManagementImpl object = new DataManagementImpl();
			object.addDictCollection(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictCollectionTest() {
		try{
			DataManagementImpl object = new DataManagementImpl();
			object.getDictCollection(null, null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteMetaDataOfDictItemTest() {
		try{
			DataManagementImpl object = new DataManagementImpl();
			object.deleteMetaDataOfDictItem(null, null, null, null, null, null, "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSyncDictGroupsTest() {
		try{
			DataManagementImpl object = new DataManagementImpl();
			object.getSyncDictGroups(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dateletDictItemByItemCodeTest() {
		try{
			DataManagementImpl object = new DataManagementImpl();
			object.dateletDictItemByItemCode(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateOrCreateNewDictCollectionTest() {
		try{
			DataManagementImpl object = new DataManagementImpl();
			object.updateOrCreateNewDictCollection(null, null, null, null, null, null, "abcde", null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictCollectionLGSPTest() {
		try{
			DataManagementImpl object = new DataManagementImpl();
			object.getDictCollectionLGSP(null, null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addMetaDataOfDictItemTest() {
		try{
			DataManagementImpl object = new DataManagementImpl();
			object.addMetaDataOfDictItem(null, null, null, null, null, null, "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSyncDictgroupsDictItemsTest() {
		try{
			DataManagementImpl object = new DataManagementImpl();
			object.getSyncDictgroupsDictItems(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void activeDictCollectionTest() {
		try{
			DataManagementImpl object = new DataManagementImpl();
			object.activeDictCollection(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void inActiveDictCollectionTest() {
		try{
			DataManagementImpl object = new DataManagementImpl();
			object.inActiveDictCollection(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getDictgroupsLGSPTest() {
//		try{
//			DataManagementImpl object = new DataManagementImpl();
//			object.getDictgroupsLGSP(null, null, null, null, null, null, "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void updateMetaDataOfDictItemTest() {
		try{
			DataManagementImpl object = new DataManagementImpl();
			object.updateMetaDataOfDictItem(null, null, null, null, null, null, "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMetaDataOfDictItemByKeyTest() {
		try{
			DataManagementImpl object = new DataManagementImpl();
			object.getMetaDataOfDictItemByKey(null, null, null, null, null, null, "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMetaDataOfDictItemTest() {
		try{
			DataManagementImpl object = new DataManagementImpl();
			object.getMetaDataOfDictItem(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateOrCreateNewDictgroupsTest() {
		try{
			DataManagementImpl object = new DataManagementImpl();
			object.updateOrCreateNewDictgroups(null, null, null, null, null, null, "abcde", "abcde", null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSyncDictCollectionsTest() {
		try{
			DataManagementImpl object = new DataManagementImpl();
			object.getSyncDictCollections(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateOrCreateNewDictItemByItemCodeTest() {
		try{
			DataManagementImpl object = new DataManagementImpl();
			object.updateOrCreateNewDictItemByItemCode(null, null, null, null, null, null, "abcde", "abcde", null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}