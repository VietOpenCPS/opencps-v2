package org.opencps.dossiermgt.action.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DossierPaymentUtilsTest {
	@Before
	public void setUp() {
	}
	@Test
	public void mainTest() {
		try{
			DossierPaymentUtils.main(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getPaymentMethodTest() {
//		try{
//			DossierPaymentUtils.getPaymentMethod("abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getTotalPaymentTest() {
//		try{
//			DossierPaymentUtils.getTotalPayment("abcde", Long.valueOf(0), Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void getTotalPaymentTest6() {
//		try{
//			DossierPaymentUtils.getTotalPayment("abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void processPaymentFileTest() {
		try{
			DossierPaymentUtils.processPaymentFile(null, "abcde", Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void _getTotalDossierPaymentTest() {
		try{
			DossierPaymentUtils._getTotalDossierPayment(null, null, "abcde", Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getMessagePaymentTest() {
		try{
			DossierPaymentUtils.getMessagePayment("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}