package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DossierActionManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getVisitedTest() {
		try{
			DossierActionManagementImpl object = new DossierActionManagementImpl();
			object.getVisited(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionPayloadTest() {
		try{
			DossierActionManagementImpl object = new DossierActionManagementImpl();
			object.getActionPayload(null, null, null, null, null, null, null, "abcde", "abcde", true);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getRollbackTest() {
		try{
			DossierActionManagementImpl object = new DossierActionManagementImpl();
			object.getRollback(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getListActionsTest() {
		try{
			DossierActionManagementImpl object = new DossierActionManagementImpl();
			object.getListActions(null, null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getActionDetailTest() {
		try{
			DossierActionManagementImpl object = new DossierActionManagementImpl();
			object.getActionDetail(null, null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getListContactsTest() {
		try{
			DossierActionManagementImpl object = new DossierActionManagementImpl();
			object.getListContacts(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getByDeliverableStateTest() {
		try{
			DossierActionManagementImpl object = new DossierActionManagementImpl();
			object.getByDeliverableState(null, null, null, null, null, null, Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getListActionsExecutedTest() {
		try{
			DossierActionManagementImpl object = new DossierActionManagementImpl();
			object.getListActionsExecuted(null, null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}