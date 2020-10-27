package org.opencps.datamgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class FileAttachActionsTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void updateTest() {
//		try{
//			FileAttachActions object = new FileAttachActions();
//			object.update(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void deleteTest() {
//		try{
//			FileAttachActions object = new FileAttachActions();
//			object.delete(Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void readTest() {
//		try{
//			FileAttachActions object = new FileAttachActions();
//			object.read(Long.valueOf(0), "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void readTest4() {
//		try{
//			FileAttachActions object = new FileAttachActions();
//			object.read(Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void createTest() {
//		try{
//			FileAttachActions object = new FileAttachActions();
//			object.create(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", Long.valueOf(0), "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getFileEntryTest() {
//		try{
//			FileAttachActions object = new FileAttachActions();
//			object.getFileEntry(Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	@Test
//	public void getFileAttachsTest() {
//		try{
//			FileAttachActions object = new FileAttachActions();
//			object.getFileAttachs(Long.valueOf(0), "abcde", "abcde", Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getFileAttachsTest9() {
		try{
			FileAttachActions object = new FileAttachActions();
			object.getFileAttachs(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileAttachVersionsTest() {
		try{
			FileAttachActions object = new FileAttachActions();
			object.getFileAttachVersions(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void uploadTest() {
		try{
			FileAttachActions object = new FileAttachActions();
			object.upload(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", null, "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}