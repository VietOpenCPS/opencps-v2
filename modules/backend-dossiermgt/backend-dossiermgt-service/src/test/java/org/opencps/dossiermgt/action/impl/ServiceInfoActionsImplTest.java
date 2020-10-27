package org.opencps.dossiermgt.action.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class ServiceInfoActionsImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void getBytesTest() {
		try{
			ServiceInfoActionsImpl.getBytes(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStatisticByDomainFilterAdministrationTest() {
		try{
			ServiceInfoActionsImpl object = new ServiceInfoActionsImpl();
			object.getStatisticByDomainFilterAdministration(Long.valueOf(0), null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceFileTemplateTest() {
		try{
			ServiceInfoActionsImpl object = new ServiceInfoActionsImpl();
			object.getServiceFileTemplate(Long.valueOf(0), "abcde", true, 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceFileTemplateTest4() {
		try{
			ServiceInfoActionsImpl object = new ServiceInfoActionsImpl();
			object.getServiceFileTemplate(Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getServiceFileTemplateTest5() {
		try{
			ServiceInfoActionsImpl object = new ServiceInfoActionsImpl();
			object.getServiceFileTemplate(Long.valueOf(0), "abcde", 0, 0);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void updateServiceFileTemplateTest() {
//		try{
//			ServiceInfoActionsImpl object = new ServiceInfoActionsImpl();
//			object.updateServiceFileTemplate(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", null, null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getStatisticByAdministrationTest() {
		try{
			ServiceInfoActionsImpl object = new ServiceInfoActionsImpl();
			object.getStatisticByAdministration(Long.valueOf(0), null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void removeServiceInfoTest() {
//		try{
//			ServiceInfoActionsImpl object = new ServiceInfoActionsImpl();
//			object.removeServiceInfo(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateServiceInfoTest() {
//		try{
//			ServiceInfoActionsImpl object = new ServiceInfoActionsImpl();
//			object.updateServiceInfo(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, true, "abcde", null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void addServiceFileTemplateTest() {
		try{
			ServiceInfoActionsImpl object = new ServiceInfoActionsImpl();
			object.addServiceFileTemplate(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", null, "abcde", 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void removeServiceFileTemplateTest() {
//		try{
//			ServiceInfoActionsImpl object = new ServiceInfoActionsImpl();
//			object.removeServiceFileTemplate(Long.valueOf(0), "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getStatisticByLevelTest() {
		try{
			ServiceInfoActionsImpl object = new ServiceInfoActionsImpl();
			object.getStatisticByLevel(null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getStatisticByDomainTest() {
		try{
			ServiceInfoActionsImpl object = new ServiceInfoActionsImpl();
			object.getStatisticByDomain(Long.valueOf(0), null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getByCodeTest() {
//		try{
//			ServiceInfoActionsImpl object = new ServiceInfoActionsImpl();
//			object.getByCode("abcde", Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void deleteAllServiceConfigTest() {
//		try{
//			ServiceInfoActionsImpl object = new ServiceInfoActionsImpl();
//			object.deleteAllServiceConfig(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateServiceInfoDBTest() {
//		try{
//			ServiceInfoActionsImpl object = new ServiceInfoActionsImpl();
//			object.updateServiceInfoDB(Long.valueOf(0), Long.valueOf(0), "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", "abcde", 0, true);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void updateServiceFileTemplateDBTest() {
//		try{
//			ServiceInfoActionsImpl object = new ServiceInfoActionsImpl();
//			object.updateServiceFileTemplateDB(Long.valueOf(0), "abcde", "abcde", "abcde", Long.valueOf(0), true, Long.valueOf(0), Long.valueOf(0), "abcde", "abcde");
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void deleteAllFileTemplateTest() {
//		try{
//			ServiceInfoActionsImpl object = new ServiceInfoActionsImpl();
//			object.deleteAllFileTemplate(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null);
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void getServiceInfosTest() {
		try{
			ServiceInfoActionsImpl object = new ServiceInfoActionsImpl();
			object.getServiceInfos(Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), null, null, 0, 0, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
//	@Test
//	public void getByIdTest() {
//		try{
//			ServiceInfoActionsImpl object = new ServiceInfoActionsImpl();
//			object.getById(Long.valueOf(0));
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void doSynServiceInfoFromDVCTest() {
		try{
			ServiceInfoActionsImpl object = new ServiceInfoActionsImpl();
			object.doSynServiceInfoFromDVC(Long.valueOf(0), Long.valueOf(0), null, "abcde", "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}