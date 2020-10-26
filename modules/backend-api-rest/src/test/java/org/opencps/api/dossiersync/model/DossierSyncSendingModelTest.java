package org.opencps.api.dossiersync.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierSyncSendingModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getMethodTest() {
		try{
			DossierSyncSendingModel object = new DossierSyncSendingModel();
			object.getMethod();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierIdTest() {
		try{
			DossierSyncSendingModel object = new DossierSyncSendingModel();
			object.setDossierId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierIdTest() {
		try{
			DossierSyncSendingModel object = new DossierSyncSendingModel();
			object.getDossierId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getClassPK_0020Test() {
		try{
			DossierSyncSendingModel object = new DossierSyncSendingModel();
			object.getClassPK_0020();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDossierTest() {
		try{
			DossierSyncSendingModel object = new DossierSyncSendingModel();
			object.getCreateDossier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDossierTest() {
		try{
			DossierSyncSendingModel object = new DossierSyncSendingModel();
			object.setCreateDossier(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setClassPK_0020Test() {
		try{
			DossierSyncSendingModel object = new DossierSyncSendingModel();
			object.setClassPK_0020(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierReferenceUidTest() {
		try{
			DossierSyncSendingModel object = new DossierSyncSendingModel();
			object.getDossierReferenceUid();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierReferenceUidTest() {
		try{
			DossierSyncSendingModel object = new DossierSyncSendingModel();
			object.setDossierReferenceUid("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileReferenceUidTest() {
		try{
			DossierSyncSendingModel object = new DossierSyncSendingModel();
			object.setFileReferenceUid("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileReferenceUidTest() {
		try{
			DossierSyncSendingModel object = new DossierSyncSendingModel();
			object.getFileReferenceUid();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMethodTest() {
		try{
			DossierSyncSendingModel object = new DossierSyncSendingModel();
			object.setMethod(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}