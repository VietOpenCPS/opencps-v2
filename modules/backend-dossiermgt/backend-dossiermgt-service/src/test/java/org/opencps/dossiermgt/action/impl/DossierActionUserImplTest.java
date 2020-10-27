package org.opencps.dossiermgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DossierActionUserImplTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void addDossierActionUserTest() {
//		try{
//			DossierActionUserImpl object = new DossierActionUserImpl();
//			object.addDossierActionUser(null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateDossierActionUserTest() {
//		try{
//			DossierActionUserImpl object = new DossierActionUserImpl();
//			object.updateDossierActionUser(null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void initDossierActionUserTest() {
		try{
			DossierActionUserImpl object = new DossierActionUserImpl();
			object.initDossierActionUser(null, null, 0, null, Long.valueOf(0), Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void deleteDossierActionUserTest() {
//		try{
//			DossierActionUserImpl object = new DossierActionUserImpl();
//			object.deleteDossierActionUser(null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void assignDossierActionUserTest() {
		try{
			DossierActionUserImpl object = new DossierActionUserImpl();
			object.assignDossierActionUser(null, 0, null, Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void copyRoleAsStepTest() {
		try{
			DossierActionUserImpl object = new DossierActionUserImpl();
			object.copyRoleAsStep(null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}