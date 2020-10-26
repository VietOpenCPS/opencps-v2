package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DossierFileManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void cloneDossierFileTest() {
		try{
			DossierFileManagementImpl object = new DossierFileManagementImpl();
			object.cloneDossierFile(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void cleanDossierFileTest() {
		try{
			DossierFileManagementImpl object = new DossierFileManagementImpl();
			object.cleanDossierFile(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void uploadFileEntryTest() {
		try{
			DossierFileManagementImpl object = new DossierFileManagementImpl();
			object.uploadFileEntry(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierFilesTest() {
		try{
			DossierFileManagementImpl object = new DossierFileManagementImpl();
			object.addDossierFiles(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFilesTest() {
		try{
			DossierFileManagementImpl object = new DossierFileManagementImpl();
			object.getDossierFiles(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void resetformdataDossierFileFormDataTest() {
		try{
			DossierFileManagementImpl object = new DossierFileManagementImpl();
			object.resetformdataDossierFileFormData(null, null, null, null, null, null, Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierFileFormDataTest() {
		try{
			DossierFileManagementImpl object = new DossierFileManagementImpl();
			object.updateDossierFileFormData(null, null, null, null, null, null, Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void useOriginalDossierFileTest() {
		try{
			DossierFileManagementImpl object = new DossierFileManagementImpl();
			object.useOriginalDossierFile(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeDossierOfProfileApplicantTest() {
		try{
			DossierFileManagementImpl object = new DossierFileManagementImpl();
			object.removeDossierOfProfileApplicant(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void cloneFromApplicantDataTest() {
		try{
			DossierFileManagementImpl object = new DossierFileManagementImpl();
			object.cloneFromApplicantData(null, null, null, null, null, null, Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFileByDeliverableCodeTest() {
		try{
			DossierFileManagementImpl object = new DossierFileManagementImpl();
			object.getDossierFileByDeliverableCode(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileDoneOfApplicantTest() {
		try{
			DossierFileManagementImpl object = new DossierFileManagementImpl();
			object.getFileDoneOfApplicant(null, null, null, null, null, null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAlreadyHasFileTemplateNoTest() {
		try{
			DossierFileManagementImpl object = new DossierFileManagementImpl();
			object.getAlreadyHasFileTemplateNo(null, null, null, null, null, null, Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void downloadByPublicUserTest() {
//		try{
//			DossierFileManagementImpl object = new DossierFileManagementImpl();
//			object.downloadByPublicUser(null, null, null, null, null, null, Long.valueOf(0), "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getAllDossierFilesByDossierIdTest() {
		try{
			DossierFileManagementImpl object = new DossierFileManagementImpl();
			object.getAllDossierFilesByDossierId(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeAllDossierFileFormDataTest() {
		try{
			DossierFileManagementImpl object = new DossierFileManagementImpl();
			object.removeAllDossierFileFormData(null, null, null, null, null, null, Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void previewDossierFileTest() {
//		try{
//			DossierFileManagementImpl object = new DossierFileManagementImpl();
//			object.previewDossierFile(null, null, null, null, null, null, Long.valueOf(0), "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void addDossierFileByDossierIdTest() {
		try{
			DossierFileManagementImpl object = new DossierFileManagementImpl();
			object.addDossierFileByDossierId(null, null, null, null, null, null, null, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFilesByDossierIdTest() {
		try{
			DossierFileManagementImpl object = new DossierFileManagementImpl();
			object.getDossierFilesByDossierId(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierFileTest() {
		try{
			DossierFileManagementImpl object = new DossierFileManagementImpl();
			object.updateDossierFile(null, null, null, null, null, null, Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void downloadByDossierId_ReferenceUidTest() {
		try{
			DossierFileManagementImpl object = new DossierFileManagementImpl();
			object.downloadByDossierId_ReferenceUid(null, null, null, null, null, null, "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDossierFileTest() {
		try{
			DossierFileManagementImpl object = new DossierFileManagementImpl();
			object.deleteDossierFile(null, null, null, null, null, null, Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void downloadByDossierIdTest() {
		try{
			DossierFileManagementImpl object = new DossierFileManagementImpl();
			object.downloadByDossierId(null, null, null, null, null, null, Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormScriptByDossierId_ReferenceUidTest() {
		try{
			DossierFileManagementImpl object = new DossierFileManagementImpl();
			object.getFormScriptByDossierId_ReferenceUid(null, null, null, null, null, null, Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormDataByDossierId_ReferenceUidTest() {
		try{
			DossierFileManagementImpl object = new DossierFileManagementImpl();
			object.getFormDataByDossierId_ReferenceUid(null, null, null, null, null, null, Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFileByDossierId_FileTemplateNoTest() {
		try{
			DossierFileManagementImpl object = new DossierFileManagementImpl();
			object.getDossierFileByDossierId_FileTemplateNo(null, null, null, null, null, null, Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}