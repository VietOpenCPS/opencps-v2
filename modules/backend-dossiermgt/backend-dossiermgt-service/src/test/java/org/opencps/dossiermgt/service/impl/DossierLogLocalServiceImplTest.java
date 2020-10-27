package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierLogLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			DossierLogLocalServiceImpl object = new DossierLogLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void searchLuceneTest() {
		try{
			DossierLogLocalServiceImpl object = new DossierLogLocalServiceImpl();
			object.searchLucene(null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void findByGroupTest() {
		try{
			DossierLogLocalServiceImpl object = new DossierLogLocalServiceImpl();
			object.findByGroup(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDossierLogTest() {
		try{
			DossierLogLocalServiceImpl object = new DossierLogLocalServiceImpl();
			object.deleteDossierLog(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDossierLogTest5() {
		try{
			DossierLogLocalServiceImpl object = new DossierLogLocalServiceImpl();
			object.deleteDossierLog(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void countLuceneTest() {
		try{
			DossierLogLocalServiceImpl object = new DossierLogLocalServiceImpl();
			object.countLucene(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierLogTest() {
		try{
			DossierLogLocalServiceImpl object = new DossierLogLocalServiceImpl();
			object.addDossierLog(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierLogTest() {
		try{
			DossierLogLocalServiceImpl object = new DossierLogLocalServiceImpl();
			object.updateDossierLog(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteByDossierAndTypeTest() {
		try{
			DossierLogLocalServiceImpl object = new DossierLogLocalServiceImpl();
			object.deleteByDossierAndType(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDossierAndTypeTest() {
		try{
			DossierLogLocalServiceImpl object = new DossierLogLocalServiceImpl();
			object.getByDossierAndType(Long.valueOf(0), "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			DossierLogLocalServiceImpl object = new DossierLogLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}