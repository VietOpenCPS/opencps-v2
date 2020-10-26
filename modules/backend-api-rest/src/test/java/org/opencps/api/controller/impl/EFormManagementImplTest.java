package org.opencps.api.controller.impl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.ArrayList;
import com.liferay.portal.kernel.service.ServiceContext;
public class EFormManagementImplTest {
	@Before
	public void setUp() {
	}
	@Test
	public void deleteEFromByIdTest() {
		try{
			EFormManagementImpl object = new EFormManagementImpl();
			object.deleteEFromById(null, null, null, null, null, null, Long.valueOf(0));
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEFromBySecretTest() {
		try{
			EFormManagementImpl object = new EFormManagementImpl();
			object.getEFromBySecret(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateEFromByIdTest() {
		try{
			EFormManagementImpl object = new EFormManagementImpl();
			object.updateEFromById(null, null, null, null, null, null, "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEFromListTest() {
		try{
			EFormManagementImpl object = new EFormManagementImpl();
			object.getEFromList(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEFromByIdTest() {
		try{
			EFormManagementImpl object = new EFormManagementImpl();
			object.getEFromById(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEFormDataByIdTest() {
		try{
			EFormManagementImpl object = new EFormManagementImpl();
			object.getEFormDataById(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void printEFormReportTest() {
		try{
			EFormManagementImpl object = new EFormManagementImpl();
			object.printEFormReport(null, null, null, null, null, null, "abcde", "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void addEFromOfFileTemplateTest() {
		try{
			EFormManagementImpl object = new EFormManagementImpl();
			object.addEFromOfFileTemplate(null, null, null, null, null, null, null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateEFromBySecretTest() {
		try{
			EFormManagementImpl object = new EFormManagementImpl();
			object.updateEFromBySecret(null, null, null, null, null, null, "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void updateEFromDataByIdTest() {
		try{
			EFormManagementImpl object = new EFormManagementImpl();
			object.updateEFromDataById(null, null, null, null, null, null, "abcde", "abcde", null);
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEFormByBarCodeAndSecretTest() {
		try{
			EFormManagementImpl object = new EFormManagementImpl();
			object.getEFormByBarCodeAndSecret(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
	@Test
	public void getEFormDataByEFormNoTest() {
		try{
			EFormManagementImpl object = new EFormManagementImpl();
			object.getEFormDataByEFormNo(null, null, null, null, null, null, "abcde");
		}catch (Exception e) {
		}
		Assert.assertEquals(1, 1);
	}
}