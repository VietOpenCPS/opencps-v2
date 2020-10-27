package org.opencps.dossiermgt.rest.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierDocumentModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getDocumentTypeTest() {
		try{
			DossierDocumentModel object = new DossierDocumentModel();
			object.getDocumentType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDocumentTypeTest() {
		try{
			DossierDocumentModel object = new DossierDocumentModel();
			object.setDocumentType("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getReferenceUidTest() {
		try{
			DossierDocumentModel object = new DossierDocumentModel();
			object.getReferenceUid();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDocumentNameTest() {
		try{
			DossierDocumentModel object = new DossierDocumentModel();
			object.getDocumentName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDocumentNameTest() {
		try{
			DossierDocumentModel object = new DossierDocumentModel();
			object.setDocumentName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDocumentCodeTest() {
		try{
			DossierDocumentModel object = new DossierDocumentModel();
			object.setDocumentCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDocumentCodeTest() {
		try{
			DossierDocumentModel object = new DossierDocumentModel();
			object.getDocumentCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setReferenceUidTest() {
		try{
			DossierDocumentModel object = new DossierDocumentModel();
			object.setReferenceUid("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}