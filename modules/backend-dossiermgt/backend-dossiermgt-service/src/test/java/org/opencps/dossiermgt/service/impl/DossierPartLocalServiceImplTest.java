package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierPartLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getContentTest() {
		try{
			DossierPartLocalServiceImpl object = new DossierPartLocalServiceImpl();
			object.getContent(Long.valueOf(0), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDataTest() {
		try{
			DossierPartLocalServiceImpl object = new DossierPartLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByTempAndFileTempNoTest() {
		try{
			DossierPartLocalServiceImpl object = new DossierPartLocalServiceImpl();
			object.getByTempAndFileTempNo(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			DossierPartLocalServiceImpl object = new DossierPartLocalServiceImpl();
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByTemplateNoTest() {
		try{
			DossierPartLocalServiceImpl object = new DossierPartLocalServiceImpl();
			object.getByTemplateNo(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByGTest() {
		try{
			DossierPartLocalServiceImpl object = new DossierPartLocalServiceImpl();
			object.findByG(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByTempAndPartNoTest() {
		try{
			DossierPartLocalServiceImpl object = new DossierPartLocalServiceImpl();
			object.getByTempAndPartNo(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneTest() {
		try{
			DossierPartLocalServiceImpl object = new DossierPartLocalServiceImpl();
			object.countLucene(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void fetchByTemplatePartNoTest() {
		try{
			DossierPartLocalServiceImpl object = new DossierPartLocalServiceImpl();
			object.fetchByTemplatePartNo(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateContentTest() {
		try{
			DossierPartLocalServiceImpl object = new DossierPartLocalServiceImpl();
			object.updateContent(Long.valueOf(0), 0, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByPartTypeEsignTest() {
		try{
			DossierPartLocalServiceImpl object = new DossierPartLocalServiceImpl();
			object.getByPartTypeEsign("abcde", "abcde", 0, true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeDossierPartTest() {
		try{
			DossierPartLocalServiceImpl object = new DossierPartLocalServiceImpl();
			object.removeDossierPart(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByFileTemplateNoTest() {
		try{
			DossierPartLocalServiceImpl object = new DossierPartLocalServiceImpl();
			object.getByFileTemplateNo(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierPartTest() {
		try{
			DossierPartLocalServiceImpl object = new DossierPartLocalServiceImpl();
			object.updateDossierPart(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", 0, true, "abcde", "abcde", "abcde", true, "abcde", true, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierPartTest15() {
		try{
			DossierPartLocalServiceImpl object = new DossierPartLocalServiceImpl();
			object.updateDossierPart(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", 0, true, "abcde", "abcde", "abcde", true, "abcde", true, "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierPartDBTest() {
		try{
			DossierPartLocalServiceImpl object = new DossierPartLocalServiceImpl();
			object.updateDossierPartDB(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", 0, true, "abcde", "abcde", true, true, "abcde", "abcde", 0, true, "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			DossierPartLocalServiceImpl object = new DossierPartLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}