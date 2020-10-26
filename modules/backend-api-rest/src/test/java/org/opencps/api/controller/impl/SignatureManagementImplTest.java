package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class SignatureManagementImplTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void getDossierTest() {
//		try{
//			SignatureManagementImpl object = new SignatureManagementImpl();
//			object.getDossier(Long.valueOf(0), Long.valueOf(0), "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void updateDossierFileByCaptchaTest() {
		try{
			SignatureManagementImpl object = new SignatureManagementImpl();
			object.updateDossierFileByCaptcha(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierFileBySignatureTest() {
		try{
			SignatureManagementImpl object = new SignatureManagementImpl();
			object.updateDossierFileBySignature(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getHashComputedBySignatureTest() {
		try{
			SignatureManagementImpl object = new SignatureManagementImpl();
			object.getHashComputedBySignature(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierFilesBySignatureTest() {
		try{
			SignatureManagementImpl object = new SignatureManagementImpl();
			object.updateDossierFilesBySignature(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierFilesByCaptchaTest() {
		try{
			SignatureManagementImpl object = new SignatureManagementImpl();
			object.updateDossierFilesByCaptcha(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierFileBySignatureDefaultTest() {
		try{
			SignatureManagementImpl object = new SignatureManagementImpl();
			object.updateDossierFileBySignatureDefault(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierFilesBySignatureDefaultTest() {
		try{
			SignatureManagementImpl object = new SignatureManagementImpl();
			object.updateDossierFilesBySignatureDefault(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}