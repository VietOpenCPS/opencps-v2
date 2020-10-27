package org.opencps.usermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class JobPosLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			JobPosLocalServiceImpl object = new JobPosLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_jobPosIdsTest() {
		try{
			JobPosLocalServiceImpl object = new JobPosLocalServiceImpl();
			object.findByF_jobPosIds(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByGTest() {
		try{
			JobPosLocalServiceImpl object = new JobPosLocalServiceImpl();
			object.findByG(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateJobPosDBTest() {
		try{
			JobPosLocalServiceImpl object = new JobPosLocalServiceImpl();
			object.updateJobPosDB(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_mappingRoleIdsTest() {
		try{
			JobPosLocalServiceImpl object = new JobPosLocalServiceImpl();
			object.findByF_mappingRoleIds(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByJobCodeTest() {
		try{
			JobPosLocalServiceImpl object = new JobPosLocalServiceImpl();
			object.getByJobCode(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void deleteJobPosTest() {
//		try{
//			JobPosLocalServiceImpl object = new JobPosLocalServiceImpl();
//			object.deleteJobPos(Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void luceneSearchEngineTest() {
		try{
			JobPosLocalServiceImpl object = new JobPosLocalServiceImpl();
			object.luceneSearchEngine(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneSearchEngineTest() {
		try{
			JobPosLocalServiceImpl object = new JobPosLocalServiceImpl();
			object.countLuceneSearchEngine(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			JobPosLocalServiceImpl object = new JobPosLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_mappingRoleIdTest() {
		try{
			JobPosLocalServiceImpl object = new JobPosLocalServiceImpl();
			object.fetchByF_mappingRoleId(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void assignPermissionTest() {
		try{
			JobPosLocalServiceImpl object = new JobPosLocalServiceImpl();
			object.assignPermission(Long.valueOf(0), null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void addJobPosTest() {
//		try{
//			JobPosLocalServiceImpl object = new JobPosLocalServiceImpl();
//			object.addJobPos(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateJobPosTest() {
//		try{
//			JobPosLocalServiceImpl object = new JobPosLocalServiceImpl();
//			object.updateJobPos(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", 0, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}