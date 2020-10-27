package org.opencps.statistic.model.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class OpencpsDossierStatisticManualCacheModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void equalsTest() {
		try{
			OpencpsDossierStatisticManualCacheModel object = new OpencpsDossierStatisticManualCacheModel();
			object.equals(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void toStringTest() {
//		try{
//			OpencpsDossierStatisticManualCacheModel object = new OpencpsDossierStatisticManualCacheModel();
//			object.toString();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void hashCodeTest() {
		try{
			OpencpsDossierStatisticManualCacheModel object = new OpencpsDossierStatisticManualCacheModel();
			object.hashCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void readExternalTest() {
		try{
			OpencpsDossierStatisticManualCacheModel object = new OpencpsDossierStatisticManualCacheModel();
			object.readExternal(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void writeExternalTest() {
		try{
			OpencpsDossierStatisticManualCacheModel object = new OpencpsDossierStatisticManualCacheModel();
			object.writeExternal(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void toEntityModelTest() {
//		try{
//			OpencpsDossierStatisticManualCacheModel object = new OpencpsDossierStatisticManualCacheModel();
//			object.toEntityModel();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void toEntityModelTest7() {
//		try{
//			OpencpsDossierStatisticManualCacheModel object = new OpencpsDossierStatisticManualCacheModel();
//			object.toEntityModel();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}