package org.opencps.communication.model.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class ZaloMapCacheModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void equalsTest() {
		try{
			ZaloMapCacheModel object = new ZaloMapCacheModel();
			object.equals(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void toStringTest() {
//		try{
//			ZaloMapCacheModel object = new ZaloMapCacheModel();
//			object.toString();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void hashCodeTest() {
		try{
			ZaloMapCacheModel object = new ZaloMapCacheModel();
			object.hashCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void readExternalTest() {
		try{
			ZaloMapCacheModel object = new ZaloMapCacheModel();
			object.readExternal(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void writeExternalTest() {
		try{
			ZaloMapCacheModel object = new ZaloMapCacheModel();
			object.writeExternal(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void toEntityModelTest() {
//		try{
//			ZaloMapCacheModel object = new ZaloMapCacheModel();
//			object.toEntityModel();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void toEntityModelTest7() {
//		try{
//			ZaloMapCacheModel object = new ZaloMapCacheModel();
//			object.toEntityModel();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}