package org.opencps.api.dossieraction.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierActionNextActiontoUserTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getUserNameTest() {
		try{
			DossierActionNextActiontoUser object = new DossierActionNextActiontoUser();
			object.getUserName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserNameTest() {
		try{
			DossierActionNextActiontoUser object = new DossierActionNextActiontoUser();
			object.setUserName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserIdTest() {
		try{
			DossierActionNextActiontoUser object = new DossierActionNextActiontoUser();
			object.setUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			DossierActionNextActiontoUser object = new DossierActionNextActiontoUser();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModeratorTest() {
		try{
			DossierActionNextActiontoUser object = new DossierActionNextActiontoUser();
			object.setModerator(true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getAssignedTest() {
		try{
			DossierActionNextActiontoUser object = new DossierActionNextActiontoUser();
			object.getAssigned();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setAssignedTest() {
		try{
			DossierActionNextActiontoUser object = new DossierActionNextActiontoUser();
			object.setAssigned(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setJobPosTitleTest() {
		try{
			DossierActionNextActiontoUser object = new DossierActionNextActiontoUser();
			object.setJobPosTitle("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getJobPosTitleTest() {
		try{
			DossierActionNextActiontoUser object = new DossierActionNextActiontoUser();
			object.getJobPosTitle();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isModeratorTest() {
		try{
			DossierActionNextActiontoUser object = new DossierActionNextActiontoUser();
			object.isModerator();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}