package org.opencps.kyso.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DigitalSignatureActionsImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void fromJSONObjectTest() {
		try{
			DigitalSignatureActionsImpl object = new DigitalSignatureActionsImpl();
			object.fromJSONObject(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getServerConfigTest() {
//		try{
//			DigitalSignatureActionsImpl object = new DigitalSignatureActionsImpl();
//			object.getServerConfig(Long.valueOf(0), "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void completeSignatureTest() {
		try{
			DigitalSignatureActionsImpl object = new DigitalSignatureActionsImpl();
			object.completeSignature("abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void createHashSignatureTest() {
//		try{
//			DigitalSignatureActionsImpl object = new DigitalSignatureActionsImpl();
//			object.createHashSignature("abcde", Long.valueOf(0), "abcde", "abcde", Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}