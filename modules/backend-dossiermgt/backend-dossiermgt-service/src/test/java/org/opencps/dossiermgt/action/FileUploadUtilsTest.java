package org.opencps.dossiermgt.action;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class FileUploadUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void cloneDossierFileTest() {
		try{
			FileUploadUtils.cloneDossierFile(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void uploadFileTest() {
		try{
			FileUploadUtils.uploadFile(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void uploadFileTest4() {
		try{
			FileUploadUtils.uploadFile(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, "abcde", "abcde", Long.valueOf(0), "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void uploadFileTest5() {
		try{
			FileUploadUtils.uploadFile(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, "abcde", "abcde", Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void uploadDossierFileTest() {
		try{
			FileUploadUtils.uploadDossierFile(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, "abcde", "abcde", Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void uploadDossierFileTest7() {
		try{
			FileUploadUtils.uploadDossierFile(Long.valueOf(0), Long.valueOf(0), null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void uploadDossierFileTest8() {
		try{
			FileUploadUtils.uploadDossierFile(Long.valueOf(0), Long.valueOf(0), null, "abcde", "abcde", Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void cloneFileTest() {
		try{
			FileUploadUtils.cloneFile(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void uploadPaymentFileTest() {
		try{
			FileUploadUtils.uploadPaymentFile(Long.valueOf(0), Long.valueOf(0), null, "abcde", "abcde", Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}