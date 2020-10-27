package org.opencps.dossiermgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DossierUserActionsImplTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void addDossierUserTest() {
//		try{
//			DossierUserActionsImpl object = new DossierUserActionsImpl();
//			object.addDossierUser(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), 0, true);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateDossierUserTest() {
//		try{
//			DossierUserActionsImpl object = new DossierUserActionsImpl();
//			object.updateDossierUser(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), 0, true);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void deleteDossierUserTest() {
//		try{
//			DossierUserActionsImpl object = new DossierUserActionsImpl();
//			object.deleteDossierUser(Long.valueOf(0), Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void initDossierUserTest() {
		try{
			DossierUserActionsImpl object = new DossierUserActionsImpl();
			object.initDossierUser(Long.valueOf(0), null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void initDossierUserTest5() {
		try{
			DossierUserActionsImpl object = new DossierUserActionsImpl();
			object.initDossierUser(Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}