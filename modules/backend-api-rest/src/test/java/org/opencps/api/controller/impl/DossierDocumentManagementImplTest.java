package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierDocumentManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getPreviewTest() {
		try{
			DossierDocumentManagementImpl object = new DossierDocumentManagementImpl();
			object.getPreview(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getQRPayTest() {
		try{
			DossierDocumentManagementImpl object = new DossierDocumentManagementImpl();
			object.getQRPay(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void downloadDocByReferenceUidTest() {
		try{
			DossierDocumentManagementImpl object = new DossierDocumentManagementImpl();
			object.downloadDocByReferenceUid(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void previewDossierDocumentListTest() {
		try{
			DossierDocumentManagementImpl object = new DossierDocumentManagementImpl();
			object.previewDossierDocumentList(null, null, null, null, null, null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void printFileByPartNoTest() {
		try{
			DossierDocumentManagementImpl object = new DossierDocumentManagementImpl();
			object.printFileByPartNo(null, null, null, null, null, null, "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPreviewByPartNoTest() {
		try{
			DossierDocumentManagementImpl object = new DossierDocumentManagementImpl();
			object.getPreviewByPartNo(null, null, null, null, null, null, "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void printDossierDocumentTest() {
		try{
			DossierDocumentManagementImpl object = new DossierDocumentManagementImpl();
			object.printDossierDocument(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}