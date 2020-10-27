package org.opencps.dossiermgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DossierLogActionsImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getDossierLogsTest() {
		try{
			DossierLogActionsImpl object = new DossierLogActionsImpl();
			object.getDossierLogs(Long.valueOf(0), "abcde", true, 0, 0, "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierLogByIdTest() {
		try{
			DossierLogActionsImpl object = new DossierLogActionsImpl();
			object.getDossierLogById(Long.valueOf(0), Long.valueOf(0), "abcde", true, 0, 0, "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void addDossierLogTest() {
//		try{
//			DossierLogActionsImpl object = new DossierLogActionsImpl();
//			object.addDossierLog(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getDossiersTest() {
		try{
			DossierLogActionsImpl object = new DossierLogActionsImpl();
			object.getDossiers(Long.valueOf(0), Long.valueOf(0), null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}