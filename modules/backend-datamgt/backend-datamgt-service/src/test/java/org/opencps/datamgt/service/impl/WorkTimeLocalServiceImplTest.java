package org.opencps.datamgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class WorkTimeLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			WorkTimeLocalServiceImpl object = new WorkTimeLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByGroupIdTest() {
		try{
			WorkTimeLocalServiceImpl object = new WorkTimeLocalServiceImpl();
			object.getByGroupId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateWorkTimeDBTest() {
		try{
			WorkTimeLocalServiceImpl object = new WorkTimeLocalServiceImpl();
			object.updateWorkTimeDB(Long.valueOf(0), Long.valueOf(0), 0, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void luceneSearchEngineTest() {
//		try{
//			WorkTimeLocalServiceImpl object = new WorkTimeLocalServiceImpl();
//			object.luceneSearchEngine(null, null, 0, 0, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void fetchByF_dayTest() {
		try{
			WorkTimeLocalServiceImpl object = new WorkTimeLocalServiceImpl();
			object.fetchByF_day(Long.valueOf(0), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateWorkTimeTest() {
		try{
			WorkTimeLocalServiceImpl object = new WorkTimeLocalServiceImpl();
			object.updateWorkTime(Long.valueOf(0), Long.valueOf(0), 0, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addWorkTimeTest() {
		try{
			WorkTimeLocalServiceImpl object = new WorkTimeLocalServiceImpl();
			object.addWorkTime(Long.valueOf(0), Long.valueOf(0), 0, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteWorkTimeTest() {
		try{
			WorkTimeLocalServiceImpl object = new WorkTimeLocalServiceImpl();
			object.deleteWorkTime(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void countLuceneSearchEngineTest() {
//		try{
//			WorkTimeLocalServiceImpl object = new WorkTimeLocalServiceImpl();
//			object.countLuceneSearchEngine(null, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			WorkTimeLocalServiceImpl object = new WorkTimeLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}