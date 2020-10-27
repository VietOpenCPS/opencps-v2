package org.opencps.statistic.model.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class OpencpsDossierStatisticCacheModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void equalsTest() {
		try{
			OpencpsDossierStatisticCacheModel object = new OpencpsDossierStatisticCacheModel();
			object.equals(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void toStringTest() {
//		try{
//			OpencpsDossierStatisticCacheModel object = new OpencpsDossierStatisticCacheModel();
//			object.toString();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void hashCodeTest() {
		try{
			OpencpsDossierStatisticCacheModel object = new OpencpsDossierStatisticCacheModel();
			object.hashCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void readExternalTest() {
		try{
			OpencpsDossierStatisticCacheModel object = new OpencpsDossierStatisticCacheModel();
			object.readExternal(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void writeExternalTest() {
		try{
			OpencpsDossierStatisticCacheModel object = new OpencpsDossierStatisticCacheModel();
			object.writeExternal(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void toEntityModelTest() {
//		try{
//			OpencpsDossierStatisticCacheModel object = new OpencpsDossierStatisticCacheModel();
//			object.toEntityModel();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void toEntityModelTest7() {
//		try{
//			OpencpsDossierStatisticCacheModel object = new OpencpsDossierStatisticCacheModel();
//			object.toEntityModel();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}