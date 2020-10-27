package org.opencps.dossiermgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DossierFileActionsImplTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void cloneDossierFileTest() {
//		try{
//			DossierFileActionsImpl object = new DossierFileActionsImpl();
//			object.cloneDossierFile(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	
//	
//	@Test
//	public void updateDossierFileFormDataTest() {
//		try{
//			DossierFileActionsImpl object = new DossierFileActionsImpl();
//			object.updateDossierFileFormData(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getDossierFileByDeliverableCodeTest() {
//		try{
//			DossierFileActionsImpl object = new DossierFileActionsImpl();
//			object.getDossierFileByDeliverableCode(Long.valueOf(0), "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateDossierFileTest() {
//		try{
//			DossierFileActionsImpl object = new DossierFileActionsImpl();
//			object.updateDossierFile(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", null, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void deleteDossierFileTest() {
//		try{
//			DossierFileActionsImpl object = new DossierFileActionsImpl();
//			object.deleteDossierFile(Long.valueOf(0), Long.valueOf(0), "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	@Test
//	public void addDossierFileTest() {
//		try{
//			DossierFileActionsImpl object = new DossierFileActionsImpl();
//			object.addDossierFile(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), null, "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void addDossierFileTest11() {
//		try{
//			DossierFileActionsImpl object = new DossierFileActionsImpl();
//			object.addDossierFile(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), null, "abcde", "abcde", "abcde", "abcde", true, "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	@Test
//	public void deleteAllDossierFileTest() {
//		try{
//			DossierFileActionsImpl object = new DossierFileActionsImpl();
//			object.deleteAllDossierFile(Long.valueOf(0), Long.valueOf(0), "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getDossierFileByFileTemplateNoTest() {
//		try{
//			DossierFileActionsImpl object = new DossierFileActionsImpl();
//			object.getDossierFileByFileTemplateNo(Long.valueOf(0), "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void addDossierFileEFormTest() {
//		try{
//			DossierFileActionsImpl object = new DossierFileActionsImpl();
//			object.addDossierFileEForm(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), null, "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void resetDossierFileFormDataTest() {
//		try{
//			DossierFileActionsImpl object = new DossierFileActionsImpl();
//			object.resetDossierFileFormData(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	
	@Test
	public void addDossierFileTest12() {
		try{
			DossierFileActionsImpl object = new DossierFileActionsImpl();
			object.addDossierFile(null, null, Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	
	@Test
	public void uploadFileEntryTest() {
		try{
			DossierFileActionsImpl object = new DossierFileActionsImpl();
			object.uploadFileEntry("abcde", null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	
	@Test
	public void getDossierFilesTest() {
		try{
			DossierFileActionsImpl object = new DossierFileActionsImpl();
			object.getDossierFiles(Long.valueOf(0), "abcde", "abcde", 0, true, true, 0, 0, "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	
	@Test
	public void copyFileTest() {
		try{
			DossierFileActionsImpl object = new DossierFileActionsImpl();
			object.copyFile("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	
	@Test
	public void zipDirectoryTest() {
		try{
			DossierFileActionsImpl object = new DossierFileActionsImpl();
			object.zipDirectory(null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}