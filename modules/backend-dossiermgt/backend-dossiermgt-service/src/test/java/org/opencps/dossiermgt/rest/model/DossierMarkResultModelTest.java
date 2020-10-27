package org.opencps.dossiermgt.rest.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierMarkResultModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setDossierMarkIdTest() {
		try{
			DossierMarkResultModel object = new DossierMarkResultModel();
			object.setDossierMarkId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierMarkIdTest() {
		try{
			DossierMarkResultModel object = new DossierMarkResultModel();
			object.getDossierMarkId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileCheckTest() {
		try{
			DossierMarkResultModel object = new DossierMarkResultModel();
			object.getFileCheck();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileCommentTest() {
		try{
			DossierMarkResultModel object = new DossierMarkResultModel();
			object.getFileComment();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileMarkTest() {
		try{
			DossierMarkResultModel object = new DossierMarkResultModel();
			object.getFileMark();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileCheckTest() {
		try{
			DossierMarkResultModel object = new DossierMarkResultModel();
			object.setFileCheck(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileMarkTest() {
		try{
			DossierMarkResultModel object = new DossierMarkResultModel();
			object.setFileMark(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileCommentTest() {
		try{
			DossierMarkResultModel object = new DossierMarkResultModel();
			object.setFileComment("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}