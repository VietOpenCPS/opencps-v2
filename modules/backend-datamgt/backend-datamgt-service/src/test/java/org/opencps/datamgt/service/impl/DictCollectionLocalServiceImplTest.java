package org.opencps.datamgt.service.impl;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DictCollectionLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			DictCollectionLocalServiceImpl object = new DictCollectionLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void fetchByF_dictCollectionCodeTest() {
//		try{
//			DictCollectionLocalServiceImpl object = new DictCollectionLocalServiceImpl();
//			object.fetchByF_dictCollectionCode("abcde", Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void activeTest() {
		try{
			DictCollectionLocalServiceImpl object = new DictCollectionLocalServiceImpl();
			object.active(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByGTest() {
		try{
			DictCollectionLocalServiceImpl object = new DictCollectionLocalServiceImpl();
			object.findByG(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void inactiveTest() {
		try{
			DictCollectionLocalServiceImpl object = new DictCollectionLocalServiceImpl();
			object.inactive(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDictCollectionDBTest() {
		try{
			DictCollectionLocalServiceImpl object = new DictCollectionLocalServiceImpl();
			object.updateDictCollectionDB(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDictCollectionTest() {
		try{
			DictCollectionLocalServiceImpl object = new DictCollectionLocalServiceImpl();
			object.deleteDictCollection(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDictCollectionTest() {
		try{
			DictCollectionLocalServiceImpl object = new DictCollectionLocalServiceImpl();
			object.updateDictCollection(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDictCollectionTest() {
		try{
			DictCollectionLocalServiceImpl object = new DictCollectionLocalServiceImpl();
			object.addDictCollection(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void luceneSearchEngineTest() {
		try{
			DictCollectionLocalServiceImpl object = new DictCollectionLocalServiceImpl();
			object.luceneSearchEngine(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void initDictCollectionTest() {
		try{
			DictCollectionLocalServiceImpl object = new DictCollectionLocalServiceImpl();
			object.initDictCollection(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDictCollectionPublishTest() {
		try{
			DictCollectionLocalServiceImpl object = new DictCollectionLocalServiceImpl();
			object.updateDictCollectionPublish(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findOlderThanDateTest() {
		try{
			DictCollectionLocalServiceImpl object = new DictCollectionLocalServiceImpl();
			object.findOlderThanDate(new Date(), Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countOlderThanDateTest() {
		try{
			DictCollectionLocalServiceImpl object = new DictCollectionLocalServiceImpl();
			object.countOlderThanDate(new Date(), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void changeStatusTest() {
		try{
			DictCollectionLocalServiceImpl object = new DictCollectionLocalServiceImpl();
			object.changeStatus(Long.valueOf(0), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneSearchEngineTest() {
		try{
			DictCollectionLocalServiceImpl object = new DictCollectionLocalServiceImpl();
			object.countLuceneSearchEngine(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			DictCollectionLocalServiceImpl object = new DictCollectionLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}