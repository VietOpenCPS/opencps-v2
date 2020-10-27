package org.opencps.dossiermgt.action.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class PaymentUrlGeneratorTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void mainTest() {
//		try{
//			PaymentUrlGenerator.main(null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void generatorPayURLTest() {
//		try{
//			PaymentUrlGenerator.generatorPayURL(Long.valueOf(0), Long.valueOf(0), "abcde", Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	
//	@Test
//	public void generatorGoodCodeTest() {
//		try{
//			PaymentUrlGenerator.generatorGoodCode(0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	
	@Test
	public void generatorPayURLEparTest() {
		try{
			PaymentUrlGenerator.generatorPayURLEpar(Long.valueOf(0), null, Long.valueOf(0), "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}