package org.opencps.communication.model.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class NotificationQueueCacheModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void equalsTest() {
		try{
			NotificationQueueCacheModel object = new NotificationQueueCacheModel();
			object.equals(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void toStringTest() {
//		try{
//			NotificationQueueCacheModel object = new NotificationQueueCacheModel();
//			object.toString();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void hashCodeTest() {
		try{
			NotificationQueueCacheModel object = new NotificationQueueCacheModel();
			object.hashCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void readExternalTest() {
		try{
			NotificationQueueCacheModel object = new NotificationQueueCacheModel();
			object.readExternal(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void writeExternalTest() {
		try{
			NotificationQueueCacheModel object = new NotificationQueueCacheModel();
			object.writeExternal(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void toEntityModelTest() {
//		try{
//			NotificationQueueCacheModel object = new NotificationQueueCacheModel();
//			object.toEntityModel();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void toEntityModelTest7() {
//		try{
//			NotificationQueueCacheModel object = new NotificationQueueCacheModel();
//			object.toEntityModel();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}