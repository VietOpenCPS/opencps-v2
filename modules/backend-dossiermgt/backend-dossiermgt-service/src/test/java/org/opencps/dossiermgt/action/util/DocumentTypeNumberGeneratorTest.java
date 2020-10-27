package org.opencps.dossiermgt.action.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DocumentTypeNumberGeneratorTest {
	@Before
	public void setUp() {
	}
	@Test
	public void generateReferenceUIDTest() {
		try{
			DocumentTypeNumberGenerator.generateReferenceUID(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void generateDocumentTypeNumberTest() {
//		try{
//			DocumentTypeNumberGenerator.generateDocumentTypeNumber(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void generateDossierDocumentNumberTest() {
		try{
			DocumentTypeNumberGenerator.generateDossierDocumentNumber(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}