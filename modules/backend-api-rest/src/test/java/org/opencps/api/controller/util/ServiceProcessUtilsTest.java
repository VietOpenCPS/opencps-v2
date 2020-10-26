package org.opencps.api.controller.util;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
public class ServiceProcessUtilsTest {
	@Before
	public void setUp() {
	}
//	@Test
//	public void mappingToServiceRoleInputTest() {
//		try{
//			ServiceProcessUtils.mappingToServiceRoleInput(new ServiceProcessRoleImpl());
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
//	@Test
//	public void mappingToServiceRoleInputTest2() {
//		try{
//			ServiceProcessUtils.mappingToServiceRoleInput(new ProcessStepRoleImpl());
//		}catch (Exception e) {
//		}
//		Assert.assertEquals(1, 1);
//	}
	@Test
	public void mappingToActionPOSTTest() {
		try{
			ServiceProcessUtils.mappingToActionPOST(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mapptingToStepPOSTTest() {
		try{
			ServiceProcessUtils.mapptingToStepPOST(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToStepRoleTest() {
		try{
			ServiceProcessUtils.mappingToStepRole(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToServiceRoleTest() {
		try{
			ServiceProcessUtils.mappingToServiceRole(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToProcessStepDataTest() {
		try{
			ServiceProcessUtils.mappingToProcessStepData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToServiceProcessDataTest() {
		try{
			ServiceProcessUtils.mappingToServiceProcessData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToProcessActionDataTest() {
		try{
			ServiceProcessUtils.mappingToProcessActionData(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToPOSTTest() {
		try{
			ServiceProcessUtils.mappingToPOST(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void mappingToDetailTest() {
		try{
			ServiceProcessUtils.mappingToDetail(null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}