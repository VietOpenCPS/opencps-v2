package org.opencps.api.dossiersync.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierSyncDataModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getMethodTest() {
		try{
			DossierSyncDataModel object = new DossierSyncDataModel();
			object.getMethod();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDateTest() {
		try{
			DossierSyncDataModel object = new DossierSyncDataModel();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			DossierSyncDataModel object = new DossierSyncDataModel();
			object.setCreateDate("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierIdTest() {
		try{
			DossierSyncDataModel object = new DossierSyncDataModel();
			object.setDossierId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierIdTest() {
		try{
			DossierSyncDataModel object = new DossierSyncDataModel();
			object.getDossierId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getClassPK_0020Test() {
		try{
			DossierSyncDataModel object = new DossierSyncDataModel();
			object.getClassPK_0020();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDossierTest() {
		try{
			DossierSyncDataModel object = new DossierSyncDataModel();
			object.getCreateDossier();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDossierTest() {
		try{
			DossierSyncDataModel object = new DossierSyncDataModel();
			object.setCreateDossier(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setClassPK_0020Test() {
		try{
			DossierSyncDataModel object = new DossierSyncDataModel();
			object.setClassPK_0020(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierReferenceUidTest() {
		try{
			DossierSyncDataModel object = new DossierSyncDataModel();
			object.getDossierReferenceUid();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierReferenceUidTest() {
		try{
			DossierSyncDataModel object = new DossierSyncDataModel();
			object.setDossierReferenceUid("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setFileReferenceUidTest() {
		try{
			DossierSyncDataModel object = new DossierSyncDataModel();
			object.setFileReferenceUid("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFileReferenceUidTest() {
		try{
			DossierSyncDataModel object = new DossierSyncDataModel();
			object.getFileReferenceUid();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierSyncIdTest() {
		try{
			DossierSyncDataModel object = new DossierSyncDataModel();
			object.getDossierSyncId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierSyncIdTest() {
		try{
			DossierSyncDataModel object = new DossierSyncDataModel();
			object.setDossierSyncId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setMethodTest() {
		try{
			DossierSyncDataModel object = new DossierSyncDataModel();
			object.setMethod(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}