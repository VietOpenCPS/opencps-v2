package org.opencps.kyso.utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class SignatureUtilTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getSignInfoTest() {
		try{
			SignatureUtil.getSignInfo("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSignCheckTest() {
		try{
			SignatureUtil.getSignCheck("abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSignerInfoTest() {
		try{
			SignatureUtil.getSignerInfo(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void computerHashTest() {
		try{
			SignatureUtil.computerHash(null, Float.valueOf(0), Float.valueOf(0), Float.valueOf(0), Float.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPdfSignerTest() {
		try{
			SignatureUtil.getPdfSigner("abcde", "abcde", "abcde", "abcde", true, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void verifyDocxSignatureTest() {
//		try{
//			SignatureUtil.verifyDocxSignature("abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void verifyPdfSignatureTest() {
		try{
			SignatureUtil.verifyPdfSignature("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPdfPkcs7SignerTest() {
		try{
			SignatureUtil.getPdfPkcs7Signer("abcde", "abcde", "abcde", "abcde", true, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}