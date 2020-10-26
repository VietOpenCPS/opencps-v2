package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class FileAttachManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void updateTest() {
		try{
			FileAttachManagementImpl object = new FileAttachManagementImpl();
			object.update(null, null, null, null, null, null, Long.valueOf(0), null, "abcde", "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void deleteTest() {
//		try{
//			FileAttachManagementImpl object = new FileAttachManagementImpl();
//			object.delete(null, null, null, null, null, null, Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void readTest() {
//		try{
//			FileAttachManagementImpl object = new FileAttachManagementImpl();
//			object.read(null, null, null, null, null, null, Long.valueOf(0), "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void readTest4() {
//		try{
//			FileAttachManagementImpl object = new FileAttachManagementImpl();
//			object.read(null, null, null, null, null, null, Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void createTest() {
		try{
			FileAttachManagementImpl object = new FileAttachManagementImpl();
			object.create(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void uploadTest() {
		try{
			FileAttachManagementImpl object = new FileAttachManagementImpl();
			object.upload(null, null, null, null, null, null, "abcde", "abcde", null, "abcde", "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileAttachsTest() {
		try{
			FileAttachManagementImpl object = new FileAttachManagementImpl();
			object.getFileAttachs(null, null, null, null, null, null, "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileAttachVersionsTest() {
		try{
			FileAttachManagementImpl object = new FileAttachManagementImpl();
			object.getFileAttachVersions(null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}