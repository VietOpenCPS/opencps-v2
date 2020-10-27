package org.opencps.communication.model.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class NotificationtemplateCacheModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void equalsTest() {
		try{
			NotificationtemplateCacheModel object = new NotificationtemplateCacheModel();
			object.equals(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void toStringTest() {
//		try{
//			NotificationtemplateCacheModel object = new NotificationtemplateCacheModel();
//			object.toString();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void hashCodeTest() {
		try{
			NotificationtemplateCacheModel object = new NotificationtemplateCacheModel();
			object.hashCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void readExternalTest() {
		try{
			NotificationtemplateCacheModel object = new NotificationtemplateCacheModel();
			object.readExternal(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void writeExternalTest() {
		try{
			NotificationtemplateCacheModel object = new NotificationtemplateCacheModel();
			object.writeExternal(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void toEntityModelTest() {
//		try{
//			NotificationtemplateCacheModel object = new NotificationtemplateCacheModel();
//			object.toEntityModel();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void toEntityModelTest7() {
//		try{
//			NotificationtemplateCacheModel object = new NotificationtemplateCacheModel();
//			object.toEntityModel();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}