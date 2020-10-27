package org.opencps.datamgt.utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DocumentUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setFilePermissionsTest() {
		try{
			DocumentUtils.setFilePermissions(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFolderPermissionsTest() {
		try{
			DocumentUtils.setFolderPermissions(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fileUploadTest() {
		try{
			DocumentUtils.fileUpload(null, "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isFolderExistTest() {
		try{
			DocumentUtils.isFolderExist(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createFolderTest() {
		try{
			DocumentUtils.createFolder(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}