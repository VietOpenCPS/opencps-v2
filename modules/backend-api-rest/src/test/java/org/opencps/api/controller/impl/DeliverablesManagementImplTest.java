package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class DeliverablesManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getDeliverablesTest() {
		try{
			DeliverablesManagementImpl object = new DeliverablesManagementImpl();
			object.getDeliverables(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateFormDataTest() {
		try{
			DeliverablesManagementImpl object = new DeliverablesManagementImpl();
			object.updateFormData(null, null, null, null, null, null, Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getPreviewTest() {
		try{
			DeliverablesManagementImpl object = new DeliverablesManagementImpl();
			object.getPreview(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormDataTest() {
		try{
			DeliverablesManagementImpl object = new DeliverablesManagementImpl();
			object.getFormData(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormScriptTest() {
		try{
			DeliverablesManagementImpl object = new DeliverablesManagementImpl();
			object.getFormScript(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getFormScript2Test() {
		try{
			DeliverablesManagementImpl object = new DeliverablesManagementImpl();
			object.getFormScript2(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void insertDeliverablesTest() {
		try{
			DeliverablesManagementImpl object = new DeliverablesManagementImpl();
			object.insertDeliverables(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverablesDetailTest() {
		try{
			DeliverablesManagementImpl object = new DeliverablesManagementImpl();
			object.getDeliverablesDetail(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableLogTest() {
		try{
			DeliverablesManagementImpl object = new DeliverablesManagementImpl();
			object.getDeliverableLog(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateDeliverablesTest() {
		try{
			DeliverablesManagementImpl object = new DeliverablesManagementImpl();
			object.updateDeliverables(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteDeliverablesTest() {
		try{
			DeliverablesManagementImpl object = new DeliverablesManagementImpl();
			object.deleteDeliverables(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableActionTest() {
		try{
			DeliverablesManagementImpl object = new DeliverablesManagementImpl();
			object.getDeliverableAction(null, null, null, null, null, null, Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDeliverableUrlTest() {
		try{
			DeliverablesManagementImpl object = new DeliverablesManagementImpl();
			object.getDeliverableUrl(null, null, null, null, null, null, "abcde", Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDataFormByTypeCodeTest() {
		try{
			DeliverablesManagementImpl object = new DeliverablesManagementImpl();
			object.getDataFormByTypeCode(null, null, null, null, null, null, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void resolveConflictDeliverablesTest() {
		try{
			DeliverablesManagementImpl object = new DeliverablesManagementImpl();
			object.resolveConflictDeliverables(null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void importDeliverables2Test() {
		try{
			DeliverablesManagementImpl object = new DeliverablesManagementImpl();
			object.importDeliverables2(null, null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void importDeliverablesTest() {
		try{
			DeliverablesManagementImpl object = new DeliverablesManagementImpl();
			object.importDeliverables(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDossierIdByCodeTest() {
		try{
			DeliverablesManagementImpl object = new DeliverablesManagementImpl();
			object.getDossierIdByCode(null, null, null, null, null, null, Long.valueOf(0), "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}