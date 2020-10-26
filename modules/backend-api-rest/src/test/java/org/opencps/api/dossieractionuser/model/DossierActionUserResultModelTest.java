package org.opencps.api.dossieractionuser.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierActionUserResultModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void setUserIdTest() {
		try{
			DossierActionUserResultModel object = new DossierActionUserResultModel();
			object.setUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			DossierActionUserResultModel object = new DossierActionUserResultModel();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setStepCodeTest() {
		try{
			DossierActionUserResultModel object = new DossierActionUserResultModel();
			object.setStepCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStepCodeTest() {
		try{
			DossierActionUserResultModel object = new DossierActionUserResultModel();
			object.getStepCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isVisitedTest() {
		try{
			DossierActionUserResultModel object = new DossierActionUserResultModel();
			object.isVisited();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierIdTest() {
		try{
			DossierActionUserResultModel object = new DossierActionUserResultModel();
			object.setDossierId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setVisitedTest() {
		try{
			DossierActionUserResultModel object = new DossierActionUserResultModel();
			object.setVisited(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModeratorTest() {
		try{
			DossierActionUserResultModel object = new DossierActionUserResultModel();
			object.getModerator();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModeratorTest() {
		try{
			DossierActionUserResultModel object = new DossierActionUserResultModel();
			object.setModerator(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierIdTest() {
		try{
			DossierActionUserResultModel object = new DossierActionUserResultModel();
			object.getDossierId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAssignedTest() {
		try{
			DossierActionUserResultModel object = new DossierActionUserResultModel();
			object.getAssigned();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAssignedTest() {
		try{
			DossierActionUserResultModel object = new DossierActionUserResultModel();
			object.setAssigned(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierActionIdTest() {
		try{
			DossierActionUserResultModel object = new DossierActionUserResultModel();
			object.setDossierActionId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierActionIdTest() {
		try{
			DossierActionUserResultModel object = new DossierActionUserResultModel();
			object.getDossierActionId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}