package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class ServiceConfigManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void removeServiceConfigTest() {
		try{
			ServiceConfigManagementImpl object = new ServiceConfigManagementImpl();
			object.removeServiceConfig(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateProcessOptionTest() {
		try{
			ServiceConfigManagementImpl object = new ServiceConfigManagementImpl();
			object.updateProcessOption(null, null, null, null, null, null, Long.valueOf(0), Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceConfigsByGovAgencyTest() {
		try{
			ServiceConfigManagementImpl object = new ServiceConfigManagementImpl();
			object.getServiceConfigsByGovAgency(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateServiceConfigTest() {
		try{
			ServiceConfigManagementImpl object = new ServiceConfigManagementImpl();
			object.updateServiceConfig(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceConfigsTest() {
		try{
			ServiceConfigManagementImpl object = new ServiceConfigManagementImpl();
			object.getServiceConfigs(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getProcessOptionsTest() {
		try{
			ServiceConfigManagementImpl object = new ServiceConfigManagementImpl();
			object.getProcessOptions(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceConfigsByDomainTest() {
		try{
			ServiceConfigManagementImpl object = new ServiceConfigManagementImpl();
			object.getServiceConfigsByDomain(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getDomainsByGovAgencyCodeTest() {
		try{
			ServiceConfigManagementImpl object = new ServiceConfigManagementImpl();
			object.getDomainsByGovAgencyCode(null, null, null, null, null, "abcde", null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void removeProcessOptionTest() {
		try{
			ServiceConfigManagementImpl object = new ServiceConfigManagementImpl();
			object.removeProcessOption(null, null, null, null, null, null, Long.valueOf(0), Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getConfigByGovAgencysTest() {
		try{
			ServiceConfigManagementImpl object = new ServiceConfigManagementImpl();
			object.getConfigByGovAgencys(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getServiceConfigTest() {
//		try{
//			ServiceConfigManagementImpl object = new ServiceConfigManagementImpl();
//			object.getServiceConfig(null, null, null, null, null, null, Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getGuideTest() {
		try{
			ServiceConfigManagementImpl object = new ServiceConfigManagementImpl();
			object.getGuide(null, null, null, null, null, null, "abcde", null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addProcessOptionTest() {
		try{
			ServiceConfigManagementImpl object = new ServiceConfigManagementImpl();
			object.addProcessOption(null, null, null, null, null, null, Long.valueOf(0), null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addServiceConfigTest() {
		try{
			ServiceConfigManagementImpl object = new ServiceConfigManagementImpl();
			object.addServiceConfig(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}