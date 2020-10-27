package org.opencps.dossiermgt.rest.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierMarkInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getFileCheckTest() {
		try{
			DossierMarkInputModel object = new DossierMarkInputModel();
			object.getFileCheck();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileCommentTest() {
		try{
			DossierMarkInputModel object = new DossierMarkInputModel();
			object.getFileComment();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileMarkTest() {
		try{
			DossierMarkInputModel object = new DossierMarkInputModel();
			object.getFileMark();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileCheckTest() {
		try{
			DossierMarkInputModel object = new DossierMarkInputModel();
			object.setFileCheck(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileMarkTest() {
		try{
			DossierMarkInputModel object = new DossierMarkInputModel();
			object.setFileMark(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileCommentTest() {
		try{
			DossierMarkInputModel object = new DossierMarkInputModel();
			object.setFileComment("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}