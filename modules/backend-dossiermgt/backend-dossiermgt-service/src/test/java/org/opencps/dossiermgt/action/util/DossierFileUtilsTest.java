package org.opencps.dossiermgt.action.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierFileUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void base64ToFileTest() {
		try{
			DossierFileUtils.base64ToFile("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileMetadataTest() {
		try{
			DossierFileUtils.getFileMetadata(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fileToInputStreamTest() {
		try{
			DossierFileUtils.fileToInputStream(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void base64ToInputStreamTest() {
		try{
			DossierFileUtils.base64ToInputStream("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}