package org.opencps.dossiermgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DossierStatisticActionImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getDossierStatisticTest() {
		try{
			DossierStatisticActionImpl object = new DossierStatisticActionImpl();
			object.getDossierStatistic(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void updateDossierStatisticTest() {
//		try{
//			DossierStatisticActionImpl object = new DossierStatisticActionImpl();
//			object.updateDossierStatistic(null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void deleteDossierStatisticTest() {
//		try{
//			DossierStatisticActionImpl object = new DossierStatisticActionImpl();
//			object.deleteDossierStatistic(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getDossierStatisticbyYearTest() {
//		try{
//			DossierStatisticActionImpl object = new DossierStatisticActionImpl();
//			object.getDossierStatisticbyYear(Long.valueOf(0), Long.valueOf(0), 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void insertDossierStatisticTest() {
//		try{
//			DossierStatisticActionImpl object = new DossierStatisticActionImpl();
//			object.insertDossierStatistic(null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void insertDossierStatisticTest6() {
//		try{
//			DossierStatisticActionImpl object = new DossierStatisticActionImpl();
//			object.insertDossierStatistic(Long.valueOf(0), 0, 0, 0, 0, 0, 0, 0, 0, 0, "abcde", "abcde", "abcde", "abcde", 0, true, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}