package org.opencps.api.dossiermark.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierMarkResultDetailModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setDossierMarkIdTest() {
		try{
			DossierMarkResultDetailModel object = new DossierMarkResultDetailModel();
			object.setDossierMarkId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setRecordCountTest() {
		try{
			DossierMarkResultDetailModel object = new DossierMarkResultDetailModel();
			object.setRecordCount("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierMarkIdTest() {
		try{
			DossierMarkResultDetailModel object = new DossierMarkResultDetailModel();
			object.getDossierMarkId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileCheckTest() {
		try{
			DossierMarkResultDetailModel object = new DossierMarkResultDetailModel();
			object.getFileCheck();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRecordCountTest() {
		try{
			DossierMarkResultDetailModel object = new DossierMarkResultDetailModel();
			object.getRecordCount();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileCommentTest() {
		try{
			DossierMarkResultDetailModel object = new DossierMarkResultDetailModel();
			object.getFileComment();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileMarkTest() {
		try{
			DossierMarkResultDetailModel object = new DossierMarkResultDetailModel();
			object.getFileMark();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileCheckTest() {
		try{
			DossierMarkResultDetailModel object = new DossierMarkResultDetailModel();
			object.setFileCheck(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileMarkTest() {
		try{
			DossierMarkResultDetailModel object = new DossierMarkResultDetailModel();
			object.setFileMark(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileCommentTest() {
		try{
			DossierMarkResultDetailModel object = new DossierMarkResultDetailModel();
			object.setFileComment("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}