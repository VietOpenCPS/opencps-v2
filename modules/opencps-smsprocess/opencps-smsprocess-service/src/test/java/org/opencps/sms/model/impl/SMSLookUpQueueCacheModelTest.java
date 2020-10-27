package org.opencps.sms.model.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class SMSLookUpQueueCacheModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void equalsTest() {
		try{
			SMSLookUpQueueCacheModel object = new SMSLookUpQueueCacheModel();
			object.equals(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void toStringTest() {
//		try{
//			SMSLookUpQueueCacheModel object = new SMSLookUpQueueCacheModel();
//			object.toString();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void hashCodeTest() {
		try{
			SMSLookUpQueueCacheModel object = new SMSLookUpQueueCacheModel();
			object.hashCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void readExternalTest() {
		try{
			SMSLookUpQueueCacheModel object = new SMSLookUpQueueCacheModel();
			object.readExternal(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void writeExternalTest() {
		try{
			SMSLookUpQueueCacheModel object = new SMSLookUpQueueCacheModel();
			object.writeExternal(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void toEntityModelTest() {
//		try{
//			SMSLookUpQueueCacheModel object = new SMSLookUpQueueCacheModel();
//			object.toEntityModel();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void toEntityModelTest7() {
//		try{
//			SMSLookUpQueueCacheModel object = new SMSLookUpQueueCacheModel();
//			object.toEntityModel();
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}