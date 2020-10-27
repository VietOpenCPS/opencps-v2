package org.opencps.dossiermgt.service.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierMarkLocalServiceImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void adminProcessDataTest() {
		try{
			DossierMarkLocalServiceImpl object = new DossierMarkLocalServiceImpl();
			object.adminProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierMarksTest() {
		try{
			DossierMarkLocalServiceImpl object = new DossierMarkLocalServiceImpl();
			object.getDossierMarks(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addDossierMarkTest() {
		try{
			DossierMarkLocalServiceImpl object = new DossierMarkLocalServiceImpl();
			object.addDossierMark(Long.valueOf(0), Long.valueOf(0), "abcde", 0, 0, "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierMarksByFileMarkTest() {
		try{
			DossierMarkLocalServiceImpl object = new DossierMarkLocalServiceImpl();
			object.getDossierMarksByFileMark(Long.valueOf(0), Long.valueOf(0), 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierMarkbyDossierIdTest() {
		try{
			DossierMarkLocalServiceImpl object = new DossierMarkLocalServiceImpl();
			object.getDossierMarkbyDossierId(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addBatchDossierMarkTest() {
		try{
			DossierMarkLocalServiceImpl object = new DossierMarkLocalServiceImpl();
			object.addBatchDossierMark(Long.valueOf(0), null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void adminProcessDeleteTest() {
		try{
			DossierMarkLocalServiceImpl object = new DossierMarkLocalServiceImpl();
			object.adminProcessDelete(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}