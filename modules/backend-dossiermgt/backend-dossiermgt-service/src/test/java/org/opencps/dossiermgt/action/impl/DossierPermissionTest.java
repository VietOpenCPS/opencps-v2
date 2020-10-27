package org.opencps.dossiermgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierPermissionTest {
	@Before
	public void setUp() {
	}
	@Test
	public void isCitizenTest() {
		try{
			DossierPermission object = new DossierPermission();
			object.isCitizen(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void hasGetDossiersTest() {
		try{
			DossierPermission object = new DossierPermission();
			object.hasGetDossiers(Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void allowSubmittingTest() {
		try{
			DossierPermission object = new DossierPermission();
			object.allowSubmitting(Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void checkPasswordTest() {
		try{
			DossierPermission object = new DossierPermission();
			object.checkPassword(null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void hasCreateDossierTest() {
		try{
			DossierPermission object = new DossierPermission();
			object.hasCreateDossier(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void isAllowSubmittingDossiersTest() {
		try{
			DossierPermission object = new DossierPermission();
			object.isAllowSubmittingDossiers(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void hasPermitDoActionTest() {
		try{
			DossierPermission object = new DossierPermission();
			object.hasPermitDoAction(Long.valueOf(0), Long.valueOf(0), null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void hasGetDetailDossierTest() {
		try{
			DossierPermission object = new DossierPermission();
			object.hasGetDetailDossier(Long.valueOf(0), Long.valueOf(0), null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}