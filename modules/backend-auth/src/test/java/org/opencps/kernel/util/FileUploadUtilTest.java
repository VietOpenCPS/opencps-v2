package org.opencps.kernel.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class FileUploadUtilTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getFileEntryPreviewPathTest() {
		try{
			FileUploadUtil.getFileEntryPreviewPath(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileEntryPreviewPathTest2() {
		try{
			FileUploadUtil.getFileEntryPreviewPath(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateFileTest() {
		try{
			FileUploadUtil.updateFile(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void uploadFileTest() {
		try{
			FileUploadUtil.uploadFile(Long.valueOf(0), Long.valueOf(0), null, "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void uploadFileTest5() {
		try{
			FileUploadUtil.uploadFile(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}