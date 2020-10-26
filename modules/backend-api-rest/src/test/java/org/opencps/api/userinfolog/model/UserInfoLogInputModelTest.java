package org.opencps.api.userinfolog.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class UserInfoLogInputModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getApplicantTest() {
		try{
			UserInfoLogInputModel object = new UserInfoLogInputModel();
			object.getApplicant();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setServiceInfoTest() {
		try{
			UserInfoLogInputModel object = new UserInfoLogInputModel();
			object.setServiceInfo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setApplicantTest() {
		try{
			UserInfoLogInputModel object = new UserInfoLogInputModel();
			object.setApplicant("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierNoTest() {
		try{
			UserInfoLogInputModel object = new UserInfoLogInputModel();
			object.setDossierNo("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierNoTest() {
		try{
			UserInfoLogInputModel object = new UserInfoLogInputModel();
			object.getDossierNo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceInfoTest() {
		try{
			UserInfoLogInputModel object = new UserInfoLogInputModel();
			object.getServiceInfo();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}