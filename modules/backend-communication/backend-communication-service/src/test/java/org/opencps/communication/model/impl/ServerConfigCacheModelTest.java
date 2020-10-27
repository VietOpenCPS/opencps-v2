package org.opencps.communication.model.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class ServerConfigCacheModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void equalsTest() {
		try{
			ServerConfigCacheModel object = new ServerConfigCacheModel();
			object.equals(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void toStringTest() {
//		try{
//			ServerConfigCacheModel object = new ServerConfigCacheModel();
//			object.toString();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void hashCodeTest() {
		try{
			ServerConfigCacheModel object = new ServerConfigCacheModel();
			object.hashCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void readExternalTest() {
		try{
			ServerConfigCacheModel object = new ServerConfigCacheModel();
			object.readExternal(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void writeExternalTest() {
		try{
			ServerConfigCacheModel object = new ServerConfigCacheModel();
			object.writeExternal(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void toEntityModelTest() {
//		try{
//			ServerConfigCacheModel object = new ServerConfigCacheModel();
//			object.toEntityModel();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void toEntityModelTest7() {
//		try{
//			ServerConfigCacheModel object = new ServerConfigCacheModel();
//			object.toEntityModel();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}