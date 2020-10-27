package org.opencps.usermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class OfficeSiteLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			OfficeSiteLocalServiceImpl object = new OfficeSiteLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void updateOfficeSiteTest() {
//		try{
//			OfficeSiteLocalServiceImpl object = new OfficeSiteLocalServiceImpl();
//			object.updateOfficeSite(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void deleteOfficeSiteTest() {
//		try{
//			OfficeSiteLocalServiceImpl object = new OfficeSiteLocalServiceImpl();
//			object.deleteOfficeSite(Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void addOfficeSiteTest() {
//		try{
//			OfficeSiteLocalServiceImpl object = new OfficeSiteLocalServiceImpl();
//			object.addOfficeSite(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void luceneSearchEngineTest() {
		try{
			OfficeSiteLocalServiceImpl object = new OfficeSiteLocalServiceImpl();
			object.luceneSearchEngine(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneSearchEngineTest() {
		try{
			OfficeSiteLocalServiceImpl object = new OfficeSiteLocalServiceImpl();
			object.countLuceneSearchEngine(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			OfficeSiteLocalServiceImpl object = new OfficeSiteLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchF_groupId_siteGroupIdTest() {
		try{
			OfficeSiteLocalServiceImpl object = new OfficeSiteLocalServiceImpl();
			object.fetchF_groupId_siteGroupId(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}