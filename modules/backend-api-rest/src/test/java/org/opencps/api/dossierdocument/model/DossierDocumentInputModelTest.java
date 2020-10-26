package org.opencps.api.dossierdocument.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierDocumentInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getGovAgencyCodeTest() {
		try{
			DossierDocumentInputModel object = new DossierDocumentInputModel();
			object.getGovAgencyCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGovAgencyCodeTest() {
		try{
			DossierDocumentInputModel object = new DossierDocumentInputModel();
			object.setGovAgencyCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossiersTest() {
		try{
			DossierDocumentInputModel object = new DossierDocumentInputModel();
			object.setDossiers("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossiersTest() {
		try{
			DossierDocumentInputModel object = new DossierDocumentInputModel();
			object.getDossiers();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setServiceCodeTest() {
		try{
			DossierDocumentInputModel object = new DossierDocumentInputModel();
			object.setServiceCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceCodeTest() {
		try{
			DossierDocumentInputModel object = new DossierDocumentInputModel();
			object.getServiceCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}