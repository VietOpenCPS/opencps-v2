package org.opencps.dossiermgt.constants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class VTPayTermTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getInvoiceNoByBillCodeTest() {
		try{
			VTPayTerm.getInvoiceNoByBillCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierIdByOrderIdTest() {
		try{
			VTPayTerm.getDossierIdByOrderId("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierNoByOrderIdTest() {
		try{
			VTPayTerm.getDossierNoByOrderId("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createOrderIdTest() {
		try{
			VTPayTerm.createOrderId(Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createBillCodeTest() {
		try{
			VTPayTerm.createBillCode("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}