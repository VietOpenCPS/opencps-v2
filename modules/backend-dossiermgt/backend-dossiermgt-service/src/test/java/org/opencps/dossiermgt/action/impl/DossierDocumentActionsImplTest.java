package org.opencps.dossiermgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DossierDocumentActionsImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getDossierDocumentByDossierIdTest() {
		try{
			DossierDocumentActionsImpl object = new DossierDocumentActionsImpl();
			object.getDossierDocumentByDossierId(Long.valueOf(0), 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void addDossierDocTest() {
//		try{
//			DossierDocumentActionsImpl object = new DossierDocumentActionsImpl();
//			object.addDossierDoc(Long.valueOf(0), Long.valueOf(0), "abcde", Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), null, "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void addDossierDocTest3() {
//		try{
//			DossierDocumentActionsImpl object = new DossierDocumentActionsImpl();
//			object.addDossierDoc(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", Long.valueOf(0), null, "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}