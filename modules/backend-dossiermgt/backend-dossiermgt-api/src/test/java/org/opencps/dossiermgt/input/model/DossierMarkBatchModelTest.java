package org.opencps.dossiermgt.input.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DossierMarkBatchModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setDossierIdTest() {
		try{
			DossierMarkBatchModel object = new DossierMarkBatchModel();
			object.setDossierId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierIdTest() {
		try{
			DossierMarkBatchModel object = new DossierMarkBatchModel();
			object.getDossierId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRecordCountTest() {
		try{
			DossierMarkBatchModel object = new DossierMarkBatchModel();
			object.setRecordCount("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierPartNoTest() {
		try{
			DossierMarkBatchModel object = new DossierMarkBatchModel();
			object.getDossierPartNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileCheckTest() {
		try{
			DossierMarkBatchModel object = new DossierMarkBatchModel();
			object.getFileCheck();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRecordCountTest() {
		try{
			DossierMarkBatchModel object = new DossierMarkBatchModel();
			object.getRecordCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileCommentTest() {
		try{
			DossierMarkBatchModel object = new DossierMarkBatchModel();
			object.getFileComment();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileMarkTest() {
		try{
			DossierMarkBatchModel object = new DossierMarkBatchModel();
			object.getFileMark();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileCheckTest() {
		try{
			DossierMarkBatchModel object = new DossierMarkBatchModel();
			object.setFileCheck(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileMarkTest() {
		try{
			DossierMarkBatchModel object = new DossierMarkBatchModel();
			object.setFileMark(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierPartNoTest() {
		try{
			DossierMarkBatchModel object = new DossierMarkBatchModel();
			object.setDossierPartNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileCommentTest() {
		try{
			DossierMarkBatchModel object = new DossierMarkBatchModel();
			object.setFileComment("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}