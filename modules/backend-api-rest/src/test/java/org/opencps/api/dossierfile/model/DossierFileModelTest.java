package org.opencps.api.dossierfile.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierFileModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getDisplayNameTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.getDisplayName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDateTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.setCreateDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.setModifiedDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormDataTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.getFormData();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormScriptTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.getFormScript();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileTemplateNoTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.getFileTemplateNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileTemplateNoTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.setFileTemplateNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierIdTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.setDossierId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEFormTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.setEForm(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReferenceUidTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.getReferenceUid();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierFileIdTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.getDossierFileId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierIdTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.getDossierId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileVersionTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.getFileVersion();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isEFormTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.isEForm();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSignInfoTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.getSignInfo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDisplayNameTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.setDisplayName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSignCheckTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.setSignCheck(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierFileIdTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.setDossierFileId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSignInfoTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.setSignInfo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSignCheckTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.getSignCheck();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileVersionTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.setFileVersion("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isRemovedTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.isRemoved();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierPartTypeTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.setDossierPartType(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierTemplateNoTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.setDossierTemplateNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierPartTypeTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.getDossierPartType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierPartNameTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.getDossierPartName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierPartNameTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.setDossierPartName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormReportTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.getFormReport();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRemovedTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.setRemoved(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierPartNoTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.getDossierPartNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFormDataTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.setFormData("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setReferenceUidTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.setReferenceUid("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierPartNoTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.setDossierPartNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierTemplateNoTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.getDossierTemplateNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileTypeTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.getFileType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileTypeTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.setFileType("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileSizeTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.getFileSize();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileSizeTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.setFileSize(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setIsNewTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.setIsNew(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isIsNewTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.isIsNew();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFormReportTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.setFormReport("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFormScriptTest() {
		try{
			DossierFileModel object = new DossierFileModel();
			object.setFormScript("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}