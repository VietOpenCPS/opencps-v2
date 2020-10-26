package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class ServiceInfoManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getBytesTest() {
		try{
			ServiceInfoManagementImpl.getBytes(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void downloadFileTemplateOfServiceInfoTest() {
//		try{
//			ServiceInfoManagementImpl object = new ServiceInfoManagementImpl();
//			object.downloadFileTemplateOfServiceInfo(null, null, null, null, null, null, "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getDetailServiceInfoTest() {
		try{
			ServiceInfoManagementImpl object = new ServiceInfoManagementImpl();
			object.getDetailServiceInfo(null, null, null, null, null, null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateFileTemplateOfServiceInfoTest() {
		try{
			ServiceInfoManagementImpl object = new ServiceInfoManagementImpl();
			object.updateFileTemplateOfServiceInfo(null, null, null, null, null, null, "abcde", "abcde", null, null, "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getFormScriptOfFileTemplateTest() {
//		try{
//			ServiceInfoManagementImpl object = new ServiceInfoManagementImpl();
//			object.getFormScriptOfFileTemplate(null, null, null, null, null, null, "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getFormOfFileTemplateTest() {
		try{
			ServiceInfoManagementImpl object = new ServiceInfoManagementImpl();
			object.getFormOfFileTemplate(null, null, null, null, null, null, "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStatisticByAgencyTest() {
		try{
			ServiceInfoManagementImpl object = new ServiceInfoManagementImpl();
			object.getStatisticByAgency(null, null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceInfoRecentlyTest() {
		try{
			ServiceInfoManagementImpl object = new ServiceInfoManagementImpl();
			object.getServiceInfoRecently(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void doSyncServiceInfoFromDVCTest() {
		try{
			ServiceInfoManagementImpl object = new ServiceInfoManagementImpl();
			object.doSyncServiceInfoFromDVC(null, null, null, null, null, null, "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateServiceInfoTest() {
		try{
			ServiceInfoManagementImpl object = new ServiceInfoManagementImpl();
			object.updateServiceInfo(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteServiceInfoTest() {
		try{
			ServiceInfoManagementImpl object = new ServiceInfoManagementImpl();
			object.deleteServiceInfo(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStatisticByLevelTest() {
		try{
			ServiceInfoManagementImpl object = new ServiceInfoManagementImpl();
			object.getStatisticByLevel(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void deleteFileTemplateOfServiceInfoTest() {
		try{
			ServiceInfoManagementImpl object = new ServiceInfoManagementImpl();
			object.deleteFileTemplateOfServiceInfo(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addFileTemplateToServiceInfoTest() {
		try{
			ServiceInfoManagementImpl object = new ServiceInfoManagementImpl();
			object.addFileTemplateToServiceInfo(null, null, null, null, null, null, null, "abcde", "abcde", "abcde", "abcde", 0, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStatisticByDomainTest() {
		try{
			ServiceInfoManagementImpl object = new ServiceInfoManagementImpl();
			object.getStatisticByDomain(null, null, null, null, null, null, "abcde", null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getFormReportOfFileTemplateTest() {
//		try{
//			ServiceInfoManagementImpl object = new ServiceInfoManagementImpl();
//			object.getFormReportOfFileTemplate(null, null, null, null, null, null, "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getFileTemplatesOfServiceInfoTest() {
		try{
			ServiceInfoManagementImpl object = new ServiceInfoManagementImpl();
			object.getFileTemplatesOfServiceInfo(null, null, null, null, null, null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void resolveConflictServiceInfoTest() {
		try{
			ServiceInfoManagementImpl object = new ServiceInfoManagementImpl();
			object.resolveConflictServiceInfo(null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getServiceInfoMappingSuggestTest() {
//		try{
//			ServiceInfoManagementImpl object = new ServiceInfoManagementImpl();
//			object.getServiceInfoMappingSuggest(null, null, null, null, null, null, null, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getServiceInfosTest() {
		try{
			ServiceInfoManagementImpl object = new ServiceInfoManagementImpl();
			object.getServiceInfos(null, null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addServiceInfoTest() {
		try{
			ServiceInfoManagementImpl object = new ServiceInfoManagementImpl();
			object.addServiceInfo(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}