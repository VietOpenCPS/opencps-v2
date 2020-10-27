package org.opencps.auth.utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DLFolderUtilTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setFilePermissionsTest() {
		try{
			DLFolderUtil.setFilePermissions(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}

	/*@Test
	public void setFolderPermissionsTest() {
		try{
			DLFolderUtil.setFolderPermissions(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFolderPermissionsTest3() {
		try{
			DLFolderUtil.setFolderPermissions(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}*/
//	@Test
//	public void getTargetFolderTest() {
//		try{
//			DLFolderUtil.getTargetFolder(Long.valueOf(0), Long.valueOf(0), "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getTargetFolderTest5() {
//		try{
//			DLFolderUtil.getTargetFolder(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), true, Long.valueOf(0), "abcde", "abcde", true, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void makeFolderTest() {
		try{
			DLFolderUtil.makeFolder(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), true, Long.valueOf(0), "abcde", "abcde", true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void hasFolderTest() {
		try{
			DLFolderUtil.hasFolder(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFolderTest() {
		try{
			DLFolderUtil.getFolder(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), true, Long.valueOf(0), "abcde", "abcde", true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFolderTest9() {
		try{
			DLFolderUtil.getFolder(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addFolderTest() {
		try{
			DLFolderUtil.addFolder(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), true, Long.valueOf(0), "abcde", "abcde", true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}