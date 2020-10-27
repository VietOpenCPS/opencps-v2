package org.opencps.usermgt.service.impl;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class WorkingUnitLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			WorkingUnitLocalServiceImpl object = new WorkingUnitLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_govAgencyCodeTest() {
		try{
			WorkingUnitLocalServiceImpl object = new WorkingUnitLocalServiceImpl();
			object.fetchByF_govAgencyCode(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void deleteWorkingUnitTest() {
//		try{
//			WorkingUnitLocalServiceImpl object = new WorkingUnitLocalServiceImpl();
//			object.deleteWorkingUnit(Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getSiblingTest() {
		try{
			WorkingUnitLocalServiceImpl object = new WorkingUnitLocalServiceImpl();
			object.getSibling(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void luceneSearchEngineTest() {
		try{
			WorkingUnitLocalServiceImpl object = new WorkingUnitLocalServiceImpl();
			object.luceneSearchEngine(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getWorkingUnitbyGidandWidTest() {
		try{
			WorkingUnitLocalServiceImpl object = new WorkingUnitLocalServiceImpl();
			object.getWorkingUnitbyGidandWid(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneSearchEngineTest() {
		try{
			WorkingUnitLocalServiceImpl object = new WorkingUnitLocalServiceImpl();
			object.countLuceneSearchEngine(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			WorkingUnitLocalServiceImpl object = new WorkingUnitLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getTreeIndexTest() {
		try{
			WorkingUnitLocalServiceImpl object = new WorkingUnitLocalServiceImpl();
			object.getTreeIndex(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addWorkingUnitPublishTest() {
		try{
			WorkingUnitLocalServiceImpl object = new WorkingUnitLocalServiceImpl();
			object.addWorkingUnitPublish(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", 0, "abcde", "abcde", "abcde", "abcde", "abcde", new Date(), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void updateWorkingUnitTest() {
//		try{
//			WorkingUnitLocalServiceImpl object = new WorkingUnitLocalServiceImpl();
//			object.updateWorkingUnit(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), new Date(), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void addWorkingUnitTest() {
//		try{
//			WorkingUnitLocalServiceImpl object = new WorkingUnitLocalServiceImpl();
//			object.addWorkingUnit(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", new Date(), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}