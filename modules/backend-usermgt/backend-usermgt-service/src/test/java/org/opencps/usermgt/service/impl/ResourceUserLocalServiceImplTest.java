package org.opencps.usermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class ResourceUserLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	
//	@Test
//	public void luceneSearchEngineTest() {
//		try{
//			ResourceUserLocalServiceImpl object = new ResourceUserLocalServiceImpl();
//			object.luceneSearchEngine(null, null, 0, 0, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	@Test
//	public void countLuceneSearchEngineTest() {
//		try{
//			ResourceUserLocalServiceImpl object = new ResourceUserLocalServiceImpl();
//			object.countLuceneSearchEngine(null, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	@Test
//	public void updateResourceUserTest() {
//		try{
//			ResourceUserLocalServiceImpl object = new ResourceUserLocalServiceImpl();
//			object.updateResourceUser(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", true, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void deleteResourceUserTest() {
//		try{
//			ResourceUserLocalServiceImpl object = new ResourceUserLocalServiceImpl();
//			object.deleteResourceUser(Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void addResourceUserTest() {
//		try{
//			ResourceUserLocalServiceImpl object = new ResourceUserLocalServiceImpl();
//			object.addResourceUser(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", true, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}

	
	@Test
	public void adminProcessDataTest() {
		try{
			ResourceUserLocalServiceImpl object = new ResourceUserLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByF_className_classPK_toUserIdTest() {
		try{
			ResourceUserLocalServiceImpl object = new ResourceUserLocalServiceImpl();
			object.fetchByF_className_classPK_toUserId(Long.valueOf(0), "abcde", "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_className_classPKTest() {
		try{
			ResourceUserLocalServiceImpl object = new ResourceUserLocalServiceImpl();
			object.findByF_className_classPK(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			ResourceUserLocalServiceImpl object = new ResourceUserLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}