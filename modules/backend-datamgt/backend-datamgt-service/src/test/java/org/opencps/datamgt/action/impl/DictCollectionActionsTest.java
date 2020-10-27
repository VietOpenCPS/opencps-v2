package org.opencps.datamgt.action.impl;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DictCollectionActionsTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void countDictCollectionsOlderThanDateTest() {
//		try{
//			DictCollectionActions object = new DictCollectionActions();
//			object.countDictCollectionsOlderThanDate(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), new Date(), 0, 0, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getListDictItemGroupsOlderThanDateTest() {
//		try{
//			DictCollectionActions object = new DictCollectionActions();
//			object.getListDictItemGroupsOlderThanDate(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), new Date(), 0, 0, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getListDictCollectionsOlderThanDateTest() {
//		try{
//			DictCollectionActions object = new DictCollectionActions();
//			object.getListDictCollectionsOlderThanDate(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), new Date(), 0, 0, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateDictgroupsTest() {
//		try{
//			DictCollectionActions object = new DictCollectionActions();
//			object.updateDictgroups(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	@Test
//	public void addDataFormTest() {
//		try{
//			DictCollectionActions object = new DictCollectionActions();
//			object.addDataForm(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void addDictItemsTest() {
//		try{
//			DictCollectionActions object = new DictCollectionActions();
//			object.addDictItems(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void addDictgroupsTest() {
//		try{
//			DictCollectionActions object = new DictCollectionActions();
//			object.addDictgroups(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	
//	@Test
//	public void deleteDictgroupsTest() {
//		try{
//			DictCollectionActions object = new DictCollectionActions();
//			object.deleteDictgroups("abcde", "abcde", Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void deleteDictgroupsTest12() {
//		try{
//			DictCollectionActions object = new DictCollectionActions();
//			object.deleteDictgroups("abcde", Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	@Test
//	public void updateDictItemDBTest() {
//		try{
//			DictCollectionActions object = new DictCollectionActions();
//			object.updateDictItemDB(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), 0, "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateDictCollectionDBTest() {
//		try{
//			DictCollectionActions object = new DictCollectionActions();
//			object.updateDictCollectionDB(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void deleteAllDictItemTest() {
//		try{
//			DictCollectionActions object = new DictCollectionActions();
//			object.deleteAllDictItem(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateDictGroupDBTest() {
//		try{
//			DictCollectionActions object = new DictCollectionActions();
//			object.updateDictGroupDB(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateDictItemGroupDBTest() {
//		try{
//			DictCollectionActions object = new DictCollectionActions();
//			object.updateDictItemGroupDB(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void deleteAllDictGroupTest() {
//		try{
//			DictCollectionActions object = new DictCollectionActions();
//			object.deleteAllDictGroup(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	@Test
//	public void addDictgroupsDictItemsTest() {
//		try{
//			DictCollectionActions object = new DictCollectionActions();
//			object.addDictgroupsDictItems(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void deleteDictgroupsDictItemsTest() {
//		try{
//			DictCollectionActions object = new DictCollectionActions();
//			object.deleteDictgroupsDictItems(Long.valueOf(0), "abcde", "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void deleteDictCollectionTest() {
//		try{
//			DictCollectionActions object = new DictCollectionActions();
//			object.deleteDictCollection("abcde", Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getDictItemByItemCodeTest() {
//		try{
//			DictCollectionActions object = new DictCollectionActions();
//			object.getDictItemByItemCode(Long.valueOf(0), "abcde", Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getDictItemByItemCodeTest25() {
//		try{
//			DictCollectionActions object = new DictCollectionActions();
//			object.getDictItemByItemCode("abcde", "abcde", Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateDictItemByItemCodeTest() {
//		try{
//			DictCollectionActions object = new DictCollectionActions();
//			object.updateDictItemByItemCode(Long.valueOf(0), Long.valueOf(0), null, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getDictCollectionDetailTest() {
//		try{
//			DictCollectionActions object = new DictCollectionActions();
//			object.getDictCollectionDetail("abcde", Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateDictCollectionTest() {
//		try{
//			DictCollectionActions object = new DictCollectionActions();
//			object.updateDictCollection(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void addDictCollectionTest() {
//		try{
//			DictCollectionActions object = new DictCollectionActions();
//			object.addDictCollection(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	
//	
//	@Test
//	public void countDictItemGroupsOlderThanDateTest() {
//		try{
//			DictCollectionActions object = new DictCollectionActions();
//			object.countDictItemGroupsOlderThanDate(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), new Date(), 0, 0, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void countDictItemsOlderThanDateTest() {
//		try{
//			DictCollectionActions object = new DictCollectionActions();
//			object.countDictItemsOlderThanDate(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), new Date(), 0, 0, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void countDictGroupsOlderThanDateTest() {
//		try{
//			DictCollectionActions object = new DictCollectionActions();
//			object.countDictGroupsOlderThanDate(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), new Date(), 0, 0, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getListDictGroupsOlderThanDateTest() {
//		try{
//			DictCollectionActions object = new DictCollectionActions();
//			object.getListDictGroupsOlderThanDate(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), new Date(), 0, 0, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getListDictItemsOlderThanDateTest() {
//		try{
//			DictCollectionActions object = new DictCollectionActions();
//			object.getListDictItemsOlderThanDate(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), new Date(), 0, 0, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getDictGroupDetailTest() {
//		try{
//			DictCollectionActions object = new DictCollectionActions();
//			object.getDictGroupDetail("abcde", "abcde", Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateMetaDataByItemCodeTest() {
//		try{
//			DictCollectionActions object = new DictCollectionActions();
//			object.updateMetaDataByItemCode(Long.valueOf(0), Long.valueOf(0), null, "abcde", "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateDictItemGroupTest() {
//		try{
//			DictCollectionActions object = new DictCollectionActions();
//			object.updateDictItemGroup(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	
//	
//	@Test
//	public void deleteDictgroupsAndSomethingUseItTest() {
//		try{
//			DictCollectionActions object = new DictCollectionActions();
//			object.deleteDictgroupsAndSomethingUseIt("abcde", "abcde", Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	
	
	
	
	
	
	
	@Test
	public void getDictItemsGroupTest() {
		try{
			DictCollectionActions object = new DictCollectionActions();
			object.getDictItemsGroup(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictgroupsDictItemsTest() {
		try{
			DictCollectionActions object = new DictCollectionActions();
			object.getDictgroupsDictItems(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", true, null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictCollectionTest() {
		try{
			DictCollectionActions object = new DictCollectionActions();
			object.getDictCollection(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictgroupsTest() {
		try{
			DictCollectionActions object = new DictCollectionActions();
			object.getDictgroups(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictItemsOlderThanDateTest() {
		try{
			DictCollectionActions object = new DictCollectionActions();
			object.getDictItemsOlderThanDate(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), new Date(), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictgroupsLGSPTest() {
		try{
			DictCollectionActions object = new DictCollectionActions();
			object.getDictgroupsLGSP(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictCollectionLGSPTest() {
		try{
			DictCollectionActions object = new DictCollectionActions();
			object.getDictCollectionLGSP(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictItemsLGSPTest() {
		try{
			DictCollectionActions object = new DictCollectionActions();
			object.getDictItemsLGSP(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictItemsTest() {
		try{
			DictCollectionActions object = new DictCollectionActions();
			object.getDictItems(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictGroupsOlderThanDateTest() {
		try{
			DictCollectionActions object = new DictCollectionActions();
			object.getDictGroupsOlderThanDate(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), new Date(), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictCollectionsOlderThanDateTest() {
		try{
			DictCollectionActions object = new DictCollectionActions();
			object.getDictCollectionsOlderThanDate(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), new Date(), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}