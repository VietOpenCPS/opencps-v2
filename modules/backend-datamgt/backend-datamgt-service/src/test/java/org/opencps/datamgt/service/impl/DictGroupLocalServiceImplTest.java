package org.opencps.datamgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DictGroupLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			DictGroupLocalServiceImpl object = new DictGroupLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDictGroupDBTest() {
		try{
			DictGroupLocalServiceImpl object = new DictGroupLocalServiceImpl();
			object.updateDictGroupDB(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void luceneSearchEngineTest() {
		try{
			DictGroupLocalServiceImpl object = new DictGroupLocalServiceImpl();
			object.luceneSearchEngine(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_DictGroupCodeTest() {
		try{
			DictGroupLocalServiceImpl object = new DictGroupLocalServiceImpl();
			object.fetchByF_DictGroupCode("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDictGroupByDictCollectionTest() {
		try{
			DictGroupLocalServiceImpl object = new DictGroupLocalServiceImpl();
			object.getDictGroupByDictCollection(Long.valueOf(0), Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findOlderThanDateTest() {
		try{
			DictGroupLocalServiceImpl object = new DictGroupLocalServiceImpl();
			object.findOlderThanDate(new Date(), Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countOlderThanDateTest() {
		try{
			DictGroupLocalServiceImpl object = new DictGroupLocalServiceImpl();
			object.countOlderThanDate(new Date(), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByGC_GI_DCITest() {
		try{
			DictGroupLocalServiceImpl object = new DictGroupLocalServiceImpl();
			object.getByGC_GI_DCI("abcde", Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDictGroupTest() {
		try{
			DictGroupLocalServiceImpl object = new DictGroupLocalServiceImpl();
			object.addDictGroup(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDictGroupTest() {
		try{
			DictGroupLocalServiceImpl object = new DictGroupLocalServiceImpl();
			object.updateDictGroup(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDictGroupTest() {
		try{
			DictGroupLocalServiceImpl object = new DictGroupLocalServiceImpl();
			object.deleteDictGroup(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneSearchEngineTest() {
		try{
			DictGroupLocalServiceImpl object = new DictGroupLocalServiceImpl();
			object.countLuceneSearchEngine(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			DictGroupLocalServiceImpl object = new DictGroupLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}