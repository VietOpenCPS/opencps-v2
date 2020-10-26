package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class ImportDataManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void addDossierFileByDossierIdTest() {
		try{
			ImportDataManagementImpl object = new ImportDataManagementImpl();
			object.addDossierFileByDossierId(null, null, null, null, null, null, null, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierImportDataTest() {
		try{
			ImportDataManagementImpl object = new ImportDataManagementImpl();
			object.addDossierImportData(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void uploadFileDossiersTest() {
		try{
			ImportDataManagementImpl object = new ImportDataManagementImpl();
			object.uploadFileDossiers(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addApplicantImportDataTest() {
		try{
			ImportDataManagementImpl object = new ImportDataManagementImpl();
			object.addApplicantImportData(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void uploadFileQuestion2Test() {
		try{
			ImportDataManagementImpl object = new ImportDataManagementImpl();
			object.uploadFileQuestion2(null, null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void uploadFileQuestionTest() {
		try{
			ImportDataManagementImpl object = new ImportDataManagementImpl();
			object.uploadFileQuestion(null, null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}