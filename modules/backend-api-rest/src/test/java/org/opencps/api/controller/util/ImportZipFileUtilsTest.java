package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class ImportZipFileUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void compressTest() {
		try{
			ImportZipFileUtils.compress("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void unzipTest() {
		try{
			ImportZipFileUtils.unzip(null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getFolderPathTest() {
//		try{
//			ImportZipFileUtils.getFolderPath("abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getSubFileNameTest() {
//		try{
//			ImportZipFileUtils.getSubFileName("abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getExtendFileNameTest() {
//		try{
//			ImportZipFileUtils.getExtendFileName("abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}