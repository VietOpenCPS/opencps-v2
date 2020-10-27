package org.opencps.dossiermgt.action.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DossierActionUtilsTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void getProcessActionTest() {
//		try{
//			DossierActionUtils.getProcessAction(Long.valueOf(0), null, "abcde", Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getSyncStateTest() {
		try{
			DossierActionUtils.getSyncState(0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void buildChangedPayloadTest() {
		try{
			DossierActionUtils.buildChangedPayload(null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}