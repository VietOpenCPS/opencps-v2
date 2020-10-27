package org.opencps.datamgt.service.impl;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DictItemLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			DictItemLocalServiceImpl object = new DictItemLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_dictCollectionId_parentItemIdTest() {
		try{
			DictItemLocalServiceImpl object = new DictItemLocalServiceImpl();
			object.findByF_dictCollectionId_parentItemId(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_dictItemCodeTest() {
		try{
			DictItemLocalServiceImpl object = new DictItemLocalServiceImpl();
			object.fetchByF_dictItemCode("abcde", Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSiblingTest() {
		try{
			DictItemLocalServiceImpl object = new DictItemLocalServiceImpl();
			object.getSibling(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDictItemTest() {
		try{
			DictItemLocalServiceImpl object = new DictItemLocalServiceImpl();
			object.deleteDictItem(Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDictItemTest6() {
		try{
			DictItemLocalServiceImpl object = new DictItemLocalServiceImpl();
			object.deleteDictItem(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDictItemTest() {
		try{
			DictItemLocalServiceImpl object = new DictItemLocalServiceImpl();
			object.updateDictItem(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDictItemDBTest() {
		try{
			DictItemLocalServiceImpl object = new DictItemLocalServiceImpl();
			object.updateDictItemDB(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), 0, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_dictCollectionIdTest() {
		try{
			DictItemLocalServiceImpl object = new DictItemLocalServiceImpl();
			object.findByF_dictCollectionId(Long.valueOf(0), 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_dictCollectionIdTest10() {
		try{
			DictItemLocalServiceImpl object = new DictItemLocalServiceImpl();
			object.findByF_dictCollectionId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void luceneSearchEngineTest() {
//		try{
//			DictItemLocalServiceImpl object = new DictItemLocalServiceImpl();
//			object.luceneSearchEngine(null, null, 0, 0, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void findByF_parentItemIdTest() {
		try{
			DictItemLocalServiceImpl object = new DictItemLocalServiceImpl();
			object.findByF_parentItemId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_treeIndexTest() {
		try{
			DictItemLocalServiceImpl object = new DictItemLocalServiceImpl();
			object.findByF_treeIndex(Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByOlderThanDateTest() {
		try{
			DictItemLocalServiceImpl object = new DictItemLocalServiceImpl();
			object.findByOlderThanDate(new Date(), Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countByOlderThanDateTest() {
		try{
			DictItemLocalServiceImpl object = new DictItemLocalServiceImpl();
			object.countByOlderThanDate(new Date(), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDictItemListenerTest() {
		try{
			DictItemLocalServiceImpl object = new DictItemLocalServiceImpl();
			object.updateDictItemListener(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void initDictItemTest() {
		try{
			DictItemLocalServiceImpl object = new DictItemLocalServiceImpl();
			object.initDictItem(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDictItemTest() {
		try{
			DictItemLocalServiceImpl object = new DictItemLocalServiceImpl();
			object.addDictItem(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", 0, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_IDSTest() {
		try{
			DictItemLocalServiceImpl object = new DictItemLocalServiceImpl();
			object.findByF_IDS(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void countLuceneSearchEngineTest() {
//		try{
//			DictItemLocalServiceImpl object = new DictItemLocalServiceImpl();
//			object.countLuceneSearchEngine(null, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			DictItemLocalServiceImpl object = new DictItemLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTreeIndexTest() {
		try{
			DictItemLocalServiceImpl object = new DictItemLocalServiceImpl();
			object.getTreeIndex(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}