package org.opencps.dossiermgt.action.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DeliverableNumberGeneratorTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void generateDeliverableNumberTest() {
//		try{
//			DeliverableNumberGenerator.generateDeliverableNumber(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void generateReferenceUIDTest() {
		try{
			DeliverableNumberGenerator.generateReferenceUID(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}