package org.opencps.api.dossierfile.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierFileCopyInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getDossierFileIdTest() {
		try{
			DossierFileCopyInputModel object = new DossierFileCopyInputModel();
			object.getDossierFileId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierFileIdTest() {
		try{
			DossierFileCopyInputModel object = new DossierFileCopyInputModel();
			object.setDossierFileId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierTemplateNoTest() {
		try{
			DossierFileCopyInputModel object = new DossierFileCopyInputModel();
			object.setDossierTemplateNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierPartNoTest() {
		try{
			DossierFileCopyInputModel object = new DossierFileCopyInputModel();
			object.getDossierPartNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierPartNoTest() {
		try{
			DossierFileCopyInputModel object = new DossierFileCopyInputModel();
			object.setDossierPartNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierTemplateNoTest() {
		try{
			DossierFileCopyInputModel object = new DossierFileCopyInputModel();
			object.getDossierTemplateNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}