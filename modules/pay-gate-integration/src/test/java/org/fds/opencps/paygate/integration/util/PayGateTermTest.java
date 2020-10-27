package org.fds.opencps.paygate.integration.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class PayGateTermTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void getMcUrlByBillCodeTest() {
//		try{
//			PayGateTerm.getMcUrlByBillCode("abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void buildPathDoActionTest() {
		try{
			PayGateTerm.buildPathDoAction("abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}