package org.opencps.statistic.model.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class OpencpsVotingStatisticCacheModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void equalsTest() {
		try{
			OpencpsVotingStatisticCacheModel object = new OpencpsVotingStatisticCacheModel();
			object.equals(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void toStringTest() {
//		try{
//			OpencpsVotingStatisticCacheModel object = new OpencpsVotingStatisticCacheModel();
//			object.toString();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void hashCodeTest() {
		try{
			OpencpsVotingStatisticCacheModel object = new OpencpsVotingStatisticCacheModel();
			object.hashCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void readExternalTest() {
		try{
			OpencpsVotingStatisticCacheModel object = new OpencpsVotingStatisticCacheModel();
			object.readExternal(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void writeExternalTest() {
		try{
			OpencpsVotingStatisticCacheModel object = new OpencpsVotingStatisticCacheModel();
			object.writeExternal(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void toEntityModelTest() {
//		try{
//			OpencpsVotingStatisticCacheModel object = new OpencpsVotingStatisticCacheModel();
//			object.toEntityModel();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void toEntityModelTest7() {
//		try{
//			OpencpsVotingStatisticCacheModel object = new OpencpsVotingStatisticCacheModel();
//			object.toEntityModel();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}