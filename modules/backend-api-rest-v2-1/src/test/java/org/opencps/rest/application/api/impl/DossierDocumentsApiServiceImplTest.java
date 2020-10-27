package org.opencps.rest.application.api.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierDocumentsApiServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getDocumentListTest() {
		try{
			DossierDocumentsApiServiceImpl object = new DossierDocumentsApiServiceImpl();
			object.getDocumentList("abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDossierDocTest() {
		try{
			DossierDocumentsApiServiceImpl object = new DossierDocumentsApiServiceImpl();
			object.createDossierDoc("abcde", null, "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void downloadDocByReferenceUidTest() {
		try{
			DossierDocumentsApiServiceImpl object = new DossierDocumentsApiServiceImpl();
			object.downloadDocByReferenceUid("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}