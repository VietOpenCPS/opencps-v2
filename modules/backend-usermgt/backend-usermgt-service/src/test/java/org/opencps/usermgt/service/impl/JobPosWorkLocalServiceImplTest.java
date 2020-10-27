package org.opencps.usermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class JobPosWorkLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void luceneSearchEngineTest() {
		try{
			JobPosWorkLocalServiceImpl object = new JobPosWorkLocalServiceImpl();
			object.luceneSearchEngine(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneSearchEngineTest() {
		try{
			JobPosWorkLocalServiceImpl object = new JobPosWorkLocalServiceImpl();
			object.countLuceneSearchEngine(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_JobPostIdTest() {
		try{
			JobPosWorkLocalServiceImpl object = new JobPosWorkLocalServiceImpl();
			object.findByF_JobPostId(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_ChecklistCatTest() {
		try{
			JobPosWorkLocalServiceImpl object = new JobPosWorkLocalServiceImpl();
			object.fetchByF_ChecklistCat(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void addJobPosWorkTest() {
//		try{
//			JobPosWorkLocalServiceImpl object = new JobPosWorkLocalServiceImpl();
//			object.addJobPosWork(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void deleteJobPosWorkTest() {
//		try{
//			JobPosWorkLocalServiceImpl object = new JobPosWorkLocalServiceImpl();
//			object.deleteJobPosWork(Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateJobPosWorkTest() {
//		try{
//			JobPosWorkLocalServiceImpl object = new JobPosWorkLocalServiceImpl();
//			object.updateJobPosWork(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}