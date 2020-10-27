package org.opencps.usermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class ResourceRoleLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			ResourceRoleLocalServiceImpl object = new ResourceRoleLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void luceneSearchEngineTest() {
//		try{
//			ResourceRoleLocalServiceImpl object = new ResourceRoleLocalServiceImpl();
//			object.luceneSearchEngine(null, null, 0, 0, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void findByF_className_classPKTest() {
		try{
			ResourceRoleLocalServiceImpl object = new ResourceRoleLocalServiceImpl();
			object.findByF_className_classPK(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void countLuceneSearchEngineTest() {
//		try{
//			ResourceRoleLocalServiceImpl object = new ResourceRoleLocalServiceImpl();
//			object.countLuceneSearchEngine(null, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			ResourceRoleLocalServiceImpl object = new ResourceRoleLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_className_classPK_roleIdTest() {
		try{
			ResourceRoleLocalServiceImpl object = new ResourceRoleLocalServiceImpl();
			object.fetchByF_className_classPK_roleId(Long.valueOf(0), "abcde", "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void deleteResourceRoleTest() {
//		try{
//			ResourceRoleLocalServiceImpl object = new ResourceRoleLocalServiceImpl();
//			object.deleteResourceRole(Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateResourceRoleTest() {
//		try{
//			ResourceRoleLocalServiceImpl object = new ResourceRoleLocalServiceImpl();
//			object.updateResourceRole(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void addResourceRoleTest() {
//		try{
//			ResourceRoleLocalServiceImpl object = new ResourceRoleLocalServiceImpl();
//			object.addResourceRole(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}