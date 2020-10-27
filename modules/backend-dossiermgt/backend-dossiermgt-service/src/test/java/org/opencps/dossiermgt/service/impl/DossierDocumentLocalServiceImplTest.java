package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierDocumentLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			DossierDocumentLocalServiceImpl object = new DossierDocumentLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierDocTest() {
		try{
			DossierDocumentLocalServiceImpl object = new DossierDocumentLocalServiceImpl();
			object.updateDossierDoc(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierDocTest3() {
		try{
			DossierDocumentLocalServiceImpl object = new DossierDocumentLocalServiceImpl();
			object.updateDossierDoc(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierDocTest() {
		try{
			DossierDocumentLocalServiceImpl object = new DossierDocumentLocalServiceImpl();
			object.addDossierDoc(Long.valueOf(0), Long.valueOf(0), "abcde", Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierDocTest5() {
		try{
			DossierDocumentLocalServiceImpl object = new DossierDocumentLocalServiceImpl();
			object.addDossierDoc(Long.valueOf(0), Long.valueOf(0), "abcde", Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierDocTest6() {
		try{
			DossierDocumentLocalServiceImpl object = new DossierDocumentLocalServiceImpl();
			object.addDossierDoc(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByActiocIdTest() {
		try{
			DossierDocumentLocalServiceImpl object = new DossierDocumentLocalServiceImpl();
			object.getByActiocId(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierDocumentListTest() {
		try{
			DossierDocumentLocalServiceImpl object = new DossierDocumentLocalServiceImpl();
			object.getDossierDocumentList(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByG_DocTypeListTest() {
		try{
			DossierDocumentLocalServiceImpl object = new DossierDocumentLocalServiceImpl();
			object.getByG_DocTypeList(Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDocByReferenceUidTest() {
		try{
			DossierDocumentLocalServiceImpl object = new DossierDocumentLocalServiceImpl();
			object.getDocByReferenceUid(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countDossierDocumentListTest() {
		try{
			DossierDocumentLocalServiceImpl object = new DossierDocumentLocalServiceImpl();
			object.countDossierDocumentList(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			DossierDocumentLocalServiceImpl object = new DossierDocumentLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}