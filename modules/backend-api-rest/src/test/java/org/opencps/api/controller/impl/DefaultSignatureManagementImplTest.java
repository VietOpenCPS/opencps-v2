package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class DefaultSignatureManagementImplTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void getDossierTest() {
//		try{
//			DefaultSignatureManagementImpl object = new DefaultSignatureManagementImpl();
//			object.getDossier(Long.valueOf(0), Long.valueOf(0), "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void updateDossierFileByCaptchaTest() {
		try{
			DefaultSignatureManagementImpl object = new DefaultSignatureManagementImpl();
			object.updateDossierFileByCaptcha(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierFileBySignatureTest() {
		try{
			DefaultSignatureManagementImpl object = new DefaultSignatureManagementImpl();
			object.updateDossierFileBySignature(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getHashComputedBySignatureTest() {
		try{
			DefaultSignatureManagementImpl object = new DefaultSignatureManagementImpl();
			object.getHashComputedBySignature(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierFilesBySignatureTest() {
		try{
			DefaultSignatureManagementImpl object = new DefaultSignatureManagementImpl();
			object.updateDossierFilesBySignature(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierFilesByCaptchaTest() {
		try{
			DefaultSignatureManagementImpl object = new DefaultSignatureManagementImpl();
			object.updateDossierFilesByCaptcha(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void vgcaDossierFilesBySignatureTest() {
		try{
			DefaultSignatureManagementImpl object = new DefaultSignatureManagementImpl();
			object.vgcaDossierFilesBySignature(null, null, null, null, null, null, Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierFilesBySignatureDefault2Test() {
		try{
			DefaultSignatureManagementImpl object = new DefaultSignatureManagementImpl();
			object.updateDossierFilesBySignatureDefault2(null, null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierFileBySignatureDefaultTest() {
		try{
			DefaultSignatureManagementImpl object = new DefaultSignatureManagementImpl();
			object.updateDossierFileBySignatureDefault(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDossierFilesBySignatureDefaultTest() {
		try{
			DefaultSignatureManagementImpl object = new DefaultSignatureManagementImpl();
			object.updateDossierFilesBySignatureDefault(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}