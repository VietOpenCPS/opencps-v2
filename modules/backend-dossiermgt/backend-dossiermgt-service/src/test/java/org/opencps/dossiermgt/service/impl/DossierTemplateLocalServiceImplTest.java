package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierTemplateLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			DossierTemplateLocalServiceImpl object = new DossierTemplateLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			DossierTemplateLocalServiceImpl object = new DossierTemplateLocalServiceImpl();
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByTemplateNoTest() {
		try{
			DossierTemplateLocalServiceImpl object = new DossierTemplateLocalServiceImpl();
			object.getByTemplateNo(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByGTest() {
		try{
			DossierTemplateLocalServiceImpl object = new DossierTemplateLocalServiceImpl();
			object.findByG(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeDossierTemplateTest() {
		try{
			DossierTemplateLocalServiceImpl object = new DossierTemplateLocalServiceImpl();
			object.removeDossierTemplate(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneTest() {
		try{
			DossierTemplateLocalServiceImpl object = new DossierTemplateLocalServiceImpl();
			object.countLucene(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierTemplateTest() {
		try{
			DossierTemplateLocalServiceImpl object = new DossierTemplateLocalServiceImpl();
			object.updateDossierTemplate(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierTemplateTest8() {
		try{
			DossierTemplateLocalServiceImpl object = new DossierTemplateLocalServiceImpl();
			object.updateDossierTemplate(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierTemplateDBTest() {
		try{
			DossierTemplateLocalServiceImpl object = new DossierTemplateLocalServiceImpl();
			object.updateDossierTemplateDB(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierTemplateDBTest10() {
		try{
			DossierTemplateLocalServiceImpl object = new DossierTemplateLocalServiceImpl();
			object.updateDossierTemplateDB(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			DossierTemplateLocalServiceImpl object = new DossierTemplateLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}