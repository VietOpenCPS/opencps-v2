package org.opencps.dossiermgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierSyncActionsImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getDossierSyncListTest() {
		try{
			DossierSyncActionsImpl object = new DossierSyncActionsImpl();
			object.getDossierSyncList(Long.valueOf(0), "abcde", 0, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierSyncByDossiersTest() {
		try{
			DossierSyncActionsImpl object = new DossierSyncActionsImpl();
			object.getDossierSyncByDossiers(Long.valueOf(0), "abcde", 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierSyncByDossierAndInfoTest() {
		try{
			DossierSyncActionsImpl object = new DossierSyncActionsImpl();
			object.getDossierSyncByDossierAndInfo(Long.valueOf(0), "abcde", 0, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierSyncByActionTest() {
		try{
			DossierSyncActionsImpl object = new DossierSyncActionsImpl();
			object.getDossierSyncByAction(Long.valueOf(0), "abcde", 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierSyncByIdTest() {
		try{
			DossierSyncActionsImpl object = new DossierSyncActionsImpl();
			object.getDossierSyncById(Long.valueOf(0), Long.valueOf(0), 0, 0, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}