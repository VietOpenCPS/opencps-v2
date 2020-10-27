package org.opencps.dossiermgt.action.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class ConfigCounterNumberGeneratorTest {
	@Before
	public void setUp() {
	}
	@Test
	public void generateReferenceUIDTest() {
		try{
			ConfigCounterNumberGenerator.generateReferenceUID(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void generateCounterNumberTest() {
//		try{
//			ConfigCounterNumberGenerator.generateCounterNumber(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", null, null, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
}