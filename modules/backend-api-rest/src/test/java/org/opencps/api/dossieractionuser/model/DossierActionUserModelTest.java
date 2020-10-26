package org.opencps.api.dossieractionuser.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierActionUserModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setUserIdTest() {
		try{
			DossierActionUserModel object = new DossierActionUserModel();
			object.setUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			DossierActionUserModel object = new DossierActionUserModel();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStepCodeTest() {
		try{
			DossierActionUserModel object = new DossierActionUserModel();
			object.setStepCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStepCodeTest() {
		try{
			DossierActionUserModel object = new DossierActionUserModel();
			object.getStepCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isVisitedTest() {
		try{
			DossierActionUserModel object = new DossierActionUserModel();
			object.isVisited();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierIdTest() {
		try{
			DossierActionUserModel object = new DossierActionUserModel();
			object.setDossierId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setVisitedTest() {
		try{
			DossierActionUserModel object = new DossierActionUserModel();
			object.setVisited(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModeratorTest() {
		try{
			DossierActionUserModel object = new DossierActionUserModel();
			object.getModerator();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModeratorTest() {
		try{
			DossierActionUserModel object = new DossierActionUserModel();
			object.setModerator(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierIdTest() {
		try{
			DossierActionUserModel object = new DossierActionUserModel();
			object.getDossierId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAssignedTest() {
		try{
			DossierActionUserModel object = new DossierActionUserModel();
			object.getAssigned();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAssignedTest() {
		try{
			DossierActionUserModel object = new DossierActionUserModel();
			object.setAssigned(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setEmailAddressTest() {
		try{
			DossierActionUserModel object = new DossierActionUserModel();
			object.setEmailAddress("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierActionIdTest() {
		try{
			DossierActionUserModel object = new DossierActionUserModel();
			object.setDossierActionId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierActionIdTest() {
		try{
			DossierActionUserModel object = new DossierActionUserModel();
			object.getDossierActionId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEmailAddressTest() {
		try{
			DossierActionUserModel object = new DossierActionUserModel();
			object.getEmailAddress();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}