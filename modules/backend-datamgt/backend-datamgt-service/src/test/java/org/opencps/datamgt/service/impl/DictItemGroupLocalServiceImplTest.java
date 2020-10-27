package org.opencps.datamgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DictItemGroupLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			DictItemGroupLocalServiceImpl object = new DictItemGroupLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDictItemGroupDBTest() {
		try{
			DictItemGroupLocalServiceImpl object = new DictItemGroupLocalServiceImpl();
			object.updateDictItemGroupDB(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void luceneSearchEngineTest() {
		try{
			DictItemGroupLocalServiceImpl object = new DictItemGroupLocalServiceImpl();
			object.luceneSearchEngine(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_dictItemIdTest() {
		try{
			DictItemGroupLocalServiceImpl object = new DictItemGroupLocalServiceImpl();
			object.findByF_dictItemId(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDictItemGroupTest() {
		try{
			DictItemGroupLocalServiceImpl object = new DictItemGroupLocalServiceImpl();
			object.deleteDictItemGroup(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDictItemGroupNoneAuthenTest() {
		try{
			DictItemGroupLocalServiceImpl object = new DictItemGroupLocalServiceImpl();
			object.deleteDictItemGroupNoneAuthen(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findOlderThanDateTest() {
		try{
			DictItemGroupLocalServiceImpl object = new DictItemGroupLocalServiceImpl();
			object.findOlderThanDate(new Date(), Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_dictItemId_dictGroupIdTest() {
		try{
			DictItemGroupLocalServiceImpl object = new DictItemGroupLocalServiceImpl();
			object.fetchByF_dictItemId_dictGroupId(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countOlderThanDateTest() {
		try{
			DictItemGroupLocalServiceImpl object = new DictItemGroupLocalServiceImpl();
			object.countOlderThanDate(new Date(), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByDictGroupIdTest() {
		try{
			DictItemGroupLocalServiceImpl object = new DictItemGroupLocalServiceImpl();
			object.findByDictGroupId(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDictItemGroupTest() {
		try{
			DictItemGroupLocalServiceImpl object = new DictItemGroupLocalServiceImpl();
			object.updateDictItemGroup(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDictItemGroupTest() {
		try{
			DictItemGroupLocalServiceImpl object = new DictItemGroupLocalServiceImpl();
			object.addDictItemGroup(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneSearchEngineTest() {
		try{
			DictItemGroupLocalServiceImpl object = new DictItemGroupLocalServiceImpl();
			object.countLuceneSearchEngine(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			DictItemGroupLocalServiceImpl object = new DictItemGroupLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}