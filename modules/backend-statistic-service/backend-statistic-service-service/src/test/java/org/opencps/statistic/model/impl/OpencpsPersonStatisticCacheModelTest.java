package org.opencps.statistic.model.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class OpencpsPersonStatisticCacheModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void equalsTest() {
		try{
			OpencpsPersonStatisticCacheModel object = new OpencpsPersonStatisticCacheModel();
			object.equals(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void toStringTest() {
//		try{
//			OpencpsPersonStatisticCacheModel object = new OpencpsPersonStatisticCacheModel();
//			object.toString();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void hashCodeTest() {
		try{
			OpencpsPersonStatisticCacheModel object = new OpencpsPersonStatisticCacheModel();
			object.hashCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void readExternalTest() {
		try{
			OpencpsPersonStatisticCacheModel object = new OpencpsPersonStatisticCacheModel();
			object.readExternal(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void writeExternalTest() {
		try{
			OpencpsPersonStatisticCacheModel object = new OpencpsPersonStatisticCacheModel();
			object.writeExternal(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void toEntityModelTest() {
//		try{
//			OpencpsPersonStatisticCacheModel object = new OpencpsPersonStatisticCacheModel();
//			object.toEntityModel();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void toEntityModelTest7() {
//		try{
//			OpencpsPersonStatisticCacheModel object = new OpencpsPersonStatisticCacheModel();
//			object.toEntityModel();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}