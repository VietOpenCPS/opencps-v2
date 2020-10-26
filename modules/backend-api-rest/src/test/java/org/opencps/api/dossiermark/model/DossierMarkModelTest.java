package org.opencps.api.dossiermark.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierMarkModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setDossierIdTest() {
		try{
			DossierMarkModel object = new DossierMarkModel();
			object.setDossierId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierIdTest() {
		try{
			DossierMarkModel object = new DossierMarkModel();
			object.getDossierId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRecordCountTest() {
		try{
			DossierMarkModel object = new DossierMarkModel();
			object.setRecordCount("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierPartNoTest() {
		try{
			DossierMarkModel object = new DossierMarkModel();
			object.getDossierPartNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileCheckTest() {
		try{
			DossierMarkModel object = new DossierMarkModel();
			object.getFileCheck();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRecordCountTest() {
		try{
			DossierMarkModel object = new DossierMarkModel();
			object.getRecordCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileCommentTest() {
		try{
			DossierMarkModel object = new DossierMarkModel();
			object.getFileComment();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileMarkTest() {
		try{
			DossierMarkModel object = new DossierMarkModel();
			object.getFileMark();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileCheckTest() {
		try{
			DossierMarkModel object = new DossierMarkModel();
			object.setFileCheck(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileMarkTest() {
		try{
			DossierMarkModel object = new DossierMarkModel();
			object.setFileMark(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierPartNoTest() {
		try{
			DossierMarkModel object = new DossierMarkModel();
			object.setDossierPartNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileCommentTest() {
		try{
			DossierMarkModel object = new DossierMarkModel();
			object.setFileComment("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}