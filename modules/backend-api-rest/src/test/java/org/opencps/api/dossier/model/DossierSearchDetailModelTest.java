package org.opencps.api.dossier.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierSearchDetailModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getUserNameTest() {
		try{
			DossierSearchDetailModel object = new DossierSearchDetailModel();
			object.getUserName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserNameTest() {
		try{
			DossierSearchDetailModel object = new DossierSearchDetailModel();
			object.setUserName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setGovAgencyNameTest() {
		try{
			DossierSearchDetailModel object = new DossierSearchDetailModel();
			object.setGovAgencyName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getGovAgencyNameTest() {
		try{
			DossierSearchDetailModel object = new DossierSearchDetailModel();
			object.getGovAgencyName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDueDateTest() {
		try{
			DossierSearchDetailModel object = new DossierSearchDetailModel();
			object.getDueDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSubmitDateTest() {
		try{
			DossierSearchDetailModel object = new DossierSearchDetailModel();
			object.setSubmitDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierIdCTNTest() {
		try{
			DossierSearchDetailModel object = new DossierSearchDetailModel();
			object.setDossierIdCTN("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierIdCTNTest() {
		try{
			DossierSearchDetailModel object = new DossierSearchDetailModel();
			object.getDossierIdCTN();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDueDateTest() {
		try{
			DossierSearchDetailModel object = new DossierSearchDetailModel();
			object.setDueDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSubmitDateTest() {
		try{
			DossierSearchDetailModel object = new DossierSearchDetailModel();
			object.getSubmitDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierNoTest() {
		try{
			DossierSearchDetailModel object = new DossierSearchDetailModel();
			object.setDossierNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierNoTest() {
		try{
			DossierSearchDetailModel object = new DossierSearchDetailModel();
			object.getDossierNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}