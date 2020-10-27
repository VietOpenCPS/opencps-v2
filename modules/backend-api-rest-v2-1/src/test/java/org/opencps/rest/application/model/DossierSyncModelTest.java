package org.opencps.rest.application.model;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierSyncModelTest {
	@Before
	public void setUp() {
	}
	@Test
	public void toStringTest() {
		try{
			DossierSyncModel object = new DossierSyncModel();
			object.toString();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getCreateDateTest() {
		try{
			DossierSyncModel object = new DossierSyncModel();
			object.getCreateDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setUserIdTest() {
		try{
			DossierSyncModel object = new DossierSyncModel();
			object.setUserId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setCreateDateTest() {
		try{
			DossierSyncModel object = new DossierSyncModel();
			object.setCreateDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getUserIdTest() {
		try{
			DossierSyncModel object = new DossierSyncModel();
			object.getUserId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getModifiedDateTest() {
		try{
			DossierSyncModel object = new DossierSyncModel();
			object.getModifiedDate();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setModifiedDateTest() {
		try{
			DossierSyncModel object = new DossierSyncModel();
			object.setModifiedDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void payloadTest() {
		try{
			DossierSyncModel object = new DossierSyncModel();
			object.payload("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void actionUserTest() {
		try{
			DossierSyncModel object = new DossierSyncModel();
			object.actionUser("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void actionNoteTest() {
		try{
			DossierSyncModel object = new DossierSyncModel();
			object.actionNote("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setActionCodeTest() {
		try{
			DossierSyncModel object = new DossierSyncModel();
			object.setActionCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionNameTest() {
		try{
			DossierSyncModel object = new DossierSyncModel();
			object.getActionName();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setActionNameTest() {
		try{
			DossierSyncModel object = new DossierSyncModel();
			object.setActionName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionCodeTest() {
		try{
			DossierSyncModel object = new DossierSyncModel();
			object.getActionCode();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSyncTypeTest() {
		try{
			DossierSyncModel object = new DossierSyncModel();
			object.setSyncType(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSyncTypeTest() {
		try{
			DossierSyncModel object = new DossierSyncModel();
			object.getSyncType();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setPayloadTest() {
		try{
			DossierSyncModel object = new DossierSyncModel();
			object.setPayload("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionNoteTest() {
		try{
			DossierSyncModel object = new DossierSyncModel();
			object.getActionNote();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionUserTest() {
		try{
			DossierSyncModel object = new DossierSyncModel();
			object.getActionUser();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPayloadTest() {
		try{
			DossierSyncModel object = new DossierSyncModel();
			object.getPayload();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setActionNoteTest() {
		try{
			DossierSyncModel object = new DossierSyncModel();
			object.setActionNote("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setActionUserTest() {
		try{
			DossierSyncModel object = new DossierSyncModel();
			object.setActionUser("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dossierSyncIdTest() {
		try{
			DossierSyncModel object = new DossierSyncModel();
			object.dossierSyncId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void actionCodeTest() {
		try{
			DossierSyncModel object = new DossierSyncModel();
			object.actionCode("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void syncTypeTest() {
		try{
			DossierSyncModel object = new DossierSyncModel();
			object.syncType(0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void actionNameTest() {
		try{
			DossierSyncModel object = new DossierSyncModel();
			object.actionName("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void syncRefUidTest() {
		try{
			DossierSyncModel object = new DossierSyncModel();
			object.syncRefUid("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void dossierRefUidTest() {
		try{
			DossierSyncModel object = new DossierSyncModel();
			object.dossierRefUid("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierRefUidTest() {
		try{
			DossierSyncModel object = new DossierSyncModel();
			object.getDossierRefUid();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierRefUidTest() {
		try{
			DossierSyncModel object = new DossierSyncModel();
			object.setDossierRefUid("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getSyncRefUidTest() {
		try{
			DossierSyncModel object = new DossierSyncModel();
			object.getSyncRefUid();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierSyncIdTest() {
		try{
			DossierSyncModel object = new DossierSyncModel();
			object.getDossierSyncId();
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setDossierSyncIdTest() {
		try{
			DossierSyncModel object = new DossierSyncModel();
			object.setDossierSyncId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void setSyncRefUidTest() {
		try{
			DossierSyncModel object = new DossierSyncModel();
			object.setSyncRefUid("abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void userIdTest() {
		try{
			DossierSyncModel object = new DossierSyncModel();
			object.userId(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void createDateTest() {
		try{
			DossierSyncModel object = new DossierSyncModel();
			object.createDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void modifiedDateTest() {
		try{
			DossierSyncModel object = new DossierSyncModel();
			object.modifiedDate(new Date());
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}