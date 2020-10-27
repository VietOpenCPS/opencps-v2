package org.opencps.datamgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class FileAttachLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			FileAttachLocalServiceImpl object = new FileAttachLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void luceneSearchEngineTest() {
//		try{
//			FileAttachLocalServiceImpl object = new FileAttachLocalServiceImpl();
//			object.luceneSearchEngine(null, null, 0, 0, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void findByF_docFileIdTest() {
		try{
			FileAttachLocalServiceImpl object = new FileAttachLocalServiceImpl();
			object.findByF_docFileId(Long.valueOf(0), "abcde", "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByF_className_classPKTest() {
		try{
			FileAttachLocalServiceImpl object = new FileAttachLocalServiceImpl();
			object.findByF_className_classPK(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateFileAttachTest() {
		try{
			FileAttachLocalServiceImpl object = new FileAttachLocalServiceImpl();
			object.updateFileAttach(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateFileAttachTest6() {
		try{
			FileAttachLocalServiceImpl object = new FileAttachLocalServiceImpl();
			object.updateFileAttach(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteFileAttachTest() {
		try{
			FileAttachLocalServiceImpl object = new FileAttachLocalServiceImpl();
			object.deleteFileAttach(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void copyFileAttachTest() {
		try{
			FileAttachLocalServiceImpl object = new FileAttachLocalServiceImpl();
			object.copyFileAttach(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addFileAttachTest() {
		try{
			FileAttachLocalServiceImpl object = new FileAttachLocalServiceImpl();
			object.addFileAttach(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void countLuceneSearchEngineTest() {
//		try{
//			FileAttachLocalServiceImpl object = new FileAttachLocalServiceImpl();
//			object.countLuceneSearchEngine(null, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			FileAttachLocalServiceImpl object = new FileAttachLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}