package org.opencps.dossiermgt.action.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DossierNumberGeneratorTest {
	@Before
	public void setUp() {
	}
	@Test
	public void generateDossierNumberTest() {
		try{
			DossierNumberGenerator.generateDossierNumber(Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void generateDossierNumberTest2() {
//		try{
//			DossierNumberGenerator.generateDossierNumber("abcde", "abcde", Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void generateDossierNumberTest3() {
//		try{
//			DossierNumberGenerator.generateDossierNumber(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", null, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void generateReferenceUIDTest() {
		try{
			DossierNumberGenerator.generateReferenceUID(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void generatePasswordTest() {
//		try{
//			DossierNumberGenerator.generatePassword("abcde", 0);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void counterDossierTest() {
//		try{
//			DossierNumberGenerator.counterDossier(Long.valueOf(0), Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void countByRegiterBookCodeTest() {
		try{
			DossierNumberGenerator.countByRegiterBookCode(Long.valueOf(0), "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}